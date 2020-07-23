/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         AtualizarSituacaoBoletoStepServicoEJB.java
 * Data Cria��o:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AdministrativoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AtualizarSituacaoBoletoStepServicoEJB � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Remote(StepServico.class)
public class AtualizarSituacaoBoletoStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao arg0) {
        try {
            getAdministrativoDelegate().atualizarSituacaoBoletos();
        } catch (ComumException e) {
            getLogger().erro(e, MensagemUtil.getString("falha.execucao.step", "Job Atualizar Situa��o Boleto", e.getMessage()));
            return erro("Falha na execu��o do STEP MOTIVO [ " + e.getMessage() + " ]");
        }
        return sucesso();
    }

    /**
     * M�todo respons�vel por obter o logger
     * 
     * @return
     */
    public static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ProcessarDetalheArquivoRecebidoCIPStepServicoEJB.class);
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @return
     */
    private AdministrativoDelegate getAdministrativoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getAdministrativoDelegate();
    }
}
