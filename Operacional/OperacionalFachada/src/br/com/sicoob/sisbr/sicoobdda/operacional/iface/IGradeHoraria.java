package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * @author Samuell.Ramos
 */
public interface IGradeHoraria {

    /**
     * @return
     * @throws OperacionalException
     * @throws ComumException
     */
    RetornoDTO listarTipoGradeHoraria() throws OperacionalException, ComumException;

    /**
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    RetornoDTO incluirGradeHoraria(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    RetornoDTO alterarGradeHoraria(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    DadosSelGeralDTO pesquisarGradeHorariaPaginada(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO obterGradeHoraria(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO removerGradeHoraria(RequisicaoReqDTO dto) throws BancoobException;
}
