/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ReplicarBeneficiarioLegadoServicoEJB.java
 * Data Criação:    Aug 28, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ReplicarPagadorEletronicoLegadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao;

/**
 * ReplicarPagadorEletronicoLegadoServicoEJB é responsável por
 * 
 * @author george.santos
 */
@Stateless
@Local({ ReplicarPagadorEletronicoLegadoServicoLocal.class })
public class ReplicarPagadorEletronicoLegadoServicoEJB extends IntegracaoCipServicoEJB implements ReplicarPagadorEletronicoLegadoServicoLocal {

    private static final String ERRO_REPLICAR_CIP_LEGADO = "integracaocip.erro.replicar.cip.legado";

    @PersistenceContext(unitName = "emSicoobDDA_sqlServer")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ReplicarPagadorEletronicoLegadoDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico#replicarPagadorCIPLegado(br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA)
     */
    public void replicarPagadorCIPLegado(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws IntegracaoCipException {
        getLogger().debug("********* replicarPagadorCIPLegado *********");

        try {
            DDAPagadorEletronico ddaPagador = getDao().obterDDAPagadorEletronico(numCpfCnpj);
            if (ObjectUtil.isNull(ddaPagador)) {
                getDao().incluirDDAPagadorEletronico(numCpfCnpj, bolSacadoEletronico, numCooperativa);
            } else {
                ddaPagador.setBolSacadoEletronico(bolSacadoEletronico);
                ddaPagador.setNumCooperativa(numCooperativa);
                getDao().alterarDDAPagadorEletronico(ddaPagador);
            }

        } catch (BancoobException e) {
            tratarException(e, ERRO_REPLICAR_CIP_LEGADO);
        }

        getLogger().debug("********* replicarPagadorCIPLegado *********");

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico#excluirPagadorCipLegador(java.lang.String)
     */
    public void excluirPagadorCipLegador(String numCpfCnpj, Boolean bolSacadoEletronico) throws IntegracaoCipException {
        try {
            DDAPagadorEletronico ddaPagador = getDao().obterDDAPagadorEletronico(numCpfCnpj);
            if (!ObjectUtil.isNull(ddaPagador) && !ObjectUtil.isNull(ddaPagador.getNumCPFCNPJ())) {
                ddaPagador.setBolSacadoEletronico(bolSacadoEletronico);
                ddaPagador.setNumCooperativa(null);
                getDao().alterarDDAPagadorEletronico(ddaPagador);
            }
        } catch (BancoobException e) {
            tratarException(e, ERRO_REPLICAR_CIP_LEGADO);
        }
    }

    /**
     * Método responsável por
     * 
     * @param e
     * @param msg
     * @throws IntegracaoCipException void
     * 
     */
    private void tratarException(BancoobException e, String msg) throws IntegracaoCipException {
        getLogger().erro(e, msg);
        throw new IntegracaoCipException(e);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return ReplicarBeneficiarioLegadoDao
     */
    public ReplicarPagadorEletronicoLegadoDao getDao() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @param dao void
     * 
     */
    public void setReplicarPagadorEletronicoLegadoDao(ReplicarPagadorEletronicoLegadoDao dao) {
        this.dao = dao;
    }
}
