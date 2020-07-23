/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ComumDelegate.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;

/**
 * ComumDelegate é responsável por
 * 
 * @author Fabricio.Brandao
 */
public abstract class ComumDelegate<T extends ComumServico> extends BancoobDelegate<T> {

}
