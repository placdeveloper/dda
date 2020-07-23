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
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<EventoTarifavelDDA>
     * 
     */
    List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException;

    /**
     * Método responsável por obter o código e a descrição do evento tarifável
     * 
     * @return
     * @throws ComumException
     */
    List<EventoTarifavelDDA> listarEventoTarifavelDDAParcial() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException List<EventoTarifavelDto>
     * 
     */
    List<EventoTarifavelDto> pesquisaEventoTarifavelDDA(Integer codEventoTarifavel, Integer codStatus) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idEventoTarifavelDDATarifa
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    EventoTarifavelDto obterEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) throws ComumException;

    /**
     * Método responsável por
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
     * Método responsável por
     * 
     * @param idEventoTarifavelDDATarifa
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    Boolean existeRateio(Long idEventoTarifavelDDATarifa, Integer codEventoTarifavel) throws ComumException;

}