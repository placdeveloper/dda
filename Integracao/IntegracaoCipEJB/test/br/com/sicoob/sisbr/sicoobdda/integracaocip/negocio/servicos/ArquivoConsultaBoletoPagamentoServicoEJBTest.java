package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoConsultaBoletoPagamentoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ArquivoConsultaBoletoPagamentoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoConsultaBoletoPagamentoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoConsultaBoletoPagamentoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoConsultaBoletoPagamentoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoConsultaBoletoPagamentoServicoEJB#obterSISARQ(java.lang.Long)}.
     */
    @Test
    public final void testObterSISARQ() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoConsultaBoletoPagamentoServicoEJB#processarArquivoRetornoConsultaBoletoDDA(long, long, long)}.
     */
    @Test
    public final void testProcessarArquivoRetornoConsultaBoletoDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarArquivoRetornoConsultaBoletoDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetADDA110() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA110", listarMensagemDDAConsultaBoleto()));
    }

}
