/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.LOCAL_ARQUIVO_TEMPORARIOS;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTD_MAX_REGISTROS_POR_STEP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoRecebidoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates.ArquivoRecebidoDtoLoader;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates.ParametroDDALoader;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao;

/**
 * ArquivoRecebidoServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ SAXParserFactory.class })
public class ArquivoRecebidoServicoTest extends Mockito {

    @InjectMocks
    private ArquivoRecebidoServicoEJB ejb;

    @Mock
    private ArquivoRecebidoDao dao;
    @Mock
    private EntityManager em;
    @Mock
    private ParametroDao parametroDao;
    @Mock
    private ArquivoRecebidoCIPProcessadorServicoDelegate arquivoDelegate;
    @Mock
    private SAXParserFactory sxpf;
    @Mock
    private SAXParser sxp;
    @Mock
    private XMLReader xreader;

    /**
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException void
     * 
     */
    @Before
    public void setUp() throws ParserConfigurationException, SAXException, IOException {
        PowerMockito.mockStatic(SAXParserFactory.class);
        PowerMockito.when(SAXParserFactory.newInstance()).thenReturn(sxpf);
        when(sxpf.newSAXParser()).thenReturn(sxp);
        when(sxp.getXMLReader()).thenReturn(xreader);
        doNothing().when(xreader).parse(anyString());
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void alterarSituacaoArquivoRecebidoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, alterarSituacaoArquivoRecebido(Constantes.LONG_UM, Constantes.SHORT_UM));
        verify(dao, times(1)).alterarSituacaoArquivo(Constantes.LONG_UM, Constantes.SHORT_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void alterarSituacaoArquivoRecebidoIdLogInvalido() throws ComumException {
        Assert.assertEquals("integracaocip.erro.arquivo.nao.informado", alterarSituacaoArquivoRecebido(Constantes.LONG_ZERO, Constantes.SHORT_UM));
        verify(dao, times(0)).alterarSituacaoArquivo(Constantes.LONG_ZERO, Constantes.SHORT_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void alterarSituacaoArquivoRecebidoCodSituacaoInvalido() throws ComumException {
        Assert.assertEquals("integracaocip.erro.situacao.arquivo.nao.informada", alterarSituacaoArquivoRecebido(Constantes.LONG_UM, Constantes.SHORT_ZERO));
        verify(dao, times(0)).alterarSituacaoArquivo(Constantes.LONG_UM, Constantes.SHORT_ZERO);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void carregarListasArquivoRecebidoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, carregarListasArquivoRecebido());
        verify(dao, times(1)).listarTipoArquivo();
        verify(dao, times(1)).listarTipoMensagemAgenAdda();
        verify(dao, times(1)).listarSitucaoArquivo();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void obterLogDetRecebimentoArquivoDDAPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterLogDetRecebimentoArquivoDDA());
        verify(em, times(1)).find(LogDetRecebimentoArquivoDDA.class, Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void obterArquivoRecebidoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterArquivoRecebido());
        verify(dao, times(1)).obterArquivoRecebido(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void descriptografarArquivoPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, descriptografarArquivo(ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL)));
        verify(arquivoDelegate, times(1)).abrirArquivoParaProcessamento(Constantes.LONG_UM, Constantes.STRING_NUMERO_1 + File.separator + Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1,
                Constantes.INTEGER_UM);
        verify(dao, times(1)).obterArquivoRecebido(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void descriptografarArquivoNull() throws BancoobException {
        Assert.assertEquals("integracaocip.erro.arquivo.nao.informado", descriptografarArquivo(null));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void descriptografarArquivoIdInvalido() throws BancoobException {
        ArquivoRecebidoDto arquivo = ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);
        arquivo.setIdLogRecebimentoArqDDA(Constantes.LONG_ZERO);
        Assert.assertEquals("integracaocip.erro.arquivo.nao.informado", descriptografarArquivo(arquivo));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void descriptografarArquivoSituacaoInvalida() throws BancoobException {
        Assert.assertEquals("integracaocip.erro.situacao.arquivo.invalida", descriptografarArquivo(ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.ARQUIVO_ABERTO)));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void descriptografarArquivoSituacaoInexistente() throws BancoobException {
        Assert.assertEquals(TipoSituacaoArquivo.ARQUIVO_INEXISTENTE.getDescricao(),
                descriptografarArquivo(ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL), TipoSituacaoArquivo.ARQUIVO_INEXISTENTE));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void gravarDetalheArquivoPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, gravarDetalheArquivo(ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.ARQUIVO_ABERTO)));
        verify(dao, times(1)).obterArquivoRecebido(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void gravarDetalheArquivoSituacaoInvalida() throws BancoobException {
        Assert.assertEquals("integracaocip.erro.situacao.arquivo.invalida", gravarDetalheArquivo(ArquivoRecebidoDtoLoader.gerar(SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS)));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarArquivoSituacaoPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarArquivo(SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarArquivoSituacaoInvalida() throws BancoobException {
        Assert.assertEquals("integracaocip.erro.situacao.arquivo.invalida", processarArquivo(SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO));
    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebimentoArquivoDDA
     * @param codSituacao
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String alterarSituacaoArquivoRecebido(long idLogRecebimentoArquivoDDA, short codSituacao) {
        try {
            ejb.alterarSituacaoArquivoRecebido(idLogRecebimentoArquivoDDA, codSituacao);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String carregarListasArquivoRecebido() throws ComumException {
        ejb.carregarListasArquivoRecebido();
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#obterLogDetRecebimentoArquivoDDA(java.lang.Long)
     */
    private String obterLogDetRecebimentoArquivoDDA() throws ComumException {
        when(em.find(LogDetRecebimentoArquivoDDA.class, Constantes.LONG_UM)).thenReturn(preparaLogDetRecebimento());
        ejb.obterLogDetRecebimentoArquivoDDA(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String obterArquivoRecebido() throws ComumException {
        ejb.obterArquivoRecebido(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param arquivoDto
     * @return String
     * 
     */
    private String descriptografarArquivo(ArquivoRecebidoDto arquivoDto) {
        return descriptografarArquivo(arquivoDto, TipoSituacaoArquivo.ARQUIVO_OK);
    }

    /**
     * @param arquivoDto
     * @return String
     * 
     */
    private String descriptografarArquivo(ArquivoRecebidoDto arquivoDto, TipoSituacaoArquivo situacaoRetorno) {
        try {
            when(parametroDao.listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO)).thenReturn(
                    ParametroDDALoader.gerarLista(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO));
            when(
                    arquivoDelegate.abrirArquivoParaProcessamento(Constantes.LONG_UM, Constantes.STRING_NUMERO_1 + File.separator + Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1,
                            Constantes.INTEGER_UM)).thenReturn(situacaoRetorno);
            ejb.descriptografarArquivo(arquivoDto);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @param arquivoDto
     * @return String
     * 
     */
    public String gravarDetalheArquivo(ArquivoRecebidoDto arquivoDto) {
        try {
            when(parametroDao.listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, QTD_MAX_REGISTROS_POR_STEP, LOCAL_ARQUIVO_TEMPORARIOS)).thenReturn(
                    ParametroDDALoader.gerarLista(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, QTD_MAX_REGISTROS_POR_STEP, LOCAL_ARQUIVO_TEMPORARIOS));
            ejb.gravarDetalheArquivo(arquivoDto);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoProcessamento
     * @return String
     * 
     */
    private String processarArquivo(Short codSituacaoProcessamento) {
        try {
            when(dao.obterDadosProcessamentoArquivo(Constantes.LONG_UM)).thenReturn(new ArquivoProcessamentoVO(Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
            ejb.processarArquivo(ArquivoRecebidoDtoLoader.gerar(codSituacaoProcessamento));
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return LogDetRecebimentoArquivoDDA
     * 
     */
    private LogDetRecebimentoArquivoDDA preparaLogDetRecebimento() {
        LogDetRecebimentoArquivoDDA log = new LogDetRecebimentoArquivoDDA();
        log.setLogRecebimentoArquivoDDA(new LogRecebimentoArquivoDDA());
        log.getLogRecebimentoArquivoDDA().setId(Constantes.LONG_UM);
        return log;
    }
}
