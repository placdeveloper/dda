package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

/**
 * CooperativaNaoEncontradaNegocioException � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = false)
public class CooperativaNaoEncontradaNegocioException extends ComumNegocioException {

    private static final long serialVersionUID = -4051884468741388999L;

    /**
     * @param chave
     * @param excecao
     */
    public CooperativaNaoEncontradaNegocioException(int cooperativa, Throwable excecao) {
        // IMPORTANTE: o texto est� fixo aqui porque se colocar no arquivo de properties e lan�ar a exce��o a partir de outro projeto ele n�o acha a mensagem
        super("A cooperativa {1} n�o foi encontrada.", cooperativa, excecao);
    }

}
