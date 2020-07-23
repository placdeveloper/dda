/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         AtualizarSituacaoRateioCCOJobServicoEJB.java
 * Data Criação:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * AtualizarSituacaoRateioCCOJobServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(JobServico.class)
public class AtualizarSituacaoRateioCCOJobServicoEJB extends JobSicoobServico {
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/AtualizarSituacaoRateioCCOStepServicoRemote";
    private static final int TIMEOUT_STEP = 180;

    /**
     * 
     */
    public AtualizarSituacaoRateioCCOJobServicoEJB() {
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
        return SicoobLoggerPadrao.getInstance(AtualizarSituacaoRateioCCOJobServicoEJB.class);
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
     * @param
     * @return ProcessamentoSWSDao
     * @throws
     */
    private ProcessamentoSWSDao getProcessamentoSWSDao() {
        return ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();
    }

    /**
     * Método responsável por calcular o esforço
     * 
     * @param qtdSteps
     * @return double
     */
    private double getEsforcoSteps(int qtdSteps) {
        return Constantes.INTEGER_UM / (double) qtdSteps;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        log(Constantes.STR_SEPARACAO);
        log(Constantes.PREPARANDO_STEPS);

        List<Step> listaSteps = new ArrayList<Step>();

        try {
            List<SituacaoRateioCreditoCCODto> listaRateiosCreditoCCO = getProcessamentoSWSDao().listarRateiosCreditoAtualizacaoCCO();

            if (!ObjectUtil.isEmpty(listaRateiosCreditoCCO)) {
                double esforcoStep = getEsforcoSteps(listaRateiosCreditoCCO.size());

                /* @formatter:off */
                for (SituacaoRateioCreditoCCODto situacaoRateioCreditoCCODto : listaRateiosCreditoCCO) {
                    listaSteps.add(ejb(JNDI_STEP_EJB).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(
                            new Parametro("idRateioDDA", situacaoRateioCreditoCCODto.getIdRateioDDA(), TipoParametro.LONGO),
                            new Parametro("qtdAguardandoEfetivacao", situacaoRateioCreditoCCODto.getQtdAguardandoEfetivacao(), TipoParametro.INTEIRO),
                            new Parametro("qtdEfetivado", situacaoRateioCreditoCCODto.getQtdEfetivado(), TipoParametro.INTEIRO),
                            new Parametro("qtdErroEfetivacao", situacaoRateioCreditoCCODto.getQtdErroEfetivacao(), TipoParametro.INTEIRO),
                            new Parametro("qtdEfetivadoManualmente", situacaoRateioCreditoCCODto.getQtdEfetivadoManualmente(), TipoParametro.INTEIRO),
                            new Parametro("qtdEfetivadoComAjuste", situacaoRateioCreditoCCODto.getQtdEfetivadoComAjuste(), TipoParametro.INTEIRO)));
                }
                /* @formatter:on */

            }
        } catch (ComumException e) {
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", EfetivarLancamentosCCOJobServicoEJB.class.getSimpleName(), e.getMessage()), e);
        }

        log(Constantes.QTD_STEPS_EXECUTADOS + listaSteps.size());
        log(Constantes.STR_SEPARACAO);

        return listaSteps;
    }

}
