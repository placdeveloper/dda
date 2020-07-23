/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         DominioDDADao.java
 * Data Cria��o:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * DominioDDADao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface DominioDDADao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por
     * 
     * @return List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<SituacaoBoletoPagamento>
     * 
     */
    List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoMensagems
     * @return GradeHoraria
     * 
     */
    GradeHoraria obterGradeHorariaReferencia(String codTipoMensagem) throws ComumException;

}
