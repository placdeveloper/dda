package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaServico;

/**
 * IntegracaoInternaPrivadaDelegate é responsável por
 * 
 * @author Samuell.Ramos
 */
public abstract class IntegracaoInternaDelegate<T extends IntegracaoInternaServico> extends ComumDelegate<T> {

    /**
     * Construtor
     */
    public IntegracaoInternaDelegate() {
    }

}
