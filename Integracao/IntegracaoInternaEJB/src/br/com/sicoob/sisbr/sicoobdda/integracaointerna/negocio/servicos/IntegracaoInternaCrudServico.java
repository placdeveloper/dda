/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.servicos
 * Arquivo:         IntegracaoInternaPrivadaCrudServico.java
 * Data Criação:    Oct 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * IntegracaoInternaPrivadaCrudServico é responsável por
 * 
 * @author Samuell.Ramos
 */
public interface IntegracaoInternaCrudServico<T extends SicoobDDAEntidade> extends IntegracaoInternaServico, ComumCrudServico<T> {

}
