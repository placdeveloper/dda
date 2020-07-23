package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;

/**
 * RateioDDALancamentoDao � respons�vel por
 * 
 * @author rodrigo.neri
 */
public interface RateioDDALancamentoDao extends OperacionalCrudDaoIF<RateioDDALancamento> {

    /**
     * M�todo respons�vel por
     * 
     * @param codSituacaoRateioLancamento
     * @return
     * @throws ComumException SituacaoRateioLancamento
     * 
     */
    SituacaoRateioLancamento obterSituacaoRateioLancamento(Long codSituacaoRateioLancamento) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException List<SituacaoRateioLancamento>
     * 
     */
    List<SituacaoRateioLancamento> listarSituacaoRateioLancamento() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idLancamento
     * @return
     * @throws ComumException RateioDDALancamento
     * 
     */
    RateioDDALancamento obterLancamento(Long idLancamento) throws ComumException;
    
    /**
     * @param idLancamento
     * @return
     * @throws ComumException
     */
    Long obterSituacaoLancamento(Long idLancamento) throws ComumException;

    /**
     * M�todo respons�vel por listar os lan�amentos das tarifas
     * 
     * @param idRateioDDALancamento
     * @return
     * @throws ComumException
     */
    List<LancamentosTarifasDDADto> listarLancamentosTarifas(Long idRateioDDALancamento) throws ComumException;

    /**
     * M�todo respons�vel por listar as tarifas DDA pagas
     * 
     * @param dto
     * @return
     * @throws ComumException
     */
    List<ConsultaTarifasDDAPagasDto> listarTarifasDDAPagas(ConsultaDto<ConsultaTarifasDDAPagasDto> dto) throws ComumException;

    /**
     * M�todo respons�vel por pesquisar os movimentos de tarifas
     * 
     * @param consultaDto
     * @return
     * @throws ComumException
     */
    List<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaMovimentoSicoobDDADto consultaDto) throws ComumException;

    /**
     * M�todo respons�vel por pesquisar os movimentos de tarifas
     * 
     * @param consultaMovimentoSicoobDDADto
     * @param pagina
     * @param tamanhoPagina
     * @return
     * @throws ComumException
     */
    List<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto, Integer pagina, Integer tamanhoPagina)
            throws ComumException;

    /**
     * M�todo respons�vel por realizar o count dos movimentos de tarifas
     * 
     * @param consultaMovimentoSicoobDDADto
     * @return
     * @throws ComumException
     */
    Integer countMovimento(ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto) throws ComumException;


}
