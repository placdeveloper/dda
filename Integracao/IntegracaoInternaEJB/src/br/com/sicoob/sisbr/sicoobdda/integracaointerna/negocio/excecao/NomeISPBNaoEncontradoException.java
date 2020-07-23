package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * NomeISPBNaoEncontradoException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = true)
public class NomeISPBNaoEncontradoException extends IntegracaoInternaException {

    private static final long serialVersionUID = 34042948946301225L;

    /**
     * @param ispb
     */
    public NomeISPBNaoEncontradoException(String ispb) {
        super("adaptadorintegracaoservico.nome.ispb.nao.encontrado", ispb);
    }

}
