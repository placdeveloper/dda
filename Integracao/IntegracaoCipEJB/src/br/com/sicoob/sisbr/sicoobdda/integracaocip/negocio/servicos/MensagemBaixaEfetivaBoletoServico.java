package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemBaixaEfetivaBoletoServico é responsável por
 * 
 * @author George.Santos
 */
public interface MensagemBaixaEfetivaBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * Método responsável por processar arquivos de baixa efetiva por script inser/select
     * 
     * @param idLogRecebArq id do arquivo recebido para processamento
     * @param idLogDetArqInicial parâmetro inicial para processamento
     * @param idLogDetArqFinal parâmetro final para processamento
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
