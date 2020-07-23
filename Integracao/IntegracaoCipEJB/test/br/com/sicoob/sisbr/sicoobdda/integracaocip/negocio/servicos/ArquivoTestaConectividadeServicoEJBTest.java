package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;

/**
 * ArquivoTestaConectividadeServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoTestaConectividadeServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoTestaConectividadeServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeServicoEJB#verificarDisponibilidade()}.
     */
    @Test
    public final void testVerificarDisponibilidade() {

    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoTestaConectividadeServicoEJB#obterSISARQ(java.lang.Long)}.
     */
    @Test
    public final void testObterSISARQ() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testMontaObjetoAgen001ComplexType() {
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "montaObjetoAgen001ComplexType", new AGEN001ComplexType(), Constantes.STRING_PALAVRA_FORAM));
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "montaObjetoAgen001ComplexType", new AGEN001ComplexType(), "<MsgECO>" + Constantes.STRING_PALAVRA_FORAM + "</MsgECO>"));
    }

}
