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
 * RegistrarArquivosJobServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Remote(JobServico.class)
public class RegistrarArquivoJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/RegistrarArquivoStepServicoRemote";
    private static final int TIMEOUT_STEP = 900;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        return sucesso();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        log(Constantes.STR_SEPARACAO);
        log(Constantes.PREPARANDO_STEPS);
        log(Constantes.STR_SEPARACAO);

        List<Step> listaSteps = new ArrayList<Step>();
        listaSteps.add(ejb(JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(1.0));
        return listaSteps;
    }

    /**
     * Método responsável por
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(RegistrarArquivoJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + Constantes.STR_SEPARACAO_2 + str);
    }
}
