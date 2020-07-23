/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ConsultaBoletoPagamentoCIPServicoEJB.java
 * Data Criação:    Nov 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.IntegracaoFilaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ConsultaBoletoPagamentoCIPServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ConsultaBoletoPagamentoCIPServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ConsultaBoletoPagamentoCIPServicoLocal.class })
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConsultaBoletoPagamentoCIPServicoEJB extends IntegracaoFilaServicoEJB implements ConsultaBoletoPagamentoCIPServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ComumDao comumDAO;

    @Resource(name = "WSMQDDAXMLJmsXA")
    private QueueConnectionFactory connectionFactory;

    @Resource(name = "QL.REQ.NPC.BANCOOB.01")
    private Queue filaEnvio;

    @Resource(name = "QL.RSP.BANCOOB.NPC.01")
    private Queue filaRecebimento;

    private Connection filaConexao;

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
        getLogger().debug("###### Session.AUTO_ACKNOWLEDGE");
        return getConexao().createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#getDeliveryMode()
     */
    @Override
    protected int getDeliveryMode() throws JMSException {
        getLogger().debug("###### Mensagem enviada sem Persistencia");
        return DeliveryMode.NON_PERSISTENT;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#enviarMensagemCip(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void enviarMensagemCip(MensagemDDA msgDDA) throws ComumException {
        try {
            abrirRecursos();
            super.enviar(filaEnvio, msgDDA.getXmlMensagem());
        } catch (ComumException e) {
            throw new ComumException("integracaocip.erro.enviar.mensagem.DDA0110.sspb", e);
        } finally {
            fecharRecursos();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#aguardarRepostaCIP(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA,
     *      int)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String aguardarRepostaCIP(MensagemDDA msg, int timeOut) throws ComumException {
        try {
            abrirRecursos();
            return consumirMensagem(filaRecebimento, msg.getId(), timeOut);
        } catch (ComumException e) {
            throw new ComumException("integracaocip.erro.consumir.retorno.mensagem.DDA0110.sspb", e);
        } finally {
            fecharRecursos();
        }
    }

}
