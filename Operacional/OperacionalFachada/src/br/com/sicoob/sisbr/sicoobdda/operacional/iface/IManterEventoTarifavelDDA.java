package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;


/**
 * @author Samuell.Ramos
 */
public interface IManterEventoTarifavelDDA {

    /**
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listaEventoTarifavelDDA() throws ComumException;

    /**
     * @param reqDto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO pesquisaEventoTarifavelDDA(RequisicaoReqDTO reqDto) throws ComumException;

    /**
     * @param reqDto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterEventoTarifavelDDATarifa(RequisicaoReqDTO reqDto) throws ComumException;

    /**
     * @param reqDto
     * @throws ComumException void
     * @throws ComumNegocioException
     * @throws BancoobException
     * 
     */
    RetornoDTO manterEventoTarifavel(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * @param reqDto
     * @throws ComumException
     * @throws ComumNegocioException void
     * @throws BancoobException
     * 
     */
    void excluirEventoTarifavelDDA(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

}
