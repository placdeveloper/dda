/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         VerificarGradeHorariaPersonalizadaStepServicoEJB.java
 * Data Criação:    28/09/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.GradeHorariaDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * CadastrarGradeHorariaSicoobStepServicoEJB é responsável por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(StepServico.class)
public class CadastrarGradeHorariaSicoobStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            getLogger().debug("Executando Step CadastrarGradeHorariaSicoobStepServicoEJB ...");
            getGradeHorariaDelegate().cadastrarGradeHorariaSicoob();
        } catch (BancoobException e) {
            return erro(e.getMessage());
        } finally {
            getLogger().debug("Finalizando Step CadastrarGradeHorariaSicoobStepServicoEJB ...");
        }
        return sucesso();
    }

    /**
     * 
     * Método responsável por
     * 
     * @return
     */
    private GradeHorariaDelegate getGradeHorariaDelegate() {
        return OperacionalFabricaDelegates.getInstance().getGradeHorariaDelegate();
    }

    /**
     * 
     * Método responsável por efetuar a instancia do log
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(CadastrarGradeHorariaSicoobStepServicoEJB.class);
    }
}
