/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IServidorDDAServico.java
 * Data Cria��o:    2 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IServidorDDAServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IServidorDDAServico {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarServidorAtivo() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarServidorDDA() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO pesquisarServidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void alterarSevidorDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

}
