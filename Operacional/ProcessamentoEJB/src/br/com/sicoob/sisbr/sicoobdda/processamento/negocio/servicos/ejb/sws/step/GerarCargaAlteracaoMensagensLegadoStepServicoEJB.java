/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         GerarCargaMensagensLegadoStepServicoEJB.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarCargaMensagensDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * GerarCargaAlteracaoMensagensLegadoStepServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(StepServico.class)
public class GerarCargaAlteracaoMensagensLegadoStepServicoEJB extends StepSicoobServico {

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

        log(Constantes.STEP + Constantes.STR_SEPARACAO_2 + "GERAR CARGA MENSAGENS LEGADO");
        log(Constantes.STR_SEPARACAO);
        log("************ INICIO EXECUCAO STEP GERAR ALTERAÇÃO CARGA MENSAGENS LEGADO *************");
        log(Constantes.STR_SEPARACAO);

        try {
            Integer numCooperativa = contexto.getParametrosStep().get("numCooperativa").getValor();
            log("Parâmetros - numCooperativa: " + numCooperativa);

            log("EFETUANDO CARGA DADOS LEGADO - ALTERAÇÃO");
            getProcessarCargaMensagensDelegate().gerarCargaDadosLegadoAlteracao(numCooperativa);
            log("CARGA DADOS LEGADO EFETUADA COM SUCESSO");
        } catch (ComumException e) {
            return erro(e.getMessage());
        }

        log(Constantes.STR_SEPARACAO);
        log("************ FIM EXECUCAO STEP GERAR CARGA MENSAGENS LEGADO ****************");
        log(Constantes.STR_SEPARACAO);

        return sucesso();
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return
     */
    private ProcessarCargaMensagensDelegate getProcessarCargaMensagensDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getProcessarCargaMensagensDelegate();
    }

    /**
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(GerarCargaAlteracaoMensagensLegadoStepServicoEJB.class);
    }

    /**
     * @param str
     */
    private void log(String str) {
        getLogger().debug(getMensagem(str));
    }

    /**
     * @param str
     * @return Sting
     */
    private String getMensagem(String str) {
        return Constantes.STEP + "CodExecucaoAgendamentoFluxo: " + codExecucaoAgendamento + " CodInstanciaFluxo: " + codInstanciaFluxo + Constantes.STR_SEPARACAO_2 + str;
    }
}
