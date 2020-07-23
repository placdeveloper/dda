/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ArquivoRecebidoCIPProcessadorServicoTest.java
 * Data Criação:    Dec 7, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarRetornoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SSPBDelegate;

/**
 * ArquivoRecebidoCIPProcessadorServicoTest é responsável por
 * 
 * @author Adriano.Pinheiro
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoInternaFabricaDelegates.class, ArquivoUtil.class, FileInputStream.class, File.class, ZipUtil.class, SAXParserFactory.class,
        IntegracaoCipFabricaDelegates.class })
public class ArquivoRecebidoCIPProcessadorServicoTest extends Mockito {
    @InjectMocks
    private ArquivoRecebidoCIPProcessadorServicoEJB processadorEJB;

    @Mock
    private MensagemDDADao msgDDADao;

    @Mock
    private ArquivoCipDao arquivoCipDao;

    @Mock
    private IntegracaoInternaFabricaDelegates fabrica;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaInternaDelegates;

    @Mock
    private EntityManager em;

    @Mock
    private SSPBDelegate sspb;

    @Mock
    private ProcessarRetornoDDADelegate procesarRetornoDelegate;

    @Mock
    private FileInputStream fileInputStreamMock;

    @Mock
    private File file;

    @Mock
    private SAXParserFactory sxpf;

    @Mock
    private SAXParser sxp;

    @Mock
    private XMLReader xreader;

    private final String nmArquivo = "xpto.zip";

    @Before
    public void setUp() throws Exception {

    }

    /**
     * Método responsável por testar a rotina que abre um arquivo recebido da cip
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void abrirArquivoParaProcessamentoTest() throws ComumException {

        // dados os parâmetros
        long arqRec = 1L;
        int tmpPosAlteracao = 2;

        String localSalva = "d:/";

        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        PowerMockito.mockStatic(ArquivoUtil.class);
        PowerMockito.mockStatic(ZipUtil.class);

        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(fabrica);
        when(ArquivoUtil.verificaSituacaoDoArquivo(any(File.class), anyInt())).thenReturn(any(TipoSituacaoArquivo.class));
        when(fabrica.getSSPBDelegate()).thenReturn(sspb);
        // when(sspb.decriptar(any(File.class))).thenReturn(new byte[255]);
        when(ZipUtil.descompactarArquivoCIP(any(byte[].class), any(FileOutputStream.class))).thenReturn(true);

        doNothing().when(arquivoCipDao).alterarSituacaoProcessamento(anyLong(), anyLong());

        try {
            PowerMockito.whenNew(File.class).withArguments(nmArquivo).thenReturn(file);
            PowerMockito.whenNew(FileInputStream.class).withArguments(file).thenReturn(fileInputStreamMock);

            when(file.length()).thenReturn(255L);
            when(fileInputStreamMock.read()).thenReturn(5);

            this.processadorEJB.abrirArquivoParaProcessamento(arqRec, nmArquivo, localSalva, tmpPosAlteracao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por testa a rotina que graga os registros de um arquivo do tipo DIS
     * 
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException void
     * 
     */
    @Test
    public void gravaDetalhesArquivoDISTest() throws IOException, SAXException, ParserConfigurationException {

        ArquivoProcessamentoVO arqProc = geraArquivoParaProc(TipoArquivoRetornoEnum.DIS, "ADDA002", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        try {
            arquivoCipDao.excluirRegistrosDetalheRecebidos(anyLong(), anyInt(), anyInt());
            PowerMockito.mockStatic(SAXParserFactory.class);
            PowerMockito.when(SAXParserFactory.newInstance()).thenReturn(sxpf);
            when(sxpf.newSAXParser()).thenReturn(sxp);
            when(sxp.getXMLReader()).thenReturn(xreader);
            doNothing().when(xreader).parse(anyString());

            processadorEJB.gravarDetalhes(arqProc, "teste000001.xml", 10);
        } catch (BancoobException e) {
            Assert.fail();
        }

    }

    /**
     * Método responsável por testar a rotina que grava os registros de um arquivo do tipo RET
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException void
     * 
     */
    @Test
    public void gravarDetalhesArquivoRETTest() throws ParserConfigurationException, SAXException, IOException {
        ArquivoProcessamentoVO arqProc = geraArquivoParaProc(TipoArquivoRetornoEnum.RET, "ADDA101RET", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        try {
            arquivoCipDao.excluirRegistrosDetalheRecebidos(anyLong(), anyInt(), anyInt());
            PowerMockito.mockStatic(SAXParserFactory.class);
            PowerMockito.when(SAXParserFactory.newInstance()).thenReturn(sxpf);
            when(sxpf.newSAXParser()).thenReturn(sxp);
            when(sxp.getXMLReader()).thenReturn(xreader);
            doNothing().when(xreader).parse(anyString());

            processadorEJB.gravarDetalhes(arqProc, "teste000001.xml", 10);
        } catch (BancoobException e) {
            Assert.fail();
        }
    }

    /**
     * Método responsável por testar a rotina responsável por testar um arauivo do tipo PRO void
     * 
     */
    @Test
    public void gravarDetalhesArquivoPROTest() {

        ArquivoProcessamentoVO arqProc = geraArquivoParaProc(TipoArquivoRetornoEnum.PRO, "ADDA101PRO", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        try {

            when(msgDDADao.listarIdsMensagensEnviadas(anyLong())).thenReturn(this.gerarListaIds());
            arquivoCipDao.atualizarMensagensComProtocolo(anyListOf(Object[].class));

            processadorEJB.atualizarMensagemProtocoloErro(nmArquivo, arqProc.getIdArquivoEnviado(), TipoArquivoRetornoEnum.PRO);
        } catch (BancoobException e) {
            Assert.fail();
        }
    }

    /**
     * Método responsável por testar a rotina de gravação dos registros de um arquivo de erro void
     * 
     */
    @Test
    public void gravarDetalhesArquivoERRTest() {

        ArquivoProcessamentoVO arqProc = geraArquivoParaProc(TipoArquivoRetornoEnum.ERR, "ADDA101ERR", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        try {

            when(msgDDADao.listarIdsMensagensEnviadas(anyLong())).thenReturn(this.gerarListaIds());
            arquivoCipDao.atualizarMensagensComProtocolo(anyListOf(Object[].class));

            processadorEJB.atualizarMensagemProtocoloErro(nmArquivo, arqProc.getIdArquivoEnviado(), TipoArquivoRetornoEnum.ERR);
        } catch (BancoobException e) {
            Assert.fail();
        }
    }

    /**
     * Método responsável por testar a rotina que altera o status dos arquivos após a execução do job de gravação que gera steps em paralelos
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void conciliarArquivoGravadoTest() throws ComumException {
        processadorEJB.atualizarSituacaoProcessamentoArquivoRecebido(1L, SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS);
    }

    /**
     * Método responsável por testar a rotina de processamento dos detalhes do arquivo
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarArquivoTest() throws BancoobException {

        TipoInstanciaProcessamentoEnum instancia = TipoInstanciaProcessamentoEnum.BEN;

        when(arquivoCipDao.listarDetalhesArquivoRecebido(anyLong(), anyLong(), anyLong())).thenReturn(gerarListaDeParametros());
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(fabricaInternaDelegates);

        when(fabricaInternaDelegates.getProcessarRetornoDDADelegate()).thenReturn(procesarRetornoDelegate);

        doNothing().when(procesarRetornoDelegate).processarMensagemRecebida(gerarListDeRegistros());

        processadorEJB.processarDetalhes(instancia, Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM, TipoMensagemDDA.ADDA504, 10);

    }

    /**
     * Método responsável por testar a rotina de alteração da situação do processamento do arquivo para processado
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void finalizarArquivoTest() throws BancoobException {

        when(em.find(LogRecebimentoArquivoDDA.class, 1l)).thenReturn(gerarLogRecebimentoArquivo());
        when(em.find(SituacaoProcessamentoArquivo.class, SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO)).thenReturn(gerarSituacaoProcessamentoArquivo());

        when(em.merge(any(LogRecebimentoArquivoDDA.class))).thenReturn(gerarLogRecebimentoArquivo());
        doNothing().when(em).flush();

        processadorEJB.atualizarSituacaoProcessamentoArquivoRecebido(geraArquivoParaProc(TipoArquivoRetornoEnum.DIS, "ADDA504", SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO)
                .getIdArquivoRecebido(), SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO);

    }

    /**
     * Método responsável por gerar uma instância de uma entidade situação de processamento
     * 
     * @return SituacaoProcessamentoArquivo
     * 
     */
    private SituacaoProcessamentoArquivo gerarSituacaoProcessamentoArquivo() {

        return new SituacaoProcessamentoArquivo();
    }

    /**
     * Método responsável por testar a entrada no tratamento de exceção que geraria uma exceção do tipo ComumException, no entanto é gerado uma runtime por não
     * encontrar o recurso do arquivo de propriedades por isto é garada uma exceção to tipo Runtime, contudo o tratamento da exceção é verificada da mesma forma
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test(expected = RuntimeException.class)
    public void falhaEmAbrirArquivoParaProcessamentoArquivoTest() throws ComumException {

        // dados os parâmetros
        long arqRec = 1L;
        int tmpPosAlteracao = 2;

        String localSalva = "d:/";

        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(fabrica);

        PowerMockito.mockStatic(ArquivoUtil.class);
        when(ArquivoUtil.verificaSituacaoDoArquivo(any(File.class), anyInt())).thenReturn(any(TipoSituacaoArquivo.class));

        when(fabrica.getSSPBDelegate()).thenReturn(sspb);
        // when(sspb.decriptar(any(File.class))).thenThrow(ComumException.class);

        this.processadorEJB.abrirArquivoParaProcessamento(arqRec, nmArquivo, localSalva, tmpPosAlteracao);

    }

    /**
     * Método responsável por gera uma instancia da entidade LogrecebimentoArquivoDDA
     * 
     * @return LogRecebimentoArquivoDDA
     * 
     */
    private LogRecebimentoArquivoDDA gerarLogRecebimentoArquivo() {
        return new LogRecebimentoArquivoDDA();
    }

    /**
     * Método responsável por gerar uma lista Stub de strings representando registros retornados
     * 
     * @return String
     * 
     */
    private List<String> gerarListDeRegistros() {
        ArrayList<String> registros = new ArrayList<String>();

        registros.add("");
        registros.add("");
        registros.add("");
        registros.add("");

        return registros;
    }

    /**
     * Método responsável por gerar uma lista Stub de string representando parametros de steps
     * 
     * @return List<String>
     * 
     */
    private List<String> gerarListaDeParametros() {

        ArrayList<String> parametros = new ArrayList<String>();

        parametros.add("");
        parametros.add("");
        parametros.add("");
        parametros.add("");

        return parametros;
    }

    /**
     * Método responsável por gerar um lista stub de long representando ids de registros
     * 
     * @return List<Long>
     * 
     */
    private List<Long> gerarListaIds() {

        ArrayList<Long> lIds = new ArrayList<Long>();

        lIds.add(1L);
        lIds.add(2L);
        lIds.add(3L);

        return lIds;
    }

    /**
     * Método responsável por gerar uma instancia do VO ArquivoParaProc
     * 
     * @param tipoDoArquivo
     * @param tipoDaMensagem
     * @param statusArq
     * @return ArquivoParaProc
     * 
     */
    private ArquivoProcessamentoVO geraArquivoParaProc(TipoArquivoRetornoEnum tipoDoArquivo, String tipoDaMensagem, short codSituacaoProcessamentoArquivo) {
        return new ArquivoProcessamentoVO(1L, tipoDoArquivo, "TESTE", 1L, tipoDaMensagem, codSituacaoProcessamentoArquivo);
    }

}
