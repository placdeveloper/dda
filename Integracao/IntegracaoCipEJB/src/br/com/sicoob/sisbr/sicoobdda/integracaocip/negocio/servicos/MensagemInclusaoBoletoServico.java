/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemInclusaoBoletoServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface MensagemInclusaoBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * M�todo respons�vel por efetuar a grava��o dos boletos na base de dados
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial identificador do primeiro par�metro
     * @param idLogDetArqFinal identificador do �ltimo par�metro
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
