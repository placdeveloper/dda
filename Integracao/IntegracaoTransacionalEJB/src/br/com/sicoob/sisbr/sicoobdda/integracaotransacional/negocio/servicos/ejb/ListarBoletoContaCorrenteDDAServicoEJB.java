package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto;
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
 * ListarBoletoContaCorrenteDDAServicoEJB é responsável por
 * 
 * @author George.santos
 */
@Stateless
@Remote(Transacao.class)
public class ListarBoletoContaCorrenteDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        debug("### Executando a consulta do boleto DDA - Transacional");

        if (ObjectUtil.isNull(mensagem)) {
            throw new IntegracaoTransacionalNegocioException("integracao.transacional.mensagem.nao.informada");
        }

        ConsultaBoletoDDAContaCorrenteDto dto = new ConsultaBoletoDDAContaCorrenteDto();
        Integer idCanal = obterValorInteger(mensagem.recuperarParametro(Rotulos.CODIGO_CANAL));
        dto.setNumeroCooperativa(obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA)));
        dto.setContaCorrente(obterValorBigDecimal(obterParametro(mensagem, Rotulos.NUMERO_CONTA_CORRENTE)));
        dto.setCodSituacao(obterValorInteger(obterParametro(mensagem, RotulosSicoobDDA.COD_SITUACAO)));
        dto.setDataInicial(obterValorDateTimeDB(obterParametro(mensagem, Rotulos.DATA_INICIO)));
        dto.setDataFinal(obterValorDateTimeDB(obterParametro(mensagem, Rotulos.DATA_FIM)));
        dto.setNumeroCooperativaCartao(obterValorInteger(obterParametro(mensagem, Rotulos.COOPERATIVA_CARTAO)));

        // Substitui o número da cooperativa pela cooperativa do cartão se tiver idCanal e for ATM e possuir número de cooperativa do cartão
        if (!ObjectUtil.isEmpty(idCanal) && idCanal.equals(CanalEnum.ATM.getId()) && !ObjectUtil.isEmpty(dto.getNumeroCooperativaCartao())) {
            debug("Número da cooperativa: " + dto.getNumeroCooperativa());
            debug("Definindo número da cooperativa do cartão: " + dto.getNumeroCooperativaCartao());
            dto.setNumeroCooperativa(dto.getNumeroCooperativaCartao());
        }

        List<BoletoDDADto> listaBoletoDDADto = OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate().listarBoletoDDAPorContaCorrente(dto);

        return popularGrupo(listaBoletoDDADto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDADto
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularGrupo(List<BoletoDDADto> listaBoletoDDADto) throws IntegracaoTransacionalException {
        debug("### Populando grupo do retorno da consulta de boleto...");

        if (ObjectUtil.isEmpty(listaBoletoDDADto)) {
            return new Grupos();
        }
        Grupo grupo = new Grupo();

        for (BoletoDDADto boletoDDADto : listaBoletoDDADto) {
            // TODO rodrigo.neri: comentando debug
            // debug("Preenchendo linha...");

            Linha linha = new Linha();
            grupo.adicionarLinha(linha);

            // debug("Adicionando descTipoPagador: " + boletoDDADto.getDescTipoPagador());
            linha.adicionarValor(boletoDDADto.getDescTipoPagador());

            // debug("Adicionando codTipoPessoaBeneficiario: " + boletoDDADto.getTipoPessoaBeneficiario());
            linha.adicionarValor(boletoDDADto.getTipoPessoaBeneficiario());

            // debug("Adicionando numCpfCnpjBeneficiario: " + boletoDDADto.getNumCpfCnpjBeneficiario());
            linha.adicionarValor(boletoDDADto.getNumCpfCnpjBeneficiario());

            // debug("Adicionando nomeBeneficiario: " + boletoDDADto.getNomeRazaoSocialBeneficiario());
            linha.adicionarValor(boletoDDADto.getNomeRazaoSocialBeneficiario());

            // debug("Adicionando tipoPessoaPagador: " + boletoDDADto.getTipoPessoaPagador());
            linha.adicionarValor(boletoDDADto.getTipoPessoaPagador());

            // debug("Adicionando numCpfCnpjPagador: " + boletoDDADto.getNumCpfCnpjPagador());
            linha.adicionarValor(boletoDDADto.getNumCpfCnpjPagador());

            // debug("Adicionando NomeRazaoSocialPagador: " + boletoDDADto.getNomeRazaoSocialPagador());
            linha.adicionarValor(boletoDDADto.getNomeRazaoSocialPagador());

            // debug("Adicionando valorBoleto: " + boletoDDADto.getValorBoleto());
            linha.adicionarValor(boletoDDADto.getValorBoleto());

            // debug("Adicionando dataVencimentoBoleto: " + boletoDDADto.getDataVencimentoBoleto());
            linha.adicionarValor(obterValorDataFormatada(boletoDDADto.getDataVencimentoBoleto()));

            // debug("Adicionando CodTipoSituacaoBoleto: " + boletoDDADto.getCodTipoSituacaoBoleto());
            linha.adicionarValor(boletoDDADto.getCodTipoSituacaoBoleto());

            // debug("Adicionando descTipoSituacaoBoleto: " + boletoDDADto.getDescSituacaoBoleto());
            linha.adicionarValor(boletoDDADto.getDescSituacaoBoleto());

            // debug("Adicionando numIdentificadorBoletoCip: " + boletoDDADto.getNumIdentificadorBoletoCip());
            linha.adicionarValor(boletoDDADto.getNumIdentificadorBoletoCip());

            // debug("Adicionando NumCodigoBarras: " + boletoDDADto.getNumCodigoBarras());
            linha.adicionarValor(boletoDDADto.getNumCodigoBarras());

            // debug("Adicionando bolAceite: " + boletoDDADto.getBolAceite());
            linha.adicionarValor(converterBooleanParaShort(boletoDDADto.getBolAceite()));

            // debug("Adicionando NumNossoNumero: " + boletoDDADto.getNumNossoNumero());
            linha.adicionarValor(boletoDDADto.getNumNossoNumero());

            // debug("Adicionando NumDocumento: " + boletoDDADto.getNumDocumento());
            linha.adicionarValor(boletoDDADto.getNumDocumento());

            // debug("Adicionando descSituacaoAgendamento: " + boletoDDADto.getDescSituacaoAgendamento());
            linha.adicionarValor(boletoDDADto.getDescSituacaoAgendamento());

            // debug("Adicionando dataPagamento: " + boletoDDADto.getDataPagamento());
            linha.adicionarValor(obterValorDataFormatada(boletoDDADto.getDataPagamento()));

            // debug("Adicionando valorPagamento: " + boletoDDADto.getValorPagamento());
            linha.adicionarValor(boletoDDADto.getValorPagamento());

            // debug("Adicionando bolMostrarDataValorPagamento: " + boletoDDADto.getBolMostrarDataValorPagamento());
            linha.adicionarValor(converterBooleanParaShort(boletoDDADto.getBolMostrarDataValorPagamento()));

            // debug("------------------------------------------------------");
        }

        return new Grupos(grupo);
    }
}
