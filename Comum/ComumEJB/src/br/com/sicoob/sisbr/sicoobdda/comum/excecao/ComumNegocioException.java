/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.excecao
 * Arquivo:         ComumNegocioException.java
 * Data Cria��o:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * ComumNegocioException
 */
@ApplicationException(rollback = false)
public class ComumNegocioException extends NegocioException {

    private static final long serialVersionUID = 136274181348347217L;

    /**
     * @param chave
     */
    public ComumNegocioException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public ComumNegocioException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public ComumNegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public ComumNegocioException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public ComumNegocioException(Throwable excecao) {
        super(excecao);
    }
}
