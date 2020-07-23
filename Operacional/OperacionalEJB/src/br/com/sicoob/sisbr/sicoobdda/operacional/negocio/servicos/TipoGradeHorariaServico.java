/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         TipoGradeHorariaServico.java
 * Data Criação:    19 08, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * TipoGradeHorariaServico
 * 
 * @author Samuell.Ramos
 */

public interface TipoGradeHorariaServico extends OperacionalServico {

    /**
     * @param codSubTipoGrade
     * @return
     * @throws OperacionalException
     */
    List<TipoGradeHorariaDto> listarTipoGradeHorariaPorCodigoSubtipo(Short codSubTipoGrade) throws OperacionalException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException TipoGradeHorariaDto
     * 
     */
    TipoGradeHorariaDto obterTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException;

    /**
     * @param tipoGradeHoraria
     * @return
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     * @throws ComumException
     */
    Boolean incluirTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException;

    /**
     * @param tipoGradeHorariaDto
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     * @throws ComumException
     */
    void apagarTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) throws OperacionalNegocionException, ComumException;

    /**
     * @param tipoGradeHoraria
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     */
    void alterarTipoGradeHoraria(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalException, OperacionalNegocionException;

    /**
     * @return
     * @throws ComumException TipoGradeHorariaDto
     * 
     */
    TipoGradeHorariaDto listarTipoGradeHorariaSubtipoGrade() throws ComumException;

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean isExisteTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException;
}
