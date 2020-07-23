package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.ValidacaoException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupo;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Grupos;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.Linha;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao.IntegracaoTransacionalNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;

/**
 * ComprovanteAdesaoSacadoDDAServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(Transacao.class)
public class ComprovanteAdesaoSacadoDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        validacaoAdesaoDDA(mensagem);

        PagadorEletronicoDDADto adesaoDDA = setPagadorEletronicoDDADto(mensagem);

        // Substitui o número da cooperativa pela cooperativa do cartão se tiver idCanal e for ATM e possuir número de cooperativa do cartão
        if (!ObjectUtil.isEmpty(adesaoDDA.getCodCanal()) && adesaoDDA.getCodCanal().equals(CanalEnum.ATM.getId().intValue()) && !ObjectUtil.isEmpty(adesaoDDA.getNumCoopCartao())) {
            debug("Número da cooperativa: " + adesaoDDA.getNumCooperativa());
            debug("Definindo número da cooperativa do cartão: " + adesaoDDA.getNumCoopCartao());
            adesaoDDA.setNumCooperativa(adesaoDDA.getNumCoopCartao());
        }

        List<PagadorEletronicoDDADto> listaPagadorEletronicoDDADto = pagadorEletronicoDDADelegate.listarComprovanteAdesaoDDA(adesaoDDA.getNumConta().longValue(),
                adesaoDDA.getNumCooperativa(), adesaoDDA.getIdRespTitular(), adesaoDDA.getNumCpfCnpj());

        return popularListaComprovantesDDA(listaPagadorEletronicoDDADto);
    }

    /**
     * Método responsável por
     * 
     * @param bolIndicadorAceite
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos popularListaComprovantesDDA(List<PagadorEletronicoDDADto> listaPagadorEletronicoDto) throws IntegracaoTransacionalException {
        if (ObjectUtil.isNull(listaPagadorEletronicoDto)) {
            return new Grupos();
        }

        Grupos grupos = new Grupos();

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        for (PagadorEletronicoDDADto pagadorEletronicoDto : listaPagadorEletronicoDto) {
            Linha linha = new Linha();
            grupo.adicionarLinha(linha);
            linha.adicionarValor(pagadorEletronicoDto.getIdEventoDDA());
            linha.adicionarValor(obterValorDataHoraFormatadaDb2(pagadorEletronicoDto.getDataEvento()));
            linha.adicionarValor(pagadorEletronicoDto.getDescOperacao());
        }

        return grupos;
    }

    /**
     * Método responsável por popular o a adesao ao sacado
     * 
     * @param mensagem
     * @return IndicadorAceiteDDADto
     * @throws IntegracaoTransacionalNegocioException
     * 
     */
    private PagadorEletronicoDDADto setPagadorEletronicoDDADto(Mensagem mensagem) throws IntegracaoTransacionalNegocioException {
        PagadorEletronicoDDADto indicadorAceite = new PagadorEletronicoDDADto();

        indicadorAceite.setNumCliente(obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CLIENTE)));
        indicadorAceite.setNumConta(BigDecimal.valueOf(obterValorLong(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))));
        indicadorAceite.setCodCanal(obterValorInteger(mensagem.recuperarParametro(Rotulos.CODIGO_CANAL)));
        indicadorAceite.setDescTerminal(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.DESCRICAO_TERMINAL)) ? null : (String) mensagem.recuperarParametro(
                RotulosSicoobDDA.DESCRICAO_TERMINAL).getValor());
        indicadorAceite.setNumCoopCartao(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA_CARTAO)));
        indicadorAceite.setNumCooperativa(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA)));
        indicadorAceite.setNumPac(obterValorInteger(mensagem.recuperarParametro(Rotulos.NUMERO_PAC)));
        indicadorAceite.setBolControlaTransacao(obterValorBoolean(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_CONTROLA_TRANS)));

        indicadorAceite.setIdRespTitular(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)) ? null
                : obterValorInteger(mensagem.recuperarParametro(RotulosSicoobDDA.ID_RESP_TITULAR)));
        indicadorAceite.setNumCpfCnpj(ObjectUtil.isNull(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)) ? null : obterValorString(mensagem.recuperarParametro(Rotulos.CPF_CNPJ)));
        return indicadorAceite;
    }

    /**
     * Método responsável pela validacao dos parametros recebidos pelo CANAL
     * 
     * @param pagadorEletronicoDDADto
     * @throws IntegracaoTransacionalNegocioException void
     * 
     */
    private void validacaoAdesaoDDA(Mensagem m) throws ValidacaoException {
        if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.NUMERO_CONTA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.CODIGO_CANAL))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.CODIGO_CANAL);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA_CARTAO))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA_CARTAO);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.COOPERATIVA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.COOPERATIVA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(Rotulos.NUMERO_PAC))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", Rotulos.NUMERO_PAC);
        }
    }
}
