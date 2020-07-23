/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemConsultaPagadorServico
 * 
 * @author George.Santos
 */
public interface MensagemConsultaPagadorServico extends IntegracaoCipServicoMensagem {

    /**
     * M�todo respons�vel por processar a lista de XMLs dos arquivos de distribui��o.
     * 
     * @param idLogRecebArq
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
