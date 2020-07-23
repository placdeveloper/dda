/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         AdministrativoDao.java
 * Data Criação:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * AdministrativoDao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface AdministrativoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por atualizar a situação do boleto pelo SWS Administrativo.
     * 
     * @throws ComumException void
     * 
     */
    void atualizarSituacaoBoletos() throws ComumException;

}
