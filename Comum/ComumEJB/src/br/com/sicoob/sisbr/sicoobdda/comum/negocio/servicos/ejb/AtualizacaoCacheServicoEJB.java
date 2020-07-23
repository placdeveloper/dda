/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         AtualizacaoCacheServicoEJB.java
 * Data Cria��o:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ServidorDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheLoad;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.AtualizacaoCacheServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.AtualizacaoCacheDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * AtualizacaoCacheServicoEJB � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(AtualizacaoCacheServicoLocal.class)
public class AtualizacaoCacheServicoEJB extends ComumCrudServicoEJB<AtualizacaoCache> implements AtualizacaoCacheServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private AtualizacaoCacheDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected AtualizacaoCacheDao getDAO() {
        return dao;
    }

    /**
     * M�todo respons�vel por
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico#processarRequisicao(br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarRequisicao(RequisicaoCache requisicaoCache) throws ComumException, ComumNegocioException {
        getLogger().debug("[DDA] - Processar requisi��o de atualiza��o. Cache: " + requisicaoCache.getDescCache());
        validaRequisicao(requisicaoCache);

        String servidorLocal = validarDestinoRequisicao(requisicaoCache.getServidorDDADestino());
        if (ObjectUtil.isEmpty(servidorLocal)) {
            CacheEnum cacheEnum = CacheEnum.valueOf(requisicaoCache.getDescCache());
            CacheLoad.getInstance().carregarCache(cacheEnum);

            incluirAtualizacaoCache(requisicaoCache, cacheEnum);
        } else {
            debug("[DDA] - Requisi��o " + requisicaoCache.getIdRequisicaoCache() + " ignorada pelo servidor: " + servidorLocal);
        }

    }

    /**
     * M�todo respons�vel por validar se h� servidor de destino para a Requisi��o. Se houver valida se o servidor local � o mesmo do destino. Se for mesmo
     * retorna null, caso contr�rio retorna o nome do servidor local.
     * 
     * @param servidorDDADestino
     * @return
     * @throws ComumException String
     * 
     */
    private String validarDestinoRequisicao(ServidorDDA servidorDDADestino) throws ComumException {
        if (ObjectUtil.isNull(servidorDDADestino)) {
            return null;
        }
        ServidorDDA servidorLocal = getServidorDDADelegate().obterServidor();
        return servidorLocal.equals(servidorDDADestino) ? null : servidorLocal.getCodServidorDDA();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param requisicaoCache
     * @param cacheEnum
     * @throws ComumException void
     * 
     */
    private void incluirAtualizacaoCache(RequisicaoCache requisicaoCache, CacheEnum cacheEnum) throws ComumException {
        AtualizacaoCache atualizacaoCache = new AtualizacaoCache(requisicaoCache, getServidorDDADelegate().obterServidor());
        try {
            getLogger().debug(
                    "[DDA] - Registrando atualiza��o de cache. Cache: " + requisicaoCache.getDescCache() + " - Servidor: " + atualizacaoCache.getServidorDDA().getCodServidorDDA());
            getDAO().incluir(atualizacaoCache);
        } catch (BancoobException e) {
            throw new ComumException("atualizacaocache.erro.inclusao", new Object[] { cacheEnum.toString() }, e);
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param requisicaoCahce void
     * 
     */
    private void validaRequisicao(RequisicaoCache requisicaoCache) throws ComumNegocioException {
        if (ObjectUtil.isNull(requisicaoCache)) {
            throw new ComumNegocioException("atualizacaocache.requisicao.invalida");
        } else if (ObjectUtil.isEmpty(requisicaoCache.getDescCache())) {
            throw new ComumNegocioException("atualizacaocache.cache.nao.informado");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico#listar(java.lang.Long)
     */
    @Override
    public List<AtualizacaoCache> listar(Long idRequisicaoCache) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isEmpty(idRequisicaoCache)) {
            throw new ComumNegocioException("atualizacaocache.requisicao.nao.informada");
        }
        return getDAO().listar(idRequisicaoCache);
    }

}
