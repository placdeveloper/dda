/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         CacheNotificaServicoEJB.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.CacheNotificaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * CacheNotificaServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(CacheNotificaServicoLocal.class)
public class CacheNotificaServicoEJB extends IntegracaoTopicoServicoEJB implements CacheNotificaServicoLocal {

    @Resource(name = "WSMQDDAXMLJmsTopicXA")
    private TopicConnectionFactory connectionFactory;

    @Resource(name = "TP.CACHE.DDA.01")
    private Topic topico;

    private Connection conexao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.IntegracaoTopicoServicoEJB#getConnectionFactory()
     */
    @Override
    protected TopicConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#getConexao()
     */
    @Override
    protected Connection getConexao() {
        return conexao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#setConexao(javax.jms.Connection)
     */
    @Override
    protected void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#createSession()
     */
    @Override
    protected Session createSession() throws JMSException {
        return getConexao().createSession(Boolean.TRUE, Session.SESSION_TRANSACTED);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#getDeliveryMode()
     */
    @Override
    protected int getDeliveryMode() throws JMSException {
        return DeliveryMode.PERSISTENT;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheNotificaServico#notificarCache(br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache)
     */
    public void notificarCache(RequisicaoCache requisicao) throws ComumException {
        try {
            abrirRecursos();
            getLogger().debug("[DDA] - Notificando cache. Cache: " + requisicao.getDescCache());
            super.enviar(topico, requisicao);
        } finally {
            fecharRecursos();
        }

    }

}
