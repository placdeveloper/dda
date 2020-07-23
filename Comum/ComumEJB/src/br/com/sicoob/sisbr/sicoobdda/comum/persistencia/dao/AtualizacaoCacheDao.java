/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         AtualizacaoCacheDao.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;

/**
 * AtualizacaoCacheDao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface AtualizacaoCacheDao extends ComumCrudDaoIF<AtualizacaoCache> {

    /**
     * Método responsável por
     * 
     * @param idRequisicaoCache
     * @return
     * @throws ComumException List<AtualizacaoCache>
     * 
     */
    List<AtualizacaoCache> listar(Long idRequisicaoCache) throws ComumException;

}
