/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * ArquivoBaixaOperacionalServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface ArquivoBaixaOperacionalServico extends IntegracaoCipServicoArquivo {

    /**
     * M�todo respons�vel por processar os registro de retorno do vindos no arquivo de baixa operacional
     * 
     * @param idLogRecebArq id do arquivo gravado na logRecebimentoArquivoDDA
     * @param idLogDetArqInicial in�cio do range dos registros a serem processados
     * @param idLogDetArqFinal final do range dos registros a serem processados void sem retorno na chamada do m�todo
     * 
     */
    void processarArquivoRetornoBaixaOperacionalDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);

}
