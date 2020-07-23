/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         AdministrativoServicoEJB.java
 * Data Criação:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.AdministrativoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AdministrativoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * AdministrativoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@SuppressWarnings("rawtypes")
@Local({ AdministrativoServicoLocal.class })
public class AdministrativoServicoEJB extends OperacionalCrudServicoEJB implements AdministrativoServicoLocal {

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private AdministrativoDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected AdministrativoDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AdministrativoServico#atualizarSituacaoBoletos()
     */
    public void atualizarSituacaoBoletos() throws ComumException {
        getDAO().atualizarSituacaoBoletos();
    }

}
