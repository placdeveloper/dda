/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.excecao
 * Arquivo:         LeituraADDA101RR2756Exception.java
 * Data Cria��o:    Jul 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import org.xml.sax.SAXException;

/**
 * LeituraADDARR2756Exception � respons�vel por
 * 
 * @author adriano.pinheiro
 */
public class LeituraADDARR2756Exception extends SAXException {

    /**
     * 
     */
    private static final long serialVersionUID = -1359140327240535586L;

    /**
     * @param tratamentoArq
     */
    public LeituraADDARR2756Exception(String chave) {
        super(chave);
    }

}
