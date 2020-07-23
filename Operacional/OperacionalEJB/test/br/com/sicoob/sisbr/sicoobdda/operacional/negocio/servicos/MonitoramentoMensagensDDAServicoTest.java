/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         MonitoramentoMensagensDDAServicoTest.java
 * Data Criação:    Dec 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * MonitoramentoMensagensDDAServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MonitoramentoMensagensDDAServicoTest extends ServicosTestUtil {

    @InjectMocks
    private MonitoramentoMensagensDDAServicoEJB ejb;

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#listarTipoMensagens(java.lang.String)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testListarTipoMensagens() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.listarTipoMensagens(Constantes.STRING_NUMERO_1)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#recuperaMensagemDDA(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testRecuperaMensagemDDA() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.recuperaMensagemDDA(Constantes.LONG_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#recuperaMensagemOrigemDDA(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testRecuperaMensagemOrigemDDA() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.recuperaMensagemOrigemDDA(Constantes.LONG_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#reenviarMensagemCip(java.util.List, java.lang.Short)}
     * .
     */
    @Test
    public final void testReenviarMensagemCipListOfIntegerShort() {
        MensagemDDA dda = recuperarMensagemDDA();
        dda.getTipoMensagemDDA().setCodTipoMensagem(TipoMensagemDDA.DDA0503E);
        assertEquals(Constantes.TESTE_SUCESSO, reenviarMensagemCip(dda));
        dda.getTipoMensagemDDA().setCodTipoMensagem(TipoMensagemDDA.DDA0505E);
        assertEquals(Constantes.TESTE_SUCESSO, reenviarMensagemCip(dda));
        dda.getTipoMensagemDDA().setCodTipoMensagem(TipoMensagemDDA.DDA0001);
        assertEquals(Constantes.TESTE_FALHA, reenviarMensagemCip(dda));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#recuperaTipoErroCip()}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testRecuperaTipoErroCip() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.recuperaTipoErroCip()));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#recuperaRegistroErroCarga(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testRecuperaRegistroErroCarga() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, recuperaRegistroErroCarga(Boolean.TRUE));
        assertEquals(Constantes.TESTE_FALHA, recuperaRegistroErroCarga(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#recuperaParametrosReprocessamento(java.lang.Long)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testRecuperaParametrosReprocessamento() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, recuperaParametrosReprocessamento(Boolean.TRUE));
        assertEquals(Constantes.TESTE_SUCESSO, recuperaParametrosReprocessamento(Boolean.FALSE));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MonitoramentoMensagensDDAServicoEJB#getDAO()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDAO() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDAO")));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testGetBeneficiarioCipDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getBeneficiarioCipDao")));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testAtualizarTipoTratamentoMensagemRetornoCip() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "atualizarTipoTratamentoMensagemRetornoCip", Constantes.LONG_UM, Constantes.INTEGER_UM)));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testGetMensagemDDABeneficiario() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getMensagemDDABeneficiario", Constantes.LONG_UM)));
    }

    // AUXILIARS ===================================================
    /**
     * Método responsável por
     * 
     * @param dda
     * @return String
     * 
     */
    private String reenviarMensagemCip(MensagemDDA dda) {
        String retorno = null;
        try {
            whenBeneficiarioCIPDao(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
            when(em.find(MensagemDDA.class, Constantes.LONG_UM)).thenReturn(dda);
            ejb.reenviarMensagemCip(listarInteger(), Constantes.SHORT_UM);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return
     * @throws ComumException String
     * 
     */
    private String recuperaRegistroErroCarga(Boolean success) throws ComumException {
        String retorno = null;
        whenMonitoramentoMensagensDDADao(Boolean.TRUE);
        when(sciDelegate.obterInstituicaoCache(anyInt())).thenReturn(success ? recuperarInstituicaoDto() : null);
        try {
            ejb.recuperaRegistroErroCarga(Constantes.LONG_UM);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param ifElse
     * @return
     * @throws ComumException String
     * 
     */
    private String recuperaParametrosReprocessamento(Boolean ifElse) throws ComumException {
        ParametroDDA parametroDDA = new ParametroDDA();
        parametroDDA.setValorParametro(ifElse ? "1" : "1/2");
        ParametroDDA parametroDDA2 = new ParametroDDA();
        parametroDDA2.setValorParametro(Constantes.STRING_NUMERO_1);
        when(em.find(ParametroDDA.class, ParametroDDA.CONTINGENCIA_CARGA_BENEFICIARIOS_POR_MENSAGEM_EM_EXECUCAO)).thenReturn(parametroDDA);
        when(em.find(ParametroDDA.class, ParametroDDA.QTD_TOTAL_ERROS_CONTINGENCIA_POR_MENSAGEM_EM_EXECUCAO)).thenReturn(parametroDDA);
        when(em.find(ParametroDDA.class, ParametroDDA.QTD_MAXIMA_ERROS_CONTINGENCIA_POR_MENSAGEM)).thenReturn(parametroDDA2);
        return retornarString(ejb.recuperaParametrosReprocessamento(Constantes.LONG_UM));
    }

   
}
