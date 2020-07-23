package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;

/**
 * RateioDDALancamentoServico é responsável por
 * 
 * @author rodrigo.neri
 */
public interface RateioDDALancamentoServico extends OperacionalCrudServico<RateioDDALancamento> {

    /**
     * Método responsável por listar os lançamentos
     * 
     * @param idRateioDDALancamento
     * @return
     * @throws BancoobException
     */
    List<LancamentosTarifasDDADto> listarLancamentosTarifas(Long idRateioDDALancamento) throws BancoobException;

    /**
     * Método responsável por pesquisar os movimentos de tarifas paginado
     * 
     * @param consultaDto
     * @return
     * @throws BancoobException
     */
    ConsultaDto<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param consulta
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioTarifasDDAPagas(ConsultaTarifasDDAPagasDto consulta, UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param consulta
     * @param listaLancamentosTarifasDDADto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioLancamentosTarifas(ConsultaTarifasDDAPagasDto consulta, ListaLancamentosTarifasDDADto listaLancamentosTarifasDDADto,
            UsuarioBancoobDTO usuario) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param consultaDto
     * @param consultaTarifasDDAPagasDto
     * @param lancamentosTarifasDDADto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioMovimentoSicoobDDA(ConsultaMovimentoSicoobDDADto consultaDto, ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto,
            LancamentosTarifasDDADto lancamentosTarifasDDADto, UsuarioBancoobDTO usuario) throws BancoobException;
}
