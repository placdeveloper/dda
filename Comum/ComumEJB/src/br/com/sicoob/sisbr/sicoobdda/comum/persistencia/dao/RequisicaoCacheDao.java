/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         RequisicaoCacheDao.java
 * Data Cria��o:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface RequisicaoCacheDao extends ComumCrudDaoIF<RequisicaoCache> {

    /**
     * M�todo respons�vel por
     * 
     * @param filtroDto
     * @return List<RequisicaoCache>
     * 
     */
    List<RequisicaoCache> listar(FiltroRequisicaoCacheDto filtroDto) throws ComumException;

}
