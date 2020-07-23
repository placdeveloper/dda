/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         MensagemBaixaEfetivaDao.java
 * Data Cria��o:    Jul 24, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;

/**
 * MensagemBaixaEfetivaDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface MensagemBaixaEfetivaDao extends IntegracaoCipCrudDaoIF<MensagemDDABaixaEfetiva> {

    /**
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void gravarBaixaADDA118RR2(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * M�todo respons�vel por atualizar o numRef e numSeq da MensagemDDABaixaEfetiva e retorna-la.
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDABaixaEfetiva
     * 
     */
    MensagemDDABaixaEfetiva obterMensagemDDABaixaEfetivaAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * @param idLogEnvioArquivoDDA
     * @return
     * @throws ComumException List<MensagemDDABaixaEfetiva>
     * 
     */
    List<MensagemDDABaixaEfetiva> listarMensagemDDABaixaEfetiva(Long idLogEnvioArquivoDDA) throws ComumException;
}
