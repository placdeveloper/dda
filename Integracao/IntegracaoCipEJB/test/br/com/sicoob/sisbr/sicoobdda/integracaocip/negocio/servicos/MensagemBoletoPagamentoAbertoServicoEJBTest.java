package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;

/**
 * MensagemBoletoPagamentoAbertoServicoEJBTest é responsável por 
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemBoletoPagamentoAbertoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemBoletoPagamentoAbertoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB#processarMensagem(java.lang.Long)}.
     */
    @Test
    public final void testProcessarMensagem() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        whenBoletoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarGrupoADDA127TitComplexType()));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0127ComplexType()));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB#getDao()}.
     */
    @Test
    public final void testGetDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getDao"));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarGrupoBoleto() throws ComumException {
        BoletoDDA boleto = retornarBoletoDDA();
        boleto.setNumSeqAtualCadBoleto(Constantes.LONG_MIL);
        when(boletoCipDao.obterBoletoDDALock(anyLong())).thenReturn(boleto);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarGrupoBoleto", retornarGrupoADDA127TitComplexType()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     */
    @Test
    public final void testProcessarGrupoBoleto2() throws ComumException {
        BoletoDDA boleto = retornarBoletoDDA();
        boleto.setId(null);
        when(boletoCipDao.obterBoletoDDALock(anyLong())).thenReturn(boleto);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarGrupoBoleto", retornarGrupoDDA0127TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testProcessarMensagemADDA127() {
        BoletoDDA boleto = retornarBoletoDDA();
        boleto.setId(null);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagemADDA127", retornarGrupoADDA127TitComplexType(), boleto));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testAlterarBoletoDDA() {
        GrupoADDA127TitComplexType grupo = retornarGrupoADDA127TitComplexType();
        grupo.setDtVencTit(null);
        grupo.setNumRefAtlCadTit(null);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "alterarBoletoDDA", retornarBoletoDDA(), grupo));
    }

}
