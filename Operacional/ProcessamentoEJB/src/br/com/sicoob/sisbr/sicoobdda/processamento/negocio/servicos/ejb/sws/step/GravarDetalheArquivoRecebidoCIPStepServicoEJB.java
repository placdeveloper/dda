package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;
import br.com.sicoob.sws.api.util.JSonUtil;

/**
 * GravarDetalheArquivoRecebidoCIPStepServicoEJB
 */
@Stateless
@Remote(StepServico.class)
public class GravarDetalheArquivoRecebidoCIPStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        try {
            ArquivoProcessamentoVO arquivo = JSonUtil.fromJson((String) contexto.getParametrosStep().get("arquivo").getValor(), ArquivoProcessamentoVO.class);
            String nomeArquivo = contexto.getParametrosStep().get("nomeArquivo").getValor();
            int qtdRegistro = contexto.getParametrosStep().get("qtdRegistro").getValor();
            getLogger().debug(
                    "**** Preparando Step para execução. PROCESSO: [GRAVAÇÃO DE DETALHES] ARQUIVO: " + arquivo.getNmArquivoRecebido() + "LOCAL: " + nomeArquivo
                            + " QTD. REGISTRO POR TRANSAÇÃO: " + qtdRegistro);

            getArquivoRecebidoCIPProcessadorServicoDelegate().gravarDetalhes(arquivo, nomeArquivo, qtdRegistro);
            return sucesso();
        } catch (BancoobException e) {
            return erro(MensagemUtil.getString("falha.execucao.step", "Step GravarDetalhes", e.getMessage()));
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
