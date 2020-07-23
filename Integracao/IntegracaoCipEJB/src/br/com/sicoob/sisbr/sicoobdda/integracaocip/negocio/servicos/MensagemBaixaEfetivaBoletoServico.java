package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemBaixaEfetivaBoletoServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface MensagemBaixaEfetivaBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * M�todo respons�vel por processar arquivos de baixa efetiva por script inser/select
     * 
     * @param idLogRecebArq id do arquivo recebido para processamento
     * @param idLogDetArqInicial par�metro inicial para processamento
     * @param idLogDetArqFinal par�metro final para processamento
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
