/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         TratamentoMensagemContingenciaStepServicoEJB.java
 * Data Criação:    06/10/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TratamentoPendenciaErroDelegate;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * TratamentoMensagemContingenciaStepServicoEJB é responsável por
 * 
 * @author Jesliel.Rocha
 */
@Stateless
@Remote(StepServico.class)
public class TratamentoMensagemContingenciaStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            Date dataMovimento = contexto.getParametrosStep().get("dataMovimento").getValor();
            Long idMensagemInicial = contexto.getParametrosStep().get("idMensagemInicial").getValor();
            Long idMensagemFinal = contexto.getParametrosStep().get("idMensagemFinal").getValor();
            String codTipoMensagem = contexto.getParametrosStep().get("codTipoMensagem").getValor();
            int qtdRegistro = contexto.getParametrosStep().get("qtdRegistro").getValor();
            getLogger().debug("**** Preparando Step para execução. PROCESSO: [CONTINGÊNCIA] - TRATAMENTO MENSAGEM QTD: [" + qtdRegistro + "] RANGE: [" + idMensagemInicial
                    + "] A [" + idMensagemFinal + "] ");

            getTratamentoPendenciaErroDelegate().executarTratamentoMensagemContingenciaBatch(new DateTimeDB(dataMovimento.getTime()), idMensagemInicial, idMensagemFinal, codTipoMensagem);
        } catch (BancoobException e) {
            return erro("Falha na processamento da Contingência ERRO: " + e.getMessage());
        } finally {
            getLogger().debug("Finalizando Step TratamentoMensagemContingenciaStepServicoEJB ...");
        }
        return sucesso();
    }

    /**
     * 
     * Método responsável por
     * 
     * @return
     */
    private TratamentoPendenciaErroDelegate getTratamentoPendenciaErroDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTratamentoPendenciaErroDelegate();
    }

    /**
     * 
     * Método responsável por efetuar a instancia do log
     * 
     * @return ISicoobLogger
     * 
     */
    private static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(TratamentoMensagemContingenciaStepServicoEJB.class);
    }
}
