/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.servicos.ejb
 * Arquivo:         IntegracaoInternaPrivadaCrudServicoEJB.java
 * Data Criação:    Oct 22, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * OperacionalCrudServicoEJB
 * 
 * @author rafael.silva
 */
public abstract class OperacionalCrudServicoEJB<T extends SicoobDDAEntidade> extends ComumCrudServicoEJB<T> {

}
