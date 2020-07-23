package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

public interface AtualizaParametroServico extends ComumCrudServico<ParametroDDA> {

    /**
     * Método responsável por obter a lista de parâmetro.
     * 
     * @param somenteVisiveis
     * @param somenteNaoGlobal
     * @return
     * @throws ComumException
     */
    List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException;

    /**
     * 
     * Método responsável por obter lista de tipo de parametro
     * 
     * @return
     * @throws ComumException List<TipoParametro>
     * 
     */
    List<TipoParametroDDA> obterListaTipoParametro() throws ComumException;

    /**
     * Método responsável por obter lista de Instituições conforme a lista informada.
     * 
     * @param listaSingular
     * @return
     * @throws ComumException
     */
    List<ValorParametroDDA> obterListaInstituicao(List<Integer> listaSingular) throws ComumException;

    /**
     * Método responsável por atualizar a lista de valor parametro de um ou mais unidades
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
     * Método responsável por obter o valor por instituição
     * 
     * @param idInstituicao
     * @param idParametro
     * @return
     * @throws ComumException String
     * 
     */
    String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException;

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
    List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException;

    /**
     * 
     * Método responsável por
     * 
     * @throws ComumException
     */
    void atualizarParametroInstituicao() throws ComumException;

}
