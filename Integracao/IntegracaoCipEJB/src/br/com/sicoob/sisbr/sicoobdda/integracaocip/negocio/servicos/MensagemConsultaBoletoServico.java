package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemConsultarBoletoServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface MensagemConsultaBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * M�todo respons�vel por processar a lista de XMLs dos arquivos de distribui��o.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetFinal void
     * 
     */
    void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetFinal);
}
