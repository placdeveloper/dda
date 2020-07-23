package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema Processamento
 * 
 * @author Sicoob
 */
public abstract class ProcessamentoCrudServicoEJB<T extends SicoobDDAEntidade> extends ComumCrudServicoEJB<T> {

}