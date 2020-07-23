package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AgendamentoBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAContaCorrenteAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator.SicoobDDAAPIServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDAAPIDelegate é responsável por prover os serviços de BoletoDDA.
 * 
 * @author Rodrigo.Neri
 */
public class BoletoDDAAPIDelegate extends SicoobDDADelegate<BoletoDDAAPIServico> implements BoletoDDAAPIServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected BoletoDDAAPIServico localizarServico() {
        return SicoobDDAAPIServiceLocator.getInstance().getBoletoDDAAPIServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long)
     */
    public ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente)
            throws SicoobDDAException, SicoobDDANegocioException {
        return localizarServico().obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long)
     */
    public ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws SicoobDDAException, SicoobDDANegocioException {
        return localizarServico().obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente, numIdentificadorBoletoCIP);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoletoParaPagamento(java.lang.String, java.util.Date,
     *      java.lang.Short, java.lang.Integer, java.lang.Integer, java.lang.Long, java.math.BigDecimal, java.lang.Long, boolean)
     */
    public ConsultaBoletoDDAAPIDto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws SicoobDDAException, NegocioException {
        return localizarServico().obterBoletoParaPagamento(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente, valorPagamento,
                numIdentificadorBoletoCIP, isAgendamentoPagamento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#boletoEstaNaCIP(java.lang.String)
     */
    public Boolean boletoEstaNaCIP(String numCodigoBarra) throws SicoobDDAException, SicoobDDANegocioException {
        return localizarServico().boletoEstaNaCIP(numCodigoBarra);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#isConsultaBoletoAtivo()
     */
    public Boolean isConsultaBoletoAtivo() throws SicoobDDAException {
        return localizarServico().isConsultaBoletoAtivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#inserirConsultaBoleto(java.util.List)
     */
    public void inserirConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws SicoobDDANegocioException, SicoobDDAException {
        localizarServico().inserirConsultaBoleto(listaNumCodBarras, numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#inserirConsultaBoleto(java.util.List)
     */
    public void inserirConsultaBoleto(List<String> listaNumCodBarras) throws SicoobDDANegocioException, SicoobDDAException {
        localizarServico().inserirConsultaBoleto(listaNumCodBarras);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws SicoobDDANegocioException,
            SicoobDDAException {
        localizarServico().processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago) throws SicoobDDANegocioException, SicoobDDAException {
        localizarServico().processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABaixaOperacional(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTime, java.lang.Integer)
     */
    public void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao) throws SicoobDDAException {
        localizarServico().incluirMensagemDDABaixaOperacional(dto, numBanco, canal, aceitaPagamentoParcial, dataMovimento, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABaixaOperacional(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTime, java.lang.Integer, short)
     */
    public void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao, short codMeioPagamento) throws SicoobDDAException {
        localizarServico().incluirMensagemDDABaixaOperacional(dto, numBanco, canal, aceitaPagamentoParcial, dataMovimento, idInstituicao, codMeioPagamento);
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
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterParametrosAgendamento(int)
     */
    public AgendamentoBoletoDDAAPIDto obterParametrosAgendamento(int idInstituicao) throws SicoobDDAException, SicoobDDANegocioException {
        return localizarServico().obterParametrosAgendamento(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#listarBoletoDDAPorContaCorrente(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAContaCorrenteAPIDto)
     */
    public List<BoletoDDAAPIDto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteAPIDto consultaBoletoDDAContaCorrenteAPIDto) throws SicoobDDAException,
            SicoobDDANegocioException {
        return localizarServico().listarBoletoDDAPorContaCorrente(consultaBoletoDDAContaCorrenteAPIDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABoletoGeraBoleto(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto)
     */
    public void incluirMensagemDDABoletoGeraBoleto(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().incluirMensagemDDABoletoGeraBoleto(boletoLegadoAPIDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirensagemDDABoletoSicoobNetEmpresarial(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto)
     */
    public void incluirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().incluirMensagemDDABoletoSicoobNetEmpresarial(boletoLegadoAPIDto);
    }

}
