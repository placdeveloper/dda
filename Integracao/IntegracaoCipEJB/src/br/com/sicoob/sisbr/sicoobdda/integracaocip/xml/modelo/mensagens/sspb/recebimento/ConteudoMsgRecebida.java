/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento
 * Arquivo:         ConteudoMsgRecebida.java
 * Data Cria��o:    Sep 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento;

/**
 * @author rafael.silva
 */
public interface ConteudoMsgRecebida {

    /**
     * M�todo respons�vel por obrigar a todos filhos retornarem o IdMsgOrigem
     * 
     * @return Long
     * 
     */
    Long getIdMensagemOrigem();

    /**
     * M�todo respons�vel por obrigar a todos filhos retornarem o codMsg. Devido as classes de retorno de erros possu�rem conte�do do tipo TagErroComplexType �
     * necess�rio retornar um Object cabendo ao recebedor tratar para String ou TagErroComplexType
     * 
     * @return Object
     * 
     */
    String getCodMsg();

    /**
     * M�todo respons�vel por obrigar a todos filhos retornarem o Numero de Identificacao especifico para cada mensagem.
     * 
     * @return Long
     * 
     */
    Long getNumIdent();

    /**
     * M�todo respons�vel por obrigar a todos os filhos retornarem o N�mero de Protocolo da CIP.
     * 
     * @return String
     * 
     */
    String getNumCtrlDDA();

}
