/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemCadastrarBeneficiarioServicoTest.java
 * Data Criação:    Jan 24, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemCadastrarBeneficiarioServicoTest é responsável por
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemCadastrarBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemCadastrarBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        definirParametroDao(Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.processarMensagem(Constantes.LONG_UM)));
        definirParametroDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.processarMensagem(Constantes.LONG_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws Exception {
        whenBeneficiarioCipDao(Boolean.TRUE);
        whenSCIDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0501R1ComplexType())));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    // PRIVATE METHODS =====================================================================
    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Ignore
    @Test
    public final void testRetornoBeneficiarioInclusaoFluxo() throws Exception {
        whenSCIDelegate(Boolean.TRUE);
        BeneficiarioDDA beneficiarioDDA = retornarBeneficiarioDDA();
        beneficiarioDDA.setId(Constantes.LONG_UM);
        when(beneficiarioCipDao.obterBeneficiario(anyString())).thenReturn(beneficiarioDDA);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "retornoBeneficiarioInclusaoFluxo", retornarDDA0501R1ComplexType(),
                retornarMensagemDDABeneficiario(), Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM)));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public final void testSetarConveniosBeneficiarioException() throws ComumException {
        assertEquals(Constantes.TESTE_FALHA, setarConveniosBeneficiario());
    }

    // AUXILIARS ===========================================================================
    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String setarConveniosBeneficiario() throws ComumException {
        String retorno = null;
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(new InstituicaoDto());
        try {
            Whitebox.invokeMethod(ejb, "setarConveniosBeneficiario", retornarBeneficiarioDDA(), retornarMensagemDDABeneficiarioConvenio());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }
}
