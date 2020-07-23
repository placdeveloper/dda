/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarContingenciaMensagemServicoTest.java
 * Data Criação:    Jan 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarContingenciaMensagemServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarContingenciaMensagemServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarRetornoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;

/**
 * ProcessarContingenciaMensagemServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessarContingenciaMensagemServicoTest extends Mockito {

    @InjectMocks
    private ProcessarContingenciaMensagemServicoEJB ejb;

    @SuppressWarnings("unused")
    @Mock
    private EntityManager em;

    @Mock
    private MensagemDDADao dao;

    @Mock
    private ProcessarRetornoDDAServicoLocal processarRetorno;

    @Mock
    private ProcessarContingenciaMensagemServicoLocal processarContingencia;

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void processarMensagemContingenciaListaPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemContingenciaLista(null));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void processarMensagemContingenciaListaException() {
        Assert.assertEquals("integracaocip.erro.processar.envio.msgs", processarMensagemContingenciaLista(new BancoobException(Constantes.TESTE_FALHA)));
    }

    /**
     * Método responsável por void
     * 
     * @throws BancoobException
     * 
     */
    @Test
    public void processarMensagemContingenciaPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemContingencia());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemContingenciaBatchListaPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemContingenciaBatchLista());
    }

    /**
     * Método responsável por
     * 
     * @param bancoobException
     * @return String
     * 
     */
    private String processarMensagemContingenciaLista(BancoobException bancoobException) {
        try {
            if (!ObjectUtil.isNull(bancoobException)) {
                doThrow(new BancoobException(Constantes.TESTE_FALHA)).when(processarContingencia).processarMensagemContingencia(Constantes.LONG_UM);
            }
            ejb.processarMensagemContingencia(geraListaId());
            verify(processarContingencia, times(1)).processarMensagemContingencia(Constantes.LONG_UM);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String processarMensagemContingencia() throws BancoobException {
        when(dao.obterMensagemErroLock(Constantes.LONG_UM)).thenReturn(MensagemDDALoader.geraMensagemDDA());
        ejb.processarMensagemContingencia(Constantes.LONG_UM);
        verify(processarRetorno, times(1)).processarMensagemRecebidaContingencia(any(MensagemDDA.class));
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws BancoobException
     * 
     */
    private String processarMensagemContingenciaBatchLista() throws BancoobException {
        ejb.processarMensagemContingenciaBatch(geraListaId(), Constantes.DATE_TIME_DB_AUX, Constantes.STRING_NUMERO_1);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return List<Long>
     * 
     */
    private List<Long> geraListaId() {
        List<Long> lista = new ArrayList<Long>();
        lista.add(Constantes.LONG_UM);
        return lista;
    }

}
