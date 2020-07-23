package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Excecao padrao para o sistema IntegracaoInternaPrivada.
 * 
 * @author Samuell.Ramos
 */
@ApplicationException(rollback = true)
public class IntegracaoInternaException extends ComumException {

    private static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public IntegracaoInternaException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public IntegracaoInternaException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param excecao
     */
    public IntegracaoInternaException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public IntegracaoInternaException(Throwable excecao) {
        super(excecao);
    }

    /**
     * @param string
     * @param parametros
     */
    public IntegracaoInternaException(String string, Object... parametros) {
        super(string, parametros);
    }
}