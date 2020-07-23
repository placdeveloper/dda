package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * Business delegate padrao para operacoes de CRUD do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipMensagemDelegate<T extends IntegracaoCipServico> extends ComumDelegate<T> {

    /**
     * 
     */
    public IntegracaoCipMensagemDelegate() {
    }

    /**
     * Método responsável por gerar o xml de envio da mensagem para CIP.
     * 
     * @param idMensagem
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    public abstract String processarMensagem(Long idMensagem) throws ComumException;

    /**
     * Método responsável por processar o retorno da mensagem enviada efetivando a operação na base do DDA.
     * 
     * @param conteudoMsg
     * @throws ComumException void
     * 
     */
    public abstract void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException;
}