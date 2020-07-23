/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         OperacaoBeneficiarioDDAServicoTest.java
 * Data Criação:    Dec 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacaoBeneficiarioDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * OperacaoBeneficiarioDDAServicoTest é responsável por
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class OperacaoBeneficiarioDDAServicoTest extends ServicosTestUtil {

    @InjectMocks
    private OperacaoBeneficiarioDDAServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacaoBeneficiarioDDAServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacaoBeneficiarioDDAServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacaoBeneficiarioDDAServicoEJB#validaSituacaoBeneficiarioCIP(java.lang.String)}.
     */
    @Test
    public final void testValidaSituacaoBeneficiarioCIP() {
        assertEquals(Constantes.TESTE_FALHA, validaSituacaoBeneficiarioCIP(Constantes.STRING_ESPACO_EM_BRANCO, Boolean.FALSE));
        assertEquals(Constantes.TESTE_FALHA, validaSituacaoBeneficiarioCIP(Constantes.STRING_NUMERO_10, Boolean.FALSE));
        assertEquals(Constantes.TESTE_FALHA, validaSituacaoBeneficiarioCIP(Constantes.CPF_AUX, Boolean.TRUE));
        assertEquals(Constantes.TESTE_SUCESSO, validaSituacaoBeneficiarioCIP(Constantes.CPF_AUX, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacaoBeneficiarioDDAServicoEJB#beneficiarioEstaNaCip(java.lang.String)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testBeneficiarioEstaNaCip() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(ejb.beneficiarioEstaNaCip(anyString())));
    }

    // AUXILIARS ===================================================
    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @return String
     * 
     */
    private String validaSituacaoBeneficiarioCIP(String numCpfCnpj, Boolean notEmpty) {
        String retorno = null;
        try {
            SituacaoBeneficiarioDDA situacaoBeneficiarioDDA = recuperarSituacaoBeneficiarioDDA();
            situacaoBeneficiarioDDA.setCodSituacaoBeneficiario(notEmpty ? SituacaoBeneficiarioDDA.INAPTO : SituacaoBeneficiarioDDA.APTO);
            when(operacaoBeneficiarioDDADao.obterSituacaoBeneficiario(anyString())).thenReturn(situacaoBeneficiarioDDA);
            ejb.validaSituacaoBeneficiarioCIP(numCpfCnpj);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

}
