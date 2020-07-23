/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         MensagemBaixaOperacionalDao.java
 * Data Cria��o:    Jul 24, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;

/**
 * MensagemBaixaOperacionalDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface MensagemBaixaOperacionalDao extends IntegracaoCipCrudDaoIF<MensagemDDABaixaOperacional> {

    /**
     * M�todo respons�vel por gravar os registros de BaixaOperacional do XML do arquivo na base de dados
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial in�cio do range para grava��o
     * @param idLogDetFinal final do range para grava��o void m�todo sem retorno
     * 
     */
    void gravarBaixaADDA108RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetFinal);
    
    /**
     * M�todo respons�vel por gravar os registros de BaixaOperacional do XML do arquivo na base de dados
     *  
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial in�cio do range para grava��o
     * @param idLogDetFinal final do range para grava��o void m�todo sem retorno
     * 
     */
    void gravarBaixaADDA108RET(long idLogRecebArq, long idLogDetArqInicial, long idLogDetFinal);

    /**
     * M�todo respons�vel por atualizar o numRef e numSeq da MensagemDDABaixaOperacional e retorna-la.
     * 
     * @param idMensagem
     * @return MensagemDDABaixaOperacional
     * 
     */
    MensagemDDABaixaOperacional obterMensagemDDABaixaOperacionalAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * M�todo respons�vel por M�todo respons�vel por obter a Lista de MensagemDDABaixaOperacional quando e enviado por Arquivo ADDA108 e ADDA114
     * 
     * CodSituacaoBoleto e utilizado quando for uma baixaOperacional (ADDA108), pois pode vim mais de um boleto
     * 
     * @param idLogEnvioArquivoDDA
     * @return
     * @throws ComumException List<MensagemDDABaixaOperacional>
     * 
     */
    List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idLogEnvioArquivoDDA
     * @return List<MensagemDDABaixaOperacional>
     */
    List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacionalContingencia(Long idLogEnvioArquivoDDA);

    /**
     * M�todo respons�vel por
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    void gravarBaixaADDA114RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);
}
