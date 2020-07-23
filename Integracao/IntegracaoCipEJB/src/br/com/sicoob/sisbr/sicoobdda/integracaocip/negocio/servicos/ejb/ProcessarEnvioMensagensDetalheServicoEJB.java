/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ProcessarEnvioMensagensServicoEJB.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarEnvioMensagensDetalheServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ProcessarEnvioMensagensServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ProcessarEnvioMensagensDetalheServicoLocal.class })
public class ProcessarEnvioMensagensDetalheServicoEJB extends IntegracaoCipServicoEJB implements ProcessarEnvioMensagensDetalheServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensDetalheServico#processarEnvioMensagens(String, Long)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarEnvioMensagens(String codTipoMensagemDDA, Long idMensagemDDA) throws ComumException {
        String xmlMsg = getDelegate(codTipoMensagemDDA).processarMensagem(idMensagemDDA);
        postarMensagemFilaSSPBDDA(xmlMsg);
        dao.atualizarMensagemEnviada(idMensagemDDA, xmlMsg);
    }

    /**
     * Método responsável por recuperar o Delegate (polimorficamente) da mensagem a ser processada.
     * 
     * @param codTipoMensagem
     * @return IntegracaoCipMensagemDelegate<IntegracaoCipServico>
     * @throws ComumException
     * 
     */
    private IntegracaoCipMensagemDelegate<IntegracaoCipServico> getDelegate(String codTipoMensagem) throws ComumException {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemDelegate(codTipoMensagem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

}
