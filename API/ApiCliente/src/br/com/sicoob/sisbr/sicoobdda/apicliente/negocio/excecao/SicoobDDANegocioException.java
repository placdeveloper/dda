/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao
 * Arquivo:         SicoobDDANegocioException.java
 * Data Criação:    12/05/2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * SicoobDDANegocioException é responsável por
 * 
 * @author Daniel.Basso
 */
@ApplicationException(rollback = false)
public class SicoobDDANegocioException extends NegocioException {

    private static final long serialVersionUID = 136274181348347217L;

    /**
     * @param chave
     */
    public SicoobDDANegocioException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public SicoobDDANegocioException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public SicoobDDANegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public SicoobDDANegocioException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public SicoobDDANegocioException(Throwable excecao) {
        super(excecao);
    }
}
