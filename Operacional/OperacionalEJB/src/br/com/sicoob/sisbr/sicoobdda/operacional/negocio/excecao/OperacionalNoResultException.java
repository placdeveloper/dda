/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao
 * Arquivo:         OperacionalNoResultException.java
 * Data Cria��o:    Sep 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao;

/**
 * OperacionalNoResultException � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public class OperacionalNoResultException extends OperacionalNegocionException {

    private static final long serialVersionUID = 4167067927545242910L;

    /**
     * @param chave
     */
    public OperacionalNoResultException(String chave) {
        super(chave);
    }

    public OperacionalNoResultException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    public OperacionalNoResultException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

}
