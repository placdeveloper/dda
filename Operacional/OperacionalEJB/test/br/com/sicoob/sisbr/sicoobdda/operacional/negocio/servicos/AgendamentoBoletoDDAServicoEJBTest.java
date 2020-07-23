/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         AgendamentoBoletoDDAServicoEJBTest.java
 * Data Criação:    Jan 10, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

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
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AgendamentoBoletoDDAServicoEJB;

/**
 * AgendamentoBoletoDDAServicoEJBTest
 * 
 * @author samuell.ramos
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendamentoBoletoDDAServicoEJBTest extends Mockito {

    @InjectMocks
    private AgendamentoBoletoDDAServicoEJB ejb;

    @Mock
    private ParametroDao parametroDAO;

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
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AgendamentoBoletoDDAServicoEJB#obterParametrosAgendamento(int)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterParametrosAgendamento() throws ComumException {
        when(parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.INTEGER_UM)).thenReturn(Boolean.TRUE);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterParametrosAgendamentoServico(Constantes.INTEGER_UM));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AgendamentoBoletoDDAServicoEJB#obterParametrosAgendamento(int)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterParametrosAgendamentoException() throws ComumException {
        when(parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.INTEGER_UM)).thenReturn(Boolean.TRUE);
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterParametrosAgendamentoServico(Constantes.INTEGER_ZERO));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AgendamentoBoletoDDAServicoEJB#obterParametrosAgendamento(int)}.
     * 
     * @throws ComumException
     */
    public String testObterParametrosAgendamentoServico(int parametro) {
        try {
            ejb.obterParametrosAgendamento(parametro);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

}
