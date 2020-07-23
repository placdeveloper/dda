/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         ServidorDDAServicoEJB.java
 * Data Criação:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheCentral;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.ServidorDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.AmbienteUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDAServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(ServidorDDAServicoLocal.class)
public class ServidorDDAServicoEJB extends ComumCrudServicoEJB<ServidorDDA> implements ServidorDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ServidorDDADao dao;

    @EJB
    private ServidorDDAServicoLocal servidorServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ServidorDDADao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#obterServidor()
     */
    @Override
    public ServidorDDA obterServidor() throws ComumException {
        getLogger().debug("[DDA] - Obter Servidor");
        ServidorDDA servidorCache = obterServidorCache();
        if (!ObjectUtil.isNull(servidorCache)) {
            getLogger().debug("[DDA] - Servidor obtido no cache. Nome: " + servidorCache.getCodServidorDDA());
            return servidorCache;
        }

        String nomeServidor = AmbienteUtil.getNomeServidor();

        ServidorDDA servidor = getDAO().obterServidor(nomeServidor);
        if (!ObjectUtil.isNull(servidor)) {
            getLogger().debug("[DDA] - Servidor obtido no banco. Nome: " + servidor.getCodServidorDDA());
            return servidor;
        }

        getLogger().debug("[DDA] - Servidor ainda não cadastrado. Nome: " + nomeServidor);
        return servidorServico.incluirServidor(nomeServidor);
    }

    /**
     * @return ServidorDDA
     * 
     */
    private ServidorDDA obterServidorCache() {
        CacheDto cacheDto = CacheCentral.getInstance().getCache(CacheEnum.SERVIDOR);
        if (ObjectUtil.isNull(cacheDto)) {
            return null;
        }
        return (ServidorDDA) cacheDto.getObjCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.ServidorDDAServicoLocal#incluirServidor(java.lang.String)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ServidorDDA incluirServidor(String nomeServidor) throws ComumException {
        ServidorDDA servidorDDA = new ServidorDDA(nomeServidor);
        try {
            getLogger().debug("[DDA] - Incluindo servidor.");
            return getDAO().incluir(servidorDDA);
        } catch (BancoobException e) {
            throw new ComumException("servidordda.erro.incluir");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#listarAtivo()
     */
    @Override
    public List<ServidorDDA> listarAtivo() throws ComumException {
        return getDAO().listarAtivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#listarServidorDDA()
     */
    @Override
    public List<ServidorDDA> listarServidorDDA() throws ComumException {
        return getDAO().listarServidorDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#pesquisarServidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public List<ServidorDDA> pesquisarServidorDDA(ServidorDDA filtro) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isNull(filtro)) {
            throw new ComumNegocioException("servidordda.filtro.nao.informado");
        }
        return getDAO().listarServidorDDA(filtro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#alterarServidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public void alterarServidorDDA(ServidorDDA servidorDDA) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isNull(servidorDDA)) {
            throw new ComumNegocioException("servidordda.dados.invalidos");
        }

        getDAO().alterarSevidorDDA(servidorDDA);
    }

}
