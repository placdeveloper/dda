/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ArquivoBoletoPagamentoAbertoServicoEJB.java
 * Data Criação:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoBoletoPagamentoAbertoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ArquivoBoletoPagamentoAbertoServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Local({ ArquivoBoletoPagamentoAbertoServicoLocal.class })
public class ArquivoBoletoPagamentoAbertoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoBoletoPagamentoAbertoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoIncluirBoletoDao dao;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBoletoPagamentoAbertoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        dao.incluirADDA127(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return null;
    }

}
