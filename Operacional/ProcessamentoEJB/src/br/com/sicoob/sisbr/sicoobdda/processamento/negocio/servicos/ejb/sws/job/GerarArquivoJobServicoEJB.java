package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
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
 * GerarArquivoJobServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Remote(JobServico.class)
public class GerarArquivoJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/GerarArquivoStepServicoRemote";
    private static final int TIMEOUT_STEP = 3000;

    private ProcessamentoSWSDao processamentoSWSDao = ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();

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
        log(Constantes.STR_SEPARACAO);
        log(Constantes.PREPARANDO_STEPS);
        log(Constantes.STR_SEPARACAO);

        List<Step> listaSteps = new ArrayList<Step>();

        List<Long> listaIdLogEnvioArquivoDDA = obterListaIdEnvioArquivoDDA();
        log("Lista idLogEnvioArquivoDDA: " + listaIdLogEnvioArquivoDDA.size());

        double esforcoStep = getEsforcoSteps(listaIdLogEnvioArquivoDDA.size());

        for (Long idLogEnvioArquivoDDA : listaIdLogEnvioArquivoDDA) {
            Parametro idLogEnvioArquivoDDAParametro = new Parametro("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA, TipoParametro.LONGO);

            listaSteps.add(ejb(JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(idLogEnvioArquivoDDAParametro));
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
    private List<Long> obterListaIdEnvioArquivoDDA() {
        List<Long> listaEnvioArquivoDDA;
        try {
            listaEnvioArquivoDDA = processamentoSWSDao.listarIdLogEnvioArquivoGeraArquivo();
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new BancoobRuntimeException(e.getMessage(), e);
        }
        log("Lista envio arquivo DDA: " + listaEnvioArquivoDDA);
        return listaEnvioArquivoDDA;
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
        return SicoobLoggerPadrao.getInstance(GerarArquivoJobServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + Constantes.STR_SEPARACAO_2 + str);
    }
}
