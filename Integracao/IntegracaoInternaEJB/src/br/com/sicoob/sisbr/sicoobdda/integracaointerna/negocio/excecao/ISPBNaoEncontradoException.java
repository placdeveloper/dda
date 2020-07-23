package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * ISPBNaoEncontradoException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = true)
public class ISPBNaoEncontradoException extends IntegracaoInternaException {

    private static final long serialVersionUID = 34042948946301225L;

    /**
     * @param ispb
     */
    public ISPBNaoEncontradoException(String ispb) {
        super("adaptadorintegracaoservico.ispb.nao.encontrado", ispb);
    }

}
