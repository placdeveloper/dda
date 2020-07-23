/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;


/**
 * MensagemInclusaoBoletoServico é responsável por
 * 
 * @author George.Santos
 */
public interface MensagemInclusaoBoletoServico extends IntegracaoCipServicoMensagem {

    /**
     * Método responsável por efetuar a gravação dos boletos na base de dados
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial identificador do primeiro parâmetro
     * @param idLogDetArqFinal identificador do último parâmetro
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);
}
