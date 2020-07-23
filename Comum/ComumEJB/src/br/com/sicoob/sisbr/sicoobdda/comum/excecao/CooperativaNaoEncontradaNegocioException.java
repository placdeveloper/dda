package br.com.sicoob.sisbr.sicoobdda.comum.excecao;

import javax.ejb.ApplicationException;

/**
 * CooperativaNaoEncontradaNegocioException é responsável por
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
        // IMPORTANTE: o texto está fixo aqui porque se colocar no arquivo de properties e lançar a exceção a partir de outro projeto ele não acha a mensagem
        super("A cooperativa {1} não foi encontrada.", cooperativa, excecao);
    }

}
