package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobException;

/**
 * ComumException
 */
@ApplicationException(rollback = true)
public class ComumException extends BancoobException {
    public static final long serialVersionUID = 1L;

    /**
     * @param chave
     */
    public ComumException(String chave) {
        super(recuperarMensagem(chave));
    }

    /**
     * @param chave
     * @param parametros
     * @param excecao
     */
    public ComumException(String chave, Object[] parametros, Throwable excecao) {
        super(chave, parametros, excecao);
    }

    /**
     * @param chave
     * @param parametros
     */
    public ComumException(String chave, Object... parametros) {
        super(chave, parametros);
    }

    /**
     * @param chave
     * @param excecao
     */
    public ComumException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

    /**
     * @param excecao
     */
    public ComumException(Throwable excecao) {
        super(excecao);
    }
}
