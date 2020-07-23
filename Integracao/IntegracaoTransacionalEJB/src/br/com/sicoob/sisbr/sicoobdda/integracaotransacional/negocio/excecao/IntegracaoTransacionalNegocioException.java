package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * Excecao padrao para o sistema Integracao Transacional.
 * 
 * @author george.santos
 */
@ApplicationException(rollback = false)
public class IntegracaoTransacionalNegocioException extends ComumNegocioException {

    private static final long serialVersionUID = -1305045613348336414L;

    /**
     * @param chave
     */
    public IntegracaoTransacionalNegocioException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public IntegracaoTransacionalNegocioException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public IntegracaoTransacionalNegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public IntegracaoTransacionalNegocioException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public IntegracaoTransacionalNegocioException(Throwable excecao) {
        super(excecao);
    }

}
