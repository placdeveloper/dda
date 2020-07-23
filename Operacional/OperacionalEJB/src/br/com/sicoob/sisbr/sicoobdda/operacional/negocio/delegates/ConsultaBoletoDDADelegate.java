package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ConsultaBoletoDDADelegate é responsável por
 * 
 * @author Rodrigo.Neri
 */
@SuppressWarnings("rawtypes")
public class ConsultaBoletoDDADelegate extends OperacionalCrudDelegate {

    private ConsultaBoletoDDAServico consultaBoletoDDAServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public ConsultaBoletoDDAServico localizarServico() {
        if (consultaBoletoDDAServico == null) {
            consultaBoletoDDAServico = OperacionalServiceLocator.getInstance().localizarConsultaBoletoDDAServico();
        }

        return consultaBoletoDDAServico;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterBoletoParaPagamento(java.lang.String, java.util.Date,
     *      java.lang.Short, java.lang.Integer, java.lang.Integer, java.lang.Long, java.math.BigDecimal, java.lang.Long, boolean)
     */
    public ConsultaBoletoDDADto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException {
        return localizarServico().obterBoletoParaPagamento(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente, valorPagamento,
                numIdentificadorBoletoCIP, isAgendamentoPagamento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long)
     */
    public ConsultaBoletoDDADto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws ComumNegocioException, ComumException {
        return localizarServico().obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente, numIdentificadorBoletoCIP);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#boletoEstaNaCIP(java.lang.String)
     */
    public Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumException, ComumNegocioException {
        return localizarServico().boletoEstaNaCIP(numCodigoBarra);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#isParametroDDAAtivo()
     */
    public Boolean isParametroDDAAtivo() throws ComumNegocioException, ComumException {
        return localizarServico().isParametroDDAAtivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarBoletoDDAPorContaCorrente(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto)
     */
    public List<BoletoDDADto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteDto dto) throws ComumNegocioException, ComumException {
        return localizarServico().listarBoletoDDAPorContaCorrente(dto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#isConsultaBoletoAtivo()
     */
    public Boolean isConsultaBoletoAtivo() throws ComumException {
        return localizarServico().isConsultaBoletoAtivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarSituacaoBoleto()
     */
    public List<SituacaoBoleto> listarSituacaoBoleto() {
        return localizarServico().listarSituacaoBoleto();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarBoletoTerceiroAutorizadoDDA(java.lang.String,
     *      java.lang.String)
     */
    public List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP, String numCpfCnpjSolicitante)
            throws ComumNegocioException, ComumException {
        return localizarServico().listarBoletoTerceiroAutorizadoDDA(numIdentificadorBoletoCIP, numCpfCnpjSolicitante);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarSituacaoBoletoDDA()
     */
    public List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException {
        return localizarServico().listarSituacaoBoletoDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#consultarBoletoCIP(java.lang.String, java.lang.Short)
     */
    public void consultarBoletoCIP(String numCodigoBarra, Short idCanal, Short codSituacaoBoleto) throws ComumException, ComumNegocioException {
        localizarServico().consultarBoletoCIP(numCodigoBarra, idCanal, codSituacaoBoleto);
    }

    /**
     * Método responsável por
     * 
     * @param numBanco
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    public String obterNomeBanco(Short numBanco) throws ComumNegocioException, ComumException {
        return localizarServico().obterNomeBanco(numBanco);
    }

    /**
     * Método responsável por
     * 
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     * @return BoletoDDA
     * @throws ComumException
     * @throws ComumNegocioException
     * 
     */
    public BoletoDDA obterBoletoDDA(String numCodigoBarra, Integer codSituacaoBoleto) throws ComumException, ComumNegocioException {
        return localizarServico().obterBoletoDDA(numCodigoBarra, codSituacaoBoleto);
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException Boolean
     * 
     */
    public Boolean isConsultaBoletoCIPHabilitadoPorInstituicao(Integer idInstituicao) throws ComumException {
        return localizarServico().isConsultaBoletoCIPHabilitadoPorInstituicao(idInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioDetalheBoleto(BoletoDDA boletoDDA, String numCodigoBarra, Integer codSituacaoBoleto, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return localizarServico().configurarRelatorioDetalheBoleto(boletoDDA, numCodigoBarra, codSituacaoBoleto, usuario);
    }

    /**
     * Método responsável por
     * 
     * @param pesquisaBoletoDDADto
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioConsultaBoleto(PesquisaBoletoDDADto pesquisaBoletoDDADto, UsuarioBancoobDTO usuario) throws BancoobException {
        return localizarServico().configurarRelatorioConsultaBoleto(pesquisaBoletoDDADto, usuario);
    }

    /**
     * Método responsável por
     * 
     * @param listaHistoricoMensagem106
     * @param usuario
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioHistoricoMensagem(ListaHistoricoMensagem106Dto listaHistoricoMensagem106, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return localizarServico().configurarRelatorioHistoricoMensagem(listaHistoricoMensagem106, usuario);
    }
}
