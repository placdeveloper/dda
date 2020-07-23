/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         IncluirParametroStepServicoEJB.java
 * Data Criação:    19/09/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.AtualizaParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * IncluirParametroStepServicoEJB é responsável por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(StepServico.class)
public class IncluirParametroStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            getLogger().debug("Executando Step IncluirParametroStepServicoEJB ...");
            getAtualizaParametroDelegate().atualizarParametroInstituicao();
        } catch (ComumException e) {
            return erro(e.getMessage());
        } finally {
            getLogger().debug("Finalizando Step IncluirParametroStepServicoEJB ...");
        }
        return sucesso();
    }

    /**
     * 
     * Método responsável por
     * 
     * @return
     */
    private AtualizaParametroDelegate getAtualizaParametroDelegate() {
        return ComumFabricaDelegates.getInstance().getAtualizaParametroDelegate();
    }

    /**
     * 
     * Método responsável por efetuar a instancia do log
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(IncluirParametroStepServicoEJB.class);
    }
}
