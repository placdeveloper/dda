/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         AtualizarDataMovimentoStepServicoEJB.java
 * Data Cria��o:    27/09/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTratamentoErroCipEnum;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TratamentoPendenciaErroDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AtualizarDataMovimentoStepServicoEJB � respons�vel por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(StepServico.class)
public class AtualizarDataMovimentoStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            getLogger().debug("Executando Step AtualizarDataMovimentoStepServicoEJB ...");
            try {
                getTratamentoPendenciaErroDelegate().executarTratamentoMensagem(new TratamentoMesagemDto(TipoTratamentoErroCipEnum.ATUALIZAR_DATA_MOVIMENTO.getCodTipoTratamento()));
            } catch (ComumException e) {
                return erro(e.getMessage());
            } catch (BancoobException e) {
                return erro(e.getMessage());
            }
        } finally {
            getLogger().debug("Finalizando Step AtualizarDataMovimentoStepServicoEJB ...");
        }
        return sucesso();
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @return
     */
    private TratamentoPendenciaErroDelegate getTratamentoPendenciaErroDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTratamentoPendenciaErroDelegate();
    }

    /**
     * 
     * M�todo respons�vel por efetuar a instancia do log
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(AtualizarDataMovimentoStepServicoEJB.class);
    }
}
