/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ArquivoAlteracaoBoletoServico é responsável por
 * 
 * @author George.Santos
 */
public interface ArquivoAlteracaoBoletoServico extends IntegracaoCipServicoArquivo {

    /**
     * Método responsável por efeturar o processamento dos arquivos ADDA102RR2 através de Script SQL
     * 
     * @param idLogRecebArq id do arquivo recebido
     * @param idLogDetArqInicial parâmetro inicial dos detalhes a serem processados
     * @param idLogDetArqFinal parâmetro final do detalhes a serem processados
     * @return void
     * @throws ComumException
     * 
     */
    void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

    /**
     * Método responsável por por efeturar o processamento dos arquivos ADDA102RET através de Script SQL
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    void processarRetornoAlteracaoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);
}
