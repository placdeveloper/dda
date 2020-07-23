package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa;

/**
 * EventoTarifavelDao
 * 
 * @author Samuell.Ramos
 */
public interface EventoTarifavelDDADao extends OperacionalCrudDaoIF<EventoTarifavelDDATarifa> {
	
	/**
	 * @return
	 * @throws ComumException
	 */
	EventoTarifavelDDA obterEventoTarifavelDDA(Integer codEventoTarifavel) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException List<EventoTarifavelDDA>
     * 
     */
    List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException;

    /**
     * M�todo respons�vel por obter o c�digo e a descri��o do evento tarif�vel
     * 
     * @return
     * @throws ComumException
     */
    List<EventoTarifavelDDA> listarEventoTarifavelDDAParcial() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException List<EventoTarifavelDto>
     * 
     */
    List<EventoTarifavelDto> pesquisaEventoTarifavelDDA(Integer codEventoTarifavel, Integer codStatus) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idEventoTarifavelDDATarifa
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    EventoTarifavelDto obterEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    public EventoTarifavelDto obterUltimoStatusEventoTarifavel(Integer codEventoTarifavel, Integer codStatus) throws ComumException;

    /**
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean existeProgramada(Integer codEventoTarifavel) throws ComumException;

    /**
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean existeVigente(Integer codEventoTarifavel) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idEventoTarifavelDDATarifa
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean existeRateio(Long idEventoTarifavelDDATarifa, Integer codEventoTarifavel) throws ComumException;

}