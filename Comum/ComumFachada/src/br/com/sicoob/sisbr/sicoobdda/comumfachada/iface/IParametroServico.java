/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.iface
 * Arquivo:         IParametroServico.java
 * Data Cria��o:    18 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.iface;

import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IParametroServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IParametroServico {

    /**
     * M�todo respons�vel por obter as informa��es pra tela de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO obterDadosPesquisa() throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter as informa��es pra tela de pesquisa.
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter os dados do parametro
     * 
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * @throws ComumNegocioException
     * 
     */
    DadosSelGeralDTO obterDadosParametro(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * 
     * M�todo respons�vel por obter a lista de parametro
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarTipoParametro() throws ComumException;

    /**
     * 
     * M�todo respons�vel por incluir parametro
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO incluirParametro(RequisicaoReqDTO dto) throws ComumException;

    /**
     * 
     * M�todo respons�vel por alterar parametro
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO alterarParametro(RequisicaoReqDTO dto) throws ComumException;

}
