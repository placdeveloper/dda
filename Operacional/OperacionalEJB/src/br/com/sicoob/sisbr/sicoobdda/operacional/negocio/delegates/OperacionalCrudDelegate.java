/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         OperacionalCrudDelegate.java
 * Data Criação:    Mai 10, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumCrudDelegate;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema Operacional
 * 
 * @author Sicoob
 */
public abstract class OperacionalCrudDelegate<T extends SicoobDDAEntidade, S extends OperacionalCrudServico<T>> extends ComumCrudDelegate<T, S> {

}