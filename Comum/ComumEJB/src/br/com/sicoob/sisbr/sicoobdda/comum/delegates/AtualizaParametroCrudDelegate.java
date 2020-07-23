package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

public abstract class AtualizaParametroCrudDelegate<T extends SicoobDDAEntidade, S extends ComumCrudServico<T>> extends BancoobCrudDelegate<T, S> {

}
