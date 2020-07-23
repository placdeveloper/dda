/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         RequisicaoCacheServico.java
 * Data Cria��o:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface RequisicaoCacheServico extends ComumCrudServico<RequisicaoCache> {

    /**
     * M�todo respons�vel por
     * 
     * @param cacheEnum
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void incluir(CacheEnum cacheEnum) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param filtroDto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<RequisicaoCache>
     * 
     */
    List<RequisicaoCache> pesquisarRequisicao(FiltroRequisicaoCacheDto filtroDto) throws ComumException, ComumNegocioException;

}
