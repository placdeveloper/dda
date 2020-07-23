/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ConsultaBoletoPagamentoCIPServico.java
 * Data Criação:    Nov 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;

/**
 * ConsultaBoletoPagamentoCIPServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ConsultaBoletoPagamentoCIPServico extends IntegracaoCipServico {

    /**
     * Método responsável por
     * 
     * @param msgDDA
     * @throws ComumException void
     * 
     */
    void enviarMensagemCip(MensagemDDA msgDDA) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param msg
     * @param timeOut
     * @return Xml da resposta
     * @throws ComumException String
     * 
     */
    String aguardarRepostaCIP(MensagemDDA msg, int timeOut) throws ComumException;

}
