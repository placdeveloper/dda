/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         IntegracaoCipServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * Interface que define o servico padrao do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public interface IntegracaoCipServicoMensagem extends IntegracaoCipServico {

    /**
     * Método responsável por buscar a mensagem, validar os dados de envio, preparar o XML para envio setar na mensagem e retornar para o motor realizar a
     * postagem.
     * 
     * @param idMensagem
     * @return
     * @throws ComumException String
     * 
     */
    String processarMensagem(Long idMensagem) throws ComumException;

    /**
     * Método responsável por receber o retorno da mensagem DDA e processar de acordo com cada mensagem.
     * 
     * @param idMensagem
     * @param conteudoMsg
     * @throws ComumException void
     * 
     */
    void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException;

}