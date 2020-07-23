/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         CacheNotificaServico.java
 * Data Cria��o:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * CacheNotificaServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface CacheNotificaServico extends ComumServico {

    /**
     * M�todo respons�vel por
     * 
     * @param requisicao
     * @throws ComumException void
     * 
     */
    void notificarCache(RequisicaoCache requisicao) throws ComumException;
}
