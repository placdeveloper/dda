package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import java.math.BigDecimal;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.ValidacaoException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
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
 * IndicadorAceiteDDAServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(Transacao.class)
public class IndicadorAceiteDDAServicoEJB extends IntegracaoTransacionalSicoobDDAEJB implements Transacao {

    PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate = OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalSicoobDDAEJB#executar(br.com.bancoob.srtb.dto.Mensagem)
     */
    @Override
    protected Grupos executar(Mensagem mensagem) throws BancoobException {
        validacaoIndicadorAceite(mensagem);

        PagadorEletronicoDDADto indicadorAceite = setPagadorEletronicoDDADto(mensagem);

        Boolean bolIndicadorAceite = pagadorEletronicoDDADelegate.indicadorAceiteDDA(indicadorAceite);

        return populaIndicadorAceite(bolIndicadorAceite);
    }

    /**
     * Método responsável por
     * 
     * @param bolIndicadorAceite
     * @return
     * @throws IntegracaoTransacionalException
     */
    private Grupos populaIndicadorAceite(Boolean bolIndicadorAceite) throws IntegracaoTransacionalException {
        if (ObjectUtil.isNull(bolIndicadorAceite)) {
            return new Grupos();
        }

        Grupos grupos = new Grupos();

        Grupo grupo = new Grupo();
        grupos.add(grupo);

        Linha linha = new Linha();
        grupo.adicionarLinha(linha);
        linha.adicionarValor(bolIndicadorAceite);

        return grupos;
    }

    /**
     * Método responsável por popular o indicadro de Aceite
     * 
     * @param mensagem
     * @return IndicadorAceiteDDADto
     * 
     */
    private PagadorEletronicoDDADto setPagadorEletronicoDDADto(Mensagem mensagem) throws IntegracaoTransacionalNegocioException {
        PagadorEletronicoDDADto indicadorAceite = new PagadorEletronicoDDADto();

        indicadorAceite.setNumConta(BigDecimal.valueOf(obterValorLong(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_CONTA))));
        indicadorAceite.setCodCanal(obterValorInteger(mensagem.recuperarParametro(Rotulos.CODIGO_CANAL)));
        indicadorAceite.setDescTerminal(ObjectUtil.isNull(mensagem.recuperarParametro(RotulosSicoobDDA.DESCRICAO_TERMINAL)) ? null : (String) mensagem.recuperarParametro(
                RotulosSicoobDDA.DESCRICAO_TERMINAL).getValor());
        indicadorAceite.setNumCoopCartao(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA_CARTAO)));
        indicadorAceite.setNumCooperativa(obterValorInteger(mensagem.recuperarParametro(Rotulos.COOPERATIVA)));
        indicadorAceite.setNumPac(obterValorInteger(mensagem.recuperarParametro(Rotulos.NUMERO_PAC)));
        indicadorAceite.setNumIdentDDA(BigDecimal.valueOf(obterValorLong(mensagem.recuperarParametro(RotulosSicoobDDA.NUMERO_IDENTIFICACAO_DDA))));

        indicadorAceite.setIndAceite(obterValorBoolean(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_ACEITE)) ? "S" : "N");
        return indicadorAceite;
    }

    /**
     * Método responsável pela validacao dos parametros recebidos pelo CANAL
     * 
     * @param pagadorEletronicoDDADto
     * @throws IntegracaoTransacionalNegocioException void
     * 
     */
    private void validacaoIndicadorAceite(Mensagem m) throws ValidacaoException {
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
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.NUMERO_IDENTIFICACAO_DDA))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.NUMERO_IDENTIFICACAO_DDA);
        } else if (ObjectUtil.isNull(m.recuperarParametro(RotulosSicoobDDA.BOL_ACEITE))) {
            throw new ValidacaoException("integracao.transacional.parametro.nao.encontrado", RotulosSicoobDDA.BOL_ACEITE);
        }
    }
}
