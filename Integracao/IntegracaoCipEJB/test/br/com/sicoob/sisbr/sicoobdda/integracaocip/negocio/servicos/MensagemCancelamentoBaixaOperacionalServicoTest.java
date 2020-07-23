/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemCancelamentoBaixaOperacionalServicoTest.java
 * Data Criação:    Jan 24, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCancelamentoBaixaOperacionalServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemCancelamentoBaixaOperacionalServicoTest é responsável por
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemCancelamentoBaixaOperacionalServicoTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemCancelamentoBaixaOperacionalServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCancelamentoBaixaOperacionalServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCancelamentoBaixaOperacionalServicoEJB#processarMensagem(java.lang.Long)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        whenMensagemBaixaOperacionalDao(Boolean.TRUE);
        whenBoletoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.processarMensagem(Constantes.LONG_UM)));

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCancelamentoBaixaOperacionalServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws Exception {
        assertEquals(Constantes.TESTE_FALHA, processarRetornoMensagemDDA(Boolean.FALSE));
        assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA(Boolean.TRUE));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCancelamentoBaixaOperacionalServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    // PRIVATE METHODS =========================================================
    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testGetDDA0115ComplexType() throws Exception {
        MensagemDDABaixaOperacional baixaOperacional = retornarMensagemDDABaixaOperacional();
        baixaOperacional.setNumIdentificadorBaixaOper(null);
        whenBoletoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDDA0115ComplexType", baixaOperacional)));
    }

    // AUXILIARS ===============================================================
    /**
     * Método responsável por
     * 
     * @param sucess
     * @return String
     * 
     */
    private String processarRetornoMensagemDDA(Boolean sucess) {
        String retorno = null;
        try {
            if (sucess) {
                whenBoletoCipDao(Boolean.TRUE);
            }
            Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0115R2ComplexType());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }
}
