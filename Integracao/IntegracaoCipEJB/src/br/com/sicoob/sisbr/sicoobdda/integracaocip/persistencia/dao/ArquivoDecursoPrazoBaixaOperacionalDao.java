/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ArquivoAlterarBoletoDao.java
 * Data Criação:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoDecursoPrazoBaixaOperacional
 * 
 * @author Danilo.Barros
 */
public interface ArquivoDecursoPrazoBaixaOperacionalDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * @param idLogRecebArq
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @throws ComumException void
     * 
     */
    void incluirADDA117(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

}
