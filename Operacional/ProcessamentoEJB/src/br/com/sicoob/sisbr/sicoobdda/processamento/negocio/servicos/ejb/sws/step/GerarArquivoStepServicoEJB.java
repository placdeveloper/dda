package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarEnvioArquivoDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * GerarArquivoStepServicoEJB
 * 
 * @author George.Santos
 */
@Stateless
@Remote(StepServico.class)
public class GerarArquivoStepServicoEJB extends StepSicoobServico {

    private long codInstanciaFluxo;
    private long codExecucaoAgendamento;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        codInstanciaFluxo = contexto.getCodInstanciaFluxo();
        codExecucaoAgendamento = contexto.getCodExecucaoAgendamentoFluxo();

        log(Constantes.STR_SEPARACAO);
        log("************ INICIO EXECUCAO STEP GERAR ARQUIVO  *************");
        log(Constantes.STR_SEPARACAO);

        try {
            Long idLogEnvioArquivoDDA = contexto.getParametrosStep().get("idLogEnvioArquivoDDA").getValor();
            log("Parâmetro - idLogEnvioArquivoDDA: " + idLogEnvioArquivoDDA);

            getProcessarEnvioArquivoDelegate().gerarArquivo(idLogEnvioArquivoDDA);
        } catch (BancoobException e) {
            return erro(e.getMessage());
        }

        log(Constantes.STR_SEPARACAO);
        log("************ FIM EXECUCAO STEP GERAR ARQUIVO  *************");
        log(Constantes.STR_SEPARACAO);

        return sucesso();
    }

    /**
     * @return ProcessarEnvioArquivoDelegate
     */
    private ProcessarEnvioArquivoDelegate getProcessarEnvioArquivoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getProcessarEnvioArquivoDelegate();
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(GerarArquivoStepServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param str
     */
    private void log(String str) {
        getLogger().debug(getMensagem(str));
    }

    /**
     * Método responsável por obter a mensagem
     * 
     * @param str
     * @return
     */
    private String getMensagem(String str) {
        return Constantes.STEP + "CodExecucaoAgendamentoFluxo: " + codExecucaoAgendamento + " CodInstanciaFluxo: " + codInstanciaFluxo + Constantes.STR_SEPARACAO_2 + str;
    }

}
