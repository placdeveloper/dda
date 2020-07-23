/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         AdministrativoDaoImplTest.java
 * Data Criação:    Jan 10, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AdministrativoDaoImpl;

/**
 * AdministrativoDaoImplTest
 * 
 * @author samuell.ramos
 */
@RunWith(PowerMockRunner.class)
public class AdministrativoDaoImplTest extends Mockito {

    @Mock
    AdministrativoDaoImpl dao;
    /**
     * @throws java.lang.Exception void
     */

    @Mock
    private Comando comando;


    @Mock
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        dao = new AdministrativoDaoImpl(){
            /**
             * {@inheritDoc}
             * 
             * @see br.com.bancoob.persistencia.dao.BancoobDao#getComando(java.lang.String)
             */
            @Override
            protected Comando getComando(String identificacao) {
                return comando;
            }

            /**
             * {@inheritDoc}
             * 
             * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2#estabelecerConexao()
             */
            @Override
            protected Connection estabelecerConexao() {
                return conn;
            }
        };
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AdministrativoDaoImpl#atualizarSituacaoBoletos()}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testAtualizarSituacaoBoletos() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testAtualizarSituacaoBoletosException());
    }

    public String testAtualizarSituacaoBoletosException() throws ComumException {
        try {
            dao.atualizarSituacaoBoletos();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }
}
