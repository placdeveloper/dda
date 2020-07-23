/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

/**
 * ArquivoConsultaBoletoPagamentoServico é responsável por
 * 
 * @author George.Santos
 */
public interface ArquivoConsultaBoletoPagamentoServico extends IntegracaoCipServicoArquivo {

    /**
     * Método responsável por executar o processamento de registro de arquivo com resultado de consulta a boleto de pagamento
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetRecebimentoInicial identificador inicial do range de registros para processamento
     * @param idLogDetRecebimentoFinal identificador final do range de registros para processamento
     * 
     */
    void processarArquivoRetornoConsultaBoletoDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);
}
