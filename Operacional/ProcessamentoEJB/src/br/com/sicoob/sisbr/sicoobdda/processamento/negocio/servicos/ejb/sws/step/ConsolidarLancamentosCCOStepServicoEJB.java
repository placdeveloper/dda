/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         ConsolidarLancamentosCCOStepServicoEJB.java
 * Data Criação:    Jan 19, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioTarifasCIPDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * ConsolidarLancamentosCCOStepServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Remote(StepServico.class)
public class ConsolidarLancamentosCCOStepServicoEJB extends StepSicoobServico {

    /**
     * 
     */
    public ConsolidarLancamentosCCOStepServicoEJB() {
        super();
    }

    /**
     * @return ISicoobLogger
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ConsolidarLancamentosCCOStepServicoEJB.class);
    }

    /**
     * Método responsável por logar as mensagens
     * 
     * @param mensagem
     * @return
     */
    private void log(String mensagem) {
        getLogger().debug(Constantes.STEP + Constantes.STR_SEPARACAO_2 + mensagem + Constantes.STR_SEPARACAO_2);
    }

    /**
     * @return RateioTarifasCIPDelegate
     */
    private RateioTarifasCIPDelegate getConsolidarLancamentosCCODelegate() {
        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        log("Executar " + ConsolidarLancamentosCCOStepServicoEJB.class.getSimpleName());

        try {
            getConsolidarLancamentosCCODelegate().consolidarLancamentosCCO();
        } catch (ComumException e) {
            return erro(e.getMessage());
        }

        log("Finalizar " + ConsolidarLancamentosCCOStepServicoEJB.class.getSimpleName());
        return sucesso();
    }

}
