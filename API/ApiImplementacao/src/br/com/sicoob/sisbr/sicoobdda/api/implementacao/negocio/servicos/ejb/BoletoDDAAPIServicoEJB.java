package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.BoletoCIPNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.ConsultaBoletoNegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AgendamentoBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAContaCorrenteAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.interfaces.BoletoDDAAPIServicoRemote;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AgendamentoBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBoletoPagamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AgendamentoBoletoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.CancelamentoBaixaOperacionalDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ConsultaBoletoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ManutencaoMensagemDDABoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDAAPIServicoEJB é responsável por prover os serviços dos boletos DDA.
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(BoletoDDAAPIServicoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BoletoDDAAPIServicoEJB extends SicoobDDAServicoEJB implements BoletoDDAAPIServicoRemote {

    private ConsultaBoletoDDADelegate consultaBoletoDDADelegate = OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate();
    private CancelamentoBaixaOperacionalDelegate cancelamentoBaixaOperacionalDelegate = OperacionalFabricaDelegates.getInstance().getCancelamentoBaixaOperacionalDelegate();
    private MensagemConsultaBoletoPagamentoDelegate mensagemConsultaBoletoPagamentoDelegate = IntegracaoCipFabricaDelegates.getInstance()
            .getMensagemConsultaBoletoPagamentoDelegate();

    private ManutencaoMensagemDDABoletoDelegate manutencaoMensagemDDABoletoDelegate = OperacionalFabricaDelegates.getInstance().getManutencaoMensagemDDABoletoDelegate();

    private AgendamentoBoletoDDADelegate agendamentoBoletoDDADelegate;

    /**
     * Método responsável por recuperar o serviço
     * 
     * @return
     */
    protected BaixaOperacionalServico getBaixaOperacionalServico() {
        return OperacionalServiceLocator.getInstance().localizarBaixaOperacionalServico();
    }

    /**
     * @return o atributo agendamentoBoletoDDADelegate
     */
    public AgendamentoBoletoDDADelegate getAgendamentoBoletoDDADelegate() {
        if (agendamentoBoletoDDADelegate == null) {
            agendamentoBoletoDDADelegate = OperacionalFabricaDelegates.getInstance().getAgendamentoBoletoDDADelegate();
        }

        return agendamentoBoletoDDADelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long)
     */
    public ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente)
            throws SicoobDDAException, SicoobDDANegocioException {
        return obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa, numContaCorrente, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long)
     */
    public ConsultaBoletoDDAAPIDto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            ConsultaBoletoDDADto consultaBoletoDDADto = consultaBoletoDDADelegate.obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac, numCooperativa,
                    numContaCorrente, numIdentificadorBoletoCIP);
            ConsultaBoletoDDAAPIDto consultaBoletoDDAApiDto = new ConsultaBoletoDDAAPIDto();

            ObjectUtil.copy(consultaBoletoDDADto, consultaBoletoDDAApiDto);

            // Copia a descrição da Instituição Emissora
            consultaBoletoDDAApiDto.setDescInstituicaoEmissora(consultaBoletoDDADto.getDescInstituicaoEmissora());

            return consultaBoletoDDAApiDto;
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterBoletoParaPagamento(java.lang.String, java.util.Date,
     *      java.lang.Short, java.lang.Integer, java.lang.Integer, java.lang.Long, java.math.BigDecimal, java.lang.Long, boolean)
     */
    public ConsultaBoletoDDAAPIDto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPac, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws SicoobDDAException, NegocioException {
        try {
            ConsultaBoletoDDADto consultaBoletoDDADto = consultaBoletoDDADelegate.obterBoletoParaPagamento(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPac,
                    numCooperativa, numContaCorrente, valorPagamento, numIdentificadorBoletoCIP, isAgendamentoPagamento);
            ConsultaBoletoDDAAPIDto consultaBoletoDDAApiDto = new ConsultaBoletoDDAAPIDto();

            ObjectUtil.copy(consultaBoletoDDADto, consultaBoletoDDAApiDto);

            // Copia a descrição da Instituição Emissora
            consultaBoletoDDAApiDto.setDescInstituicaoEmissora(consultaBoletoDDADto.getDescInstituicaoEmissora());

            return consultaBoletoDDAApiDto;
        } catch (br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.BoletoCIPNaoEncontradoException e) {
            throw new BoletoCIPNaoEncontradoException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        } catch (br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.ConsultaBoletoNegocioException e) {
            throw new ConsultaBoletoNegocioException(e.getMessage());
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#boletoEstaNaCIP(java.lang.String)
     */
    public Boolean boletoEstaNaCIP(String numCodigoBarra) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            return (consultaBoletoDDADelegate.isParametroDDAAtivo() && consultaBoletoDDADelegate.boletoEstaNaCIP(numCodigoBarra));
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#isConsultaBoletoAtivo()
     */
    public Boolean isConsultaBoletoAtivo() throws SicoobDDAException {
        try {
            return consultaBoletoDDADelegate.isConsultaBoletoAtivo();
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#inserirConsultaBoleto(java.util.List)
     */
    public void inserirConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            mensagemConsultaBoletoPagamentoDelegate.incluirMensagemDDAConsultaBoleto(listaNumCodBarras, numCooperativa);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    public void inserirConsultaBoleto(List<String> listaNumCodBarras) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            mensagemConsultaBoletoPagamentoDelegate.incluirMensagemDDAConsultaBoleto(listaNumCodBarras, null);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal)
            throws SicoobDDANegocioException, SicoobDDAException {
        try {
            cancelamentoBaixaOperacionalDelegate.processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago, idCanal);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago) throws SicoobDDANegocioException, SicoobDDAException {
        try {
            cancelamentoBaixaOperacionalDelegate.processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago, null);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABaixaOperacional(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
     */
    public void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao) throws SicoobDDAException {
        try {
            MensagemDDABaixaOperacional mensagem = new MensagemDDABaixaOperacional();

            ObjectUtil.copy(dto, mensagem);

            getBaixaOperacionalServico().incluir(mensagem, numBanco, canal, aceitaPagamentoParcial, (DateTimeDB)dataMovimento, idInstituicao);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABaixaOperacional(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.MensagemDDABaixaOperacionalAPIDto,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, short)
     */
    public void incluirMensagemDDABaixaOperacional(MensagemDDABaixaOperacionalAPIDto dto, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTime dataMovimento,
            Integer idInstituicao, short codMeioPagamento) throws SicoobDDAException {
        try {
            MensagemDDABaixaOperacional mensagem = new MensagemDDABaixaOperacional();

            ObjectUtil.copy(dto, mensagem);

            getBaixaOperacionalServico().incluir(mensagem, numBanco, canal, aceitaPagamentoParcial, (DateTimeDB)dataMovimento, idInstituicao, codMeioPagamento);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#obterParametrosAgendamento(int)
     */
    public AgendamentoBoletoDDAAPIDto obterParametrosAgendamento(int idInstituicao) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            AgendamentoBoletoDDADto dto = getAgendamentoBoletoDDADelegate().obterParametrosAgendamento(idInstituicao);

            AgendamentoBoletoDDAAPIDto api = new AgendamentoBoletoDDAAPIDto();
            ObjectUtil.copy(dto, api);

            return api;
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#listarBoletoDDAPorContaCorrente(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.ConsultaBoletoDDAContaCorrenteAPIDto)
     */
    public List<BoletoDDAAPIDto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteAPIDto api) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            ConsultaBoletoDDAContaCorrenteDto dto = new ConsultaBoletoDDAContaCorrenteDto();
            ObjectUtil.copy(api, dto);

            List<BoletoDDADto> listaBoletoDDADto = consultaBoletoDDADelegate.listarBoletoDDAPorContaCorrente(dto);

            List<BoletoDDAAPIDto> lista = null;

            if (!ObjectUtil.isEmpty(listaBoletoDDADto)) {
                lista = new ArrayList<BoletoDDAAPIDto>();

                for (BoletoDDADto boletoDDADto : listaBoletoDDADto) {
                    BoletoDDAAPIDto boletoDDAAPIDto = new BoletoDDAAPIDto();
                    ObjectUtil.copy(boletoDDADto, boletoDDAAPIDto);
                    lista.add(boletoDDAAPIDto);
                }
            }

            return lista;
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SicoobDDANegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABoleto(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto)
     */
    public void incluirMensagemDDABoletoGeraBoleto(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException {
        BoletoLegadoDto dto = new BoletoLegadoDto();
        ObjectUtil.copy(boletoLegadoAPIDto, dto);

        try {
            manutencaoMensagemDDABoletoDelegate.inserirMensagemDDABoletoGeraBoleto(dto);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @throws SicoobDDANegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico#incluirMensagemDDABoleto(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.BoletoLegadoAPIDto)
     */
    public void incluirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoAPIDto boletoLegadoAPIDto) throws SicoobDDAException, SicoobDDANegocioException {
        BoletoLegadoDto dto = new BoletoLegadoDto();
        ObjectUtil.copy(boletoLegadoAPIDto, dto);

        try {
            manutencaoMensagemDDABoletoDelegate.inserirMensagemDDABoletoSicoobNetEmpresarial(dto);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        }

    }

}