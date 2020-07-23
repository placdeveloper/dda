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
 * ArquivoManutencaoPagadorServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface ArquivoManutencaoPagadorServico extends IntegracaoCipServicoMensagem {

    /**
     * M�todo respons�vel por processar a lista de XMLs dos arquivos de distribui��o.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @throws ComumException
     * 
     */
    void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;
}
