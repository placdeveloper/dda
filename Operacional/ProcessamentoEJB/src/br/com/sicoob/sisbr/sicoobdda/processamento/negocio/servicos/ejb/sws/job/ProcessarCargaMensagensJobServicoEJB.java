/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         ProcessarCargaMensagensJobServicoEJB.java
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaCargaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CooperativaLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarCargaMensagensDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * ProcessarCargaMensagensJobServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Remote(JobServico.class)
public class ProcessarCargaMensagensJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_PAGADOR_STEP_TODOS = "sicoobdda_processamento/CadastrarMensagensCargaPagadorStepServicoRemote";
    private static final String JNDI_BOLETO_STEP_TODOS = "sicoobdda_processamento/CadastrarMensagensCargaBoletoStepServicoRemote";
    private static final String JNDI_BAIXA_BOLETO_STEP_TODOS = "sicoobdda_processamento/CadastrarMensagensCargaBaixaEfetivaBoletoStepServicoRemote";

    private static final String JNDI_MSG_STEP_ESPECIFICAS = "sicoobdda_processamento/CadastrarMensagensCargaEspecificasStepServicoRemote";

    private static final int TIMEOUT_STEP = 1200; // 20 min

    private CooperativaLegadoDao cooperativaLegadoDao = ComumDaoFactory.getInstance().criarCooperativaLegadoDao();

    private static final String NUMCOOPERATIVA = "numCooperativa";
    private static final String NRAGRUPAMENTO = "nrAgrupamento";
    private static final String CODTIPOOPERACAO = "codTipoOperacao";

    private static final String BOLETO = "I";
    private static final String BAIXA = "B";
    private static final String PAGADOR = "P";

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

        List<Step> listaSteps = new ArrayList<>();

        String instancia = contexto.getParametroDinamico().getValor();

        TipoInstanciaCargaEnum instanciaSWS = TipoInstanciaCargaEnum.valueOf(instancia);

        if (instanciaSWS.getDescricao().equals(TipoInstanciaCargaEnum.ZERO1.getDescricao())) {
            List<Integer> listaCooperativaLegado = TipoInstanciaCargaEnum.getListaCooperativasEspecificas();
            double esforcoStep = getEsforcoSteps(listaCooperativaLegado.size());

            criarStepCargaBoletoInstanciaEspecificas(listaSteps, listaCooperativaLegado, esforcoStep);

            criarStepCargaBaixaBoletoInstanciaEspecificas(listaSteps, listaCooperativaLegado, esforcoStep);

            criarStepCargaPagadoresInstanciaEspecificas(listaSteps, listaCooperativaLegado, esforcoStep);

        } else if (instanciaSWS.getDescricao().equals(TipoInstanciaCargaEnum.TODOS.getDescricao())) {
            List<Integer> listaCooperativaLegado = listarCooperativaLegado(1);

            listaCooperativaLegado.removeAll(TipoInstanciaCargaEnum.getListaCooperativasEspecificas());

            double esforcoStep = getEsforcoSteps(listaCooperativaLegado.size());

            criarStepCargaBoletoInstanciaTodos(listaSteps, listaCooperativaLegado, esforcoStep);

            criarStepCargaPagadoresInstanciaTodos(listaSteps, listaCooperativaLegado, esforcoStep);

            criarStepCargaBaixaInstanciaTodos(listaSteps, listaCooperativaLegado, esforcoStep);

            log(Constantes.QTD_STEPS_EXECUTADOS + listaSteps.size());
        }

        return listaSteps;
    }

    /**
     * Método responsável por
     * 
     * @param listaSteps
     * @param listaCooperativaEspecifica
     * @param esforcoStep void
     * @throws ComumException
     */
    private void criarStepCargaBoletoInstanciaEspecificas(List<Step> listaSteps, List<Integer> listaCooperativaEspecifica, double esforcoStep) {
        for (Integer numCooperativaEspecifica : listaCooperativaEspecifica) {
            List<Integer> listaAgrupamento = getProcessarCargaMensagensDelegate().listaAgrupaCooperativaEspecificaBoleto(numCooperativaEspecifica);

            for (Integer agrupamento : listaAgrupamento) {
                Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaEspecifica, TipoParametro.INTEIRO);
                Parametro nrAgrupamento = new Parametro(NRAGRUPAMENTO, agrupamento, TipoParametro.INTEIRO);
                Parametro codTipoOperacao = new Parametro(CODTIPOOPERACAO, BOLETO, TipoParametro.TEXTO);
                
                listaSteps.add(
                        ejb(JNDI_MSG_STEP_ESPECIFICAS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa, nrAgrupamento, codTipoOperacao));
            }

        }
    }

    /**
     * Método responsável por
     * 
     * @param listaSteps
     * @param listaCooperativaEspecifica
     * @param esforcoStep void
     * @throws ComumException
     */
    private void criarStepCargaBaixaBoletoInstanciaEspecificas(List<Step> listaSteps, List<Integer> listaCooperativaEspecifica, double esforcoStep) {
        for (Integer numCooperativaEspecifica : listaCooperativaEspecifica) {
            List<Integer> listaAgrupamento = getProcessarCargaMensagensDelegate().listaAgrupaCooperativaEspecificaBaixa(numCooperativaEspecifica);

            for (Integer agrupamento : listaAgrupamento) {
                Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaEspecifica, TipoParametro.INTEIRO);
                Parametro nrAgrupamento = new Parametro(NRAGRUPAMENTO, agrupamento, TipoParametro.INTEIRO);
                Parametro codTipoOperacao = new Parametro(CODTIPOOPERACAO, BAIXA, TipoParametro.TEXTO);

                listaSteps.add(ejb(JNDI_MSG_STEP_ESPECIFICAS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa, nrAgrupamento,
                        codTipoOperacao));

            }

        }
    }

    /**
     * Método responsável por
     * 
     * @param listaSteps
     * @param listaCooperativaEspecifica
     * @param esforcoStep void
     * @throws ComumException
     */
    private void criarStepCargaPagadoresInstanciaEspecificas(List<Step> listaSteps, List<Integer> listaCooperativaEspecifica, double esforcoStep) {
        for (Integer numCooperativaEspecifica : listaCooperativaEspecifica) {
            List<Integer> listaAgrupamento = getProcessarCargaMensagensDelegate().listaAgrupaCooperativaEspecificaPagador(numCooperativaEspecifica);

            for (Integer agrupamento : listaAgrupamento) {
                Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaEspecifica, TipoParametro.INTEIRO);
                Parametro nrAgrupamento = new Parametro(NRAGRUPAMENTO, agrupamento, TipoParametro.INTEIRO);
                Parametro codTipoOperacao = new Parametro(CODTIPOOPERACAO, PAGADOR, TipoParametro.TEXTO);

                listaSteps.add(
                        ejb(JNDI_MSG_STEP_ESPECIFICAS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa, nrAgrupamento, codTipoOperacao));

            }

        }
    }

    /**
     * Método responsável por 
     * @param listaSteps
     * @param listaCooperativaLegado
     * @param esforcoStep void
     */
    private void criarStepCargaBoletoInstanciaTodos(List<Step> listaSteps, List<Integer> listaCooperativaLegado, double esforcoStep) {
        for (Integer numCooperativaLegado : listaCooperativaLegado) {
            Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaLegado, TipoParametro.INTEIRO);
            listaSteps.add(ejb(JNDI_BOLETO_STEP_TODOS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
        }
    }

    /**
     * Método responsável por
     * 
     * @param listaSteps
     * @param listaCooperativaLegado
     * @param esforcoStep void
     */
    private void criarStepCargaPagadoresInstanciaTodos(List<Step> listaSteps, List<Integer> listaCooperativaLegado, double esforcoStep) {
        for (Integer numCooperativaLegado : listaCooperativaLegado) {
            Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaLegado, TipoParametro.INTEIRO);
            listaSteps.add(ejb(JNDI_PAGADOR_STEP_TODOS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
        }
    }

    /**
     * Método responsável por 
     * @param listaSteps
     * @param listaCooperativaLegado
     * @param esforcoStep void
     */
    private void criarStepCargaBaixaInstanciaTodos(List<Step> listaSteps, List<Integer> listaCooperativaLegado, double esforcoStep) {
        String horaParametro = null;
        try {
            horaParametro = getParametroDao().obterValor(ParametroDDA.HORA_MOTOR_CARGA_BAIXA, Constantes.ID_SICOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", ProcessarCargaMensagensJobServicoEJB.class.getSimpleName(), e.getMessage()), e);
        }

        final Calendar dataHoraCadastro = Calendar.getInstance();
        int hour = dataHoraCadastro.get(Calendar.HOUR_OF_DAY);

        log("Verificando a validação da baixa efetiva");
        log("Hora Parametro DDA 103: " + horaParametro);
        log("Hora Sistema: " + hour);
        if (!ObjectUtil.isEmpty(horaParametro) && Integer.valueOf(horaParametro) > hour) {
            for (Integer numCooperativaLegado : listaCooperativaLegado) {
                Parametro numCooperativa = new Parametro(NUMCOOPERATIVA, numCooperativaLegado, TipoParametro.INTEIRO);
                listaSteps.add(ejb(JNDI_BAIXA_BOLETO_STEP_TODOS).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
            }
        }
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
        return SicoobLoggerPadrao.getInstance(RegistrarArquivoJobServicoEJB.class);
    }

    /**
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + "GERAR MENSAGENS DB2" + Constantes.STR_SEPARACAO_2 + str);
    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    private ParametroDao getParametroDao() {
        return ComumDaoFactory.getInstance().criarParametroDao();
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return
     */
    private ProcessarCargaMensagensDelegate getProcessarCargaMensagensDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getProcessarCargaMensagensDelegate();
    }

}
