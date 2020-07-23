package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAlterarPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDAPagadorLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005R1ComplexType;

/**
 * MensagemAlterarPagadorServicoTest é responsável por
 * 
 * @author George.Santos
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class })
public class MensagemAlterarPagadorServicoTest extends Mockito {

    @InjectMocks
    private MensagemAlterarPagadorServicoEJB ejb;

    @Mock
    private PagadorCipDao dao;

    @Mock
    private EntityManager em;

    @Mock
    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaCip;

    /**
     * Método responsável por void
     * 
     */
    @Before
    public void setUp() {
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(fabricaCip);
        when(fabricaCip.getReplicarPagadorEletronicoLegadoDelegate()).thenReturn(replicarPagadorEletronicoLegadoDelegate);
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void processarMensagemRetornoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemRetorno());
        verify(em, times(1)).merge(any(PagadorDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @param idLogEnvioArquivoDDA
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDAPagadorAtualizaReferencias(Constantes.LONG_UM)).thenReturn(MensagemDDAPagadorLoader.gerar());
        when(dao.obterPagadorDDA(Constantes.CPF_AUX, Boolean.FALSE)).thenReturn(geraPagadorDDA());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarMensagemRetorno() throws ComumException {
        when(dao.obterMensagemDDAPagador(Constantes.LONG_UM)).thenReturn(MensagemDDAPagadorLoader.gerar());
        when(dao.obterPagadorDDA(Constantes.CPF_AUX, Boolean.FALSE)).thenReturn(geraPagadorDDA());
        ejb.processarRetornoMensagemDDA(geraDDA0005R1());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0005R1ComplexType
     * 
     */
    private DDA0005R1ComplexType geraDDA0005R1() {
        DDA0005R1ComplexType dDA0005R1ComplexType = new DDA0005R1ComplexType();
        dDA0005R1ComplexType.setNumIdentcPagdr(BigInteger.TEN);
        dDA0005R1ComplexType.setNumRefAtlCadCliPagdr(BigInteger.TEN);
        dDA0005R1ComplexType.setNumSeqAtlzCadCliPagdr(BigInteger.TEN);
        dDA0005R1ComplexType.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        return dDA0005R1ComplexType;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorDDA
     * 
     */
    private PagadorDDA geraPagadorDDA() {
        PagadorDDA pagadorDDA = new PagadorDDA();
        pagadorDDA.setNumIdentificaPagadorCip(Constantes.LONG_UM);
        pagadorDDA.setNumRefAtualCadPagador(Constantes.LONG_UM);
        return pagadorDDA;
    }

}
