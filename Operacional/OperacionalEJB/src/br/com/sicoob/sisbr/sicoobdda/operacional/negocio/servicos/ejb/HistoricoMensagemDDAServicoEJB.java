package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.HistoricoMensagemDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.HistoricoMensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * HistoricoMensagemDDAServicoEJB é responsável por
 * 
 * @author George.santos
 */
@Stateless
@Local({ HistoricoMensagemDDAServicoLocal.class })
public class HistoricoMensagemDDAServicoEJB extends OperacionalServicoEJB implements HistoricoMensagemDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private HistoricoMensagemDDADao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void arquivarMensagensDDA() throws ComumException {
        Long qtdRegistroBlocoExpurgo = Long.valueOf(parametroDAO.obterValor(ParametroDDA.QTD_REGISTROS_BLOCO_EXPURGO, Constantes.ID_SICOOB));
        Long qtdDiasLimiteExpurgo = Long.valueOf(parametroDAO.obterValor(ParametroDDA.QTD_DIAS_LIMITE_EXPURGO_MENSAGENS, Constantes.ID_SICOOB));

        dao.arquivarMensagensDDA(qtdRegistroBlocoExpurgo, qtdDiasLimiteExpurgo);

    }

}
