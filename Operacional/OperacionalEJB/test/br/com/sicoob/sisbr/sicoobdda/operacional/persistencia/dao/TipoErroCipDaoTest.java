/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         TipoErroCipDaoTest.java
 * Data Criação:    Oct 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TipoErroCipDaoImpl;

/**
 * TipoErroCipDaoTest é responsável por 
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoErroCipDaoTest extends Mockito {

    @InjectMocks
    private TipoErroCipDaoImpl dao;

    @Mock
    private Comando comando;

    @Mock
    private Query query;

    /**
     * Método responsável por void
     * 
     */
    @Before
    public void setUp() {
        dao = new TipoErroCipDaoImpl() {

            /**
             * {@inheritDoc}
             * 
             * @see br.com.bancoob.persistencia.dao.BancoobDao#getComando(java.lang.String)
             */
            @Override
            protected Comando getComando(String identificacao) {
                return comando;
            }

        };
        when(comando.criarQuery(any(EntityManager.class))).thenReturn(query);
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterTipoErroPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterTipoErro());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterTipoErroNoResult() {
        Assert.assertEquals("integracaocip.tipo.erro.cip.nao.encontrado", obterTipoErro(Boolean.TRUE));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void existeMensagemVinculoTipoErroTrue() {
        Assert.assertTrue(existeMensagemVinculoTipoErro(Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void existeMensagemVinculoTipoErroFalse() {
        Assert.assertFalse(existeMensagemVinculoTipoErro(Constantes.LONG_ZERO));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterTipoErro() {
        return obterTipoErro(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param nomeTeste
     * @return String
     * @throws OperacionalNoResultException
     * 
     */
    private String obterTipoErro(Boolean bolNoResult) {
        try {
            if (bolNoResult) {
                when(query.getSingleResult()).thenThrow(new NoResultException());
            } else {
                when(query.getSingleResult()).thenReturn(new TipoErroCip());
            }
            dao.obterTipoErro(Constantes.NOME_TESTE);
        } catch (OperacionalNoResultException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param nomeTeste
     * @return String
     * 
     */
    private Boolean existeMensagemVinculoTipoErro(Long countMensagem) {
        when(query.getSingleResult()).thenReturn(countMensagem);
        return dao.existeMensagemVinculoTipoErro(Constantes.NOME_TESTE);
    }

}
