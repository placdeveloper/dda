/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         CadastrarMensagensCargaBoletoStepServicoEJB.java
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
 * CadastrarMensagensCargaEspecificasStepServicoEJB
 * 
 */
@Stateless
@Remote(StepServico.class)
public class CadastrarMensagensCargaEspecificasStepServicoEJB extends StepSicoobServico {

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

        log(Constantes.STEP + Constantes.STR_SEPARACAO_2 + "CADASTRAR MENSAGENS ESPECIFICAS");
        log(Constantes.STR_SEPARACAO);
        log("************ INICIO EXECUCAO STEP CADASTRAR MENSAGENS ESPECIFICAS *************");
        log(Constantes.STR_SEPARACAO);

        try {
            Integer numCooperativa = contexto.getParametrosStep().get("numCooperativa").getValor();
            Integer nrAgrupamento = contexto.getParametrosStep().get("nrAgrupamento").getValor();
            String codTipoOperacao = contexto.getParametrosStep().get("codTipoOperacao").getValor();
            log("Parâmetros - numCooperativa: " + numCooperativa);
            log("Parâmetros - nrAgrupamento: " + nrAgrupamento);
            log("Parâmetros - codTipoOperacao: " + codTipoOperacao);
            log("EFETUANDO CADASTRO DE MENSAGENS ESPECIFICAS");
            getProcessarCargaMensagensDelegate().processarCadastroMensagens(numCooperativa, nrAgrupamento, codTipoOperacao);
            log("CADASTRO DE MENSAGENS ESPECIFICAS EFETUADO COM SUCESSO");
        } catch (ComumException e) {
            return erro(e.getMessage());
        }

        log(Constantes.STR_SEPARACAO);
        log("************ FIM EXECUCAO STEP CADASTRAR MENSAGENS ESPECIFICAS *************");
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
        return SicoobLoggerPadrao.getInstance(CadastrarMensagensCargaEspecificasStepServicoEJB.class);
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
