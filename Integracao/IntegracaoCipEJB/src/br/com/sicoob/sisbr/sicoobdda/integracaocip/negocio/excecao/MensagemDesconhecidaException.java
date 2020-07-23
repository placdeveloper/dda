/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao
 * Arquivo:         MensagemDesconhecidaException.java
 * Data Criação:    Oct 10, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * MensagemDesconhecidaException
 * 
 * @author Rafael.Silva
 */
public class MensagemDesconhecidaException extends ComumException {

    private static final long serialVersionUID = 3195702332059347080L;

    /**
     * @param chave
     */
    public MensagemDesconhecidaException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros void
     * 
     */
    public MensagemDesconhecidaException(String chave, Object... parametros) {
        super(chave, parametros);
    }

}
