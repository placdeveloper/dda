/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         RequisitarArquivoServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * RequisitarArquivoServico
 * 
 * @author Rafael.Silva
 */
public interface RequisitarArquivoServico extends OperacionalServico {

    /**
     * M�todo respons�vel por solicitar a CIP o arquivo de carga inicial.
     * 
     * @throws BancoobException void
     * 
     */
    public void processarRequisicaoArquivoCargaInicial() throws BancoobException;

    /**
     * M�todo respons�vel por solicitar arquivo de invent�rio de benefici�rios da cip com todas as situa��es.
     * 
     * @throws BancoobException void
     * 
     */
    public void processarRequisicaoArquivoInventario() throws BancoobException;

}
