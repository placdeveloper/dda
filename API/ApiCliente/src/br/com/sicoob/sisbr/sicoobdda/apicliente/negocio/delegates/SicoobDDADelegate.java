/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         SicoobDDADelegate.java
 * Data Criação:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.bancoob.negocio.servicos.BancoobServico;

/**
 * SicoobDDADelegate é responsável por
 * 
 * @author Daniel.Basso
 */
public abstract class SicoobDDADelegate<T extends BancoobServico> extends BancoobDelegate<T> {

}
