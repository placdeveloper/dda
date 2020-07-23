package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * ConsultaBoletoNegocioException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class ConsultaBoletoNegocioException extends SicoobDDANegocioException {

    private static final long serialVersionUID = 9042298596722030068L;

    /**
     * @param chave
     */
    public ConsultaBoletoNegocioException(String chave) {
        super(chave);
    }

    public ConsultaBoletoNegocioException(String mensagem, Throwable excecao) {
        super(mensagem, excecao);
    }

}
