/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         GerarCargaMensagensLegadoJobServicoEJB.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CooperativaLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
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
 * GerarCargaMensagensLegadoJobServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Remote(JobServico.class)
public class GerarCargaMensagensLegadoJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP_INCLUSAO = "sicoobdda_processamento/GerarCargaInclusaoMensagensLegadoStepServicoRemote";
    private static final String JNDI_STEP_ALTERACAO = "sicoobdda_processamento/GerarCargaAlteracaoMensagensLegadoStepServicoRemote";
    private static final String JNDI_STEP_BAIXA = "sicoobdda_processamento/GerarCargaBaixaMensagensLegadoStepServicoRemote";
    private static final int TIMEOUT_STEP = 600; // 10 min

    private CooperativaLegadoDao cooperativaLegadoDao = ComumDaoFactory.getInstance().criarCooperativaLegadoDao();

    private static final String NUMCOOPERATIVA = "numCooperativa";
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
        log(Constantes.PREPARANDO_STEPS);
        String horaParametro = null;
        try {
            horaParametro = getParametroDao().obterValor(ParametroDDA.HORA_MOTOR_CARGA_BAIXA, Constantes.ID_SICOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", GerarCargaMensagensLegadoJobServicoEJB.class.getSimpleName(), e.getMessage()), e);
        }

        List<Step> listaSteps = new ArrayList<>();

        List<Integer> listaCooperativaLegado = listarCooperativaLegado(1);
        log("listaCooperativaLegado: " + listaCooperativaLegado);

        double esforcoStep = getEsforcoSteps(listaCooperativaLegado.size());

        final Calendar dataHoraCadastro = Calendar.getInstance();
        int hour = dataHoraCadastro.get(Calendar.HOUR_OF_DAY);

        for (Integer numCooperativaLegado : listaCooperativaLegado) {
            Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaLegado, TipoParametro.INTEIRO);
            listaSteps.add(ejb(JNDI_STEP_INCLUSAO).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
            listaSteps.add(ejb(JNDI_STEP_ALTERACAO).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));

            log("Verificando a validação da baixa efetiva");
            log("Hora Parametro DDA 103: " + horaParametro);
            log("Hora Sistema: " + hour);
            if (!ObjectUtil.isEmpty(horaParametro) && Integer.valueOf(horaParametro) > hour) {
                listaSteps.add(ejb(JNDI_STEP_BAIXA).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
            }
        }

        log(Constantes.QTD_STEPS_EXECUTADOS + listaSteps.size());

        return listaSteps;
    }

    /**
     * Método responsável por listar as cooperativas para processar
     * 
     * @param numCooperativa
     * @return
     */
    private List<Integer> listarCooperativaLegado(Integer numCooperativa) {
        try {
            List<Integer> listaCooperativa = cooperativaLegadoDao.listarCooperativa(numCooperativa);
            log("listaCooperativa: " + listaCooperativa);
            return listaCooperativa;
        } catch (ComumException e) {
            throw new BancoobRuntimeException(e);
        }
    }

    /**
     * @param qtdSteps
     * @return double
     */
    private double getEsforcoSteps(int qtdSteps) {
        return Constantes.INTEGER_UM / (double) qtdSteps;
    }

    /**
     * @return ISicoobLogger
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(GerarCargaMensagensLegadoJobServicoEJB.class);
    }

    /**
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + "GERAR CARGA MENSAGENS LEGADO" + Constantes.STR_SEPARACAO_2 + str);
    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    private ParametroDao getParametroDao() {
        return ComumDaoFactory.getInstance().criarParametroDao();
    }

}
