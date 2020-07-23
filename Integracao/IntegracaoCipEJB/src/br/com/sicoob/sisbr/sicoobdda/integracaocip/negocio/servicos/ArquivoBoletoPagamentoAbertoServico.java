/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ArquivoBoletoPagamentoAbertoServico.java
 * Data Cria��o:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

/**
 * ArquivoBoletoPagamentoAbertoServico � respons�vel por
 * 
 * @author felipe.rosa
 */
public interface ArquivoBoletoPagamentoAbertoServico extends IntegracaoCipServicoArquivo {

    /**
     * M�todo respons�vel por processar os registro de retorno do vindos no arquivo de distribui��o de boletos em aberto para nova ades�o de pagador eletr�nico.
     * 
     * @param idLogArquivoRecebido id do arquivo gravado na logRecebimentoArquivoDDA
     * @param idLogDetArqInicial in�cio do range dos registros a serem processados
     * @param idLogDetArqFinal final do range dos registros a serem processados void sem retorno na chamada do m�todo
     * 
     */
    void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

}
