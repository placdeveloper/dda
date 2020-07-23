/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         ParametroServico.java
 * Data Cria��o:    8 de set de 2018
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
 * ParametroServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ParametroServico extends ComumCrudServico<ParametroDDA> {

    /**
     * M�todo respons�vel por listar todos os parametros globais que est�o ativos.
     * 
     * @return
     * @throws ComumException List<ParametroDDA>
     */
    List<ParametroDDA> listarAtivos() throws ComumException;

    /**
     * M�todo respons�vel por obter a lista de parametros no Cache Central.
     * 
     * @return
     * @throws ComumException List<ParametroDDA>
     */
    public List<ParametroDDA> listarAtivosCache() throws ComumException;

    /**
     * M�todo respons�vel por obter o parametro primeiro no cache, depois no banco.
     * 
     * @param idParametro
     * @return
     * @throws ComumException ParametroDDA
     */
    ParametroDDA obter(Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por obter o parametro primeiro no cache, depois no banco.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException ParametroDDA
     */
    ParametroDDA obter(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter a lista de par�metro para o combo de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ParametroDto
     * 
     */
    ParametroDto obterDadosPesquisa() throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por obter a lista de par�metro para o combo de pesquisa.
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ParametroDto
     * 
     */
    ParametroDto obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException;

    /**
     * 
     * M�todo respons�vel por obter lista de tipo de parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> listarTipoParametro() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException List<ParametroDDA>
     * 
     */
    List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param listaSingular
     * @return
     * @throws ComumException List<ValorParametroDDA>
     * 
     */
    List<ValorParametroDDA> listarValorParametro(List<Integer> listaSingular) throws ComumException;

    /**
     * 
     * M�todo respons�vel por obter o valor por institui��o
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    String obterValorParametro(Integer idInstituicao, Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por incluir o par�metro e atualizar as informa��es de valor par�metro.
     * 
     * @param parametro
     * @return
     * @throws ComumException
     */
    ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException;

    /**
     * M�todo respons�vel por alterar o par�metro e atualizar as informa��es de valor par�metro no DB2 e legado.
     * 
     * @param parametro
     * @throws ComumException
     */
    void alterarParametro(ParametroDDA parametro) throws ComumException;

    /**
     * M�todo respons�vel por recuperar lista de par�metros
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException
     */
    void atualizarValorParametro() throws ComumException;

    /**
     * M�todo respons�vel por atualizar a lista de valor parametro de um ou mais unidades
     * 
     * @param parametro
     * @param listaValorParametro
     * @param idInstituicao
     * @param listaNumCooperativa
     * @throws ComumException
     */
    void atualizarValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter valor Boolean do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * 
     * @param idParametro
     * @return Boolean
     * 
     */
    Boolean obterValorBoolean(Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por obter valor Boolean do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return Boolean
     * @throws ComumException
     */
    Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter valor Integer do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @return
     * @throws ComumException Integer
     * 
     */
    Integer obterValorInteger(Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por obter valor Double do campo VALORBASEPARAMETRO de um parametro Global indo primeiro no Cache Central.
     * 
     * @param idParametro
     * @return
     * @throws ComumException Double
     * 
     */
    Double obterValorDouble(Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorParametroBoolean(Long idParametro, Integer idInstituicao) throws ComumException;
}
