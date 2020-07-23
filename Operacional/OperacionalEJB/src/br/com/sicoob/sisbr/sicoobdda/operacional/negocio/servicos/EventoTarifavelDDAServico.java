package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa;

/**
 * EventoTarifavelServico
 * 
 * @author Samuell.Ramos
 */
public interface EventoTarifavelDDAServico extends OperacionalCrudServico<EventoTarifavelDDATarifa> {

    /**
     * @return
     * @throws ComumException
     */
    List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException;

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
     * @param eventoTarifavelDto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException String
     * 
     */
    String manterEventoTarifavel(EventoTarifavelDto eventoTarifavelDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param eventoTarDto
     * @throws BancoobException void
     * 
     */
    void excluirEventoTarifavelDDA(EventoTarifavelDto eventoTarDto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param filtroRelatorio
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioEventoTarifavel(FiltroRelatorioEventoTarifavelDto filtroRelatorio, UsuarioBancoobDTO usuario) throws BancoobException;

}