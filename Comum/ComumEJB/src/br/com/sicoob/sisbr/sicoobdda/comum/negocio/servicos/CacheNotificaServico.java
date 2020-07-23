/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         CacheNotificaServico.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * CacheNotificaServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface CacheNotificaServico extends ComumServico {

    /**
     * Método responsável por
     * 
     * @param requisicao
     * @throws ComumException void
     * 
     */
    void notificarCache(RequisicaoCache requisicao) throws ComumException;
}
