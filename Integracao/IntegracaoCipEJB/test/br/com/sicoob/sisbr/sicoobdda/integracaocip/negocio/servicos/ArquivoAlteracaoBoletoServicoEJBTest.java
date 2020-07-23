package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoAlteracaoBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102TitComplexType;

/**
 * ArquivoAlteracaoBoletoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoAlteracaoBoletoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoAlteracaoBoletoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoAlteracaoBoletoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoAlteracaoBoletoServicoEJB#processarRetornoMensagemDDA(long, long, long)}.
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoAlteracaoBoletoServicoEJB#obterSISARQ(java.lang.Long)}.
     */
    @Test
    public final void testObterSISARQ() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetADDA102() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getADDA102", listarMensagemDDABoleto()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBeneficiarioOriginal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBeneficiarioOriginal", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBeneficiarioFinal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBeneficiarioFinal", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetPagador() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setPagador", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetSacadorAvalista() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setSacadorAvalista", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetDocumentoTitulo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setDocumentoTitulo", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetInstrucaoPagamentoTitulo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setInstrucaoPagamentoTitulo", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetInstrucaoValorRecebimento() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setInstrucaoValorRecebimento", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoJuros() {
        MensagemDDABoleto mensagem = retornarMensagemDDABoleto();
        mensagem.setCodTipoJuros(Constantes.INTEGER_UM);
        mensagem.setDataJuros(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualJuros(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoJuros", mensagem, new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoMulta() {
        MensagemDDABoleto mensagem = new MensagemDDABoleto();
        mensagem.setCodTipoMulta(Constantes.INTEGER_UM);
        mensagem.setDataMulta(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualMulta(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoMulta", mensagem, new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoDesconto() {
        MensagemDDABoleto mensagem = new MensagemDDABoleto();
        mensagem.setDataDesconto1(Constantes.DATE_TIME_DB_AUX);
        mensagem.setDataDesconto2(Constantes.DATE_TIME_DB_AUX);
        mensagem.setDataDesconto3(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualDesconto1(Constantes.CEM);
        mensagem.setValorPercentualDesconto2(Constantes.CEM);
        mensagem.setValorPercentualDesconto3(Constantes.CEM);
        mensagem.setCodTipoDesconto1(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoDesconto", mensagem, new GrupoADDA102TitComplexType()));
        mensagem.setCodTipoDesconto2(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoDesconto", mensagem, new GrupoADDA102TitComplexType()));
        mensagem.setCodTipoDesconto3(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoDesconto", mensagem, new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoDesconto2() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoDesconto2", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoDesconto3() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoDesconto3", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoNotaFiscal() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoNotaFiscal", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testSetBoletoTextoInfo() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setBoletoTextoInfo", new MensagemDDABoleto(), new GrupoADDA102TitComplexType()));
    }

}
