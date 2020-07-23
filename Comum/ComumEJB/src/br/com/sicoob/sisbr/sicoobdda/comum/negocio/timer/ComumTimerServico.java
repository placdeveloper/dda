package br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer;

import java.util.Collection;
import java.util.Date;

import javax.ejb.NoMoreTimeoutsException;
import javax.ejb.SessionContext;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * ComumServicoTimer é responsável por
 * 
 * @author Felipe.Rosa
 */
public abstract class ComumTimerServico extends ComumServicoEJB implements TimedObject {

    /**
     * @return
     */
    protected abstract TimerService getTimerService();

    /**
     * @return
     */
    protected abstract SessionContext getContext();

    /**
     * Retorna o interfalo entre uma execução e outra deste serviço. Hoje configurado em horas.
     */
    protected abstract int getIntervaloExecucao();

    /**
     * Retorna o nome do Timer
     */
    protected abstract String getNomeTimer();

    /**
     * Método responsável por retornar o horário da primeira exeução.
     * 
     * @return Date
     * 
     */
    protected abstract Date getPrimeiraExecucao();

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    protected abstract Boolean isPrimeiraExecucao();

    /**
     * Retorna true caso o timer já tenha sido definido, desde de que o horário seja posterior ao atual.
     * 
     * @return
     */
    protected boolean existeTimerAgendado() {
        Timer timer = obterTimer();
        if (!ObjectUtil.isNull(timer)) {
            try {
                return timer.getNextTimeout().after(new Date());
            } catch (NoMoreTimeoutsException e) {
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Obtêm o timer
     * 
     * @return
     */
    protected Timer obterTimer() {
        Timer timer = null;

        Collection<Timer> listTimer = getTimerService().getTimers();
        if (!ObjectUtil.isEmpty(listTimer)) {
            timer = listTimer.iterator().next();
            if (!ObjectUtil.isNull(timer.getInfo()) && timer.getInfo().equals(getNomeTimer())) {
                return timer;
            }
        }

        return timer;
    }

    /**
     * Cancela o timer agendado.
     */
    protected void clearTimer() {
        Timer timer = obterTimer();
        if (!ObjectUtil.isNull(timer)) {
            timer.cancel();
        }
    }

    /**
     * Define o próximo horário de execução desta EJB. Caso existe Timers agendados, estes serão cancelados, antes de agendar um novo Timer.
     */
    protected void configurarProximoHorario() {
        getLogger().info("[DDA] Configuração do próximo horário para execução do serviço: ".concat(getClass().getName()));

        Date proximaExecucao = getProximaExecucao();

        TimerConfig config = new TimerConfig();
        config.setInfo(getNomeTimer());
        config.setPersistent(Boolean.FALSE);

        getTimerService().createSingleActionTimer(proximaExecucao, config);

        getLogger().info("[DDA] Próximo disparo dos serviços será: ".concat(proximaExecucao.toString()));
    }

    /**
     * Método responsável por
     * 
     * @return Date
     * 
     */
    private Date getProximaExecucao() {
        Date proximaExecucao;
        if (isPrimeiraExecucao()) {
            proximaExecucao = getPrimeiraExecucao();
        } else {
            proximaExecucao = DateUtil.incrementarData(new Date(), getPeriodoExecucao(), getIntervaloExecucao());
        }
        return proximaExecucao;
    }

    /**
     * Método responsável por obter o período para execução do timer
     *
     * @return int - utilizar o Calendar.VALOR
     */
    protected abstract int getPeriodoExecucao();

    /**
     * Exibe o próximo horário de execução do disparador
     * 
     * @param timer
     * @return
     */
    protected String exibirHorarioDisparoExecucao(Timer timer) {
        String horario = null;

        try {
            horario = timer.getNextTimeout().toString();
        } catch (NoMoreTimeoutsException e) {
            horario = "[DDA] não disponível";
        }

        return horario;
    }

}