/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ConsultarBeneficiarioServicoTest.java
 * Data Criação:    Dec 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ConsultarBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * ConsultarBeneficiarioServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ConsultarBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    private ConsultarBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ConsultarBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ConsultarBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ConsultarBeneficiarioServicoEJB#processarConsultaBeneficiarioPorCnpjCpf(br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum, java.lang.String)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarConsultaBeneficiarioPorCnpjCpf() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarConsultaBeneficiarioPorCnpjCpf", TipoPessoaEnum.PF, Constantes.CPF_AUX)));
    }

}
