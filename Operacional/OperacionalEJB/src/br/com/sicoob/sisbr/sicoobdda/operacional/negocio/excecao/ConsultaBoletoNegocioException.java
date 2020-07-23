package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * ConsultaBoletoNegocioException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class ConsultaBoletoNegocioException extends ComumNegocioException {

    private static final long serialVersionUID = -6144121156793095037L;

    /**
     * @param chave
     * @param parametros
     */
    public ConsultaBoletoNegocioException(String chave, Object... parametros) {
        super(chave, parametros);
    }

}
