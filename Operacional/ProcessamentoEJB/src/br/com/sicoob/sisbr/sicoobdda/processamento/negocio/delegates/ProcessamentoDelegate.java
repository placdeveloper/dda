package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ProcessamentoServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema Processamento
 * 
 * @author Sicoob
 */
public abstract class ProcessamentoDelegate <T extends ProcessamentoServico> extends ComumDelegate<T> {
	
	/**
     * Construtor
     */
    public ProcessamentoDelegate() {
    }
}