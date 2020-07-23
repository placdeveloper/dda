/**
 * Projeto:         DDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         ConsolidarEventosTarifaveisDDAServicoEJB.java
 * Data Criação:    Jan 18, 2018
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
 * ConsolidarEventosTarifaveisCipJobServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(JobServico.class)
public class ConsolidarEventosTarifaveisCipJobServicoEJB extends JobSicoobServico {
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/ConsolidarEventosTarifaveisCipStepServicoRemote";
    private static final int TIMEOUT_STEP = 180;

    /**
     * 
     */
    public ConsolidarEventosTarifaveisCipJobServicoEJB() {
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
        return SicoobLoggerPadrao.getInstance(ConsolidarEventosTarifaveisCipJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return ISicoobLogger
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
        log("Executar " + ConsolidarEventosTarifaveisCipJobServicoEJB.class.getSimpleName());

        final List<Step> listaSteps = new ArrayList<Step>();

        listaSteps.add(ejb(JNDI_STEP_EJB).comTimeout(TIMEOUT_STEP));
        log("Finalizar " + ConsolidarEventosTarifaveisCipJobServicoEJB.class.getSimpleName());
        return listaSteps;
    }

}