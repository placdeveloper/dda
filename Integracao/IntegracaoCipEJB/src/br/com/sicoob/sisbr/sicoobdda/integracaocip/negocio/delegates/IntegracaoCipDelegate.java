package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipDelegate<T extends IntegracaoCipServico> extends ComumDelegate<T> {

    /**
     * 
     */
    public IntegracaoCipDelegate() {
    }

}