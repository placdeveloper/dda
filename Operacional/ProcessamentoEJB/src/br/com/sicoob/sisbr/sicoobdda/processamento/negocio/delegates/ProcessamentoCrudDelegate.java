package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumCrudDelegate;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ProcessamentoCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema Processamento
 * 
 * @author Sicoob
 */
public abstract class ProcessamentoCrudDelegate<T extends SicoobDDAEntidade, S extends ProcessamentoCrudServico<T>> extends ComumCrudDelegate<T, S> {

}