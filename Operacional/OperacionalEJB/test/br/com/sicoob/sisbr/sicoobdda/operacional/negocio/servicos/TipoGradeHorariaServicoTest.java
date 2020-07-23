/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TipoGradeHorariaServicoTest.java
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
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.TipoGradeHorariaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;

/**
 * TipoGradeHorariaServicoTest
 * 
 * @author samuell.ramos
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoGradeHorariaServicoTest extends Mockito {

    @InjectMocks
    private TipoGradeHorariaServicoEJB ejb;

    @Mock
    private TipoGradeHorariaDao dao;

    @Mock
    private EntityManager em;

    @Test
    public void listarTipoGradeHorariaPorCodigoSubtipo() throws OperacionalException, OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarPorCodigoSubtipoEJB());
        verifyListarPorCodigoSubtipo(1);
    }

    @Test
    public void obterTipoGradeHoraria() throws ComumException, OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoGradeHorariaEJB(true));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoGradeHorariaEJB(false));
        verifyObterTipoGradeHoraria(2);
    }

    @Test
    public void removerTipoGradeHoraria() throws OperacionalNegocionException, ComumException {
        TipoGradeHorariaDto tipoGradeHoraria = geraTipoGradeHorariaDto();
        Assert.assertEquals(Constantes.TESTE_SUCESSO, removerTipoGradeHorariaEJB(tipoGradeHoraria, false));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, removerTipoGradeHorariaEJB(tipoGradeHoraria, true));
    }

    @Test
    public void listarTipoGradeHorariaSubtipoGrade() throws OperacionalNegocionException, ComumException {
        validaIncluirAlterarTipoGradeHoraria(2);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoGradeHorariaSubtipoGradeEJB());
    }

    private String listarTipoGradeHorariaSubtipoGradeEJB() throws ComumException {
        List<SubTipoGrade> listaSubTipoGrade = new ArrayList<SubTipoGrade>();
        listaSubTipoGrade.add(geraSubtipoGrade());
        listaSubTipoGrade.add(geraSubtipoGrade());

        List<TipoGradeHoraria> listaTipoGradeHoraria = new ArrayList<TipoGradeHoraria>();
        listaTipoGradeHoraria.add(geraTipoGradeHoraria());
        listaTipoGradeHoraria.add(geraTipoGradeHoraria());

        when(dao.listarSubTipoGrade()).thenReturn(listaSubTipoGrade);
        when(dao.listarTipoGradeHorariaDDA()).thenReturn(listaTipoGradeHoraria);

        ejb.listarTipoGradeHorariaSubtipoGrade();
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void isExisteTipoGradeHoraria() throws OperacionalNegocionException, ComumException {
        validaIncluirAlterarTipoGradeHoraria(2);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, isExisteTipoGradeHorariaEJB(0));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, isExisteTipoGradeHorariaEJB(1));
    }

    private String isExisteTipoGradeHorariaEJB(int quantidadeRetorno) throws ComumException {
        if (quantidadeRetorno > 0) {
            when(dao.obterCountTipoGradeHoraria(Constantes.NOME_TESTE)).thenReturn(0L);
        } else {
            when(dao.obterCountTipoGradeHoraria(Constantes.NOME_TESTE)).thenReturn(2L);
        }

        ejb.isExisteTipoGradeHoraria(Constantes.NOME_TESTE);
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void incluirGradeHoraria() throws OperacionalNegocionException, ComumException {
        validaIncluirAlterarTipoGradeHoraria(1);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirGradeHorariaEJB(Boolean.TRUE));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirGradeHorariaEJB(Boolean.FALSE));
    }

    @Test
    public void alterarGradeHoraria() throws OperacionalNegocionException, ComumException {
        validaIncluirAlterarTipoGradeHoraria(2);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, alterarGradeHorariaEJB(geraTipoGradeHorariaDto()));
    }

    private String alterarGradeHorariaEJB(TipoGradeHorariaDto tipoGradeHorariaDto) throws OperacionalNegocionException, OperacionalException {
        ejb.alterarTipoGradeHoraria(tipoGradeHorariaDto);
        return Constantes.TESTE_SUCESSO;
    }

    public void validaIncluirAlterarTipoGradeHoraria(int tipoOperacao) throws ComumException {
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 1, tipoOperacao));
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 2, tipoOperacao));
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 3, tipoOperacao));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 4, tipoOperacao));
        Assert.assertEquals("integracaocip.preenchimento.obrigatorio", validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 5, tipoOperacao));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaIncluirAlterarTipoGradeHorariaEJB(geraTipoGradeHorariaDto(), 6, tipoOperacao));
    }

    private String validaIncluirAlterarTipoGradeHorariaEJB(TipoGradeHorariaDto tipoGradeHorariaDto, int tipoException, int tipoOperacao) throws ComumException {
        try {
            switch (tipoException) {
            case 1:
                tipoGradeHorariaDto.setCodTipoGradeHoraria(null);
                break;
            case 2:
                tipoGradeHorariaDto.setDescTipoGradeHoraria(null);
                break;
            case 3:
                tipoGradeHorariaDto.setCodSubTipoGrade(null);
                break;
            case 4:
                tipoGradeHorariaDto.setCodSubTipoGrade((short) 1);
                tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);
                break;
            case 5:
                tipoGradeHorariaDto.setCodSubTipoGrade((short) 2);
                tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(null);
                break;
            case 6:
                tipoGradeHorariaDto.setCodSubTipoGrade((short) 2);
                tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);
                break;
            }
            if (tipoOperacao == 1) {
                ejb.incluirTipoGradeHoraria(tipoGradeHorariaDto);
            } else {
                ejb.alterarTipoGradeHoraria(tipoGradeHorariaDto);
            }

        } catch (OperacionalNegocionException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    private String removerTipoGradeHorariaEJB(TipoGradeHorariaDto tipoGradeHoraria, boolean erroAoRemover) throws ComumException, OperacionalNegocionException {
        when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
        when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);

        ejb.apagarTipoGradeHoraria(geraTipoGradeHoraria());
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void validaRemoverTipoGradeHoraria() throws OperacionalNegocionException, ComumException {
        Assert.assertEquals("integracaocip.tipomensagem.erro.existe.dependencia", validaRemoverTipoGradeHorariaEJB(0));
        Assert.assertEquals("integracaocip.tipomensagem.erro.existe.dependencia", validaRemoverTipoGradeHorariaEJB(1));
        Assert.assertEquals("integracaocip.tipomensagem.erro.existe.dependencia", validaRemoverTipoGradeHorariaEJB(2));
        Assert.assertEquals("integracaocip.tipomensagem.erro.existe.dependencia", validaRemoverTipoGradeHorariaEJB(3));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaRemoverTipoGradeHorariaEJB(4));
    }

    @SuppressWarnings("unchecked")
    private String validaRemoverTipoGradeHorariaEJB(int operacao) throws ComumException {
        String msgRetorno = Constantes.TESTE_SUCESSO;
        try {
            TipoGradeHoraria grade = geraTipoGradeHoraria();
            when(em.find((Class<TipoGradeHoraria>) any(), any())).thenReturn(grade);

            switch (operacao) {
            case 0:
                when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                when(dao.isExisteEmGradeOrigem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);

                break;
            case 1:
                when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeOrigem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                break;
            case 2:
                when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeOrigem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                break;
            case 3:
                when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeOrigem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.TRUE);
                break;
            case 4:
                when(dao.isExisteEmTipoMensagem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeOrigem(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                when(dao.isExisteEmGradeHoraria(Constantes.NOME_TESTE)).thenReturn(Boolean.FALSE);
                break;
            }
            ejb.apagarTipoGradeHoraria(grade);
        } catch (OperacionalNegocionException e) {
            msgRetorno = e.getMessage();
        }
        return msgRetorno;
    }

    private String incluirGradeHorariaEJB(boolean existeOrigemSubTipo) throws OperacionalException, OperacionalNegocionException {
        if (existeOrigemSubTipo) {
            ejb.incluirTipoGradeHoraria(geraTipoGradeHorariaDto());
        } else {
            ejb.incluirTipoGradeHoraria(geraTipoGradeHorariaDtoSemGradeSubTipo());
        }
        return Constantes.TESTE_SUCESSO;
    }

    private String listarPorCodigoSubtipoEJB() throws OperacionalException {
        ejb.listarTipoGradeHorariaPorCodigoSubtipo(Constantes.SHORT_UM);
        return Constantes.TESTE_SUCESSO;
    }

    private String obterTipoGradeHorariaEJB(boolean existeOrigemSubTipo) throws ComumException {
        if (existeOrigemSubTipo) {
            when(dao.obterTipoGradeHoraria(Constantes.NOME_TESTE)).thenReturn(geraTipoGradeHoraria());
        } else {
            when(dao.obterTipoGradeHoraria(Constantes.NOME_TESTE)).thenReturn(geraTipoGradeHorariaSemOrigemSubTipo());
        }
        ejb.obterTipoGradeHoraria(Constantes.NOME_TESTE);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @throws OperacionalNoResultException void
     * @throws OperacionalException
     */
    private void verifyListarPorCodigoSubtipo(int vezes) throws OperacionalNoResultException, OperacionalException {
        verify(dao, times(vezes)).listarTipoGradeHorariaPorCodigoSubtipo(Constantes.SHORT_UM);
    }

    /**
     * @throws OperacionalNoResultException void
     * @throws OperacionalException
     */
    private void verifyObterTipoGradeHoraria(int vezes) throws OperacionalNoResultException, ComumException {
        verify(dao, times(vezes)).obterTipoGradeHoraria(Constantes.NOME_TESTE);
        verify(dao, times(vezes)).listarTipoGradeHorariaDDA();
        verify(dao, times(vezes)).listarSubTipoGrade();
    }

    private TipoGradeHoraria geraTipoGradeHorariaSemOrigemSubTipo() {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setDescTipoGradeHoraria(Constantes.NOME_TESTE);

        tipoGradeHoraria.setSubTipoGrade(null);
        tipoGradeHoraria.setTipoGradeHorariaOrigem(null);

        return tipoGradeHoraria;
    }

    private TipoGradeHoraria geraTipoGradeHoraria() {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setDescTipoGradeHoraria(Constantes.NOME_TESTE);

        tipoGradeHoraria.setSubTipoGrade(geraSubtipoGrade());
        tipoGradeHoraria.setTipoGradeHorariaOrigem(geraTipoGradeHorariaOrigem());

        return tipoGradeHoraria;
    }

    private TipoGradeHoraria geraTipoGradeHorariaOrigem() {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setSubTipoGrade(geraSubtipoGrade());

        return tipoGradeHoraria;
    }

    private SubTipoGrade geraSubtipoGrade() {
        SubTipoGrade subTipoGrade = new SubTipoGrade();
        subTipoGrade.setCodSubTipoGrade(Constantes.SHORT_UM);
        subTipoGrade.setDescSubTipoGrade(Constantes.NOME_TESTE);

        return subTipoGrade;
    }

    private TipoGradeHorariaDto geraTipoGradeHorariaDto() {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setCodSubTipoGrade(Constantes.SHORT_UM);
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);

        return tipoGradeHorariaDto;
    }

    private TipoGradeHorariaDto geraTipoGradeHorariaDtoSemGradeSubTipo() {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setCodSubTipoGrade((short) 1);
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(null);

        return tipoGradeHorariaDto;
    }
}
