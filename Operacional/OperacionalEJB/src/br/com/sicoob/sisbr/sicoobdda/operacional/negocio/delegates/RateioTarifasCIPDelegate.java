package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * RateioTarifasCIPDelegate � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
@SuppressWarnings("rawtypes")
public class RateioTarifasCIPDelegate extends OperacionalCrudDelegate {

    private RateioTarifasCipServico rateioTarifasCipServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public RateioTarifasCipServico localizarServico() {
        if (rateioTarifasCipServico == null) {
            rateioTarifasCipServico = OperacionalServiceLocator.getInstance().localizarRateioTarifasCipServico();
        }

        return rateioTarifasCipServico;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws BancoobException List<SituacaoRateio>
     * 
     */
    public List<SituacaoRateio> listaSituacaoRateio() throws BancoobException {
        return localizarServico().listaSituacaoRateio();
    }

    /**
     * M�todo respons�vel por obter os dados da tela de rateio.
     * 
     * @return
     */
    public ControleRateioDto obterDadosControleRateio() throws BancoobException {
        return localizarServico().obterDadosControleRateio();
    }

    /**
     * M�todo respons�vel por pesquisar os eventos dispon�veis para rateio
     * 
     * @param dto
     * @return
     */
    public List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws BancoobException {
        return localizarServico().pesquisarEventosDisponiveis(dto);
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#consolidarEventosTarifaveis()
     */
    public void consolidarEventosTarifaveis() throws ComumException {
        localizarServico().consolidarEventosTarifaveis();
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#consolidarLancamentosCCO()
     */
    public void consolidarLancamentosCCO() throws ComumException {
        localizarServico().consolidarLancamentosCCO();
    }

    /**
     * M�todo respons�vel por incluir os eventos consolidados no rateio
     * 
     * @param lista
     * @return
     * @throws BancoobException
     */
    public RateioDDA incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException {
        return localizarServico().incluirListaEventoConsolidadoRateio(lista);
    }

    /**
     * M�todo respons�vel por remover os eventos consolidados do rateio
     * 
     * @param lista
     * @throws BancoobException
     */
    public void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException {
        localizarServico().removerListaEventoConsolidadoRateio(lista);
    }

    /**
     * M�todo respons�vel por alterar os quantitativos dos eventos consolidados e o par�metro do desvio padr�o
     * 
     * @param desvioPadrao
     * @param lista
     */
    public void atualizarEventoConsolidado(BigDecimal desvioPadrao, List<EventoRateioDto> lista) throws BancoobException {
        localizarServico().atualizarEventoConsolidado(desvioPadrao, lista);
    }

    /**
     * M�todo respons�vel por aprovar o rateio
     * 
     * @param idUsuarioAprovacao
     * @param idInstituicaoUsuarioAprovacao
     * @return
     * @throws BancoobException
     */
    public RateioDDA aprovarRateio(String idUsuarioAprovacao, Integer idInstituicaoUsuarioAprovacao) throws BancoobException {
        return localizarServico().aprovarRateio(idUsuarioAprovacao, idInstituicaoUsuarioAprovacao);
    }

    /**
     * M�todo respons�vel por alterar a situa��o do rateio para "aguardando aprova��o"
     * 
     * @return
     * @throws BancoobException
     */
    public RateioDDA cancelarAprovacaoRateio() throws BancoobException {
        return localizarServico().cancelarAprovacaoRateio();
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#efetivarLancamentosRateioCCO(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto)
     */
    public void efetivarLancamentosRateioCCO(RateioCreditoLancamentoCCODto lancamentosRateioCCODto) throws ComumException, ComumNegocioException {
        localizarServico().efetivarLancamentosRateioCCO(lancamentosRateioCCODto);
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#atualizarSituacaoRateioCreditoCCO(java.lang.Long, java.lang.Long)
     */
    public void atualizarSituacaoRateioCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException, ComumNegocioException {
        localizarServico().atualizarSituacaoRateioCreditoCCO(idRateioDDA, codSituacaoRateio);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param consultaDto
     * @return
     * @throws BancoobException void
     * 
     */
    public ConsultaDto<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(ConsultaDto<VisualizaRateioTarifaDto> consultaDto) throws BancoobException {
        return localizarServico().pesquisarRateioTarifaDDAPaginado(consultaDto);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    public VisualizaRateioTarifaDto detalharRateioTarifas(VisualizaRateioTarifaDto dto) throws BancoobException {
        return localizarServico().detalharRateioTarifa(dto);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    public VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws BancoobException {
        return localizarServico().obterDadosLancamentoRateioDDA(idRateioLancamento);
    }
    
    /**
     * M�todo respons�vel por
     * 
     * @param idRateioDDALanc
     * @return
     * @throws BancoobException Long
     * 
     */
    public Long realizarEfetivacaoManual(Long idRateioDDALanc) throws BancoobException {
        return localizarServico().efetivacaoManualLancamento(idRateioDDALanc);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param idRateioDDALanc
     * @param numeroCooperativa
     * @param codSituacaoLancamento
     * @return
     * @throws BancoobException Long
     * 
     */
    public Long realizaTransferenciaDebito(Long idRateioDDALanc, Integer numeroCooperativa, long codSituacaoLancamento) throws BancoobException {
        return localizarServico().transferenciaDebito(idRateioDDALanc, numeroCooperativa, codSituacaoLancamento);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    public List<VisualizaRateioTarifaDto> pesquisarLancamento(VisualizaRateioTarifaDto dto) throws BancoobException {
        return localizarServico().pesquisarLancamento(dto);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    public VisualizaRateioTarifaDto pesquisarDadosRateio(VisualizaRateioTarifaDto dto) throws BancoobException {
        return localizarServico().pesquisarDadosRateio(dto);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param controleRateioDto
     * @param consultaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioEventosDisponiveis(ControleRateioDto controleRateioDto, ConsultaEventoRateioDto consultaDto, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return localizarServico().configurarRelatorioEventosDisponiveis(controleRateioDto, consultaDto, usuario);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param controleRateioDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioEventosRateio(ControleRateioDto controleRateioDto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioEventosRateio(controleRateioDto, usuario);

    }

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioRateioLancamento(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioRateioLancamento(visualizaRateioTarifaDto, usuario);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioDadosRateio(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioDadosRateio(visualizaRateioTarifaDto, usuario);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioRateioTarifa(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioRateioTarifa(visualizaRateioTarifaDto, usuario);
    }

}
