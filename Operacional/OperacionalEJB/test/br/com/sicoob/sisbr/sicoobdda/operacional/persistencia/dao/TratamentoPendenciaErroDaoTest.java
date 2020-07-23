/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         TratamentoPendenciaErroDaoTest.java
 * Data Criação:    Oct 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl.TratamentoPendenciaErroDaoImpl;
import junit.framework.Assert;

/**
 * TratamentoPendenciaErroDaoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class TratamentoPendenciaErroDaoTest extends Mockito {

    @InjectMocks
    private TratamentoPendenciaErroDaoImpl dao;

    @Mock
    private Comando comando;

    @Mock
    private Query query;

    @Mock
    private Connection conn;

    @Mock
    private ResultSet rs;

    /**
     * Método responsável por void
     * 
     * @throws SQLException
     * 
     */
    @Before
    public void setUp() throws SQLException {
        dao = new TratamentoPendenciaErroDaoImpl() {

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
        when(comando.criarQuery(any(EntityManager.class))).thenReturn(query);
        when(comando.executarConsulta(conn)).thenReturn(rs);
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarPendenciaPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarPendencia());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarPendenciaSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.pendecia", listarPendencia(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarErroAgrupadoPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarErroAgrupado());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarErroAgrupadoSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.erro.agrupado", listarErroAgrupado(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarErroProcessamentoPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarErroProcessamento());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void listarErroProcessamentoSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.erro.processamento.cip", listarErroProcessamento(Boolean.TRUE));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void listarTipoTratamentoErroCipPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoTratamentoErroCip());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoErroCIPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterListaTratamentoErroCIP());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoErroSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.tratamento.erro.cip", obterListaTratamentoErroCIP(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException
     * @throws OperacionalException void
     * 
     */
    @Test
    public void obterListaTratamentoRetornoErroPassou() throws SQLException, OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterListaTratamentoRetornoErro());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoMensagemArquivoPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterListaTratamentoMensagemArquivo());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoMensagemArquivoSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.tratamento.mensagem.arquivo", obterListaTratamentoMensagemArquivo(Boolean.TRUE));
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void atualizarDataMovimentoMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, atualizarDataMovimentoMensagem());
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void reenviarSSPBMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, reenviarSSPBMensagem());
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void reenviarCIPMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, reenviarCIPMensagem());
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void finalizarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, finalizarMensagem());
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void reenviarMensagemFinalizadaSSPBPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, reenviarMensagemFinalizadaSSPB());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void reprocessarArquivoRecebidoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, reprocessarArquivoRecebido());
    }

    /**
     * Método responsável por void
     * 
     */
    @Ignore
    @Test
    public void prepararReenvioArquivoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, prepararReenvioArquivo());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterDetalheMensagemErroPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDetalheMensagemErro());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterDetalheMensagemErroNoResult() {
        Assert.assertEquals("integracaocip.erro.obter.detalhe.mensagem.erro", obterDetalheMensagemErro(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaMensagemErroLockPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterListaMensagemErroLock());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaMensagemErroLockSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.mensagem.lock.registro", obterListaMensagemErroLock(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoAutomatizadoPassou() throws SQLException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterListaTratamentoAutomatizado());
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    @Test
    public void obterListaTratamentoAutomatizadoSQLException() throws SQLException {
        Assert.assertEquals("integracaocip.erro.obter.lista.tratamento.automatizado", obterListaTratamentoAutomatizado(Boolean.TRUE));
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * @throws ComumException
     * 
     */
    @Test
    public void excluirMensagemPassou() throws SQLException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, excluirMensagem());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String listarPendencia() throws SQLException {
        return listarPendencia(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String listarPendencia(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.listarPendencia();
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String listarErroAgrupado() throws SQLException {
        return listarErroAgrupado(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String listarErroAgrupado(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.listarErroAgrupado();
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String listarErroProcessamento() throws SQLException {
        return listarErroProcessamento(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String listarErroProcessamento(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.listarErroProcessamento();
        } catch (OperacionalException e) {
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
    private String listarTipoTratamentoErroCip() {
        when(query.getResultList()).thenReturn(new ArrayList<TipoTratamentoErroCip>());
        dao.listarTipoTratamentoErroCip(Constantes.SHORT_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoErroCIP() throws SQLException {
        return obterListaTratamentoErroCIP(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoErroCIP(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.obterListaTratamentoErroCIP(Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM, Constantes.NOME_TESTE, Constantes.INTEGER_UM);
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException
     * @throws OperacionalException String
     * 
     */
    private String obterListaTratamentoRetornoErro() throws SQLException, OperacionalException {
        mockingResultSet();
        dao.obterListaTratamentoRetornoErro(Constantes.NOME_TESTE, Constantes.DATE_TIME_DB_AUX, Constantes.INTEGER_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoMensagemArquivo() throws SQLException {
        return obterListaTratamentoMensagemArquivo(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoMensagemArquivo(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.obterListaTratamentoMensagemArquivo(Constantes.DATE_TIME_DB_AUX, Constantes.INTEGER_UM);
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumException String
     * 
     */
    private String atualizarDataMovimentoMensagem() throws ComumException {
        dao.atualizarDataMovimentoMensagem(Constantes.DATE_AUX);
        dao.atualizarDataMovimentoMensagem(new ArrayList<Long>(), Constantes.DATE_AUX);
        verificaAdicionarVariavelCodSituacaoMensagem(2, SituacaoMensagemDDA.A_ENVIAR);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumException String
     * 
     */
    private String reenviarSSPBMensagem() throws ComumException {
        dao.reenviarSSPBMensagem(new ArrayList<Long>(), Constantes.DATE_AUX);
        verificaAdicionarVariavelCodSituacaoMensagem(1, SituacaoMensagemDDA.SEM_RETORNO_SSPB);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String reenviarCIPMensagem() throws ComumException {
        dao.reenviarCIPMensagem(new ArrayList<Long>(), Constantes.DATE_AUX);
        verificaAdicionarVariavelCodSituacaoMensagem(1, SituacaoMensagemDDA.SEM_RETORNO_CIP);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String finalizarMensagem() throws ComumException {
        dao.finalizarMensagem(new ArrayList<Long>());
        verificaAdicionarVariavelCodSituacaoMensagem(1, SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String reenviarMensagemFinalizadaSSPB() throws ComumException {
        dao.reenviarMensagemFinalizadaSSPB(new ArrayList<Long>(), Constantes.DATE_AUX);
        verificaAdicionarVariavelCodSituacaoMensagem(1);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String reprocessarArquivoRecebido() throws ComumException {
        dao.reprocessarArquivoRecebido(new ArrayList<Long>());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String prepararReenvioArquivo() throws ComumException {
        dao.prepararReenvioArquivo(new ArrayList<Long>());
        verificaAdicionarVariavelCodSituacaoMensagem(1, SituacaoMensagemDDA.SEM_RETORNO_CIP);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterDetalheMensagemErro() {
        return obterDetalheMensagemErro(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return String
     * 
     */
    private String obterDetalheMensagemErro(Boolean bolSQLException) {
        try {
            if (bolSQLException) {
                when(query.getSingleResult()).thenThrow(new NoResultException());
            } else {
                when(query.getSingleResult()).thenReturn(new MensagemDDA());
            }
            dao.obterDetalheMensagemErro(Constantes.LONG_UM);
        } catch (OperacionalNoResultException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaMensagemErroLock() throws SQLException {
        return obterListaMensagemErroLock(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaMensagemErroLock(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.obterListaMensagemErroLock(new ArrayList<Long>());
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoAutomatizado() throws SQLException {
        return obterListaTratamentoAutomatizado(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolSQLException
     * @return
     * @throws SQLException String
     * 
     */
    private String obterListaTratamentoAutomatizado(Boolean bolSQLException) throws SQLException {
        try {
            if (bolSQLException) {
                mockingResultSetSQLException();
            } else {
                mockingResultSet();
            }
            dao.obterListaTratamentoAutomatizado();
        } catch (OperacionalException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumException String
     * 
     */
    private String excluirMensagem() throws ComumException {
        dao.excluirMensagem(Constantes.LONG_UM);
        verify(comando, times(1)).adicionarVariavel("id", Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @throws SQLException void
     * 
     */
    private void mockingResultSetSQLException() throws SQLException {
        when(rs.next()).thenThrow(new SQLException());
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

    /**
     * Método responsável por
     * 
     * @param vezes void
     * 
     */
    private void verificaAdicionarVariavelCodSituacaoMensagem(int vezes) {
        verificaAdicionarVariavelCodSituacaoMensagem(vezes, null);
    }

    /**
     * Método responsável por
     * 
     * @param vezes
     * @param codSituacaoMensagemDDA void
     * 
     */
    private void verificaAdicionarVariavelCodSituacaoMensagem(int vezes, Short codSituacaoMensagemDDA) {
        verify(comando, times(vezes)).adicionarVariavel("codSituacaoMensagemDDA", codSituacaoMensagemDDA);
    }

}
