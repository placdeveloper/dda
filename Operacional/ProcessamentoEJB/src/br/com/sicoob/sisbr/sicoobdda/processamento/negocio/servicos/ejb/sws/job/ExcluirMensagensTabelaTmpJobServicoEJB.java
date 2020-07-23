/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         ProcessarEnvioMensagensPrioritariasJobServicoEJB.java
 * Data Criação:    Jul 8, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * ExcluirMensagensTabelaTmpJobServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(JobServico.class)
public class ExcluirMensagensTabelaTmpJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_EXCLUIR_MSG_TMP = "sicoobdda_processamento/ExcluirMensagensTabelaTmpStepServicoRemote";

    private static final int TIMEOUT_STEP = 600;

    /*
     * (non-Javadoc)
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

        List<Step> listaSteps = new ArrayList<Step>();

        listaSteps.add(ejb(JNDI_EXCLUIR_MSG_TMP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(1.0));
        return listaSteps;
    }
}
