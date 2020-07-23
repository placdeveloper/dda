package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeArquivosServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ArquivoTestaConectividadeArquivosServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoTestaConectividadeArquivosServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoTestaConectividadeArquivosServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeArquivosServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeArquivosServicoEJB#processarTesteConectividade()}.
     */
    @Test
    public final void testProcessarTesteConectividade() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeArquivosServicoEJB#processarRetornoCIPTesteConectividadePorArquivo(java.lang.String)}.
     */
    @Test
    public final void testProcessarRetornoCIPTesteConectividadePorArquivo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoCIPTesteConectividadePorArquivo", Constantes.SUFIXO_ARQUIVO_PROTOCOLO));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoCIPTesteConectividadePorArquivo", Constantes.SUFIXO_ARQUIVO_ERRO));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoCIPTesteConectividadePorArquivo", Constantes.SUFIXO_ARQUIVO_RETORNO));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetAGEN001() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getAGEN001"));
    }

}
