package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Excecao padrao para o sistema Integracao Transacional.
 * 
 * @author george.santos
 */
@ApplicationException(rollback = true)
public class IntegracaoTransacionalException extends ComumException {

    private static final long serialVersionUID = 4419262988291718068L;

    /**
     * @param chave
     */
    public IntegracaoTransacionalException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public IntegracaoTransacionalException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param excecao
     */
    public IntegracaoTransacionalException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public IntegracaoTransacionalException(Throwable excecao) {
        super(excecao);
    }

}
