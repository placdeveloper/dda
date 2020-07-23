package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipCrudServicoEJB<T extends SicoobDDAEntidade> extends ComumCrudServicoEJB<T> {

}