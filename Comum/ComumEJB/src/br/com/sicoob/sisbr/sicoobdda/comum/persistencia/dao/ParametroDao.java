/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ParametroDao.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

/**
 * ParametroDao é responsável por
 * 
 * @author felipe.rosa
 */
public interface ParametroDao extends ComumCrudDaoIF<ParametroDDA> {

    /**
     * Método responsável por obter o valor do parâmetro da instituição informada.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException ValorParametro
     * 
     */
    ParametroDDA obterParametro(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valor" do ValorParâmetro da instituição informada.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException String
     * 
     */
    String obterValor(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valorBaseParametro" do parametro da instituição informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valorBaseParametro" do parametro da instituição informada bloqueando o registro (SELECT FOR UPDATE)
     * selecionado ate finalizar a transação.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorBooleanLockRegistro(Long idParametro, Integer idInstituicao, Boolean parametroGlobal) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valorBaseParametro" do parametro da instituição informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valorBaseParametro" do parametro da instituição informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    Double obterValorDouble(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por obter apenas o campo "valorBaseParametro" do parametro da instituição informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Short
     * 
     */
    Short obterValorShort(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por atualizar o valor do parametro global ou por instituição conforme o tipo do parametro cadastrado.
     * 
     * @param idParametro
     * @param idInstituicao
     * @throws ComumException void
     * 
     */
    void atualizarValorParametro(Long idParametro, Integer idInstituicao, String valorParametro) throws ComumException;

    /**
     * Método responsável por listar os parâmetros
     */
    List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException;

    /**
     * 
     * Método responsável por obter lista de tipo parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> obterListaTipoParametro() throws ComumException;

    /**
     * Método responsável por atualizar o valor parametro por instituição
     * 
     * @throws ComumException
     */
    void atualizaValorParametro() throws ComumException;

    /**
     * Método responsável por obter lista de Instituições conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    List<ValorParametroDDA> obterListaInstituicoes(List<Integer> listaSingular) throws ComumException;

    /**
     * 
     * Método responsável por atualizar lista de valor parametro
     * 
     * @param parametro
     * @param listaInstituicao
     * @throws ComumException void
     * 
     */
    void atualizarValorParametro(ParametroDDA parametro, List<Long> listaInstituicao) throws ComumException;

    /**
     * 
     * Método responsável por obter o valor parametro por instituição
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException;

    /**
     * Método responsável por recuperar lista de parâmetros
     * 
     * @param idParametro
     * @param nomeParametro
     * @return
     * @throws ComumException
     */
    List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException;

    /**
     * @author Adriano.Pinheiro
     * 
     *         Método responsável por retornar apenas os parâmetros necessários em uma única consulta
     * 
     * @param idsParametros um array com os ids desejados
     * @return uma lista com os objetos que representam os valores dos parâmetros
     * @throws ComumException
     */
    List<ParametroDDA> listarParametros(long... idsParametros);

    /**
     * Método responsável por listar todos os parametros globais que estão ativos.
     * 
     * @return List<ParametroDDA>
     */
    List<ParametroDDA> listarAtivos() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idParametros
     * @return
     * @throws ComumException Map<Long,Parametro>
     * 
     */
    Map<Long, ParametroDDA> obterMapaParametro(long... idParametros) throws ComumException;

}
