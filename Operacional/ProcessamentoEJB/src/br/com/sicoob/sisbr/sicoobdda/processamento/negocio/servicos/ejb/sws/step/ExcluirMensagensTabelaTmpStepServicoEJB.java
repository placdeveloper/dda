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
 * ExcluirMensagensTabelaTmpStepServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Remote(StepServico.class)
public class ExcluirMensagensTabelaTmpStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            getProcessarEnvioMensagensDelegate().excluirMensagensTabelaTmp();
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
