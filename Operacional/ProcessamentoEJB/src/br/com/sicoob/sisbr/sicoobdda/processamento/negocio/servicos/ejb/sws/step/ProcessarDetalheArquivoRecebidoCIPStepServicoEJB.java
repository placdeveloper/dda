package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.step;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.execucao.StepSicoobServico;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * ProcessarDetalheArquivoRecebidoCIPStepServicoEJB
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(StepServico.class)
public class ProcessarDetalheArquivoRecebidoCIPStepServicoEJB extends StepSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.StepServico#executar(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public RetornoExecucao executar(ContextoExecucao contexto) {
        long idArquivo = 0;
        try {
            idArquivo = contexto.getParametrosStep().get("idArquivo").getValor();
            long idLogDetRecInicial = contexto.getParametrosStep().get("idDetInicial").getValor();
            long idLogDetRecFinal = contexto.getParametrosStep().get("idDetFinal").getValor();
            TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf((String) contexto.getParametrosStep().get("idTipoInstancia").getValor());
            String codTipoMensagem = contexto.getParametrosStep().get("codTipoMensagem").getValor();
            int qtdRegTrans = contexto.getParametrosStep().get("qtdRegistroTrans").getValor();

            getLogger().debug(
                    "**** Preparando Step para execu��o. PROCESSO: [PROCESSAR DETALHES] - REGISTROS DO ARQUIVO RECEBIDO ID [" + idArquivo + "] RANGE: [" + idLogDetRecInicial + "] A ["
                            + idLogDetRecFinal + "] ");
            getArquivoRecebidoCIPProcessadorServicoDelegate().processarDetalhes(instanciaSWS, idArquivo, idLogDetRecInicial, idLogDetRecFinal, codTipoMensagem, qtdRegTrans);

            return sucesso();
        } catch (BancoobException e) {
            getLogger().erro(e, MensagemUtil.getString("falha.execucao.step", "Job Processar detalhe", e.getMessage()));
            return erro("Falha na grava��o dos detalhes do arquivo[ID: " + idArquivo + "] ERRO: " + e.getMessage());
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ArquivoRecebidoCIPProcessadorServicoDelegate
     * 
     */
    private ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebidoCIPProcessadorServicoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();
    }

    /**
     * M�todo respons�vel por obter o logger
     * 
     * @return
     */
    static ISicoobLogger getLogger() {
        return SicoobLoggerPadrao.getInstance(ProcessarDetalheArquivoRecebidoCIPStepServicoEJB.class);
    }

}
