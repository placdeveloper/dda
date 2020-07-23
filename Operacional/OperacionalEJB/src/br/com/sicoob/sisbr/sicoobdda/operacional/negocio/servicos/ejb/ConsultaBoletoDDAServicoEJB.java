package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CapesDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.DominioDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DescontoBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CarteiraDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.EspecieDocumentoDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.BoletoDDAUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.CanalPagamentoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPercentualValor;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBoletoPagamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ContaCorrenteDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.BoletoCIPNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.ConsultaBoletoNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ConsultaBoletoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.OperacionalContextUtil;

/**
 * ConsultaBoletoDDAServicoEJB � respons�vel por fornecer servi�o acessado pelos canais: Caixa, ATM, SicoobNet Empresarial, SicoobNet Pessoal e Correspondentes
 * Banc�rios
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local(ConsultaBoletoDDAServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConsultaBoletoDDAServicoEJB extends OperacionalCrudServicoEJB<BoletoDDA> implements ConsultaBoletoDDAServicoLocal {

    private static final String ERRO_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO = "integracaocip.parametro.nao.informado";

    private static final int SCALE_2 = 2;
    private static final int SCALE_6 = 6;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private AgendamentoBoletoDao agendamentoBoletoDAO;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private BoletoDDADao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoCipDao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private PagadorEletronicoDDADao pagadorEletronicoDDADao;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Deprecated
    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    // Vari�vel iniciada como FALSE para o servi�o isConsultaBoletoAtivo chamado diretamente pela API;
    private Boolean isCacheHabilitado = Boolean.FALSE;

    private ADMDelegate admDelegate;

    private MensagemConsultaBoletoPagamentoDelegate mensagemConsultaBoletoPagamentoDelegate;

    private ContaCorrenteDelegate contaCorrenteDelegate;

    private InstituicaoDelegate instituicaoDelegate;

    private SCIDelegate sciDelegate;

    private CapesDelegate capesDelegate;

    private MensagemDDADelegate mensagemDDADelegate;

    private ConfigurarRelatorioJasper configurarRelatorio = null;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return o atributo dao
     */
    protected BoletoDDADao getDAO() {
        return dao;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    private MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * @return o atributo ADMDelegate
     */
    private ADMDelegate getADMDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * @return o atributo MensagemConsultaBoletoPagamentoDelegate
     */
    private MensagemConsultaBoletoPagamentoDelegate getMensagemConsultaBoletoPagamentoDelegate() {
        if (ObjectUtil.isNull(mensagemConsultaBoletoPagamentoDelegate)) {
            mensagemConsultaBoletoPagamentoDelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemConsultaBoletoPagamentoDelegate();
        }
        return mensagemConsultaBoletoPagamentoDelegate;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ContaCorrenteDelegate
     * 
     */
    private ContaCorrenteDelegate getContaCorrenteDelegate() {
        if (ObjectUtil.isNull(contaCorrenteDelegate)) {
            contaCorrenteDelegate = IntegracaoInternaFabricaDelegates.getInstance().getContaCorrenteDelegate();
        }
        return contaCorrenteDelegate;
    }

    /**
     * @return o atributo instituicaoDelegate
     */
    private InstituicaoDelegate getInstituicaoDelegate() {
        if (ObjectUtil.isNull(instituicaoDelegate)) {
            instituicaoDelegate = ComumFabricaDelegates.getInstance().getInstituicaoDelegate();
        }
        return instituicaoDelegate;
    }

    /**
     * @return o atributo sciDelegate
     */
    public SCIDelegate getSCIDelegate() {
        if (ObjectUtil.isNull(sciDelegate)) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }

        return sciDelegate;
    }

    /**
     * @return o atributo capesDelegate
     */
    private CapesDelegate getCapesDelegate() {
        if (ObjectUtil.isNull(capesDelegate)) {
            capesDelegate = IntegracaoInternaFabricaDelegates.getInstance().getCapesDelegate();
        }
        return capesDelegate;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ParametroDelegate
     * 
     */
    private ParametroDelegate getParametroDelegate() {
        return ComumFabricaDelegates.getInstance().getParametroDelegate();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return DominioDDADelegate
     * 
     */
    private DominioDDADelegate getDominioDDADelegate() {
        return ComumFabricaDelegates.getInstance().getDominioDDADelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterBoleto(java.lang.String, java.util.Date, java.lang.Short,
     *      java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConsultaBoletoDDADto obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPA, Integer numCooperativa, Long numContaCorrente,
            Long numIdentificadorBoletoCIP) throws ComumNegocioException, ComumException {
        debug("### Obtendo boleto para consulta...");
        return processarConsulta(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPA, numCooperativa, numContaCorrente, null, numIdentificadorBoletoCIP, Boolean.TRUE,
                Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterBoletoParaPagamento(java.lang.String, java.util.Date,
     *      java.lang.Short, java.lang.Integer, java.lang.Integer, java.lang.Long, java.math.BigDecimal, boolean)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConsultaBoletoDDADto obterBoletoParaPagamento(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPA, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException {
        debug("### Obtendo boleto para pagamento...");
        return processarConsulta(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPA, numCooperativa, numContaCorrente, valorPagamento, numIdentificadorBoletoCIP,
                isAgendamentoPagamento, Boolean.FALSE);
    }

    /**
     * M�todo respons�vel por validar as informa��es e obter o boleto.
     * 
     * @param linhaDigitavelCodigoBarras
     * @param dataPagamento
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param numContaCorrente
     * @param valorPagamento
     * @param numIdentificadorBoletoCIP
     * @param isAgendamentoPagamento
     * @param isConsulta
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto processarConsulta(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPA, Integer numCooperativa,
            Long numContaCorrente, BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento, boolean isConsulta)
            throws ComumNegocioException, ComumException {
        debug("### Processando consulta...");
        debug("Par�metro - linhaDigitavelCodigoBarras: " + linhaDigitavelCodigoBarras);
        debug("Par�metro - dataPagamento: " + dataPagamento);
        debug("Par�metro - idCanal: " + idCanal);
        debug("Par�metro - numPA: " + numPA);
        debug("Par�metro - numCooperativa: " + numCooperativa);
        debug("Par�metro - numContaCorrente: " + numContaCorrente);
        debug("Par�metro - valorPagamento: " + valorPagamento);
        debug("Par�metro - numIdentificadorBoletoCIP: " + numIdentificadorBoletoCIP);
        debug("Par�metro - isAgendamentoPagamento: " + isAgendamentoPagamento);

        // Contig�ncia caso de problema no cache.
        isCacheHabilitado = parametroDAO.obterValorBoolean(ParametroDDA.CACHE_HABILITADO, Constantes.ID_BANCOOB);
        debug("[DDA] CACHE HABILITADO: " + isCacheHabilitado);

        /*
         * Se o idCanal n�o estiver nulo e for canal de agendamento, deve usar o PA zero, caso contr�rio utiliza o que foi passado pelo canal.
         * 
         * Caso o idCanal seja nulo lan�ar� exce��o na valida��o do canal.
         */
        if (!ObjectUtil.isEmpty(idCanal) && !isCanalCaixaOuCorrespondente(idCanal)) {
            numPA = Constantes.INTEGER_ZERO;
            debug("Novo numPA: " + numPA);
        }

        // Se informou a data de pagamento
        if (!ObjectUtil.isNull(dataPagamento)) {
            debug("Removendo as horas da data de pagamento para evitar problema com as valida��es com data...");
            dataPagamento = DateUtil.obterDataSemHora(dataPagamento);

            // Se veio da transa��o de consulta e o par�metro estiver ativo
            if (isConsulta && isObterProximoDiaUtilPagamento(idCanal)) {
                Date dataPagamentoUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, dataPagamento, true);
                debug("Data de pagamento �til: " + dataPagamentoUtil);

                dataPagamento = dataPagamentoUtil;
            }
        }

        // Se N�O informou a data de pagamento e n�o possui valor, ent�o est� vindo do detalhamento de boleto DDA
        boolean isDetalhamento = ObjectUtil.isNull(dataPagamento) && ObjectUtil.isEmpty(valorPagamento);
        debug("� um detalhamento: " + isDetalhamento);

        validarParametro(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPA, numCooperativa, isDetalhamento);

        int idInstituicao = obterIdInstituicao(numCooperativa);
        debug("Id Institui��o: " + idInstituicao);

        final String codigoBarras = obterNumCodigoBarras(linhaDigitavelCodigoBarras);
        debug("C�digo de barras: " + codigoBarras);

        short numBanco = LinhaDigitavelCodigoBarrasUtil.obterNumeroBancoPorCodigoBarras(codigoBarras);
        debug("NumBanco recuperado: " + numBanco);

        // Se for um detalhamento
        if (isDetalhamento) {
            return obterBoletoDDADetalhamento(codigoBarras, numIdentificadorBoletoCIP, numBanco);
        }

        ConsultaBoletoDDADto consultaBoletoDDADto = obterBoletoDDACIP(codigoBarras, idCanal, numPA, numCooperativa, idInstituicao, dataPagamento, valorPagamento,
                numIdentificadorBoletoCIP, isAgendamentoPagamento, numBanco);

        // Se encontrou o boleto no SicoobDDA ou na CIP
        if (!ObjectUtil.isNull(consultaBoletoDDADto)) {
            debug("Retornando o boletoDDA");
            return consultaBoletoDDADto;
        }

        return tratarObterBoleto(numCooperativa, idInstituicao, codigoBarras, idCanal, numPA, dataPagamento);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param linhaDigitavelCodigoBarras
     * @return
     * @throws ComumNegocioException String
     * 
     */
    private String obterNumCodigoBarras(String linhaDigitavelCodigoBarras) throws ComumNegocioException {
        if (linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_CODIGO_BARRAS) {
            return linhaDigitavelCodigoBarras;
        }

        return LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(linhaDigitavelCodigoBarras);
    }

    /**
     * M�todo respons�vel por obter o id da institui��o
     * 
     * @param numCooperativa
     * @return
     * @throws ComumException
     */
    private int obterIdInstituicao(Integer numCooperativa) throws ComumException {
        debug("### Obtendo o id da institui��o...");
        debug("Par�metro - numCooperativa: " + numCooperativa);

        InstituicaoDto instituicaoDto;
        if (isCacheHabilitado) {
            instituicaoDto = getInstituicaoDelegate().obterInstituicaoPorCooperativa(numCooperativa);
        } else {
            instituicaoDto = getSCIDelegate().obterInstituicaoPorCooperativaCache(numCooperativa);
        }

        debug("InstituicaoDto: " + instituicaoDto);

        return instituicaoDto.getIdInstituicao();
    }

    /**
     * M�todo respons�vel por obter o boleto do legado atrav�s do c�digo de barras. Se o n�mero do banco for diferente de 756, retornar� o DTO com apenas
     * algumas informa��es contidas no c�digo de barras.
     * 
     * @param numCooperativa
     * @param idInstituicao
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param dataPagamento
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto tratarObterBoleto(Integer numCooperativa, Integer idInstituicao, String codigoBarras, Short idCanal, Integer numPA, Date dataPagamento)
            throws ComumNegocioException, ComumException {
        debug("### Tratar obter boleto...");

        validarDataVencimento(numCooperativa, codigoBarras, idCanal, numPA, dataPagamento);

        ConsultaBoletoDDADto consultaBoletoDDADto = null;

        short numBanco = LinhaDigitavelCodigoBarrasUtil.obterNumeroBancoPorCodigoBarras(codigoBarras);
        debug("N�mero do banco obtido do c�digo de barras: " + numBanco);

        boolean isBoleto756 = numBanco == Constantes.NUM_BANCOOB;
        debug("Boleto 756: " + isBoleto756);

        // Se for 756
        if (isBoleto756) {
            consultaBoletoDDADto = obterBoletoLegado(numCooperativa, idInstituicao, numBanco, numPA, codigoBarras, dataPagamento);
        } else if (numBanco == Constantes.NUM_INSTITUICAO_FINANCEIRA) {
            throw new ComumNegocioException("integracaocip.consulta.nao.permitida.instituicao.financeira.invalida", numBanco);
        } else {
            validarPagamentoSemRegistro(idInstituicao);

            // Caso contr�rio preenche apenas as informa��es do c�digo de barras
            consultaBoletoDDADto = preencherInformacoesBasicas(numBanco, codigoBarras, dataPagamento);
        }

        preencherInformacoesExtras(numCooperativa, codigoBarras, idCanal, numPA, consultaBoletoDDADto);

        return consultaBoletoDDADto;
    }

    /**
     * M�todo respons�vel por verificar se permite pagamento de boleto sem registro
     * 
     * @param idInstituicao
     * @throws BoletoCIPNaoEncontradoException
     * @throws ComumException
     */
    private void validarPagamentoSemRegistro(Integer idInstituicao) throws BoletoCIPNaoEncontradoException, ComumException {
        if (!permitePagarBoletoSemRegistro(idInstituicao)) {
            throw new BoletoCIPNaoEncontradoException("integracaocip.consulta.boleto.sem.registro.nao.permitida");
        }
    }

    /**
     * M�todo respons�vel por verificar se o par�metro est� habilitado ou desabilitado.
     * 
     * @param idInstituicao
     * @return boolean
     * @throws ComumException
     */
    private boolean permitePagarBoletoSemRegistro(Integer idInstituicao) throws ComumException {
        boolean permitePagamentoSR = parametroDAO.obterValorBoolean(ParametroDDA.PERMITE_PAGAMENTO_BOLETO_SEM_REGISTRO, idInstituicao);
        debug("Permite pagamento de boleto sem registro: " + permitePagamentoSR);
        return permitePagamentoSR;
    }

    /**
     * M�todo respons�vel por preencher algumas informa��es extras
     * 
     * @param numCooperativa
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param consultaBoletoDDADto
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void preencherInformacoesExtras(Integer numCooperativa, String codigoBarras, Short idCanal, Integer numPA, ConsultaBoletoDDADto consultaBoletoDDADto)
            throws ComumException, ComumNegocioException {
        debug("### Preenchendo informa��es extras...");

        realizarCalculoValorPagamento(consultaBoletoDDADto, true);

        if (!ObjectUtil.isNull(consultaBoletoDDADto)) {
            debug("Definindo campos extras...");

            consultaBoletoDDADto.setBolNovoFluxo(Boolean.FALSE);
            consultaBoletoDDADto.setBloquearPagamento(Boolean.FALSE);
            consultaBoletoDDADto.setPermiteAlterarValor(Boolean.TRUE);
            consultaBoletoDDADto.setPermiteValorDivergente(Boolean.TRUE);
            consultaBoletoDDADto.setValidarValorMinimoEMaximo(Boolean.FALSE);
            consultaBoletoDDADto.setValidarValorMinimo(Boolean.FALSE);
            consultaBoletoDDADto.setValidarValorMaximo(Boolean.FALSE);

            // Se for canal CAIXA ou Correspondente
            if (isCanalCaixaOuCorrespondente(idCanal)) {
                // Verifica se possui alguma mensagem de alerta para pagamento de boleto que esteja vencido
                String mensagemAlertaPagamento = agendamentoBoletoDAO.obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(numCooperativa, codigoBarras, idCanal, numPA);
                consultaBoletoDDADto.setMensagemBloqueioPagamento(mensagemAlertaPagamento);
            }

            if (!ObjectUtil.isNull(consultaBoletoDDADto.getDataVencimentoBoleto())) {
                Date dataVencimentoUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, consultaBoletoDDADto.getDataVencimentoBoleto());
                debug("Data de vencimento �til: " + dataVencimentoUtil);

                // Define a data de vencimento �til que ser� utilizada no pagamento
                consultaBoletoDDADto.setDataVencimentoUtil(new DateTimeDB(dataVencimentoUtil.getTime()));
            }
        }
    }

    /**
     * M�todo respons�vel por preencher as informa��es b�sicas contidas no c�digo de barras:<br>
     * 
     * <li>C�digo de barras</li><br>
     * <li>Linha digit�vel</li><br>
     * <li>N�mero do banco</li><br>
     * <li>Nome do banco</li><br>
     * <li>Valor do boleto</li>
     * <li>Data de vencimento</li><br>
     * 
     * @param numBanco
     * @param codigoBarras
     * @param dataPagamento
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto preencherInformacoesBasicas(short numBanco, String codigoBarras, Date dataPagamento) throws ComumNegocioException, ComumException {
        debug("### Preenchendo as informa��es b�sicas do c�digo de barras");

        ConsultaBoletoDDADto dto = new ConsultaBoletoDDADto();
        dto.setDataPagamento(new DateTimeDB(dataPagamento.getTime()));

        dto.setNumLinhaDigitavel(LinhaDigitavelCodigoBarrasUtil.obterLinhaDigitavelPorCodigoBarras(codigoBarras));
        dto.setNumCodigoBarras(codigoBarras);

        dto.setNumInstituicaoEmissora(numBanco);
        dto.setNomeInstituicaoEmissora(obterNomeBanco(numBanco));

        double valor = LinhaDigitavelCodigoBarrasUtil.obterValorTituloPorCodigoBarras(codigoBarras);
        dto.setValorBoleto(BigDecimal.valueOf(valor).setScale(SCALE_2, BigDecimal.ROUND_HALF_EVEN));

        debug("Valor do boleto obtido pelo c�digo de barra: " + valor);

        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(codigoBarras);
        debug("Data de vencimento obtida do c�digo de barras: " + dataVencimento);

        if (!ObjectUtil.isNull(dataVencimento)) {
            dto.setDataVencimentoBoleto(new DateTimeDB(dataVencimento.getTime()));
        } else {
            debug("Definindo data de pagamento como data de vencimento");
            // Se a data de vencimento do boleto for nula, utilizaremos a data de pagamento (e n�o a data de atual) como sendo a data de vencimento.
            dto.setDataVencimentoBoleto(new DateTimeDB(dataPagamento.getTime()));
        }

        return dto;
    }

    /**
     * M�todo respons�vel por realizar os c�lculos do boleto.
     * 
     * @param consultaBoletoDDADto
     * @param isBoletoEmAberto
     */
    private void realizarCalculoValorPagamento(ConsultaBoletoDDADto consultaBoletoDDADto, boolean isBoletoEmAberto) {
        debug("### Realizando o c�lculo do valor de pagamento...");

        /*
         * Se o dto n�o for nulo e o valor de pagamento for nulo ou igual a zero far� os c�lculos.
         * 
         * OBS: quando obtemos o boleto da tabela TITULO, o c�lculo de encargos e valor de pagamento pode vir calculado, dependendo da situa��o. N�o sendo
         * necess�rio recalcular.
         */
        if (!ObjectUtil.isNull(consultaBoletoDDADto) && ObjectUtil.isEmpty(consultaBoletoDDADto.getValorPagamento())) {
            if (isBoletoEmAberto) {
                BigDecimal valorAbatimentoDesconto = obterBigDecimal(consultaBoletoDDADto.getValorAbatimento()).add(obterBigDecimal(consultaBoletoDDADto.getValorDesconto()));
                debug("Valor de abatimento e desconto: " + valorAbatimentoDesconto);

                BigDecimal valorMultaMora = obterBigDecimal(consultaBoletoDDADto.getValorMora()).add(obterBigDecimal(consultaBoletoDDADto.getValorMulta()));
                debug("Valor de encargos: " + valorMultaMora);

                // Valores somados para retornar na transa��o para os canais
                consultaBoletoDDADto.setValorAbatimentoDesconto(valorAbatimentoDesconto.setScale(SCALE_2, BigDecimal.ROUND_DOWN));
                consultaBoletoDDADto.setValorMultaMora(valorMultaMora.setScale(SCALE_2, BigDecimal.ROUND_DOWN));

                BigDecimal valorBoleto = obterBigDecimal(consultaBoletoDDADto.getValorBoleto());
                debug("Valor do boleto: " + valorBoleto);

                BigDecimal valorPagamento = valorBoleto.subtract(valorAbatimentoDesconto.setScale(SCALE_2, BigDecimal.ROUND_DOWN))
                        .add(valorMultaMora.setScale(SCALE_2, BigDecimal.ROUND_DOWN));
                debug("Valor de pagamento: " + valorPagamento);

                BigDecimal valorTotal = valorPagamento.setScale(SCALE_2, BigDecimal.ROUND_DOWN);
                debug("Valor total: " + valorTotal);

                // Se o valor final for inferior a ZERO define ZERO
                if (valorTotal.doubleValue() < 0) {
                    consultaBoletoDDADto.setValorPagamento(BigDecimal.ZERO);
                } else {
                    consultaBoletoDDADto.setValorPagamento(valorTotal);
                }
            } else {
                consultaBoletoDDADto.setValorPagamento(BigDecimal.ZERO);
                consultaBoletoDDADto.setValorAbatimentoDesconto(BigDecimal.ZERO);
                consultaBoletoDDADto.setValorMultaMora(BigDecimal.ZERO);
            }
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param valor
     * @return BigDecimal
     */
    private BigDecimal obterBigDecimal(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO : valor;
    }

    /**
     * M�todo respons�vel por obter o nome do banco atrav�s pelo n�mero do banco.
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    public String obterNomeBanco(short numBanco) throws ComumException, ComumNegocioException {
        debug("### Obtendo o nome do banco: " + numBanco);

        return getADMDelegate().obterNomeBancoCache(numBanco);
    }

    /**
     * M�todo respons�vel por obter o nome da IF pelo ISPB.
     * 
     * @param ispb
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private String obterNomeInstituicaoFinanceira(String ispb) throws ComumException, ComumNegocioException {
        debug("### Obtendo nome da IF do ISPB: " + ispb);

        String nomeIF = getADMDelegate().obterNomeInstituicaoFinanceiraCache(ispb);
        debug("Nome da IF: " + nomeIF);

        return nomeIF;
    }

    /**
     * M�todo respons�vel por obter o boleto pelo c�digo de barras na tabela T�tulo e, se n�o achar, obt�m do c�digo de barras
     * 
     * @param numCooperativa
     * @param idInstituicao
     * @param numBanco
     * @param numPA
     * @param codigoBarras
     * @param dataPagamento
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto obterBoletoLegado(Integer numCooperativa, Integer idInstituicao, short numBanco, Integer numPA, String codigoBarras, Date dataPagamento)
            throws ComumNegocioException, ComumException {
        debug("### Obtendo boleto do legado...");

        int numCooperativaCodBarras = LinhaDigitavelCodigoBarrasUtil.obterNumeroCooperativaPorCodigoBarras(codigoBarras);
        debug("N�mero da cooperativa obtido do c�digo de barras: " + numCooperativaCodBarras);

        ConsultaBoletoDDADto dto = agendamentoBoletoDAO.obterBoletoPorCodBarras(numCooperativaCodBarras, numCooperativa, numPA, codigoBarras, dataPagamento);

        // Se n�o encontrar o boleto retornar� as informa��es b�sicas
        if (ObjectUtil.isNull(dto)) {
            validarPagamentoSemRegistro(idInstituicao);

            dto = preencherInformacoesBasicas(numBanco, codigoBarras, dataPagamento);
        } else {
            preencherInformacoesComplementares(numBanco, codigoBarras, dto, dataPagamento);
        }

        return dto;
    }

    /**
     * M�todo respons�vel por validar a data de vencimento para o canais de agendamento.
     * 
     * @param numCooperativa
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param dataPagamento
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void validarDataVencimento(Integer numCooperativa, String codigoBarras, Short idCanal, Integer numPA, Date dataPagamento) throws ComumNegocioException, ComumException {
        debug("### Validar data de vencimento...");

        // Se for canal de agendamento
        if (!isCanalCaixaOuCorrespondente(idCanal)) {
            Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(codigoBarras);
            debug("Data de vencimento obtida do c�digo de barras: " + dataVencimento);

            /*
             * Verifica se tem data de vencimento
             * 
             * OBS: Se n�o tiver data de vencimento, ser� utilizada a data de pagamento posteriormente, portanto o boleto n�o estar� vencido
             */
            if (!ObjectUtil.isNull(dataVencimento)) {
                // Obt�m a data de vencimento para dia �til
                Date dataVencimentoUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, dataVencimento);
                debug("Data de vencimento �til: " + dataVencimentoUtil);

                // Verifica se a data de pagamento � maior que a de vencimento
                if (dataPagamento.after(dataVencimentoUtil)) {
                    throw new ConsultaBoletoNegocioException("integracaocip.boleto.vencido");
                }
            }
        }
    }

    /**
     * M�todo respons�vel por identificar se � canal CAIXA ou Correspondente.
     * 
     * @param idCanal
     * @return
     */
    private boolean isCanalCaixaOuCorrespondente(Short idCanal) {
        return idCanal.equals(CanalEnum.CAIXA.getId()) || idCanal.equals(CanalEnum.CORRESPONDENTE_SICOOB.getId());
    }

    /**
     * M�todo respons�vel por preencher as informa��es complementares que n�o s�o obtidas no legado.
     * 
     * @param numBanco
     * @param codigoBarras
     * @param dto
     * @param dataPagamento
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void preencherInformacoesComplementares(short numBanco, String codigoBarras, ConsultaBoletoDDADto dto, Date dataPagamento)
            throws ComumNegocioException, ComumException {
        debug("### Preenchendo as informa��es complementares do boleto...");

        dto.setNumLinhaDigitavel(LinhaDigitavelCodigoBarrasUtil.obterLinhaDigitavelPorCodigoBarras(codigoBarras));
        dto.setNumCodigoBarras(codigoBarras);
        dto.setNomeInstituicaoEmissora(obterNomeBanco(numBanco));

        dto.setDataPagamento(new DateTimeDB(dataPagamento.getTime()));
    }

    /**
     * M�todo respons�vel por validar os par�metros informados.
     * 
     * @param codigoBarras
     * @param dataPagamento
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param isDetalhamento
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void validarParametro(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPA, Integer numCooperativa, boolean isDetalhamento)
            throws ComumNegocioException, ComumException {
        debug("### Validando os par�metros...");

        if (ObjectUtil.isEmpty(linhaDigitavelCodigoBarras)) {
            throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.nao.informado");
        } else if (ObjectUtil.isEmpty(numCooperativa)) {
            throw new ComumNegocioException(ERRO_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "N�mero da cooperativa");
        } else if (ObjectUtil.isEmpty(idCanal)) {
            throw new ComumNegocioException(ERRO_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "Canal");
        } else if (ObjectUtil.isNull(numPA)) {
            throw new ComumNegocioException(ERRO_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "N�mero do PA");
        }

        // Se n�o for um detalhamento
        if (!isDetalhamento) {
            Date dataAtual = DateUtil.obterDataSemHora(new Date());
            debug("Data atual: " + dataAtual);

            // Se n�o for um detalhamento e n�o tiver informado a data de pagamento
            if (ObjectUtil.isNull(dataPagamento)) {
                throw new ComumNegocioException(ERRO_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "data de pagamento");
            } else if (dataPagamento.before(dataAtual)) {
                throw new ConsultaBoletoNegocioException("integracaocip.data.vencimento.anterior.data.atual");
            } else if (!agendamentoBoletoDAO.isDataUtil(numCooperativa, dataPagamento)) {
                // Verifica se a data de pagamento � um dia �til
                throw new ConsultaBoletoNegocioException("integracaocip.data.vencimento.dia.util");
            }
        }

        int tamanho = linhaDigitavelCodigoBarras.length();
        debug("Tamanho do par�metro linhaDigitavelCodigoBarras: " + tamanho);

        if (tamanho != Constantes.TAMANHO_CODIGO_BARRAS && tamanho != Constantes.TAMANHO_LINHA_DIGITAVEL) {
            throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido");
        } else if (!linhaDigitavelCodigoBarras.matches("\\d+")) {
            throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.invalido");
        }

        // Valida os DVs da linha digit�vel/c�digo de barras
        LinhaDigitavelCodigoBarrasUtil.validarDVLinhaDigitavelCodigoBarras(linhaDigitavelCodigoBarras);
    }

    /**
     * M�todo respons�vel por obter o boleto no servi�o no SicoobDDA ou na CIP.
     * 
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param idInstituicao
     * @param dataPagamento
     * @param valorPagamento
     * @param numIdentificadorBoletoCIP
     * @param isAgendamentoPagamento
     * @param numBanco
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto obterBoletoDDACIP(String codigoBarras, Short idCanal, Integer numPA, Integer numCooperativa, int idInstituicao, Date dataPagamento,
            BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento, short numBanco) throws ComumNegocioException, ComumException {
        debug("### Obtendo boleto DDA...");

        ConsultaBoletoDDADto consultaBoletoDDADto = obterBoletoDDAValido(codigoBarras, dataPagamento, numCooperativa, numPA, numBanco, idCanal, valorPagamento,
                numIdentificadorBoletoCIP, isAgendamentoPagamento);

        // Se n�o existe um BoletoDDA v�lido
        if (ObjectUtil.isNull(consultaBoletoDDADto)) {
            consultaBoletoDDADto = obterBoletoCIP(codigoBarras, idCanal, numPA, numCooperativa, idInstituicao, dataPagamento, valorPagamento, isAgendamentoPagamento, numBanco,
                    numIdentificadorBoletoCIP);
        }

        return consultaBoletoDDADto;
    }

    /**
     * M�todo respons�vel por obter o boleto da CIP se for permitido.
     * 
     * Se for banco 756, ou o par�metro SICOOBDDA_CONSULTA_BOLETO_ATIVO estiver desligado ou o valor do boleto for menor que o valor m�nimo ent�o N�O deve
     * consultar na CIP.
     * 
     * Se a conting�ncia estiver ativa verifica permite continuar o agendamento/pagamento ou se deve lan�ar exce��o.
     * 
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param idInstituicao
     * @param dataPagamento
     * @param valorPagamento
     * @param isAgendamentoPagamento
     * @param numBanco
     * @param numIdentificadorBoletoCIP
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private ConsultaBoletoDDADto obterBoletoCIP(String codigoBarras, Short idCanal, Integer numPA, Integer numCooperativa, int idInstituicao, Date dataPagamento,
            BigDecimal valorPagamento, boolean isAgendamentoPagamento, short numBanco, Long numIdentificadorBoletoCIP) throws ComumException, ComumNegocioException {
        debug("### Tratar obter boleto da CIP...");

        // Se for banco 756 N�O deve consultar na CIP
        if (numBanco == Constantes.NUM_BANCOOB) {
            return null;
        }

        // Se a consulta na CIP estiver inativa N�O deve consultar na CIP
        if (!isConsultaBoletoAtivo()) {
            debug("Consulta BoletoDDA inativa");
            return null;
        }

        // Obt�m o valor do boleto
        double valorBoleto = LinhaDigitavelCodigoBarrasUtil.obterValorTituloPorCodigoBarras(codigoBarras);
        debug("Valor do boleto obtido do c�digo de barras: " + valorBoleto);

        /*
         * Se N�O for as IFs que n�o s�o banco verifica pelo valor do boleto se deve consultar na CIP. Se for IF deve consultar na cip j� que n�o � poss�vel
         * obter as informa��es pelo c�digo de barras.
         */
        if (numBanco != Constantes.NUM_INSTITUICAO_FINANCEIRA) {
            // Indica��o do valor m�nimo de boleto que j� est� na CIP
            int valorBoletoNaCIP = obterValorBoletoNaCIP();
            debug("Valor do boleto na CIP: " + valorBoletoNaCIP);

            // Se o valor do boleto for menor do que o par�metro de valor m�nimo N�O deve consultar na CIP
            if (valorBoleto < valorBoletoNaCIP) {
                debug("O valor do boleto � menor que o valor de onda da CIP");
                return null;
            }
        }

        // Verifica se a conting�ncia est� ativa
        if (isContingenciaAtiva()) {
            validarContingencia(codigoBarras, isAgendamentoPagamento, numBanco, valorBoleto, dataPagamento, valorPagamento);

            // Se n�o lan�ou exce��o na valida��o, ent�o obt�m os dados do c�digo de barras
            return preencherInformacoesBasicasCIP(numBanco, codigoBarras, dataPagamento, numCooperativa, idCanal, numPA, Boolean.TRUE);
        }

        return obterBoletoCIP(codigoBarras, idCanal, numPA, numCooperativa, idInstituicao, dataPagamento, numBanco, valorPagamento, numIdentificadorBoletoCIP,
                isAgendamentoPagamento);
    }

    /**
     * M�todo respons�vel por verificar se a conting�ncia est� ativa
     * 
     * @return
     * @throws ComumException
     */
    private boolean isContingenciaAtiva() throws ComumException {
        boolean contingenciaAtiva;
        if (isCacheHabilitado) {
            contingenciaAtiva = getParametroDelegate().obterValorBoolean(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP);
        } else {
            contingenciaAtiva = parametroDAO.obterValorBoolean(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, Constantes.ID_BANCOOB);
        }
        debug("Conting�ncia ativa: " + contingenciaAtiva);
        return contingenciaAtiva;
    }

    /**
     * M�todo respons�vel por obter o par�metro para verificar se deve obter pr�ximo dia �til para pagamento
     * 
     * @param idCanal
     * 
     * @return
     * @throws ComumException
     */
    private boolean isObterProximoDiaUtilPagamento(short idCanal) throws ComumException {
        long idParametro;

        if (idCanal == CanalEnum.RETAGUARDA.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_RETAGUARDA;
        } else if (idCanal == CanalEnum.ATM.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_ATM;
        } else if (idCanal == CanalEnum.IB.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_IB;
        } else if (idCanal == CanalEnum.SICOOB_NET.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET;
        } else if (idCanal == CanalEnum.SICOOB_NET_EMPRESARIAL.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_EMPRESARIAL;
        } else if (idCanal == CanalEnum.SICOOB_NET_MOBILE.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_MOBILE;
        } else if (idCanal == CanalEnum.SICOOB_NET_MOBILE_EMPRESARIAL_2.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_SICOOB_NET_MOBILE_EMPRESARIAL;
        } else if (idCanal == CanalEnum.CONTA_PAGAMENTO_DIGITAL.getId()) {
            idParametro = ParametroDDA.OBTER_PROXIMO_DIA_UTIL_PAGAMENTO_BOLETO_CONTA_PAGAMENTO_DIGITAL;
        } else {
            return false;
        }

        return obterParametro(idParametro);
    }

    /**
     * M�todo respons�vel por obter o par�metro
     * 
     * @param idParametro
     * @return
     * @throws ComumException
     */
    private boolean obterParametro(long idParametro) throws ComumException {
        boolean obterProxDiaUtilPgmto;

        if (isCacheHabilitado) {
            obterProxDiaUtilPgmto = getParametroDelegate().obterValorBoolean(idParametro);
        } else {
            obterProxDiaUtilPgmto = parametroDAO.obterValorBoolean(idParametro, Constantes.ID_BANCOOB);
        }

        debug("Obter pr�ximo dia �til para pagamento (parametro " + idParametro + "): " + obterProxDiaUtilPgmto);
        return obterProxDiaUtilPgmto;
    }

    /**
     * M�todo respons�vel por obter o valor dos boletos na CIP
     * 
     * @return
     * @throws ComumException
     */
    private Integer obterValorBoletoNaCIP() throws ComumException {
        if (isCacheHabilitado) {
            return getParametroDelegate().obterValorInteger(ParametroDDA.VALOR_MIN_BOLETO_ENVIO_A_CIP);
        }
        return parametroDAO.obterValorInteger(ParametroDDA.VALOR_MIN_BOLETO_ENVIO_A_CIP, Constantes.ID_BANCOOB);
    }

    /**
     * M�todo respons�vel por validar quando estiver em conting�ncia.
     * 
     * @param codigoBarras
     * @param isAgendamentoPagamento
     * @param numBanco
     * @param valorBoleto
     * @param dataPagamento
     * @param valorPagamento
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void validarContingencia(String codigoBarras, boolean isAgendamentoPagamento, short numBanco, double valorBoleto, Date dataPagamento, BigDecimal valorPagamento)
            throws ComumNegocioException, ComumException {
        // Se conting�ncia ativa e for IF 988 deve lan�ar exce��o
        if (numBanco == Constantes.NUM_INSTITUICAO_FINANCEIRA) {
            throw new ComumNegocioException("integracaocip.contingencia.instituicao.financeira.invalida", Constantes.NUM_INSTITUICAO_FINANCEIRA);
        }

        // Obt�m o valor m�ximo para agendamento quando estiver em conting�ncia
        double valorBoletoPermitidoContingencia = obterValorBoletoPermitidoContingencia();

        // Se for um agendamento/pagamento (se vier da "teimosinha" n�o valida essa regra)
        if (isAgendamentoPagamento) {
            // Verifica se o valor do boleto ou se o valor de pagamento � maior que o valor m�ximo permitido para agendamento em conting�ncia
            if ((valorBoleto > valorBoletoPermitidoContingencia) || (!ObjectUtil.isEmpty(valorPagamento) && valorPagamento.doubleValue() > valorBoletoPermitidoContingencia)) {
                throw new ComumNegocioException("integracaocip.contingencia.valor.maior.permitido", FormatadorUtil.formatarValorReal(valorBoletoPermitidoContingencia));
            }
        }

        int moeda = LinhaDigitavelCodigoBarrasUtil.obterCodigoMoedaPorCodigoBarras(codigoBarras);
        debug("Moeda: " + moeda);

        // Verifica se a moeda � diferente de real
        if (moeda != Constantes.COD_MOEDA_REAL) {
            throw new ComumNegocioException("integracaocip.contingencia.moeda.estrangeira");
        }

        Date dataAtual = DateUtil.obterDataSemHora(new Date());
        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(codigoBarras);

        // Se estiver vencido n�o aceita o pagamento
        if (!ObjectUtil.isNull(dataVencimento) && dataVencimento.before(dataAtual)) {
            throw new ComumNegocioException("integracaocip.contingencia.boleto.vencido");
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException double
     * 
     */
    private double obterValorBoletoPermitidoContingencia() throws ComumException {
        double valorBoletoPermitidoContingencia;
        if (isCacheHabilitado) {
            valorBoletoPermitidoContingencia = getParametroDelegate().obterValorDouble(ParametroDDA.VALOR_MAXIMO_PERMITIDO_RECEBIMENTO_EM_CONTINGENCIA);
        } else {
            valorBoletoPermitidoContingencia = parametroDAO.obterValorDouble(ParametroDDA.VALOR_MAXIMO_PERMITIDO_RECEBIMENTO_EM_CONTINGENCIA, Constantes.ID_BANCOOB);
        }
        debug("Valor permitido durante conting�ncia: " + valorBoletoPermitidoContingencia);
        return valorBoletoPermitidoContingencia;
    }

    /**
     * M�todo respons�vel por preencher as informa��es do boleto obtidos no c�digo de barra.
     * 
     * @param numBanco
     * @param codigoBarras
     * @param dataPagamento
     * @param numCooperativa
     * @param idCanal
     * @param numPA
     * @param novoFluxo
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto preencherInformacoesBasicasCIP(short numBanco, String codigoBarras, Date dataPagamento, Integer numCooperativa, Short idCanal, Integer numPA,
            boolean novoFluxo) throws ComumNegocioException, ComumException {
        debug("### Preenchendo informa��es b�sicas da CIP...");

        ConsultaBoletoDDADto dto = preencherInformacoesBasicas(numBanco, codigoBarras, dataPagamento);

        preencherInformacoesExtras(numCooperativa, codigoBarras, idCanal, numPA, dto);

        /*
         * Se veio da conting�ncia significa que o boleto pode estar na CIP. Neste caso, indica que � o novo fluxo para que envie uma baixa operacional se for
         * pagamento. Caso n�o esteja em conting�ncia, significa que tentou obter o boleto na CIP e n�o encontrou. Sendo assim, n�o deve enviar baixa
         * operacional para pagamento.
         */
        dto.setBolNovoFluxo(novoFluxo);
        debug("Novo fluxo: " + novoFluxo);

        dto.setBolPagamentoParcial(false);

        return dto;
    }

    /**
     * M�todo respons�vel por obter o boleto na CIP
     * 
     * @param codigoBarras
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param idInstituicao
     * @param dataPagamento
     * @param numBanco
     * @param valorPagamento
     * @param numIdentificadorBoletoCIP
     * @param isAgendamentoPagamento
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto obterBoletoCIP(String codigoBarras, Short idCanal, Integer numPA, Integer numCooperativa, int idInstituicao, Date dataPagamento, short numBanco,
            BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException {
        debug("### Obtendo BoletoDDA da CIP...");
        debug("Par�metro - codigoBarras: " + codigoBarras);

        try {
            BoletoDDA boletoDDA = getMensagemConsultaBoletoPagamentoDelegate().obterBoletoDDA(codigoBarras, idInstituicao, getUsuarioLogado(), idCanal);
            debug("BoletoDDA obtido da CIP: " + boletoDDA);

            // Se tiver informado o numIdentificadorBoletoCIP e for diferente do retornado pela CIP
            if (!ObjectUtil.isEmpty(numIdentificadorBoletoCIP) && !numIdentificadorBoletoCIP.equals(boletoDDA.getNumIdentificadorBoletoCip())) {
                throw new ConsultaBoletoNegocioException("integracaocip.pagamento.nao.permitido.boleto.divergente");
            }

            /*
             * � necess�rio obter algumas informa��es complementares que n�o vem no retorno do servi�o de BoletoDDA, entre eles, a situa��o do boleto e a
             * situa��o para pagamento e as listas de desconto e grupo de c�lculo
             */
            boletoDDA = dao.complementarDadosBoletoDDA(boletoDDA);

            return criarConsultaBoletoDDADto(boletoDDA, dataPagamento, numCooperativa, numPA, numBanco, valorPagamento, Boolean.FALSE, Boolean.FALSE, isAgendamentoPagamento,
                    Boolean.TRUE, idCanal);
        } catch (ErroCIPNegocioException e) {
            // Se n�o permitir pagar o boleto sem ter encontrado na CIP
            if (!autorizaPagarBoletoDDANaoEncontradoNaCIP(codigoBarras, numBanco)) {
                throw new BoletoCIPNaoEncontradoException(e.getMessage(), e);
            }
        }

        validarPagamentoSemRegistro(idInstituicao);

        // Obt�m as informa��es do c�digo de barras, visto que n�o encontrou na CIP e permite seguir pelo fluxo antigo
        return preencherInformacoesBasicasCIP(numBanco, codigoBarras, dataPagamento, numCooperativa, idCanal, numPA, Boolean.FALSE);
    }

    /**
     * M�todo respons�vel por verificar se permite pagar o boleto que n�o foi encontrado na CIP.
     * 
     * Caso n�o encontre o boleto na CIP ser� feita a valida��o para verificar se permite seguir pelo fluxo antigo (ler o CB) ou se lan�a a exce��o (no caso de
     * ter acabado o per�odo de piloto do valor na CIP, ou seja, todos os boletos desse valor devem estar na CIP).
     * 
     * @param codigoBarras
     * @param numBanco
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private boolean autorizaPagarBoletoDDANaoEncontradoNaCIP(String codigoBarras, short numBanco) throws ComumNegocioException, ComumException {
        // Caso seja IF 988 n�o permite pagar
        if (numBanco == Constantes.NUM_INSTITUICAO_FINANCEIRA) {
            return false;
        }

        double valorBoleto = LinhaDigitavelCodigoBarrasUtil.obterValorTituloPorCodigoBarras(codigoBarras);
        debug("Valor do boleto na barra: " + valorBoleto);

        double valorMaxPermitido = obterValorMaxConsultaCIP();

        /*
         * Caso valor do boleto seja menor que o par�metro ent�o permite seguir pelo fluxo antigo e obter os dados da linha digit�vel, caso contr�rio, deve
         * lan�ar a exce��o
         */
        return valorBoleto < valorMaxPermitido;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException double
     * 
     */
    private double obterValorMaxConsultaCIP() throws ComumException {
        // Indica o valor dos boletos obrigatoriamente j� est�o na CIP
        double valorMaxPermitido;
        if (isCacheHabilitado) {
            valorMaxPermitido = getParametroDelegate().obterValorDouble(ParametroDDA.VALOR_MAX_CONSULTA_PILOTO_CIP);
        } else {
            valorMaxPermitido = parametroDAO.obterValorDouble(ParametroDDA.VALOR_MAX_CONSULTA_PILOTO_CIP, Constantes.ID_BANCOOB);
        }
        debug("Valor m�ximo permitido: " + valorMaxPermitido);
        return valorMaxPermitido;
    }

    /**
     * M�todo respons�vel por pesquisar o boleto DDA, se encontrar verifica:
     * <ul>
     * <li>Se o banco = 756; ou</li>
     * <li>Se est� no prazo de validade da consulta; ou</li>
     * <li>Se � pagador eletr�nico.</li>
     * </ul>
     * Se for retorna o objeto encontrado, caso contr�rio retorna <code>null</code>
     * 
     * @param codigoBarras
     * @param dataPagamento
     * @param numCooperativa
     * @param numBanco
     * @param idCanal
     * @param valorPagamento
     * @param numIdentificadorBoletoCIP
     * @param isAgendamentoPagamento
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private ConsultaBoletoDDADto obterBoletoDDAValido(String codigoBarras, Date dataPagamento, int numCooperativa, int numPA, short numBanco, Short idCanal,
            BigDecimal valorPagamento, Long numIdentificadorBoletoCIP, boolean isAgendamentoPagamento) throws ComumNegocioException, ComumException {
        debug("### Obtendo boleto DDA v�lido...");

        BoletoDDA boletoDDA = dao.obterBoletoDDA(codigoBarras, numIdentificadorBoletoCIP, Boolean.TRUE);
        debug("BoletoDDA recuperado: " + boletoDDA);

        if (!ObjectUtil.isNull(boletoDDA)) {
            boolean isBoleto756 = (numBanco == Constantes.NUM_BANCOOB);
            boolean isPagadorEletronicoSicoob = Boolean.FALSE;

            if (!isBoleto756) {
                isPagadorEletronicoSicoob = isPagadorEletronicoSicoob(boletoDDA);
            }

            // Se for banco 756, pagador eletr�nico ou se estiver no prazo de validade da consulta
            if (isBoleto756 || isPagadorEletronicoSicoob || isBoletoDDAValido(boletoDDA.getDataHoraUltimaAtualizacao())) {
                // Verifca se o valor do boleto no CB est� na onda
                boolean estaNaOnda = LinhaDigitavelCodigoBarrasUtil.obterValorTituloPorCodigoBarras(codigoBarras) >= obterValorBoletoNaCIP();
                debug("Boleto est� na onda: " + estaNaOnda);

                return criarConsultaBoletoDDADto(boletoDDA, dataPagamento, numCooperativa, numPA, numBanco, valorPagamento, isBoleto756, isPagadorEletronicoSicoob,
                        isAgendamentoPagamento, estaNaOnda, idCanal);
            }
        }

        return null;
    }

    /**
     * M�todo respons�vel por verificar se � boleto de pagador eletr�nico ou de agregado.
     * 
     * @param codTipoPessoaPagador
     * @param numCpfCnpjPagador
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean isPagadorEletronicoSicoob(BoletoDDA boletoDDA) throws ComumException {
        Character codTipoPessoaPagador = ObjectUtil.converterValor(boletoDDA.getCodTipoPessoaPagador());

        boolean isPagadorEletronicoSicoob = pagadorEletronicoDDADao.isPagadorEletronicoSicoob(codTipoPessoaPagador, boletoDDA.getNumCpfCnpjPagador());
        debug("Pagador eletr�nico: " + isPagadorEletronicoSicoob);

        return isPagadorEletronicoSicoob;
    }

    /**
     * M�todo respons�vel por preencher o dto ConsultaBoletoDDADto com os dados do boletoDDA
     * 
     * @param boletoDDA
     * @param dataPagamento
     * @param numCooperativa
     * @param numBanco
     * @param valorPagamento
     * @param isBanco756
     * @param isPagadorEletronico
     * @param isAgendamentoPagamento
     * @param estaNaOnda
     * @param idCanal
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private ConsultaBoletoDDADto criarConsultaBoletoDDADto(BoletoDDA boletoDDA, Date dataPagamento, int numCooperativa, int numPA, short numBanco, BigDecimal valorPagamento,
            boolean isBanco756, boolean isPagadorEletronico, boolean isAgendamentoPagamento, boolean estaNaOnda, Short idCanal) throws ComumException, ComumNegocioException {
        debug("### Criando ConsultaBoletoDDADto para retorno...");

        debug("� boleto 756: " + isBanco756);
        debug("� boleto de pagador eletr�nico: " + isPagadorEletronico);

        validaModeloCalculo(boletoDDA, dataPagamento, isBanco756, estaNaOnda);

        tratarDataVencimento(boletoDDA, dataPagamento);

        tratarDataLimitePagamento(boletoDDA, dataPagamento, numCooperativa, numPA);

        ConsultaBoletoDDADto dto = new ConsultaBoletoDDADto();
        dto.setDataPagamento(new DateTimeDB(dataPagamento.getTime()));

        Date dataVencimentoUtil = obterDataVencimentoUtil(boletoDDA.getDataVencimento(), numCooperativa, numPA, true);

        // Define a data de vencimento �til que ser� utilizada no pagamento
        dto.setDataVencimentoUtil(new DateTimeDB(dataVencimentoUtil.getTime()));

        // Indica se o boleto est� vencido
        boolean isBoletoVencido = dataVencimentoUtil.before(dataPagamento);
        debug("Boleto est� vencido: " + isBoletoVencido);

        boolean isCartaoCredito = !ObjectUtil.isEmpty(boletoDDA.getIdEspecieDocumento())
                && boletoDDA.getIdEspecieDocumento() == EspecieDocumentoDeParaEnum.CARTAO_CREDITO.getIdEspecieDocumentoCip();
        debug("� cart�o de cr�dito: " + isCartaoCredito);

        boolean isBoletoProposta = !ObjectUtil.isEmpty(boletoDDA.getIdEspecieDocumento())
                && boletoDDA.getIdEspecieDocumento() == EspecieDocumentoDeParaEnum.BOLETO_PROPOSTA.getIdEspecieDocumentoCip();
        debug("� boleto proposta: " + isBoletoProposta);

        definirCamposBasicos(boletoDDA, dto, numBanco, isBoletoVencido, isCartaoCredito);

        // Se a situa��o do boleto for "em aberto" permite fazer alguns c�lculos
        boolean isBoletoEmAberto = boletoDDA.getCodSituacaoBoleto().equals(SituacaoBoleto.ABERTO);
        debug("Boleto est� com situa��o 'em aberto': " + isBoletoEmAberto);

        definirValorSaldoPagamento(boletoDDA, dto, isBoletoEmAberto);

        // Se permitir pagamento parcial e se for a �ltima parcela
        boolean isUltimaParcela = boletoDDA.getBolPagamentoParcial() && boletoDDA.getQtdPagamentoRestante() == 1;
        debug("�ltima parcela: " + isUltimaParcela);

        definirValorBoleto(boletoDDA, dto, isUltimaParcela, isBoletoVencido);

        // Permite valor divergente se n�o estiver na onda ou se o boleto permitir

        boolean permiteValorDivergente = validaPermiteValorDivergente(boletoDDA, estaNaOnda);

        dto.setPermiteValorDivergente(permiteValorDivergente);

        /*
         * Se estiver na onda e n�o for pagamento de cart�o de cr�dito nem boleto proposta e n�o aceitar pagamento divergente ou aceitar pagamento parcial e for
         * a �ltima parcela ou se estiver vencido e n�o for modelo de c�lculo 03 dever� pagar o valor integral/residual
         * 
         * - Para casos de pagamentos parciais de boletos cuja parcela seja a �ltima poss�vel cadastrada, dever� a Institui��o Recebedora liquid�-lo
         * obrigatoriamente pelo valor residual
         * 
         * - Pagamentos parciais de boletos vencidos dever�o ser recebidos em sua integralidade ap�s o vencimento, salvo boletos com modelo de c�lculo 03
         */
        boolean deveEfetuarPagamentoIntegral = estaNaOnda
                && (!isCartaoCredito && !isBoletoProposta && (!permiteValorDivergente || (boletoDDA.getBolPagamentoParcial() && (isUltimaParcela
                        || (isBoletoVencido && !boletoDDA.getCodTipoModeloCalculo().equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS))))));
        debug("Deve efetuar pagamento integral/residual: " + deveEfetuarPagamentoIntegral);

        // Permite alterar o valor se N�O deve efetuar o pagamento integral e se o boleto aceitar valor divergente
        dto.setPermiteAlterarValor(!deveEfetuarPagamentoIntegral && permiteValorDivergente);
        debug("Permite alterar o valor dos campos: " + dto.getPermiteAlterarValor());

        /*
         * Se N�O tiver efetuado algum pagamento considera que est� fazendo um pagamento integral para que possa calcular os descontos, multas e mora.
         */
        boolean pagamentoIntegral = ObjectUtil.isEmpty(boletoDDA.getValorPago());
        debug("Pagamento integral: " + pagamentoIntegral);

        definirEncargosDescontos(numCooperativa, numPA, boletoDDA, dataPagamento, dto, isUltimaParcela, isBoletoVencido, pagamentoIntegral, deveEfetuarPagamentoIntegral,
                isBanco756, isPagadorEletronico, isBoletoEmAberto, isCartaoCredito, estaNaOnda);

        realizarCalculoValorPagamento(dto, isBoletoEmAberto);

        definirInstrucaoValorMinMax(dto, boletoDDA, isUltimaParcela, isCartaoCredito, estaNaOnda);

        /*
         * Se informou o valor e for uma efetiva��o de um agendamento e N�O permitir valor divergente e o valor de pagamento informado for diferente do valor
         * que deve ser pago, definir� o valor de pagamento como sendo o valor a pagar do boleto para que n�o caia nas valida��es que impedem pagamento
         * divergente. E no momento do pagamento far� o tratamento necess�rio para enviar o novo valor para a SP de pagamento
         */
        if (!ObjectUtil.isEmpty(valorPagamento) && !isAgendamentoPagamento && !permiteValorDivergente && (valorPagamento.doubleValue() != dto.getValorPagamento().doubleValue())) {
            debug("Alterando o valor de pagamento para efetiva��o de agendamento. Valor antigo: " + valorPagamento + " - Novo valor: " + dto.getValorPagamento());

            // Define o valor de pagamento como sendo o valor a pagar ao inv�s de utilizar o valor agendado
            valorPagamento = dto.getValorPagamento();
        }

        definirBloqueioPagamento(dto, boletoDDA, dataPagamento, valorPagamento, isBoletoVencido, isCartaoCredito, isBanco756, permiteValorDivergente, idCanal);

        return dto;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @param estaNaOnda
     * @return
     * @throws ComumException boolean
     * 
     */
    @SuppressWarnings("deprecation")
    private boolean validaPermiteValorDivergente(BoletoDDA boletoDDA, boolean estaNaOnda) throws ComumException {
        boolean autorizadoValorDivergente;
        if (isCacheHabilitado) {
            autorizadoValorDivergente = obterAutorizacaoValorDivergente(boletoDDA.getCodAutorizacaoValorDivergente());
        } else {
            autorizadoValorDivergente = dao.permiteAlterarValor(boletoDDA.getCodAutorizacaoValorDivergente());
        }

        boolean permiteValorDivergente = !estaNaOnda || autorizadoValorDivergente;
        debug("Permite valor divergente: " + permiteValorDivergente);
        return permiteValorDivergente;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean obterAutorizacaoValorDivergente(String codAutorizacaoValorDivergente) throws ComumException {
        List<AutorizacaoValorDivergente> listaAutorizacao = getDominioDDADelegate().listarAutorizacaoDivergente();
        for (AutorizacaoValorDivergente autorizacaoValorDivergente : listaAutorizacao) {
            if (autorizacaoValorDivergente.getCodAutorizacaoValorDivergente().equals(codAutorizacaoValorDivergente)) {
                return autorizacaoValorDivergente.getBolPermiteAlterarValor();
            }
        }
        return Boolean.FALSE;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @param dataPagamento
     * @param isBanco756
     * @param estaNaOnda
     * @throws ComumNegocioException void
     * 
     */
    private void validaModeloCalculo(BoletoDDA boletoDDA, Date dataPagamento, boolean isBanco756, boolean estaNaOnda) throws ComumNegocioException {
        // Se n�o possuir tipo de modelo de c�lculo lan�a exception
        if (ObjectUtil.isEmpty(boletoDDA.getCodTipoModeloCalculo())) {
            throw new ComumNegocioException("integracaocip.tipo.modelo.calculo.nulo");
        } else {
            debug("Modelo de c�lculo definindo no boletoDDA: " + boletoDDA.getCodTipoModeloCalculo());

            // Se estiver na onda e for diferente de 756 e o pagamento n�o for pra hoje, ou seja, se for um agendamento e o modelo de c�lculo for 03
            if (estaNaOnda && (!isBanco756 && !dataPagamento.equals(DateUtil.obterDataSemHora(new Date()))
                    && boletoDDA.getCodTipoModeloCalculo().equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS))) {
                throw new ComumNegocioException("integracaocip.tipo.modelo.calculo.nao.permite.agendamento");
            }
        }
    }

    /**
     * M�todo respons�vel por obter a data de vencimento �til. Se <code>validarFeriadoLocal = true</code> valida tamb�m se � feriado local.
     * 
     * @param dataVencimento
     * @param numCooperativa
     * @param numPA
     * @param validarFeriadoLocal
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private Date obterDataVencimentoUtil(DateTimeDB dataVencimento, int numCooperativa, int numPA, boolean validarFeriadoLocal) throws ComumException, ComumNegocioException {
        debug("### Obtendo a data de vencimento �til...");

        // TODO - Verificar a necessidade de SP
        Date dataVencimentoUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, dataVencimento, validarFeriadoLocal);
        debug("Data de vencimento �til: " + dataVencimentoUtil);

        // Remove a data para o caso da SP retornar com data
        return DateUtil.obterDataSemHora(dataVencimentoUtil);
    }

    /**
     * M�todo respons�vel por definir o saldo para pagamento.
     * 
     * @param boletoDDA
     * @param dto
     * @param isBoletoEmAberto
     * @throws ComumException
     */
    private void definirValorSaldoPagamento(BoletoDDA boletoDDA, ConsultaBoletoDDADto dto, boolean isBoletoEmAberto) throws ComumException {
        debug("### Definindo o valor de saldo para pagamento...");

        // Se o boleto estiver em aberto permite fazer os c�lculos do saldo
        if (isBoletoEmAberto) {
            definirValorSaldoPagamento(boletoDDA, dto);
        }
        // Se o boleto estiver baixado, o valor do boleto ser� o nominal e o valor � pagar deve ser o ZERO
        else {
            dto.setValorSaldoPagamento(boletoDDA.getValorBoleto());
        }
    }

    /**
     * M�todo respons�vel por definir o valor saldo para pagamento baseado no que j� foi pago
     * 
     * @param boletoDDA
     * @param dto
     */
    private void definirValorSaldoPagamento(BoletoDDA boletoDDA, ConsultaBoletoDDADto dto) {
        // Obt�m o valor j� pago
        BigDecimal valorPago = boletoDDA.getValorPago();
        debug("Valor atualmente pago: " + valorPago);
        debug("Valor do boleto: " + boletoDDA.getValorBoleto());

        // Calcula o valor � pagar restante
        dto.setValorSaldoPagamento(boletoDDA.getValorBoleto().subtract(obterBigDecimal(valorPago)).setScale(SCALE_2, BigDecimal.ROUND_DOWN));
        debug("Definindo valor de saldo para pagamento: " + dto.getValorSaldoPagamento());
    }

    /**
     * M�todo respons�vel por definir uma data de vencimento caso essa seja nula.
     * 
     * @param boletoDDA
     * @param dataPagamento
     */
    private void tratarDataVencimento(BoletoDDA boletoDDA, Date dataPagamento) {
        debug("### Tratando a data de vencimento...");

        if (ObjectUtil.isNull(boletoDDA.getDataVencimento())) {
            debug("A data de vencimento est� nula. Definindo a data de vencimento como " + dataPagamento);

            // Se a data de vencimento do boleto for nula, utilizaremos a data de pagamento (e n�o a data de atual) como sendo a data de vencimento.
            boletoDDA.setDataVencimento(new DateTimeDB(dataPagamento.getTime()));
        }
    }

    /**
     * M�todo respons�vel por definir uma data limite de pagamento caso essa seja nula e definir a data �til limite para pagamento.
     * 
     * @param boletoDDA
     * @param dataPagamento
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void tratarDataLimitePagamento(BoletoDDA boletoDDA, Date dataPagamento, int numCooperativa, int numPA) throws ComumNegocioException, ComumException {
        debug("### Tratando a data limite para pagamento...");

        if (ObjectUtil.isNull(boletoDDA.getDataLimitePagamento())) {
            debug("A data limite para pagamento est� nula. Definindo a data limite como sendo a data de pagamento: " + dataPagamento);

            // Se a data limite estiver nula, utilizaremos a data de pagamento
            boletoDDA.setDataLimitePagamento(new DateTimeDB(dataPagamento.getTime()));
        }

        // TODO - Validar necessidade de executar a SP
        Date dataUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, boletoDDA.getDataLimitePagamento());
        debug("Data limite para pagamento �til: " + dataUtil);

        boletoDDA.setDataLimitePagamento(new DateTimeDB(dataUtil.getTime()));
    }

    /**
     * M�todo respons�vel por preencher os campos b�sicos do boletoDDA
     * 
     * @param boletoDDA
     * @param dto
     * @param numBanco
     * @param isBoletoVencido
     * @param isCartaoCredito
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void definirCamposBasicos(BoletoDDA boletoDDA, ConsultaBoletoDDADto dto, short numBanco, boolean isBoletoVencido, boolean isCartaoCredito)
            throws ComumNegocioException, ComumException {
        debug("### Definindo os campos b�sicos...");

        // Indica que deve seguir o fluxo de agendamento pela nova SP
        dto.setBolNovoFluxo(Boolean.TRUE);
        dto.setIspbParticipanteDestinatario(boletoDDA.getCodIspbPartDestinatario());
        dto.setTipoPessoaBeneficiario(ObjectUtil.converterValor(boletoDDA.getCodTipoPessoaBeneficiario()));
        dto.setNumCnpjCpfBeneficiario(boletoDDA.getNumCpfCnpjBeneficiario());
        dto.setNomeRazaoSocialBeneficiario(boletoDDA.getNomeBeneficiario());
        dto.setNomeFantasiaBeneficiario(boletoDDA.getNomeFantasiaBeneficiario());
        dto.setTipoPessoaSacadorAvalista(ObjectUtil.converterValor(boletoDDA.getCodTipoPessoaDDAAvalista()));
        dto.setNumCnpjCpfSacadorAvalista(obterCpfCnpj(boletoDDA.getNumCpfCnpjAvalista()));
        dto.setNomeRazaoSocialSacadorAvalista(boletoDDA.getNomeAvalista());
        dto.setTipoPessoaPagador(ObjectUtil.converterValor(boletoDDA.getCodTipoPessoaPagador()));
        dto.setNumCnpjCpfPagador(boletoDDA.getNumCpfCnpjPagador());
        dto.setNomeRazaoSocialPagador(boletoDDA.getNomePagador());
        dto.setNomeFantasiaPagador(boletoDDA.getNomeFantasiaPagador());
        dto.setNumCodigoBarras(boletoDDA.getNumCodigoBarra());
        dto.setNumNossoNumero(boletoDDA.getNumNossoNumero());
        dto.setNumDocumento(boletoDDA.getNumDocumento());
        dto.setNumIdentificadorBoletoCIP(boletoDDA.getNumIdentificadorBoletoCip());

        // A linha digit�vel n�o � obrigat�ria, se estiver vazia ser� gerada a linha digit�vel
        if (ObjectUtil.isEmpty(boletoDDA.getNumLinhaDigitavel())) {
            debug("Gerando a linha digit�vel...");
            dto.setNumLinhaDigitavel(LinhaDigitavelCodigoBarrasUtil.obterLinhaDigitavelPorCodigoBarras(boletoDDA.getNumCodigoBarra()));
        } else {
            dto.setNumLinhaDigitavel(boletoDDA.getNumLinhaDigitavel());
        }

        dto.setDataVencimentoBoleto(boletoDDA.getDataVencimento());
        dto.setQtdDiasProtesto(boletoDDA.getNumDiasProtesto());
        dto.setDataLimitePagamentoBoleto(boletoDDA.getDataLimitePagamento());
        // S� aceita pagamento parcial se for cart�o de cr�dito ou se o boleto permitir e n�o estiver vencido
        dto.setBolPagamentoParcial(isCartaoCredito || (boletoDDA.getBolPagamentoParcial() && !isBoletoVencido));
        dto.setNumRefAtualCadBoleto(boletoDDA.getNumRefAtualCadBoleto());

        dto.setNumInstituicaoEmissora(numBanco);
        dto.setNomeInstituicaoEmissora(obterNomeInstituicaoFinanceira(boletoDDA.getCodIspbPartDestinatario()));
    }

    /**
     * M�todo respons�vel por obter o cpf/cnpj tratado, pois est� ocorrendo o caso do cpf/cnpj vir como zero.
     * 
     * @param numCpfCnpjAvalista
     * @return
     */
    private String obterCpfCnpj(String cpfCnpj) {
        return ObjectUtil.isEmpty(cpfCnpj) ? null : (cpfCnpj.equals(Constantes.STRING_NUMERO_0) ? null : cpfCnpj);
    }

    /**
     * M�todo respons�vel por verificar se est� fazendo o pagamento integral, ou se deve efetuar o pagamento integral ou se � a �ltima parcela e se a esp�cie �
     * diferente de cart�o de cr�dito.<br>
     * <br>
     * <b>IMPORTANTE</b><br>
     * <li>N�o haver� incid�ncia de juros, multas, descontos ou abatimentos na fatura corrente para o tipo cart�o de cr�dito, sendo aplicadas na pr�xima
     * cobran�a para o cliente.</li><br>
     * <li>S� pode calcular encargos para pagamentos parciais se for um pagamento integral</li><br>
     * <li>Pagamentos parciais de boletos vencidos dever� liquidar o saldo devedor em um �nico pagamento APLICANDO JUROS SOBRE ESTE VALOR</li><br>
     * 
     * @param numCooperativa
     * @param numPA
     * @param boletoDDA
     * @param dataPagamento
     * @param dto
     * @param isUltimaParcela
     * @param isBoletoVencido
     * @param pagamentoIntegral
     * @param deveEfetuarPagamentoIntegral
     * @param isBanco756
     * @param isPagadorEletronico
     * @param isBoletoEmAberto
     * @param isCartaoCredito
     * @param estaNaOnda
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void definirEncargosDescontos(int numCooperativa, int numPA, BoletoDDA boletoDDA, Date dataPagamento, ConsultaBoletoDDADto dto, boolean isUltimaParcela,
            boolean isBoletoVencido, boolean pagamentoIntegral, boolean deveEfetuarPagamentoIntegral, boolean isBanco756, boolean isPagadorEletronico, boolean isBoletoEmAberto,
            boolean isCartaoCredito, boolean estaNaOnda) throws ComumException, ComumNegocioException {
        debug("### Tratando encargos e descontos...");

        /*
         * Se o boleto estiver em aberto e for um pagamento integral, ou se deve efetuar um pagamento integral ou se for a �ltima parcela e n�o for pagamento de
         * cart�o de cr�dito
         */
        if (isBoletoEmAberto && ((pagamentoIntegral || deveEfetuarPagamentoIntegral || isUltimaParcela) && !isCartaoCredito)) {
            /*
             * Para aplica��o de descontos e abatimentos em boletos que acatem pagamentos parciais, dever� a Institui��o Recebedora aplic�-los se o recebimento
             * for efetuado em parcela �nica, ou seja, n�o houver nenhum recebimento anterior para o boleto em quest�o.
             */
            if (pagamentoIntegral) {
                debug("Definindo valor de abatimento para pagamento integral");
                dto.setValorAbatimento(boletoDDA.getValorAbatimento());
            }

            String codTipoModeloCalculo = boletoDDA.getCodTipoModeloCalculo();
            BoletoDDAGrupoCalculo calculo = tratarObterGrupoCalculo(boletoDDA.getListaBoletoDDAGrupoCalculo(), dataPagamento, dto.getDataVencimentoBoleto(), isBoletoVencido,
                    isBanco756, isPagadorEletronico, codTipoModeloCalculo, numCooperativa, numPA, estaNaOnda);

            // Se possui grupo de c�lculo
            if (!ObjectUtil.isNull(calculo)) {
                definirEncargosDescontoInstituicaoDestinataria(calculo, dto);
            }
            // Se N�O for modelo de c�lculo 03 nem modelo de c�lculo 02 (para boletos vencidos)
            else if (!(codTipoModeloCalculo.equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS) || (isBoletoVencido
                    && codTipoModeloCalculo.equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_VENCIDOS_E_RECEBEDORA_CALCULA_TITULOS_A_VENCER)))) {
                definirEncargosDesconto(numCooperativa, numPA, boletoDDA, dataPagamento, dto, isBoletoVencido, pagamentoIntegral, codTipoModeloCalculo);
            }
        }

        // Define as instru��es independente do modelo de c�lculo e da situa��o do boleto
        definirInstrucoesDesconto(dto, boletoDDA);
    }

    /**
     * M�todo respons�vel por obter o grupo de c�lculo
     * 
     * @param listaBoletoDDAGrupoCalculo
     * @param dataPagamento
     * @param dataVencimento
     * @param isBoletoVencido
     * @param isBanco756
     * @param isPagadorEletronico
     * @param codTipoModeloCalculo
     * @param numPA
     * @param numCooperativa
     * @param estaNaOnda
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private BoletoDDAGrupoCalculo tratarObterGrupoCalculo(List<BoletoDDAGrupoCalculo> listaBoletoDDAGrupoCalculo, Date dataPagamento, DateTimeDB dataVencimento,
            boolean isBoletoVencido, boolean isBanco756, boolean isPagadorEletronico, String codTipoModeloCalculo, int numCooperativa, int numPA, boolean estaNaOnda)
            throws ComumNegocioException, ComumException {
        debug("### Tratando obter grupo de c�lculo...");

        // Se for 756 ou n�o for modelo de c�lculo '04' n�o possui grupo de c�lculo
        if (isBanco756 || codTipoModeloCalculo.equals(TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA)) {
            return null;
        }

        Date data = null;
        /*
         * Se o boleto n�o estiver vencido e a data de pagamento for superior a data do vencimento, define a data de vencimento �til (sem considerar o feriado
         * local) para obter o grupo de c�lculo v�lido, pois nesse caso o grupo de c�lculo v�lido � o que foi definido para o dia de vencimento �til.
         */
        if (!isBoletoVencido && dataPagamento.after(dataVencimento)) {
            debug("Definindo data de VENCIMENTO �til para obter grupo de c�lculo");
            data = obterDataVencimentoUtil(dataVencimento, numCooperativa, numPA, false);
        } else {
            debug("Definindo data de PAGAMENTO para obter grupo de c�lculo");
            data = dataPagamento;
        }

        BoletoDDAGrupoCalculo calculo = obterGrupoCalculoValido(listaBoletoDDAGrupoCalculo, data);

        // Se estiver na onda e for pagador eletr�nico e o modelo de c�lculo � 03 ou se o boleto estiver vencido e for modelo de c�lculo 02
        boolean devePossuirGrupoCalculo = estaNaOnda && isPagadorEletronico && (codTipoModeloCalculo
                .equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS)
                || (isBoletoVencido && codTipoModeloCalculo.equals(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_VENCIDOS_E_RECEBEDORA_CALCULA_TITULOS_A_VENCER)));
        debug("Deve possuir grupo de c�lculo: " + devePossuirGrupoCalculo);

        // Se deve possuir grupo de c�lculo e n�o possuir
        if (devePossuirGrupoCalculo && ObjectUtil.isNull(calculo)) {
            throw new ConsultaBoletoNegocioException("integracaocip.grupo.calculo.nao.encontrado", codTipoModeloCalculo);
        }

        return calculo;
    }

    /**
     * M�todo respons�vel por definir os encargos e descontos.
     * 
     * @param numCooperativa
     * @param numPA
     * @param boletoDDA
     * @param dataPagamento
     * @param dto
     * @param isBoletoVencido
     * @param pagamentoIntegral
     * @param codTipoModeloCalculo
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void definirEncargosDesconto(int numCooperativa, int numPA, BoletoDDA boletoDDA, Date dataPagamento, ConsultaBoletoDDADto dto, boolean isBoletoVencido,
            boolean pagamentoIntegral, String codTipoModeloCalculo) throws ComumException, ComumNegocioException {
        debug("### Definindo os encargos e desconto...");

        /*
         * Para aplica��o de descontos e abatimentos em boletos que acatem pagamentos parciais, dever� a Institui��o Recebedora aplic�-los se o recebimento for
         * efetuado em parcela �nica, ou seja, n�o houver nenhum recebimento anterior para o boleto em quest�o.
         */
        if (pagamentoIntegral) {
            debug("Definindo desconto para pagamento integral");
            definirDesconto(numCooperativa, numPA, dto, boletoDDA, codTipoModeloCalculo, dataPagamento, isBoletoVencido);
        }

        // Se estiver vencido calcula os encargos
        if (isBoletoVencido) {
            debug("Definindo encargos para boleto vencido do modelo de c�lculo: " + codTipoModeloCalculo);

            definirMulta(dto, boletoDDA, dataPagamento);
            definirJuros(numCooperativa, numPA, dto, boletoDDA, dataPagamento, codTipoModeloCalculo);
        }
    }

    /**
     * M�todo respons�vel por validar o valor do boleto.
     * 
     * @param boletoDDA
     * @param dto
     * @param isUltimaParcela
     * @param isBoletoVencido
     */
    private void definirValorBoleto(BoletoDDA boletoDDA, ConsultaBoletoDDADto dto, boolean isUltimaParcela, boolean isBoletoVencido) {
        debug("### Definindo o valor do boleto...");

        /*
         * Se for pagamento parcial e ainda houver saldo para pagamento e se for a �ltima parcela ou se o boleto estiver vencido ter� que pagar o valor de saldo
         * restante. Para boletos que n�o aceitem pagamento parcial, o valor ser� o valor total do boleto.
         * 
         * - Para casos de pagamentos parciais de boletos cuja parcela seja a �ltima poss�vel cadastrada, dever� a Institui��o Recebedora liquid�-lo
         * obrigatoriamente pelo valor residual, ainda que esta seja maior que o valor m�ximo permitido para recebimento ou menor que o m�nimo definido.
         * 
         * - Pagamentos parciais de boletos vencidos dever�o ser recebidos em sua integralidade ap�s o vencimento, ou seja, a Institui��o Recebedora dever�
         * liquidar o saldo devedor em um �nico pagamento aplicando juros sobre este valor, salvo boletos com tipo de modelo de c�lculo 03 (Institui��o
         * Destinat�ria atualizada diariamente valor do boleto vincendo e vencido) que por decis�o do GT permitir� recebimento do valor encaminhado pela
         * Institui��o Destinat�ria na atualiza��o di�ria da base.
         */
        if (boletoDDA.getBolPagamentoParcial() && !ObjectUtil.isEmpty(dto.getValorSaldoPagamento()) && (isUltimaParcela || isBoletoVencido)) {
            /*
             * Para boletos com pagamentos parciais, o valor do boleto deve ser o valor a pagar, j� descontando os montantes recebidos parcialmente.
             * 
             * Se o valor de saldo for menor que ZERO, define ZERO
             */
            if (dto.getValorSaldoPagamento().doubleValue() < 0) {
                debug("Definindo o valor do boleto com o valor ZERO pois o valor de saldo �: " + dto.getValorSaldoPagamento());
                dto.setValorBoleto(BigDecimal.ZERO);
            } else {
                debug("Definindo o valor do boleto com o valor de saldo para pagamento: " + dto.getValorSaldoPagamento());
                dto.setValorBoleto(dto.getValorSaldoPagamento());
            }
        } else {
            debug("Definindo o valor do boleto com o valor nominal: " + boletoDDA.getValorBoleto());
            dto.setValorBoleto(boletoDDA.getValorBoleto());
        }
    }

    /**
     * M�todo respons�vel por obter o c�lculo v�lido para a data informada.
     * 
     * @param listaBoletoDDAGrupoCalculo
     * @param data
     * @return
     */
    private BoletoDDAGrupoCalculo obterGrupoCalculoValido(List<BoletoDDAGrupoCalculo> listaBoletoDDAGrupoCalculo, Date data) {
        debug("### Obtendo grupo de c�lculo v�lido...");
        debug("Par�metro - data: " + data);

        if (!ObjectUtil.isEmpty(listaBoletoDDAGrupoCalculo)) {
            debug("Iterando a lista de grupo de c�lculo...");

            for (BoletoDDAGrupoCalculo boletoDDAGrupoCalculo : listaBoletoDDAGrupoCalculo) {
                debug("Comparando a data de validade do grupo de c�lculo. Data de validade do c�lculo: " + boletoDDAGrupoCalculo.getDataValidadeCalculo());

                if (DateUtil.igualA(data, boletoDDAGrupoCalculo.getDataValidadeCalculo())) {
                    debug("Grupo de c�lculo v�lido encontrado");
                    return boletoDDAGrupoCalculo;
                }
            }
        }

        return null;
    }

    /**
     * M�todo respons�vel por definir os encargos e descontos j� calculados pela institui��o destinat�ria.
     * 
     * @param calculo
     * @param dto
     * @param pagamentoIntegral
     * @param isBoletoVencido
     */
    private void definirEncargosDescontoInstituicaoDestinataria(BoletoDDAGrupoCalculo calculo, ConsultaBoletoDDADto dto) {
        debug("### Definindo encargos e descontos baseados no grupo de c�lculo");

        debug("Definindo desconto de pagamento integral");
        dto.setValorDesconto(calculo.getValorCalculadoDesconto());

        debug("Definindo encargos de boleto vencido");
        dto.setValorMora(calculo.getValorCalculadoJuros());
        dto.setValorMulta(calculo.getValorCalculadoMulta());

        // Limpa o abatimento, pois j� estar� calculado no valor do boleto, pois � responsabilidade da IF destinat�ria
        dto.setValorAbatimento(BigDecimal.ZERO);

        dto.setValorBoleto(
                calculo.getValorTotalCobrado().subtract(calculo.getValorCalculadoJuros()).subtract(calculo.getValorCalculadoMulta()).add(calculo.getValorCalculadoDesconto()));
        debug("Valor do boleto: " + dto.getValorBoleto());
    }

    /**
     * M�todo respons�vel por obter o desconto vigente, ou seja, o que � maior ou igual � data de pagamento.
     * 
     * @param listaBoletoDDADesconto
     * @param dataPagamento
     * @param numCooperativa
     * @param numPA
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private DescontoBoletoDDADto obterDescontoVigente(BoletoDDA boletoDDA, Date dataPagamento, int numCooperativa, int numPA) throws ComumNegocioException, ComumException {
        debug("### Obtendo desconto vigente...");
        DateTimeDB dataVencimento = boletoDDA.getDataVencimento();

        // Se for isento
        if (boletoDDA.isTipoDescontoIsento()) {
            return null;
        }

        List<DescontoBoletoDDADto> listaDesconto = preencherDescontos(boletoDDA);

        for (DescontoBoletoDDADto descontoBoletoDDADto : listaDesconto) {
            debug("### Validando desconto...");

            DescontoBoletoDDADto descontoBoleto = validarDescontoVigente(descontoBoletoDDADto, dataPagamento, dataVencimento, numCooperativa, numPA);

            if (!ObjectUtil.isNull(descontoBoleto)) {
                return descontoBoleto;
            }
        }

        return null;
    }

    /**
     * M�todo respons�vel por preencher a lista de desconto
     * 
     * @param boletoDDA
     * @return List<DescontoBoletoDDADto>
     */
    private List<DescontoBoletoDDADto> preencherDescontos(BoletoDDA boletoDDA) {
        debug("Preenchendo os descontos...");

        List<DescontoBoletoDDADto> listaDesconto = new ArrayList<>();

        DescontoBoletoDDADto desconto1 = obterBoletoDesconto1(boletoDDA);

        if (!ObjectUtil.isNull(desconto1)) {
            listaDesconto.add(desconto1);

            DescontoBoletoDDADto desconto2 = obterBoletoDesconto2(boletoDDA);

            if (!ObjectUtil.isNull(desconto2)) {
                listaDesconto.add(desconto2);

                DescontoBoletoDDADto desconto3 = obterBoletoDesconto3(boletoDDA);

                if (!ObjectUtil.isNull(desconto3)) {
                    listaDesconto.add(desconto3);
                }
            }
        }

        ordenarDescontos(boletoDDA.getDataVencimento(), listaDesconto);

        return listaDesconto;
    }

    /**
     * M�todo respons�vel por ordenar os descontos pela data
     * 
     * @param dataVencimento
     * @param listaDesconto
     */
    private void ordenarDescontos(DateTimeDB dataVencimento, List<DescontoBoletoDDADto> listaDesconto) {
        debug("Ordenando os descontos...");

        Collections.sort(listaDesconto, new Comparator<DescontoBoletoDDADto>() {
            @Override
            public int compare(DescontoBoletoDDADto d1, DescontoBoletoDDADto d2) {
                Date data1 = obterData(dataVencimento, d1.getDataDesconto());
                Date data2 = obterData(dataVencimento, d2.getDataDesconto());

                return data1.compareTo(data2);
            }

            private Date obterData(DateTimeDB dataVencimento, Date data) {
                if (ObjectUtil.isNull(data)) {
                    data = dataVencimento;
                }

                return data;
            }
        });
    }

    /**
     * M�todo respons�vel por
     * 
     * @param descontoBoleto
     * @param dataPagamento
     * @param dataVencimento
     * @param numCooperativa
     * @param numPA
     * @return
     * @throws ComumNegocioException
     * @throws ComumException DescontoBoletoDDADto
     * 
     */
    private DescontoBoletoDDADto validarDescontoVigente(DescontoBoletoDDADto descontoBoleto, Date dataPagamento, DateTimeDB dataVencimento, int numCooperativa, int numPA)
            throws ComumNegocioException, ComumException {

        if (ObjectUtil.isNull(descontoBoleto)) {
            return null;
        }

        DateTimeDB dataDesconto = descontoBoleto.getDataDesconto();
        debug("Data de desconto: " + dataDesconto);

        if (ObjectUtil.isNull(dataDesconto)) {
            debug("Definindo a data de desconto como sendo a data de vencimento do boleto: " + dataVencimento);
            dataDesconto = dataVencimento;
        }

        if (descontoBoleto.getCodTipoDesconto().equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_CORRIDO)
                || descontoBoleto.getCodTipoDesconto().equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_UTIL)
                || descontoBoleto.getCodTipoDesconto().equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_CORRIDO)
                || descontoBoleto.getCodTipoDesconto().equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_UTIL)) {
            dataDesconto = new DateTimeDB(DateUtil.decrementarData(dataDesconto, Calendar.DAY_OF_MONTH, 1).getTime());
        }

        Date dataDescontoUtil = agendamentoBoletoDAO.obterDataUtil(numCooperativa, numPA, dataDesconto);
        debug("Data de desconto (�til): " + dataDescontoUtil);

        // Se a data de desconto N�O for menor que a data de pagamento
        if (!dataDescontoUtil.before(dataPagamento)) {
            debug("Data de desconto vigente: " + dataDescontoUtil);
            return descontoBoleto;
        }

        debug("Desconto n�o vigente!");
        return null;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @return DescontoBoletoDDADto
     * 
     */
    private DescontoBoletoDDADto obterBoletoDesconto1(BoletoDDA boletoDDA) {
        // Se for desconto do tipo isento
        String codTipoDesconto1 = boletoDDA.getTipoDesconto1() == null ? null : boletoDDA.getTipoDesconto1().getCodTipoDesconto();
        debug("Tipo de desconto: " + codTipoDesconto1);
        return obterBoletoDesconto(boletoDDA.getDataDesconto1(), codTipoDesconto1, boletoDDA.getValorPercentualDesconto1());
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @return DescontoBoletoDDADto
     * 
     */
    private DescontoBoletoDDADto obterBoletoDesconto2(BoletoDDA boletoDDA) {
        String codTipoDesconto2 = boletoDDA.getTipoDesconto2() == null ? null : boletoDDA.getTipoDesconto2().getCodTipoDesconto();
        debug("Tipo de desconto2: " + codTipoDesconto2);
        return obterBoletoDesconto(boletoDDA.getDataDesconto2(), codTipoDesconto2, boletoDDA.getValorPercentualDesconto2());
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @return DescontoBoletoDDADto
     * 
     */
    private DescontoBoletoDDADto obterBoletoDesconto3(BoletoDDA boletoDDA) {
        String codTipoDesconto3 = boletoDDA.getTipoDesconto3() == null ? null : boletoDDA.getTipoDesconto3().getCodTipoDesconto();
        debug("Tipo de desconto3: " + codTipoDesconto3);
        return obterBoletoDesconto(boletoDDA.getDataDesconto3(), codTipoDesconto3, boletoDDA.getValorPercentualDesconto3());
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dataDesconto
     * @param codTipoDesconto
     * @param valorPercentualDesconto
     * @return DescontoBoletoDDADto
     * 
     */
    private DescontoBoletoDDADto obterBoletoDesconto(DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        if (ObjectUtil.isNull(codTipoDesconto) || ObjectUtil.isNull(valorPercentualDesconto)) {
            return null;
        }
        return new DescontoBoletoDDADto(dataDesconto, codTipoDesconto, valorPercentualDesconto);
    }

    /**
     * M�todo respons�vel por verificar se o pagamento estar� bloqueado ou n�o
     * 
     * @param dto
     * @param boletoDDA
     * @param dataPagamento
     * @param valorPagamento
     * @param isBoletoVencido
     * @param isCartaoCredito
     * @param isBanco756
     * @param permiteValorDivergente
     * @param idCanal
     * @throws ComumException
     */
    private void definirBloqueioPagamento(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, Date dataPagamento, BigDecimal valorPagamento, boolean isBoletoVencido,
            boolean isCartaoCredito, boolean isBanco756, boolean permiteValorDivergente, Short idCanal) throws ComumException {
        debug("### Tratar bloqueio do pagamento...");

        dto.setBloquearPagamento(Boolean.FALSE);

        if (!ObjectUtil.isEmpty(boletoDDA.getCodSituacaoBoletoPagamento())) {
            SituacaoBoletoPagamento situacao = obterSituacaoBoletoPagamento(boletoDDA.getCodSituacaoBoletoPagamento());

            dto.setSituacaoBoletoPagamento(situacao.getCodSituacaoBoletoPagamento());
            debug("Definindo situa��o do boleto pagamento: " + dto.getSituacaoBoletoPagamento());

            /*
             * Se possuir uma situa��o que bloqueie o pagamento
             * 
             * OBS: Se extrapolar o limite de parciais a situa��o bloquear� o pagamento
             */
            if (!situacao.getBolLiberadoRecebimento()) {
                debug("Bloqueado para pagamento: " + situacao.getDescMensagemErroPagamento());

                // Se n�o estiver liberado para receber
                dto.setBloquearPagamento(Boolean.TRUE);
                dto.setMensagemBloqueioPagamento(situacao.getDescMensagemErroPagamento());
                return;
            }
        }

        if (boletoDDA.getBolBloqueioPagamento()) {
            dto.setSituacaoBoletoPagamento(null);
            dto.setBloquearPagamento(Boolean.TRUE);
            dto.setMensagemBloqueioPagamento(MensagemUtil.getString("integracaocip.pagamento.nao.permitido.boleto.bloqueado"));

            debug("Bloqueado para pagamento: " + dto.getMensagemBloqueioPagamento());

            return;
        }

        // Se a data de pagamento for superior a data limite
        if (!ObjectUtil.isNull(boletoDDA.getDataLimitePagamento()) && dataPagamento.after(boletoDDA.getDataLimitePagamento())) {
            dto.setSituacaoBoletoPagamento(null);
            dto.setBloquearPagamento(Boolean.TRUE);
            dto.setMensagemBloqueioPagamento(MensagemUtil.getString("integracaocip.pagamento.nao.autorizado.data.limite.excedida"));

            debug("Bloqueado para pagamento: " + dto.getMensagemBloqueioPagamento());

            return;
        }

        double valorAbatimentoDesconto = obterBigDecimal(dto.getValorAbatimentoDesconto()).doubleValue();

        // Se o valor de abatimento e desconto for maior que zero e maior que o valor nominal do boleto
        if (valorAbatimentoDesconto > 0 && valorAbatimentoDesconto >= dto.getValorBoleto().doubleValue()) {
            dto.setSituacaoBoletoPagamento(null);
            dto.setBloquearPagamento(Boolean.TRUE);
            dto.setMensagemBloqueioPagamento(MensagemUtil.getString("integracaocip.pagamento.nao.permitido.soma.descontos.abatimentos.maior.igual.valor.boleto"));

            debug("Bloqueado para pagamento: " + dto.getMensagemBloqueioPagamento());

            return;
        }

        // Se n�o for caixa ou correspondente, n�o for cart�o de cr�dito 756, verifica se o boleto est� vencido e se a data de pagamento � para uma data futura
        if (!isCanalCaixaOuCorrespondente(idCanal) && !(isCartaoCredito && isBanco756) && (isBoletoVencido && dataPagamento.after(DateUtil.obterDataSemHora(new Date())))) {
            dto.setSituacaoBoletoPagamento(null);
            dto.setBloquearPagamento(Boolean.TRUE);
            dto.setMensagemBloqueioPagamento(MensagemUtil.getString("integracaocip.pagamento.nao.permitido.agendar.boleto.vencido.data.futura"));

            debug("Bloqueado para pagamento: " + dto.getMensagemBloqueioPagamento());

            return;
        }

        // Verifica se foi informado o valor de pagamento, se N�O permite valor divergente e se o valor � diferente do que foi retornado na consulta
        if (!ObjectUtil.isEmpty(valorPagamento) && !permiteValorDivergente && valorPagamento.doubleValue() != dto.getValorPagamento().doubleValue()) {
            dto.setSituacaoBoletoPagamento(null);
            dto.setBloquearPagamento(Boolean.TRUE);
            dto.setMensagemBloqueioPagamento(MensagemUtil.getString("integracaocip.pagamento.nao.autorizado.valor.divergente"));

            debug("Bloqueado para pagamento: " + dto.getMensagemBloqueioPagamento());

            return;
        }

        debug("Pagamento n�o bloqueado");
    }

    /**
     * M�todo respons�vel por
     * 
     * @param boletoDDA
     * @return
     * @throws ComumException SituacaoBoletoPagamento
     * 
     */
    @SuppressWarnings("deprecation")
    private SituacaoBoletoPagamento obterSituacaoBoletoPagamento(String codSituacaoBoletoPagamento) throws ComumException {
        if (isCacheHabilitado) {
            List<SituacaoBoletoPagamento> listaSituacao = getDominioDDADelegate().listarSituacaoBoletoPagamento();
            for (SituacaoBoletoPagamento situacaoBoletoPagamento : listaSituacao) {
                if (situacaoBoletoPagamento.getCodSituacaoBoletoPagamento().equals(codSituacaoBoletoPagamento)) {
                    return situacaoBoletoPagamento;
                }
            }
        }
        return dao.obterSituacaoBoleto(codSituacaoBoletoPagamento);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param numPA
     * @param numCooperativa
     * 
     * @param dto
     * @param boletoDDAJuros
     * @param dataPagamento
     * @param codTipoModeloCalculo
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void definirJuros(int numCooperativa, int numPA, ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, Date dataPagamento, String codTipoModeloCalculo)
            throws ComumException, ComumNegocioException {
        debug("### Tratando mora...");

        Date dataJuros = boletoDDA.getDataJuros();
        debug("Data da mora: " + dataJuros);

        short codTipoJuros = boletoDDA.getTipoJuros() == null ? null : boletoDDA.getTipoJuros().getId();
        debug("Tipo de mora: " + codTipoJuros);

        // Se possuir data e data de pagamento for anterior a data do juros n�o aplica os juros
        if (boletoDDA.isTipoJurosIsento() || DateUtil.menorQue(dataPagamento, dataJuros)) {
            return;
        }

        validarTipoJurosXModeloCalculo(codTipoJuros, codTipoModeloCalculo);

        BigDecimal qtdDias;
        /*
         * Verifica se � pra validar por dias corridos
         * 
         * IMPORTANTE: n�o chega aqui as situa��es de percentual de dias corridos/�teis para DIA e ANO, pois n�o � permitido pela CIP para a recebedora calcular
         * e � feita uma valida��o no m�todo validarTipoJurosXModeloCalculo
         */
        if (codTipoJuros == TipoJuros.VALOR_DIAS_CORRIDOS || codTipoJuros == TipoJuros.PERCENTUAL_AO_MES_DIAS_CORRIDOS) {
            // Dias corridos +1 (pois inclui o dia do in�cio dos juros)
            qtdDias = new BigDecimal(DateUtil.calcularDiferencaDiasSemTimezone(dataPagamento, dataJuros) + 1);
            debug("Quantidade de dias corridos: " + qtdDias);
        } else {
            // Dias �teis +1 (pois inclui o dia do in�cio dos juros)
            qtdDias = new BigDecimal(agendamentoBoletoDAO.obterQtdDiasUteis(numCooperativa, numPA, dataJuros, dataPagamento) + 1);
            debug("Quantidade de dias �teis: " + qtdDias);
        }

        BigDecimal valorPercentualJuros = boletoDDA.getValorPercentualJuros();
        BigDecimal valorMora = null;

        // Se for c�lculo por valor
        if (codTipoJuros == TipoJuros.VALOR_DIAS_CORRIDOS || codTipoJuros == TipoJuros.VALOR_DIA_UTIL) {
            debug("Definindo valor por dias corridos/�teis");
            valorMora = valorPercentualJuros.multiply(qtdDias);
        } else {
            if (codTipoJuros == TipoJuros.PERCENTUAL_AO_MES_DIAS_UTEIS || codTipoJuros == TipoJuros.PERCENTUAL_AO_MES_DIAS_CORRIDOS) {
                debug("Definindo percentual ao m�s por dias �teis/corridos");
                valorPercentualJuros = (valorPercentualJuros.divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN)).divide(Constantes.TOTAL_DIAS_MES,
                        Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN);
            }

            BigDecimal valorPercentualTruncado = valorPercentualJuros.multiply(qtdDias).setScale(SCALE_6, BigDecimal.ROUND_DOWN);
            debug("Valor/Percentual truncado: " + valorPercentualTruncado);

            // � feito o c�lculo utilizando o valor do boleto menos o abatimento
            valorMora = (dto.getValorBoleto().subtract(obterBigDecimal(dto.getValorAbatimento()))).multiply(valorPercentualTruncado);
        }

        // Valor calculado de multa: 2 (duas) casas decimais sem arredondamento (truncado)
        dto.setValorMora(valorMora.setScale(SCALE_2, BigDecimal.ROUND_DOWN));
        debug("Definindo valor de mora: " + dto.getValorMora());
    }

    /**
     * M�todo respons�vel por verificar se o tipo de juros x modelo de c�lculo est� de acordo com as regras abaixo, caso contr�rio lan�a exce��o:<br>
     * <br>
     * <table>
     * <tr>
     * <th>Tipo de juros</th>
     * <th>Modelo de c�lculo aplicado</th>
     * </tr>
     * <tr>
     * <td>Valor (dias corridos)</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Percentual ao dia (dias corridos)</td>
     * <td>02,03</td>
     * </tr>
     * <tr>
     * <td>Percentual ao m�s (dias corridos)</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Percentual ao ano (dias corridos)</td>
     * <td>02,03</td>
     * </tr>
     * <tr>
     * <td>Isento</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Valor (dia �til)</td>
     * <td>01,02,03</td>
     * </tr>
     * <tr>
     * <td>Percentual ao dia (dias �teis)</td>
     * <td>02,03</td>
     * </tr>
     * <tr>
     * <td>Percentual ao m�s (dias �teis)</td>
     * <td>01,02,03</td>
     * </tr>
     * <tr>
     * <td>Percentual ao ano (dias �teis)</td>
     * <td>02,03</td>
     * </tr>
     * </table>
     * 
     * @param codTipoJuros
     * @param codTipoModeloCalculo
     * @throws ComumNegocioException
     */
    private void validarTipoJurosXModeloCalculo(short codTipoJuros, String codTipoModeloCalculo) throws ComumNegocioException {
        debug("### Validando tipo de juros X modelo de c�lculo...");

        if (((codTipoJuros == TipoJuros.PERCENTUAL_AO_DIA_DIAS_CORRIDOS || codTipoJuros == TipoJuros.PERCENTUAL_AO_DIA_DIAS_UTEIS
                || codTipoJuros == TipoJuros.PERCENTUAL_AO_ANO_DIAS_CORRIDOS || codTipoJuros == TipoJuros.PERCENTUAL_AO_ANO_DIAS_UTEIS)
                && (codTipoModeloCalculo.equals(TipoModeloCalculo.INSTITUICAO_RECEBEDORA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS)
                        || codTipoModeloCalculo.equals(TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA)))
                || ((codTipoJuros == TipoJuros.VALOR_DIA_UTIL || codTipoJuros == TipoJuros.PERCENTUAL_AO_MES_DIAS_UTEIS)
                        && codTipoModeloCalculo.equals(TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA))) {
            throw new ComumNegocioException("integracaocip.modelo.calculo.nao.permitido.tipo.juros", codTipoModeloCalculo, codTipoJuros);
        }
    }

    /**
     * M�todo respons�vel por calcular a multa.
     * 
     * @param dto
     * @param boletoDDA
     * @param dataPagamento
     */
    private void definirMulta(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, Date dataPagamento) {
        debug("### Tratando multa...");

        Date dataMulta = boletoDDA.getDataMulta();
        debug("Data da multa: " + dataMulta);

        // Se possuir data e a data de pagamento for anterior a data da multa n�o aplica os juros
        if (DateUtil.menorQue(dataPagamento, dataMulta)) {
            return;
        }

        BigDecimal valorMulta = null;
        switch (boletoDDA.getTipoMulta().getId()) {
        case TipoMulta.VALOR_FIXO:
            debug("Definindo multa - valor fixo");
            valorMulta = boletoDDA.getValorPercentualMulta();
            break;
        case TipoMulta.PERCENTUAL:
            debug("Definindo multa - percentual");

            // � feito o c�lculo utilizando o valor do boleto menos o abatimento
            valorMulta = (dto.getValorBoleto().subtract(obterBigDecimal(dto.getValorAbatimento())))
                    .multiply((boletoDDA.getValorPercentualMulta().divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN)));
            break;
        default:
            debug("N�o haver� multa - isento");
            return;
        }

        // Valor calculado de multa: 2 (duas) casas decimais sem arredondamento (truncado)
        dto.setValorMulta(valorMulta.setScale(SCALE_2, BigDecimal.ROUND_DOWN));
        debug("Definindo multa: " + dto.getValorMulta());
    }

    /**
     * M�todo respons�vel por criar a instru��o do valor m�nimo e m�ximo para pagamento do boleto.
     * 
     * @param dto
     * @param boletoDDA
     * @param ultimaParcela
     * @param isCartaoCredito
     * @param estaNaOnda
     */
    private void definirInstrucaoValorMinMax(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, boolean ultimaParcela, boolean isCartaoCredito, boolean estaNaOnda) {
        debug("### Criando instru��es de valor m�nimo e m�ximo...");

        StringBuilder instrucao = new StringBuilder();

        definirInstrucaoValorMin(dto, boletoDDA, instrucao);

        definirInstrucaoValorMax(dto, boletoDDA, instrucao);

        /*
         * S� deve validar o valor m�nimo e m�ximo se estiver na onda e n�o for cart�o de cr�dito nem a �ltima parcela de um pagamento parcial.
         * 
         * - Se a esp�cie for CART�O DE CR�DITO os valores m�nimo e m�ximo servem apenas para refer�ncia.
         * 
         * - Para casos de pagamentos parciais de boletos cuja parcela seja a �ltima poss�vel cadastrada, dever� a Institui��o Recebedora liquid�-lo
         * obrigatoriamente pelo valor residual, ainda que esta seja maior que o valor m�ximo permitido para recebimento ou menor que o m�nimo definido.
         */
        dto.setValidarValorMinimoEMaximo(estaNaOnda && !isCartaoCredito && !ultimaParcela);
        debug("Validar valor m�n e m�x: " + dto.getValidarValorMinimoEMaximo());

        dto.setInstrucaoValorMinMax(instrucao.toString());
        debug("Instru��o de valor m�n e m�x: " + dto.getInstrucaoValorMinMax());

        // Define valor padr�o que pode ser alterado logo abaixo
        dto.setValidarValorMinimo(Boolean.FALSE);
        dto.setValidarValorMaximo(Boolean.FALSE);

        /*
         * Verifica se permite pagamento divergente e se � para validar o valor m�nimo e m�ximo, pois quando a esp�cie do documento for cart�o de cr�dito, ou se
         * for a �ltima parcela n�o � para validar os valores m�nimo e m�ximo.
         */
        if (!ObjectUtil.isNull(dto.getPermiteValorDivergente()) && dto.getPermiteValorDivergente() && dto.getValidarValorMinimoEMaximo()) {
            String codAutorizacaoValorDivergente = boletoDDA.getCodAutorizacaoValorDivergente();

            // Se n�o aceitar qualquer valor
            if (!codAutorizacaoValorDivergente.equals(AutorizacaoValorDivergente.QUALQUER_VALOR)) {
                // Deve validar o valor m�nimo
                dto.setValidarValorMinimo(Boolean.TRUE);

                // Se n�o for pra validar apenas o valor m�nimo
                if (!codAutorizacaoValorDivergente.equals(AutorizacaoValorDivergente.SOMENTE_VALOR_MINIMO)) {
                    // Deve validar o valor m�ximo
                    dto.setValidarValorMaximo(Boolean.TRUE);
                }
            }
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @param boletoDDA
     * @param instrucao void
     * 
     */
    private void definirInstrucaoValorMin(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, StringBuilder instrucao) {
        Character codIndicadorValorMin = ObjectUtil.converterValor(boletoDDA.getCodIndicadorValorMinimo());
        debug("CodIndicadorValorMin: " + codIndicadorValorMin);

        // Se houver o indicador e o valor preenchidos
        if (codIndicadorValorMin != null && !ObjectUtil.isNull(boletoDDA.getValorMinimo())) {
            if (codIndicadorValorMin == TipoPercentualValor.PERCENTUAL) {
                debug("Definindo instru��o por percentual (valor m�nimo)");

                instrucao.append("O percentual m�nimo � ");
                instrucao.append(FormatadorUtil.formatarPorcentagem(boletoDDA.getValorMinimo().doubleValue()));
                instrucao.append(". ");

                dto.setValorMinimo(boletoDDA.getValorMinimo().multiply(boletoDDA.getValorBoleto().subtract(obterBigDecimal(boletoDDA.getValorAbatimento())))
                        .divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_UNNECESSARY).setScale(SCALE_2, BigDecimal.ROUND_DOWN));
            } else if (codIndicadorValorMin == TipoPercentualValor.VALOR) {
                debug("Definindo instru��o por valor (valor m�nimo)");

                instrucao.append("O valor m�nimo � ");
                instrucao.append(FormatadorUtil.formatarValorReal(boletoDDA.getValorMinimo().doubleValue()));
                instrucao.append(". ");

                dto.setValorMinimo(boletoDDA.getValorMinimo());
            }

            debug("Valor m�nimo: " + dto.getValorMinimo());
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @param boletoDDA
     * @param instrucao void
     * 
     */
    private void definirInstrucaoValorMax(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, StringBuilder instrucao) {
        Character codIndicadorValorMax = ObjectUtil.converterValor(boletoDDA.getCodIndicadorValorMaximo());
        debug("CodIndicadorValorMax: " + codIndicadorValorMax);

        // Se houver o indicador e o valor preenchidos
        if (codIndicadorValorMax != null && !ObjectUtil.isNull(boletoDDA.getValorMaximo())) {
            if (codIndicadorValorMax == TipoPercentualValor.PERCENTUAL) {
                debug("Definindo instru��o por percentual (valor m�ximo)");

                instrucao.append("O percentual m�ximo � ");
                instrucao.append(FormatadorUtil.formatarPorcentagem(boletoDDA.getValorMaximo().doubleValue()));
                instrucao.append(". ");

                dto.setValorMaximo(boletoDDA.getValorMaximo().multiply(boletoDDA.getValorBoleto().subtract(obterBigDecimal(boletoDDA.getValorAbatimento())))
                        .divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_UNNECESSARY).setScale(SCALE_2, BigDecimal.ROUND_DOWN));
            } else if (codIndicadorValorMax == TipoPercentualValor.VALOR) {
                debug("Definindo instru��o por valor (valor m�ximo)");

                instrucao.append("O valor m�ximo � ");
                instrucao.append(FormatadorUtil.formatarValorReal(boletoDDA.getValorMaximo().doubleValue()));
                instrucao.append(". ");

                dto.setValorMaximo(boletoDDA.getValorMaximo());
            }

            debug("Valor m�ximo: " + dto.getValorMaximo());
        }
    }

    /**
     * M�todo respons�vel por definir as instru��es de desconto
     * 
     * @param dto
     * @param boletoDDA
     */
    private void definirInstrucoesDesconto(ConsultaBoletoDDADto dto, BoletoDDA boletoDDA) {
        debug("### Definindo as instru��es de desconto...");

        List<DescontoBoletoDDADto> listaDesconto = preencherDescontos(boletoDDA);

        if (ObjectUtil.isEmpty(listaDesconto)) {
            return;
        }

        if (listaDesconto.size() > 0) {
            debug("Definindo instru��o de desconto 1. Data: " + listaDesconto.get(0).getDataDesconto());
            dto.setInstrucaoDesconto1(obterInstrucaoDesconto(listaDesconto.get(0), dto.getDataVencimentoBoleto()));

            if (listaDesconto.size() > 1) {
                debug("Definindo instru��o de desconto 2. Data: " + listaDesconto.get(1).getDataDesconto());
                dto.setInstrucaoDesconto2(obterInstrucaoDesconto(listaDesconto.get(1), dto.getDataVencimentoBoleto()));

                if (listaDesconto.size() > 2) {
                    debug("Definindo instru��o de desconto 3. Data: " + listaDesconto.get(2).getDataDesconto());
                    dto.setInstrucaoDesconto3(obterInstrucaoDesconto(listaDesconto.get(2), dto.getDataVencimentoBoleto()));
                }
            }
        }
    }

    /**
     * M�todo respons�vel por realizar o c�lculo do desconto de acordo com o tipo.
     * 
     * @param numPA
     * @param numCooperativa
     * 
     * @param dto
     * @param boletoDDA
     * @param codTipoModeloCalculo
     * @param dataPagamento
     * @param isBoletoVencido
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void definirDesconto(int numCooperativa, int numPA, ConsultaBoletoDDADto dto, BoletoDDA boletoDDA, String codTipoModeloCalculo, Date dataPagamento,
            boolean isBoletoVencido) throws ComumException, ComumNegocioException {
        debug("### Calculando os descontos...");

        DescontoBoletoDDADto descontoVigente = obterDescontoVigente(boletoDDA, dataPagamento, numCooperativa, numPA);

        if (ObjectUtil.isNull(descontoVigente)) {
            return;
        }

        String codTipoDesconto = descontoVigente.getCodTipoDesconto();

        validarTipoDescontoXModeloCalculo(codTipoDesconto, codTipoModeloCalculo);

        BigDecimal valorDesconto = BigDecimal.ZERO;

        if (codTipoDesconto.equals(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA)) {
            debug("Definindo desconto - valor fixo at� a data informada");
            valorDesconto = descontoVigente.getValorPercentualDesconto();
        } else if (codTipoDesconto.equals(TipoDesconto.PERCENTUAL_ATE_A_DATA_INFORMADA)) {
            debug("Definindo desconto - percentual at� a data informada");
            // � feito o c�lculo utilizando o valor do boleto menos o abatimento
            valorDesconto = descontoVigente.getValorPercentualDesconto().multiply(dto.getValorBoleto().subtract(obterBigDecimal(dto.getValorAbatimento()))).divide(Constantes.CEM,
                    Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN);
        } else {
            // Se n�o informou data limite de desconto utiliza a data de vencimento do boleto
            DateTimeDB dataDesconto = ObjectUtil.isNull(descontoVigente.getDataDesconto()) ? dto.getDataVencimentoBoleto() : descontoVigente.getDataDesconto();

            // Se o boleto n�o estiver vencido e a data de pagamento for anterior a data de vencimento do desconto
            if (!isBoletoVencido && dataPagamento.before(dataDesconto)) {
                BigDecimal qtdDias = null;

                if (codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_CORRIDO) || codTipoDesconto.equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_CORRIDO)) {
                    // Dias corridos
                    qtdDias = new BigDecimal(DateUtil.calcularDiferencaDiasSemTimezone(dataDesconto, dataPagamento));
                    debug("Quantidade de dias corrido: " + qtdDias);
                } else {
                    // Dias �teis
                    qtdDias = new BigDecimal(agendamentoBoletoDAO.obterQtdDiasUteis(numCooperativa, numPA, dataPagamento, dataDesconto));
                    debug("Quantidade de dias �teis: " + qtdDias);
                }

                // Valor por antecipa��o dia corrido ou �til
                if (codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_CORRIDO) || codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_UTIL)) {
                    debug("Definindo desconto - valor por antecipa��o");
                    valorDesconto = descontoVigente.getValorPercentualDesconto().multiply(qtdDias);
                } else {
                    debug("Definindo desconto - percentual por antecipa��o");
                    // Percentual por antecipa��o dia corrido ou �til
                    // � feito o c�lculo utilizando o valor do boleto menos o abatimento
                    valorDesconto = descontoVigente.getValorPercentualDesconto().multiply(qtdDias)
                            .multiply(dto.getValorBoleto().subtract(obterBigDecimal(dto.getValorAbatimento())))
                            .divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN);
                }
            }
        }

        dto.setValorDesconto(valorDesconto.setScale(SCALE_2, BigDecimal.ROUND_DOWN));
        debug("Definindo desconto: " + dto.getValorDesconto());
    }

    /**
     * M�todo respons�vel por verificar se o tipo de desconto x modelo de c�lculo est� de acordo com as regras abaixo, caso contr�rio lan�a exce��o:<br>
     * <br>
     * <table>
     * <tr>
     * <th>Tipo de desconto</th>
     * <th>Modelo de c�lculo aplicado</th>
     * </tr>
     * <tr>
     * <td>Isento</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Valor Fixo at� a data informada</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Percentual at� a data informada</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Valor por antecipa��o dia corrido</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Valor por antecipa��o dia �til</td>
     * <td>01,02,03</td>
     * </tr>
     * <tr>
     * <td>Percentual por antecipa��o dia corrido</td>
     * <td>01,02,03,04</td>
     * </tr>
     * <tr>
     * <td>Percentual por antecipa��o dia �til</td>
     * <td>01,02,03</td>
     * </tr>
     * </table>
     * 
     * @param codTipoDesconto
     * @param codTipoModeloCalculo
     * @throws ComumNegocioException
     */
    private void validarTipoDescontoXModeloCalculo(String codTipoDesconto, String codTipoModeloCalculo) throws ComumNegocioException {
        debug("### Validando o tipo de desconto X modelo de c�lculo...");

        if ((codTipoDesconto.equals(TipoDesconto.VALOR_POR_ANTECIPACAO_DIA_UTIL) || codTipoDesconto.equals(TipoDesconto.PERCENTUAL_POR_ANTECIPACAO_DIA_UTIL))
                && codTipoModeloCalculo.equals(TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA)) {
            throw new ComumNegocioException("integracaocip.modelo.calculo.nao.permitido.tipo.desconto", codTipoModeloCalculo, codTipoDesconto);
        }
    }

    /**
     * M�todo respons�vel por obter a instru��o de desconto caso exista.
     * 
     * @param index
     * @param listaBoletoDDADesconto
     * @param dataVencimento
     * @return
     */
    private String obterInstrucaoDesconto(DescontoBoletoDDADto descontoBoleto, DateTimeDB dataVencimento) {
        debug("### Obtendo instru��o de desconto...");

        if (ObjectUtil.isNull(descontoBoleto)) {
            return null;

        }
        // Quando a data de desconto for nula utilizar� a data de vencimento do boleto
        return BoletoDDAUtil.obterInstrucao(descontoBoleto.getCodTipoDesconto(), descontoBoleto.getValorPercentualDesconto(),
                ObjectUtil.isNull(descontoBoleto.getDataDesconto()) ? dataVencimento : descontoBoleto.getDataDesconto());

    }

    /**
     * M�todo respons�vel por verificar se a data do BoletoDDA ainda est� v�lida.
     * 
     * @param dataBoletoDDA
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private boolean isBoletoDDAValido(Date dataBoletoDDA) throws ComumNegocioException, ComumException {
        debug("### Verificando se o BoletoDDA est� no per�odo v�lido...");
        debug("Par�metro - dataBoletoDDA: " + dataBoletoDDA);

        if (ObjectUtil.isNull(dataBoletoDDA)) {
            return Boolean.FALSE;
        }

        String valorParametro = obterValidaConsultaCIP();

        char tipoValidade = valorParametro.charAt(0);
        debug("Tipo de validade: " + tipoValidade);

        int tempo = Integer.parseInt(valorParametro.substring(1, valorParametro.length()));
        debug("Tempo de validade: " + tempo);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataBoletoDDA);

        debug("Data atual de validade do boletoDDA: " + calendar.getTime());

        switch (tipoValidade) {
        case Constantes.TIPO_VALIDADE_DIA:
            debug("Definindo atualiza��o da data por dia");
            calendar.add(Calendar.DATE, tempo);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            break;
        case Constantes.TIPO_VALIDADE_MINUTO:
            debug("Definindo atualiza��o da data por minuto");
            calendar.add(Calendar.MINUTE, tempo);
            break;
        default:
            throw new ComumNegocioException("integracaocip.erro.tipo.validade.desconhecido", tipoValidade);
        }

        debug("Data atualizada do boletoDDA: " + calendar.getTime());

        Date dataAtual = new Date();

        debug("Data atual: " + dataAtual);

        return dataAtual.before(calendar.getTime());
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    private String obterValidaConsultaCIP() throws ComumException {
        String valorParametro;
        if (isCacheHabilitado) {
            valorParametro = getParametroDelegate().obter(ParametroDDA.VALIDADE_CONSULTA_CIP).getValorParametro();
        } else {
            valorParametro = parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB);
        }
        debug("Par�metro " + ParametroDDA.VALIDADE_CONSULTA_CIP + ": " + valorParametro);
        return valorParametro;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarBoletoDDAPorContaCorrente(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDAContaCorrenteDto)
     */
    public List<BoletoDDADto> listarBoletoDDAPorContaCorrente(ConsultaBoletoDDAContaCorrenteDto dto) throws ComumNegocioException, ComumException {
        debug("### Listar BoletoDDA por conta corrente...");
        ObjectUtil.log(dto);

        validarConsultaBoletoDDA(dto);

        // Se o DDA estiver habilitado
        Integer idInstituicao = getInstituicaoDelegate().obterInstituicaoPorCooperativaView(dto.getNumeroCooperativa()).getIdInstituicao();
        debug("IdInstituicao: " + idInstituicao);

        List<ParticipanteContaDto> listaParticipanteContaDto = getContaCorrenteDelegate().listarParticipanteConta(idInstituicao, dto.getContaCorrente().longValue());
        debug("ListaParticipanteContaDto: " + listaParticipanteContaDto);

        Set<String> listaCpfCnpjContaCorrente = new HashSet<>();

        for (ParticipanteContaDto participanteContaDto : listaParticipanteContaDto) {
            PessoaDto pessoaDto = getCapesDelegate().obterPessoa(participanteContaDto.getIdPessoa(), idInstituicao);

            String cpfCnpj = pessoaDto.getCpfCnpj();
            debug("Cpf/Cnpj: " + cpfCnpj);

            boolean isPagadorEletronicoSicoob = pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(cpfCnpj, Boolean.TRUE);
            debug("Pagador eletr�nico: " + isPagadorEletronicoSicoob);

            if (isPagadorEletronicoSicoob) {
                listaCpfCnpjContaCorrente.add(cpfCnpj);
            }
        }

        if (!ObjectUtil.isEmpty(listaCpfCnpjContaCorrente)) {
            return dao.listarBoletoDDATransacaoCanais(listaCpfCnpjContaCorrente, dto.getDataInicial(), dto.getDataFinal(), dto.getCodSituacao(), dto.getNumeroCooperativa(),
                    dto.getContaCorrente());
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws NumberFormatException
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterBoletoDDA(java.lang.String, java.lang.Short)
     */
    public BoletoDDA obterBoletoDDA(String numCodigoBarra, Integer codSituacaoBoleto) throws ComumException {
        BoletoDDA boletoDDA = getDAO().obterBoletoDDA(numCodigoBarra, codSituacaoBoleto);
        if (!ObjectUtil.isNull(boletoDDA)) {

            try {
                boletoDDA.setDescBanco(obterNomeBanco(Short.valueOf(boletoDDA.getCodPartDestinatario())));
            } catch (ComumNegocioException e) {
                boletoDDA.setDescBanco("");
            }

            boletoDDA.setDescCarteira(CarteiraDeParaEnum.getDescricaoCarteiraCob(boletoDDA.getIdCarteira()));
            boletoDDA.setDescEspecie(EspecieDocumentoDeParaEnum.getDescricaoEspecieDocumentoCob(boletoDDA.getIdEspecieDocumento()));
            boletoDDA.setDescTipoPagamento(
                    ObjectUtil.isNull(boletoDDA.getCodTipoPagamento()) ? "" : em.find(TipoPagamento.class, boletoDDA.getCodTipoPagamento().shortValue()).getDescTipoPagamento());

            boletoDDA.setDescModeloCalculo(
                    ObjectUtil.isNull(boletoDDA.getCodTipoModeloCalculo()) ? "" : em.find(TipoModeloCalculo.class, boletoDDA.getCodTipoModeloCalculo()).getDescTipoModeloCalculo());
            boletoDDA
                    .setDescAutorizacaoValorDivergente(em.find(AutorizacaoValorDivergente.class, boletoDDA.getCodAutorizacaoValorDivergente()).getDescAutorizacaoValorDivergente());
            boletoDDA.setDescSituacaoBoleto(
                    ObjectUtil.isNull(boletoDDA.getCodSituacaoBoleto()) ? "" : em.find(SituacaoBoleto.class, boletoDDA.getCodSituacaoBoleto().toString()).getDescSituacaoBoleto());
            boletoDDA.setDescSituacaoBoletoPagamento(ObjectUtil.isNull(boletoDDA.getCodSituacaoBoletoPagamento()) ? ""
                    : em.find(SituacaoBoletoPagamento.class, boletoDDA.getCodSituacaoBoletoPagamento()).getDescSituacaoBoletoPagamento());
            if (!ObjectUtil.isNull(boletoDDA.getCodTipoPessoaDDAAvalista())) {
                boletoDDA.setDescTipoPessoaAvalista(em.find(TipoPessoaDDAAvalista.class, boletoDDA.getCodTipoPessoaDDAAvalista()).getDescTipoPessoaDDAAvalista());
            }

            boletoDDA.setNumIdentificadorBoletoCipStr(boletoDDA.getNumIdentificadorBoletoCip().toString());
            boletoDDA.setListaPagadorDDAAgregado(pagadorEletronicoDDADao.listarPagadorAgregadoDDA(boletoDDA.getNumCpfCnpjPagador()));

            if (!ObjectUtil.isNull(boletoDDA.getListaBoletoDDABaixaOper())) {
                for (BoletoDDABaixaOper boletoDDABaixaOper : boletoDDA.getListaBoletoDDABaixaOper()) {
                    try {
                        boletoDDABaixaOper.setDescBanco(ObjectUtil.isEmpty(boletoDDABaixaOper.getCodPartRecebedorBaixaOperacional()) ? ""
                                : obterNomeBanco(Short.valueOf(boletoDDABaixaOper.getCodPartRecebedorBaixaOperacional())));
                    } catch (ComumNegocioException e) {
                        boletoDDABaixaOper.setDescBanco("");
                    }
                    boletoDDABaixaOper.setDescCanalPagamento(ObjectUtil.isNull(boletoDDABaixaOper.getCodCanalPagamento()) ? ""
                            : em.find(CanalPagamentoDDA.class, boletoDDABaixaOper.getCodCanalPagamento()).getDescCanalPagamento());

                    boletoDDABaixaOper.setDescMeioPagamento(ObjectUtil.isNull(boletoDDABaixaOper.getCodMeioPagamento()) ? ""
                            : em.find(MeioPagamento.class, boletoDDABaixaOper.getCodMeioPagamento()).getDescMeioPagamento());

                    boletoDDABaixaOper.setDescTpBaixaOperacional(ObjectUtil.isNull(boletoDDABaixaOper.getCodTipoBaixaOperacional()) ? ""
                            : em.find(TipoBaixaOperacional.class, boletoDDABaixaOper.getCodTipoBaixaOperacional().shortValue()).getDescTipoBaixaOperacional());

                    boletoDDABaixaOper.setDescSituacaoBxOperacional(ObjectUtil.isNull(boletoDDABaixaOper.getCodSituacaoBaixaOperacional()) ? ""
                            : em.find(SituacaoBaixaOperacional.class, boletoDDABaixaOper.getCodSituacaoBaixaOperacional()).getDescSituacaoBaixaOperacional());
                }
            }

            if (!ObjectUtil.isNull(boletoDDA.getListaBoletoDDABaixaEfet())) {
                for (BoletoDDABaixaEfet boletoDDABaixaEfet : boletoDDA.getListaBoletoDDABaixaEfet()) {
                    try {
                        boletoDDABaixaEfet.setDescBanco(ObjectUtil.isEmpty(boletoDDABaixaEfet.getCodPartRecebedorBaixaEfetiva()) ? ""
                                : obterNomeBanco(Short.valueOf(boletoDDABaixaEfet.getCodPartRecebedorBaixaEfetiva())));
                    } catch (ComumNegocioException e) {
                        boletoDDABaixaEfet.setDescBanco("");
                    }
                    if (boletoDDABaixaEfet.getCodCanalPagamento() != null) {
                        boletoDDABaixaEfet.setDescCanalPagamento(em.find(CanalPagamentoDDA.class, boletoDDABaixaEfet.getCodCanalPagamento().shortValue()).getDescCanalPagamento());
                    }
                    if (boletoDDABaixaEfet.getCodMeioPagamento() != null) {
                        boletoDDABaixaEfet.setDescMeioPagamento(em.find(MeioPagamento.class, boletoDDABaixaEfet.getCodMeioPagamento().shortValue()).getDescMeioPagamento());
                    }
                    if (boletoDDABaixaEfet.getCodTipoBaixaEfetiva() != null) {
                        boletoDDABaixaEfet.setDescTpBaixaEfetiva(em.find(TipoBaixaEfetiva.class, boletoDDABaixaEfet.getCodTipoBaixaEfetiva().toString()).getDescTipoBaixaEfetiva());
                    }
                }
            }

            boletoDDA.getDescTextoInformativoSemEspaco();
        }
        return boletoDDA;
    }

    /**
     * M�todo respons�vel por validar o dto
     * 
     * @param dto
     * @throws ComumNegocioException
     */
    private void validarConsultaBoletoDDA(ConsultaBoletoDDAContaCorrenteDto dto) throws ComumNegocioException {
        if (ObjectUtil.isNull(dto)) {
            throw new ComumNegocioException("integracaocip.parametro.obrigatorio", "dto");
        } else if (ObjectUtil.isEmpty(dto.getContaCorrente())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "conta corrente");
        } else if (ObjectUtil.isNull(dto.getDataInicial())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "data inicial");
        } else if (ObjectUtil.isNull(dto.getDataFinal())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "data final");
        }

        if (ObjectUtil.isNull(dto.getCodSituacao())) {
            dto.setCodSituacao(Constantes.SITUACAO_BOLETO_AGENDADO);
        }

        debug("Removendo as horas das datas...");
        dto.setDataInicial(new DateTimeDB(DateUtil.obterDataSemHora(dto.getDataInicial()).getTime()));
        debug("Data inicial: " + dto.getDataInicial());
        dto.setDataFinal(new DateTimeDB(DateUtil.obterDataSemHora(dto.getDataFinal()).getTime()));
        debug("Data final: " + dto.getDataFinal());

        // Se a data inicial for maior que a data final
        if (dto.getDataInicial().after(dto.getDataFinal())) {
            throw new ComumNegocioException("integracaocip.data.final.deve.ser.maior.igual.data.inicial");
        }

        // Incrementa a data inicial utilizando LIMITE_DIAS_PESQUISA_BOLETO_DDA
        Date dataLimite = DataUtil.incrementarData(dto.getDataInicial(), Calendar.DATE, Constantes.LIMITE_DIAS_PESQUISA_BOLETO_DDA);

        // Se a data final for maior que a data limite
        if (dto.getDataFinal().after(dataLimite)) {
            throw new ComumNegocioException("integracaocip.consulta.boleto.periodo.maior.limite", Constantes.LIMITE_DIAS_PESQUISA_BOLETO_DDA);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarBoletoTerceiroAutorizadoDDA(java.lang.String,
     *      java.lang.String)
     */
    public List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP, String numCpfCnpjSolicitante)
            throws ComumNegocioException, ComumException {
        debug("### Listando terceiro autorizado DDA...");
        debug("Par�metro - numIdentificadorBoletoCIP: " + numIdentificadorBoletoCIP);
        debug("Par�metro - numCpfCnpjSolicitante: " + numCpfCnpjSolicitante);

        if (ObjectUtil.isEmpty(numCpfCnpjSolicitante)) {
            throw new ComumNegocioException("integracaocip.cpf.cnpj.solicitante.nao.informado");
        } else if (boletoCipDao.isBoletoVinculadoTerceiro(numCpfCnpjSolicitante, Long.parseLong(numIdentificadorBoletoCIP))) {
            throw new ComumNegocioException("integracaocip.terceiro.autorizado.boleto.vinculado.terceiro");
        }

        return dao.listarBoletoTerceiroAutorizadoDDA(numIdentificadorBoletoCIP);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarSituacaoBoleto()
     */
    public List<SituacaoBoleto> listarSituacaoBoleto() {
        return dao.listarSituacaoBoleto();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#listarSituacaoBoletoDDA()
     */
    public List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException {
        return dao.listarSituacaoBoletoDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#boletoEstaNaCIP(java.lang.String)
     */
    public Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException {
        return dao.boletoEstaNaCIP(numCodigoBarra);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#isParametroDDAAtivo()
     */
    public Boolean isParametroDDAAtivo() throws ComumNegocioException, ComumException {
        debug("### Verificando se a consulta boleto est� ativa...");

        Boolean ddaAtivo;

        if (isCacheHabilitado) {
            ddaAtivo = getParametroDelegate().obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO);
        } else {
            ddaAtivo = parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.ID_BANCOOB);
        }

        debug("DDA ativo: " + ddaAtivo);

        return ddaAtivo;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#isConsultaBoletoAtivo()
     */
    public Boolean isConsultaBoletoAtivo() throws ComumException {
        debug("### Verificando se a consulta boleto est� ativa...");
        Boolean consultaAtiva;
        if (isCacheHabilitado) {
            consultaAtiva = getParametroDelegate().obterValorBoolean(ParametroDDA.SICOOBDDA_CONSULTA_BOLETO_ATIVO);
        } else {
            consultaAtiva = parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_CONSULTA_BOLETO_ATIVO, Constantes.ID_BANCOOB);
        }

        debug("Consulta ativa: " + consultaAtiva);

        return consultaAtiva;
    }

    /**
     * M�todo respons�vel por obter o BoletoDDA <b>APENAS</b> para detalhamento.
     * 
     * @param codigoBarras
     * @param numIdentificadorBoletoCIP
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ConsultaBoletoDDADto
     * 
     */
    private ConsultaBoletoDDADto obterBoletoDDADetalhamento(String codigoBarras, Long numIdentificadorBoletoCIP, short numBanco) throws ComumException, ComumNegocioException {
        debug("### Obtendo boletoDDA para detalhamento...");

        ConsultaBoletoDDADto dto = obterBoletoDDA(codigoBarras, numIdentificadorBoletoCIP, numBanco);

        if (ObjectUtil.isNull(dto)) {
            throw new ComumNegocioException("integracaocip.boleto.nao.encontrado");
        }

        debug("Definindo campos extras...");

        realizarCalculoValorPagamento(dto, Boolean.TRUE);

        dto.setBolNovoFluxo(Boolean.FALSE);
        dto.setBloquearPagamento(Boolean.TRUE);
        dto.setPermiteAlterarValor(Boolean.FALSE);
        dto.setPermiteValorDivergente(Boolean.FALSE);
        dto.setValidarValorMinimoEMaximo(Boolean.FALSE);
        dto.setValidarValorMinimo(Boolean.FALSE);
        dto.setValidarValorMaximo(Boolean.FALSE);
        dto.setBloquearPagamento(Boolean.FALSE);

        return dto;
    }

    /**
     * M�todo respons�vel por obter o BoletoDDA <b>APENAS</b> para detalhamento
     * 
     * @param codigoBarras
     * @param numIdentificadorBoletoCIP
     * @param numBanco
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private ConsultaBoletoDDADto obterBoletoDDA(String codigoBarras, Long numIdentificadorBoletoCIP, short numBanco) throws ComumException, ComumNegocioException {
        debug("### Obtendo boletoDDA...");

        BoletoDDA boletoDDA = dao.obterBoletoDDA(codigoBarras, numIdentificadorBoletoCIP, Boolean.FALSE);

        if (ObjectUtil.isNull(boletoDDA)) {
            return null;
        }

        ConsultaBoletoDDADto dto = new ConsultaBoletoDDADto();

        definirCamposBasicos(boletoDDA, dto, numBanco, Boolean.FALSE, Boolean.FALSE);

        definirInstrucaoValorMinMax(dto, boletoDDA, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);

        dto.setValorBoleto(boletoDDA.getValorBoleto());

        return dto;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#consultarBoletoCIP(java.lang.String, java.lang.Short)
     */
    public void consultarBoletoCIP(String numCodigoBarra, Short idCanal, Short codSituacaoBoleto) throws ComumException, ComumNegocioException {
        if (boletoEncaminhadoCIP(numCodigoBarra, codSituacaoBoleto)) {
            throw new ComumNegocioException("consulta.boleto.cip.sem.retorno");
        }

        validaConsultaForaTempo(numCodigoBarra, codSituacaoBoleto);

        criarMensagens106(numCodigoBarra, idCanal, codSituacaoBoleto);

    }

    /**
     * M�todo respons�vel por criar as mensagens 106 de acordo com a situacao ou todas as situa��es
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @throws ComumException void
     * 
     */
    private void criarMensagens106(String numCodigoBarra, Short idCanal, Short codSituacaoBoleto) throws ComumException {
        if (ObjectUtil.isNull(codSituacaoBoleto)) {
            criarMensagens106(numCodigoBarra, idCanal);
        } else if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_EM_ABERTO) {
            criarConsultaBoleto106Aberto(numCodigoBarra, idCanal);
        } else if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_BAIXADO) {
            criarConsultaBoleto106Baixado(numCodigoBarra, idCanal);
        } else if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_LIQUIDADO) {
            criarConsultaBoleto106Liquidado(numCodigoBarra, idCanal);
        }

    }

    /**
     * M�todo respons�vel por criar todas a consulta com todas as situacaoes
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @throws ComumException void
     * 
     */
    private void criarMensagens106(String numCodigoBarra, Short idCanal) throws ComumException {
        criarConsultaBoleto106Aberto(numCodigoBarra, idCanal);
        criarConsultaBoleto106Baixado(numCodigoBarra, idCanal);
        criarConsultaBoleto106Liquidado(numCodigoBarra, idCanal);
    }

    /**
     * M�todo respons�vel por verificar se o codigo de barras ja foi encaminhado para CIP de acordo com o Parametro (), necessario para nao ter varias 106 em um
     * determinado tempo
     * 
     * 
     * @param numCodigoBarra
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    private void validaConsultaForaTempo(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException, ComumNegocioException {
        DateTimeDB dataHoraCadastro = dao.obterDataHoraEncaminhadoParaCIP(numCodigoBarra, codSituacaoBoleto);
        if (!ObjectUtil.isNull(dataHoraCadastro)) {
            throw new ComumNegocioException("consulta.boleto.cip.nao.permitido", DateUtil.formatarDataLocalePtBr(dataHoraCadastro));
        }
    }

    /**
     * M�todo respons�vel por verificar se o codigo de barras ja foi encaminhado para CIP
     * 
     * @param numCodigoBarra
     * @return
     * @throws ComumException Boolean
     * 
     */
    private Boolean boletoEncaminhadoCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException {
        return dao.boletoEncaminhadoCIP(numCodigoBarra, codSituacaoBoleto);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @throws ComumException void
     * 
     */
    private void criarConsultaBoleto106Aberto(String numCodigoBarra, Short idCanal) throws ComumException {
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.ABERTO);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @throws ComumException void
     * 
     */
    private void criarConsultaBoleto106Baixado(String numCodigoBarra, Short idCanal) throws ComumException {
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_POR_DECURSO_DE_PRAZO);
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_POR_ENVIO_PARA_PROTESTO);
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_POR_SOLICITACAO_DO_CEDENTE);

    }

    /**
     * M�todo respons�vel por
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @throws ComumException void
     * 
     */
    private void criarConsultaBoleto106Liquidado(String numCodigoBarra, Short idCanal) throws ComumException {
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_LIQUIDACAO_INTRABANCARIA);
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_POR_LIQUIDACAO_INTERBANCARIA);
        incluirConsultaMensagem106(numCodigoBarra, idCanal, (short) SituacaoBoleto.BAIXA_EFETIVA_POR_SOLICITACAO_DA_INSTITUICAO_DESTINATARIA);
    }

    /**
     * M�todo respons�vel por Incluir uma Mensagen 106 por codigo de barra e a situacao do Boelto, nao vai ser Onlinne, pois a prioridade esta setada como 10.
     * 
     * @param numCodigoBarra
     * @param idCanal
     * @param situacaoBoleto
     * @throws ComumException void s
     */
    private void incluirConsultaMensagem106(String numCodigoBarra, Short idCanal, Short situacaoBoleto) throws ComumException {
        getMensagemDDADelegate().incluir(
                new MensagemDDAConsultaBoleto(numCodigoBarra, situacaoBoleto,
                        numCodigoBarra.startsWith(Constantes.BANCOOB) ? TipoBoletoConsultado.PROPRIO : TipoBoletoConsultado.TERCEIRO),
                TipoMensagemDDA.DDA0106, new DateTimeDB(), Integer.valueOf(10), getUsuarioLogado(), getIdInstituicaoLogado(), idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#isConsultaBoletoHabilitadoPorInstituicao(java.lang.Integer)
     */
    public Boolean isConsultaBoletoCIPHabilitadoPorInstituicao(Integer idInstituicao) throws ComumException {
        return getParametroDelegate().obterValorParametroBoolean(ParametroDDA.HABILITA_CONSULTA_BOLETO_CIP_POR_INSTITUICAO, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#configurarRelatorioDetalheBoleto(br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA,
     *      java.lang.String, java.lang.Integer, br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioDetalheBoleto(BoletoDDA boletoDDA, String numCodigoBarra, Integer codSituacaoBoleto, UsuarioBancoobDTO usuario)
            throws BancoobException {

        if (ObjectUtil.isNull(boletoDDA)) {
            boletoDDA = obterBoletoDDA(numCodigoBarra, codSituacaoBoleto);
        }

        List<BoletoDDA> listaBoletoDDA = new ArrayList<>();
        listaBoletoDDA.add(boletoDDA);

        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA506, listaBoletoDDA, usuario, null);
        configurarRelatorio.setParametro("siglaCooperativa", OperacionalContextUtil.getInstituicao(usuario.getIdInstituicao()).getSiglaInstituicao());

        return configurarRelatorio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#configurarRelatorioConsultaBoleto(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioConsultaBoleto(PesquisaBoletoDDADto pesquisaBoletoDDADto, UsuarioBancoobDTO usuario) throws BancoobException {

        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra()) && !ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumLinhaDigitavel())) {
            pesquisaBoletoDDADto.setNumCodigoBarra(LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(pesquisaBoletoDDADto.getNumLinhaDigitavel()));
        }

        if (!ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra())) {
            pesquisaBoletoDDADto.setNumCodigoBarraCampoLivre(LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(pesquisaBoletoDDADto.getNumCodigoBarra()));
        }

        validaPesquisaBoletoDDADto(pesquisaBoletoDDADto);

        List<PesquisaBoletoDDADto> lista = dao.listarBoletoDDA(pesquisaBoletoDDADto);
        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA510, lista, usuario, null);
        configurarRelatorio.setParametro("siglaCooperativa", OperacionalContextUtil.getInstituicao(usuario.getIdInstituicao()).getSiglaInstituicao());

        return configurarRelatorio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#configurarRelatorioHistoricoMensagem(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioHistoricoMensagem(ListaHistoricoMensagem106Dto listaHistoricoMensagem106, UsuarioBancoobDTO usuario)
            throws BancoobException {
        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA511, listaHistoricoMensagem106, usuario, null);
        configurarRelatorio.setParametro("siglaCooperativa", OperacionalContextUtil.getInstituicao(usuario.getIdInstituicao()).getSiglaInstituicao());

        return configurarRelatorio;
    }

    /**
     * M�todo respons�vel por fazer a validacao do pesquisaBoletoDDADto
     * 
     * @param pesquisaBoletoDDADto void
     * 
     */
    private void validaPesquisaBoletoDDADto(PesquisaBoletoDDADto pesquisaBoletoDDADto) {
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCodigoBarra())) {
            pesquisaBoletoDDADto.setNumCodigoBarra(null);
        }
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCpfCnpjBeneficiario())) {
            pesquisaBoletoDDADto.setNumCpfCnpjBeneficiario(null);
        }
        if (ObjectUtil.isEmpty(pesquisaBoletoDDADto.getNumCpfCnpjPagador())) {
            pesquisaBoletoDDADto.setNumCpfCnpjPagador(null);
        }
    }

}
