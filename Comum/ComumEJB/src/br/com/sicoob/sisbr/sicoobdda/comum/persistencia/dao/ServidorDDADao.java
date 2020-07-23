/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ServidorDDADao.java
 * Data Criação:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDADao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ServidorDDADao extends ComumCrudDaoIF<ServidorDDA> {

    /**
     * Método responsável por
     * 
     * @param codServidorDDA
     * @return
     * @throws ComumException ServidorDDA
     * 
     */
    ServidorDDA obterServidor(String codServidorDDA) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarAtivo() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarServidorDDA() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param filtro
     * @return List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarServidorDDA(ServidorDDA filtro) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param servidorDDA
     * @throws ComumException void
     * 
     */
    void alterarSevidorDDA(ServidorDDA servidorDDA) throws ComumException;

}
