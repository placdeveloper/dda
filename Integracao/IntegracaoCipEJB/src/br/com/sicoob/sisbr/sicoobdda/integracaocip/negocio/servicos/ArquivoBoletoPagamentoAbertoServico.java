/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ArquivoBoletoPagamentoAbertoServico.java
 * Data Criação:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

/**
 * ArquivoBoletoPagamentoAbertoServico é responsável por
 * 
 * @author felipe.rosa
 */
public interface ArquivoBoletoPagamentoAbertoServico extends IntegracaoCipServicoArquivo {

    /**
     * Método responsável por processar os registro de retorno do vindos no arquivo de distribuição de boletos em aberto para nova adesão de pagador eletrônico.
     * 
     * @param idLogArquivoRecebido id do arquivo gravado na logRecebimentoArquivoDDA
     * @param idLogDetArqInicial início do range dos registros a serem processados
     * @param idLogDetArqFinal final do range dos registros a serem processados void sem retorno na chamada do método
     * 
     */
    void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

}
