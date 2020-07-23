package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema Processamento.
 * 
 * @author Sicoob
 */
public final class ProcessamentoFabricaDelegates extends ComumFabricaDelegates {

    /** Instancia do Fabrica de Delegates. */
    private static ProcessamentoFabricaDelegates fabrica;

    /**
     * Retorna a fabrica de delegates a ser utilizada.
     * 
     * @return a fabrica de delegates a ser utilizada.
     */
    public static ProcessamentoFabricaDelegates getInstance() {
        if (fabrica == null) {
            fabrica = new ProcessamentoFabricaDelegates();
        }
        return fabrica;
    }

    /**
     * Construtor privado no-args da classe
     */
    private ProcessamentoFabricaDelegates() {

    }
}