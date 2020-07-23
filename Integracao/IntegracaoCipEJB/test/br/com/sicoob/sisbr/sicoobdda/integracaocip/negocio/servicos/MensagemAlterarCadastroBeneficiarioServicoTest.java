/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemAlterarCadastroBeneficiarioServicoTest.java
 * Data Criação:    Jan 23, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemAlterarCadastroBeneficiarioServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAlterarCadastroBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    MensagemAlterarCadastroBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#processarMensagem(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarMensagem() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(Boolean.TRUE));
        assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws Exception {
        whenBeneficiarioCipDao(Boolean.TRUE);
        whenSCIDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarRetornoMensagemDDA", retornarDDA0502R1ComplexType())));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    // PRIVATE METHODS ===========================================================
    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testAlterarBeneficiarioInstituicao() throws Exception {
        whenSCIDelegate(Boolean.TRUE);
        MensagemDDABeneficiarioConvenio mensagem = retornarMensagemDDABeneficiarioConvenio();
        mensagem.setCodTipoOperacao(TipoManutencaoEnum.EXCLUSAO.getCodIndicador());
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "alterarBeneficiarioInstituicao", retornarBeneficiarioDDA(), retornarMensagemDDABeneficiario(), mensagem)));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public final void testSetarConveniosBeneficiario() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, setarConveniosBeneficiario(Boolean.TRUE));
        assertEquals(Constantes.TESTE_FALHA, setarConveniosBeneficiario(Boolean.FALSE));
    }

    // AUXILIARS =================================================================
    /**
     * Método responsável por
     * 
     * @param ddaAtivo
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem(Boolean ddaAtivo) throws ComumException {
        when(parametroDao.obterValorBoolean(anyLong(), anyInt())).thenReturn(ddaAtivo);
        whenBeneficiarioCipDao(Boolean.TRUE);
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String setarConveniosBeneficiario(Boolean success) throws ComumException {
        String retorno = null;
        InstituicaoDto inst = new InstituicaoDto();
        inst.setIdInstituicao(Constantes.INTEGER_UM);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst).thenReturn(success ? inst : new InstituicaoDto());
        try {
            BeneficiarioDDA beneficiarioDDA = retornarBeneficiarioDDA();
            beneficiarioDDA.setListaBeneficiarioInstituicao(null);
            Whitebox.invokeMethod(ejb, "setarConveniosBeneficiario", beneficiarioDDA, retornarMensagemDDABeneficiario(), retornarMensagemDDABeneficiarioConvenio());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

}
