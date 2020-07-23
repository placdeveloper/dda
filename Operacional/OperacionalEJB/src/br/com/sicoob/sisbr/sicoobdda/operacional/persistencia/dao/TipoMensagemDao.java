/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         TipoGradeHorariaDDADao.java
 * Data Criação:    Ago 18, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;

/**
 * TipoGradeHorariaDDADao
 * 
 * @author samuell.ramos
 */
public interface TipoMensagemDao extends OperacionalCrudDaoIF<TipoMensagemDDA> {

    /**
     * @return
     * @throws ComumException List<TipoMensagemDDA>
     * 
     */
    List<TipoMensagemDDA> listarTipoMensagem() throws ComumException;

    /**
     * @return
     * @throws ComumException List<CategoriaMensagemDDA>
     * 
     */
    List<CategoriaMensagemDDA> listarCategoriaMensagemDDA() throws ComumException;

    /**
     * @return
     * @throws ComumException List<TipoMensagemDDA>
     * 
     */
    List<TipoMensagemDDA> listarTipoMensagemPorCategoria() throws ComumException;

    /**
     * @param codTipoMensagem
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isExisteEmTipoMensagem(String codTipoMensagem) throws ComumException;

    /**
     * @param codTipoMensagem
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean isVinculadoArqCorrespondente(String codTipoMensagem) throws ComumException;

}