/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         OperacionalCrudServico.java
 * Data Criação:    May 10, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * OperacionalCrudServico
 * 
 * @author rafael.silva
 */
public interface OperacionalCrudServico<T extends SicoobDDAEntidade> extends OperacionalServico, ComumCrudServico<T> {

}
