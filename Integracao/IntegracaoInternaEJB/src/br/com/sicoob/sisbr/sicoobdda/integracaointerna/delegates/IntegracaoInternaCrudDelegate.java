package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumCrudDelegate;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoInternaCrudDelegate<T extends SicoobDDAEntidade, S extends IntegracaoInternaCrudServico<T>> extends ComumCrudDelegate<T, S> {

}