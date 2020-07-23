/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         AtualizacaoCacheDaoImpl.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.AtualizacaoCacheDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;

/**
 * AtualizacaoCacheDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class AtualizacaoCacheDaoImpl extends ComumCrudDaoDB2<AtualizacaoCache> implements AtualizacaoCacheDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_comum.atualizacaocache.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-atualizacaocache";

    /**
     * 
     */
    public AtualizacaoCacheDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, AtualizacaoCache.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.AtualizacaoCacheDao#listar(java.lang.Long)
     */
    @Override
    public List<AtualizacaoCache> listar(Long idRequisicaoCache) throws ComumException {
        return listar("LISTAR_ATUALIZACAO_CACHE", getMapaParametro(idRequisicaoCache, "idRequisicaoCache"));
    }

}
