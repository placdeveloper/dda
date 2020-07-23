package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * ConsultaBoletoCIPTimedOutException é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ConsultaBoletoCIPTimedOutException extends ComumNegocioException {

    private static final long serialVersionUID = 7991070163012676609L;

    /**
     * @param chave
     */
    public ConsultaBoletoCIPTimedOutException(String chave) {
        super(chave);
    }

}
