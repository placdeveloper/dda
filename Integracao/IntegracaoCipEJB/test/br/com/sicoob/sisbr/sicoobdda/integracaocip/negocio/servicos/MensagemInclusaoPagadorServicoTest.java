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
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemInclusaoPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDAPagadorLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001R1ComplexType;

/**
 * MensagemInclusaoPagadorServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class })
public class MensagemInclusaoPagadorServicoTest extends Mockito {

    @InjectMocks
    private MensagemInclusaoPagadorServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private PagadorCipDao dao;

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
     * Método responsável por validar o teste do ProcessarMensagem
     * 
     * @throws ComumException
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
    }

    /**
     * Método responsável por validar o teste do ProcessarMensagem
     * 
     * @throws ComumException
     */
    @Test
    public void processarRetornoMensagemDDAPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA());
        verify(em, times(1)).persist(any(PagadorDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @return Object
     * @throws ComumException
     * 
     */
    private Object processarMensagem() throws ComumException {
        when(dao.obterMensagemDDAPagadorAtualizaReferencias(Constantes.LONG_UM)).thenReturn(MensagemDDAPagadorLoader.gerar());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return Object
     * @throws ComumException
     * 
     */
    private Object processarRetornoMensagemDDA() throws ComumException {
        when(dao.obterMensagemDDAPagador(Constantes.LONG_UM)).thenReturn(MensagemDDAPagadorLoader.gerar());
        when(dao.obterPagadorDDA(Constantes.CPF_AUX, Boolean.FALSE)).thenReturn(null);
        ejb.processarRetornoMensagemDDA(geraDDA0001R1());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0001R1ComplexType
     * 
     */
    private DDA0001R1ComplexType geraDDA0001R1() {
        DDA0001R1ComplexType dda0001R1 = new DDA0001R1ComplexType();
        dda0001R1.setNumIdentcPagdr(BigInteger.TEN);
        dda0001R1.setNumRefAtlCadCliPagdr(BigInteger.TEN);
        dda0001R1.setNumSeqAtlzCadCliPagdr(BigInteger.TEN);
        dda0001R1.setQtdAdesCliPagdrDDA(BigInteger.TEN);
        dda0001R1.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        return dda0001R1;
    }

}
