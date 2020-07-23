/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         RequisitarArquivoServico.java
 * Data Criação:    May 21, 2015
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
     * Método responsável por solicitar a CIP o arquivo de carga inicial.
     * 
     * @throws BancoobException void
     * 
     */
    public void processarRequisicaoArquivoCargaInicial() throws BancoobException;

    /**
     * Método responsável por solicitar arquivo de inventário de beneficiários da cip com todas as situações.
     * 
     * @throws BancoobException void
     * 
     */
    public void processarRequisicaoArquivoInventario() throws BancoobException;

}
