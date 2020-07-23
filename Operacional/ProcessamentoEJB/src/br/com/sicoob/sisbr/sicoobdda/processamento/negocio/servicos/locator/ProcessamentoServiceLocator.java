package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.locator;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;

/**
 * Service Locator usado pelo sistema Processamento.
 * 
 * @author Sicoob
 */
public class ProcessamentoServiceLocator extends ComumServiceLocator {

    private static ProcessamentoServiceLocator locator;

    /**
     * Singleton da class
     * 
     * @return A instancia da classe
     */
    public static ProcessamentoServiceLocator getInstance() {
        if (locator == null) {
            locator = new ProcessamentoServiceLocator();
        }

        return locator;
    }

    /**
     * @param nomeAplicacao
     */
    private ProcessamentoServiceLocator() {
        super("sicoobdda_processamento");
    }
}