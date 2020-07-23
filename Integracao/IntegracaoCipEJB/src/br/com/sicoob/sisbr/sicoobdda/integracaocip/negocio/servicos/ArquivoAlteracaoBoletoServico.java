/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ArquivoAlteracaoBoletoServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface ArquivoAlteracaoBoletoServico extends IntegracaoCipServicoArquivo {

    /**
     * M�todo respons�vel por efeturar o processamento dos arquivos ADDA102RR2 atrav�s de Script SQL
     * 
     * @param idLogRecebArq id do arquivo recebido
     * @param idLogDetArqInicial par�metro inicial dos detalhes a serem processados
     * @param idLogDetArqFinal par�metro final do detalhes a serem processados
     * @return void
     * @throws ComumException
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

    /**
     * M�todo respons�vel por por efeturar o processamento dos arquivos ADDA102RET atrav�s de Script SQL
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    void processarRetornoAlteracaoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);
}
