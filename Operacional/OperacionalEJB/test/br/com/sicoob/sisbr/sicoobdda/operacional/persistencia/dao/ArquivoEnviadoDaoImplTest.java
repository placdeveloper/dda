/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ArquivoEnviadoDaoImplTest.java
 * Data Criação:    Jan 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

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
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoEnviadoDaoImpl;

/**
 * ArquivoEnviadoDaoImplTest
 * 
 * @author Samuell.Ramos
 */
@RunWith(PowerMockRunner.class)
public class ArquivoEnviadoDaoImplTest extends Mockito {

    @InjectMocks
    private ArquivoEnviadoDaoImpl dao;
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
        dao = new ArquivoEnviadoDaoImpl() {

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
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoEnviadoDaoImpl#obterEnvioArquivoDDA(java.lang.Long)}.
     */
    @Test
    public final void testObterEnvioArquivoDDA() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterEnvioArquivoDDADAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testObterEnvioArquivoDDADAO() {
        try {
            dao.obterEnvioArquivoDDA(Constantes.LONG_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.ArquivoEnviadoDaoImpl#listarTipoMensagemAgenAdda()}.
     */
    @Test
    public final void testListarTipoMensagemAgenAdda() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testListarTipoMensagemAgenAddaDAO());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testListarTipoMensagemAgenAddaDAO() {
        try {
            dao.listarTipoMensagemAgenAdda();
            return Constantes.TESTE_SUCESSO;
        } catch (BancoobException e) {
            return Constantes.TESTE_FALHA;
        }
    }


}
