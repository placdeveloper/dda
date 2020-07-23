/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.mdb
 * Arquivo:         CacheMDB.java
 * Data Cria��o:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.mdb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_CACHE_ASSUNTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_CACHE_HABILITADO;

import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.bancoob.negocio.mdb.BancoobMdb;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.AmbienteUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * CacheMDB � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@MessageDriven(messageListenerInterface = MessageListener.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CacheMDB extends BancoobMdb {

    /**
     * {@inheritDoc}
     * 
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message message) {
        RequisicaoCache requisicaoCache = null;
        try {
            getLogger().debug("[DDA] - CacheMDB");
            ObjectMessage objeto = (ObjectMessage) message;

            requisicaoCache = (RequisicaoCache) objeto.getObject();
            getLogger().debug("[DDA] - Recebendo requisi��o para atualiza��o de cache. Cache: " + requisicaoCache.getDescCache());

            ComumFabricaDelegates.getInstance().getAtualizacaoCacheDelegate().processarRequisicao(requisicaoCache);

        } catch (JMSException | ComumException | ComumNegocioException e) {
            getLogger().erro(e, "Erro ao sincronizar cache!");
            enviarEmail(requisicaoCache, e);
        }
    }

    /**
     * M�todo respons�vel por void
     * 
     */
    private void enviarEmail(RequisicaoCache requisicaoCache, Exception exception) {

        try {
            ComumFabricaDelegates.getInstance().getEmailDelegate().enviar(EMAIL_CACHE_HABILITADO, EMAIL_CACHE_ASSUNTO, montarMensagem(requisicaoCache, exception));
        } catch (ComumException e) {
            getLogger().erro(e, "Erro ao enviar email com erro no Cache!");
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param requisicaoCache
     * @return StringBuilder
     * 
     */
    private String montarMensagem(RequisicaoCache requisicaoCache, Exception e) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Erro ao atualizar cache!");
        mensagem.append("\nServidor: " + AmbienteUtil.getNomeServidor());
        if (!ObjectUtil.isNull(requisicaoCache)) {
            mensagem.append("\nRequisi��o: " + requisicaoCache.getIdRequisicaoCache());
            mensagem.append("\nCache: " + requisicaoCache.getDescCache());
        }

        mensagem.append("\n\nLog: " + e.getMessage());
        return mensagem.toString();
    }

}
