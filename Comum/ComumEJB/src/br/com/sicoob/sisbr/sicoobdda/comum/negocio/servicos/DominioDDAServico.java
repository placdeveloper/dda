/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         DominioDDAServico.java
 * Data Criação:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * DominioDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface DominioDDAServico extends ComumCrudServico<SicoobDDAEntidade> {

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoDivergente() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<AutorizacaoValorDivergente>
     * 
     */
    List<AutorizacaoValorDivergente> listarAutorizacaoDivergenteBanco() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<SituacaoBoletoPagamento>
     * 
     */
    List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<SituacaoBoletoPagamento>
     * 
     */
    List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamentoBanco() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException GradeHoraria
     * 
     */
    GradeHoraria obterGradeHorariaReferenciaDDA0110() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException GradeHoraria
     * 
     */
    GradeHoraria obterGradeHorariaReferenciaDDA0110Banco() throws ComumException;

}
