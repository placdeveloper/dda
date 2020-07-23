/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         IntegracaoFilaSSPBEnvioDDAServico.java
 * Data Criação:    Jul 22, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.io.Serializable;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IntegracaoFilaSSPBEnvioDDAServico
 * 
 * @author rafael.silva
 */
public interface IntegracaoFilaSSPBEnvioDDAServico extends IntegracaoCipServico {

    /**
     * Método responsável por criar a conexão com a fila de envio de mensagens SSPB-DDA
     * 
     * @param aObj
     * @return
     * @throws Exception String
     * 
     */
    String enviar(Serializable aObj) throws ComumException;

    /**
     * Método responsável por criar a conexão com a fila de recebimento de mensagens SSPB-DDA
     * 
     * @param aObj
     * @return
     * @throws ComumException String
     * 
     */
    String enviarFilaRecebimento(Serializable aObj) throws ComumException;

}
