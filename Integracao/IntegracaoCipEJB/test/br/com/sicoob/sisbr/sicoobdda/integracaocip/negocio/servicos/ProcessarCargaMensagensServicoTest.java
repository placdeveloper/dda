/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarCargaMensagensServicoTest.java
 * Data Criação:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarCargaMensagensServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDABaixaEfetivaLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDABoletoLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDAPagadorLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.TipoMensagemDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;
import junit.framework.Assert;

/**
 * ProcessarCargaMensagensServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessarCargaMensagensServicoTest extends Mockito {

    private static final String PERSISTENCE_EXCEPTION = "br.com.bancoob.persistencia.excecao.PersistenciaException: " + Constantes.TESTE_FALHA;

    @InjectMocks
    private ProcessarCargaMensagensServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private IntegracaoCipLegadoDao dao;

    @Mock
    private SCIDelegate sciDelegate;

    @Mock
    private SessionContext sessionContext;

    @Mock
    private UserTransaction userTransaction;

    @Mock
    private ParametroDao parametroDAO;

    @Mock
    private MensagemDDADao mensagemDDADao;

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Before
    public void setUp() throws ComumException {
        when(sessionContext.getUserTransaction()).thenReturn(userTransaction);
        when(em.find(TipoMensagemDDA.class, Constantes.STRING_NUMERO_1)).thenReturn(TipoMensagemDDALoader.geraTipoMensagemDDA(Constantes.STRING_NUMERO_1));
        when(sciDelegate.obterInstituicaoPorCooperativaCache(Constantes.INTEGER_UM)).thenReturn(getInstituicaoDto());

        doThrow(gerarPersistenciaException()).when(dao).atualizarDataEventoDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
        doThrow(gerarPersistenciaException()).when(dao).atualizarDataEventoTituloDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
        doThrow(gerarPersistenciaException()).when(dao).atualizarDataMovimentoTituloDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void gerarCargaDadosLegadoInclusaoPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, gerarCargaDadosLegadoInclusao());
        verify(dao, times(1)).gerarCargaDadosLegadoInclusao(Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void gerarCargaDadosLegadoInclusaoException() {
        Assert.assertEquals(Constantes.TESTE_FALHA, gerarCargaDadosLegadoInclusao(gerarPersistenciaException()));
        verify(dao, times(1)).gerarCargaDadosLegadoInclusao(Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por void
     * 
     */
    public void processarCadastroMensagensPagadorPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroMensagensPagador());
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    public void processarCadastroMensagensPagadorException() throws ComumException {
        Assert.assertEquals(PERSISTENCE_EXCEPTION, processarCadastroMensagensPagador(gerarPersistenciaException()));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void processarCadastroMensagensBoletoPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroMensagensBoleto());
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    // @Test
    // public void processarCadastroMensagensBoletoPassouException() throws ComumException {
    // Assert.assertEquals(PERSISTENCE_EXCEPTION, processarCadastroMensagensBoleto(gerarPersistenciaException()));
    // verifyAtualizarDataMovimentoTitulo();
    // }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void processarCadastroMensagensBaixaEfetivaPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroMensagensBaixaEfetiva());
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     *         //
     */
    // @Test
    // public void processarCadastroMensagensBaixaEfetivaException() throws ComumException {
    // Assert.assertEquals(PERSISTENCE_EXCEPTION, processarCadastroMensagensBaixaEfetiva(gerarPersistenciaException()));
    // verifyAtualizarDataMovimentoTitulo();
    // }

    /**
     * @param numCooperativa
     * @param exception
     * @return String
     * 
     */
    private String gerarCargaDadosLegadoInclusao(PersistenciaException exception) {
        doThrow(exception).when(dao).gerarCargaDadosLegadoInclusao(Constantes.INTEGER_UM);
        return gerarCargaDadosLegadoInclusao();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String gerarCargaDadosLegadoInclusao() {
        try {
            ejb.gerarCargaDadosLegadoInclusao(Constantes.INTEGER_UM);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return String
     * 
     */
    private String processarCadastroMensagensPagador(PersistenciaException exception) {
        mockingEmFind(exception);
        return processarCadastroMensagensPagador();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String processarCadastroMensagensPagador() {
        try {
            when(dao.listarMensagensPagadorDDA(Constantes.INTEGER_UM, Constantes.DATE_AUX, Constantes.INTEGER_UM))
                    .thenReturn(MensagemDDAPagadorLoader.gerarLista())
                    .thenReturn(new ArrayList<MensagemDDAPagador>());
            when(parametroDAO.obterValorInteger(ParametroDDA.QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_TODOS, Constantes.ID_SICOOB)).thenReturn(10);
            ejb.processarCadastroMensagensPagador(Constantes.INTEGER_UM, Constantes.INTEGER_UM);
        } catch (ComumException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;

    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return String
     * 
     */
    private String processarCadastroMensagensBoleto(PersistenciaException exception) {
        mockingEmFind(exception);
        return processarCadastroMensagensBoleto();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String processarCadastroMensagensBoleto() {
        try {
            when(dao.listarMensagensBoleto(Constantes.INTEGER_UM, Constantes.DATE_AUX, Boolean.FALSE, Constantes.INTEGER_UM))
                    .thenReturn(MensagemDDABoletoLoader.gerarLista()).thenReturn(
                    new ArrayList<MensagemDDABoleto>());
            when(mensagemDDADao.obterDataReferencia(TipoMensagemDDA.DDA0101)).thenReturn(new Date());
            when(parametroDAO.obterValorInteger(ParametroDDA.QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_TODOS, Constantes.ID_SICOOB)).thenReturn(10);
            ejb.processarCadastroMensagensBoleto(Constantes.INTEGER_UM, Constantes.INTEGER_UM);
        } catch (ComumException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return String
     * 
     */
    private String processarCadastroMensagensBaixaEfetiva(PersistenciaException exception) {
        mockingEmFind(exception);
        return processarCadastroMensagensBaixaEfetiva();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String processarCadastroMensagensBaixaEfetiva() {
        try {
            when(dao.listarMensagensBaixaEfetivaDDA(Constantes.INTEGER_UM, Constantes.DATE_AUX, Constantes.INTEGER_UM))
                    .thenReturn(MensagemDDABaixaEfetivaLoader.gerarLista()).thenReturn(
                    new ArrayList<MensagemDDABaixaEfetiva>());
            when(parametroDAO.obterValorInteger(ParametroDDA.QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_TODOS, Constantes.ID_SICOOB)).thenReturn(10);
            when(mensagemDDADao.obterDataReferencia(TipoMensagemDDA.DDA0118)).thenReturn(new Date());
            ejb.processarCadastroMensagensBaixaEfetiva(Constantes.INTEGER_UM, Constantes.INTEGER_UM);
        } catch (ComumException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return InstituicaoDto
     * 
     */
    private InstituicaoDto getInstituicaoDto() {
        return new InstituicaoDto(Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por
     * 
     * @return PersistenciaException
     * 
     */
    private PersistenciaException gerarPersistenciaException() {
        return new PersistenciaException(Constantes.TESTE_FALHA);
    }

    /**
     * Método responsável por
     * 
     * @param exception void
     * 
     */
    private void mockingEmFind(PersistenciaException exception) {
        doThrow(exception).when(em).persist(any(IMensagemDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void verifyAtualizarDataEventoTitulo() throws ComumException {
        verify(dao, times(1)).atualizarDataEventoTituloDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void verifyAtualizarDataEvento() throws ComumException {
        verify(dao, times(1)).atualizarDataEventoDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void verifyAtualizarDataMovimentoTitulo() throws ComumException {
        verify(dao, times(1)).atualizarDataMovimentoTituloDDA(Constantes.LONG_UM, Constantes.INTEGER_UM);
    }
}
