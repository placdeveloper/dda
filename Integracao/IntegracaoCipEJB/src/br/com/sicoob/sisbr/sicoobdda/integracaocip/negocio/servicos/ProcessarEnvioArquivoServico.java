/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarCargaMensagensServico.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * ProcessarCargaMensagensServico
 * 
 * @author George.Santos
 */
public interface ProcessarEnvioArquivoServico extends IntegracaoCipServico {

    /**
     * Método responsável por gravar na Tabela DDA.LogEnvioArquivoDDA e DDA.LogDetalheEnvioArquivoDDA
     * 
     * @param codTipoMensagemDDA
     * @param idMensagemInicial
     * @param idMensagemFinal
     * @param numSequencial
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void registrarArquivo() throws ComumException;

    /**
     * Método responsável por fazer o Marshal dos arquivos recebidos,
     * 
     * Gravar o arquivo na Pasta "Pre-Enviar"
     * 
     * Atualizar a Data Hora do Arquivo da Tabela DDA.LogEnvioArquivoDDA
     * 
     * @param idLogEnvioArquivoDDA
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void gerarArquivo(Long idLogEnvioArquivoDDA) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por fazer o Marshall dos arquivos recebidos,
     * 
     * Gravar o arquivo na Pasta "ENVIAR"
     * 
     * Atualizar a Data Hora Envio da Tabela DDA.LogEnvioArquivoDDA
     * 
     * @param idLogEnvioArquivoDDA
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void postarArquivo(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * Método responsável por fazer o tratamento dos arquivos antes de enviar para cip
     * 
     * @param idLogEnvioArquivoDDA
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void tratarArquivosComErro(Long idLogEnvioArquivoDDA, Date dataMovimento) throws ComumException;

}
