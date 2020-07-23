/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         ProcessarEnvioMensagensPrioritariasJobServicoEJB.java
 * Data Criação:    Jul 8, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
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
 * RegistrarMensagensTabelaTmpJobServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(JobServico.class)
public class RegistrarMensagensTabelaTmpJobServicoEJB extends JobSicoobServico {

    private static final String JNDI_EXCLUIR_MSG_TMP = "sicoobdda_processamento/ExcluirMensagensTabelaTmpStepServicoRemote";
    private static final String JNDI_REGISTRAR_MSG_TABELA_TMP = "sicoobdda_processamento/RegistrarMensagensTabelaTmpStepServicoRemote";

    private static final int TIMEOUT_STEP = 600;

    private ProcessamentoSWSDao processamentoSWSDao = ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();
    private ParametroDao parametroDao = ComumDaoFactory.getInstance().criarParametroDao();

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        try {
            int qtdMaxRegistrosEnvioImediato = parametroDao.obterValorInteger(ParametroDDA.QTD_MAX_REGISTRO_NA_FILA_PERMITE_ENVIO_IMEDIATO, Constantes.ID_SICOOB);
            Long qtdRegistrosFilaSemProtocolo = processamentoSWSDao.verificarQtdRegistroNaFilaEnvioMsgSemProtocolo();

            if (qtdRegistrosFilaSemProtocolo >= qtdMaxRegistrosEnvioImediato) {
                return finalizarJob("A fila está com a qtde máxima de registros (" + qtdRegistrosFilaSemProtocolo + ") para envio imediato.");
            }
        } catch (ComumException e) {
            return erro(e.getMessage());
        }
        return sucesso();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        Parametro qtdParametroMensagensEnviadasPorRajada = new Parametro("qtdParametroMensagensEnviadasPorRajada", getQtdParametroMensgemEnviadaPorRajada(), TipoParametro.INTEIRO);
        Parametro qtdMaxRegistrosPorStep = new Parametro("qtdMaxRegistrosPorStep", getQtdMaxRegistrosPorStep(), TipoParametro.INTEIRO);
        Parametro qtdParametroNumPrioridadeMsg = new Parametro("qtdParametroNumPrioridadeMsg", getQtdParametroNumPrioridadeMsg(), TipoParametro.INTEIRO);

        List<Step> listaSteps = new ArrayList<Step>();

        listaSteps.add(ejb(JNDI_EXCLUIR_MSG_TMP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(0.5));
        listaSteps.add(ejb(JNDI_REGISTRAR_MSG_TABELA_TMP).comTimeout(TIMEOUT_STEP).realizandoEsforcoDe(0.5).comParametros(qtdParametroMensagensEnviadasPorRajada,
                qtdMaxRegistrosPorStep,
                qtdParametroNumPrioridadeMsg));
        return listaSteps;
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    private int getQtdParametroMensgemEnviadaPorRajada() {
        int qtdParametroMensagensEnviadasPorRajada;
        try {
            qtdParametroMensagensEnviadasPorRajada = parametroDao.obterValorInteger(ParametroDDA.QTD_MENSAGEM_ENVIADA_POR_RAJADA, Constantes.ID_SICOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException("Erro ao recuperar o valor do parametro QTD_MENSAGEM_ENVIADA_POR_RAJADA", e);
        }
        return qtdParametroMensagensEnviadasPorRajada;
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    private int getQtdMaxRegistrosPorStep() {
        int qtdMaxRegistrosPorStep;
        try {
            qtdMaxRegistrosPorStep = parametroDao.obterValorInteger(ParametroDDA.QTD_MAX_REGISTROS_POR_STEP_MOTOR_ENVIO_CIP, Constantes.ID_SICOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException("Erro ao recuperar o valor do parametro QTD_MAX_REGISTROS_POR_STEP_MOTOR_ENVIO_CIP", e);
        }
        return qtdMaxRegistrosPorStep;
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    private int getQtdParametroNumPrioridadeMsg() {
        int qtdParametroNumPrioridadeMsg;
        try {
            qtdParametroNumPrioridadeMsg = parametroDao.obterValorInteger(ParametroDDA.NUM_PRIORIDADE_ENVIO_MSG_CIP, Constantes.ID_SICOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException("Erro ao recuperar o valor do parametro Número da prioridade de envio CIP ( <= X )", e);
        }
        return qtdParametroNumPrioridadeMsg;
    }
}
