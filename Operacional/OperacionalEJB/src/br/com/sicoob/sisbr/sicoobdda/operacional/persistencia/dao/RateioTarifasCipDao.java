/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         RateioTarifasCipDaox.java
 * Data Cria��o:    Jan 19, 2018
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por Listar os tipos de situa��es de rateio.
     * 
     * @return
     * @throws ComumException List<SituacaoRateio>
     * 
     */
    public List<SituacaoRateio> listaSituacaoRateio() throws ComumException;

    /**
     * M�todo respons�vel por consolidar os eventos DDA e PCR tarif�vies
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarEventosTarifaveis() throws ComumException;

    /**
     * M�todo respons�vel por obter o rateio atual com situa��o "aguardando aprova��o" ou "aprovado para efetiva��o"
     * 
     * @return
     * @throws ComumException
     */
    RateioDDA obterRateioAtual() throws ComumException;

    /**
     * M�todo respons�vel por pesquisar os eventos dispon�veis para rateio
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws ComumException;

    /**
     * M�todo respons�vel por listar os eventos do rateio informado
     * 
     * @param idRateio
     * @return
     * @throws ComumException
     */
    List<EventoRateioDto> listarEventosRateio(Long idRateio) throws ComumException;

    /**
     * M�todo respons�vel por consolidar os quantitativos e valores dos lan�amentos de d�bitos que ser�o realizados nas contas conv�nio das cooperativas por
     * meio do servi�o do CCO.
     * 
     * @param
     * @return
     * @throws ComumException
     */
    void consolidarLancamentosCCO() throws ComumException;

    /**
     * M�todo respons�vel por incluir os eventos consolidados no rateio
     * 
     * @param lista
     * @param idRateio
     * @param idInstituicaoUsuarioInclusaoRateio
     * @param idUsuarioInclusaoRateio
     */
    void incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista, Long idRateio, Integer idInstituicaoUsuarioInclusaoRateio, String idUsuarioInclusaoRateio)
            throws ComumException;

    /**
     * M�todo respons�vel por remover os eventos consolidados do rateio
     * 
     * @param lista
     */
    void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws ComumException;

    /**
     * M�todo respons�vel por alterar os quantitativos dos eventos consolidados
     * 
     * @param lista
     * @throws ComumException
     */
    void atualizarEventoConsolidado(List<EventoRateioDto> lista) throws ComumException;

    /**
     * M�todo respons�vel por listar os lan�amentos de rateio
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
     * M�todo respons�vel por atualizar os lan�amentos com o retorno do CCO
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
     * M�todo respons�vel por verificar se existe algum rateio em processamento
     * 
     * @return
     * @throws ComumException
     */
    boolean existeRateioProcessando() throws ComumException;

    /**
     * M�todo respons�vel por verificar se o rateio possui evento consolidado
     * 
     * @param idRateio
     * @return
     * @throws ComumException
     */
    boolean possuiListaEventoConsolidado(Long idRateio) throws ComumException;

    /**
     * M�todo respons�vel por atualizar a situa��o do rateio
     * 
     * @param idRateioDDA
     * @param codSituacaoRateio
     * @return
     * @throws ComumException
     */
    void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param visualizaRateioTarifaDto
     * @return
     * @throws BancoobException List<VisualizaRateioTarifaDto>
     * 
     */
    public List<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException;

    /**
     * M�todo respons�vel por
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
     * M�todo respons�vel por
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