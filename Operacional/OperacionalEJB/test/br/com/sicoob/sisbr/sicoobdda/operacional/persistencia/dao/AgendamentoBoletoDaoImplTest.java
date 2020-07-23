/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         AgendamentoBoletoDaoImplTest.java
 * Data Criação:    Jan 10, 2018
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
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl;


/**
 * AgendamentoBoletoDaoImplTest
 * 
 * @author samuell.ramos
 */
@RunWith(PowerMockRunner.class)
public class AgendamentoBoletoDaoImplTest extends Mockito {

    @InjectMocks
    private AgendamentoBoletoDaoImpl dao;
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
        dao = new AgendamentoBoletoDaoImpl() {

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
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterBoletoPorCodBarras(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.util.Date)}
     */
    @Test
    public final void testObterBoletoPorCodBarras() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterBoletoPorCodBarrasDao());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testObterBoletoPorCodBarrasDao() {
        try {
            mockingResultSet();
            dao.obterBoletoPorCodBarras(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.COD_BARRAS_39_VALIDO, Constantes.DATE_AUX);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(java.lang.Integer, java.lang.String, java.lang.Short, java.lang.Integer)}.
     */
    @Test
    public final void testObterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, Constantes.TESTE_SUCESSO, testObterAlertaPagamentoBoletoVencidoCaixaOuCorrespondenteDAO());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(java.lang.Integer, java.lang.String, java.lang.Short, java.lang.Integer)}
     * .
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterAlertaPagamentoBoletoVencidoCaixaOuCorrespondenteSQLException() throws SQLException {
        when(cs.execute()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterAlertaPagamentoBoletoVencidoCaixaOuCorrespondenteDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testObterAlertaPagamentoBoletoVencidoCaixaOuCorrespondenteDAO() {
        try {
            dao.obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(Constantes.INTEGER_UM, Constantes.LINHA_DIGITAVEL, Constantes.SHORT_UM, Constantes.INTEGER_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterDataUtil(int, int, java.util.Date)}.
     */
    @Test
    public final void testObterDataUtilIntIntDate() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterDataUtilIntIntDateDAO());
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterDataUtil(int, int, java.util.Date)}.
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterDataUtilIntIntDateSQLException() throws SQLException {
        when(cs.execute()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterDataUtilIntIntDateDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testObterDataUtilIntIntDateDAO() {
        try {
            dao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.DATE_AUX);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterDataUtil(int, int, java.util.Date, boolean)}.
     */
    @Test
    public final void testObterDataUtilIntIntDateBoolean() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterDataUtilIntIntDateBooleanDAO());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterDataUtil(int, int, java.util.Date, boolean)}.
     * 
     * @throws SQLException
     */
    @Test
    public final void testObterDataUtilIntIntDateBooleanSQLException() throws SQLException {
        when(cs.execute()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterDataUtilIntIntDateBooleanDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testObterDataUtilIntIntDateBooleanDAO() {
        try {
            dao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.DATE_AUX, Boolean.TRUE);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#isDataUtil(int, java.util.Date)}.
     */
    @Test
    public final void testIsDataUtil() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testIsDataUtilDAO());
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#isDataUtil(int, java.util.Date)}.
     */
    @Test
    public final void testIsDataUtilSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testIsDataUtilDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testIsDataUtilDAO() {
        try {
            dao.isDataUtil(Constantes.INTEGER_UM, Constantes.DATE_AUX);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterQtdDiasUteis(int, int, java.util.Date, java.util.Date)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testObterQtdDiasUteis() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterQtdDiasUteisDAO());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterQtdDiasUteis(int, int, java.util.Date, java.util.Date)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testObterQtdDiasUteisSQLException() throws ComumNegocioException, ComumException, SQLException {
        when(cs.execute()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterQtdDiasUteisDAO());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.AgendamentoBoletoDaoImpl#obterQtdDiasUteis(int, int, java.util.Date, java.util.Date)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testObterQtdDiasUteisIllegalStateException() throws ComumNegocioException, ComumException, SQLException {
        when(cs.execute()).thenThrow(new IllegalStateException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testObterQtdDiasUteisDAO());
    }


    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testObterQtdDiasUteisDAO() {
        try {
            dao.obterQtdDiasUteis(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.DATE_AUX, Constantes.DATE_AUX);
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
