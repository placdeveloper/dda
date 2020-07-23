package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemCadastrarBeneficiarioServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemCadastrarBeneficiarioServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemCadastrarBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
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
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemCadastrarBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        whenSCIDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0501R1ComplexType()));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @Test
    public final void testRetornoBeneficiarioInclusaoFluxo() throws ComumException {
        whenSCIDelegate(Boolean.TRUE);
        BeneficiarioDDA beneficiario = retornarBeneficiarioDDA();
        beneficiario.setId(Constantes.LONG_UM);
        when(beneficiarioCipDao.obterBeneficiario(anyString())).thenReturn(beneficiario);
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "retornoBeneficiarioInclusaoFluxo", retornarMensagemDDABeneficiario(), Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @SuppressWarnings("deprecation")
    @Test
    public final void testSetarConveniosBeneficiario() throws ComumException {
        InstituicaoDto dto = new InstituicaoDto();
        dto.setIdInstituicao(Constantes.INTEGER_ZERO);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(dto);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "setarConveniosBeneficiario", retornarBeneficiarioDDA(), retornarMensagemDDABeneficiarioConvenio()));
    }

}
