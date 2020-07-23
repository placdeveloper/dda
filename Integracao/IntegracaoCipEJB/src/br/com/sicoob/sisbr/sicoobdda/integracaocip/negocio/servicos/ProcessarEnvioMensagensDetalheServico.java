/**
7 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarEnvioMensagensServico.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ProcessarEnvioMensagensServico é responsável por centralizar o envio de mensagens para CIP
 * 
 * @author Rafael.Silva
 */
public interface ProcessarEnvioMensagensDetalheServico extends IntegracaoCipServico {

    /**
     * Método responsável por processar o envio da mensagem abrindo uma nova transação.
     * 
     * @param msg
     * @throws ComumException void
     * 
     */
    public void processarEnvioMensagens(String codTipoMensagemDDA, Long idMensagemDDA) throws ComumException;
}
