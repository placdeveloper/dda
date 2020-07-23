package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ConsultaBoletoPagamentoCIPServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ConsultaBoletoPagamentoCIPServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ConsultaBoletoPagamentoCIPServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#getFilaConexao()}.
     */
    @Test
    public final void testGetConexao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getConexao"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#setFilaConexao(javax.jms.QueueConnection)}.
     */
    @Test
    public final void testSetConexao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setConexao", connection));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#getConnectionFactory()}.
     */
    @Test
    public final void testGetConnectionFactory() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getConnectionFactory"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#enviarConsultaBoleto(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Short)}.
     */
    @Test
    public final void testEnviarConsultaBoleto() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#aguardarRepostaCIP(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, int)}.
     * 
     * @throws JMSException
     * @throws BancoobException
     */
    @Test
    public final void testAguardarRepostaCIP() throws JMSException, BancoobException {
        whenConnection(Boolean.TRUE);
        whenSession(Boolean.TRUE);
        setarValorAtributoPrivado(ejb, "filaRecebimento", queue);
        whenConnectionFactory(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "aguardarRepostaCIP", retornarMensagemDDA(), Constantes.INTEGER_UM));
        whenConnectionFactory(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "aguardarRepostaCIP", retornarMensagemDDA(), Constantes.INTEGER_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#gravarMensagemRetornoSucessoDDA(java.lang.String, java.lang.String, br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Short)}.
     */
    @Test
    public final void testGravarMensagemRetornoSucessoDDA() {

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ConsultaBoletoPagamentoCIPServicoEJB#gravarMensagemRetornoErroDDA(java.lang.String, java.lang.String, br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, java.lang.Integer, java.lang.String, java.lang.Short)}.
     */
    @Test
    public final void testGravarMensagemRetornoErroDDA() {

    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testCreateSession() {
        this.testSetConexao();
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "createSession"));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetDeliveryMode() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getDeliveryMode"));
    }

    /**
     * Método responsável por void
     * 
     * @throws JMSException
     * @throws BancoobException
     */
    @Test
    public final void testEnviarMensagemCip() throws JMSException, BancoobException {
        this.testSetConexao();
        whenConnection(Boolean.TRUE);
        whenSession(Boolean.TRUE);
        setarValorAtributoPrivado(ejb, "filaEnvio", queue);
        whenConnectionFactory(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "enviarMensagemCip", retornarMensagemDDA()));
        whenConnectionFactory(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "enviarMensagemCip", retornarMensagemDDA()));
    }

}
