/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ArquivoAlterarBoletoDao.java
 * Data Criação:    Jul 18, 2017
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
     * Método responsável por executar a inclusão dos boletos através de Script
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial parâmetro nicial do
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
     * Método responsável por gravar os dados do boleto selecionando dentro do XML do arquivo ADDA106 gravado na tabela LogDetRecebimentoArquivo.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void inluirADDA106(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * Método responsável por
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void incluirADDA127(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

}
