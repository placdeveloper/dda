package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IntegracaoFilaServicoEJB
 * 
 * @author rafael.silva
 */
public abstract class IntegracaoFilaServicoEJB extends JMSComumServicoEJB {

    private static final long MULTIPLICADOR = 1000L;
    private static final String FILTRO_REGISTRO_NUMSEQ = "numseq";

    /**
     * 
     */
    public IntegracaoFilaServicoEJB() {
        super();
    }

    /**
     * @return the connectionFactory
     */
    protected abstract QueueConnectionFactory getConnectionFactory();

    /**
     * Método responsável por enviar a mensagem para a fila especifica
     * 
     * @param aFilaDestino
     * @param aObj
     * @param nonPersistence
     * @return
     * @throws ComumException String
     * 
     */
    @Override
    protected String enviar(Destination destino, Serializable obj) throws ComumException {
        Session session = null;
        MessageProducer queueProducer = null;
        Message msgObject = null;
        String idMq = null;

        if (!(destino instanceof Queue)) {
            throw new ComumException("erro.tipo.destino.nao.fila");
        }

        try {
            Queue fila = (Queue) destino;
            getLogger().debug("###### Enviando " + obj + " para fila " + fila.getQueueName());

            long ms = System.currentTimeMillis();

            session = createSession();
            queueProducer = session.createProducer(fila);
            msgObject = setMessage(obj, session);

            queueProducer.setDeliveryMode(getDeliveryMode());

            queueProducer.send(msgObject);

            getLogger().debug("###### Enviada em: " + (System.currentTimeMillis() - ms));

            idMq = msgObject.getJMSMessageID();

        } catch (JMSException ex) {
            getLogger().erro(ex, ex.getMessage());
            throw new ComumException(ex);
        } finally {
            // Por ser obtido pela Factory manipulada pelo JCA, a sessão deve ser fechada.
            fecharMessageProducer(queueProducer);
            fecharSessao(session);
        }
        getLogger().debug("###### Mensagem postada na fila com sucesso.");

        return idMq;
    }

    /**
     * Método responsável por
     * 
     * @param fila
     * @param numSeq
     * @param timeOut
     * @return
     * @throws ComumException String
     * 
     */
    protected String consumirMensagem(Queue fila, Long numSeq, int timeOut) throws ComumException {
        Session session = null;
        MessageConsumer messageConsumer = null;
        try {
            session = createSession();
            debug("###### Sessão criada!");

            messageConsumer = session.createConsumer(fila, FILTRO_REGISTRO_NUMSEQ + " = '" + numSeq + "'");
            debug("###### Aguardando resposta da mensagem" + FILTRO_REGISTRO_NUMSEQ + " = '" + numSeq + "'");

            getConexao().start();
            return getConteudo(messageConsumer.receive(timeOut * MULTIPLICADOR));
        } catch (JMSException e) {
            throw new ComumException(e);
        } finally {
            fecharSessao(session);
            fecharMessageConsumer(messageConsumer);
        }
    }

}