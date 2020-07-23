/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ExcluirRelacionamentoBeneficiarioServicoTest.java
 * Data Criação:    Dec 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ExcluirRelacionamentoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ExcluirRelacionamentoBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * ExcluirRelacionamentoBeneficiarioServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ExcluirRelacionamentoBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    private ExcluirRelacionamentoBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ExcluirRelacionamentoBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ExcluirRelacionamentoBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ExcluirRelacionamentoBeneficiarioServicoEJB#excluirRelacionamentoBeneficiario(java.lang.String, java.lang.Long, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)}
     * .
     */
    @Test
    public final void testExcluirRelacionamentoBeneficiarioStringLongDateTimeDBShort() {
        assertEquals(Constantes.TESTE_FALHA, excluirRelacionamentoBeneficiario(null));
        assertEquals(Constantes.TESTE_SUCESSO, excluirRelacionamentoBeneficiario(Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidaDadosBeneficiario() {
        ExcluirRelacionamentoBeneficiarioDto dto = new ExcluirRelacionamentoBeneficiarioDto();
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(dto));
        dto.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(dto));
        dto.setCnpjCpfBeneficiario(Constantes.CPF_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(dto));
        dto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
        dto.setCnpjCpfBeneficiario(Constantes.CNPJ_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(dto));
    }

    // AUXILIARS ==========================================================
    /**
     * Método responsável por
     * 
     * @param numIdentBeneficiario
     * @return String
     * 
     */
    private String excluirRelacionamentoBeneficiario(Long numIdentBeneficiario) {
        String retorno = null;
        try {
            ejb.excluirRelacionamentoBeneficiario(Constantes.CPF_AUX, numIdentBeneficiario, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validaDadosBeneficiario(ExcluirRelacionamentoBeneficiarioDto dto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validaDadosBeneficiario", dto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

}
