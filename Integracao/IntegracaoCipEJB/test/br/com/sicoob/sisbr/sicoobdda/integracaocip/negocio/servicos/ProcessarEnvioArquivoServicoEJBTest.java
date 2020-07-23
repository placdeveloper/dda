package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;

/**
 * ProcessarEnvioArquivoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ EscritorXMLArquivoUtil.class, ArquivoUtil.class, ZipUtil.class })
public class ProcessarEnvioArquivoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ProcessarEnvioArquivoServicoEJB ejb;

    @Before
    public void initThis() {
        PowerMockito.mockStatic(EscritorXMLArquivoUtil.class);
        PowerMockito.mockStatic(ArquivoUtil.class);
        PowerMockito.mockStatic(ZipUtil.class);
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#getDao()}.
     */
    @Test
    public final void testGetDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getDao"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#getParametroDao()}.
     */
    @Test
    public final void testGetParametroDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getParametroDao"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#registrarArquivo(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Integer, java.util.Date)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testRegistrarArquivo() throws ComumException {
        whenParametroDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "registrarArquivo"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#gerarArquivo(java.lang.Long)}.
     * Testando também MÉTODOS FALTANTES de IntegracaoCipServicoEJB: gravarArquivoAbertoDiretorio
     * 
     * @throws ComumException
     * @throws IOException
     */
    @Test
    public final void testGerarArquivo() throws ComumException, IOException {
        whenIntegracaoCipDao(Boolean.TRUE);
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "gerarArquivo", Constantes.LONG_UM));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#gerarArquivo(java.lang.Long)}.
     * Testando também MÉTODOS FALTANTES de IntegracaoCipServicoEJB: gravarArquivoAbertoDiretorio
     * 
     * @throws ComumException
     * @throws IOException
     */
    @Test
    public final void testGerarArquivoIOException() throws ComumException, IOException {
        whenIntegracaoCipDao(Boolean.TRUE);
        whenEscritorXMLArquivoUtil(Boolean.FALSE);
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "gerarArquivo", Constantes.LONG_UM));
    }

    /**
     * Testando também MÉTODOS FALTANTES de IntegracaoCipServicoEJB: postarArquivoDiretorio
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#postarArquivo(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testPostarArquivo() throws ComumException {
        whenIntegracaoCipDao(Boolean.TRUE);
        when(file.exists()).thenReturn(Boolean.TRUE);
        when(ArquivoUtil.getBytesFromFileSemLimite(any(File.class))).thenReturn(retornarByte());
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP)).thenReturn(retornarParametroDDA());
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_CIP)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "postarArquivo", Constantes.LONG_UM));
    }

    /**
     * Testando também MÉTODOS FALTANTES de IntegracaoCipServicoEJB: salvarArquivo
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#postarArquivo(java.lang.Long)}.
     * 
     * @throws ComumException
     * @throws IOException 
     */
    public final void testPostarArquivoIOException() throws ComumException, IOException {
        whenIntegracaoCipDao(Boolean.TRUE);
        when(file.exists()).thenReturn(Boolean.TRUE);
        when(ArquivoUtil.getBytesFromFileSemLimite(any(File.class))).thenReturn(retornarByte());
        PowerMockito.doThrow(new IOException()).when(EscritorXMLArquivoUtil.class);
        EscritorXMLArquivoUtil.salvarArquivo(any(byte[].class), anyString());
        whenZipUtil(Boolean.TRUE);
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP)).thenReturn(retornarParametroDDA());
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_CIP)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "postarArquivo", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarEnvioArquivoServicoEJB#tratarArquivosComErro(java.lang.Long, java.util.Date)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testTratarArquivosComErro() throws ComumException {
        whenIntegracaoCipDao(Boolean.TRUE);
        when(file.isFile()).thenReturn(Boolean.TRUE);
        when(em.find(ParametroDDA.class, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "tratarArquivosComErro", Constantes.LONG_UM, DateUtil.somarDias(Constantes.DATE_AUX, Constantes.INTEGER_UM)));
    }

}
