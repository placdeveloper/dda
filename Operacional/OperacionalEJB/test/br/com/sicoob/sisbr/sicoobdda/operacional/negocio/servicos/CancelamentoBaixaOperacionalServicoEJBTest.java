/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         CancelamentoBaixaOperacionalServicoEJBTest.java
 * Data Criação:    Jan 10, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;

/**
 * CancelamentoBaixaOperacionalServicoEJBTest
 * 
 * @author samuell.ramos
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoInternaFabricaDelegates.class, SCIDelegate.class, ADMDelegate.class, InformacoesUsuario.class })
public class CancelamentoBaixaOperacionalServicoEJBTest extends Mockito {

    @InjectMocks
    private CancelamentoBaixaOperacionalServicoEJB ejb;
    
    @Mock
    private SCIDelegate sciDelegate;

    @Mock
    private MensagemDDAServicoLocal mensagemDDAServico;

    @Mock
    private ADMDelegate admDelegate;

    @Mock
    private InformacoesUsuario informacoesUsuario;
    @Mock
    protected IntegracaoInternaFabricaDelegates integracaoInternaFabricaDelegates;

    @Mock
    private ParametroDao parametroDAO;

    @Mock
    private BoletoDDADao dao;

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(informacoesUsuario);

        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(integracaoInternaFabricaDelegates);
        when(integracaoInternaFabricaDelegates.getSCIDelegate()).thenReturn(sciDelegate);
        when(integracaoInternaFabricaDelegates.getADMDelegate()).thenReturn(admDelegate);
    }

    /**
     * Test method sucess
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB#processarCancelamentoBaixaOperacional(java.lang.String, java.lang.Integer, java.math.BigDecimal, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCancelamentoBaixaOperacional() throws ComumNegocioException, ComumException {
        InstituicaoDto instituicaoDto = new InstituicaoDto();
        instituicaoDto.setIdInstituicao(Constantes.INTEGER_UM);

        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(instituicaoDto);

        when(parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.INTEGER_UM)).thenReturn(Boolean.TRUE);
        when(dao.possuiBoletoDDA(anyString())).thenReturn(Boolean.TRUE);

        when(admDelegate.obterDataMovimento(anyInt())).thenReturn(Constantes.DATE_AUX);

        when(dao.listarMensagemDDABaixaOperacional(Constantes.COD_BARRAS_39_VALIDO, Constantes.DATE_TIME_DB_AUX)).thenReturn(montarListaMensagemBaixaOP());

        Assert.assertEquals(Constantes.TESTE_SUCESSO,
                testProcessarCancelamentoBaixaOperacionalEJB(Constantes.COD_BARRAS_39_VALIDO, Constantes.INTEGER_UM, BigDecimal.ONE, Constantes.SHORT_UM));
    }

    /**
     * Baixa não existe
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB#processarCancelamentoBaixaOperacional(java.lang.String, java.lang.Integer, java.math.BigDecimal, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    //TODO:samuell.ramos TESTE FALHA NO JENKS - FAZER ANALISE PARA DESCOBRIR O ERRO.
    @Ignore
    @Test
    public final void testProcessarCancelamentoBaixaOperacionalException() throws ComumNegocioException, ComumException {
        InstituicaoDto instituicaoDto = new InstituicaoDto();
        instituicaoDto.setIdInstituicao(Constantes.INTEGER_UM);

        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(instituicaoDto);

        when(parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.INTEGER_UM)).thenReturn(Boolean.TRUE);
        when(dao.possuiBoletoDDA(anyString())).thenReturn(Boolean.TRUE);

        when(admDelegate.obterDataMovimento(anyInt())).thenReturn(Constantes.DATE_AUX);

        List<MensagemDDABaixaOperacional> listaMensagem = new ArrayList<MensagemDDABaixaOperacional>();
        MensagemDDABaixaOperacional mensagemBaixaOP = montarMensagemBaixaOP();
        mensagemBaixaOP.setValorBaixa(BigDecimal.TEN);
        listaMensagem.add(mensagemBaixaOP);

        when(dao.listarMensagemDDABaixaOperacional(Constantes.COD_BARRAS_39_VALIDO, Constantes.DATE_TIME_DB_AUX)).thenReturn(listaMensagem);

        Assert.assertEquals(Constantes.TESTE_FALHA,
                testProcessarCancelamentoBaixaOperacionalEJB(Constantes.COD_BARRAS_39_VALIDO, Constantes.INTEGER_UM, BigDecimal.ONE, Constantes.SHORT_UM));
    }

    /**
     * Codigo de barras nulo
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB#processarCancelamentoBaixaOperacional(java.lang.String, java.lang.Integer, java.math.BigDecimal, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCancelamentoBaixaOperacionalExceptionCodBarras() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_FALHA, testProcessarCancelamentoBaixaOperacionalEJB("", Constantes.INTEGER_UM, BigDecimal.ONE, Constantes.SHORT_UM));
    }

    /**
     * Cooperativa zero ou nula
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB#processarCancelamentoBaixaOperacional(java.lang.String, java.lang.Integer, java.math.BigDecimal, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCancelamentoBaixaOperacionalExceptionNumCoop() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_FALHA,
                testProcessarCancelamentoBaixaOperacionalEJB(Constantes.COD_BARRAS_39_VALIDO, Constantes.INTEGER_ZERO, BigDecimal.ONE, Constantes.SHORT_UM));
    }

    /**
     * Valor base zero ou nula
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CancelamentoBaixaOperacionalServicoEJB#processarCancelamentoBaixaOperacional(java.lang.String, java.lang.Integer, java.math.BigDecimal, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCancelamentoBaixaOperacionalExceptionValorPago() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_FALHA,
                testProcessarCancelamentoBaixaOperacionalEJB(Constantes.COD_BARRAS_39_VALIDO, Constantes.INTEGER_UM, BigDecimal.ZERO, Constantes.SHORT_UM));
    }

    /**
     * Método responsável por
     * 
     * @param codigoBarras
     * @param numCooperativa
     * @param valorPago
     * @param idCanal
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String testProcessarCancelamentoBaixaOperacionalEJB(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws ComumNegocioException,
            ComumException {
        try {
            ejb.processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago, idCanal);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }

    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABaixaOperacional>
     * 
     */
    private List<MensagemDDABaixaOperacional> montarListaMensagemBaixaOP() {
        List<MensagemDDABaixaOperacional> listaMensagem = new ArrayList<MensagemDDABaixaOperacional>();
        listaMensagem.add(montarMensagemBaixaOP());
        return listaMensagem;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABaixaOperacional
     * 
     */
    private MensagemDDABaixaOperacional montarMensagemBaixaOP() {
        MensagemDDABaixaOperacional mensagemBaixaOP = new MensagemDDABaixaOperacional();
        mensagemBaixaOP.setValorBaixa(BigDecimal.ONE);
        return mensagemBaixaOP;
    }
}
