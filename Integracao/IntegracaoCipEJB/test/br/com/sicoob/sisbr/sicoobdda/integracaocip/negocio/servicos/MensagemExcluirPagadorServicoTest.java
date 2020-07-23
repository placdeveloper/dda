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
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemExcluirPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDAPagadorLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.PagadorDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006R1ComplexType;

/**
 * MensagemAlterarPagadorServicoTest é responsável por
 * 
 * @author George.Santos
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class })
public class MensagemExcluirPagadorServicoTest extends Mockito {

    @InjectMocks
    private MensagemExcluirPagadorServicoEJB ejb;

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
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
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
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDAPagadorAtualizaReferencias(Constantes.LONG_UM)).thenReturn(MensagemDDAPagadorLoader.gerar());
        when(dao.obterPagadorDDA(Constantes.CPF_AUX, Boolean.FALSE)).thenReturn(PagadorDDALoader.geraPagadorDDA());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagemRetorno() throws ComumException {
        when(dao.obterPagadorDDA(Constantes.LONG_UM)).thenReturn(PagadorDDALoader.geraPagadorDDA());
        ejb.processarRetornoMensagemDDA(geraDDA0006R1());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0005R1ComplexType
     * 
     */
    private DDA0006R1ComplexType geraDDA0006R1() {
        DDA0006R1ComplexType dda0006R1 = new DDA0006R1ComplexType();
        dda0006R1.setNumIdentcPagdr(BigInteger.TEN);
        dda0006R1.setNumRefAtlCadCliPagdr(BigInteger.TEN);
        dda0006R1.setNumSeqAtlzCadCliPagdr(BigInteger.TEN);
        dda0006R1.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        return dda0006R1;
    }
}
