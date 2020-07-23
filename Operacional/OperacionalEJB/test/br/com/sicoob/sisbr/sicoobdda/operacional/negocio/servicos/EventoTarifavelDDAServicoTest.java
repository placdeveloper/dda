/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         EventoTarifavelDDAServicoTest.java
 * Data Criação:    Jan 5, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.StatusEventoTarifavelEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao;

/**
 * EventoTarifavelDDAServicoTest
 * 
 * @author samuell.ramos
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class, SCIDelegate.class, IntegracaoInternaFabricaDelegates.class })
public class EventoTarifavelDDAServicoTest extends Mockito {

    @InjectMocks
    private EventoTarifavelDDAServicoEJB ejb;

    @Mock
    private EventoTarifavelDDADao dao;

    @Mock
    private ParametroDao parametroDao;
    @Mock
    private  SCIDelegate sciDelegate;
    @Mock
    protected IntegracaoInternaFabricaDelegates integracaoInternaFabricaDelegates;
    @Mock
    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws MensagemDesconhecidaException {
        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
        
        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(integracaoInternaFabricaDelegates);
        when(integracaoInternaFabricaDelegates.getSCIDelegate()).thenReturn(sciDelegate);
        
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#listaEventoTarifavelDDA()}.
     */
    @Test
    public final void testListaEventoTarifavelDDA() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testListarEventoTarifavelDDAEJB());
    }

    /**
     * @return
     */
    public String testListarEventoTarifavelDDAEJB() {
        try {
            ejb.listarEventoTarifavelDDA();
            return Constantes.TESTE_SUCESSO;
        } catch (ComumException e) {
            return Constantes.TESTE_FALHA;
        }
    }


    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#pesquisaEventoTarifavelDDA(java.lang.Integer, java.lang.Integer)}.
     */
    @Test
    public final void testPesquisaEventoTarifavelDDA() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testPesquisaEventoTarifavelDDAEJB());
    }

    /**
     * @return
     */
    public String testPesquisaEventoTarifavelDDAEJB() {
        try {
            ejb.pesquisaEventoTarifavelDDA(anyInt(), anyInt());
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#obterEventoTarifavelDDATarifa(java.lang.Long)}.
     */
    @Test
    public final void testObterEventoTarifavelDDATarifa() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterEventoTarifavelDDATarifaEJB());
    }

    public String testObterEventoTarifavelDDATarifaEJB() {
        try {
            when(dao.obterEventoTarifavelDDATarifa(Constantes.LONG_UM)).thenReturn(new EventoTarifavelDto());
            ParametroDDA parametroDDA = new ParametroDDA();
            parametroDDA.setValorParametro(Constantes.STRING_NUMERO_1);
            when(parametroDao.obterParametro(ParametroDDA.DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP, null)).thenReturn(parametroDDA);
            parametroDao.obterParametro(ParametroDDA.DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP, null);

            ejb.obterEventoTarifavelDDATarifa(Constantes.LONG_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testManterEventoTarifavelIncluirProgramado() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto dtoProgramado = montaEventoTarifavelDtoProgramado();
        dtoVigente.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/12/" + (DateUtil.obterAnoAtual() - 1), "dd/MM/yyyy").getTime()));
        dtoProgramado.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/01/" + (DateUtil.obterAnoAtual() + 1), "dd/MM/yyyy").getTime()));
        dtoProgramado.setValorCIP(BigDecimal.TEN);
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testManterEventoTarifavelEJB(dtoVigente, dtoProgramado));
    }
    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelEditarProgramado() throws BancoobException {
         EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto dtoProgramado = montaEventoTarifavelDtoProgramado();

        dtoVigente.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/12/" + (DateUtil.obterAnoAtual() - 1), "dd/MM/yyyy").getTime()));
        dtoProgramado.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/01/" + (DateUtil.obterAnoAtual() + 1), "dd/MM/yyyy").getTime()));

        dtoProgramado.setValorCIP(BigDecimal.TEN);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeVigente(anyInt())).thenReturn(Boolean.TRUE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testManterEventoTarifavelEJB(dtoProgramado, dtoProgramado));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelEditarProgramadoDataInferiorAtual() throws ComumException {
        EventoTarifavelDto dtoDB2 = montaEventoTarifavelDtoProgramado();
        EventoTarifavelDto dtoTela = montaEventoTarifavelDtoProgramado();
        dtoTela.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("25/01/" + (DateUtil.obterAnoAtual() - 2), "dd/MM/yyyy").getTime()));
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        Assert.assertEquals(Constantes.TESTE_FALHA, testManterEventoTarifavelEJB(dtoDB2, dtoTela));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelIncluirProgramadoJaExiste() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        dtoVigente.setDataFimVigencia(new DateTimeDB(DateUtil.converterStringToDate("25/01/" + DateUtil.obterAnoAtual(), "dd/MM/yyyy").getTime()));
        EventoTarifavelDto dtoProgramado = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        Assert.assertEquals(Constantes.TESTE_FALHA, testManterEventoTarifavelEJB(dtoVigente, dtoProgramado));
    }
    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelIncluirProgramadoDataInferiorAtual() throws ComumException {
        EventoTarifavelDto dtoDB2 = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto dtoTela = montaEventoTarifavelDtoVigente();
        dtoTela.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("25/01/" + (DateUtil.obterAnoAtual() - 2), "dd/MM/yyyy").getTime()));
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        Assert.assertEquals(Constantes.TESTE_FALHA, testManterEventoTarifavelEJB(dtoDB2, dtoTela));
    }
    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelEditar() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto dtoProgramado = montaEventoTarifavelDtoProgramado();
        dtoProgramado.setValorCIP(BigDecimal.TEN);
        dtoProgramado.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/12/" + (DateUtil.obterAnoAtual() - 1), "dd/MM/yyyy").getTime()));
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testManterEventoTarifavelEJB(dtoVigente, dtoProgramado));
    }
    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#manterEventoTarifavel(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Test
    public final void testManterEventoTarifavelEditarExisteRateio() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        registroDaTela.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/12/" + (DateUtil.obterAnoAtual() - 1), "dd/MM/yyyy").getTime()));
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.TRUE);

        Assert.assertEquals(Constantes.TESTE_FALHA, testManterEventoTarifavelEJB(dtoVigente, registroDaTela));
    }

    /**
     * @param dtoDB2
     * @param dtoTela
     * @param existeProgramada
     * @param existeRateio
     * @return
     */
    public String testManterEventoTarifavelEJB(EventoTarifavelDto dtoDB2, EventoTarifavelDto dtoTela) {
        try {
            when(dao.obter(anyLong())).thenReturn(new EventoTarifavelDDATarifa());
            when(dao.obterEventoTarifavelDDATarifa(anyLong())).thenReturn(dtoDB2);

            ParametroDDA parametroDDA = new ParametroDDA();
            parametroDDA.setValorParametro(Constantes.STRING_NUMERO_1);
            when(parametroDao.obterParametro(ParametroDDA.DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP, null)).thenReturn(parametroDDA);
            parametroDao.obterParametro(ParametroDDA.DIA_VENCIMENTO_FATURA_EVENTO_TARIFAVEL_CIP, null);

            ejb.manterEventoTarifavel(dtoTela);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        } 
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testExcluirEventoTarifavelDDA() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testExcluirEventoTarifavelDDAEJB(dtoVigente, registroDaTela));
    }
    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}.
     */
    @Test
    public final void testExcluirEventoTarifavelDDAVigente() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testExcluirEventoTarifavelDDAEJB(dtoVigente, registroDaTela));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     */
    @Test
    public final void testExcluirEventoTarifavelDDAUnicoVigenteException() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(null);
        Assert.assertEquals(Constantes.TESTE_FALHA, testExcluirEventoTarifavelDDAEJB(dtoVigente, registroDaTela));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}.
     */
    @Test
    public final void testExcluirEventoTarifavelDDAUnicoProgramadoException() throws ComumException {
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(null);
        Assert.assertEquals(Constantes.TESTE_FALHA, testExcluirEventoTarifavelDDAEJB(registroDaTela, registroDaTela));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}
     * .
     */
    @Test
    public final void testExcluirEventoTarifavelDDAProgramado() throws ComumException {
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(registroDaTela);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testExcluirEventoTarifavelDDAEJB(registroDaTela, registroDaTela));
    }
    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}.
     */
    @Test
    public final void testExcluirEventoTarifavelDDAVigentePossuiRateio() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.FALSE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.TRUE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_FALHA, testExcluirEventoTarifavelDDAEJB(dtoVigente, registroDaTela));
    }
    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.EventoTarifavelDDAServicoEJB#excluirEventoTarifavelDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto)}.
     */
    @Test
    public final void testExcluirEventoTarifavelDDAVigenteExistePorgamacao() throws ComumException {
        EventoTarifavelDto dtoVigente = montaEventoTarifavelDtoVigente();
        EventoTarifavelDto registroDaTela = montaEventoTarifavelDtoProgramado();
        when(dao.existeProgramada(anyInt())).thenReturn(Boolean.TRUE);
        when(dao.existeRateio(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        when(dao.obterUltimoStatusEventoTarifavel(anyInt(), anyInt())).thenReturn(dtoVigente);
        Assert.assertEquals(Constantes.TESTE_FALHA, testExcluirEventoTarifavelDDAEJB(dtoVigente, registroDaTela));
    }

    /**
     * @param dtoVigente
     * @param dtoProgramado
     * @return
     */
    public String testExcluirEventoTarifavelDDAEJB(EventoTarifavelDto dtoVigente, EventoTarifavelDto dtoProgramado) {
        try {
            when(dao.obter(anyLong())).thenReturn(new EventoTarifavelDDATarifa());

            ejb.excluirEventoTarifavelDDA(dtoVigente);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }


    public EventoTarifavelDto montaEventoTarifavelDtoVigente() {
        EventoTarifavelDto dto = new EventoTarifavelDto();
        dto.setStatus(StatusEventoTarifavelEnum.getDescricaoStatus(1));
        dto.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/12/" + (DateUtil.obterAnoAtual() - 1), "dd/MM/yyyy").getTime()));
        dto.setValorBancoob(BigDecimal.ONE);
        dto.setValorCIP(BigDecimal.ONE);
        return dto;
    }

    /**
     * @return
     */
    public EventoTarifavelDto montaEventoTarifavelDtoProgramado() {
        EventoTarifavelDto dto = new EventoTarifavelDto();
        dto.setStatus(StatusEventoTarifavelEnum.getDescricaoStatus(2));
        dto.setDataInicioVigencia(new DateTimeDB(DateUtil.converterStringToDate("26/01/" + DateUtil.obterAnoAtual(), "dd/MM/yyyy").getTime()));
        dto.setValorBancoob(BigDecimal.ONE);
        dto.setValorCIP(BigDecimal.ONE);
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return UsuarioBancoob
     * 
     */
    private UsuarioBancoob geraUsuarioBancoob() {
        UsuarioBancoob usuario = new UsuarioBancoob();
        usuario.setCooperativa(Constantes.STRING_NUMERO_0);
        usuario.setDominio(Constantes.STRING_NUMERO_0);
        usuario.setIdInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setIdUnidadeInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setLogin(Constantes.STRING_NUMERO_0);
        usuario.setPac(Constantes.STRING_NUMERO_0);
        return usuario;
    }

}
