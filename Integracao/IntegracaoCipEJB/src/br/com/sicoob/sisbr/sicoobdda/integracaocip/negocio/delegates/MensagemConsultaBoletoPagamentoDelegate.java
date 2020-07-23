package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemConsultarBoletoDelegate
 * 
 * @author George.Santos
 */
public class MensagemConsultaBoletoPagamentoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemConsultaBoletoPagamentoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemConsultaBoletoPagamentoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemConsultaBoletoPagamentoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico#obterBoletoDDA(java.lang.String)
     */
    public BoletoDDA obterBoletoDDA(String codigoBarras, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ComumNegocioException, ErroCIPNegocioException {
        return localizarServico().obterBoletoDDA(codigoBarras, idInstituicao, idUsuario, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico#incluirMensagemDDAConsultaBoleto(java.util.List)
     */
    public void incluirMensagemDDAConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws ComumException {
        localizarServico().incluirMensagemDDAConsultaBoleto(listaNumCodBarras, numCooperativa);
    }

}
