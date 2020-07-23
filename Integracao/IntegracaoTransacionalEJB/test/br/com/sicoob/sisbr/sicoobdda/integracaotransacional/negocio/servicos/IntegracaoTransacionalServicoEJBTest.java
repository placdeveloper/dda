/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos
 * Arquivo:         IntegracaoTransacionalServicoEJBTest.java
 * Data Criação:    Dec 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util.ServicosTestUtil;

/**
 * IntegracaoTransacionalServicoEJBTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class IntegracaoTransacionalServicoEJBTest extends ServicosTestUtil {

    private IntegracaoTransacionalServicoEJB ejb = Mockito.mock(IntegracaoTransacionalServicoEJB.class, Mockito.CALLS_REAL_METHODS);

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#contemParametro(br.com.bancoob.srtb.dto.Mensagem, java.lang.String)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testContemParametro() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "contemParametro", mensagem, Constantes.NOME_BANCO)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorInteger(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorInteger() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorLong(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorLong() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorBigInteger(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorBigInteger() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorBigDecimal(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorBigDecimal() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorShort(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorShort() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorDouble(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorDouble() {

    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testObterValorDateTimeDB() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorString(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorString() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorDate(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorDate() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorBoolean(br.com.bancoob.srtb.dto.Parametro)}
     * .
     */
    @Test
    public final void testObterValorBoolean() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterParametro(br.com.bancoob.srtb.dto.Mensagem, java.lang.String)}
     * .
     */
    @Test
    public final void testObterParametroMensagemString() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterParametro(br.com.bancoob.srtb.dto.Mensagem, java.lang.String, boolean)}
     * .
     */
    @Test
    public final void testObterParametroMensagemStringBoolean() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorDataHoraFormatada(java.util.Date)}
     * .
     */
    @Test
    public final void testObterValorDataHoraFormatada() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorDataFormatada(java.util.Date)}
     * .
     */
    @Test
    public final void testObterValorDataFormatada() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IntegracaoTransacionalServicoEJB#obterValorBigDecimalArredondado(java.math.BigDecimal)}
     * .
     */
    @Test
    public final void testObterValorBigDecimalArredondado() {

    }

}
