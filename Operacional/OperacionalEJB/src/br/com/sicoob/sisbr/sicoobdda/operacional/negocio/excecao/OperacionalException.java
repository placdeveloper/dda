package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Excecao padrao para o sistema Operacional.
 * 
 * @author rafael.silva
 */
public class OperacionalException extends ComumException {

    private static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public OperacionalException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public OperacionalException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param excecao
     */
    public OperacionalException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public OperacionalException(Throwable excecao) {
        super(excecao);
    }

    /**
     * @param string
     * @param parametros
     */
    public OperacionalException(String string, Object... parametros) {
        super(string, parametros);
    }
}