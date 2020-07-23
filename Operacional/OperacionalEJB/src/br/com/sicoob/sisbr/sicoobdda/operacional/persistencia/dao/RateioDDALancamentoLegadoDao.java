/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipLegadoDao.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;

/**
 * IntegracaoCipLegadoDao
 * 
 * @author Rafael.Silva
 */
public interface RateioDDALancamentoLegadoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * 
     * @param lancamentoIntegracaoDto
     * @return LancamentoIntegracaoRetDto
     */
    LancamentoIntegracaoRetDto gravarLancamentoIntegracaoSP(Integer numCooperativa, LancamentoIntegracaoDto lancamentoIntegracaoDto);

}
