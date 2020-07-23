package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * BancoNaoEncontradoException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class BancoNaoEncontradoException extends ComumNegocioException {

    private static final long serialVersionUID = 34042948946301225L;

    /**
     * @param numBanco
     */
    public BancoNaoEncontradoException(short numBanco) {
        super("adaptadorintegracaoservico.banco.nao.encontrado", numBanco);
    }

}
