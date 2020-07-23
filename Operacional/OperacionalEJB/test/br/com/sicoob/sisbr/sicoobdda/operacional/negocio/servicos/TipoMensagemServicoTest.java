/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TipoMensagemServicoTest.java
 * Data Criação:    Out 03, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.TipoMensagemServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao;

/**
 * TipoMensagemServicoTest
 * 
 * @author samuell.ramos
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoMensagemServicoTest extends Mockito {

    @InjectMocks
    private TipoMensagemServicoEJB ejb;

    @Mock
    private TipoMensagemDao dao;

    @Mock
    private EntityManager em;

    @Mock
    private TipoGradeHorariaDao tipoGradeHorariaDao;

    @Test
    public void carregarListasTipoMensagem() throws OperacionalNoResultException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterCarregarListasTipoMensagem());
        verifyCarregarListasTipoMensagens(1);
    }

    private String obterCarregarListasTipoMensagem() throws ComumException {
        ejb.carregarListasTipoMensagem();
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void incluirTipoMensagem() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirTipoMensagemEJB());
        verifyIncluirTipoMensagens(1);
    }

    private String incluirTipoMensagemEJB() throws ComumException, OperacionalNegocionException {
        when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
        ejb.incluirTipoMensagemDDA(geraTipoMensagem(), false);
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void alterarTipoMensagem() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, alterarTipoMensagemEJB(geraTipoMensagem()));
        verifyAlterarTipoMensagens(1);
    }

    private String alterarTipoMensagemEJB(TipoMensagemDDA tipoMensagemDDA) throws ComumException, OperacionalNegocionException {
        ejb.alterarTipoMensagemDDA(geraTipoMensagem());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @throws OperacionalNoResultException void
     * @throws OperacionalException
     */
    private void verifyCarregarListasTipoMensagens(int vezes) throws OperacionalNoResultException, ComumException {
        verify(dao, times(vezes)).listarTipoMensagemPorCategoria();
        verify(tipoGradeHorariaDao, times(vezes)).listarCodigosTipoGradeHoraria();
        verify(dao, times(vezes)).listarCategoriaMensagemDDA();
    }

    private void verifyIncluirTipoMensagens(int vezes) throws OperacionalNoResultException, ComumException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        verify(dao, times(vezes)).isExisteEmTipoMensagem(tipoMensagemDDA.getCodTipoMensagem());
    }

    private void verifyAlterarTipoMensagens(int vezes) throws OperacionalNoResultException, ComumException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        verify(dao, times(vezes)).isExisteEmTipoMensagem(tipoMensagemDDA.getCodTipoMensagem());
    }

    @Test
    public void validaJaCadastrado() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals("integracaocip.tipomensagem.jacadastrado", validaJaCadastradoEJB());
    }

    @Test
    public void validaNaoCadastrado() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaNaoCadastradoEJB());
    }

    @Test
    public void validaExigeRetorno() throws ComumException, OperacionalNegocionException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        tipoMensagemDDA.setBolExigeMensagemRetorno(null);
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaTipoMensagem(tipoMensagemDDA));
    }

    @Test
    public void validaCodTipoMensagem() throws ComumException, OperacionalNegocionException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        tipoMensagemDDA.setCodTipoMensagem(null);
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaTipoMensagem(tipoMensagemDDA));
    }

    @Test
    public void validaDescTipoMensagem() throws ComumException, OperacionalNegocionException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        tipoMensagemDDA.setDescTipoMensagem(null);
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaTipoMensagem(tipoMensagemDDA));
    }

    @Test
    public void validaNumPrioridade() throws ComumException, OperacionalNegocionException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        tipoMensagemDDA.setNumPrioridadeEnvio(null);
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaTipoMensagem(tipoMensagemDDA));
    }

    @Test
    public void validaCategoria() throws ComumException, OperacionalNegocionException {
        TipoMensagemDDA tipoMensagemDDA = geraTipoMensagem();
        tipoMensagemDDA.setCategoriaMensagemDDA(null);
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaTipoMensagem(tipoMensagemDDA));
    }

    @Test
    public void removerTipoMensagem() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, removerTipoMensagem(geraTipoMensagem()));
    }

    @Test
    public void validaRemoverTipoMensagem() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals("integracaocip.tipomensagem.erro.existe.vinculo", removerExisteDependencia(geraTipoMensagem()));
    }

    @Test
    public void listarTipoGradeHoraria() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoGradeHorariaEJB());
    }

    @Test
    public void listarCategoriaMensagemDDA() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarCategoriaMensagemDDAEJB());
    }

    @Test
    public void listarTipoMensagemDDA() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoMensagemDDAEJB());
    }

    @Test
    public void obterTipoMensagem() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoMensagemEJB(0));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoMensagemEJB(1));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoMensagemEJB(2));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoMensagemEJB(3));
    }

    private String obterTipoMensagemEJB(int operacao) throws ComumException {
        when(dao.listarTipoMensagemPorCategoria()).thenReturn(geraListaTipoMensagemDDA());
        when(tipoGradeHorariaDao.listarCodigosTipoGradeHoraria()).thenReturn(geraListaTipoGradeHoraria());
        when(dao.listarCategoriaMensagemDDA()).thenReturn(geraListaCategoriaMensagem());

        switch (operacao) {
        case 1:
            TipoMensagemDDA tipoMensagemDDA1 = geraTipoMensagem();
            tipoMensagemDDA1.setCodTipoArquivoCorrespondente(null);
            when(em.find((Class<TipoMensagemDDA>) any(), any())).thenReturn(tipoMensagemDDA1);
            break;
        case 2:
            TipoMensagemDDA tipoMensagemDDA2 = geraTipoMensagem();
            tipoMensagemDDA2.setTipoGradeHoraria(null);
            when(em.find((Class<TipoMensagemDDA>) any(), any())).thenReturn(tipoMensagemDDA2);
            break;
        case 3:
            TipoMensagemDDA tipoMensagemDDA3 = geraTipoMensagem();
            tipoMensagemDDA3.setCategoriaMensagemDDA(null);
            when(em.find((Class<TipoMensagemDDA>) any(), any())).thenReturn(tipoMensagemDDA3);
            break;

        default:
            when(em.find((Class<TipoMensagemDDA>) any(), any())).thenReturn(geraTipoMensagem());
            break;
        }

        ejb.obterTipoMensagem(geraTipoMensagem());
        return Constantes.TESTE_SUCESSO;
    }

    private String listarTipoMensagemDDAEJB() throws ComumException {
        when(dao.listarTipoMensagem()).thenReturn(geraListaTipoMensagemDDA());
        ejb.listarTipoMensagemDDA();
        return Constantes.TESTE_SUCESSO;
    }

    private String listarCategoriaMensagemDDAEJB() throws ComumException {
        when(dao.listarCategoriaMensagemDDA()).thenReturn(geraListaCategoriaMensagem());
        ejb.listarCategoriaMensagemDDA();
        return Constantes.TESTE_SUCESSO;
    }

    private String listarTipoGradeHorariaEJB() throws ComumException {
        when(tipoGradeHorariaDao.listarCodigosTipoGradeHoraria()).thenReturn(geraListaTipoGradeHoraria());
        ejb.listarTipoGradeHoraria();
        return Constantes.TESTE_SUCESSO;
    }

    private String removerTipoMensagem(TipoMensagemDDA tipoMensagemDDA) throws ComumException {
        when(dao.isVinculadoArqCorrespondente(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
        return removerTipoMensagemEJB(tipoMensagemDDA);
    }

    private String removerExisteDependencia(TipoMensagemDDA tipoMensagemDDA) throws ComumException {
        when(dao.isVinculadoArqCorrespondente(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
        return removerTipoMensagemEJB(tipoMensagemDDA);
    }

    private String removerTipoMensagemEJB(TipoMensagemDDA tipoMensagemDDA) throws ComumException {
        try {
            when(em.find((Class<TipoMensagemDDA>) any(), any())).thenReturn(tipoMensagemDDA);
            ejb.removerTipoMensagem(Constantes.NOME_TESTE);
        } catch (OperacionalNegocionException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    private String validaTipoMensagem(TipoMensagemDDA tipoMensagemDDA) throws OperacionalNegocionException, ComumException {
        try {
            ejb.incluirTipoMensagemDDA(tipoMensagemDDA, false);
        } catch (OperacionalNegocionException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    public String validaJaCadastradoEJB() throws ComumException, OperacionalNegocionException {
        when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
        return validaTipoMensagem(geraTipoMensagem());
    }

    public String validaNaoCadastradoEJB() throws ComumException, OperacionalNegocionException {
        when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
        ejb.incluirTipoMensagemDDA(geraTipoMensagem(), true);
        return validaTipoMensagem(geraTipoMensagem());
    }

    private TipoMensagemDDA geraTipoMensagem() {
        TipoMensagemDDA tipoMensagemDDA = new TipoMensagemDDA();
        tipoMensagemDDA.setCodTipoMensagem(Constantes.NOME_TESTE);
        tipoMensagemDDA.setDescTipoMensagem(Constantes.NOME_TESTE);
        tipoMensagemDDA.setNumPrioridadeEnvio(5);
        tipoMensagemDDA.setCodTipoArquivoCorrespondente(Constantes.NOME_TESTE);
        tipoMensagemDDA.setBolExigeMensagemRetorno(true);

        tipoMensagemDDA.setCategoriaMensagemDDA(geraCategoriaMensagem());
        tipoMensagemDDA.setTipoGradeHoraria(geraTipoGradeHoraria());
        return tipoMensagemDDA;
    }

    private CategoriaMensagemDDA geraCategoriaMensagem() {
        CategoriaMensagemDDA categoriaMensagemDDA = new CategoriaMensagemDDA();
        categoriaMensagemDDA.setCodCategoriaMensagemDda("A");
        categoriaMensagemDDA.setDescCategoriaMensagemDda("Teste");

        return categoriaMensagemDDA;
    }

    private TipoGradeHoraria geraTipoGradeHoraria() {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria("DDA01");
        tipoGradeHoraria.setDescTipoGradeHoraria("Teste");

        return tipoGradeHoraria;
    }

    private List<CategoriaMensagemDDA> geraListaCategoriaMensagem() {
        List<CategoriaMensagemDDA> listaCategoriaMensagem = new ArrayList<CategoriaMensagemDDA>();
        CategoriaMensagemDDA categoriaMensagemDDA = new CategoriaMensagemDDA();
        categoriaMensagemDDA.setCodCategoriaMensagemDda("A");
        categoriaMensagemDDA.setDescCategoriaMensagemDda("Teste");
        listaCategoriaMensagem.add(categoriaMensagemDDA);

        return listaCategoriaMensagem;
    }

    private List<TipoGradeHoraria> geraListaTipoGradeHoraria() {
        List<TipoGradeHoraria> listaTipoGradeHoraria = new ArrayList<TipoGradeHoraria>();
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();

        tipoGradeHoraria.setCodTipoGradeHoraria("DDA01");
        tipoGradeHoraria.setDescTipoGradeHoraria("Teste");
        listaTipoGradeHoraria.add(tipoGradeHoraria);

        return listaTipoGradeHoraria;
    }

    private List<TipoMensagemDDA> geraListaTipoMensagemDDA() {
        List<TipoMensagemDDA> listaTipoMensagemDDA = new ArrayList<TipoMensagemDDA>();

        TipoMensagemDDA tipoMensagemDDA = new TipoMensagemDDA();
        tipoMensagemDDA.setCodTipoMensagem(Constantes.NOME_TESTE);
        tipoMensagemDDA.setDescTipoMensagem(Constantes.NOME_TESTE);
        tipoMensagemDDA.setNumPrioridadeEnvio(5);
        tipoMensagemDDA.setBolExigeMensagemRetorno(true);

        tipoMensagemDDA.setCategoriaMensagemDDA(geraCategoriaMensagem());
        tipoMensagemDDA.setTipoGradeHoraria(geraTipoGradeHoraria());
        listaTipoMensagemDDA.add(tipoMensagemDDA);

        return listaTipoMensagemDDA;
    }
}
