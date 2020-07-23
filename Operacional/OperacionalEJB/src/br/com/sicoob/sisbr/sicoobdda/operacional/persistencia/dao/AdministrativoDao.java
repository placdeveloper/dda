/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         AdministrativoDao.java
 * Data Cria��o:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * AdministrativoDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface AdministrativoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por atualizar a situa��o do boleto pelo SWS Administrativo.
     * 
     * @throws ComumException void
     * 
     */
    void atualizarSituacaoBoletos() throws ComumException;

}
