/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ComumDelegate.java
 * Data Cria��o:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;

/**
 * ComumDelegate � respons�vel por
 * 
 * @author Fabricio.Brandao
 */
public abstract class ComumDelegate<T extends ComumServico> extends BancoobDelegate<T> {

}
