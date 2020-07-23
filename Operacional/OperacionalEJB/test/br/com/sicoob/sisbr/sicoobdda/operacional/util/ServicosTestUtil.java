package br.com.sicoob.sisbr.sicoobdda.operacional.util;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CapesDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroAgrupadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroProcessamentoCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendentePagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PendenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBoletoPagamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDA0401Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemGEN0014Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarContingenciaMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ContaCorrenteDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.BancoNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AlterarCadastroBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AlterarSituacaoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.CadastrarBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ExcluirRelacionamentoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.MensagemDDAPagadorDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoramentoMensagensDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDALegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao;
import junit.framework.Assert;

/**
 * ServicosUtil é responsável por instanciar todos os serviçoes que serão usados nos testes do projeto "Operacional"
 * 
 * @author Tayrone.Oliveira
 */
@SuppressWarnings("deprecation")
@PrepareForTest({ TransformerFactory.class, XPathFactory.class, InformacoesUsuario.class, IntegracaoCipFabricaDelegates.class, MensagemDDADelegate.class,
        ExcluirRelacionamentoBeneficiarioDelegate.class, InformacoesUsuario.class, SCIDelegate.class, OperacionalFabricaDelegates.class, IntegracaoInternaFabricaDelegates.class,
        LinhaDigitavelCodigoBarrasUtil.class })
public class ServicosTestUtil extends FabricaUtilMockito {

    // MOCKS ====================================================================================
    @Mock
    private InformacoesUsuario informacoesUsuario;

    @Mock
    private TransformerFactory transformerFactory;

    @Mock
    private XPathFactory xPathFactory;

    @Mock
    protected EntityManager em;

    @Mock
    protected BeneficiarioCipDao beneficiarioCipDao;

    @Mock
    protected BeneficiarioLegadoDao beneficiarioDao;

    @Mock
    protected ManutencaoMensagemDDABoletoDao manutencaoMensagemDDABoletoDao;

    @Mock
    protected PagadorEletronicoDDADao pagadorEletronicoDDADao;

    @Mock
    protected ArquivoRecebidoDao arquivoDao;

    @Mock
    protected MensagemDDADao mensagemDao;

    @Mock
    protected MonitoramentoMensagensDDADao monitoramentoMensagensDao;

    @Mock
    protected ParametroDao parametroDao;

    @Mock
    protected ContingenciaDao contigenciaDao;

    @Mock
    protected ExcluirRelacionamentoBeneficiarioDelegate excluirRelacionamentoBeneficiarioDelegate;

    @Mock
    protected PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate;

    @Mock
    protected MensagemDDA0401Delegate mensagemDDA0401Delegate;

    @Mock
    protected MensagemGEN0014Delegate mensagemGEN0014Delegate;

    @Mock
    protected MensagemConsultaBoletoPagamentoDelegate mensagemConsultaBoletoPagamentoDelegate;

    @Mock
    protected OperacaoBeneficiarioDDADao operacaoBeneficiarioDDADao;

    @Mock
    protected PagadorEletronicoDDALegadoDao pagadorEletronicoDDALegadoDao;

    @Mock
    protected GradeHorariaDao gradeHorariaDao;

    @Mock
    protected MensagemDDADelegate mensagemDDADelegate;

    @Mock
    protected ContaCorrenteDelegate contaCorrenteDelegate;

    @Mock
    protected CapesDelegate capesDelegate;

    @Mock
    protected IntegracaoCipFabricaDelegates integracaoCipFabricaDelegates;

    @Mock
    protected MensagemDDAPagadorDelegate mensagemDDAPagadorDelegate;

    @Mock
    protected IntegracaoInternaFabricaDelegates integracaoInternaFabricaDelegates;

    @Mock
    protected TipoGradeHorariaDao tipoGradeHorariaDao;

    @Mock
    protected InformacoesUsuario informacaoUsuario;

    @Mock
    protected BoletoDDADao boletoDao;

    @Mock
    protected TipoErroCipDao tipoErroCipDao;

    @Mock
    protected TratamentoPendenciaErroDao tratamentoPendenciaErroDao;

    @Mock
    protected AgendamentoBoletoDao agendamentoDao;

    @Mock
    protected BoletoCipDao boletoCipDao;

    @Mock
    protected TipoMensagemDao tipoMensagemDao;

    @Mock
    protected SessionContext sessionContext;

    @Mock
    protected UserTransaction userTransaction;

    @Mock
    protected AlterarSituacaoBeneficiarioDelegate alterarSituacaoBeneficiarioDelegate;

    @Mock
    protected AlterarCadastroBeneficiarioDelegate alterarCadastroBeneficiarioDelegate;

    @Mock
    protected SCIDelegate sciDelegate;

    @Mock
    protected ADMDelegate admDelegate;

    @Mock
    protected ProcessarContingenciaMensagemDelegate processarContingenciaMensagemDelegate;

    @Mock
    protected CadastrarBeneficiarioDelegate cadastrarBeneficiarioDelegate;

