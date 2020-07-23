/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ejb;

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

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.interfaces.DisparadorCacheServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;

/**
 * DisparadorCacheServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Singleton
@Startup
@Local(DisparadorCacheServicoLocal.class)
public class DisparadorCacheServicoEJB extends ComumTimerServico implements DisparadorCacheServicoLocal {

    private static final int TEMPO_INICIO_PRIMEIRA_EXECUCAO = 5; // Tempo em segundos de iniciar a primeira execução do monitor cache
    private static final int INTERVALO = 4; // Tempo em horas para executar o próximo timer.
    private static final String NOME_TIMER = "timerCache";

    private static Date primeiraExecucao = DateUtil.incrementarData(new Date(), Calendar.SECOND, TEMPO_INICIO_PRIMEIRA_EXECUCAO);

    private Boolean isPrimeiraExecucao = Boolean.TRUE;

    @Resource
    TimerService timerService;

    @Resource
    private SessionContext context;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.ComumTimerServico#getTimerService()
     */
    protected TimerService getTimerService() {
        return timerService;
    }

    /**
     * @return the context
     */
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer.DisparadorMonitorCacheServico#executarServico()
     */
    public void executarServico() {
        try {
            getLogger().debug("[DDA] Primeira execucao timer cache: " + primeiraExecucao);

            getLogger().debug("[DDA] Executando carregamento de cache...");
            ComumFabricaDelegates.getInstance().getCacheDelegate().carregar();
        } catch (Exception ex) {
            getLogger().alerta(ex, ex.getMessage());
        }
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
        return Calendar.HOUR;
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