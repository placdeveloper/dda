package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.interfaces.TimerContigenciaServicoLocal;

/**
 * TimerContigenciaServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Singleton
@Startup
@Local(TimerContigenciaServicoLocal.class)
public class TimerContigenciaServicoEJB extends ComumTimerServico implements TimerContigenciaServicoLocal {

    private static final int INTERVALO = 10; // Tempo em segundos para executar o próximo timer.
    private static final int TEMPO_INICIO_PRIMEIRA_EXECUCAO = 5; // Tempo em segundos de iniciar a primeira execução do monitor cache
    private static final String NOME_TIMER = "timerContingencia";

    private static Date primeiraExecucao = DateUtil.incrementarData(new Date(), Calendar.SECOND, TEMPO_INICIO_PRIMEIRA_EXECUCAO);

    private Boolean isPrimeiraExecucao = Boolean.TRUE;

    @Resource
    TimerService timerService;

    @Resource
    private SessionContext context;

    private void executarServico() {
        getLogger().debug("[DDA] Primeira execução do timer de contingência: " + isPrimeiraExecucao);

        try {
            OperacionalFabricaDelegates.getInstance().getMonitoracaoDelegate().avaliarHabilitarContingencia();
        } catch (Exception ex) {
            getLogger().erro(ex, ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getTimerService()
     */
    protected TimerService getTimerService() {
        return timerService;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getContext()
     */
    protected SessionContext getContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getNomeTimer()
     */
    @Override
    protected String getNomeTimer() {
        return NOME_TIMER;
    }

    /**
     * Primeiro método a ser executado onde é configurado inicialmente o período de execução da classe.
     */
    @PostConstruct
    public void initTimer() {
        configurarProximoHorario();
        isPrimeiraExecucao = Boolean.FALSE;
    }

    /**
     * Método executado a cada chamada do Timer, onde:
     * 
     * 1) É cancelado o timer para evitar que durante a execução o Timer seja chamado novamente; 2) Execução da regra de negócio; 3) Configuração do período de
     * execução da classe pelo Timer.
     */
    @Override
    public void ejbTimeout(Timer timer) {
        clearTimer();
        executarServico();
        configurarProximoHorario();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getIntervaloExecucao()
     */
    protected int getIntervaloExecucao() {
        return INTERVALO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getPeriodoExecucao()
     */
    protected int getPeriodoExecucao() {
        return Calendar.SECOND;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getPrimeiraExecucao()
     */
    @Override
    protected Date getPrimeiraExecucao() {
        return primeiraExecucao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#isPrimeiraExecucao()
     */
    @Override
    protected Boolean isPrimeiraExecucao() {
        return isPrimeiraExecucao;
    }

}