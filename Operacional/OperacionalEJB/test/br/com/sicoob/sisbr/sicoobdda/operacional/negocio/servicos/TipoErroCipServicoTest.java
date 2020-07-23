/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TipoErroCipServicoTest.java
 * Data Criação:    Sep 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.TipoErroCipServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao;

/**
 * TipoErroCipServicoTest é responsável por 
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoErroCipServicoTest extends Mockito {

    @InjectMocks
    private TipoErroCipServicoEJB ejb;

    @Mock
    private TipoErroCipDao dao;

    /**
     * Método responsável por
     * 
     * @return TipoErroCip
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void obterTipoErroPassou() throws OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoErro(Constantes.NOME_TESTE));
        verifyObterTipoErro(1);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void obterTipoErroCodTipoErroNull() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.codtipoerro.nao.informado", obterTipoErro(null));
        verifyObterTipoErro(0);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void obterTipoErroCodTipoErroVazio() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.codtipoerro.nao.informado", obterTipoErro(""));
        verifyObterTipoErro(0);
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCip
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirTipoErroPassou() throws OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirTipoErro());
        verifyObterTipoErro(1);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirTipoErroNull() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.incluir.tipoerro.null", incluirTipoErroTipoNull());
        verifyObterTipoErro(0);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirTipoErroDescNull() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.incluir.desctipoerro.invalida", incluirTipoErro(geraTipoErroDesc(null), geraTipoErro()));
        verifyObterTipoErro(0);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirTipoErroDescInvalido() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.incluir.desctipoerro.invalida", incluirTipoErro(geraTipoErroDesc(""), geraTipoErro()));
        verifyObterTipoErro(0);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirTipoErroExistente() throws OperacionalNoResultException {
        Assert.assertEquals("integracaocip.erro.incluir.tipoerro.existente", incluirTipoErro(geraTipoErro()));
        verifyObterTipoErro(1);
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCip
     * @throws BancoobException
     * 
     */
    @Test
    public void alterarTipoErroPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, alterarTipoErro());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void excluirTipoErroPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, excluirTipoErro(Boolean.FALSE));
        verifyExisteMensagemVinculoTipoErro();
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void excluirTipoErroMensagemVinculada() {
        Assert.assertEquals("integracaocip.erro.excluir.tipoerro.vinculado.mensagem.dda", excluirTipoErro(Boolean.TRUE));
        verifyExisteMensagemVinculoTipoErro();
    }


    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterTipoErro(String codTipoErro) {
        try {
            ejb.obterTipoErro(codTipoErro);
        } catch (OperacionalNegocionException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String incluirTipoErroTipoNull() {
        return incluirTipoErro(null, null);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String incluirTipoErro() {
        return incluirTipoErro(geraTipoErro(), null);
    }

    /**
     * Método responsável por
     * 
     * @param erroExistente
     * @return String
     * 
     */
    private String incluirTipoErro(TipoErroCip erroExistente) {
        return incluirTipoErro(geraTipoErro(), erroExistente);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String incluirTipoErro(TipoErroCip tipoErro, TipoErroCip tipoErroExistente) {
        try {
            if (ObjectUtil.isNull(tipoErroExistente)) {
                when(dao.obterTipoErro(Constantes.NOME_TESTE)).thenThrow(new OperacionalNoResultException("teste"));
            } else {
                when(dao.obterTipoErro(Constantes.NOME_TESTE)).thenReturn(tipoErroExistente);
            }
            ejb.incluirTipoErro(tipoErro);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param geraTipoErro
     * @return String
     * @throws BancoobException
     * 
     */
    private String alterarTipoErro() throws BancoobException {
        ejb.alterarTipoErro(geraTipoErro());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String excluirTipoErro(Boolean bolMensagemVinculada) {
        try {
            when(dao.existeMensagemVinculoTipoErro(Constantes.NOME_TESTE)).thenReturn(bolMensagemVinculada);
            ejb.excluirTipoErro(Constantes.NOME_TESTE);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param descTipoErro
     * @return TipoErroCip
     * 
     */
    private TipoErroCip geraTipoErroDesc(String descTipoErro) {
        return geraTipoErro(Constantes.NOME_TESTE, descTipoErro);
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCip
     * 
     */
    private TipoErroCip geraTipoErro() {
        return geraTipoErro(Constantes.NOME_TESTE, Constantes.NOME_TESTE);
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @param descTipoErro
     * 
     * @return TipoErroCip
     * 
     */
    private TipoErroCip geraTipoErro(String codTipoErro, String descTipoErro) {
        TipoErroCip tipoErro = new TipoErroCip();
        tipoErro.setCodTipoErro(codTipoErro);
        tipoErro.setDescTipoErro(descTipoErro);
        return tipoErro;
    }

    /**
     * Método responsável por
     * 
     * @throws OperacionalNoResultException void
     * 
     */
    private void verifyObterTipoErro(int vezes) throws OperacionalNoResultException {
        verify(dao, times(vezes)).obterTipoErro(Constantes.NOME_TESTE);
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyExisteMensagemVinculoTipoErro() {
        verify(dao, times(1)).existeMensagemVinculoTipoErro(Constantes.NOME_TESTE);
    }

}
