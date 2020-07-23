/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao
 * Arquivo:         OperacionalNegocionException.java
 * Data Criação:    Sep 15, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * OperacionalNegocionException é responsável por
 * 
 * @author felipe.rosa
 */
@ApplicationException(rollback = true)
public class OperacionalNegocionException extends ComumNegocioException {

    private static final long serialVersionUID = 9094342952045327778L;

    /**
     * @param chave
     */
    public OperacionalNegocionException(String chave) {
        super(chave);
    }

    /**
     * @param string
     * @param preparaStringLista
     */
    public OperacionalNegocionException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    public OperacionalNegocionException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);

    }

    public OperacionalNegocionException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

}
