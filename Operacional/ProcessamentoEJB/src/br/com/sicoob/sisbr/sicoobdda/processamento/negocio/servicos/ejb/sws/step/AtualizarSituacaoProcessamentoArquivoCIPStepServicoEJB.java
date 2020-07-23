package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * AtualizarSituacaoProcessamentoArquivoCIPStepServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Remote(StepServico.class)
public class AtualizarSituacaoProcessamentoArquivoCIPStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            long idArquivoRecebido = contexto.getParametrosStep().get("idArquivoRecebido").getValor();
            short codSituacaoProcessamentoArquivo = contexto.getParametrosStep().get("codSituacaoProcessamentoArquivo").getValor();
            getLogger().debug(
                    "**** Preparando Step para execução. PROCESSO: [ATUALIZAR SITUACAO PROCESSAMENTO ARQUIVO] IDARQUIVO: " + idArquivoRecebido + "; CODSITUACAOPROCESSAMENTO: "
                            + codSituacaoProcessamentoArquivo);

            getArquivoRecebidoCIPProcessadorServicoDelegate().atualizarSituacaoProcessamentoArquivoRecebido(idArquivoRecebido, codSituacaoProcessamentoArquivo);
            return sucesso();
        } catch (ComumException e) {
            return erro("Falha na execução do STEP MOTIVO [ " + e.getMessage() + " ]");
        }
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoRecebidoCIPProcessadorServicoDelegate
     * 
     */
    private ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebidoCIPProcessadorServicoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ProcessarDetalheArquivoRecebidoCIPStepServicoEJB.class);
    }

}
