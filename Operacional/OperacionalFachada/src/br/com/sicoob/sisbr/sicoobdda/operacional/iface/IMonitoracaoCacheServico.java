/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IMonitoracaoCache.java
 * Data Cria��o:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IMonitoracaoCache � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IMonitoracaoCacheServico {

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO pesquisarRequisicaoCache(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO listarAtualizacaoCache(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarRequisicaoCache(RequisicaoReqDTO dto) throws BancoobException;

}
