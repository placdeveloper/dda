/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao
 * Arquivo:         IntegracaoCipException.java
 * Data Criação:    Abr 18, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Excecao padrao para o sistema IntegracaoCip.
 * 
 * @author Sicoob
 */
public class IntegracaoCipException extends ComumException {

    private static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public IntegracaoCipException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public IntegracaoCipException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param excecao
     */
    public IntegracaoCipException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public IntegracaoCipException(Throwable excecao) {
        super(excecao);
    }

    /**
     * @param string
     * @param parametros
     */
    public IntegracaoCipException(String string, Object... parametros) {
        super(string, parametros);
    }
}