package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.AtualizaParametroServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoIF;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;

/**
 * @author Daniel.Moraes
 * 
 */
@Stateless
@Local(AtualizaParametroServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Deprecated
public class AtualizaParametroServicoEJB extends ComumCrudServicoEJB<ParametroDDA> implements AtualizaParametroServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao dao;

    @EJB
    AtualizaParametroServicoLocal ejb;

    public List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException {
        return getParametroDAO().listarParametros(somenteVisiveis, somenteNaoGlobal);
    }

    public List<TipoParametroDDA> obterListaTipoParametro() throws ComumException {
        return getParametroDAO().obterListaTipoParametro();
    }

    public List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException {
        return getParametroDAO().obterDadosParametro(idParametro, nomeParametro);
    }

    public ParametroDDA incluirParametro(ParametroDDA parametro) throws ComumException {
        try {
            ParametroDDA retorno = ejb.incluirParametroNovaTransacao(parametro);

            // Atualiza os valores de parâmetro
            getParametroDAO().atualizaValorParametro();

            return retorno;
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    public void alterarParametro(ParametroDDA parametro) throws ComumException {
        try {
            ejb.alterarParametroNovaTransacao(parametro);

            // Atualiza os valores de parâmetro
            getParametroDAO().atualizaValorParametro();

            // Não será feita atualização dos parâmetros no legado
            // if (parametro.getBolParametroGlobal() && !ObjectUtil.isEmpty(parametro.getIdParametroLegado())) {
            // atualizarParametroLegado(parametro, getParametroDAO().listarNumCooperativa());
            // }
        } catch (BancoobException e) {
            throw new ComumException(e);
        }

    }

    public List<ValorParametroDDA> obterListaInstituicao(List<Integer> listaSingular) throws ComumException {
        return getParametroDAO().obterListaInstituicoes(listaSingular);
    }

    public void atualizarListaValorParametro(ParametroDDA parametro, List<ValorParametroDDA> listaValorParametro, Long idInstituicao, List<Integer> listaNumCooperativa)
            throws ComumException {
        List<Long> listaInstituicao = new ArrayList<Long>();

        for (ValorParametroDDA valorParametro : listaValorParametro) {
            listaInstituicao.add(Long.valueOf(valorParametro.getIdInstituicao()));
        }
        // Adicionando a instituição selecionada na lista
        listaInstituicao.add(idInstituicao);

        getParametroDAO().atualizarValorParametro(parametro, listaInstituicao);

        // Não será feita atualização dos parâmetros no legado
        // atualizarParametroLegado(parametro, listaNumCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico#obterValorParametroInstituicao(java.lang.Integer, java.lang.Long)
     */
    public String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException {
        return getParametroDAO().obterValorParametroInstituicao(idInstituicao, idParametro);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void alterarParametroNovaTransacao(ParametroDDA parametro) throws ComumException {
        try {
            getLogger().debug("##### Alterando o parâmetro");
            super.alterar(parametro);
            getLogger().debug("##### Alterado o parâmetro com sucesso");
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ParametroDDA incluirParametroNovaTransacao(ParametroDDA parametro) throws ComumException {
        try {
            getLogger().debug("##### Incluíndo o parâmetro");
            parametro = super.incluir(parametro);
            getLogger().debug("##### Incluído o parâmetro com sucesso");
            return parametro;
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico#atualizarParametroInstituicao()
     */
    public void atualizarParametroInstituicao() throws ComumException {
        getParametroDAO().atualizaValorParametro();
    }

    protected ParametroDao getParametroDAO() {
        return this.dao;
    }

    public void setParametroDAO(ParametroDao dao) {
        this.dao = dao;
    }

    @Override
    protected ComumCrudDaoIF<ParametroDDA> getDAO() {
        return dao;
    }

}
