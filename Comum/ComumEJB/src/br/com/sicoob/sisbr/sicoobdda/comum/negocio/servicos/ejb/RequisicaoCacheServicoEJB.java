/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         RequisicaoCacheServicoEJB.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CacheNotificaDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ServidorDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.RequisicaoCacheServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.RequisicaoCacheDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(RequisicaoCacheServicoLocal.class)
public class RequisicaoCacheServicoEJB extends ComumCrudServicoEJB<RequisicaoCache> implements RequisicaoCacheServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private RequisicaoCacheDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected RequisicaoCacheDao getDAO() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @return CacheNotificaDelegate
     * 
     */
    private CacheNotificaDelegate getCacheNotificaDelegate() {
        return ComumFabricaDelegates.getInstance().getCacheNotificaDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return ServidorDDADelegate
     * 
     */
    private ServidorDDADelegate getServidorDDADelegate() {
        return ComumFabricaDelegates.getInstance().getServidorDDADelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum)
     */
    @Override
    public void incluir(CacheEnum cacheEnum) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isNull(cacheEnum)) {
            throw new ComumNegocioException("requisicaocache.cache.nao.informado");
        }

        incluir(new RequisicaoCache(cacheEnum.toString()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico#incluirRequisicao(br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache)
     */
    @Override
    public RequisicaoCache incluir(RequisicaoCache requisicaoCache) throws ComumException, ComumNegocioException {
        validarRequisicao(requisicaoCache);

        requisicaoCache.setServidorDDARequisitante(getServidorDDADelegate().obterServidor());
        try {
            getLogger().debug("[DDA] - Incluindo requisição para atualização de cache. Cache: " + requisicaoCache.getDescCache());
            dao.incluir(requisicaoCache);
        } catch (BancoobException e) {
            throw new ComumException("requisicaocache.erro.inclusao", new Object[] { requisicaoCache.getDescCache() }, e);
        }

        getCacheNotificaDelegate().notificarCache(requisicaoCache);

        return requisicaoCache;
    }

    /**
     * Método responsável por
     * 
     * @param requisicaoCache
     * @throws ComumNegocioException void
     * 
     */
    private void validarRequisicao(RequisicaoCache requisicaoCache) throws ComumNegocioException {
        if (ObjectUtil.isNull(requisicaoCache)) {
            throw new ComumNegocioException("requisicaocache.nao.informada");
        } else if (ObjectUtil.isNull(requisicaoCache.getDataHoraRequisicao())) {
            throw new ComumNegocioException("requisicaocache.data.nao.informada");
        } else if (ObjectUtil.isNull(requisicaoCache.getDescCache())) {
            throw new ComumNegocioException("requisicaocache.cache.nao.informado");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico#pesquisarRequisicao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto)
     */
    @Override
    public List<RequisicaoCache> pesquisarRequisicao(FiltroRequisicaoCacheDto filtroDto) throws ComumException, ComumNegocioException {
        validarFiltro(filtroDto);
        return getDAO().listar(filtroDto);
    }

    /**
     * Método responsável por
     * 
     * @param filtroDto void
     * 
     */
    private void validarFiltro(FiltroRequisicaoCacheDto filtroDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(filtroDto)) {
            throw new ComumNegocioException("requisicaocache.filtro.nao.informado");
        } else if (ObjectUtil.isNull(filtroDto.getDataRequisicao())) {
            throw new ComumNegocioException("requisicaocache.data.nao.informada");
        }
    }

}
