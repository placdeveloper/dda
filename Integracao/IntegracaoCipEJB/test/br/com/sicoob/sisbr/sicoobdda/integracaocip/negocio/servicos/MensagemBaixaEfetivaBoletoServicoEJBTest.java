package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemBaixaEfetivaBoletoServicoEJBTest é responsável por 
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemBaixaEfetivaBoletoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemBaixaEfetivaBoletoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB#processarRetornoMensagemDDA(long, long, long)}.
     */
    @Test
    public final void testProcessarRetornoMensagemDDALongLongLong() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        whenMensagemBaixaEfetivaDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDAConteudoMsgRecebida() throws ComumException {
        whenBoletoCipDao(Boolean.TRUE);
        when(em.find(MensagemDDABaixaEfetiva.class, Constantes.LONG_UM)).thenReturn(retornarMensagemDDABaixaEfetiva());
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0118R1ComplexType()));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaEfetivaBoletoServicoEJB#getBoletoDao()}.
     */
    @Test
    public final void testGetBoletoDao() {

    }

}
