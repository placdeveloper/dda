/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         RequisicaoCacheDaoImpl.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.RequisicaoCacheDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class RequisicaoCacheDaoImpl extends ComumCrudDaoDB2<RequisicaoCache> implements RequisicaoCacheDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_comum.requisicaocache.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-requisicaocache";

    /**
     * 
     */
    public RequisicaoCacheDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, RequisicaoCache.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.RequisicaoCacheDao#listar(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto)
     */
    @Override
    public List<RequisicaoCache> listar(FiltroRequisicaoCacheDto filtroDto) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(filtroDto.getDataRequisicao(), "dataRequisicao");
        parametros.put("codServidorDestino", filtroDto.getCodServidorDestino());
        parametros.put("descCache", filtroDto.getDescCache());
        parametros.put("descPerfilDestino", filtroDto.getDescPerfilDestino());
        return listar("LISTAR_REQUISICAO_CACHE", parametros);
    }

}
