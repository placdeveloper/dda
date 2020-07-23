package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiarioCooperativaPK;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * IntegracaoFilaSSPBEnvioDDAServicoEJBTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class IntegracaoFilaSSPBEnvioDDAServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private IntegracaoFilaSSPBEnvioDDAServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#getFilaConexao()}.
     */
    @Test
    public final void testGetFilaConexao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getConexao"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#setFilaConexao(javax.jms.QueueConnection)}.
     */
    @Test
    public final void testSetFilaConexao() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "setConexao", connection));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#getConnectionFactory()}.
     */
    @Test
    public final void testGetConnectionFactory() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getConnectionFactory"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#getFilaConcorrencia()}.
     */
    @Test
    public final void testGetFilaConcorrencia() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getFilaConcorrencia"));
    }

    /**
     * Método responsável por  void
     */
    @Test
    public final void testCreateSession() {
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
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#enviar(javax.jms.Queue, java.io.Serializable)}.
     * 
     * @throws JMSException
     */
    @Test
    public final void testEnviarQueueSerializable() throws JMSException {
        whenConnectionFactory(Boolean.TRUE);
        whenSession(Boolean.TRUE);
        whenConnection(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "enviar", queue, new DDABeneficiarioCooperativaPK()));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#enviar(java.io.Serializable)}.
     * 
     * @throws JMSException
     */
    @Test
    public final void testEnviarSerializable() throws JMSException {
        whenConnectionFactory(Boolean.TRUE);
        whenSession(Boolean.TRUE);
        whenConnection(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "enviar", new DDABeneficiarioCooperativaPK()));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoFilaSSPBEnvioDDAServicoEJB#enviarFilaRecebimento(java.io.Serializable)}.
     * 
     * @throws JMSException
     * @throws BancoobException
     */
    @Test
    public final void testEnviarFilaRecebimento() throws JMSException, BancoobException {
        whenConnectionFactory(Boolean.TRUE);
        whenSession(Boolean.TRUE);
        whenConnection(Boolean.TRUE);
        setarValorAtributoPrivado(ejb, "filaRecebimento", queue);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "enviarFilaRecebimento", new DDABeneficiarioCooperativaPK()));
    }

}
