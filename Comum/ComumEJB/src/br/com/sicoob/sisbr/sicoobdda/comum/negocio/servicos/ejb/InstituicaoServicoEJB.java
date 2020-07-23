package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheCentral;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.EnderecoInstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.CentralSingularDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.InstituicaoCooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.InstituicaoVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.InstituicaoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * InstituicaoServicoEJB é responsável
 * 
 * @author samuell.ramos
 */
@Stateless
@Local(InstituicaoServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InstituicaoServicoEJB extends ComumCrudServicoEJB<SicoobDDAEntidade> implements InstituicaoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private InstituicaoDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    protected InstituicaoDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaCentrais(java.lang.Integer)
     */
    public List<CentralSingularVo> listaCentrais(Integer idInstituicao) throws ComumException {
        return getDAO().listaCentrais(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaSingulares(java.lang.Integer, java.lang.Integer)
     */
    public List<CentralSingularVo> listaSingulares(Integer idInstituicao, Integer numeroCooperativa) throws ComumException {
        return getDAO().listaSingulares(idInstituicao, numeroCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaUnidades(java.lang.Integer, java.lang.Integer)
     */
    public List<UnidadeVo> listaUnidades(Integer numeroCooperativa, Integer idInstituicao) throws ComumException {
        return getDAO().listaUnidades(numeroCooperativa, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterIdInstituicaoPai(java.lang.Integer)
     */
    public InstituicaoDto obterIdInstituicaoPai(Integer idInstituicao) throws ComumException {
        return getDAO().obterIdInstituicaoPai(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterTipoGrauCooperativo(java.lang.Integer)
     */
    public InstituicaoVo obterTipoGrauCooperativo(Integer idInstituicao) throws ComumException {
        return getDAO().obterTipoGrauCooperativo(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterCooperativaPorInstituicao(java.lang.Integer)
     */
    public Integer obterCooperativaPorInstituicao(Integer idInstituicao) throws ComumException {
        return getDAO().obterCooperativaPorInstituicao(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaCentrais()
     */
    public List<InstituicaoCooperativaDto> listaCentrais() throws ComumException {
        return getDAO().listaCentrais();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listaSingulares(java.lang.Integer)
     */
    public List<InstituicaoCooperativaDto> listaSingulares(Integer idInstituicao) throws ComumException {
        return getDAO().listaSingulares(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicao()
     */
    public List<InstituicaoDto> listarInstituicao() throws ComumException {
        return getDAO().listarInstituicao();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicaoCache()
     */
    @SuppressWarnings("unchecked")
    public List<InstituicaoDto> listarInstituicaoCache() throws ComumException {
        return (List<InstituicaoDto>) CacheCentral.getInstance().getCache(CacheEnum.INSTITUICAO).getObjCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicao(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicao(Integer idInstituicao) throws ComumException {
        getLogger().debug("[DDA] - Obter Instituição: " + idInstituicao);
        List<InstituicaoDto> listaInstituicao = listarInstituicaoCache();

        for (InstituicaoDto instituicao : listaInstituicao) {
            if (instituicao.getIdInstituicao().equals(idInstituicao)) {
                getLogger().debug("[DDA] - Instituição obtido no cache. Id: " + idInstituicao);
                return instituicao;
            }
        }
        getLogger().debug("[DDA] - Parâmetro obtido no banco. Id: " + idInstituicao);
        return getDAO().obterInstituicao(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoView(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicaoView(Integer idInstituicao) throws ComumException {
        getLogger().debug("[DDA] - Parâmetro obtido no banco. Id: " + idInstituicao);
        return getDAO().obterInstituicao(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoPorCooperativa(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicaoPorCooperativa(Integer numeroCooperativa) throws ComumException {
        getLogger().debug("[DDA] - Obter Instituição por Cooperativa: " + numeroCooperativa);
        if (numeroCooperativa == Constantes.ID_BANCOOB) {
            return obterInstituicao(Constantes.ID_BANCOOB);
        }

        List<InstituicaoDto> listaInstituicao = listarInstituicaoCache();

        for (InstituicaoDto instituicao : listaInstituicao) {
            if (instituicao.getNumCooperativa().equals(numeroCooperativa)) {
                getLogger().debug("[DDA] - Instituição obtido no cache. Cooperativa: " + numeroCooperativa);
                return instituicao;
            }
        }
        getLogger().debug("[DDA] - Instituição obtido no banco. Cooperativa: " + numeroCooperativa);
        return getDAO().obterInstituicaoPorCooperativa(numeroCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterInstituicaoPorCooperativaView(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicaoPorCooperativaView(Integer numeroCooperativa) throws ComumException {
        if (numeroCooperativa == Constantes.ID_BANCOOB) {
            return obterInstituicaoView(Constantes.ID_BANCOOB);
        }
        getLogger().debug("[DDA] - Instituição obtido no banco. Cooperativa: " + numeroCooperativa);
        return getDAO().obterInstituicaoPorCooperativa(numeroCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarInstituicao(java.util.List)
     */
    public List<InstituicaoDto> listarInstituicao(List<Integer> listaIdInstituicao) throws ComumException {
        return getDAO().listarInstituicao(listaIdInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#obterEnderecoUnidadeInstituicao(java.lang.Integer, java.lang.Integer)
     */
    public EnderecoInstituicaoDto obterEnderecoUnidadeInstituicao(Integer idInstituicao, Integer idUnidadeInstituicao) throws ComumException {
        return getDAO().obterEnderecoUnidadeInstituicao(idInstituicao, idUnidadeInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarCentralSingularUnidade(java.lang.Integer)
     */
    public CentralSingularDto listarCentralSingularUnidade(Integer idInstituicaoLogada) throws ComumException {
        CentralSingularDto dto = new CentralSingularDto();

        List<CentralSingularVo> listaCentral = listaCentrais(idInstituicaoLogada);
        Integer numCoperativa = listaCentral.isEmpty() ? null : listaCentral.get(0).getNumeroCooperativa();
        List<CentralSingularVo> listaSingular = listaSingulares(idInstituicaoLogada, numCoperativa);
        Integer numCoperativaSingular = listaSingular.isEmpty() ? null : listaSingular.get(0).getNumeroCooperativa();
        List<UnidadeVo> listaUnidade = listaUnidades(numCoperativaSingular, null);
        Integer codGrauInstituicao = obterTipoGrauCooperativo(idInstituicaoLogada).getCodTipoGrauCoop();

        dto.setListaCentral(listaCentral);
        dto.setListaSingular(listaSingular);
        dto.setListaUnidade(listaUnidade);
        dto.setCodGrauInstituicao(codGrauInstituicao);
        return dto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico#listarSingularUnidade(java.lang.Integer, java.lang.Integer)
     */
    public CentralSingularDto listarSingularUnidade(Integer idInstituicaoLogada, Integer numeroCooperativa) throws ComumException {
        CentralSingularDto dto = new CentralSingularDto();
        dto.setListaSingular(listaSingulares(idInstituicaoLogada, numeroCooperativa));
        if (!dto.getListaSingular().isEmpty()) {
            dto.setListaUnidade(listaUnidades(dto.getListaSingular().get(0).getNumeroCooperativa(), null));
        }
        return dto;
    }
}
