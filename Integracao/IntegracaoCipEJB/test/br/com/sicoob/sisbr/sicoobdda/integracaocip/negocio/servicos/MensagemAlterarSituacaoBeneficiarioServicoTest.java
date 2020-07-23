/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemAlterarSituacaoBeneficiarioServicoTest.java
 * Data Criação:    Jan 23, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemAlterarSituacaoBeneficiarioServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAlterarSituacaoBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAlterarSituacaoBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarMensagem() throws Exception {
        whenBeneficiarioCipDao(Boolean.TRUE);
        definirParametroDaoObterValorBoolean(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarMensagem", Constantes.LONG_UM)));
        definirParametroDaoObterValorBoolean(Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarMensagem", Constantes.LONG_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws Exception {
        whenBeneficiarioCipDao(Boolean.TRUE);
        whenEm(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0505R1ComplexType())));
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0506ComplexType())));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarSituacaoBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    // PRIVATE METHODS ==========================================================
    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testProcessarDDAAvisaAlteracaoSituacaoBeneficiario() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarDDAAvisaAlteracaoSituacaoBeneficiario", retornarDDA0506ComplexType())));
    }

    /**
     * Método responsável por void
     * 
     * @throws Exception
     * 
     */
    @Test
    public final void testAlterarBeneficiario() throws Exception {
        MensagemDDABeneficiario mensagemBeneficiario = retornarMensagemDDABeneficiario();
        mensagemBeneficiario.setCodSituacaoBeneficiario(SituacaoBeneficiarioDDA.APTO);
        when(em.find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO)).thenReturn(retornarParametroDDA());
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "alterarBeneficiario", mensagemBeneficiario, retornarBeneficiarioDDA())));
    }

    // AUXILIARS ================================================================
    /**
     * Método responsável por
     * 
     * @param param
     * @throws ComumException void
     * 
     */
    private void definirParametroDaoObterValorBoolean(Boolean param) throws ComumException {
        when(parametroDao.obterValorBoolean(anyLong(), anyInt())).thenReturn(param);
    }

}
