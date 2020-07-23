/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         InstituicaoDelegate.java
 * Data Criação:    Oct 1, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.EnderecoInstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.CentralSingularDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.InstituicaoCooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.InstituicaoVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;

/**
 * InstituicaoDelegate é responsável por
 * 
 * @author samuell.ramos
 */
public class InstituicaoDelegate extends ComumDelegate<InstituicaoServico> implements InstituicaoServico {

    /**
     * Método utilizado pelo CacheEnum
     * 
     * @return InstituicaoDelegate
     */
    public static InstituicaoDelegate getInstance() {
        return ComumFabricaDelegates.getInstance().getInstituicaoDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected InstituicaoServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarInstituicaoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaCentrais(java.lang.Integer)
     */
    public List<CentralSingularVo> listaCentrais(Integer idInstituicao) throws ComumException {
        return localizarServico().listaCentrais(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaSingulares(java.lang.Integer, java.lang.Integer)
     */
    public List<CentralSingularVo> listaSingulares(Integer idInstituicao, Integer numCooperativa) throws ComumException {
        return localizarServico().listaSingulares(idInstituicao, numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaUnidades(java.lang.Integer, java.lang.Integer)
     */
    public List<UnidadeVo> listaUnidades(Integer numCooperativa, Integer idInstituicao) throws ComumException {
        return localizarServico().listaUnidades(numCooperativa, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterIdInstituicaoPai(java.lang.Integer)
     */
    public InstituicaoDto obterIdInstituicaoPai(Integer idInstituicao) throws ComumException {
        return localizarServico().obterIdInstituicaoPai(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterTipoGrauCooperativo(java.lang.Integer)
     */
    public InstituicaoVo obterTipoGrauCooperativo(Integer idInstituicao) throws ComumException {
        return localizarServico().obterTipoGrauCooperativo(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterCooperativaPorInstituicao(java.lang.Integer)
     */
    public Integer obterCooperativaPorInstituicao(Integer idInstituicao) throws ComumException {
        return localizarServico().obterCooperativaPorInstituicao(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaSingulares(java.lang.Integer)
     */
    public List<InstituicaoCooperativaDto> listaSingulares(Integer idInstituicao) throws ComumException {
        return localizarServico().listaSingulares(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaCentrais()
     */
    public List<InstituicaoCooperativaDto> listaCentrais() throws ComumException {
        return localizarServico().listaCentrais();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicao()
     */
    public List<InstituicaoDto> listarInstituicao() throws ComumException {
        return localizarServico().listarInstituicao();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicao(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicao(Integer idInstituicao) throws ComumException {
        return localizarServico().obterInstituicao(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoPorCooperativa(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicaoPorCooperativa(Integer numeroCooperativa) throws ComumException {
        return localizarServico().obterInstituicaoPorCooperativa(numeroCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicao(java.util.List)
     */
    public List<InstituicaoDto> listarInstituicao(List<Integer> listaIdInstituicao) throws ComumException {
        return localizarServico().listarInstituicao(listaIdInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterEnderecoUnidadeInstituicao(java.lang.Integer, java.lang.Integer)
     */
    public EnderecoInstituicaoDto obterEnderecoUnidadeInstituicao(Integer idInstituicao, Integer idUnidadeInstituicao) throws ComumException {
        return localizarServico().obterEnderecoUnidadeInstituicao(idInstituicao, idUnidadeInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarCentralSingularUnidade(java.lang.Integer)
     */
    public CentralSingularDto listarCentralSingularUnidade(Integer idInstituicaoLogada) throws ComumException {
        return localizarServico().listarCentralSingularUnidade(idInstituicaoLogada);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarSingularUnidade(java.lang.Integer, java.lang.Integer)
     */
    public CentralSingularDto listarSingularUnidade(Integer idInstituicaoLogada, Integer numeroCooperativa) throws ComumException {
        return localizarServico().listarSingularUnidade(idInstituicaoLogada, numeroCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicaoCache()
     */
    @Override
    public List<InstituicaoDto> listarInstituicaoCache() throws ComumException {
        return localizarServico().listarInstituicaoCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoView(java.lang.Integer)
     */
    @Override
    @Deprecated
    public InstituicaoDto obterInstituicaoView(Integer idInstituicao) throws ComumException {
        return localizarServico().obterInstituicaoView(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoPorCooperativaView(java.lang.Integer)
     */
    @Override
    @Deprecated
    public InstituicaoDto obterInstituicaoPorCooperativaView(Integer numeroCooperativa) throws ComumException {
        return localizarServico().obterInstituicaoPorCooperativaView(numeroCooperativa);
    }
}
