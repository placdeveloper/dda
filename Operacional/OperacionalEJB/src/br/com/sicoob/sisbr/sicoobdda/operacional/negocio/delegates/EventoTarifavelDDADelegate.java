package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * EventoTarifavelDelegate
 * 
 * @author Samuell.Ramos
 */
@SuppressWarnings("rawtypes")
public class EventoTarifavelDDADelegate extends OperacionalCrudDelegate {

    private EventoTarifavelDDAServico eventoTarifavelDDAServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public EventoTarifavelDDAServico localizarServico() {
        if (eventoTarifavelDDAServico == null) {
            eventoTarifavelDDAServico = OperacionalServiceLocator.getInstance().localizarEventoTarifavelDDAServico();
        }

        return eventoTarifavelDDAServico;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * @return
     * @throws ComumException
     * 
     */
    public List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException {
        return localizarServico().listarEventoTarifavelDDA();
    }

    /**
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException List<EventoTarifavelDto>
     * 
     */
    public List<EventoTarifavelDto> pesquisaEventoTarifavelDDA(Integer codEventoTarifavel, Integer codStatus) throws ComumException {
        return localizarServico().pesquisaEventoTarifavelDDA(codEventoTarifavel, codStatus);
    }

    /**
     * @param idEventoTarifavelDDATarifa
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    public EventoTarifavelDto obterEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) throws ComumException {
        return localizarServico().obterEventoTarifavelDDATarifa(idEventoTarifavelDDATarifa);
    }

    /**
     * @param eventoTarifavelDDA
     * @throws BancoobException
     * 
     */
    public String manterEventoTarifavel(EventoTarifavelDto eventoTarDto) throws BancoobException {
        return localizarServico().manterEventoTarifavel(eventoTarDto);
    }

    /**
     * Método responsável por
     * 
     * @param eventoTarDto
     * @throws BancoobException void
     * 
     */
    public void excluirEventoTarifavelDDA(EventoTarifavelDto eventoTarDto) throws BancoobException {
        localizarServico().excluirEventoTarifavelDDA(eventoTarDto);
    }

    /**
     * Método responsável por
     * 
     * @param filtroRelatorio
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioEventoTarifavel(FiltroRelatorioEventoTarifavelDto filtroRelatorio, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioEventoTarifavel(filtroRelatorio, usuario);
    }

}