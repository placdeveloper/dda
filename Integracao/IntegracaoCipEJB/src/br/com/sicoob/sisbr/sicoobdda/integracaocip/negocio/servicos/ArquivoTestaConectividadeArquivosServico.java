/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         TestaConectividadeArquivosServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * TestaConectividadeArquivosServico
 * 
 * @author Rafael.Silva
 */
public interface ArquivoTestaConectividadeArquivosServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por criar e postar o arquivo de teste de conectividade com a CIP
     * 
     * @throws ComumException void
     * 
     */
    void processarTesteConectividade() throws ComumException;

    /**
     * M�todo respons�vel por processar o retorno do arquivo de teste de conectividade por arquivo CIP
     * 
     * @param nomeArquivoRecebido
     * @throws ComumException void
     * 
     */
    void processarRetornoCIPTesteConectividadePorArquivo(String nomeArquivoRecebido) throws ComumException;
}
