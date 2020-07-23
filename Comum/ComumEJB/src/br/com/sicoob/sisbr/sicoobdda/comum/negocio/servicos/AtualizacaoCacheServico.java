/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         AtualizacaoCacheServico.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * AtualizacaoCacheServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface AtualizacaoCacheServico extends ComumCrudServico<AtualizacaoCache> {

    /**
     * Método responsável por
     * 
     * @param requisicaoCache
     * @throws ComumException void
     * 
     */
    void processarRequisicao(RequisicaoCache requisicaoCache) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param idRequisicaoCache
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<AtualizacaoCache>
     * 
     */
    List<AtualizacaoCache> listar(Long idRequisicaoCache) throws ComumException, ComumNegocioException;

}
