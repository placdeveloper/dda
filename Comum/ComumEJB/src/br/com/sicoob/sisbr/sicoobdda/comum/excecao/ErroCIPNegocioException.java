/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.excecao
 * Arquivo:         ComumNegocioException.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * ErroCIPNegocioException
 * 
 * Erro EDDA retornados pela CIP.
 */
@ApplicationException(rollback = false)
public class ErroCIPNegocioException extends NegocioException {

    private static final long serialVersionUID = 136274181348347217L;

    /**
     * @param chave
     */
    public ErroCIPNegocioException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public ErroCIPNegocioException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public ErroCIPNegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public ErroCIPNegocioException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public ErroCIPNegocioException(Throwable excecao) {
        super(excecao);
    }
}
