package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoExclusaoTerceiroAutorizadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ArquivoExclusaoTerceiroAutorizadoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoExclusaoTerceiroAutorizadoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoExclusaoTerceiroAutorizadoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoExclusaoTerceiroAutorizadoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoExclusaoTerceiroAutorizadoServicoEJB#obterSISARQ(java.lang.Long)}.
     */
    @Test
    public final void testObterSISARQ() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetADDA122() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA122", listarMensagemDDATerceiroAut()));
    }
}