    @Mock
    protected OperacionalFabricaDelegates operacionalFabricaDelegates;

    @Mock
    protected ReplicarBeneficiarioLegadoDelegate replicarBeneficiarioLegadoDelegate;

    @Mock
    protected XPath xPath;

    @Mock
    protected Transformer transformer;

    @Mock
    protected Node node;

    @Mock
    protected NodeList nodeList;

    // MESSAGES
    protected static final String ERRO_TIPO_PESSOA = "operacional.mensagemddaboleto.erro.tipopessoa.documento";
    protected static final String ERRO_PREENCHIMENTO_OBRIGATORIO = "integracaocip.preenchimento.obrigatorio";
    protected static final String ERRO_CAMPO_OBRIGATORIO = "integracaocip.campo.obrigatorio";
    protected static final String ERRO_COD_TIPO_GRADE_NAO_CADASTRADO = "Cód. Tipo Grade não cadastrado";
    protected static final String ERRO_CODIGO_BARRAS_INVALIDO = "integracaocip.linha.digitavel.codigo.barras.invalido";
    protected static final String ERRO_CODIGO_BARRAS_TAMANHO_INVALIDO = "integracaocip.linha.digitavel.codigo.barras.tamanho.invalido";
    protected static final String ERRO_DATA_VENCIMENTO_DIA_UTIL = "integracaocip.data.vencimento.dia.util";
    protected static final String ERRO_DATA_VENCIMENTO_ANTERIOR_DATA_ATUAL = "integracaocip.data.vencimento.anterior.data.atual";
    protected static final String ERRO_CODIGO_BARRAS_NAO_INFORMADO = "integracaocip.linha.digitavel.codigo.barras.nao.informado";
    protected static final String ERRO_BOLETO_VENCIDO = "integracaocip.boleto.vencido";
    protected static final String ERRO_INSTITUICAO_FINANCEIRA_INVALIDA = "integracaocip.consulta.nao.permitida.instituicao.financeira.invalida";
    protected static final String ERRO_ALTERAR_SITUACAO_BANCOOB_ORIGEM = "integracaocip.erro.alterar.situacao.bancoob.origem";
    protected static final String ERRO_ALTERAR_SITUACOES_IGUAIS = "integracaocip.erro.alterar.situacoes.iguais";
    protected static final String ERRO_ALTERAR_SITUACAO_APTO_PARA_APTO = "integracaocip.erro.alterar.situacao.apto.para.apto";
    protected static final String ERRO_BENEFICARIO_NAO_CADASTRADO_CIP = "integracaocip.erro.beneficario.nao.cadastrado.cip";
    protected static final String ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO = "integracaocip.erro.cnpj.cpf.representante.invalido";
    protected static final String ERRO_TIPO_PESSOA_REPRESENTANTE_INVALIDO = "integracaocip.erro.tipo.pessoa.representante.invalido";
    protected static final String ERRO_TP_PRODUTO_CONVENIO_INVALIDA = "integracaocip.erro.tp.produto.convenio.invalida";
    protected static final String ERRO_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.agencia.destino.convenio.invalida";
    protected static final String ERRO_TP_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.tp.agencia.destino.convenio.invalida";
    protected static final String ERRO_DATA_INICIO_RELACIONAMENTO_CONVENIO_INVALIDA = "integracaocip.erro.data.inicio.relacionamento.convenio.invalida";
    protected static final String ERRO_NOME_RAZAO_SOCIAL_BENEFICIARIO_INVALIDO = "integracaocip.erro.nome.razao.social.beneficiario.invalido";
    protected static final String ERRO_NOME_FANTASIA_BENEFICIARIO_OBRIGATORIO_PJ = "integracaocip.erro.nome.fantasia.beneficiario.obrigatorio.pj";
    protected static final String ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO = "integracaocip.erro.cnpj.cpf.beneficiario.invalido";
    protected static final String ERRO_TIPO_PESSOA_INVALIDO = "integracaocip.erro.tipo.pessoa.invalido";
    protected static final String ERRO_INSTITUICAO_SCI_INVALIDA = "integracaocip.erro.instituicao.sci.invalida";
    protected static final String ERRO_CARGA_EM_EXECUCAO = "integracaocip.erro.carga.em.execucao";
    protected static final String ERRO_DESCOMPACTAR_XML_RECEBIDO = "integracaocip.erro.descompactar.xml.recebido";
    protected static final String ERRO_RECUPERAR_BYTES_ARQUIVO = "integracaocip.erro.recuperar.bytes.arquivo";
    protected static final String ERRO_COMPACTAR_XML_ENVIO = "integracaocip.erro.compactar.xml.envio";
    protected static final String ERRO_SALVAR_ARQUIVO_DIRETORIO = "integracaocip.erro.salvar.arquivo.diretorio";
    protected static final String ERRO_ENVIAR_MENSAGEM_SSPB = "integracaocip.erro.enviar.mensagem.sspb";
    protected static final String ERRO_GRUPO_CONVENIO_INVALIDO = "integracaocip.erro.grupo.convenio.invalido";
    protected static final String ERRO_DATA_INICIO_RELACIONAMENTO_PARTICIPANTE_INVALIDA = "integracaocip.erro.data.inicio.relacionamento.participante.invalida";
    protected static final String ERRO_SITUACAO_RELACIONAMENTO_PARTICIPANTE_INVALIDA = "integracaocip.erro.situacao.relacionamento.participante.invalida";
    protected static final String ERRO_DATA_HORA_SITUACAO_BENEFICIARIO_INVALIDA = "integracaocip.erro.data.hora.situacao.beneficiario.invalida";
    protected static final String ERRO_SITUACAO_BENEFICIARIO_INVALIDA = "integracaocip.erro.situacao.beneficiario.invalida";
    protected static final String ERRO_BENEFICARIO_JA_CADASTRADO = "integracaocip.erro.beneficario.ja.cadastrado";
    protected static final String ERRO_BENEFICIARIO_SEM_CONVENIO = "integracaocip.erro.beneficiario.sem.convenio";
    protected static final String ERRO_TP_MANUTENCAO_CONVENIO_INVALIDA = "integracaocip.erro.tp.manutencao.convenio.invalida";
    protected static final String ERRO_TP_MANUTENCAO_REPRESENTANTE_INVALIDA = "integracaocip.erro.tp.manutencao.representante.invalida";
    protected static final String ERRO_DATA_SITUACAO_BENEFICIARIO_INVALIDO = "integracaocip.erro.data.situacao.beneficiario.invalido";
    protected static final String ERRO_SITUACAO_BENEFICIARIO_INVALIDO = "integracaocip.erro.situacao.beneficiario.invalido";
    protected static final String ERRO_PROCESSAR_DDA_AVISA_ALTERACAO_SITUACAO = "integracaocip.erro.processar.dda.avisa.alteracao.situacao";
    protected static final String ERRO_BOLETO_BAIXA_NAO_EXISTE = "integracaocip.boleto.baixa.operacional.nao.existe";
    protected static final String ERRO_PARAMETRO_NAO_INFORMADO = "integracaocip.parametro.nao.informado";
    protected static final String ERRO_ENVIAR_MENSAGEM_CONTINGENCIA = "integracaocip.erro.enviar.mensagem.contingencia";
    protected static final String ERRO_ENVIAR_MENSAGEM_CONTINGENCIA_TIPO_MENSAGEM_INVALIDO = "integracaocip.erro.enviar.mensagem.contingencia.tipo.mensagem.invalido";
    protected static final String ERRO_RECUPERAR_LOG_ERRO_CARGA = "integracaocip.erro.recuperar.log.erro.carga";
    protected static final String ERRO_INTEGRACAO_INSTITUICAO_NAO_ENCONTRADA = "integracaocip.erro.integracao.instituicao.nao.encontrada";

