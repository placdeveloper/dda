/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao
 * Arquivo:         ErroProtocoloSSPBException.java
 * Data Criação:    Jan 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ErroProtocoloSSPBException
 * 
 * @author rafael.silva
 */
public class ErroProtocoloSSPBException extends ComumException {

    private static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public ErroProtocoloSSPBException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public ErroProtocoloSSPBException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param excecao
     */
    public ErroProtocoloSSPBException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public ErroProtocoloSSPBException(Throwable excecao) {
        super(excecao);
    }

    /**
     * @param string
     * @param parametros
     */
    public ErroProtocoloSSPBException(String string, Object... parametros) {
        super(string, parametros);
    }

}
