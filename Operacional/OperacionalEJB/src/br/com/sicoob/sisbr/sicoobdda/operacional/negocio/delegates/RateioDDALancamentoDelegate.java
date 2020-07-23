package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * RateioDDALancamentoDelegate é responsável por
 * 
 * @author Rodrigo.Neri
 */
@SuppressWarnings("rawtypes")
public class RateioDDALancamentoDelegate extends OperacionalCrudDelegate {

    private RateioDDALancamentoServico rateioDDALancamentoServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public RateioDDALancamentoServico localizarServico() {
        if (rateioDDALancamentoServico == null) {
            rateioDDALancamentoServico = OperacionalServiceLocator.getInstance().localizarRateioDDALancamentoServico();
        }

        return rateioDDALancamentoServico;
    }

    /**
     * Método responsável por listar os lançamentos
     * 
     * @param idRateioDDALancamento
     * @return
     * @throws BancoobException
     */
    public List<LancamentosTarifasDDADto> listarLancamentosTarifas(Long idRateioDDALancamento) throws BancoobException {
        return localizarServico().listarLancamentosTarifas(idRateioDDALancamento);
    }

    /**
     * Método responsável por pesquisar os movimentos de tarifas paginado
     * 
     * @param consultaDto
     * @return
     * @throws BancoobException
     */
    public ConsultaDto<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto) throws BancoobException {
        return localizarServico().pesquisarMovimentoPaginado(consultaDto);
    }

    /**
     * Método responsável por
     * 
     * @param consulta
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioTarifasDDAPagas(ConsultaTarifasDDAPagasDto consulta, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioTarifasDDAPagas(consulta, usuario);
    }

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
    public ConfigurarRelatorioJasper configurarRelatorioLancamentosTarifas(ConsultaTarifasDDAPagasDto consulta, ListaLancamentosTarifasDDADto listaLancamentosTarifasDDADto,
            UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioLancamentosTarifas(consulta, listaLancamentosTarifasDDADto, usuario);
    }

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
    public ConfigurarRelatorioJasper configurarRelatorioMovimentoSicoobDDA(ConsultaMovimentoSicoobDDADto consultaDto, ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto,
            LancamentosTarifasDDADto lancamentosTarifasDDADto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioMovimentoSicoobDDA(consultaDto, consultaTarifasDDAPagasDto, lancamentosTarifasDDADto, usuario);
    }
}
