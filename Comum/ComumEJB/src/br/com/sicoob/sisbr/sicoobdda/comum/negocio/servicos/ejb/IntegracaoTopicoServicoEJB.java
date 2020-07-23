/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         IntegracaoTopicoServicoEJB.java
 * Data Criação:    15 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IntegracaoTopicoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
public abstract class IntegracaoTopicoServicoEJB extends JMSComumServicoEJB {

    /**
     * 
     */
    public IntegracaoTopicoServicoEJB() {
        super();
    }

    /**
     * @return the connectionFactory
     */
    protected abstract TopicConnectionFactory getConnectionFactory();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.JMSComumServicoEJB#enviar(javax.jms.Destination, java.io.Serializable)
     */
    @Override
    public String enviar(Destination destino, Serializable obj) throws ComumException {
        Session session = null;
        MessageProducer messageProducer = null;
        Message msgObject = null;
        String idMq = null;

        if (!(destino instanceof Topic)) {
            throw new ComumException("erro.tipo.destino.nao.topico");
        }

        try {
            Topic fila = (Topic) destino;
            getLogger().debug("###### Enviando " + obj + " para o topico " + fila.getTopicName());

            long ms = System.currentTimeMillis();
            session = createSession();
            messageProducer = session.createProducer(fila);
            msgObject = setMessage(obj, session);

            messageProducer.setDeliveryMode(getDeliveryMode());

            messageProducer.send(msgObject);

            getLogger().debug("###### Enviada em: " + (System.currentTimeMillis() - ms));

            idMq = msgObject.getJMSMessageID();

        } catch (JMSException ex) {
            getLogger().erro(ex, ex.getMessage());
            throw new ComumException(ex);
        } finally {
            // Por ser obtido pela Factory manipulada pelo JCA, a sessão deve ser fechada.
            fecharMessageProducer(messageProducer);
            fecharSessao(session);
        }
        getLogger().debug("###### Mensagem postada na fila com sucesso.");

        return idMq;
    }

}
