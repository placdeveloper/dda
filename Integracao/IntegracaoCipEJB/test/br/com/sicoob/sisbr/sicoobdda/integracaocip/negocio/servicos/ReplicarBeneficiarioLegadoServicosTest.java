/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ReplicarBeneficiarioLegadoServicosTest.java
 * Data Criação:    Jan 25, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarBeneficiarioLegadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;

/**
 * ReplicarBeneficiarioLegadoServicosTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ReplicarBeneficiarioLegadoServicosTest extends ServicosTestUtil {

    @InjectMocks
    private ReplicarBeneficiarioLegadoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarBeneficiarioLegadoServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarBeneficiarioLegadoServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarBeneficiarioLegadoServicoEJB#replicarSituacaoBeneficiarioCIPLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testReplicarSituacaoBeneficiarioCIPLegado() throws BancoobException {
        assertEquals(Constantes.TESTE_SUCESSO, replicarSituacaoBeneficiarioCIPLegado(Boolean.TRUE, SituacaoBeneficiarioDDA.INAPTO));
        assertEquals(Constantes.TESTE_SUCESSO, replicarSituacaoBeneficiarioCIPLegado(Boolean.TRUE, SituacaoBeneficiarioDDA.APTO));
        assertEquals(Constantes.TESTE_FALHA, replicarSituacaoBeneficiarioCIPLegado(Boolean.FALSE, SituacaoBeneficiarioDDA.APTO));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ReplicarBeneficiarioLegadoServicoEJB#replicarSituacaoBeneficiarioFraudeLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)}
     * .
     */
    @Test
    public final void testReplicarSituacaoBeneficiarioFraudeLegado() throws Exception {
        whenReplicarBeneficiarioLegadoDao(Boolean.TRUE);
        whenCAPESDelegate(Boolean.TRUE);
        whenSCIDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "replicarSituacaoBeneficiarioFraudeLegado", definirCodSituacaoBeneficiario(SituacaoBeneficiarioDDA.APTO))));
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "replicarSituacaoBeneficiarioFraudeLegado", definirCodSituacaoBeneficiario(SituacaoBeneficiarioDDA.INAPTO))));
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "replicarSituacaoBeneficiarioFraudeLegado", definirCodSituacaoBeneficiario(SituacaoBeneficiarioDDA.EM_ANALISE))));
    }

    // PRIVATE METHODS ===================================================
    @Test
    public final void testObterBeneficiarioLegado() throws Exception {
        assertEquals(Constantes.TESTE_FALHA, obterBeneficiarioLegado());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public final void testExcluirBeneficiario() throws BancoobException {
        assertEquals(Constantes.TESTE_FALHA, excluirBeneficiario());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public final void testExcluirRegistroBeneficiarioLegado() throws BancoobException {
        assertEquals(Constantes.TESTE_FALHA, excluirRegistroBeneficiarioLegado());
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testIncluirAlterarBeneficiarioInapto() throws Exception {
        whenSCIDelegate(Boolean.TRUE);
        whenCAPESDelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "incluirAlterarBeneficiarioInapto", retornarBeneficiarioDDA(), null)));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public final void testIncluirRegsitroLegadoInapto() throws BancoobException {
        assertEquals(Constantes.TESTE_FALHA, incluirRegsitroLegadoInapto());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public final void testAlterarRegistroLegadoInapto() throws BancoobException {
        assertEquals(Constantes.TESTE_FALHA, alterarRegistroLegadoInapto());
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public final void testObtemListaCooperativa() throws ComumException {
        assertEquals(Constantes.TESTE_FALHA, obtemListaCooperativa());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testGetIdPessoaLegado() {
        assertEquals(Constantes.TESTE_FALHA, getIdPessoaLegado());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testGetNumCooperativa() {
        assertEquals(Constantes.TESTE_FALHA, getNumCooperativa());
    }

    /**
     * Método responsável por void
     * 
     * @throws Exception
     * 
     */
    @Test
    public final void testValidaCooperativaNaoListada() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "validaCooperativaNaoListada", listarDDABeneficiarioCooperativa(), Constantes.INTEGER_UM)));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testObtemListaOperacaoInapto() throws Exception {
        whenReplicarBeneficiarioLegadoDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "obtemListaOperacaoInapto", retornarBeneficiarioDDA(), retornarDDABeneficiario())));
    }

    @Test
    public final void testReplicarExclusaoBeneficiarioLegado() {
        assertEquals(Constantes.TESTE_SUCESSO, replicarExclusaoBeneficiarioLegado(Boolean.TRUE));
        assertEquals(Constantes.TESTE_FALHA, replicarExclusaoBeneficiarioLegado(Boolean.FALSE));
    }

    // AUXILIARS =========================================================
    /**
     * Método responsável por
     * 
     * @param param
     * @return String
     * @throws BancoobException
     * 
     */
    private String replicarSituacaoBeneficiarioCIPLegado(Boolean success, String param) throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(success);
        whenSCIDelegate(Boolean.TRUE);
        whenCAPESDelegate(Boolean.TRUE);
        try {
            Whitebox.invokeMethod(ejb, "replicarSituacaoBeneficiarioCIPLegado", definirCodSituacaoBeneficiario(param));
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoBeneficiario
     * @return BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA definirCodSituacaoBeneficiario(String codSituacaoBeneficiario) {
        BeneficiarioDDA beneficiarioDDA = retornarBeneficiarioDDA();
        beneficiarioDDA.getSituacaoBeneficiarioDDA().setCodSituacaoBeneficiario(codSituacaoBeneficiario);
        return beneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws BancoobException
     * 
     */
    private String obterBeneficiarioLegado() throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
        try {
            Whitebox.invokeMethod(ejb, "obterBeneficiarioLegado", new BeneficiarioDDA());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException String
     * 
     */
    private String excluirBeneficiario() throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
        try {
            Whitebox.invokeMethod(ejb, "excluirBeneficiario", new BeneficiarioDDA());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException String
     * 
     */
    private String excluirRegistroBeneficiarioLegado() throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
        try {
            Whitebox.invokeMethod(ejb, "excluirRegistroBeneficiarioLegado", new DDABeneficiario());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws BancoobException
     * 
     */
    private String incluirRegsitroLegadoInapto() throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
        whenSCIDelegate(Boolean.TRUE);
        whenCAPESDelegate(Boolean.TRUE);
        try {
            Whitebox.invokeMethod(ejb, "incluirRegsitroLegadoInapto", retornarBeneficiarioDDA());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException String
     * 
     */
    private String alterarRegistroLegadoInapto() throws BancoobException {
        String retorno = null;
        whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
        whenSCIDelegate(Boolean.TRUE);
        whenCAPESDelegate(Boolean.TRUE);
        try {
            Whitebox.invokeMethod(ejb, "alterarRegistroLegadoInapto", retornarBeneficiarioDDA(), retornarDDABeneficiario());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String obtemListaCooperativa() throws ComumException {
        String retorno = null;
        DDABeneficiario ddaBeneficiario = retornarDDABeneficiario();
        ddaBeneficiario.setListaDDABeneficiarioCooperativa(listarDDABeneficiarioCooperativa());
        whenSCIDelegate(Boolean.FALSE);
        try {
            Whitebox.invokeMethod(ejb, "obtemListaCooperativa", retornarBeneficiarioDDA(), ddaBeneficiario);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String getIdPessoaLegado() {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "getIdPessoaLegado", retornarBeneficiarioDDA(), retornarBeneficiarioInstituicao());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String getNumCooperativa() {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "getNumCooperativa", retornarBeneficiarioInstituicao());
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String replicarExclusaoBeneficiarioLegado(Boolean success) {
        String retorno = null;
        try {
            whenReplicarBeneficiarioLegadoDao(Boolean.TRUE);
            if (!success) {
                whenReplicarBeneficiarioLegadoDao(Boolean.FALSE);
            }
            Whitebox.invokeMethod(ejb, "replicarExclusaoBeneficiarioLegado", Constantes.LONG_UM);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoFalha(retorno);
    }

}
