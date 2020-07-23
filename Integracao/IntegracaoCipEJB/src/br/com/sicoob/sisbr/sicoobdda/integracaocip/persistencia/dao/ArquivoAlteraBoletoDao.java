/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ArquivoAlterarBoletoDao.java
 * Data Criação:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoAlterarBoletoDao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ArquivoAlteraBoletoDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por executar a alteração dos boletos através de Script
     * 
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial parâmetro nicial do
     * @param idLogDetArqFinal
     * @throws ComumException void
     * 
     */
    void alterarADDA102RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

    /**
     * Método responsável por executar a alteração dos boletos através de Script
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    void alterarADDA102RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal);

}
