package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

public interface AtualizaParametroServico extends ComumCrudServico<ParametroDDA> {

    /**
     * M�todo respons�vel por obter a lista de par�metro.
     * 
     * @param somenteVisiveis
     * @param somenteNaoGlobal
     * @return
     * @throws ComumException
     */
    List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException;

    /**
     * 
     * M�todo respons�vel por obter lista de tipo de parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> obterListaTipoParametro() throws ComumException;

    /**
     * M�todo respons�vel por obter lista de Institui��es conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    List<ValorParametroDDA> obterListaInstituicao(List<Integer> listaSingular) throws ComumException;

    /**
     * M�todo respons�vel por atualizar a lista de valor parametro de um ou mais unidades
     * 
     * @param parametro
     * @param listaValorParametro
     * @param idInstituicao
     * @param listaNumCooperativa
     * @throws ComumException
     */
    void atualizarListaValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao, List<Integer> listaNumCooperativa)
            throws ComumException;

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
    String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException;

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
    List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException;

    /**
     * 
     * M�todo respons�vel por
     * 
     * @throws ComumException
     */
    void atualizarParametroInstituicao() throws ComumException;

}
