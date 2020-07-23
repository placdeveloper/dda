/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IServidorDDAServico.java
 * Data Criação:    2 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IServidorDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface IServidorDDAServico {

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarServidorAtivo() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarServidorDDA() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO pesquisarServidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void alterarSevidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

}
