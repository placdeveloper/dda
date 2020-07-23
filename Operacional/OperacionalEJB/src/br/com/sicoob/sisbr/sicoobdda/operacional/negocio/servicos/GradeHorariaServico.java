/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         TipoGradeHorariaServico.java
 * Data Criação:    19 08, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * TipoGradeHorariaServico
 * 
 * @author Samuell.Ramos
 */

public interface GradeHorariaServico extends OperacionalCrudServico<GradeHoraria> {

    /**
     * @return
     * @throws OperacionalException
     */
    List<TipoGradeHorariaDto> listarTipoGradeHoraria() throws OperacionalException;

    /**
     * @param gradeHorariaDto
     * @throws ComumNegocioException
     * @throws OperacionalException
     * @throws ComumException
     */
    void incluirGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumNegocioException, OperacionalException, ComumException;

    /**
     * @param gradeHoraria
     * @throws ComumException void
     * 
     */
    void removerGradeHoraria(GradeHoraria gradeHoraria) throws ComumException;

    /**
     * @param gradeHorariaDto
     * @return
     * @throws ComumException GradeHorariaDto
     * 
     */
    GradeHorariaDto obterGradeHoraria(GradeHorariaDto gradeHorariaDto) throws ComumException;

    /**
     * 
     * Método responsável por verificar se há tipo de grade personalizada sem grade horaria. Copia a grade padrao CIP
     * 
     * @throws BancoobException void
     * 
     */
    void cadastrarGradeHoraria() throws BancoobException;

}
