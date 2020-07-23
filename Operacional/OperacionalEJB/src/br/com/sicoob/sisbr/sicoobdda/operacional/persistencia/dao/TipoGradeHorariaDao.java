/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         TipoGradeHorariaDDADao.java
 * Data Criação:    Ago 18, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * TipoGradeHorariaDDADao
 * 
 * @author samuell.ramos
 */
public interface TipoGradeHorariaDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException
     */
    Boolean isExisteEmTipoMensagem(String codTipoGradeHoraria) throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException
     */
    Boolean isExisteEmGradeHoraria(String codTipoGradeHoraria) throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException
     */
    Boolean isExisteEmGradeOrigem(String codTipoGradeHoraria) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param codSubTipoGrade
     * @return
     * @throws OperacionalException
     */
    List<TipoGradeHorariaDto> listarTipoGradeHorariaPorCodigoSubtipo(Short codSubTipoGrade) throws OperacionalException;

    /**
     * @return
     * @throws OperacionalException
     * @throws ComumException List<TipoGradeHoraria>
     * 
     */
    List<TipoGradeHoraria> listarCodigosTipoGradeHoraria() throws OperacionalException, ComumException;

    /**
     * 
     * Método responsável por listar Tipo Grade Horaria sem Grade Horaria personalizada
     * 
     * @param dataReferencia
     * @return
     * @throws ComumException
     * @throws OperacionalException
     */
    List<TipoGradeHorariaDto> listarTipoGradeHorariaPersonalizadaSemGradeHoraria(DateTimeDB dataReferencia) throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws OperacionalException
     * @throws ComumException TipoGradeHoraria
     * 
     */
    TipoGradeHoraria obterTipoGradeHoraria(String codTipoGradeHoraria) throws OperacionalException, ComumException;

    /**
     * @return
     * @throws ComumException List<SubTipoGrade>
     * 
     */
    List<SubTipoGrade> listarSubTipoGrade() throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws OperacionalException
     * @throws ComumException Long
     * 
     */
    Long obterCountTipoGradeHoraria(String codTipoGradeHoraria) throws OperacionalException, ComumException;

    /**
     * @return
     * @throws OperacionalException
     * @throws ComumException List<TipoGradeHoraria>
     * 
     */
    List<TipoGradeHoraria> listarTipoGradeHorariaDDA() throws OperacionalException, ComumException;

}