package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemAlterarSituacaoBeneficiarioServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAlterarSituacaoBeneficiarioServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAlterarSituacaoBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0505R1ComplexType()));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0506ComplexType()));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#getDao()}.
     */
    @Test
    public final void testGetDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @Test
    public final void testAlterarSituacaoClienteBeneficiarioDia() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "alterarSituacaoClienteBeneficiarioDia", retornarDDA0505R1ComplexType()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     */
    @Test
    public final void testProcessarDDAAvisaAlteracaoSituacaoBeneficiario() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDDAAvisaAlteracaoSituacaoBeneficiario", retornarDDA0506ComplexType()));
        whenBeneficiarioCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarDDAAvisaAlteracaoSituacaoBeneficiario", retornarDDA0506ComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testAlterarBeneficiario() {
        when(em.find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO)).thenReturn(retornarParametroDDA());
        MensagemDDABeneficiario mensagem = retornarMensagemDDABeneficiario();
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "alterarBeneficiario", mensagem, retornarBeneficiarioDDA()));
        mensagem.setCodSituacaoBeneficiario(SituacaoBeneficiarioDDA.APTO);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "alterarBeneficiario", mensagem, retornarBeneficiarioDDA()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testAlterarBeneficiario2() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "alterarBeneficiario", retornarDDA0506ComplexType(), retornarBeneficiarioDDA()));
    }

}
