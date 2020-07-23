/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ConsultaBoletoPagamentoCIPServico.java
 * Data Cria��o:    Nov 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;

/**
 * ConsultaBoletoPagamentoCIPServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ConsultaBoletoPagamentoCIPServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por
     * 
     * @param msgDDA
     * @throws ComumException void
     * 
     */
    void enviarMensagemCip(MensagemDDA msgDDA) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param msg
     * @param timeOut
     * @return Xml da resposta
     * @throws ComumException String
     * 
     */
    String aguardarRepostaCIP(MensagemDDA msg, int timeOut) throws ComumException;

}
