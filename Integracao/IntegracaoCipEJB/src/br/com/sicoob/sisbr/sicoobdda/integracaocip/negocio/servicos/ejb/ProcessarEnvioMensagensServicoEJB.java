/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ProcessarEnvioMensagensServicoEJB.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarEnvioMensagensServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ProcessarEnvioMensagensServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ProcessarEnvioMensagensServicoLocal.class })
public class ProcessarEnvioMensagensServicoEJB extends IntegracaoCipServicoEJB implements ProcessarEnvioMensagensServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#processarEnvioMensagensPrioritarias(int)
     */
    public void processarEnvioMensagensPrioritarias(int numAgrupamentoSteps) throws ComumException {
        processarEnvioMensagens(dao.mapMensagensEnvioCip(Boolean.TRUE, numAgrupamentoSteps));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#processarEnvioMensagensNaoPrioritarias(java.lang.Long, java.lang.Long)
     */
    public void processarEnvioMensagensNaoPrioritarias(int numAgrupamentoSteps) throws ComumException {
        processarEnvioMensagens(dao.mapMensagensEnvioCip(Boolean.FALSE, numAgrupamentoSteps));
    }

    /**
     * Método responsável por
     * 
     * @param listaIdMensagens
     * @throws ComumException void
     * 
     */
    private void processarEnvioMensagens(Map<Long, String> mapMensagensEnvioCip) throws ComumException {
        getLogger().debug("########### Inicio processarEnvioMensagens(Map<String, Long> mapMensagensEnvioCip).");
        LogErroSWSDto log = new LogErroSWSDto("integracaocip.erro.processar.envio.msgs");
        try {
            // TODO - VALIDAR TRATAMENTO DE EXCEÇÃO
            for (Map.Entry<Long, String> mapMensagem : mapMensagensEnvioCip.entrySet()) {
                try {
                    IntegracaoCipFabricaDelegates.getInstance().getProcessarEnvioMensagensDetalheDelegate().processarEnvioMensagens(mapMensagem.getValue(), mapMensagem.getKey());
                } catch (Exception e) { // NOSONAR
                    getLogger().erro(e, log.getLogErroComChave(e.getMessage(), mapMensagem.getValue().toString()));
                    log.incluirErro(e, mapMensagem.getValue().toString());
                }
            }
        } catch (BancoobException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e.getMessage(), e);
        }
        log.validarPossuiErro();
        getLogger().debug("########### Fim processarEnvioMensagens(Map<String, Long> mapMensagensEnvioCip).");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#excluirMensagensTabelaTmp()
     */
    public void excluirMensagensTabelaTmp() throws ComumException {
        dao.excluirMensagensTabelaTmp();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#registrarMensagensTabelaTmp(java.lang.Integer,
     *      java.lang.Integer, java.lang.Integer)
     */
    public void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg)
            throws ComumException {
        dao.registrarMensagensTabelaTmp(qtdParametroMensagensEnviadasPorRajada, qtdMaxRegistrosPorStep, qtdParametroNumPrioridadeMsg);
    }

}
