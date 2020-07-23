/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         JMSComumServicoEJB.java
 * Data Criação:    15 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * JMSComumServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
public abstract class JMSComumServicoEJB extends ComumServicoEJB {

    /**
     * 
     */
    public JMSComumServicoEJB() {
        super();
    }

    /**
     * @return the conexao
     */
    protected abstract Connection getConexao();

    /**
     * @param Conexao the Conexao to set
     */
    protected abstract void setConexao(Connection conexao);

    /**
     * @return the connectionFactory
     */
    protected abstract ConnectionFactory getConnectionFactory();

    /**
     * @return
     * @throws JMSException Session
     * 
     */
    protected abstract Session createSession() throws JMSException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws JMSException int
     * 
     */
    protected abstract int getDeliveryMode() throws JMSException;

    /**
     * Método responsável por
     * 
     * @param destino
     * @param obj
     * @return
     * @throws ComumException String
     * 
     */
    protected abstract String enviar(Destination destino, Serializable obj) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param obj
     * @param session
     * @return
     * @throws JMSException Message
     * 
     */
    protected Message setMessage(Serializable obj, Session session) throws JMSException {
        Message msgObject;
        if (obj instanceof String) {
            msgObject = session.createTextMessage((String) obj);
        } else if (obj instanceof byte[]) {
            msgObject = session.createBytesMessage();
            ((BytesMessage) msgObject).writeBytes((byte[]) obj);
        } else {
            msgObject = session.createObjectMessage(obj);
        }
        return msgObject;
    }

    /**
     * 
     */
    protected void abrirRecursos() throws ComumException {
        try {
            setConexao(getConnectionFactory().createConnection());
        } catch (JMSException ex) {
            getLogger().erro(ex, "Erro ao criar conexão JMS");
            throw new ComumException("Erro ao criar conexão JMS", ex);
        }
    }

    /**
     * Método responsável por fechar a a conexão void
     */
    protected void fecharRecursos() {
        if (!ObjectUtil.isNull(getConexao())) {
            try {
                getConexao().close();
            } catch (JMSException ex) {
                getLogger().alerta(ex);
            }
        }
    }

    /**
     * Método responsável por fechar a sessão
     * 
     * @param session void
     * 
     */
    protected void fecharSessao(Session session) {
        if (!ObjectUtil.isNull(session)) {
            try {
                session.close();
            } catch (JMSException ex) {
                getLogger().alerta(ex);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param messageProducer void
     * 
     */
    protected void fecharMessageConsumer(MessageConsumer messageConsumer) {
        if (!ObjectUtil.isNull(messageConsumer)) {
            try {
                messageConsumer.close();
            } catch (JMSException ex) {
                getLogger().alerta(ex);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param messageProducer void
     * 
     */
    protected void fecharMessageProducer(MessageProducer messageProducer) {
        if (!ObjectUtil.isNull(messageProducer)) {
            try {
                messageProducer.close();
            } catch (JMSException ex) {
                getLogger().alerta(ex);
            }
        }
    }

    /**
     * @param message
     * @param prop
     * @throws JMSException
     */
    protected void setOpcoes(Message message, Map<String, Object> prop) throws JMSException {
        if (!ObjectUtil.isNull(prop)) {
            for (Entry<String, Object> itKeyProp : prop.entrySet()) {
                message.setObjectProperty(itKeyProp.getKey(), prop.get(itKeyProp.getKey()));
            }
        }
    }

    /**
     * @param message
     * @return
     * @throws ComumException String
     * 
     */
    protected String getConteudo(Message message) throws ComumException {
        if (ObjectUtil.isNull(message)) {
            return null;
        }

        StringBuilder bufferMsg = new StringBuilder();
        try {
            if (message instanceof BytesMessage) {
                BytesMessage retornoDDA = (BytesMessage) message;
                for (int i = 0; i < (int) retornoDDA.getBodyLength(); i++) {
                    bufferMsg.append((char) retornoDDA.readByte());
                }
            } else if (message instanceof TextMessage) {
                TextMessage retornoDDA = (TextMessage) message;
                String[] valor = retornoDDA.getText().split("\\r?\\n");
                for (int i = 0; i < valor.length; i++) {
                    bufferMsg.append(valor[i]);
                }
            }
        } catch (JMSException e) {
            throw new ComumException(e);
        }
        return bufferMsg.toString();
    }
}
