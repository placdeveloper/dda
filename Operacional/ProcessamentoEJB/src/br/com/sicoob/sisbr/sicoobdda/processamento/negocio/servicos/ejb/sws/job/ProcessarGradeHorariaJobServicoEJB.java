/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         EnviarGradeHorariaJobServicoEJB.java
 * Data Criação:    19/09/2016
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
 * ProcessarGradeHorariaJobServicoEJB é responsável por verificar se existe grade do tipo DDA da próxima data de movimento cadastrada, caso não exista, deve ser enviada mensagem (DDA0401) para CIP solicitando a grade
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(JobServico.class)
public class ProcessarGradeHorariaJobServicoEJB extends JobSicoobServico {

    private static final String CADASTRAR_GRADE_HORARIA_SICOOB_JNDI_STEP = "sicoobdda_processamento/CadastrarGradeHorariaSicoobStepServicoRemote";
    private static final int TIMEOUT_STEP = 300; // 5 min

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
        List<Step> listaSteps = new ArrayList<Step>();
        listaSteps.add(ejb(CADASTRAR_GRADE_HORARIA_SICOOB_JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(0.5));
        return listaSteps;
    }

}
