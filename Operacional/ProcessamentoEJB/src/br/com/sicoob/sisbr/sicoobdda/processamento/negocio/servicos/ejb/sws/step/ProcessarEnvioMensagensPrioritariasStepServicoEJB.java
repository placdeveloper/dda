package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarEnvioMensagensDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * ProcessarEnvioMensagensStepServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Remote(StepServico.class)
public class ProcessarEnvioMensagensPrioritariasStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            int numAgrupamentoSteps = contexto.getParametrosStep().get("numAgrupamentoSteps").getValor();
            getProcessarEnvioMensagensDelegate().processarEnvioMensagensPrioritarias(numAgrupamentoSteps);
        } catch (ComumException e) {
            return erro(e.getMessage());
        }
        return sucesso();
    }

    /**
     * @return ProcessarEnvioMensagensDelegate
     */
    private ProcessarEnvioMensagensDelegate getProcessarEnvioMensagensDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getProcessarEnvioMensagensDelegate();
    }
}
