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
 * RegistrarMensagensTabelaTmpStepServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(StepServico.class)
public class RegistrarMensagensTabelaTmpStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            Integer qtdParametroMensagensEnviadasPorRajada = contexto.getParametrosStep().get("qtdParametroMensagensEnviadasPorRajada").getValor();
            Integer qtdMaxRegistrosPorStep = contexto.getParametrosStep().get("qtdMaxRegistrosPorStep").getValor();
            Integer qtdParametroNumPrioridadeMsg = contexto.getParametrosStep().get("qtdParametroNumPrioridadeMsg").getValor();
            getProcessarEnvioMensagensDelegate().registrarMensagensTabelaTmp(qtdParametroMensagensEnviadasPorRajada, qtdMaxRegistrosPorStep, qtdParametroNumPrioridadeMsg);
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
