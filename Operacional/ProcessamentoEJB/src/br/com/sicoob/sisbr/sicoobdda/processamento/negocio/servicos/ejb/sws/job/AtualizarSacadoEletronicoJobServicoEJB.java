/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         AtualizarSacadoEletronicoJobServicoEJB.java
 * Data Criação:    09/12/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CooperativaLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * AtualizarSacadoEletronicoJobServicoEJB é responsável por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(JobServico.class)
public class AtualizarSacadoEletronicoJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/AtualizarSacadoEletronicoStepServicoRemote";

    private static final int TIMEOUT_STEP = 600; // 10 min

    private CooperativaLegadoDao cooperativaLegadoDao = ComumDaoFactory.getInstance().criarCooperativaLegadoDao();

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

        List<Step> listaSteps = new ArrayList<Step>();

        Integer numCooperativaContext = contexto.getParametroDinamico().getValor();
        log("Parâmetro - numCooperativaContext: " + numCooperativaContext);

        List<Integer> listaCooperativaLegado = listarCooperativaLegado(numCooperativaContext);
        log("listaCooperativaLegado: " + listaCooperativaLegado);

        double esforcoStep = getEsforcoSteps(listaCooperativaLegado.size());

        for (Integer numCooperativaLegado : listaCooperativaLegado) {
            Parametro numCooperativa = new Parametro("numCooperativa", numCooperativaLegado, TipoParametro.INTEIRO);
            listaSteps.add(ejb(JNDI_STEP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(esforcoStep).comParametros(numCooperativa));
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
        return SicoobLoggerPadrao.getInstance(RegistrarArquivoJobServicoEJB.class);
    }

    /**
     * @param str
     */
    private void log(String str) {
        getLogger().debug(Constantes.JOB + "ATUALIZAR SACADO ELETRONICO" + Constantes.STR_SEPARACAO_2 + str);
    }

}
