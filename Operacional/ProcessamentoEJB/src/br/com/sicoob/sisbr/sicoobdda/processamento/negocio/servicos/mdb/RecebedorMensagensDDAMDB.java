/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.mdb
 * Arquivo:         SicoobDDAMDB.java
 * Data Criação:    24 de jan de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.mdb;

import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.bancoob.negocio.mdb.BancoobMdb;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarRetornoDDADelegate;

/**
 * SicoobDDAMDB é responsável por 
 * 
 * @author Felipe.Rosa
 */
@MessageDriven(messageListenerInterface = MessageListener.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class RecebedorMensagensDDAMDB extends BancoobMdb {

    public static final String OBTENDO_XML_FILA = "###### Obtendo xml fila...";
    public static final String MENSAGEM_RECEBIDA_TRATANDO = "###### Mensagem recebida tratando";
    public static final String MENSAGEM_RECEBIDA_ENVIANDO_PARA_PROCESSAMENTO = "###### Enviando para processamento";
    public static final String MENSAGEM_ENVIADA_PROCESSANDO = "###### Mensagem enviada, processando";
    public static final String MENSAGEM_PROCESSADA_BASE_DE_DADOS_ATUALIZADA = "###### Mensagem processada com sucesso.";
    public static final String ERRO_AO_RECUPERAR_MENSAGEM = "###### Erro ao recuperar mensagem na fila DDA.";

    /**
     * {@inheritDoc}
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void onMessage(Message message) {
        // Trata recebimento e envia para processador de mensagens recebidas.
        try {
            getLogger().debug(OBTENDO_XML_FILA);
            String xml = getConteudo(message);

            getLogger().info(MENSAGEM_RECEBIDA_ENVIANDO_PARA_PROCESSAMENTO);
            ProcessarRetornoDDADelegate delegate = IntegracaoCipFabricaDelegates.getInstance().getProcessarRetornoDDADelegate();
            delegate.processarMensagemRecebida(xml);
            getLogger().info(MENSAGEM_PROCESSADA_BASE_DE_DADOS_ATUALIZADA);

        } catch (Exception e) { // NOSONAR
            getLogger().erro(e, ERRO_AO_RECUPERAR_MENSAGEM + e.getMessage());
            getMessageDrivenContext().setRollbackOnly();
        }
    }

    /**
     * Gets the conteudo.
     *
     * @param message the message
     * @return the conteudo
     * @throws JMSException the JMS exception
     */
    private String getConteudo(Message message) throws JMSException {
        StringBuffer bufferMsg = new StringBuffer();
        if (message instanceof BytesMessage) {
            BytesMessage retornoDDA = (BytesMessage) message;
            getLogger().info(MENSAGEM_RECEBIDA_TRATANDO);
            for (int i = 0; i < (int) retornoDDA.getBodyLength(); i++) {
                bufferMsg.append((char) retornoDDA.readByte());
            }
        } else if (message instanceof TextMessage) {
            TextMessage retornoDDA = (TextMessage) message;
            getLogger().info(MENSAGEM_RECEBIDA_TRATANDO);
            String valor[] = retornoDDA.getText().split("\\r?\\n");
            for (int i = 0; i < valor.length; i++) {
                bufferMsg.append(valor[i]);
            }
        }
        return bufferMsg.toString();
    }

}
