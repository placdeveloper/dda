/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         EfetivarLancamentosCCOJobServicoEJB.java
 * Data Criação:    Jan 22, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
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
 * EfetivarLancamentosCCOJobServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(JobServico.class)
public class EfetivarLancamentosCCOJobServicoEJB extends JobSicoobServico {
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/EfetivarLancamentosCCOStepServicoRemote";
    private static final int TIMEOUT_STEP = 180;
    private Date dataMovimentoBancoob = null;

    /**
     * 
     */
    public EfetivarLancamentosCCOJobServicoEJB() {
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
     * @param
     * @return ISicoobLogger
     * @throws
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(EfetivarLancamentosCCOJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return
     * @throws
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
     * Método responsável por obter a data de movimento
     * 
     * @return Date
     * @throws ComumException
     */
    private Date getDataMovimentoBancoob() throws ComumException {
        try {
            if (ObjectUtil.isNull(dataMovimentoBancoob)) {
                dataMovimentoBancoob = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate().obterDataMovimentoBancoob();
            }
        } catch (IntegracaoInternaException e) {
            throw new ComumException(e.getMessage());
        }
        return dataMovimentoBancoob;
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
            List<RateioCreditoLancamentoCCODto> listaCooperativasRateioCCO = getProcessamentoSWSDao().listarCooperativasLancamentosRateioCCO();

            if (!ObjectUtil.isEmpty(listaCooperativasRateioCCO)) {
                double esforcoStep = getEsforcoSteps(listaCooperativasRateioCCO.size());

                /* @formatter:off */
                for (RateioCreditoLancamentoCCODto lancamentosRateioCCODto : listaCooperativasRateioCCO) {
                    listaSteps.add(ejb(JNDI_STEP_EJB).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(
                            new Parametro("idRateioDDA", lancamentosRateioCCODto.getIdRateioDDA(), TipoParametro.LONGO),
                            new Parametro("idInstituicao", lancamentosRateioCCODto.getIdInstituicao(), TipoParametro.INTEIRO),
                            new Parametro("numCooperativa", lancamentosRateioCCODto.getNumCooperativa(), TipoParametro.TEXTO),
                            new Parametro("dataMovimentoBancoob", getDataMovimentoBancoob(), TipoParametro.DATAHORA)));
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
