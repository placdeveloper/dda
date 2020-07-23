/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         TipoGradeHorariaDDADao.java
 * Data Criação:    Ago 18, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaOrigemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * TipoGradeHorariaDDADao
 * 
 * @author samuell.ramos
 */
public interface GradeHorariaDao extends OperacionalCrudDaoIF<GradeHoraria> {

    /**
     * @param codTipoGradeHoraria
     * @param dataReferencia
     * @throws ComumException void
     * 
     */
    void removerGrades(String codTipoGradeHoraria, Date dataReferencia) throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @param dataReferencia
     * @return
     * @throws ComumException List<GradeHorariaDto>
     * 
     */
    List<GradeHorariaDto> listarGrades(String codTipoGradeHoraria, Date dataReferencia) throws ComumException;

    /**
     * @param idGradeHoraria
     * @return
     * @throws ComumException GradeHorariaDto
     * 
     */
    GradeHorariaDto obterGradeHoraria(Long idGradeHoraria) throws ComumException;

    /**
     * @param dataReferencia
     * @throws ComumException void
     * 
     */
    void incluirGradeHorariaCIP(Date dataReferencia) throws ComumException;

    /**
     * 
     * Método responsável por incluir a grade para a data do movimento com base na última grade válida
     * 
     * @param dataReferencia
     * @param dataUltimaGrade
     * @param codTipoGradeHoraria
     * @throws ComumException void
     * 
     */
    void incluirGradeHorariaPersonalizada(Date dataReferencia, Date dataUltimaGrade, String codTipoGradeHoraria) throws ComumException;

    /**
     * 
     * Método responsável por verificar se existe grade extrapolando a grande origem
     * 
     * @param dataReferencia
     * @return
     * @throws OperacionalException
     */
    List<GradeHorariaOrigemDto> listarGradeHorariaPersonalizadaExtrapolada(Date dataReferencia) throws OperacionalException;

    /**
     * Método responsável por excluir as grades gravadas antes a pertir de 10 dias atrás.
     * 
     * @throws ComumException void
     * 
     */
    void excluirGradesHorariasAntigas() throws ComumException;
}