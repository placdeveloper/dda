package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoBaixaEfetivaBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ArquivoBaixaEfetivaBoletoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoBaixaEfetivaBoletoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoBaixaEfetivaBoletoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoBaixaEfetivaBoletoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoBaixaEfetivaBoletoServicoEJB#obterSISARQ(java.lang.Long)}.
     */
    @Test
    public final void testObterSISARQ() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoBaixaEfetivaBoletoServicoEJB#processarArquivoRetornoBaixaEfetivaDDA(long, long, long)}.
     */
    @Test
    public final void testProcessarArquivoRetornoBaixaEfetivaDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarArquivoRetornoBaixaEfetivaDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void getADDA118() {
        MensagemDDABaixaEfetiva mensagem = retornarMensagemDDABaixaEfetiva();
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA118", gerarlistaMensagemDDABaixaEfetivaForTest(mensagem)));
        mensagem.setNumIdentificadorBaixaOper(null);
        mensagem.setCodTipoBaixaEfetiva(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA118", gerarlistaMensagemDDABaixaEfetivaForTest(mensagem)));
        mensagem.setCodTipoBaixaEfetiva(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA));
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA118", gerarlistaMensagemDDABaixaEfetivaForTest(mensagem)));
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @return List<MensagemDDABaixaEfetiva>
     */
    private List<MensagemDDABaixaEfetiva> gerarlistaMensagemDDABaixaEfetivaForTest(MensagemDDABaixaEfetiva mensagem) {
        List<MensagemDDABaixaEfetiva> ret = new ArrayList<MensagemDDABaixaEfetiva>();
        ret.add(mensagem);
        return ret;
    }

}
