/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         ConsolidarLancamentosCCOJobServicoEJB.java
 * Data Criação:    Jan 19, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * ConsolidarLancamentosCCOJobServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(JobServico.class)
public class ConsolidarLancamentosCCOJobServicoEJB extends JobSicoobServico {
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/ConsolidarLancamentosCCOStepServicoRemote";
    private static final int TIMEOUT_STEP = 180;

    /**
     * 
     */
    public ConsolidarLancamentosCCOJobServicoEJB() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        return sucesso();
    }

    /**
     * @return ISicoobLogger
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ConsolidarLancamentosCCOJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return
     */
    private void log(String mensagem) {
        getLogger().debug(Constantes.JOB + Constantes.STR_SEPARACAO_2 + mensagem + Constantes.STR_SEPARACAO_2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        log("Executar " + ConsolidarLancamentosCCOJobServicoEJB.class.getSimpleName());

        final List<Step> listaSteps = new ArrayList<Step>();

        listaSteps.add(ejb(JNDI_STEP_EJB).comTimeout(TIMEOUT_STEP));
        log("Finalizar " + ConsolidarLancamentosCCOJobServicoEJB.class.getSimpleName());
        return listaSteps;
    }

}
