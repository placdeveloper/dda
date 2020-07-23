/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         DominioDDAServicoEJB.java
 * Data Criação:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheCentral;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.DominioDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.DominioDDADao;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;

/**
 * DominioDDAServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(DominioDDAServicoLocal.class)
public class DominioDDAServicoEJB extends ComumCrudServicoEJB<SicoobDDAEntidade> implements DominioDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private DominioDDADao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected DominioDDADao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarAutorizacaoDivergente()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AutorizacaoValorDivergente> listarAutorizacaoDivergente() throws ComumException {
        getLogger().debug("[DDA] - Listar Domínio Autorização Divergente");
        List<AutorizacaoValorDivergente> lista = (List<AutorizacaoValorDivergente>) CacheCentral.getInstance().getCache(CacheEnum.DOMINIO_AUTORIZACAO_VALOR_DEVERGENTE)
                .getObjCache();
        if (!ObjectUtil.isEmpty(lista)) {
            getLogger().debug("[DDA] - Domínio obtido no cache - Autorização Divergente");
            return lista;
        }
        return listarAutorizacaoDivergenteBanco();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarAutorizacaoDivergenteBanco()
     */
    @Override
    public List<AutorizacaoValorDivergente> listarAutorizacaoDivergenteBanco() throws ComumException {
        getLogger().debug("[DDA] - Domínio obtido no banco - Autorização Divergente");
        return getDAO().listarAutorizacaoValorDivergente();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarSituacaoBoletoPagamento()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException {
        getLogger().debug("[DDA] - Listar Domínio Situação Boleto Pagamento");
        List<SituacaoBoletoPagamento> lista = (List<SituacaoBoletoPagamento>) CacheCentral.getInstance().getCache(CacheEnum.DOMINIO_SITUACAO_BOLETO_PAGAMENTO).getObjCache();
        if (!ObjectUtil.isEmpty(lista)) {
            getLogger().debug("[DDA] - Domínio obtido no cache - Situação Boleto Pagamento");
            return lista;
        }
        return listarSituacaoBoletoPagamentoBanco();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarSituacaoBoletoPagamentoBanco()
     */
    @Override
    public List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamentoBanco() throws ComumException {
        getLogger().debug("[DDA] - Domínio obtido no banco - Situação Boleto Pagamento");
        return getDAO().listarSituacaoBoletoPagamento();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#obterGradeHorariaReferenciaDDA0110()
     */
    public GradeHoraria obterGradeHorariaReferenciaDDA0110() throws ComumException {
        getLogger().debug("[DDA] - Obtendo Grade Horária Referência DDA0110");
        GradeHoraria gradeReferencia = (GradeHoraria) CacheCentral.getInstance().getCache(CacheEnum.DOMINIO_GRADE_HORARIA_DDA0110).getObjCache();
        if (!ObjectUtil.isNull(gradeReferencia)) {
            getLogger().debug("[DDA] - Grade Horária DDA0110 obtida no cache");
            return gradeReferencia;
        }

        return obterGradeHorariaReferenciaDDA0110Banco();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#obterGradeHorariaReferenciaDDA0110Banco()
     */
    @Override
    public GradeHoraria obterGradeHorariaReferenciaDDA0110Banco() throws ComumException {
        getLogger().debug("[DDA] - Grade Horária DDA0110 obtida no banco");
        return getDAO().obterGradeHorariaReferencia(TipoMensagemDDA.DDA0110);
    }

}
