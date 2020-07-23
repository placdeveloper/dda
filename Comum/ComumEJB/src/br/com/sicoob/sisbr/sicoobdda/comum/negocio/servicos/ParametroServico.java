/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         ParametroServico.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

/**
 * ParametroServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ParametroServico extends ComumCrudServico<ParametroDDA> {

    /**
     * Método responsável por listar todos os parametros globais que estão ativos.
     * 
     * @return
     * @throws ComumException List<ParametroDDA>
     */
    List<ParametroDDA> listarAtivos() throws ComumException;

    /**
     * Método responsável por obter a lista de parametros no Cache Central.
     * 
     * @return
     * @throws ComumException List<ParametroDDA>
     */
    public List<ParametroDDA> listarAtivosCache() throws ComumException;

    /**
     * Método responsável por obter o parametro primeiro no cache, depois no banco.
     * 
     * @param idParametro
     * @return
     * @throws ComumException ParametroDDA
     */
    ParametroDDA obter(Long idParametro) throws ComumException;

    /**
     * Método responsável por obter o parametro primeiro no cache, depois no banco.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException ParametroDDA
     */
    ParametroDDA obter(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter a lista de parâmetro para o combo de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ParametroDto
     * 
     */
    ParametroDto obterDadosPesquisa() throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter a lista de parâmetro para o combo de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ParametroDto
     * 
     */
    ParametroDto obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException;

    /**
     * 
     * Método responsável por obter lista de tipo de parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> listarTipoParametro() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException List<ParametroDDA>
     * 
     */
    List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param listaSingular
     * @return
     * @throws ComumException List<ValorParametroDDA>
     * 
     */
    List<ValorParametroDDA> listarValorParametro(List<Integer> listaSingular) throws ComumException;

    /**
     * 
     * Método responsável por obter o valor por instituição
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    String obterValorParametro(Integer idInstituicao, Long idParametro) throws ComumException;

    /**
     * Método responsável por incluir o parâmetro e atualizar as informações de valor parâmetro.
     * 
     * @param parametro
     * @return
     * @throws ComumException
     */
    ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException;

    /**
     * Método responsável por alterar o parâmetro e atualizar as informações de valor parâmetro no DB2 e legado.
     * 
     * @param parametro
     * @throws ComumException
     */
    void alterarParametro(ParametroDDA parametro) throws ComumException;

    /**
     * Método responsável por recuperar lista de parâmetros
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException
     */
    void atualizarValorParametro() throws ComumException;

    /**
     * Método responsável por atualizar a lista de valor parametro de um ou mais unidades
     * 
     * @param parametro
     * @param listaValorParametro
     * @param idInstituicao
     * @param listaNumCooperativa
     * @throws ComumException
     */
    void atualizarValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao) throws ComumException;

    /**
     * Método responsável por obter valor Boolean do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * 
     * @param idParametro
     * @return Boolean
     * 
     */
    Boolean obterValorBoolean(Long idParametro) throws ComumException;

    /**
     * Método responsável por obter valor Boolean do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return Boolean
     * @throws ComumException
     */
    Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter valor Integer do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @return
     * @throws ComumException Integer
     * 
     */
    Integer obterValorInteger(Long idParametro) throws ComumException;

    /**
     * Método responsável por obter valor Double do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @return
     * @throws ComumException Double
     * 
     */
    Double obterValorDouble(Long idParametro) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorParametroBoolean(Long idParametro, Integer idInstituicao) throws ComumException;
}
