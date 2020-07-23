/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         IntegracaoCipCrudServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public interface IntegracaoCipCrudServico<T extends SicoobDDAEntidade> extends IntegracaoCipServico, ComumCrudServico<T> {

}