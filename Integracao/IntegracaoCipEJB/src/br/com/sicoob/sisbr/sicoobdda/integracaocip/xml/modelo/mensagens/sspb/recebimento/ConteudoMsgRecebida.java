/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento
 * Arquivo:         ConteudoMsgRecebida.java
 * Data Criação:    Sep 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento;

/**
 * @author rafael.silva
 */
public interface ConteudoMsgRecebida {

    /**
     * Método responsável por obrigar a todos filhos retornarem o IdMsgOrigem
     * 
     * @return Long
     * 
     */
    Long getIdMensagemOrigem();

    /**
     * Método responsável por obrigar a todos filhos retornarem o codMsg. Devido as classes de retorno de erros possuírem conteúdo do tipo TagErroComplexType é
     * necessário retornar um Object cabendo ao recebedor tratar para String ou TagErroComplexType
     * 
     * @return Object
     * 
     */
    String getCodMsg();

    /**
     * Método responsável por obrigar a todos filhos retornarem o Numero de Identificacao especifico para cada mensagem.
     * 
     * @return Long
     * 
     */
    Long getNumIdent();

    /**
     * Método responsável por obrigar a todos os filhos retornarem o Número de Protocolo da CIP.
     * 
     * @return String
     * 
     */
    String getNumCtrlDDA();

}
