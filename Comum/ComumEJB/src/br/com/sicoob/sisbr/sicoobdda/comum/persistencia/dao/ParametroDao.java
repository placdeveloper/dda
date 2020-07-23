/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ParametroDao.java
 * Data Cria��o:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

/**
 * ParametroDao � respons�vel por
 * 
 * @author felipe.rosa
 */
public interface ParametroDao extends ComumCrudDaoIF<ParametroDDA> {

    /**
     * M�todo respons�vel por obter o valor do par�metro da institui��o informada.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException ValorParametro
     * 
     */
    ParametroDDA obterParametro(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valor" do ValorPar�metro da institui��o informada.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException String
     * 
     */
    String obterValor(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valorBaseParametro" do parametro da institui��o informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valorBaseParametro" do parametro da institui��o informada bloqueando o registro (SELECT FOR UPDATE)
     * selecionado ate finalizar a transa��o.
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean obterValorBooleanLockRegistro(Long idParametro, Integer idInstituicao, Boolean parametroGlobal) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valorBaseParametro" do parametro da institui��o informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valorBaseParametro" do parametro da institui��o informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    Double obterValorDouble(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por obter apenas o campo "valorBaseParametro" do parametro da institui��o informada
     * 
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException Short
     * 
     */
    Short obterValorShort(Long idParametro, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por atualizar o valor do parametro global ou por institui��o conforme o tipo do parametro cadastrado.
     * 
     * @param idParametro
     * @param idInstituicao
     * @throws ComumException void
     * 
     */
    void atualizarValorParametro(Long idParametro, Integer idInstituicao, String valorParametro) throws ComumException;

    /**
     * M�todo respons�vel por listar os par�metros
     */
    List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException;

    /**
     * 
     * M�todo respons�vel por obter lista de tipo parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> obterListaTipoParametro() throws ComumException;

    /**
     * M�todo respons�vel por atualizar o valor parametro por institui��o
     * 
     * @throws ComumException
     */
    void atualizaValorParametro() throws ComumException;

    /**
     * M�todo respons�vel por obter lista de Institui��es conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    List<ValorParametroDDA> obterListaInstituicoes(List<Integer> listaSingular) throws ComumException;

    /**
     * 
     * M�todo respons�vel por atualizar lista de valor parametro
     * 
     * @param parametro
     * @param listaInstituicao
     * @throws ComumException void
     * 
     */
    void atualizarValorParametro(ParametroDDA parametro, List<Long> listaInstituicao) throws ComumException;

    /**
     * 
     * M�todo respons�vel por obter o valor parametro por institui��o
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException;

    /**
     * M�todo respons�vel por recuperar lista de par�metros
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
     *         M�todo respons�vel por retornar apenas os par�metros necess�rios em uma �nica consulta
     * 
     * @param idsParametros um array com os ids desejados
     * @return uma lista com os objetos que representam os valores dos par�metros
     * @throws ComumException
     */
    List<ParametroDDA> listarParametros(long... idsParametros);

    /**
     * M�todo respons�vel por listar todos os parametros globais que est�o ativos.
     * 
     * @return List<ParametroDDA>
     */
    List<ParametroDDA> listarAtivos() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idParametros
     * @return
     * @throws ComumException Map<Long,Parametro>
     * 
     */
    Map<Long, ParametroDDA> obterMapaParametro(long... idParametros) throws ComumException;

}
