package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
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
 * TratarArquivosErroFluxoJobServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Remote(JobServico.class)
public class TratarArquivosErroFluxoJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/TratarArquivosErroFluxoStepServicoRemote";
    private static final int TIMEOUT_STEP = 900;

    private long codInstanciaFluxo;

    private ProcessamentoSWSDao processamentoSWSDao = ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();

    private ADMDelegate admDelegate;

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
        codInstanciaFluxo = contexto.getCodInstanciaFluxo();

        log(Constantes.STR_SEPARACAO);
        log(Constantes.PREPARANDO_STEPS);
        log(Constantes.STR_SEPARACAO);

        List<Step> listaSteps = new ArrayList<Step>();

        List<Long> listaEnvioArquivoDDA = obterListaEnvioArquivoDDA();

        Date dataMovimento = getDataMovimento();
        log("Data de movimento: " + dataMovimento);

        double esforcoStep = getEsforcoSteps(listaEnvioArquivoDDA.size());

        for (Long idLogEnvioArquivoDDA : listaEnvioArquivoDDA) {
            Parametro idLogEnvioArquivoDDAParametro = new Parametro("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA, TipoParametro.LONGO);
            Parametro dataMovimentoParametro = new Parametro("dataMovimento", dataMovimento, TipoParametro.DATA);

            listaSteps.add(ejb(JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(idLogEnvioArquivoDDAParametro, dataMovimentoParametro));
        }

        log(Constantes.STR_SEPARACAO);
        log(Constantes.QTD_STEPS_EXECUTADOS + listaSteps.size());
        log(Constantes.STR_SEPARACAO);

        return listaSteps;
    }

    /**
     * Método responsável por
     * 
     * @return List<Long>
     * 
     */
    private List<Long> obterListaEnvioArquivoDDA() {
        List<Long> listaEnvioArquivoDDA;
        try {
            listaEnvioArquivoDDA = processamentoSWSDao.listarIdLogEnvioArquivoTratamento();
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        log("Lista envio arquivo DDA: " + listaEnvioArquivoDDA);
        return listaEnvioArquivoDDA;
    }

    /**
     * Método responsável por obter a data de movimento
     * 
     * @return
     */
    private Date getDataMovimento() {
        Date dataMovimento = null;
        try {
            dataMovimento = getAdmDelegate().obterDataMovimentoBancoob();
        } catch (IntegracaoInternaException e) {
            getLogger().erro(e, e.getMessage());
            throw new BancoobRuntimeException(e.getMessage(), e);
        }
        return dataMovimento;
    }

    /**
     * Método responsável por obter o esforço
     * 
     * @param qtdSteps
     * @return
     */
    private double getEsforcoSteps(int qtdSteps) {
        return Constantes.INTEGER_UM / (double) qtdSteps;
    }

    /**
     * Método responsável por
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(TratarArquivosErroFluxoJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + codInstanciaFluxo + Constantes.STR_SEPARACAO_2 + str);
    }

    /**
     * @return o atributo admDelegate
     */
    public ADMDelegate getAdmDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

}
