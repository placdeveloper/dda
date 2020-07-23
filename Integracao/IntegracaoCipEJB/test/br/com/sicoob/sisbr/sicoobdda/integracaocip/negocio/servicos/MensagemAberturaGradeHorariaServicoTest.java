/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemAberturaGradeHorariaServicoTest.java
 * Data Criação:    Jan 22, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAberturaGradeHorariaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemAberturaGradeHorariaServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAberturaGradeHorariaServicoTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAberturaGradeHorariaServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAberturaGradeHorariaServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAberturaGradeHorariaServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarMensagem() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarMensagem", Constantes.LONG_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAberturaGradeHorariaServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0403ComplexType())));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAberturaGradeHorariaServicoEJB#getMensagemDao()}.
     * 
     * @throws Exception
     */
    @Test
    @Ignore
    public final void testGetMensagemDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getMensagemDao")));
    }

}
