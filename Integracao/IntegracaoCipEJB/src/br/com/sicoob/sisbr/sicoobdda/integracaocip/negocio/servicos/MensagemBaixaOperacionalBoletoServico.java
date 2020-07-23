package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemBaixaOperacionalBoletoServico é responsável por
 * 
 * @author George.Santos
 */
public interface MensagemBaixaOperacionalBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * Método responsável por processar os registro de retorno do vindos no arquivo de baixa operacional
     * 
     * @param idLogRecebArq id do arquivo gravado na logRecebimentoArquivoDDA
     * @param idLogDetArqInicial início do range dos registros a serem processados
     * @param idLogDetArqFinal final do range dos registros a serem processados void sem retorno na chamada do método
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
