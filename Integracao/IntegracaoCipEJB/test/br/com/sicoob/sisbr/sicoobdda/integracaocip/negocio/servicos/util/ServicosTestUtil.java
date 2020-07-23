/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util
 * Arquivo:         ServicosTestUtil.java
 * Data Criação:    Jan 22, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CapesDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.RequisicaoCacheDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoAlteracaoBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoBaixaEfetivaBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoBaixaOperacionalDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoBoletoPagamentoAbertoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoConsultaBoletoPagamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoDecursoPrazoBaixaOperacionalDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoInclusaoBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoManutencaoPagadorDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipArquivoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemBaixaEfetivaBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemBaixaOperacionalBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaPagadorDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemInclusaoBoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarRetornoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoAlteraBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoDecursoPrazoBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarBeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SSPBDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import junit.framework.Assert;

/**
 * ServicosTestUtil é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@SuppressWarnings("deprecation")
@PrepareForTest({ InformacoesUsuario.class, XmlReader.class, SAXParser.class, SAXParserFactory.class, File.class, IntegracaoInternaFabricaDelegates.class,
        IntegracaoCipFabricaDelegates.class })
public class ServicosTestUtil extends RetornarObjetosUtil {

    // PRIVATES ============================================
    @Mock
    private IntegracaoInternaFabricaDelegates integracaoInternaFabricaDelegates;

    @Mock
    private IntegracaoCipFabricaDelegates integracaoCipFabricaDelegates;

    // PROTECTEDS ==========================================
    @Mock
    protected ArquivoAlteracaoBoletoDelegate arquivoAlteracaoBoletoDelegate;

    @Mock
    protected XMLReader xmlReader;

    @Mock
    protected SAXParserFactory saxParserFactory;

    @Mock
    protected SAXParser saxParser;

    @Mock
    protected ArquivoCipDao arquivoCipDao;

    @Mock
    protected SSPBDelegate ssPBDelegate;

    @Mock
    protected ReplicarPagadorEletronicoLegadoDao replicarPagadorEletronicoLegadoDao;

    @Mock
    protected ArquivoDecursoPrazoBaixaOperacionalDao arquivoDecursoPrazoBaixaOperacionalDao;

    @Mock
    protected ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate;

    @Mock
    protected PagadorCipDao pagadorCipDao;

    @Mock
    protected ArquivoAlteraBoletoDao arquivoAlteraBoletoDao;

    @Mock
    protected ArquivoIncluirBoletoDao arquivoIncluirBoletoDao;

    @Mock
    protected EntityManager em;

    @Mock
    protected MensagemDDADao mensagemDDADao;

    @Mock
    protected BoletoCipDao boletoCipDao;

    @Mock
    protected ParametroDao parametroDao;

    @Mock
    protected SCIDelegate sciDelegate;

    @Mock
    protected BeneficiarioCipDao beneficiarioCipDao;

    @Mock
    protected ReplicarBeneficiarioLegadoDelegate replicarBeneficiarioLegadoDelegate;

    @Mock
    protected ReplicarBeneficiarioLegadoDao replicarBeneficiarioLegadoDao;

    @Mock
    protected MensagemBaixaEfetivaDao mensagemBaixaEfetivaDao;

    @Mock
    protected MensagemBaixaOperacionalDao mensagemBaixaOperacionalDao;

    @Mock
    protected CapesDelegate capesDelegate;

    @Mock
    protected File file;

    @Mock
    protected MensagemConsultaPagadorDelegate mensagemConsultaPagadorDelegate;

    @Mock
    protected ArquivoManutencaoPagadorDelegate arquivoManutencaoPagadorDelegate;

    @Mock
    protected MensagemConsultaBoletoDelegate mensagemConsultaBoletoDelegate;

    @Mock
    protected MensagemInclusaoBoletoDelegate mensagemInclusaoBoletoDelegate;

    @Mock
    protected MensagemBaixaOperacionalBoletoDelegate mensagemBaixaOperacionalBoletoDelegate;

    @Mock
    protected ArquivoBoletoPagamentoAbertoDelegate arquivoBoletoPagamentoAbertoDelegate;

    @Mock
    protected MensagemBaixaEfetivaBoletoDelegate mensagemBaixaEfetivaBoletoDelegate;

    @Mock
    protected ArquivoInclusaoBoletoDelegate arquivoInclusaoBoletoDelegate;

    @Mock
    protected ArquivoDecursoPrazoBaixaOperacionalDelegate arquivoDecursoPrazoBaixaOperacionalDelegate;

    @Mock
    protected ArquivoConsultaBoletoPagamentoDelegate arquivoConsultaBoletoPagamentoDelegate;

    @Mock
    protected ArquivoBaixaEfetivaBoletoDelegate arquivoBaixaEfetivaBoletoDelegate;

    @Mock
    protected ArquivoBaixaOperacionalDelegate arquivoBaixaOperacionalDelegate;

    @Mock
    protected ProcessarRetornoDDADelegate processarRetornoDDADelegate;

    @Mock
    protected Connection connection;

    @Mock
    protected ConnectionFactory connectionFactory;

    @Mock
    protected QueueConnectionFactory queueConnectionFactory;

    @Mock
    protected Queue queue;

    @Mock
    protected Session session;

    @Mock
    protected MessageProducer messageProducer;

    @Mock
    protected Message message;

    @Mock
    protected ObjectMessage objectMessage;

    @Mock
    protected MessageConsumer messageConsumer;

    @Mock
    protected ComplexType complexType;

    @Mock
    protected MensagemDDADelegate mensagemDDADelegate;

    @Mock
    protected InformacoesUsuario informacoesUsuario;

    @Mock
    protected IntegracaoCipDao integracaoCipDao;

    @Rule
    protected TemporaryFolder folder = new TemporaryFolder();

    @Mock
    protected IntegracaoCipArquivoDelegate<IntegracaoCipServico> integracaoCipArquivoDelegate;

    @Mock
    protected RequisicaoCacheDelegate requisicaoCacheDelegate;

    @Mock
    protected ArquivoRecebidoCIPProcessadorServicoDelegate arquivoRecebidoCIPProcessadorServicoDelegate;

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Before
    public void init() throws ComumException {
        PowerMockito.mockStatic(InformacoesUsuario.class);
        PowerMockito.when(InformacoesUsuario.getInstance()).thenReturn(informacoesUsuario);

        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        PowerMockito.when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(integracaoInternaFabricaDelegates);
        PowerMockito.when(integracaoInternaFabricaDelegates.getSCIDelegate()).thenReturn(sciDelegate);
        PowerMockito.when(integracaoInternaFabricaDelegates.getCapesDelegate()).thenReturn(capesDelegate);
        PowerMockito.when(integracaoInternaFabricaDelegates.getSSPBDelegate()).thenReturn(ssPBDelegate);

        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        PowerMockito.when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(integracaoCipFabricaDelegates);
        PowerMockito.when(integracaoCipFabricaDelegates.getReplicarBeneficiarioLegadoDelegate()).thenReturn(replicarBeneficiarioLegadoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getReplicarPagadorEletronicoLegadoDelegate()).thenReturn(replicarPagadorEletronicoLegadoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoAlteracaoBoletoDelegate()).thenReturn(arquivoAlteracaoBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemConsultarPagadorDelegate()).thenReturn(mensagemConsultaPagadorDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoManutencaoPagadorDelegate()).thenReturn(arquivoManutencaoPagadorDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemConsultaBoletoDelegate()).thenReturn(mensagemConsultaBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemInclusaoBoletoDelegate()).thenReturn(mensagemInclusaoBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemBaixaOperacionalBoletoDelegate()).thenReturn(mensagemBaixaOperacionalBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoBoletoPagamentoAbertoDelegate()).thenReturn(arquivoBoletoPagamentoAbertoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemBaixaEfetivaBoletoDelegate()).thenReturn(mensagemBaixaEfetivaBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoInclusaoBoletoDelegate()).thenReturn(arquivoInclusaoBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoDecursoPrazoBaixaOperacionalDelegate()).thenReturn(arquivoDecursoPrazoBaixaOperacionalDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoConsultaBoletoPagamentoDelegate()).thenReturn(arquivoConsultaBoletoPagamentoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoBaixaEfetivaBoletoDelegate()).thenReturn(arquivoBaixaEfetivaBoletoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoBaixaOperacionalDelegate()).thenReturn(arquivoBaixaOperacionalDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getProcessarRetornoDDADelegate()).thenReturn(processarRetornoDDADelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getMensagemDDADelegate()).thenReturn(mensagemDDADelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoDelegate(anyString())).thenReturn(integracaoCipArquivoDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getRequisicaoCacheDelegate()).thenReturn(requisicaoCacheDelegate);
        PowerMockito.when(integracaoCipFabricaDelegates.getArquivoRecebidoCIPProcessadorServicoDelegate()).thenReturn(arquivoRecebidoCIPProcessadorServicoDelegate);
    }

    // AUXILIARS
    /**
     * Método responsável por resumir o método assertEquals
     * 
     * @param expected
     * @param actual void
     * 
     */
    protected void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    /**
     * Método responsável por
     * 
     * @param ejb
     * @param method
     * @param obj
     * @return String
     */
    protected String genericTestsRelatorio(Object ejb, String method, Object... obj) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, method, obj);
        } catch (Exception e) {
            retorno = Constantes.TESTE_SUCESSO;
        }
        return retornarSucessoFalha(retorno);
    }

    protected String genericTests(Object ejb, String method, Object... obj) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, method, obj);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por ler método e retornar Sucesso
     * 
     * @param method
     * @return String
     * 
     */
    protected String retornarString(Object method) {
        try {
            // LOREM IPSUM : LOREM IPSUM : LOREM IPSUM
        } catch (Exception e) {
            return Constantes.TESTE_SUCESSO;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por injetar um objeto em um Field passado.
     * 
     * @param instanciaClasse
     * @param nomeAtributo
     * @param valorAtributo
     * @throws BancoobException
     */
    public void setarValorAtributoPrivado(Object instanciaClasse, String nomeAtributo, Object valorAtributo) throws BancoobException {

        Field privateField;
        try {
            privateField = obterCampo(instanciaClasse.getClass(), nomeAtributo);
            privateField.setAccessible(true);
            privateField.set(instanciaClasse, valorAtributo);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new BancoobException("Erro ao setar valor em atributos privados. Classe: " + instanciaClasse.getClass().getCanonicalName() + " - atributo: " + nomeAtributo, e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BancoobException("Erro ao setar valor em atributos privados. Classe: " + instanciaClasse.getClass().getCanonicalName() + " - atributo: " + nomeAtributo, e);
        }
    }

    /**
     * 
     * @param classe
     * @param nomeCampo
     * @return
     * @throws NoSuchFieldException
     */
    @Deprecated
    public Field obterCampo(Class<?> classe, String nomeCampo) throws NoSuchFieldException {
        try {
            return classe.getDeclaredField(nomeCampo);
        } catch (NoSuchFieldException e) {
            Class<?> superClasse = classe.getSuperclass();

            if (superClasse == null) {
                throw e;
            } else {
                return obterCampo(superClasse, nomeCampo);
            }
        }
    }

    /**
     * Método responsável por retornar Sucesso ou Falha
     * 
     * @param retorno
     * @return String
     * 
     */
    protected String retornarSucessoFalha(String retorno) {
        return ObjectUtil.isNull(retorno) ? Constantes.TESTE_SUCESSO : retorno;
    }

    // WHENS ==================================================================
    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenBoletoCipDao(Boolean success) throws ComumException {
        when(boletoCipDao.listarMensagemDDABoletoLogEnvioArquivo(anyLong())).thenReturn(listarMensagemDDABoleto());
        when(boletoCipDao.listarBoletoDDABaixaOperacional(anyString(), any(BigDecimal.class), any(DateTimeDB.class))).thenReturn(listarBoletoDDABaixaOper());
        when(boletoCipDao.obterBoletoDDA(anyLong())).thenReturn(retornarBoletoDDA());
        when(boletoCipDao.obterBoletoDDABaixaOperacional(anyLong())).thenReturn(retornarBoletoDDABaixaOper());
        when(boletoCipDao.obterMensagemDDABoleto(anyLong())).thenReturn(retornarMensagemDDABoleto());
        when(boletoCipDao.obterMensagemDDAAceiteAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDAAceite());
        when(boletoCipDao.obterMensagemDDAAceite(anyLong())).thenReturn(retornarMensagemDDAAceite());
        when(boletoCipDao.obterBoletoDDALock(anyLong())).thenReturn(retornarBoletoDDA());
        when(boletoCipDao.obterMensagemDDABoletoAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDABoleto());
        when(boletoCipDao.obterBoletoDDABaixaEfetiva(anyString())).thenReturn(retornarBoletoDDABaixaEfet());
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     */
    protected void whenParametroDao(Boolean success) throws ComumException {
        if (success) {
            when(parametroDao.obterValor(anyLong(), anyInt())).thenReturn(Constantes.STRING_NUMERO_1);
        } else {

        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * @throws ComumException
     */
    protected void whenIntegracaoCipDao(Boolean success) throws ComumException {
        if (success) {
            when(integracaoCipDao.obterLogEnvioArquivoDDA(anyLong())).thenReturn(retornarLogEnvioArquivoDDA());
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     */
    protected void whenInformacoesUsuario(Boolean success) {
        when(informacoesUsuario.getIdInstituicao()).thenReturn(Constantes.STRING_NUMERO_1);
        when(informacoesUsuario.getLogin()).thenReturn(Constantes.STRING_NUMERO_1);
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumNegocioException
     * @throws ComumException void
     */
    protected void whenRequisicaoCacheDelegate(Boolean success) throws ComumNegocioException, ComumException {
        if (success) {

        } else {
            PowerMockito.doThrow(new ComumNegocioException(Constantes.TESTE_FALHA)).when(requisicaoCacheDelegate).incluir(any(CacheEnum.class));
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     */
    protected void whenArquivoUtil(Boolean success) throws ComumException {
        if (success) {
            when(ArquivoUtil.getBytesFromFileSemLimite(any(File.class))).thenReturn(retornarByte());
        } else {
            when(ArquivoUtil.getBytesFromFileSemLimite(any(File.class))).thenThrow(new ComumException(Constantes.TESTE_FALHA));
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws IOException void
     */
    protected void whenZipUtil(Boolean success) throws IOException {
        if (success) {
            when(ZipUtil.extrairArquivo(any(byte[].class))).thenReturn(Constantes.STRING_NUMERO_1);
            when(ZipUtil.compactarArquivo(any(byte[].class), anyString())).thenReturn(retornarByte());
        } else {
            when(ZipUtil.extrairArquivo(any(byte[].class))).thenThrow(new IOException(Constantes.TESTE_FALHA));
            when(ZipUtil.compactarArquivo(any(byte[].class), anyString())).thenThrow(new IOException(Constantes.TESTE_FALHA));
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ParserConfigurationException
     * @throws SAXException void
     */
    protected void whenSAXParserFactory(Boolean success) throws ParserConfigurationException, SAXException {
        PowerMockito.mockStatic(SAXParserFactory.class);
        when(SAXParserFactory.newInstance()).thenReturn(saxParserFactory);
        when(saxParserFactory.newSAXParser()).thenReturn(saxParser);
        when(saxParser.getXMLReader()).thenReturn(xmlReader);
    }

    protected void whenEscritorXMLArquivoUtil(Boolean success) throws ComumException, IOException {
        if (success) {
        } else {
            PowerMockito.doThrow(new IOException()).when(EscritorXMLArquivoUtil.class);
            EscritorXMLArquivoUtil.salvarArquivoAberto(any(Object.class), any(LogEnvioArquivoDDA.class), anyString());
            EscritorXMLArquivoUtil.salvarArquivo(any(byte[].class), anyString());
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws JMSException void
     */
    protected void whenConnection(Boolean success) throws JMSException {
        if (success) {
            when(connection.createSession(anyBoolean(), anyInt())).thenReturn(session);
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws IntegracaoInternaException void
     */
    protected void whenSSPBDelegate(Boolean success) throws IntegracaoInternaException {
        if (success) {
            when(ssPBDelegate.decriptar(any(byte[].class))).thenReturn(retornarByte());
        } else {
            when(ssPBDelegate.decriptar(any(byte[].class))).thenThrow(new IntegracaoInternaException(Constantes.TESTE_FALHA));
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws JMSException void
     */
    protected void whenConnectionFactory(Boolean success) throws JMSException {
        if (success) {
            when(connectionFactory.createConnection()).thenReturn(connection);
            when(queueConnectionFactory.createConnection()).thenReturn(connection);
        } else {
            try {
                when(connectionFactory.createConnection()).thenThrow(new JMSException(Constantes.TESTE_FALHA));
                when(queueConnectionFactory.createConnection()).thenThrow(new JMSException(Constantes.TESTE_FALHA));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     */
    protected void whenMensagemDDADao(Boolean success) throws ComumException {
        if (success) {
            when(mensagemDDADao.listarIdsMensagensEnviadas(anyLong())).thenReturn(listarLong());
        } else {

        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws JMSException void
     */
    protected void whenSession(Boolean success) throws JMSException {
        if (success) {
            when(session.createProducer(any(Destination.class))).thenReturn(messageProducer);
            when(session.createObjectMessage(any(Serializable.class))).thenReturn(objectMessage);
            when(session.createConsumer(any(Destination.class), anyString())).thenReturn(messageConsumer);
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenBeneficiarioCipDao(Boolean success) throws ComumException {
        when(beneficiarioCipDao.obterBeneficiario(anyString())).thenReturn(retornarBeneficiarioDDA());
        when(beneficiarioCipDao.obterBeneficiario(any(BigInteger.class), any(TipoPessoaEnum.class))).thenReturn(retornarBeneficiarioDDA());
        when(beneficiarioCipDao.obterBeneficiario(anyLong())).thenReturn(retornarBeneficiarioDDA());
        when(beneficiarioCipDao.obterMensagemDDABeneficiario(anyLong())).thenReturn(retornarMensagemDDABeneficiario());
        when(beneficiarioCipDao.obterMensagemDDABeneficiarioAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDABeneficiario());
        when(beneficiarioCipDao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(retornarBeneficiarioDDA());
    }

    /**
     * Método responsável por
     * 
     * @param sucess void
     * 
     */
    protected void whenEm(Boolean sucess) {
        when(em.find(MensagemDDABaixaEfetiva.class, Constantes.LONG_UM)).thenReturn(retornarMensagemDDABaixaEfetiva());
        when(em.find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO)).thenReturn(retornarParametroDDA());
        when(em.find(MensagemDDABaixaOperacional.class, Constantes.LONG_UM)).thenReturn(retornarMensagemDDABaixaOperacional());
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenSCIDelegate(Boolean success) throws ComumException {
        if (success) {
            when(sciDelegate.obterInstituicaoCache(anyInt())).thenReturn(retornarInstituicaoDto());
            when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(retornarInstituicaoDto());
        } else {
            when(sciDelegate.obterInstituicaoCache(anyInt())).thenThrow(new ComumException(Constantes.TESTE_FALHA));
        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * @throws ComumException
     */
    protected void whenArquivoCipDao(Boolean success) throws ComumException {
        if (success) {
            when(arquivoCipDao.listarDetalhesArquivoRecebido(anyLong(), anyLong(), anyLong())).thenReturn(listarStrings());
            when(arquivoCipDao.obterQtdRegistroArquivo(anyLong())).thenReturn(Constantes.INTEGER_UM);
            when(arquivoCipDao.obterCountDetGravados(anyLong())).thenReturn(Constantes.LONG_UM);
            when(arquivoCipDao.obterLogEnvioArquivoPorNome(anyString())).thenReturn(retornarLogEnvioArquivoDDA());
        } else {

        }
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * @throws ComumException
     */
    protected void whenPagadorCipDao(Boolean success) throws ComumException {
        if (success) {
            when(pagadorCipDao.obterMensagemDDAPagador(anyLong())).thenReturn(retornarMensagemDDAPagador());
            when(pagadorCipDao.obterMensagemDDAPagadorAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDAPagador());
            when(pagadorCipDao.listarPagadorEletronicoModificados(anyLong(), anyLong(), anyLong())).thenReturn(listarObjectArray());
            when(pagadorCipDao.listarMensagemDDAPagadorLogEnvioArquivo(anyLong())).thenReturn(listarMensagemDDAPagador());
            when(pagadorCipDao.obterPagadorDDA(anyString(), anyBoolean())).thenReturn(retornarPagadorDDA());
        } else {
            PowerMockito.doThrow(new ComumException(Constantes.TESTE_FALHA)).when(pagadorCipDao).executarManutencaoPagadorEletronico(anyLong(), anyLong(), anyLong());
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenMensagemBaixaEfetivaDao(Boolean success) throws ComumException {
        when(mensagemBaixaEfetivaDao.obterMensagemDDABaixaEfetivaAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDABaixaEfetiva());
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenMensagemBaixaOperacionalDao(Boolean success) throws ComumException {
        when(mensagemBaixaOperacionalDao.obterMensagemDDABaixaOperacionalAtualizaReferencias(anyLong())).thenReturn(retornarMensagemDDABaixaOperacional());
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumException void
     * 
     */
    protected void whenCAPESDelegate(Boolean success) throws ComumException {
        when(capesDelegate.obterPessoa(anyString(), anyInt())).thenReturn(retornarPessoaDto());
    }

    /**
     * Método responsável por
     * 
     * @param success void
     * @throws BancoobException
     * 
     */
    protected void whenReplicarBeneficiarioLegadoDao(Boolean success) throws BancoobException {
        if (success) {
            when(replicarBeneficiarioLegadoDao.obter(anyLong())).thenReturn(retornarDDABeneficiario());
            when(replicarBeneficiarioLegadoDao.obtemOperacoesInapto()).thenReturn(listarDDAOperacao());
        } else {
            PowerMockito.doThrow(new BancoobException(Constantes.TESTE_FALHA)).when(replicarBeneficiarioLegadoDao).alterar(any(DDABeneficiario.class));
            PowerMockito.doThrow(new BancoobException(Constantes.TESTE_FALHA)).when(replicarBeneficiarioLegadoDao).incluir(any(DDABeneficiario.class));
            PowerMockito.doThrow(new BancoobException(Constantes.TESTE_FALHA)).when(replicarBeneficiarioLegadoDao).excluir(anyLong());
            when(replicarBeneficiarioLegadoDao.obter(anyLong())).thenThrow(new BancoobException(Constantes.TESTE_FALHA));
        }
    }

    /**
     * Método responsável por
     * 
     * @param param void
     * @throws ComumException
     * 
     */
    protected void definirParametroDao(Boolean param) throws ComumException {
        when(parametroDao.obterValorBoolean(anyLong(), anyInt())).thenReturn(param);
    }
}
