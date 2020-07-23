/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ArquivoManutencaoPagadorServicoTest.java
 * Data Criação:    Oct 25, 2016
 */
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.PagadorDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;

/**
 * ArquivoManutencaoPagadorServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class })
public class ArquivoManutencaoPagadorServicoTest extends Mockito {

    @InjectMocks
    private ArquivoManutencaoPagadorServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private PagadorCipDao dao;

    @Mock
    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaCip;

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
    public void processarRetornoMensagemDDAInclusaoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAIncluir());
        verifyPersistPagador();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAAlterarPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAAlterar());
        verifyMergePagador();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAAlterarPersist() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAAlterar(null));
        verifyPersistPagador();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAExclusaoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAExclusao());
        verifyMergePagador();
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem() throws ComumException {
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
    private String processarRetornoMensagemDDAIncluir() throws ComumException {
        return processarRetornoMensagemDDA(null, TipoManutencaoEnum.INCLUSAO);
    }

    /**
     * Método responsável por
     * 
     * @return Object
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDAAlterar() throws ComumException {
        return processarRetornoMensagemDDAAlterar(PagadorDDALoader.geraPagadorDDA());
    }

    /**
     * Método responsável por
     * 
     * @param pagador
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagemDDAAlterar(PagadorDDA pagador) throws ComumException {
        return processarRetornoMensagemDDA(pagador, TipoManutencaoEnum.ALTERAR);
    }

    /**
     * Método responsável por
     * 
     * @return Object
     * @throws ComumException
     * 
     */
    private Object processarRetornoMensagemDDAExclusao() throws ComumException {
        return processarRetornoMensagemDDA(PagadorDDALoader.geraPagadorDDA(), TipoManutencaoEnum.EXCLUSAO);
    }

    /**
     * Método responsável por
     * 
     * @param pagador
     * @param tipoManutencao
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagemDDA(PagadorDDA pagador, TipoManutencaoEnum tipoManutencao) throws ComumException {
        when(dao.obterPagadorDDA(Constantes.CNPJ_AUX, Boolean.FALSE)).thenReturn(pagador);
        ejb.processarRetornoMensagemDDA(geraADDA003(tipoManutencao));
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0001R1ComplexType
     * 
     */
    private GrupoADDA003PagdrDDAComplexType geraADDA003(TipoManutencaoEnum tipoManutencao) {
        GrupoADDA003PagdrDDAComplexType adda003 = new GrupoADDA003PagdrDDAComplexType();
        adda003.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        adda003.setTpPessoaPagdr(TipoPessoaEnum.PJ.getCodDominioCip());
        adda003.setQtdAdesCliPagdrDDA(BigInteger.TEN);
        adda003.setIndrManutPagdr(tipoManutencao.getCodIndicador());
        return adda003;
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyPersistPagador() {
        verify(em, times(1)).persist(any(PagadorDDA.class));
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyMergePagador() {
        verify(em, times(1)).merge(any(PagadorDDA.class));
    }

}
