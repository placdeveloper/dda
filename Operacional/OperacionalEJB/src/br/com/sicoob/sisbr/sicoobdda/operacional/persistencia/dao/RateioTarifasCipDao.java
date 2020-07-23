/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         RateioTarifasCipDaox.java
 * Data Criação:    Jan 19, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoCCODto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;

/**
 * RateioTarifasCipDao
 * 
 * @author Danilo.Barros
 */
public interface RateioTarifasCipDao extends OperacionalCrudDaoIF<RateioDDA> {

    /**
     * Método responsável por
     * 
     * @param idRateioLancamento
     * @return
     * @throws ComumException VisualizaRateioTarifaDto
     * 
     */
    public VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws ComumException;

    /**
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    public List<VisualizaRateioTarifaDto> listarDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException VisualizaRateioTarifaDto
     * 
     */
    public List<VisualizaRateioTarifaDto> obterTotalDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * @param dto
     * @return
     * @throws ComumException List<VisualizaRateioTarifaDto>
     * 
     */
    public List<VisualizaRateioTarifaDto> listarDadosLancamento(VisualizaRateioTarifaDto dto) throws ComumException;

    /**
     * Método responsável por Listar os tipos de situações de rateio.
     * 
     * @return
     * @throws ComumException List<SituacaoRateio>
     * 
     */
    public List<SituacaoRateio> listaSituacaoRateio() throws ComumException;

    /**
     * Método responsável por consolidar os eventos DDA e PCR tarifávies
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarEventosTarifaveis() throws ComumException;

    /**
     * Método responsável por obter o rateio atual com situação "aguardando aprovação" ou "aprovado para efetivação"
     * 
     * @return
     * @throws ComumException
     */
    RateioDDA obterRateioAtual() throws ComumException;

    /**
     * Método responsável por pesquisar os eventos disponíveis para rateio
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws ComumException;

    /**
     * Método responsável por listar os eventos do rateio informado
     * 
     * @param idRateio
     * @return
     * @throws ComumException
     */
    List<EventoRateioDto> listarEventosRateio(Long idRateio) throws ComumException;

    /**
     * Método responsável por consolidar os quantitativos e valores dos lançamentos de débitos que serão realizados nas contas convênio das cooperativas por
     * meio do serviço do CCO.
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarLancamentosCCO() throws ComumException;

    /**
     * Método responsável por incluir os eventos consolidados no rateio
     * 
     * @param lista
     * @param idRateio
     * @param idInstituicaoUsuarioInclusaoRateio
     * @param idUsuarioInclusaoRateio
     */
    void incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista, Long idRateio, Integer idInstituicaoUsuarioInclusaoRateio, String idUsuarioInclusaoRateio)
            throws ComumException;

    /**
     * Método responsável por remover os eventos consolidados do rateio
     * 
     * @param lista
     */
    void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws ComumException;

    /**
     * Método responsável por alterar os quantitativos dos eventos consolidados
     * 
     * @param lista
     * @throws ComumException
     */
    void atualizarEventoConsolidado(List<EventoRateioDto> lista) throws ComumException;

    /**
     * Método responsável por listar os lançamentos de rateio
     * 
     * @param idRateioDDA
     * @param idInstituicao
     * @param idRateioDDALancamento
     * @return List<LancamentoIntegracaoDto>
     * @throws ComumException
     * 
     */
    List<LancamentoIntegracaoDto> listarLancamentosRateioCCO(Long idRateioDDA, Integer idInstituicao, Long idRateioDDALancamento) throws ComumException;

    /**
     * Método responsável por atualizar os lançamentos com o retorno do CCO
     * 
     * @param rateioCreditoLancamentoCCODto
     * @param listaLancamentosIntegracaoCCODto
     * @return
     * @throws ComumException
     * 
     */
    void atualizarLancamentosRateioCCO(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto, List<LancamentoIntegracaoCCODto> listaLancamentosIntegracaoCCODto)
            throws ComumException;

    /**
     * Método responsável por verificar se existe algum rateio em processamento
     * 
     * @return
     * @throws ComumException
     */
    boolean existeRateioProcessando() throws ComumException;

    /**
     * Método responsável por verificar se o rateio possui evento consolidado
     * 
     * @param idRateio
     * @return
     * @throws ComumException
     */
    boolean possuiListaEventoConsolidado(Long idRateio) throws ComumException;

    /**
     * Método responsável por atualizar a situação do rateio
     * 
     * @param idRateioDDA
     * @param codSituacaoRateio
     * @return
     * @throws ComumException
     */
    void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @return
     * @throws BancoobException List<VisualizaRateioTarifaDto>
     * 
     */
    public List<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @param pagina
     * @param tamanhoPagina
     * @return
     * @throws BancoobException List<VisualizaRateioTarifaDto>
     * 
     */
    public List<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(VisualizaRateioTarifaDto visualizaRateioTarifaDto, Integer pagina, Integer tamanhoPagina)
            throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @return
     * @throws BancoobException Integer
     * 
     */
    public Integer countRateioTarifaDDA(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException;
    
    /**
     * @param idRateio 
     * @return
     * @throws ComumException
     */
    public SituacaoRateioCreditoCCODto listarRateiosCreditoAtualizacaoCCO(Long idRateio) throws ComumException;

}