package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * BoletoCIPNaoEncontradoException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class BoletoCIPNaoEncontradoException extends ComumNegocioException {

    private static final long serialVersionUID = 9042298596722030068L;

    /**
     * @param chave
     */
    public BoletoCIPNaoEncontradoException(String chave) {
        super(chave);
    }

    /**
     * @param chave
     * @param excecao
     */
    public BoletoCIPNaoEncontradoException(String chave, Throwable excecao) {
        super(chave, excecao);
    }

}
