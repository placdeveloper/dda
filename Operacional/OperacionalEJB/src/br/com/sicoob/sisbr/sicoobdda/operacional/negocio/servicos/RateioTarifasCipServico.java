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
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    VisualizaRateioTarifaDto pesquisarDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    List<VisualizaRateioTarifaDto> pesquisarLancamento(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idRateioLancamento
     * @return
     * @throws ComumException VisualizaRateioTarifaDto
     * 
     */
    VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException VisualizaRateioTarifaDto
     * 
     */
    VisualizaRateioTarifaDto detalharRateioTarifa(VisualizaRateioTarifaDto dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param consultaDto
     * @return
     * @throws BancoobException ConsultaDto<ConsultaMovimentoSicoobDDADto>
     * 
     */
    ConsultaDto<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(ConsultaDto<VisualizaRateioTarifaDto> consultaDto) throws BancoobException;

    /**
     * M�todo respons�vel por listar os tipos de situa��o de rateios
     * 
     * @return
     * @throws ComumException List<SituacaoRateio>
     * 
     */
    List<SituacaoRateio> listaSituacaoRateio() throws ComumException;

    /**
     * M�todo respons�vel por consolidar os eventos DDA e PCR tarif�vies
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarEventosTarifaveis() throws ComumException;

    /**
     * M�todo respons�vel por obter os dados da tela de rateio
     * 
     * @return
     */
    ControleRateioDto obterDadosControleRateio() throws BancoobException;

    /**
     * M�todo respons�vel por pesquisar os eventos dispon�veis para rateio
     * 
     * @param dto
     * @return
     */
    List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws BancoobException;

    /**
     * M�todo respons�vel por consolidar os quantitativos e valores dos lan�amentos de d�bitos que ser�o realizados nas contas conv�nio das cooperativas por
     * meio do servi�o do CCO.
     * 
     * @throws ComumException
     */
    void consolidarLancamentosCCO() throws ComumException;

    /**
     * M�todo respons�vel por incluir os eventos consolidados no rateio
     * 
     * @param lista
     * @return
     * @throws BancoobException
     */
    RateioDDA incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException;

    /**
     * M�todo respons�vel por remover os eventos consolidados do rateio
     * 
     * @param lista
     * @throws BancoobException
     */
    void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException;

    /**
     * M�todo respons�vel por alterar os quantitativos dos eventos consolidados e o par�metro do desvio padr�o
     * 
     * @param desvioPadrao
     * @param lista
     */
    void atualizarEventoConsolidado(BigDecimal desvioPadrao, List<EventoRateioDto> lista) throws BancoobException;

    /**
     * M�todo respons�vel por aprovar o rateio
     * 
     * @param idUsuarioAprovacao
     * @param idInstituicaoUsuarioAprovacao
     * @return
     * @throws BancoobException
     */
    RateioDDA aprovarRateio(String idUsuarioAprovacao, Integer idInstituicaoUsuarioAprovacao) throws BancoobException;

    /**
     * M�todo respons�vel por alterar a situa��o do rateio para "aguardando aprova��o"
     * 
     * @return
     * @throws BancoobException
     */
    RateioDDA cancelarAprovacaoRateio() throws BancoobException;

    /**
     * M�todo respons�vel por efetivar os lan�amentos de rateio DDA e PCR nas contas de conv�nio das cooperativas no CCO
     * 
     * @param RateioCreditoLancamentoCCODto
     * @return
     * @throws ComumException, ComumNegocioException
     */
    void efetivarLancamentosRateioCCO(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por atualizar a situa��o do rateio
     * 
     * @param idRateioDDA
     * @param codSituacaoRateio
     * @return
     * @throws ComumException, ComumNegocioException
     */
    void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param idRateioDDALanc
     * @return
     * @throws BancoobException Long
     * 
     */
    Long efetivacaoManualLancamento(Long idRateioDDALanc) throws BancoobException;

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
    Long transferenciaDebito(Long idRateioDDALanc, Integer numeroCooperativa, long codSituacaoLancamento) throws BancoobException;

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
    ConfigurarRelatorioJasper configurarRelatorioEventosDisponiveis(ControleRateioDto controleRateioDto, ConsultaEventoRateioDto consultaDto, UsuarioBancoobDTO usuario)
            throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param controleRateioDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioEventosRateio(ControleRateioDto controleRateioDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioRateioLancamento(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioDadosRateio(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioRateioTarifa(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException;
}
