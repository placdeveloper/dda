/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IMonitoracaoServico.java
 * Data Cria��o:    Nov 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IMonitoracaoServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IMonitoracaoServico {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracao() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoDDA0110(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoArquivoRemessa() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoracaoArquivoVarredura() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMonitoracaoDemaisMensagens() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMonitoracaoArquivoRemessa() throws ComumException;

}
