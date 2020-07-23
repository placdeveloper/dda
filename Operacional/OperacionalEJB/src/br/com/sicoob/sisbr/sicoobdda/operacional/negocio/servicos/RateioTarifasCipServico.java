package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

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

/**
 * RateioTarifasCipServico
 * 
 * @author Danilo.Barros
 */
public interface RateioTarifasCipServico extends OperacionalCrudServico<RateioDDA> {

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    VisualizaRateioTarifaDto pesquisarDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    List<VisualizaRateioTarifaDto> pesquisarLancamento(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idRateioLancamento
     * @return
     * @throws ComumException VisualizaRateioTarifaDto
     * 
     */
    VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    VisualizaRateioTarifaDto detalharRateioTarifa(VisualizaRateioTarifaDto dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param consultaDto
     * @return
     * @throws BancoobException ConsultaDto<ConsultaMovimentoSicoobDDADto>
     * 
     */
    ConsultaDto<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(ConsultaDto<VisualizaRateioTarifaDto> consultaDto) throws BancoobException;

    /**
     * Método responsável por listar os tipos de situação de rateios
     * 
     * @return
     * @throws ComumException List<SituacaoRateio>
     * 
     */
    List<SituacaoRateio> listaSituacaoRateio() throws ComumException;

    /**
     * Método responsável por consolidar os eventos DDA e PCR tarifávies
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarEventosTarifaveis() throws ComumException;

    /**
     * Método responsável por obter os dados da tela de rateio
     * 
     * @return
     */
    ControleRateioDto obterDadosControleRateio() throws BancoobException;

    /**
     * Método responsável por pesquisar os eventos disponíveis para rateio
     * 
     * @param dto
     * @return
     */
    List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws BancoobException;

    /**
     * Método responsável por consolidar os quantitativos e valores dos lançamentos de débitos que serão realizados nas contas convênio das cooperativas por
     * meio do serviço do CCO.
     * 
     * @throws ComumException
     */
    void consolidarLancamentosCCO() throws ComumException;

    /**
     * Método responsável por incluir os eventos consolidados no rateio
     * 
     * @param lista
     * @return
     * @throws BancoobException
     */
    RateioDDA incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException;

    /**
     * Método responsável por remover os eventos consolidados do rateio
     * 
     * @param lista
     * @throws BancoobException
     */
    void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException;

    /**
     * Método responsável por alterar os quantitativos dos eventos consolidados e o parâmetro do desvio padrão
     * 
     * @param desvioPadrao
     * @param lista
     */
    void atualizarEventoConsolidado(BigDecimal desvioPadrao, List<EventoRateioDto> lista) throws BancoobException;

    /**
     * Método responsável por aprovar o rateio
     * 
     * @param idUsuarioAprovacao
     * @param idInstituicaoUsuarioAprovacao
     * @return
     * @throws BancoobException
     */
    RateioDDA aprovarRateio(String idUsuarioAprovacao, Integer idInstituicaoUsuarioAprovacao) throws BancoobException;

    /**
     * Método responsável por alterar a situação do rateio para "aguardando aprovação"
     * 
     * @return
     * @throws BancoobException
     */
    RateioDDA cancelarAprovacaoRateio() throws BancoobException;

    /**
     * Método responsável por efetivar os lançamentos de rateio DDA e PCR nas contas de convênio das cooperativas no CCO
     * 
     * @param RateioCreditoLancamentoCCODto
     * @return
     * @throws ComumException, ComumNegocioException
     */
    void efetivarLancamentosRateioCCO(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por atualizar a situação do rateio
     * 
     * @param idRateioDDA
     * @param codSituacaoRateio
     * @return
     * @throws ComumException, ComumNegocioException
     */
    void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param idRateioDDALanc
     * @return
     * @throws BancoobException Long
     * 
     */
    Long efetivacaoManualLancamento(Long idRateioDDALanc) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param idRateioDDALanc
     * @param numeroCooperativa
     * @param codSituacaoLancamento
     * @return
     * @throws BancoobException Long
     * 
     */
    Long transferenciaDebito(Long idRateioDDALanc, Integer numeroCooperativa, long codSituacaoLancamento) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param controleRateioDto
     * @param consultaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioEventosDisponiveis(ControleRateioDto controleRateioDto, ConsultaEventoRateioDto consultaDto, UsuarioBancoobDTO usuario)
            throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param controleRateioDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioEventosRateio(ControleRateioDto controleRateioDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioRateioLancamento(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioDadosRateio(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioRateioTarifa(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;
}
