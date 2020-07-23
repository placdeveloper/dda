/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ArquivoRecebidoDaoImplTest.java
 * Data Criação:    Jan 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl;

/**
 * ArquivoRecebidoDaoImplTest
 * 
 * @author Samuell.Ramos
 */
@RunWith(PowerMockRunner.class)
public class ArquivoRecebidoDaoImplTest extends Mockito {

    @InjectMocks
    private ArquivoRecebidoDaoImpl dao;

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
        dao = new ArquivoRecebidoDaoImpl() {

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
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#listarSitucaoArquivo()}.
     */
    @Test
    public final void testListarSitucaoArquivo() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testListarSitucaoArquivoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testListarSitucaoArquivoDAO() {
        try {
            dao.listarSitucaoArquivo();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#listarTipoArquivo()}.
     */
    @Test
    public final void testListarTipoArquivo() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testListarTipoArquivoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testListarTipoArquivoDAO() {
        try {
            dao.listarTipoArquivo();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#listarTipoMensagemAgenAdda()}.
     * 
     * @throws SQLException
     */
    @Test
    public final void testListarTipoMensagemAgenAdda() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testListarTipoMensagemAgenAddaDAO(Boolean.FALSE));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#listarTipoMensagemAgenAdda()}.
     * 
     * @throws SQLException
     */
    @Test
    public final void testListarTipoMensagemAgenAddaSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testListarTipoMensagemAgenAddaDAO(Boolean.TRUE));
    }

    @Test
    public final void testListarTipoMensagemAgenAddaPersistenceException() throws SQLException {
        when(rs.next()).thenThrow(new PersistenceException());
        Assert.assertEquals(Constantes.TESTE_FALHA, testListarTipoMensagemAgenAddaDAO(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws SQLException
     * 
     */
    private String testListarTipoMensagemAgenAddaDAO(boolean exception) throws SQLException {
        try {
            if (!exception) {
                mockingResultSet();
            }
            dao.listarTipoMensagemAgenAdda();
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#obterArquivoRecebido(java.lang.Long)}.
     */
    @Test
    public final void testObterArquivoRecebido() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterArquivoRecebidoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testObterArquivoRecebidoDAO() {
        try {
            dao.obterArquivoRecebido(Constantes.LONG_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }       
    }


    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#alterarSituacaoArquivo(long, short)}.
     */
    @Test
    public final void testAlterarSituacaoArquivo() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testAlterarSituacaoArquivoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testAlterarSituacaoArquivoDAO() {
        try {
            dao.alterarSituacaoArquivo(Constantes.LONG_UM, Constantes.SHORT_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#atualizarQtdeTotalRegistrosArquivoRecebido(long, int)}.
     */
    @Test
    public final void testAtualizarQtdeTotalRegistrosArquivoRecebido() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testAtualizarQtdeTotalRegistrosArquivoRecebidoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testAtualizarQtdeTotalRegistrosArquivoRecebidoDAO() {
        try {
            dao.atualizarQtdeTotalRegistrosArquivoRecebido(Constantes.LONG_UM, Constantes.INTEGER_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#obterDadosProcessamentoArquivo(java.lang.Long)} .
     */
    @Test
    public final void testObterDadosProcessamentoArquivo() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterDadosProcessamentoArquivoDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testObterDadosProcessamentoArquivoDAO() {
        try {
            dao.obterDadosProcessamentoArquivo(Constantes.LONG_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoRecebidoDaoImpl#alterarSituacaoRegistro(br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA)}
     * .
     */
    @Test
    public final void testAlterarSituacaoRegistro() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testAlterarSituacaoRegistroDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String testAlterarSituacaoRegistroDAO() {
        try {
            dao.alterarSituacaoRegistro(montarLogDetRecebimentoArquivoDDA());
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @return LogDetRecebimentoArquivoDDA
     * 
     */
    private LogDetRecebimentoArquivoDDA montarLogDetRecebimentoArquivoDDA() {
        LogDetRecebimentoArquivoDDA logDetRecbArqDDA = new LogDetRecebimentoArquivoDDA();
        logDetRecbArqDDA.setId(Constantes.LONG_UM);
        return logDetRecbArqDDA;
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
