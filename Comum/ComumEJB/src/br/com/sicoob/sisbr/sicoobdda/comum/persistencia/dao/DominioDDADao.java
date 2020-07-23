/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         DominioDDADao.java
 * Data Criação:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * DominioDDADao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface DominioDDADao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por
     * 
     * @return List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return List<SituacaoBoletoPagamento>
     * 
     */
    List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param codTipoMensagems
     * @return GradeHoraria
     * 
     */
    GradeHoraria obterGradeHorariaReferencia(String codTipoMensagem) throws ComumException;

}
