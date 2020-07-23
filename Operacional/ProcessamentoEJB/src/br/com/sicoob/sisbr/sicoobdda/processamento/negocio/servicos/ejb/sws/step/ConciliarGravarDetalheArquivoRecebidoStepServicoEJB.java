/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step
 * Arquivo:         ConciliarGravarDetalheArquivoRecebidoStepServicoEJB.java
 * Data Criação:    Jan 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * ConciliarGravarDetalheArquivoRecebidoStepServicoEJB
 * 
 * @author Adriano.Pinheiro
 */
@Stateless
@Remote(StepServico.class)
public class ConciliarGravarDetalheArquivoRecebidoStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            long idArquivoRecebido = contexto.getParametrosStep().get("idArquivoRecebido").getValor();
            getLogger().debug("**** Preparando Step para execução. PROCESSO: [CONCILIAÇÃO DE TOTAL DE DETALHES] IDARQUIVO: " + idArquivoRecebido);

            getArquivoRecebidoCIPProcessadorServicoDelegate().conciliarDetalhesGravados(idArquivoRecebido);
            return sucesso();
        } catch (BancoobException e) {
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
        return SicoobLoggerPadrao.getInstance(GravarDetalheArquivoRecebidoCIPStepServicoEJB.class);
    }
}
