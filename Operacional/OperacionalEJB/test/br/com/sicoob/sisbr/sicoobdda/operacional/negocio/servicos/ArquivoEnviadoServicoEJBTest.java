/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ArquivoEnviadoServicoEJBTest.java
 * Data Criação:    Jan 10, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoEnviadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoEnviadoDaoImpl;

/**
 * ArquivoEnviadoServicoEJBTest
 * 
 * @author samuell.ramos
 */
@RunWith(MockitoJUnitRunner.class)
public class ArquivoEnviadoServicoEJBTest extends Mockito {

    @InjectMocks
    private ArquivoEnviadoServicoEJB ejb;
    @Mock
    private ArquivoEnviadoDaoImpl dao;

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoEnviadoServicoEJB#carregarListaTipoMensagem()}.
     */
    @Test
    public final void testCarregarListaTipoMensagem() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testCarregarListaTipoMensagemEJB());
    }

    public String testCarregarListaTipoMensagemEJB() {
        try {
            ejb.carregarListaTipoMensagem();
            return Constantes.TESTE_SUCESSO;
        } catch (ComumException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoEnviadoServicoEJB#obterArquivoEnviado(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterArquivoEnviado() throws ComumException {
        when(dao.obterEnvioArquivoDDA(anyLong())).thenReturn(new LogEnvioArquivoDDA());
        List<TipoMensagemDDA> listaTipoMensagemDDA = new ArrayList<TipoMensagemDDA>();
        when(dao.listarTipoMensagemAgenAdda()).thenReturn(listaTipoMensagemDDA);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterArquivoEnviadoEJB());
    }

    public String testObterArquivoEnviadoEJB() {
        try {
            ejb.obterArquivoEnviado(Constantes.LONG_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoEnviadoServicoEJB#alterarArquivo(br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA)}.
     */
    @Test
    public final void testAlterarArquivo() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testAlterarArquivoEJB());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ArquivoEnviadoServicoEJB#alterarArquivo(br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA)}
     * .
     */
    public String testAlterarArquivoEJB() {
        try {
            ejb.alterarArquivo(new LogEnvioArquivoDDA());
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

}
