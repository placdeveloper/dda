package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.AssignIdToArticleAnswer;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0015.GEN0015ComplexType;

/**
 * MensagemAvisaArquivoDisponivelServicoEJBTest é responsável por 
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAvisaArquivoDisponivelServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAvisaArquivoDisponivelServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB#processarMensagem(java.lang.Long)}.
     */
    @Test
    public final void testProcessarMensagem() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        when(em.find(TipoMensagemDDA.class, "1234567PRO")).thenReturn(retornarTipoMensagemDDA());
        doAnswer(new AssignIdToArticleAnswer(Constantes.LONG_UM, new LogRecebimentoArquivoDDA())).when(em).persist(any(LogRecebimentoArquivoDDA.class));
        whenArquivoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarGEN0015ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGravarLogRecebimentoArquivoDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "gravarLogRecebimentoArquivoDDA", retornarGEN0015ComplexType(), Constantes.STRING_NUMERO_1, null));
    }

    /**
     * Método responsável por void
     * 
     * @throws ParseException
     */
    @Test
    public final void testCriarLogRecebimentoArquivo() throws ParseException {
        GEN0015ComplexType gn = retornarGEN0015ComplexType();
        gn.setNomArq("123456789002458295820ER0341293842095842095829408528239482930852582452450");
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "criarLogRecebimentoArquivo", gn, retornarTipoMensagemDDA()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testDeterminarSituacaoArquivo() {
        TipoMensagemDDA mensagem = new TipoMensagemDDA();
        mensagem.setCodTipoMensagem(TipoMensagemDDA.ADDA001);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "determinarSituacaoArquivo", mensagem, Constantes.STRING_NUMERO_1));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetTipoMensagemPorNomeArquivo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getTipoMensagemPorNomeArquivo", null));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testRetirarSufixoNomeArquivo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "retirarSufixoNomeArquivo", Constantes.STRING_PALAVRA_FOI));
    }

    /**
     * Método responsável por
     * 
     * @return vid
     */
    @Test
    public final void testObterTipoArquivo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterTipoArquivo", Constantes.STRING_LETRA_S));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB#getArquivoRecebido()}.
     */
    @Test
    public final void testGetArquivoRecebido() {

    }

}
