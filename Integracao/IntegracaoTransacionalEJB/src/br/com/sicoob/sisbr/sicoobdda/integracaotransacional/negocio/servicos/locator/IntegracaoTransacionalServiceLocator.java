package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.locator;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;

/**
 * Service Locator usado pelo sistema Integracao Transacional.
 * 
 * @author george.santos
 */
public class IntegracaoTransacionalServiceLocator extends ComumServiceLocator {

    private static IntegracaoTransacionalServiceLocator locator;

    /**
     * Singleton da class
     * 
     * @return A instancia da classe
     */
    public static IntegracaoTransacionalServiceLocator getInstance() {
        if (locator == null) {
            locator = new IntegracaoTransacionalServiceLocator();
        }

        return locator;
    }

    /**
     * @param nomeAplicacao
     */
    private IntegracaoTransacionalServiceLocator() {
        super("sicoobdda_integracaotransacional");
    }

}
