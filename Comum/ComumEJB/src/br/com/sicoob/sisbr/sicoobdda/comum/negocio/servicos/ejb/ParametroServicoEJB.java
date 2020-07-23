/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         ParametroServicoEJB.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.RequisicaoCacheDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheCentral;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * ParametroServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(ParametroServicoLocal.class)
public class ParametroServicoEJB extends ComumCrudServicoEJB<ParametroDDA> implements ParametroServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ParametroDao getDAO() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @return RequisicaoCacheDelegate
     * 
     */
    private RequisicaoCacheDelegate getRequisicaoCacheDelegate() {
        return ComumFabricaDelegates.getInstance().getRequisicaoCacheDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarAtivos()
     */
    @Override
    public List<ParametroDDA> listarAtivos() throws ComumException {
        return getDAO().listarAtivos();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarAtivosCache()
     */
    @SuppressWarnings("unchecked")
    public List<ParametroDDA> listarAtivosCache() throws ComumException {
        return (List<ParametroDDA>) CacheCentral.getInstance().getCache(CacheEnum.PARAMETRO).getObjCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obter(java.lang.Long)
     */
    public ParametroDDA obter(Long idParametro) throws ComumException {
        return obter(idParametro, Constantes.ID_BANCOOB);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obter(java.lang.Long, java.lang.Integer)
     */
    public ParametroDDA obter(Long idParametro, Integer idInstituicao) throws ComumException {
        getLogger().debug("[DDA] - Obter Parâmetro: " + idParametro);
        List<ParametroDDA> listaParametro = listarAtivosCache();

        for (ParametroDDA parametroDDA : listaParametro) {
            if (parametroDDA.getId().equals(idParametro)) {
                getLogger().debug("[DDA] - Parâmetro obtido no cache. Id: " + idParametro);
                return parametroDDA;
            }
        }
        getLogger().debug("[DDA] - Parâmetro obtido no banco. Id: " + idParametro);
        return getDAO().obterParametro(idParametro, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosPesquisa(boolean, boolean)
     */
    public ParametroDto obterDadosPesquisa() throws ComumException, ComumNegocioException {
        return obterDados(Boolean.FALSE, Boolean.FALSE, getIdInstituicaoLogado());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosValorParametroPesquisa()
     */
    public ParametroDto obterDadosValorParametroPesquisa() throws ComumException, ComumNegocioException {
        int idInstituicao = getIdInstituicaoLogado();
        boolean somenteVisiveis = isSomenteVisiveis(idInstituicao);

        return obterDados(somenteVisiveis, Boolean.TRUE, idInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param somenteVisiveis
     * @param somenteNaoGlobal
     * @param idInstituicao
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ParametroDto
     * 
     */
    private ParametroDto obterDados(boolean somenteVisiveis, boolean somenteNaoGlobal, int idInstituicao) throws ComumException, ComumNegocioException {
        return new ParametroDto(getDAO().listarParametros(somenteVisiveis, somenteNaoGlobal), permiteAlterarTodos(idInstituicao), idInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException boolean
     * 
     */
    private boolean permiteAlterarTodos(int idInstituicao) throws ComumNegocioException {
        if (idInstituicao == Constantes.ID_BANCOOB) {
            return Boolean.FALSE;
        } else if (idInstituicao == Constantes.ID_SICOOB) {
            return Boolean.TRUE;
        } else {
            throw new ComumNegocioException("parametro.instituicao.funcionalidade.nao.permitida");
        }
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @return boolean
     * 
     */
    private boolean isSomenteVisiveis(int idInstituicao) {
        return idInstituicao != Constantes.ID_BANCOOB && idInstituicao != Constantes.ID_SICOOB;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarTipoParametro()
     */
    public List<TipoParametroDDA> listarTipoParametro() throws ComumException {
        return getDAO().obterListaTipoParametro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterDadosParametro(java.lang.Long, java.lang.String)
     */
    public List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException {
        return getDAO().obterDadosParametro(idParametro, nomeParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#listarValorParametro(java.util.List)
     */
    public List<ValorParametroDDA> listarValorParametro(List<Integer> listaSingular) throws ComumException {
        return getDAO().obterListaInstituicoes(listaSingular);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorParametro(java.lang.Long, java.lang.Long)
     */
    public String obterValorParametro(Integer idInstituicao, Long idParametro) throws ComumException {
        return getDAO().obterValorParametroInstituicao(idInstituicao, idParametro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#incluirParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA)
     */
    public ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException {
        try {
            parametro.setDataCriacao(new DateTimeDB());
            parametro.setDataHoraUltimaAtualizacao(new DateTimeDB());

            getLogger().debug("##### Incluíndo o parâmetro");
            parametro = super.incluir(parametro);
            getLogger().debug("##### Incluído o parâmetro com sucesso");

            getRequisicaoCacheDelegate().incluir(CacheEnum.PARAMETRO);

            return parametro;
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#alterarParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA)
     */
    public void alterarParametro(ParametroDDA parametro) throws ComumException {
        try {
            getLogger().debug("##### Alterando o parâmetro");
            super.alterar(parametro);
            getLogger().debug("##### Alterado o parâmetro com sucesso");

            getRequisicaoCacheDelegate().incluir(CacheEnum.PARAMETRO);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#atualizarValorParametro()
     */
    public void atualizarValorParametro() throws ComumException {
        getDAO().atualizaValorParametro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#atualizarValorParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA,
     *      java.util.List, java.lang.Long)
     */
    public void atualizarValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao) throws ComumException {
        List<Long> listaInstituicao = new ArrayList<>();

        for (ValorParametroDDA valorParametro : listaValorParametro) {
            listaInstituicao.add(valorParametro.getIdInstituicao());
        }
        // Adicionando a instituição selecionada na lista
        listaInstituicao.add(idInstituicao);

        getDAO().atualizarValorParametro(parametro, listaInstituicao);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorBoolean(java.lang.Long)
     */
    @Override
    public Boolean obterValorBoolean(Long idParametro) throws ComumException {
        ParametroDDA parametro = obter(idParametro);
        return !ObjectUtil.isNull(parametro) && parametro.getValorParametro().equals(Constantes.STRING_NUMERO_1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorBoolean(java.lang.Long, java.lang.Integer)
     */
    @Override
    public Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException {
        ParametroDDA parametro = obter(idParametro, idInstituicao);
        return !ObjectUtil.isNull(parametro) && parametro.getValorParametro().equals(Constantes.STRING_NUMERO_1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorInteger(java.lang.Long)
     */
    public Integer obterValorInteger(Long idParametro) throws ComumException {
        ParametroDDA parametro = obter(idParametro);
        if (ObjectUtil.isNull(parametro)) {
            return null;
        }
        return Integer.valueOf(parametro.getValorParametro());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorDouble(java.lang.Long)
     */
    public Double obterValorDouble(Long idParametro) throws ComumException {
        ParametroDDA parametro = obter(idParametro);
        if (ObjectUtil.isNull(parametro)) {
            return null;
        }
        return Double.valueOf(parametro.getValorParametro());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico#obterValorParametroBoolean(java.lang.Long, java.lang.Integer)
     */
    public Boolean obterValorParametroBoolean(Long idParametro, Integer idInstituicao) throws ComumException {
        return obterValorParametro(idInstituicao, idParametro).equals(Constantes.STRING_NUMERO_1);
    }
}
