package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * MensagemAlterarCadastroBeneficiarioServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class MensagemAlterarCadastroBeneficiarioServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private MensagemAlterarCadastroBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#processarMensagem(java.lang.Long)}.
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
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() throws ComumException {
        whenBeneficiarioCipDao(Boolean.TRUE);
        whenSCIDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", retornarDDA0502R1ComplexType()));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @Test
    public final void testAlterarBeneficiarioInstituicao() throws ComumException {
        MensagemDDABeneficiarioConvenio msgBeneficiario = retornarMensagemDDABeneficiarioConvenio();
        whenSCIDelegate(Boolean.TRUE);
        msgBeneficiario.setCodTipoOperacao(TipoManutencaoEnum.EXCLUSAO.getCodIndicador());
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "alterarBeneficiarioInstituicao", retornarBeneficiarioDDA(), new MensagemDDABeneficiario(), msgBeneficiario));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @Test
    public final void testSetarConveniosBeneficiario() throws ComumException {
        whenSCIDelegate(Boolean.TRUE);
        BeneficiarioDDA beneficiario = new BeneficiarioDDA();
        beneficiario.setDataInicioRelacionamento(new DateTimeDB(DateUtil.somarDias(Constantes.DATE_AUX, Constantes.INTEGER_UM).getTime()));
        assertEquals(Constantes.TESTE_SUCESSO,
                genericTests(ejb, "setarConveniosBeneficiario", beneficiario, retornarMensagemDDABeneficiario(), retornarMensagemDDABeneficiarioConvenio()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     */
    @SuppressWarnings("deprecation")
    @Test
    public final void testSetarConveniosBeneficiarioComumException() throws ComumException {
        InstituicaoDto inst = new InstituicaoDto();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(retornarInstituicaoDto()).thenReturn(inst);
        BeneficiarioDDA beneficiario = new BeneficiarioDDA();
        beneficiario.setDataInicioRelacionamento(new DateTimeDB(DateUtil.somarDias(Constantes.DATE_AUX, Constantes.INTEGER_UM).getTime()));
        assertEquals(Constantes.TESTE_FALHA,
                genericTests(ejb, "setarConveniosBeneficiario", beneficiario, retornarMensagemDDABeneficiario(), retornarMensagemDDABeneficiarioConvenio()));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     */
    @SuppressWarnings("deprecation")
    @Test
    public final void testGetBeneficiarioInstituicao() throws ComumException {
        InstituicaoDto inst = new InstituicaoDto();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "getBeneficiarioInstituicao", Constantes.INTEGER_UM, new ArrayList<BeneficiarioInstituicao>()));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarCadastroBeneficiarioServicoEJB#getDao()}.
     */
    @Test
    public final void testGetDao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getDao"));
    }

}
