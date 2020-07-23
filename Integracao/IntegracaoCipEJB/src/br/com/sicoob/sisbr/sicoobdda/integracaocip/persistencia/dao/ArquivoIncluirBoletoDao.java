/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ArquivoAlterarBoletoDao.java
 * Data Cria��o:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoAlterarBoletoDao
 * 
 * @author Danilo.Barros
 */
public interface ArquivoIncluirBoletoDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por executar a inclus�o dos boletos atrav�s de Script
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial par�metro nicial do
     * @param idLogDetArqFinal void
     * 
     */
    void incluirADDA101RET(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * @param idLogRecebArq
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void incluirADDA101RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * M�todo respons�vel por gravar os dados do boleto selecionando dentro do XML do arquivo ADDA106 gravado na tabela LogDetRecebimentoArquivo.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void inluirADDA106(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * M�todo respons�vel por
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void incluirADDA127(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

}
