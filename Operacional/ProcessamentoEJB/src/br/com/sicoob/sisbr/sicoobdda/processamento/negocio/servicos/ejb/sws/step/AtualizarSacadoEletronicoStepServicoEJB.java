/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         AtualizarSacadoEletronicoStepServicoEJB.java
 * Data Criação:    09/12/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AtualizarSacadoEletronicoStepServicoEJB é responsável por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(StepServico.class)
public class AtualizarSacadoEletronicoStepServicoEJB extends StepSicoobServico {

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

        log(Constantes.STEP + Constantes.STR_SEPARACAO_2 + "ATUALIZAR SACADO ELETRONICO");
        log(Constantes.STR_SEPARACAO);
        log("************ INICIO EXECUCAO STEP ATUALIZAR SACADO ELETRONICO *************");
        log(Constantes.STR_SEPARACAO);

        try {
            Integer numCooperativa = contexto.getParametrosStep().get("numCooperativa").getValor();

            log("Parâmetro - numCooperativa: " + numCooperativa);

            log("EFETUANDO ATUALIZACAO SACADO ELETRONICO");
            getPagadorEletronicoDDADelegate().atualizarSacadoEletronico(numCooperativa);
            log("ATUALIZACAO SACADO ELETRONICO EFETUADA COM SUCESSO");
        } catch (ComumException e) {

            log("Falha no processamento: Falha[" + e.getMessage() + "]");
            return erro(e.getMessage());
        }

        log(Constantes.STR_SEPARACAO);
        log("************ FIM EXECUCAO STEP ATUALIZAR SACADO ELETRONICO *************");
        log(Constantes.STR_SEPARACAO);

        return sucesso();
    }

    /**
     * 
     * Método responsável por
     * 
     * @return
     */
    private PagadorEletronicoDDADelegate getPagadorEletronicoDDADelegate() {
        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();
    }

    /**
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(AtualizarSacadoEletronicoStepServicoEJB.class);
    }

    /**
     * @param str
     */
    private void log(String str) {
        getLogger().debug(getMensagem(str));
    }

    /**
     * @param str
     * @return String
     */
    private String getMensagem(String str) {
        return Constantes.STEP + "CodExecucaoAgendamentoFluxo: " + codExecucaoAgendamento + " CodInstanciaFluxo: " + codInstanciaFluxo + Constantes.STR_SEPARACAO_2 + str;
    }
}
