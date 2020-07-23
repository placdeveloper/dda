package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarBoletoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ConversorBoletoDDA.class })
public class MensagemAlterarBoletoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAlterarBoletoServicoEJB ejb;

    @Before
    public void initThis() {
        PowerMockito.mockStatic(ConversorBoletoDDA.class);
    }
    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarBoletoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarBoletoServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        whenBoletoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarBoletoServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0110R1ComplexType()));

        BoletoDDA boleto = retornarBoletoDDA();
        boleto.setBolProcessarMensagemRecebida(Boolean.TRUE);
        PowerMockito.when(ConversorBoletoDDA.merge(any(ConteudoMsgRecebida.class), any(MensagemDDABoleto.class), any(BoletoDDA.class))).thenReturn(boleto);
        when(boletoCipDao.obterBoletoDDALock(anyLong())).thenReturn(boleto);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0110R1ComplexType()));
        boleto.setBolProcessarMensagemRecebida(Boolean.FALSE);
        boleto.setCodPartDestinatario("3066");
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0110R1ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoBeneficiarioOriginal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoBeneficiarioOriginal", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoBeneficiarioFinal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoBeneficiarioFinal", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoPagador() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoPagador", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoSacadorAvalista() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoSacadorAvalista", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoDocumentoBoleto() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoDocumentoBoleto", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoPagamentoBoleto() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoPagamentoBoleto", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetGrupoValorRecebimento() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setGrupoValorRecebimento", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoJuros() {
        MensagemDDABoleto menage = retornarMensagemDDABoleto();
        menage.setCodTipoJuros(Integer.valueOf(TipoJuros.ISENTO));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoJuros", menage, new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoMulta() {
        MensagemDDABoleto menage = retornarMensagemDDABoleto();
        menage.setCodTipoMulta(Constantes.INTEGER_UM);
        menage.setValorPercentualMulta(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoMulta", menage, new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoDesconto() {
        MensagemDDABoleto mensagem = retornarMensagemDDABoleto();
        mensagem.setValorPercentualDesconto1(Constantes.CEM);
        mensagem.setValorPercentualDesconto2(Constantes.CEM);
        mensagem.setValorPercentualDesconto3(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto", mensagem, new DDA0102ComplexType()));
        mensagem.setCodTipoDesconto2(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto", mensagem, new DDA0102ComplexType()));
        mensagem.setCodTipoDesconto3(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto", mensagem, new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoDesconto1() {
        MensagemDDABoleto mensagem = new MensagemDDABoleto();
        mensagem.setCodTipoDesconto1(Constantes.STRING_NUMERO_1);
        mensagem.setValorPercentualDesconto1(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto1", mensagem, new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoDesconto2() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto2", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoDesconto3() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoDesconto3", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoNotaFiscal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoNotaFiscal", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetMensagemDDABoletoTextoInfo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setMensagemDDABoletoTextoInfo", new MensagemDDABoleto(), new DDA0102ComplexType()));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarBoletoServicoEJB#getDao()}.
     */
    @Test
    public final void testGetDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getDao"));
    }

}
