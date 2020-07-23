/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumDao.java
 * Data Cria��o:    06/10/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ComumDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por recuperar a data e hora corrente do banco de dados.
     * 
     * @return DateTimeDB
     */
    DateTimeDB obterDataHoraDataBase();

    /**
     * M�todo respons�vel por recuperar a data de refer�ncia da grade hor�ria aberta para o Tipo de Mensagem informada.
     * 
     * @param codTipoMensagem
     * @throws ComumException
     */
    DateTimeDB obterDataReferenciaGradeAberta(String codTipoMensagem) throws ComumException;

}
