/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         AtualizarSituacaoBoletoJobServicoEJB.java
 * Data Criação:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * AtualizarSituacaoBoletoJobServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Remote(JobServico.class)
public class AtualizarSituacaoBoletoJobServicoEJB extends JobSicoobServico {

    private static final String ALTERAR_SITUACAO_BOLETO_JNDI_STEP = "sicoobdda_processamento/AtualizarSituacaoBoletoStepServicoRemote";
    private static final int TIMEOUT_STEP = 300; // 5 min
    private static final String NOME_JOB = "AtualizarSituacaoBoleto";

    private ProcessamentoSWSDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao arg0) {
        List<Step> listaSteps = new ArrayList<Step>();
        long qtdSteps = obterQtdSteps();
        while (qtdSteps-- > 0) {
            listaSteps.add(ejb(ALTERAR_SITUACAO_BOLETO_JNDI_STEP).comTimeout(TIMEOUT_STEP));
        }
        return listaSteps;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao arg0) {
        return sucesso();
    }

    /**
     * Método responsável por
     * 
     * @return long
     * 
     */
    private long obterQtdSteps() {
        try {
            return getProcessamentoSWSDao().obterQuantidadeStepsAtualizarSituacaoBoleto();
        } catch (ComumException e) {
            getLogger().erro(e, MensagemUtil.getString("erro.job.obter.steps", NOME_JOB, e.getMessage()));
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", NOME_JOB, e.getMessage()), e);
        }
    }

    /**
     * @return ProcessamentoSWSDao
     * 
     */
    private ProcessamentoSWSDao getProcessamentoSWSDao() {
        if (dao == null) {
            dao = ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();
        }
        return dao;
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(ProcessarDetalheArquivoRecebidoCIPJobServicoEJB.class);
    }

}
