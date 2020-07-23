package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * @author Samuell.Ramos
 */
public interface ITipoGradeHoraria {

    /**
     * @throws ComumException
     * @param codSubTipoGrade
     * @return
     * @throws OperacionalException
     * @throws
     */
    RetornoDTO listarTipoGradeHorariaPorCodigoSubtipo(RequisicaoReqDTO dto) throws ComumException;

    /**
     * @return
     * @throws ComumException
     */
    RetornoDTO obterTipoGradeHoraria(RequisicaoReqDTO dto) throws ComumException;

    /**
     * @param tipoGradeHoraria
     * @return
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     */
    RetornoDTO incluirTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalException, OperacionalNegocionException;

    /**
     * @param dto
     * @throws OperacionalNegocionException
     * @throws ComumException
     */
    void apagarTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalNegocionException, ComumException;

    /**
     * @param dto
     * @throws OperacionalException
     * @throws OperacionalNegocionException
     */
    void alterarTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalException, OperacionalNegocionException;

    /**
     * @return
     * @throws ComumException
     */
    RetornoDTO listarCombos() throws ComumException;

    /**
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO isExisteTipoGradeHoraria(RequisicaoReqDTO dto) throws ComumException;

}
