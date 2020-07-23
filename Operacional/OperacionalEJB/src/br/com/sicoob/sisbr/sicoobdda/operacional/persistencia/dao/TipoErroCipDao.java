/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         TipoErroCipDao.java
 * Data Cria��o:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;

/**
 * TipoErroCipDao � respons�vel por 
 * 
 * @author Felipe.Rosa
 */
public interface TipoErroCipDao extends OperacionalCrudDaoIF<TipoErroCip> {

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoErro
     * @return
     * @throws OperacionalNoResultException TipoErroCip
     * 
     */
    TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNoResultException;

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoErro
     * @return Boolean
     * 
     */
    Boolean existeMensagemVinculoTipoErro(String codTipoErro);

}
