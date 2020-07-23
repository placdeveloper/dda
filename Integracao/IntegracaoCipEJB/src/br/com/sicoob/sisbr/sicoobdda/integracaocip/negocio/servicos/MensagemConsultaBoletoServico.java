package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemConsultarBoletoServico é responsável por
 * 
 * @author George.Santos
 */
public interface MensagemConsultaBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * Método responsável por processar a lista de XMLs dos arquivos de distribuição.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetFinal void
     * 
     */
    void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetFinal);
}
