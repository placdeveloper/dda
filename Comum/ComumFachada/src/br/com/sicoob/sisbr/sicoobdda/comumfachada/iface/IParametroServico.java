/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.iface
 * Arquivo:         IParametroServico.java
 * Data Criação:    18 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.iface;

import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IParametroServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface IParametroServico {

    /**
     * Método responsável por obter as informações pra tela de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO obterDadosPesquisa() throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter as informações pra tela de pesquisa.
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    RetornoDTO obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter os dados do parametro
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
     * Método responsável por obter a lista de parametro
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarTipoParametro() throws ComumException;

    /**
     * 
     * Método responsável por incluir parametro
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO incluirParametro(RequisicaoReqDTO dto) throws ComumException;

    /**
     * 
     * Método responsável por alterar parametro
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO alterarParametro(RequisicaoReqDTO dto) throws ComumException;

}
