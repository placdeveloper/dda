/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ConsultaBoletoPagamentoCIPDelegate.java
 * Data Criação:    Nov 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ConsultaBoletoPagamentoCIPDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ConsultaBoletoPagamentoCIPDelegate extends IntegracaoCipDelegate<ConsultaBoletoPagamentoCIPServico> implements ConsultaBoletoPagamentoCIPServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ConsultaBoletoPagamentoCIPServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarConsultaBoletoPagamentoCIPServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#enviarMensagemCip(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA)
     */
    @Override
    public void enviarMensagemCip(MensagemDDA msgDDA) throws ComumException {
        localizarServico().enviarMensagemCip(msgDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#aguardarRepostaCIP(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA,
     *      int)
     */
    public String aguardarRepostaCIP(MensagemDDA msg, int timeOut) throws ComumException {
        return localizarServico().aguardarRepostaCIP(msg, timeOut);
    }

}
