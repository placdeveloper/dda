package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.SCIServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.SSPBServico;

/**
 * Service Locator usado pelo sistema IntegracaoInternaPrivada.
 * 
 * @author Samuell.Ramos
 */
public class IntegracaoInternaServiceLocator extends ComumServiceLocator {

    private static IntegracaoInternaServiceLocator locator;

    /**
     * Singleton da class
     * 
     * @return A instancia da classe
     */
    public static IntegracaoInternaServiceLocator getInstance() {
        if (locator == null) {
            locator = new IntegracaoInternaServiceLocator();
        }
        return locator;
    }

    /**
     * @param nomeAplicacao
     */
    private IntegracaoInternaServiceLocator() {
        super("sicoobdda_integracaointerna");
    }

    /**
     * 
     * @return SCIServico
     * 
     */
    public SCIServico localizarSCIServico() {
        return (SCIServico) localizar("servico.sicoobdda.integracaointerna.SCIServico");
    }

    /**
     * Método responsável por
     * 
     * @return SSPBServico
     * 
     */
    public SSPBServico localizarSSPBServico() {
        return (SSPBServico) localizar("servico.sicoobdda.integracaointerna.SSPBServico");
    }

    /**
     * Método responsável por criar o serviço
     * 
     * @return ADMServico
     * 
     */
    public ADMServico localizarADMServico() {
        return (ADMServico) localizar("servico.sicoobdda.integracaointerna.ADMServico");
    }

    /**
     * Método responsável por criar o serviço
     * 
     * @return ADMServico
     * 
     */
    public ContaCorrenteServico localizarContaCorrenteServico() {
        return (ContaCorrenteServico) localizar("servico.sicoobdda.integracaointerna.ContaCorrenteServico");
    }

}
