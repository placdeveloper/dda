/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api_cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao
 * Arquivo:         SicoobDDAException.java
 * Data Criação:    12/05/2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;

/**
 * ComumException é responsável por
 * 
 * @author Daniel.BAsso
 */
@ApplicationException(rollback = true)
public class SicoobDDAException extends BancoobException {
    public static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public SicoobDDAException(String chave) {
        super(recuperarMensagem(chave));
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public SicoobDDAException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public SicoobDDAException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public SicoobDDAException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public SicoobDDAException(Throwable excecao) {
        super(excecao);
    }
}
