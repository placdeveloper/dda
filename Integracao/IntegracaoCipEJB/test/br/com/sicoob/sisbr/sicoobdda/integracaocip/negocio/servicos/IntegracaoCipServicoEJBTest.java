package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;

/**
 * IntegracaoCipServicoEJBTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ArquivoUtil.class, ZipUtil.class, LeitorXmlUtil.class })
public class IntegracaoCipServicoEJBTest extends ServicosTestUtil {

    private IntegracaoCipServicoEJB ejb = Mockito.mock(IntegracaoCipServicoEJB.class, Mockito.CALLS_REAL_METHODS);

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     */
    @Before
    public void initThis() throws BancoobException {
        setarValorAtributoPrivado(ejb, "integracaoFilaSSPBEnvioDDAServico", integracaoFilaSSPBEnvioDDAServico);
        PowerMockito.mockStatic(ArquivoUtil.class);
        PowerMockito.mockStatic(ZipUtil.class);
        PowerMockito.mockStatic(LeitorXmlUtil.class);

    }

    @Mock
    protected IntegracaoFilaSSPBEnvioDDAServicoEJB integracaoFilaSSPBEnvioDDAServico;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getDiretorio(java.lang.Long)}.
     */
    @Ignore
    @Test
    public final void testGetDiretorio() {
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#postarMensagemFilaSSPBDDA(java.lang.String)}.
     * 
     * @throws BancoobException
     */
    @Test
    public final void testPostarMensagemFilaSSPBDDA() throws BancoobException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "postarMensagemFilaSSPBDDA", Constantes.STRING_PALAVRA_FORAM));
        PowerMockito.doThrow(new ComumException(Constantes.TESTE_FALHA)).when(integracaoFilaSSPBEnvioDDAServico).enviar(any(Serializable.class));
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "postarMensagemFilaSSPBDDA", Constantes.STRING_PALAVRA_FORAM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#postarMensagemFilaRecebimentoSSPBDDA(java.lang.String)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testPostarMensagemFilaRecebimentoSSPBDDA() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "postarMensagemFilaRecebimentoSSPBDDA", Constantes.STRING_NUMERO_1));
        PowerMockito.doThrow(new ComumException(Constantes.TESTE_FALHA)).when(integracaoFilaSSPBEnvioDDAServico).enviarFilaRecebimento(any(Serializable.class));
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "postarMensagemFilaRecebimentoSSPBDDA", Constantes.STRING_NUMERO_1));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#criarMensagemEnvioDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)}.
     * 
     * @throws BancoobException
     */
    @Test
    public final void testCriarMensagemEnvioDDA() throws BancoobException {
        setarValorAtributoPrivado(ejb, "mensagemDDADelegate", mensagemDDADelegate);
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "criarMensagemEnvioDDA", complexType, Constantes.INTEGER_UM, Constantes.STRING_NUMERO_1, Constantes.INTEGER_UM, Constantes.SHORT_ZERO));
    }

    /**
     * Testável apenas no método da SolidClass;
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#postarArquivoDiretorio(java.lang.Long, br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA, java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    @Ignore
    public final void testPostarArquivoDiretorio() throws Exception {
        try {

            when(file.exists()).thenReturn(Boolean.TRUE);
            whenArquivoUtil(Boolean.TRUE);
            setarValorAtributoPrivado(ejb, "fileOrigem", file);
            assertEquals(Constantes.TESTE_SUCESSO,
                    genericTests(ejb, "postarArquivoDiretorio", Constantes.LONG_UM, new LogEnvioArquivoDDA(Constantes.LONG_UM), Constantes.STRING_NUMERO_1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Testável apenas no método da SolidClass;
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#gravarArquivoAbertoDiretorio(java.lang.Object, java.lang.Long, br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA)}.
     */
    @Test
    public final void testGravarArquivoAbertoDiretorio() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#obterObjectArquivoRecebido(java.lang.Class, java.io.File)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterObjectArquivoRecebido() throws ComumException {
        whenArquivoUtil(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterObjectArquivoRecebido", null, file));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#obterObjectArquivoRecebido(java.lang.Class, java.io.File)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterObjectArquivoRecebidoComumException() throws ComumException {
        whenArquivoUtil(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "obterObjectArquivoRecebido", null, file));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#obterObjectArquivoRecebido(java.lang.Class, java.io.File)}.
     * 
     * @throws ComumException
     * @throws IOException
     */
    @Test
    public final void testObterObjectArquivoRecebidoIOException() throws ComumException, IOException {
        whenArquivoUtil(Boolean.TRUE);
        whenZipUtil(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "obterObjectArquivoRecebido", null, file));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#obterObjectArquivo(java.io.File)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterObjectArquivo() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterObjectArquivo", file));
        whenArquivoUtil(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "obterObjectArquivo", file));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#validarBeneficiarioCadastrado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)}.
     */
    @Test
    public final void testValidarBeneficiarioCadastrado() {
        BeneficiarioDDA beneficiario = retornarBeneficiarioDDA();
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "validarBeneficiarioCadastrado", beneficiario));
        beneficiario.setNumIdentBeneficiario(Constantes.LONG_UM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "validarBeneficiarioCadastrado", beneficiario));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#verificaReplicacaoLegadoAutorizada()}.
     */
    @Test
    public final void testVerificaReplicacaoLegadoAutorizada() {

    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getCapesDelegate()}.
     */
    @Test
    public final void testGetCapesDelegate() {

    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getIntegracaoFilaSSPBEnvioDDAServico()}.
     */
    @Test
    public final void testGetIntegracaoFilaSSPBEnvioDDAServico() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#setIntegracaoFilaSSPBEnvioDDAServico(br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.IntegracaoFilaSSPBEnvioDDAServicoLocal)}.
     */
    @Test
    public final void testSetIntegracaoFilaSSPBEnvioDDAServico() {

    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getSspbDelegate()}.
     */
    @Test
    public final void testGetSspbDelegate() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#setSspbDelegate(br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SSPBDelegate)}.
     */
    @Test
    public final void testSetSspbDelegate() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#removerRelacionamentoBoletoDDA(br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA, br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao)}.
     */
    @Test
    public final void testRemoverRelacionamentoBoletoDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "removerRelacionamentoBoletoDDA", retornarBoletoDDA(), boletoCipDao));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#prepararArquivoParaProcessamento(java.io.File)}.
     * 
     * @throws IOException
     */
    @Test
    public final void testPrepararArquivoParaProcessamento() throws IOException {
        File file1 = folder.newFile("teste.txt");
        whenZipUtil(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "prepararArquivoParaProcessamento", file1));
        whenZipUtil(Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "prepararArquivoParaProcessamento", file1));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#incluirMensagemDDA0106(br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA)}.
     * 
     * @throws BancoobException
     */
    @Test
    public final void testIncluirMensagemDDA0106() throws BancoobException {
        whenInformacoesUsuario(Boolean.TRUE);
        setarValorAtributoPrivado(ejb, "mensagemDDADelegate", mensagemDDADelegate);
        BoletoDDA boleto = retornarBoletoDDA();
        boleto.setCodPartDestinatario(TipoBoletoConsultado.PROPRIO);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "incluirMensagemDDA0106", boleto));
    }

    /**
     * Método responsável por void
     * 
     * @throws IOException
     */
    @Test
    public final void testCompactarArquivo() throws IOException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "compactarArquivo", retornarByte(), Constantes.STRING_NUMERO_0));
        whenZipUtil(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "compactarArquivo", retornarByte(), Constantes.STRING_NUMERO_0));
    }

}
