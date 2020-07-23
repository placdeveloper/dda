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
 * DisparadorCacheServicoEJB � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@Singleton
@Startup
@Local(DisparadorCacheServicoLocal.class)
public class DisparadorCacheServicoEJB extends ComumTimerServico implements DisparadorCacheServicoLocal {

    private static final int TEMPO_INICIO_PRIMEIRA_EXECUCAO = 5; // Tempo em segundos de iniciar a primeira execu��o do monitor cache
    private static final int INTERVALO = 4; // Tempo em horas para executar o pr�ximo timer.
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
     * Primeiro m�todo a ser executado onde � configurado inicialmente o per�odo de execu��o da classe.
     */
    @PostConstruct
    public void initTimer() {
        configurarProximoHorario();
        isPrimeiraExecucao = Boolean.FALSE;
    }

    /**
     * M�todo executado a cada chamada do Timer, onde:
     * 
     * 1) � cancelado o timer para evitar que durante a execu��o o Timer seja chamado novamente; 2) Execu��o da regra de neg�cio; 3) Configura��o do per�odo de
     * execu��o da classe pelo Timer.
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