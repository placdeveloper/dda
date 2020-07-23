package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * ConsultaBoletoDDAServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(Transacao.class)
public class ConsultaBoletoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        debug("### Executando a consulta do boleto DDA - Transacional");

        if (ObjectUtil.isNull(mensagem)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.mensagem.nao.informada");
        }

        String linhaDigitavelCodigoBarras = obterValorString(obterParametro(mensagem, RotulosSicoobDDA.LINHA_DIGITAVEL_CODIGO_BARRAS));
        Date dataPagamento = obterValorDate(obterParametro(mensagem, RotulosSicoobDDA.DATA_PAGAMENTO));
        Short idCanal = obterValorShort(obterParametro(mensagem, Rotulos.CODIGO_CANAL));
        Integer numPac = obterValorInteger(obterParametro(mensagem, Rotulos.NUMERO_PAC));
        Integer numCooperativa = obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA));
        Long numContaCorrente = obterValorLong(obterParametro(mensagem, Rotulos.NUMERO_CONTA_CORRENTE));
        Long numIdentificadorBoletoCIP = obterValorLong(obterParametro(mensagem, RotulosSicoobDDA.NUMERO_IDENTIFICADOR_BOLETO_CIP, false));
        Integer numCooperativaCartao = obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA_CARTAO, false));

        // Substitui o número da cooperativa pela cooperativa do cartão se tiver idCanal e for ATM e possuir número de cooperativa do cartão
        if (!ObjectUtil.isEmpty(idCanal) && idCanal.equals(CanalEnum.ATM.getId()) && !ObjectUtil.isEmpty(numCooperativaCartao)) {
            debug("Número da cooperativa: " + numCooperativa);
            debug("Definindo número da cooperativa do cartão: " + numCooperativaCartao);
            numCooperativa = numCooperativaCartao;
        }

        ConsultaBoletoDDADto boletoDDADto = OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal,
                numPac, numCooperativa, numContaCorrente, numIdentificadorBoletoCIP);

        return popularGrupo(boletoDDADto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularGrupo(ConsultaBoletoDDADto boletoDDADto) throws IntegracaoTransacionalException {
        debug("### Populando grupo do retorno da consulta de boleto...");

        Grupos grupos = new Grupos();

        criarGrupoBoletoDDA(boletoDDADto, grupos);

        return grupos;
    }

    /**
     * Método responsável por criar o grupo de boletoDDA
     * 
     * @param boletoDDADto
     * @param grupos
     * @throws IntegracaoTransacionalException
     */
    private void criarGrupoBoletoDDA(ConsultaBoletoDDADto boletoDDADto, Grupos grupos) throws IntegracaoTransacionalException {
        debug("### Criando grupo da consulta de boleto...");

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        Linha linha = new Linha();
        grupo.adicionarLinha(linha);

        criarLinhasBoletoDDA(linha, boletoDDADto);
    }

    /**
     * Método responsável por criar as linhas do objeto boletoDDA
     * 
     * @param linha
     * @param dto
     * @throws IntegracaoTransacionalException
     */
    private void criarLinhasBoletoDDA(Linha linha, ConsultaBoletoDDADto dto) throws IntegracaoTransacionalException {
        debug("### Criando linhas para o grupo da consulta de boleto...");

        debug("Adicionando IspbParticipanteDestinatario: " + dto.getIspbParticipanteDestinatario());
        linha.adicionarValor(dto.getIspbParticipanteDestinatario());

        debug("Adicionando TipoPessoaBeneficiario: " + dto.getTipoPessoaBeneficiario());
        linha.adicionarValor(dto.getTipoPessoaBeneficiario());

        debug("Adicionando NumCnpjCpfBeneficiario: " + dto.getNumCnpjCpfBeneficiario());
        linha.adicionarValor(dto.getNumCnpjCpfBeneficiario());

        debug("Adicionando NomeRazaoSocialBeneficiario: " + dto.getNomeRazaoSocialBeneficiario());
        linha.adicionarValor(dto.getNomeRazaoSocialBeneficiario());

        debug("Adicionando NomeFantasiaBeneficiario: " + dto.getNomeFantasiaBeneficiario());
        linha.adicionarValor(dto.getNomeFantasiaBeneficiario());

        debug("Adicionando TipoPessoaSacadorAvalista: " + dto.getTipoPessoaSacadorAvalista());
        linha.adicionarValor(dto.getTipoPessoaSacadorAvalista());

        debug("Adicionando NumCnpjCpfSacadorAvalista: " + dto.getNumCnpjCpfSacadorAvalista());
        linha.adicionarValor(dto.getNumCnpjCpfSacadorAvalista());

        debug("Adicionando NomeRazaoSocialSacadorAvalista: " + dto.getNomeRazaoSocialSacadorAvalista());
        linha.adicionarValor(dto.getNomeRazaoSocialSacadorAvalista());

        debug("Adicionando NomeFantasiaSacadorAvalista: " + dto.getNomeFantasiaSacadorAvalista());
        linha.adicionarValor(dto.getNomeFantasiaSacadorAvalista());

        debug("Adicionando TipoPessoaPagador: " + dto.getTipoPessoaPagador());
        linha.adicionarValor(dto.getTipoPessoaPagador());

        debug("Adicionando NumCnpjCpfPagador: " + dto.getNumCnpjCpfPagador());
        linha.adicionarValor(dto.getNumCnpjCpfPagador());

        debug("Adicionando NomeRazaoSocialPagador: " + dto.getNomeRazaoSocialPagador());
        linha.adicionarValor(dto.getNomeRazaoSocialPagador());

        debug("Adicionando NomeFantasiaPagador: " + dto.getNomeFantasiaPagador());
        linha.adicionarValor(dto.getNomeFantasiaPagador());

        debug("Adicionando NumCodigoBarras: " + dto.getNumCodigoBarras());
        linha.adicionarValor(dto.getNumCodigoBarras());

        debug("Adicionando NumLinhaDigitavel: " + dto.getNumLinhaDigitavel());
        linha.adicionarValor(dto.getNumLinhaDigitavel());

        debug("Adicionando DataVencimentoBoleto: " + obterValorDataFormatada(dto.getDataVencimentoBoleto()));
        linha.adicionarValor(obterValorDataFormatada(dto.getDataVencimentoBoleto()));

        debug("Adicionando ValorBoleto: " + obterValorBigDecimalArredondado(dto.getValorBoleto()));
        linha.adicionarValor(obterValorBigDecimalArredondado(dto.getValorBoleto()));

        debug("Adicionando QtdDiasProtesto: " + dto.getQtdDiasProtesto());
        linha.adicionarValor(dto.getQtdDiasProtesto());

        debug("Adicionando DataLimitePagamentoBoleto: " + obterValorDataFormatada(dto.getDataLimitePagamentoBoleto()));
        linha.adicionarValor(obterValorDataFormatada(dto.getDataLimitePagamentoBoleto()));

        debug("Adicionando PermiteAlterarValor: " + converterBooleanParaShort(dto.getPermiteAlterarValor()));
        linha.adicionarValor(converterBooleanParaShort(dto.getPermiteAlterarValor()));

        debug("Adicionando SituacaoBoletoPagamento: " + dto.getSituacaoBoletoPagamento());
        linha.adicionarValor(dto.getSituacaoBoletoPagamento());

        debug("Adicionando ValorAbatimentoDesconto: " + obterValorBigDecimalArredondado(dto.getValorAbatimentoDesconto()));
        linha.adicionarValor(obterValorBigDecimalArredondado(dto.getValorAbatimentoDesconto()));

        debug("Adicionando DescInstituicaoEmissora: " + dto.getDescInstituicaoEmissora());
        linha.adicionarValor(dto.getDescInstituicaoEmissora());

        debug("Adicionando InstrucaoDesconto1: " + dto.getInstrucaoDesconto1());
        linha.adicionarValor(dto.getInstrucaoDesconto1());

        debug("Adicionando InstrucaoDesconto2: " + dto.getInstrucaoDesconto2());
        linha.adicionarValor(dto.getInstrucaoDesconto2());

        debug("Adicionando InstrucaoDesconto3: " + dto.getInstrucaoDesconto3());
        linha.adicionarValor(dto.getInstrucaoDesconto3());

        debug("Adicionando InstrucaoValorMinMax: " + dto.getInstrucaoValorMinMax());
        linha.adicionarValor(dto.getInstrucaoValorMinMax());

        debug("Adicionando ValorMultaMora: " + obterValorBigDecimalArredondado(dto.getValorMultaMora()));
        linha.adicionarValor(obterValorBigDecimalArredondado(dto.getValorMultaMora()));

        debug("Adicionando ValorPagamento: " + obterValorBigDecimalArredondado(dto.getValorPagamento()));
        linha.adicionarValor(obterValorBigDecimalArredondado(dto.getValorPagamento()));

        debug("Adicionando BloquearPagamento: " + converterBooleanParaShort(dto.getBloquearPagamento()));
        linha.adicionarValor(converterBooleanParaShort(dto.getBloquearPagamento()));

        debug("Adicionando MensagemBloqueioPagamento: " + dto.getMensagemBloqueioPagamento());
        linha.adicionarValor(dto.getMensagemBloqueioPagamento());

        debug("Adicionando NumNossoNumero: " + dto.getNumNossoNumero());
        linha.adicionarValor(dto.getNumNossoNumero());

        debug("Adicionando NumDocumento: " + dto.getNumDocumento());
        linha.adicionarValor(dto.getNumDocumento());

        debug("Adicionando NumIdentificadorBoletoCIP: " + dto.getNumIdentificadorBoletoCIP());
        linha.adicionarValor(dto.getNumIdentificadorBoletoCIP());

        debug("Adicionando DataPagamento: " + obterValorDataFormatada(dto.getDataPagamento()));
        linha.adicionarValor(obterValorDataFormatada(dto.getDataPagamento()));
    }

}
