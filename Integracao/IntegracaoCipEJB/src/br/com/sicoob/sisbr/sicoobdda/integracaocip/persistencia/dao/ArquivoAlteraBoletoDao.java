/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ArquivoAlterarBoletoDao.java
 * Data Cria��o:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoAlterarBoletoDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ArquivoAlteraBoletoDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por executar a altera��o dos boletos atrav�s de Script
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial par�metro nicial do
     * @param idLogDetArqFinal
     * @throws ComumException void
     * 
     */
    void alterarADDA102RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

    /**
     * M�todo respons�vel por executar a altera��o dos boletos atrav�s de Script
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    void alterarADDA102RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);

}
