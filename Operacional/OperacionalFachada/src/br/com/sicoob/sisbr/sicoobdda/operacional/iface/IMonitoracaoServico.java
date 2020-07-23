/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IMonitoracaoServico.java
 * Data Criação:    Nov 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IMonitoracaoServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface IMonitoracaoServico {

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracao() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoDDA0110(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoArquivoVarredura() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMonitoracaoArquivoRemessa() throws ComumException;

}
