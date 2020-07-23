package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * BoletoCIPNaoEncontradoException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class BoletoCIPNaoEncontradoException extends SicoobDDANegocioException {

    private static final long serialVersionUID = 9042298596722030068L;

    /**
     * @param chave
     */
    public BoletoCIPNaoEncontradoException(String chave) {
        super(chave);
    }

    public BoletoCIPNaoEncontradoException(String mensagem, Throwable excecao) {
        super(mensagem, excecao);
    }

}