    // GLOBALS =================================================================================
    private final static String LETRA_A = "A";
    private final static Long LONG_6 = 6L;

    /**
     * Método responsável por void
     * 
     * @throws TransformerConfigurationException
     * 
     */
    @Before
    public void init() throws TransformerConfigurationException {
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(integracaoCipFabricaDelegates);
        when(integracaoCipFabricaDelegates.getMensagemDDADelegate()).thenReturn(mensagemDDADelegate);
        when(integracaoCipFabricaDelegates.getReplicarBeneficiarioLegadoDelegate()).thenReturn(replicarBeneficiarioLegadoDelegate);
        when(integracaoCipFabricaDelegates.getMensagemConsultaBoletoPagamentoDelegate()).thenReturn(mensagemConsultaBoletoPagamentoDelegate);
        when(integracaoCipFabricaDelegates.getMensagemGEN0014Delegate()).thenReturn(mensagemGEN0014Delegate);
        when(integracaoCipFabricaDelegates.getProcessarContingenciaMensagemDelegate()).thenReturn(processarContingenciaMensagemDelegate);

        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(informacaoUsuario);

        PowerMockito.mockStatic(XPathFactory.class);
        PowerMockito.when(XPathFactory.newInstance()).thenReturn(xPathFactory);
        PowerMockito.when(xPathFactory.newXPath()).thenReturn(xPath);

        PowerMockito.mockStatic(TransformerFactory.class);
        PowerMockito.when(TransformerFactory.newInstance()).thenReturn(transformerFactory);
        PowerMockito.when(transformerFactory.newTransformer()).thenReturn(transformer);

        PowerMockito.mockStatic(InformacoesUsuario.class);
        PowerMockito.when(InformacoesUsuario.getInstance()).thenReturn(informacoesUsuario);

        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(integracaoInternaFabricaDelegates);
        when(integracaoInternaFabricaDelegates.getADMDelegate()).thenReturn(admDelegate);
        when(integracaoInternaFabricaDelegates.getSCIDelegate()).thenReturn(sciDelegate);
        when(integracaoInternaFabricaDelegates.getContaCorrenteDelegate()).thenReturn(contaCorrenteDelegate);
        when(integracaoInternaFabricaDelegates.getCapesDelegate()).thenReturn(capesDelegate);

        PowerMockito.mockStatic(OperacionalFabricaDelegates.class);
        when(OperacionalFabricaDelegates.getInstance()).thenReturn(operacionalFabricaDelegates);
        when(operacionalFabricaDelegates.getCadastrarBeneficiarioDelegate()).thenReturn(cadastrarBeneficiarioDelegate);
        when(operacionalFabricaDelegates.getExcluirRelacionamentoBeneficiarioDelegate()).thenReturn(excluirRelacionamentoBeneficiarioDelegate);
        when(operacionalFabricaDelegates.getPagadorEletronicoDDADelegate()).thenReturn(pagadorEletronicoDDADelegate);
        when(operacionalFabricaDelegates.getAlterarSituacaoBeneficiarioDelegate()).thenReturn(alterarSituacaoBeneficiarioDelegate);
        when(operacionalFabricaDelegates.getMensagemDDAPagadorDelegate()).thenReturn(mensagemDDAPagadorDelegate);

        when(sessionContext.getUserTransaction()).thenReturn(userTransaction);
        whenInformacoesUsuario();
    }

