/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.IntegracaoFilaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.IntegracaoFilaSSPBEnvioDDAServicoLocal;

/**
 * IntegracaoFilaSSPBEnvioDDAServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(IntegracaoFilaSSPBEnvioDDAServicoLocal.class)
public class IntegracaoFilaSSPBEnvioDDAServicoEJB extends IntegracaoFilaServicoEJB implements IntegracaoFilaSSPBEnvioDDAServicoLocal {

    private Connection filaConexao;

    @Resource(name = "WSMQDDAXMLJmsXA")
    private QueueConnectionFactory connectionFactory;

    @Resource(name = "QL.REQ.DDA.BANCOOB.01")
    private Queue filaEnvio;

    @Resource(name = "QL.RSP.BANCOOB.DDA.01")
    private Queue filaRecebimento;

    /**
     * @return Queue
     * 
     */
    protected Queue getFilaConcorrencia() {
        return filaEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb.IntegracaoFilaServicoEJB#getConnectionFactory()
     */
    @Override
    protected QueueConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb.IntegracaoFilaServicoEJB#getFilaConexao()
     */
    @Override
    protected Connection getConexao() {
        return filaConexao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb.IntegracaoFilaServicoEJB#setFilaConexao(javax.jms.QueueConnection)
     */
    @Override
    protected void setConexao(Connection filaConexao) {
        this.filaConexao = filaConexao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#createSession()
     */
    @Override
    protected Session createSession() throws JMSException {
        getLogger().debug("###### Session.SESSION_TRANSACTED");
        return getConexao().createSession(Boolean.TRUE, Session.SESSION_TRANSACTED);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#getDeliveryMode()
     */
    @Override
    protected int getDeliveryMode() throws JMSException {
        getLogger().debug("###### Mensagem enviada com Persistencia");
        return DeliveryMode.PERSISTENT;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb.IntegracaoFilaServicoEJB#enviar(javax.jms.Queue, java.io.Serializable)
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String enviar(Queue aFilaDestino, Serializable aObj) throws ComumException {
        try {
            abrirRecursos();
            return super.enviar(aFilaDestino, aObj);
        } finally {
            if (!(ObjectUtil.isNull(this.filaConexao))) {
                fecharRecursos();
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoFilaSSPBEnvioDDAServico#enviar(java.io.Serializable)
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String enviar(Serializable aObj) throws ComumException {
        return enviar(filaEnvio, aObj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoFilaSSPBEnvioDDAServico#enviar(java.io.Serializable)
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String enviarFilaRecebimento(Serializable aObj) throws ComumException {
        return enviar(filaRecebimento, aObj);
    }

}