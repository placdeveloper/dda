package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema Integracao Transacional
 * 
 * @author george.santos
 */
public abstract class IntegracaoTransacionalCrudServicoEJB<T extends SicoobDDAEntidade> extends ComumCrudServicoEJB<T> {

}