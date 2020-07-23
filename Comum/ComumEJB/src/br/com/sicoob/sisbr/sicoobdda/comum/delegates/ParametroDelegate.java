/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ParametroDelegate.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

/**
 * ParametroDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ParametroDelegate extends ComumCrudDelegate<ParametroDDA, ParametroServico> implements ParametroServico {

    /**
     * Método utilizado pelo CacheEnum
     * 
     * @return ParametroDelegate
     */
    public static ParametroDelegate getInstance() {
        return ComumFabricaDelegates.getInstance().getParametroDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ParametroServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarParametroServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    @Override
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosPesquisa()
     */
    @Override
    public ParametroDto obterDadosPesquisa() throws ComumException, ComumNegocioException {
        return localizarServico().obterDadosPesquisa();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosValorParametroPesquisa()
     */
    @Override
    public ParametroDto obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException {
        return localizarServico().obterDadosValorParametroPesquisa();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarAtivos()
     */
    @Override
    public List<ParametroDDA> listarAtivos() throws ComumException {
        return localizarServico().listarAtivos();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarAtivosCache()
     */
    @Override
    public List<ParametroDDA> listarAtivosCache() throws ComumException {
        return localizarServico().listarAtivosCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obter(java.lang.Long)
     */
    @Override
    public ParametroDDA obter(Long idParametro) throws ComumException {
        return localizarServico().obter(idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obter(java.lang.Long, java.lang.Integer)
     */
    @Override
    public ParametroDDA obter(Long idParametro, Integer idInstituicao) throws ComumException {
        return localizarServico().obter(idParametro, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarTipoParametro()
     */
    @Override
    public List<TipoParametroDDA> listarTipoParametro() throws ComumException {
        return localizarServico().listarTipoParametro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosParametro(java.lang.Long, java.lang.String)
     */
    @Override
    public List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException {
        return localizarServico().obterDadosParametro(idParametro, nomeParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarValorParametro(java.util.List)
     */
    @Override
    public List<ValorParametroDDA> listarValorParametro(List<Integer> listaSingular) throws ComumException {
        return localizarServico().listarValorParametro(listaSingular);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorParametro(java.lang.Integer, java.lang.Long)
     */
    @Override
    public String obterValorParametro(Integer idInstituicao, Long idParametro) throws ComumException {
        return localizarServico().obterValorParametro(idInstituicao, idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#incluirParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA)
     */
    @Override
    public ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException {
        return localizarServico().incluirParametro(parametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#alterarParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA)
     */
    @Override
    public void alterarParametro(ParametroDDA parametro) throws ComumException {
        localizarServico().alterarParametro(parametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#atualizarValorParametro()
     */
    @Override
    public void atualizarValorParametro() throws ComumException {
        localizarServico().atualizarValorParametro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#atualizarValorParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA,
     *      java.util.List, java.lang.Long)
     */
    @Override
    public void atualizarValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao) throws ComumException {
        localizarServico().atualizarValorParametro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorBoolean(java.lang.Long)
     */
    @Override
    public Boolean obterValorBoolean(Long idParametro) throws ComumException {
        return localizarServico().obterValorBoolean(idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorBoolean(java.lang.Long, java.lang.Integer)
     */
    @Override
    public Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException {
        return localizarServico().obterValorBoolean(idParametro, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorInteger(java.lang.Long)
     */
    public Integer obterValorInteger(Long idParametro) throws ComumException {
        return localizarServico().obterValorInteger(idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorDouble(java.lang.Long)
     */
    public Double obterValorDouble(Long idParametro) throws ComumException {
        return localizarServico().obterValorDouble(idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorParametroBoolean(java.lang.Long, java.lang.Integer)
     */
    @Override
    public Boolean obterValorParametroBoolean(Long idParametro, Integer idInstituicao) throws ComumException {
        return localizarServico().obterValorParametroBoolean(idParametro, idInstituicao);
    }

}
