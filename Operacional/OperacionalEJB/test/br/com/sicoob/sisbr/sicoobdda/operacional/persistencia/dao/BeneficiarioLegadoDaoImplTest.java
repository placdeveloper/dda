/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         BeneficiarioLegadoDaoImplTest.java
 * Data Criação:    Jan 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl;

/**
 * BeneficiarioLegadoDaoImplTest
 * 
 * @author Samuell.Ramos
 */
@RunWith(PowerMockRunner.class)
public class BeneficiarioLegadoDaoImplTest extends Mockito {

    @InjectMocks
    private BeneficiarioLegadoDaoImpl dao;
    @Mock
    private Comando comando;

    @Mock
    private Query query;

    @Mock
    private Connection conn;

    @Mock
    private ResultSet rs;

    @Mock
    private CallableStatement cs = null;

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws Exception {
        dao = new BeneficiarioLegadoDaoImpl() {

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

            @Override
            protected Connection estabelecerConexao(Integer numCooperativa) {
                return conn;
            }

        };
        when(comando.criarQuery(any(EntityManager.class))).thenReturn(query);
        when(comando.executarConsulta(conn)).thenReturn(rs);
        when(conn.prepareCall(anyString())).thenReturn(cs);
        when(cs.executeQuery()).thenReturn(rs);

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterCadastroBeneficiarioDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterCadastroBeneficiarioDDADto() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterCadastroBeneficiarioDDADtoDAO(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterCadastroBeneficiarioDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterCadastroBeneficiarioDDADtoSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterCadastroBeneficiarioDDADtoDAO(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return
     * @throws SQLException String
     * 
     */
    public String testObterCadastroBeneficiarioDDADtoDAO(boolean exception) throws SQLException {
        try {
            if (!exception) {
                mockingResultSet();
            }
            dao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.NUM_COOP_0001);
            return Constantes.TESTE_SUCESSO;
        } catch (IntegracaoCipException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterAlterarCadastroBeneficiarioDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterAlterarCadastroBeneficiarioDDADtoStringInteger() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterAlterarCadastroBeneficiarioDDADtoStringIntegerDAO(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterAlterarCadastroBeneficiarioDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterAlterarCadastroBeneficiarioDDADtoStringIntegerSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterAlterarCadastroBeneficiarioDDADtoStringIntegerDAO(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return
     * @throws SQLException String
     * 
     */
    public String testObterAlterarCadastroBeneficiarioDDADtoStringIntegerDAO(boolean exception) throws SQLException {
        try {
            if (!exception) {
                mockingResultSet();
            }
            dao.obterAlterarCadastroBeneficiarioDDADto(Constantes.STRING_NUMERO_1, Constantes.NUM_COOP_0001);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterAlterarCadastroBeneficiarioDDADto(java.lang.Long, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterAlterarCadastroBeneficiarioDDADtoLongInteger() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterAlterarCadastroBeneficiarioDDADtoLongIntegerDAO(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterAlterarCadastroBeneficiarioDDADto(java.lang.Long, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterAlterarCadastroBeneficiarioDDADtoLongIntegerSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterAlterarCadastroBeneficiarioDDADtoStringIntegerDAO(Boolean.TRUE));
    }

    public String testObterAlterarCadastroBeneficiarioDDADtoLongIntegerDAO(boolean exception) throws SQLException {
        try {
            if (!exception) {
                mockingResultSet();
            }
            dao.obterAlterarCadastroBeneficiarioDDADto(Constantes.LONG_UM, Constantes.NUM_COOP_0001);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterConvenioAlteracaoDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterConvenioAlteracaoDDADto() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterConvenioAlteracaoDDADtoDAO(Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.BeneficiarioLegadoDaoImpl#obterConvenioAlteracaoDDADto(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterConvenioAlteracaoDDADtoSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterConvenioAlteracaoDDADtoDAO(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @param exception
     * @return
     * @throws SQLException String
     * 
     */
    public String testObterConvenioAlteracaoDDADtoDAO(boolean exception) throws SQLException {
        try {
            if (!exception) {
                mockingResultSet();
            }
            dao.obterConvenioAlteracaoDDADto(Constantes.CPF_AUX, Constantes.NUM_COOP_0001);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    private void mockingResultSet() throws SQLException {
        when(rs.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(rs.getString(anyString())).thenReturn(Constantes.NOME_TESTE);
        when(rs.getInt(anyString())).thenReturn(Constantes.INTEGER_UM);
        when(rs.getLong(anyString())).thenReturn(Constantes.LONG_UM);
        when(rs.getDate(anyString())).thenReturn(Constantes.DATE_SQL);
    }

}