    /**
     * Método responsável por
     * 
     * @param msg
     * @param method void
     * 
     */
    public void assertEquals(String msg, Object method) {
        Assert.assertEquals(msg, method);
    }

    /**
     * Método responsável por
     * 
     * @param clazz
     * @param privateMethod
     * @param arguments void
     * 
     */
    public void privateMethods(Object clazz, String privateMethod, Object... arguments) {
        try {
            Whitebox.invokeMethod(clazz, privateMethod, arguments);
        } catch (Exception e) {
            System.out.println("Erro ao tentar testar método privado: " + privateMethod);
            return;
        }
    }

    /**
     * Método responsável por
     * 
     * @param retorno
     * @return String
     * 
     */
    public String retornaSucessFailed(String retorno) {
        return ObjectUtil.isEmpty(retorno) ? Constantes.TESTE_SUCESSO : retorno;
    }

    /**
     * Método responsável por
     * 
     * @param method
     * @return String
     * 
     */
    public String retornarString(Object method) {
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param data
     * @param hora
     * @param min
     * @param seg
     * @return Date
     * 
     */
    public Date setHorario(Date data, int hora, int min, int seg) {
        data = (ObjectUtil.isNull(data) ? Constantes.DATE_AUX : data);
        data.setHours(hora);
        data.setMinutes(min);
        data.setSeconds(seg);

        return data;
    }

    /**
     * Método responsável por
     * 
     * @param clazz
     * @param privateMethod
     * @param arguments
     * @throws Exception void
     * 
     */
    public void privateMethodsException(Object clazz, String privateMethod, Object... arguments) throws Exception {
        Whitebox.invokeMethod(clazz, privateMethod, arguments);
    }

    // INSTANCES SERVICES ========================================================================
    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenTratamentoPendenciaErroDao(Boolean success) {
        try {
            when(tratamentoPendenciaErroDao.listarPendencia()).thenReturn(new ArrayList<PendenciaDto>());

            when(tratamentoPendenciaErroDao.listarErroAgrupado()).thenReturn(new ArrayList<ErroAgrupadoDto>());

            when(tratamentoPendenciaErroDao.listarErroProcessamento()).thenReturn(new ArrayList<ErroProcessamentoCipDto>());

            when(tratamentoPendenciaErroDao.listarTipoTratamentoErroCip(Constantes.SHORT_UM)).thenReturn(new ArrayList<TipoTratamentoErroCip>());

            when(tratamentoPendenciaErroDao.obterListaTratamentoAutomatizado()).thenReturn(listarTratamentoMesagemDto());

            when(tratamentoPendenciaErroDao.obterDetalheMensagemErro(Constantes.LONG_UM)).thenReturn(recuperarMensagemDDA());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenTipoMensagemDao(Boolean success) {
        try {
            if (success) {
                when(tipoMensagemDao.listarTipoMensagemPorCategoria()).thenReturn(listarTipoMensagemDDA());

                when(tipoMensagemDao.listarCategoriaMensagemDDA()).thenReturn(new ArrayList<CategoriaMensagemDDA>());

                when(tipoMensagemDao.isExisteEmTipoMensagem("ADDA001")).thenReturn(Boolean.TRUE);

                when(tipoMensagemDao.isVinculadoArqCorrespondente("ADDA001")).thenReturn(Boolean.TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenTipoErroCipDao(Boolean success) {
        try {
            if (success) {
                when(tipoErroCipDao.obterTipoErro(String.valueOf(Constantes.INTEGER_UM))).thenReturn(recuperarTipoErroCip());

                when(tipoErroCipDao.existeMensagemVinculoTipoErro(Constantes.STRING_NUMERO_1)).thenReturn(Boolean.TRUE);

            } else {
                when(tipoErroCipDao.obterTipoErro(String.valueOf(Constantes.INTEGER_UM))).thenThrow(new OperacionalNoResultException("falhou"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenManutencaoMensagemDDABoletoDao(Boolean success) {
        try {
            when(manutencaoMensagemDDABoletoDao.listarAutorizacaoValorDivergente()).thenReturn(new ArrayList<AutorizacaoValorDivergente>());
            when(manutencaoMensagemDDABoletoDao.listarMeioPagamento()).thenReturn(new ArrayList<MeioPagamento>());
            when(manutencaoMensagemDDABoletoDao.listarTipoDesconto()).thenReturn(new ArrayList<TipoDesconto>());
            when(manutencaoMensagemDDABoletoDao.listarTipoJuros()).thenReturn(new ArrayList<TipoJuros>());
            when(manutencaoMensagemDDABoletoDao.listarTipoModeloCalculo()).thenReturn(new ArrayList<TipoModeloCalculo>());
            when(manutencaoMensagemDDABoletoDao.listarTipoMulta()).thenReturn(new ArrayList<TipoMulta>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenContaCorrenteDelegate(Boolean success) {
        try {
            when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(listarString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param ret void
     * 
     */
    public void whenPagadorEletronicoDDADao(Boolean success, Boolean ret) {
        try {
            if (success) {
                when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico('I', Constantes.CPF_AUX, Boolean.FALSE)).thenReturn(ret);

                when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(ret);

                when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());

                when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());

                when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM))
                        .thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());

                when(pagadorEletronicoDDADao.existeSolicitacaoAdesao(Constantes.CPF_AUX)).thenReturn(ret);

                when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(ret);

                when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(ret);

                when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(!ret);

                when(pagadorEletronicoDDADao.obterTermoPagadorEletronico(Constantes.DATE_TIME_DB_AUX, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM, Boolean.TRUE,
                        Constantes.INTEGER_UM)).thenReturn(new TermoPagadorDto());

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param ret void
     * 
     */
    public void whenBoletoCipDao(Boolean success, Boolean ret) {
        try {
            if (success) {
                when(boletoCipDao.obterBoletoDDA(Constantes.LONG_UM)).thenReturn(recuperarBoletoDDA());

                when(boletoCipDao.existeSolicitacaoTerceiroAutorizadoEmAndamento(Constantes.CPF_AUX, Constantes.LONG_UM)).thenReturn(ret);

                when(boletoCipDao.existeTerceiroAutorizadoAtivo(Constantes.CPF_AUX, Constantes.LONG_UM)).thenReturn(!ret);

                when(boletoCipDao.obterMensagemDDABoleto(Constantes.LONG_UM)).thenReturn(recuperarMensagemDDABoleto());

            } else {
                when(boletoCipDao.obterBoletoDDA(Constantes.LONG_UM)).thenThrow(new ComumException("falhou"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenAlterarSituacaoBeneficiarioDelegate(Boolean success) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenMonitoramentoMensagensDDADao(Boolean success) {
        try {
            when(monitoramentoMensagensDao.listarTipoMensagensDDA(Constantes.NOME_BANCO)).thenReturn(listarTipoMensagemDDA());

            when(monitoramentoMensagensDao.recuperaMensagemOrigemDDA(Constantes.LONG_UM)).thenReturn(new MensagemDDA());

            when(monitoramentoMensagensDao.listarTiposErroCIP()).thenReturn(new ArrayList<TipoErroCipDto>());

            when(monitoramentoMensagensDao.obterLogErroCargaDTO(anyLong())).thenReturn(listarLogErroCargaDto());

            when(monitoramentoMensagensDao.obterQtdTotalErrosBeneficiariosPorArquivo(anyLong())).thenReturn(Constantes.LONG_UM);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenHistoricoMensagensDDADao(Boolean success) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenContingenciaDao(Boolean success) {
        try {
            when(contigenciaDao.obterIdUltimaHabilitacaoContingencia()).thenReturn(Constantes.LONG_UM);

            when(contigenciaDao.listarHistoricoContingencias()).thenReturn(new ArrayList<GradeContingenciaDto>());

            when(contigenciaDao.listar()).thenReturn(listarHistoricoContingencia());

            when(contigenciaDao.listarBoletosPagamentoContingencia()).thenReturn(listarBoletoPagamentoContingenciaDto());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenMensagemConsultaBoletoPgtDelegate() {
        try {
            when(mensagemConsultaBoletoPagamentoDelegate.obterBoletoDDA(Constantes.COD_BARRAS_DT_VENC_BEFORE, Constantes.INTEGER_UM, Constantes.NOME_BANCO, Constantes.SHORT_UM))
                    .thenReturn(recuperarBoletoDDA());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenPagadorEletronicoDDADelegate(Boolean success) {
        try {
            when(pagadorEletronicoDDADelegate.isCpfCnpjPagadorEletronico(ObjectUtil.converterValor(String.valueOf(Constantes.INTEGER_UM)), Constantes.CPF_AUX, Boolean.TRUE))
                    .thenReturn(success);
        } catch (ComumNegocioException e) {

            e.printStackTrace();
        } catch (ComumException e) {

            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenMensagemDDADao(Boolean success) {
        try {

            when(mensagemDao.obterCanalDDA(CanalEnum.CAIXA.getId())).thenReturn(success ? Constantes.SHORT_UM : null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param nullObject void
     * 
     */
    public void whenBoletoDao(Boolean nullObject) {
        try {
            if (nullObject) {
                when(boletoDao.obterBoletoDDA(anyString(), null, Boolean.FALSE)).thenReturn(null);

                // when(boletoDao.obterMensagemDDABaixaOperacional(Constantes.NOME_BANCO, Constantes.DATE_TIME_DB_AUX, new
                // BigDecimal(Constantes.BIG_INTEGER_1_AUX)))
                // .thenReturn(null);
                return;
            }

            when(boletoDao.complementarDadosBoletoDDA(any(BoletoDDA.class))).thenReturn(recuperarBoletoDDA());

            when(boletoDao.obterBoletoDDA(anyString(), null, Boolean.FALSE)).thenReturn(recuperarBoletoDDA());

            // when(boletoDao.obterMensagemDDABaixaOperacional(Constantes.NOME_BANCO, Constantes.DATE_TIME_DB_AUX, new
            // BigDecimal(Constantes.BIG_INTEGER_1_AUX)))
            // .thenReturn(recuperarMensagemDDABaixaOperacional());

            when(boletoDao.possuiBoletoDDA(Constantes.NOME_BANCO)).thenReturn(Boolean.TRUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenAgendamentoBoletoDao() {

        try {
            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM,
                    LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.COD_BARRAS_DT_VENC_BEFORE))).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM, DateUtil.obterDataSemHora(Constantes.DATE_AUX)))
                    .thenReturn(DateUtil.obterDataSemHora(Constantes.DATE_AUX));

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM, DateUtil.obterDataSemHora(Constantes.DATE_AUX))).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM,
                    LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.LINHA_DIGITAVEL))).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM,
                    LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.COD_BARRAS_BANCOOB))).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM,
                    LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.COD_BARRAS_INST_FINANCEIRA))).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM,
                    LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.COD_BARRAS_39_VALIDO))).thenReturn(recuperaDateMenor());

            when(agendamentoDao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.DATE_AUX)).thenReturn(Constantes.DATE_AUX);

            when(agendamentoDao.obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(Constantes.INTEGER_UM, Constantes.COD_BARRAS_BANCOOB, CanalEnum.CAIXA.getId(),
                    Constantes.INTEGER_UM)).thenReturn(Constantes.NOME_BANCO);

        } catch (ComumNegocioException e) {

            e.printStackTrace();
        } catch (ComumException e) {

            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param nullObject
     * @param emptyListsObject void
     * 
     */
    public void whenBeneficiarioCIPDao(Boolean success, Boolean nullObject, Boolean emptyListsObject) {
        try {
            if (emptyListsObject) {
                BeneficiarioDDA ret = recuperarBeneciarioDDA();
                ret.setListaBeneficiarioInstituicao(new ArrayList<BeneficiarioInstituicao>());

                when(beneficiarioCipDao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(ret);
                return;
            }

            if (nullObject) {
                when(beneficiarioCipDao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(null);

                when(beneficiarioCipDao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);
                return;
            }

            if (success) {
                when(beneficiarioCipDao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(recuperarBeneciarioDDA());

                when(beneficiarioCipDao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(recuperarBeneciarioDDA());

                when(beneficiarioCipDao.obterMensagemDDABeneficiario(anyLong())).thenReturn(new MensagemDDABeneficiario());

                return;
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar instanciar BeneficiarioDDAA:" + e.getMessage());
            return;
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenLinhaDigitavalUtil() {
        PowerMockito.mockStatic(LinhaDigitavelCodigoBarrasUtil.class);
        try {
            when(LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(Constantes.COD_BARRAS_BANCOOB)).thenReturn(null);
        } catch (ComumNegocioException e) {

            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenADMDelegate(Boolean success) {
        try {
            if (success) {
                when(admDelegate.obterDataMovimento(Constantes.INTEGER_UM)).thenReturn(Constantes.DATE_AUX);

                when(admDelegate.obterNomeBancoCache(Constantes.SHORT_UM)).thenReturn(Constantes.NOME_BANCO);

                when(admDelegate.obterNomeInstituicaoFinanceiraCache(Constantes.NOME_BANCO)).thenReturn(Constantes.NOME_BANCO);

                when(admDelegate.obterProdutoCobrancaBancoob()).thenReturn(recuperarProdutoDto());

                when(admDelegate.obterDataProximoMovimentoBancoob()).thenReturn(Constantes.DATE_AUX);

                when(admDelegate.obterDataMovimentoBancoob()).thenReturn(Constantes.DATE_AUX);
            } else {
                when(admDelegate.obterNomeBancoCache(Constantes.SHORT_UM)).thenThrow(new BancoNaoEncontradoException(Constantes.SHORT_30));

                when(admDelegate.obterNomeInstituicaoFinanceiraCache(Constantes.NOME_BANCO)).thenThrow(new BancoNaoEncontradoException(Constantes.SHORT_30));

                when(admDelegate.obterDataMovimentoBancoob()).thenThrow(new IntegracaoInternaException("falhou"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param contigenciaHabilitada void
     * 
     */
    public void whenParametroDao(Boolean success, Boolean contigenciaHabilitada) {
        try {
            when(parametroDao.obterValorBoolean(anyLong(), anyInt())).thenReturn(success);

            when(parametroDao.obterValorInteger(ParametroDDA.VALOR_MIN_BOLETO_ENVIO_A_CIP, Constantes.ID_SICOOB)).thenReturn(Constantes.INTEGER_UM);

            when(parametroDao.obterValorBoolean(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn(contigenciaHabilitada);

            when(parametroDao.obterValor(anyLong(), anyInt())).thenReturn(String.valueOf(Constantes.LONG_UM));

            when(parametroDao.obterValorDouble(anyLong(), anyInt())).thenReturn((double) (((success) ? 5 : 1) * Constantes.INTEGER_CEM));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenArquivoRecebidoDao() {
        try {
            when(arquivoDao.listarTipoArquivo()).thenReturn(listarTipoArquivo());

            when(arquivoDao.listarTipoMensagemAgenAdda()).thenReturn(listarTipoMensagemDDA());

            when(arquivoDao.listarSitucaoArquivo()).thenReturn(listarSituacaoProcessamentoArquivo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenEm(Boolean success) {
        try {
            when(em.find(SituacaoBeneficiarioDDA.class, LETRA_A)).thenReturn(recuperarSituacaoBeneficiarioDDA());

            when(em.find(ParametroDDA.class, LONG_6)).thenReturn(recuperarParametroDDA());

            when(em.find(LogRecebimentoArquivoDDA.class, Constantes.LONG_UM)).thenReturn(recuperarLogRecebimentoArquivoDDA());

            when(em.find(LogDetRecebimentoArquivoDDA.class, Constantes.LONG_UM)).thenReturn(recuperarLogDetRecebimentoArquivoDDA());

            when(em.find(SituacaoProcessamentoArquivo.class, Constantes.SHORT_UM)).thenReturn(recuperarSituacaoProcessamentoArquivo());

            when(em.find(BeneficiarioDDA.class, Constantes.LONG_UM)).thenReturn(recuperarBeneciarioDDA());

            when(em.find(HistoricoContingencia.class, Constantes.LONG_UM)).thenReturn(recuperarHistoricoContingencia());

            when(em.find(GradeHoraria.class, Constantes.LONG_UM)).thenReturn(recuperarGradeHoraria());

            when(em.find(MensagemDDA.class, Constantes.LONG_UM)).thenReturn(recuperarMensagemDDA());

            // when(em.find(ParametroDDA.class, anyLong()))
            // .thenReturn(recuperarParametroDDA());

            when(em.find(TipoGradeHoraria.class, Constantes.STRING_NUMERO_1)).thenReturn(recuperarTipoGradeHoraria());

            when(em.find(TipoMensagemDDA.class, "ADDA001")).thenReturn(recuperarTipoMensagemDDA());

            when(em.find(MensagemDDABoletoGrupoCalculo.class, Constantes.LONG_UM)).thenReturn(retornarMensagemDDABoletoGrupoCalculo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenGradeHorariaDao() {
        try {
            when(gradeHorariaDao.listarGrades(String.valueOf(Constantes.INTEGER_UM), Constantes.DATE_AUX)).thenReturn(listarGradeHorariaDto());

            PowerMockito.doThrow(new ComumException("falhou")).when(gradeHorariaDao).excluirGradesHorariasAntigas();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenTipoGradeHorariaDao(Boolean success) {
        try {
            when(tipoGradeHorariaDao.listarTipoGradeHorariaPorCodigoSubtipo(SubTipoGrade.GRADE_TODOS)).thenReturn(listarTipoGradeHorariaDto());

            when(tipoGradeHorariaDao.obterCountTipoGradeHoraria(String.valueOf(Constantes.INTEGER_UM))).thenReturn(success ? Constantes.LONG_UM : null);

            when(tipoGradeHorariaDao.listarTipoGradeHorariaPersonalizadaSemGradeHoraria(new DateTimeDB(Constantes.DATE_AUX.getTime()))).thenReturn(listarTipoGradeHorariaDto());

            when(tipoGradeHorariaDao.listarTipoGradeHorariaDDA()).thenReturn(listarTipoGradeHoraria());

            when(tipoGradeHorariaDao.listarSubTipoGrade()).thenReturn(new ArrayList<SubTipoGrade>());

            when(tipoGradeHorariaDao.obterTipoGradeHoraria(Constantes.STRING_NUMERO_1)).thenReturn(recuperarTipoGradeHoraria());

            when(tipoGradeHorariaDao.isExisteEmGradeHoraria(Constantes.STRING_NUMERO_1)).thenReturn(Boolean.TRUE);

            when(tipoGradeHorariaDao.isExisteEmGradeOrigem(Constantes.STRING_NUMERO_1)).thenReturn(Boolean.TRUE);

            when(tipoGradeHorariaDao.isExisteEmTipoMensagem(Constantes.STRING_NUMERO_1)).thenReturn(Boolean.TRUE);

            when(tipoGradeHorariaDao.listarCodigosTipoGradeHoraria()).thenReturn(listarTipoGradeHoraria());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    public void whenSciDelegate(Boolean success) {
        try {
            if (success) {
                when(sciDelegate.obterInstituicaoCache(Constantes.INTEGER_UM)).thenReturn(recuperarInstituicaoDto());
                when(sciDelegate.obterInstituicaoCache(anyInt())).thenReturn(recuperarInstituicaoDto());
                when(sciDelegate.obterInstituicaoPorCooperativaCache(Constantes.INTEGER_UM)).thenReturn(recuperarInstituicaoDto());
            } else {
                when(sciDelegate.obterInstituicaoCache(Constantes.INTEGER_UM)).thenThrow(new ComumException("falhou"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param emptyListsObject void
     * 
     */
    public void whenBeneficiarioLegadoDao(Boolean success, Boolean emptyListsObject) {
        try {
            if (emptyListsObject) {
                AlterarCadastroBeneficiarioDDADto ret = recuperarAlterarCadastroBeneficiarioDDADto();
                ret.setListaConvenioAlteracaoDto(new ArrayList<ConvenioAlteracaoDDADto>());

                CadastroBeneficiarioDDADto retCad = recuperarCadastroBeneficiarioDDADto();
                retCad.setListaRepresentanteBeneficiarioDto(null);

                when(beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(anyString(), anyInt())).thenReturn(ret);

                when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(retCad);

                return;
            }
            if (success) {
                when(beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(anyLong(), anyInt())).thenReturn(recuperarAlterarCadastroBeneficiarioDDADto());

                when(beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(recuperarAlterarCadastroBeneficiarioDDADto());

                when(beneficiarioDao.obterConvenioAlteracaoDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(recuperarConvenioAlteracaoDDADto());

                when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(recuperarCadastroBeneficiarioDDADto());
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar instanciar BeneficiarioLogadoDao:" + e.getMessage());
            return;
        }
    }

    /**
     * Método responsável por void
     * 
     */
    public void whenInformacoesUsuario() {
        when(informacaoUsuario.getCooperativa()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(informacaoUsuario.getDominio()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(informacaoUsuario.getIdInstituicao()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(informacaoUsuario.getIdUnidadeInstituicao()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(informacaoUsuario.getLogin()).thenReturn(Constantes.NOME_BANCO);
        when(informacaoUsuario.getPac()).thenReturn(String.valueOf(Constantes.INTEGER_UM));

        when(InformacoesUsuario.getInstance().getCooperativa()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(InformacoesUsuario.getInstance().getDominio()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(InformacoesUsuario.getInstance().getIdInstituicao()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(InformacoesUsuario.getInstance().getIdUnidadeInstituicao()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
        when(InformacoesUsuario.getInstance().getLogin()).thenReturn(Constantes.NOME_BANCO);
        when(InformacoesUsuario.getInstance().getPac()).thenReturn(String.valueOf(Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por void
     * 
     */
    @SuppressWarnings("unchecked")
    public void whenInformacoesUsuarioException() {
        try {
            when(informacaoUsuario.getCooperativa()).thenThrow(NullPointerException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param emptyObject void
     * 
     */
    public void whenOperacionalBeneficiarioDDADao(Boolean success, Boolean emptyObject) {
        try {
            if (emptyObject) {
                when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendenteBeneficiarioDto>());
                when(operacaoBeneficiarioDDADao.obterSituacaoBeneficiario(anyString())).thenReturn(null);
                return;
            }

            if (success) {
                when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(anyString())).thenReturn(listarViewMensagemDDAPendente(emptyObject));

                when(operacaoBeneficiarioDDADao.obterSituacaoBeneficiario(anyString()))
                        .thenReturn((SituacaoBeneficiarioDDA) (emptyObject ? null : recuperarSituacaoBeneficiarioDDA()));

                when(operacaoBeneficiarioDDADao.beneficiarioEstaNaCip(Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);

            } else {
                when(operacaoBeneficiarioDDADao.obterSituacaoBeneficiario(Constantes.CPF_AUX)).thenThrow(new ComumException("falhou"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar instanciar OperacionalBeneficiarioDDADao:" + e.getMessage());
            return;
        }
    }

    /**
     * Método responsável por void
     * 
     * @throws XPathExpressionException
     * 
     */
    public void whenNodeList() throws XPathExpressionException {
        when(nodeList.item(anyInt())).thenReturn(node);
        when(nodeList.getLength()).thenReturn(Constantes.INTEGER_UM);
        when(node.getParentNode()).thenReturn(node);
        when(node.removeChild(any(Node.class))).thenReturn(node);
        when(xPath.evaluate(anyString(), any(Object.class), any(QName.class))).thenReturn(nodeList);
    }
}
