package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ArquivoRecebidoCIPProcessadorServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ArquivoUtil.class, ZipUtil.class })
public class ArquivoRecebidoCIPProcessadorServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoRecebidoCIPProcessadorServicoEJB ejb;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    /**
     * Método responsável por void
     */
    @Before
    public void initThis() {
        PowerMockito.mockStatic(ArquivoUtil.class);
        PowerMockito.mockStatic(ZipUtil.class);
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#abrirArquivoParaProcessamento(long, java.lang.String, java.lang.String, int)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testAbrirArquivoParaProcessamento() throws Exception {
        whenSSPBDelegate(Boolean.TRUE);
        PowerMockito.when(ArquivoUtil.verificaSituacaoDoArquivo(any(File.class), anyInt())).thenReturn(TipoSituacaoArquivo.ARQUIVO_OK);
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "abrirArquivoParaProcessamento", Constantes.LONG_UM, Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1, Constantes.INTEGER_UM));
        PowerMockito.when(ArquivoUtil.verificaSituacaoDoArquivo(any(File.class), anyInt())).thenReturn(TipoSituacaoArquivo.ARQUIVO_EM_TRANSFERENCIA);
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "abrirArquivoParaProcessamento", Constantes.LONG_UM, Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1, Constantes.INTEGER_UM));
        PowerMockito.doThrow(new IOException(Constantes.TESTE_FALHA)).when(ZipUtil.class);
        ZipUtil.descompactarArquivoCIP(any(byte[].class), anyString());
        PowerMockito.when(ArquivoUtil.verificaSituacaoDoArquivo(any(File.class), anyInt())).thenReturn(TipoSituacaoArquivo.ARQUIVO_OK);
        assertEquals(Constantes.TESTE_FALHA,
                genericTests(ejb, "abrirArquivoParaProcessamento", Constantes.LONG_UM, Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1, Constantes.INTEGER_UM));
        whenSSPBDelegate(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA,
                genericTests(ejb, "abrirArquivoParaProcessamento", Constantes.LONG_UM, Constantes.STRING_NUMERO_1, Constantes.STRING_NUMERO_1, Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#abrirArquivoCIP(java.util.List)}.
     * 
     * @throws ComumException
     * @throws IOException
     */
    @Test
    public final void testAbrirArquivoCIP() throws ComumException, IOException {
        whenSSPBDelegate(Boolean.TRUE);
        when(ArquivoUtil.getBytesFromFileSemLimite(any(File.class))).thenReturn(retornarByte());
        ParametroDDA parametro1 = new ParametroDDA(ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, "DIRETORIO_ARQUIVOS_RECEBIDOS_CIP", "DIRETORIO_ARQUIVOS_RECEBIDOS_CIP");
        ParametroDDA parametro2 = new ParametroDDA(ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, "DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO",
                "DIRETORIO_ARQUIVOS_RECEBIDOS_CIP");
        List<ParametroDDA> listaParametroDDA = new ArrayList<ParametroDDA>();
        listaParametroDDA.add(parametro1);
        listaParametroDDA.add(parametro2);
        when(parametroDao.listarParametros(anyLong(), anyLong())).thenReturn(listaParametroDDA);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "abrirArquivoCIP", listarStrings()));
        PowerMockito.doThrow(new IOException(Constantes.TESTE_FALHA)).when(ZipUtil.class);
        ZipUtil.descompactarArquivoCIP(any(byte[].class), anyString());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "abrirArquivoCIP", listarStrings()));
        whenSSPBDelegate(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "abrirArquivoCIP", listarStrings()));

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)}.
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @Test
    public final void testGravarDetalhes() throws ParserConfigurationException, SAXException {
        whenSAXParserFactory(Boolean.TRUE);
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO();
        arquivo.setTipoArquivo(TipoArquivoRetornoEnum.RET);
        arquivo.setTipoDaMensagem("ADDA005RET");
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "gravarDetalhes", arquivo, "1234567890.xml", Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)}.
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ComumException
     */
    @Test
    public final void testGravarDetalhesComumException() throws ParserConfigurationException, SAXException, ComumException {
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO();
        arquivo.setTipoArquivo(TipoArquivoRetornoEnum.RET);
        PowerMockito.doThrow(new ComumException(Constantes.TESTE_FALHA)).when(arquivoCipDao).excluirRegistrosDetalheRecebidos(anyLong(), anyInt(), anyInt());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "gravarDetalhes", arquivo, "1234567890.xml", Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)}.
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ComumException
     */
    @Test
    public final void testGravarDetalhesParserConfigurationException() throws ParserConfigurationException, SAXException, ComumException {
        whenSAXParserFactory(Boolean.TRUE);
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO();
        arquivo.setTipoArquivo(TipoArquivoRetornoEnum.RET);
        when(saxParserFactory.newSAXParser()).thenThrow(new ParserConfigurationException());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "gravarDetalhes", arquivo, "1234567890.xml", Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)}.
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ComumException
     */
    @Test
    public final void testGravarDetalhesSAXException() throws ParserConfigurationException, SAXException, ComumException {
        whenSAXParserFactory(Boolean.TRUE);
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO();
        arquivo.setTipoArquivo(TipoArquivoRetornoEnum.RET);
        when(saxParserFactory.newSAXParser()).thenThrow(new SAXException());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "gravarDetalhes", arquivo, "1234567890.xml", Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)}.
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ComumException
     * @throws IOException
     */
    @Test
    public final void testGravarDetalhesIOException() throws ParserConfigurationException, SAXException, ComumException, IOException {
        whenSAXParserFactory(Boolean.TRUE);
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO();
        arquivo.setTipoArquivo(TipoArquivoRetornoEnum.RET);
        arquivo.setTipoDaMensagem("ADDA005RET");
        PowerMockito.doThrow(new IOException()).when(xmlReader).parse(anyString());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "gravarDetalhes", arquivo, "1234567890.xml", Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#atualizarSituacaoProcessamentoArquivoRecebido(long, short)}.
     */
    @Test
    public final void testAtualizarSituacaoProcessamentoArquivoRecebido() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "atualizarSituacaoProcessamentoArquivoRecebido", Constantes.LONG_UM, Constantes.SHORT_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#atualizarSituacaoProcessamentoArquivoRecebido(long, short)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testAtualizarSituacaoProcessamentoArquivoRecebidoComumException() throws ComumException {
        PowerMockito.doThrow(new ComumException(Constantes.TESTE_FALHA)).when(arquivoCipDao).alterarSituacaoProcessamento(anyLong(), anyLong());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "atualizarSituacaoProcessamentoArquivoRecebido", Constantes.LONG_UM, Constantes.SHORT_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#processarDetalhes(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum, long, long, long, java.lang.String, int)}.
     * 
     * @throws BancoobException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testProcessarDetalhes() throws BancoobException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA002, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA003, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA106, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA101RR2, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA108RR2, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA118RR2, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA102RR2, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA101RET, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA102RET, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA117, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA110RET, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA118RET, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA108RET, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA127, Constantes.INTEGER_UM));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA001, Constantes.INTEGER_UM));
        List<String> listaString = new ArrayList<String>();
        listaString.add(Constantes.STRING_NUMERO_1);
        listaString.add(Constantes.STRING_NUMERO_0);
        when(arquivoCipDao.listarDetalhesArquivoRecebido(anyLong(), anyLong(), anyLong())).thenReturn(listaString);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM,
                Constantes.LONG_UM, TipoMensagemDDA.ADDA001, Constantes.INTEGER_UM));
        PowerMockito.doThrow(new BancoobException(Constantes.TESTE_FALHA)).when(processarRetornoDDADelegate).processarMensagemRecebida(anyList());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "processarDetalhes", TipoInstanciaProcessamentoEnum.BAX, Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM,
                TipoMensagemDDA.ADDA001, Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#conciliarDetalhesGravados(long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testConciliarDetalhesGravados() throws ComumException {
        whenArquivoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "conciliarDetalhesGravados", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoRecebidoCIPProcessadorServicoEJB#atualizarMensagemProtocoloErro(java.lang.String, long, br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testAtualizarMensagemProtocoloErro() throws ComumException {
        assertEquals(Constantes.TESTE_FALHA,
                genericTests(ejb, "atualizarMensagemProtocoloErro", Constantes.STRING_PALAVRA_FORAM, Constantes.LONG_UM, TipoArquivoRetornoEnum.PRO));
        whenMensagemDDADao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "atualizarMensagemProtocoloErro", Constantes.STRING_PALAVRA_FORAM, Constantes.LONG_UM, TipoArquivoRetornoEnum.PRO));
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "atualizarMensagemProtocoloErro", Constantes.STRING_PALAVRA_FORAM, Constantes.LONG_UM, TipoArquivoRetornoEnum.ERR));
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "atualizarMensagemProtocoloErro", Constantes.STRING_PALAVRA_FORAM, Constantes.LONG_UM, TipoArquivoRetornoEnum.ADD));

    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGravarLogDetRecebimentoArquivoDDAEmLote() {
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "gravarLogDetRecebimentoArquivoDDAEmLote", retornarArquivoProcessamentoVO(), listarLogDetRecebimentoArquivoDDA(), Constantes.INTEGER_UM));
    }
}
