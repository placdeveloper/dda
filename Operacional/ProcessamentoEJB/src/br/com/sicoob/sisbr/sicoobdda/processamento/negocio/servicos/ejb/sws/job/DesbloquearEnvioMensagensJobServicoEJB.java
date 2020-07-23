/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         BloquearEnvioMensagensJobServicoEJB.java
 * Data Criação:    Jun 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * BloquearEnvioMensagensJobServicoEJB
 * 
 * @author rafael.silva
 */
@Stateless
@Remote(JobServico.class)
public class DesbloquearEnvioMensagensJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/BloquearDesbloquearMotorStepServicoRemote";
    private static final int TIMEOUT_STEP = 300;

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
        Parametro paramParametroDDA = new Parametro("idParametro", ParametroDDA.MOTOR_ENVIO_MSGS_EM_EXECUCAO, TipoParametro.LONGO);
        Parametro paramExecucao = new Parametro("emExecucao", Constantes.STRING_NUMERO_0, TipoParametro.TEXTO);
        Parametro paramInstituicao = new Parametro("idInstituicao", Constantes.ID_SICOOB, TipoParametro.INTEIRO);
        listaSteps.add(ejb(JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(1.0).comParametros(paramParametroDDA, paramExecucao, paramInstituicao));
        return listaSteps;
    }

}
