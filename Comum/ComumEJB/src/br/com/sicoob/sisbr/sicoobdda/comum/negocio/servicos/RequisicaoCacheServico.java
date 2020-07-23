/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         RequisicaoCacheServico.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface RequisicaoCacheServico extends ComumCrudServico<RequisicaoCache> {

    /**
     * Método responsável por
     * 
     * @param cacheEnum
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void incluir(CacheEnum cacheEnum) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param filtroDto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<RequisicaoCache>
     * 
     */
    List<RequisicaoCache> pesquisarRequisicao(FiltroRequisicaoCacheDto filtroDto) throws ComumException, ComumNegocioException;

}
