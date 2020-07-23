package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * OperacionalBackOfficeCrudDaoDB2
 * 
 * @author Kaio.Rocha
 */
public abstract class IntegracaoCipCrudDaoDB2<T extends SicoobDDAEntidade> extends ComumCrudDaoDB2<T> implements IntegracaoCipCrudDaoIF<T> {

    protected static final String ID_MENSAGEM = "idMensagem";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public IntegracaoCipCrudDaoDB2(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar, String nomeComandoPesquisaNumeroRegistros) {
        super(arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar, nomeComandoPesquisaNumeroRegistros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2#estabelecerConexao()
     */
    @Override
    protected Connection estabelecerConexao() {
        try {
            CorporativoDataSource dataSource = new CorporativoDataSource(NOME_DATA_SOURCE, System.getProperties());

            return dataSource.getConnection();
        } catch (SQLException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new PersistenciaException(excecao);
        }
    }

    /**
     * Método responsável por obter a mensagemDDA de acordo com o idMensagemDDA
     * 
     * @param idMensagem
     * @return MensagemDDA
     * 
     */
    protected MensagemDDA obterMensagemDDA(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDA()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDA mensagemDDA = null;

        try {
            comando = getComando("OBTER_MENSAGEM_DDA");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDA = setMensagemDDA(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDA;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDA
     * 
     */
    private MensagemDDA setMensagemDDA(ResultSet rs) throws SQLException {
        return new MensagemDDA(rs.getLong("idMensagemDDA"), ObjectUtil.isEmpty(rs.getLong("idMensagemDDAOrigem")) ? null : rs.getLong("idMensagemDDAOrigem"),
                rs.getString("codTipoMensagemDDA"), new DateTimeDB(rs.getDate("dataMovimento").getTime()), ObjectUtil.isNull(rs.getDate("dataHoraMensagem")) ? null
                        : new DateTimeDB(rs.getDate("dataHoraMensagem").getTime()), ObjectUtil.isNull(rs.getDate("dataHoraProtocolo")) ? null : new DateTimeDB(rs.getDate(
                        "dataHoraProtocolo").getTime()), rs.getString("numOperacao"), rs.getString("xmlMensagem"), rs.getBoolean("bolOrigemSicoob"), new DateTimeDB(rs.getDate(
                        "dataHoraCadastro").getTime()), rs.getInt("numPrioridadeEnvio"), rs.getString("numControleDDA"), rs.getBoolean("bolExcedeuSLA"), rs.getShort("idCanal"),
                rs.getInt("idInstituicaoSolicitante"), rs.getString("idUsuarioSolicitante"));
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @param nomeParametro
     * @return Map<String,Object>
     * 
     */
    protected Map<String, Object> setParametroId(Long id, String nomeParametro) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(nomeParametro, id);
        return parametros;
    }

    /**
     * @param nomeComando
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @param log void
     * 
     */
    protected void executarScriptEmXML(String nomeComando, long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal, String log) {
        Comando comando = null;
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            comando = getComando(nomeComando, getParametrosXML(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal));
            comando.executarConsulta(conn);
        } catch (PersistenciaException e) {
            erroProcessamentoArquivo(nomeComando, idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, log, e);
        } finally {
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param nomeComando
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @param log void
     * 
     */
    protected void executarScriptEmXML(Connection conn, String nomeComando, long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal, String log) {
        Comando comando = null;
        try {
            conn = estabelecerConexao();
            comando = getComando(nomeComando, getParametrosXML(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal));
            comando.executarConsulta(conn);
        } catch (PersistenciaException e) {
            erroProcessamentoArquivo(nomeComando, idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, log, e);
        } finally {
            fecharComando(comando);

        }
    }

    /**
     * Método genérico que executa uma ATUALIZAÇÂO com os parâmetros informados.
     * 
     * @param conn
     * @param nomeComando
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @param log void
     * 
     */
    protected void executarAtualizacaoArquivoDDA(Connection conn, String nomeComando, long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal, String log) {
        Comando comando = null;
        try {
            comando = getComando(nomeComando, getParametrosXML(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal));
            comando.executarAtualizacao(conn);
        } catch (PersistenciaException e) {
            erroProcessamentoArquivo(nomeComando, idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, log, e);
        } finally {
            fecharComando(comando);
        }
    }

    /**
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getParametrosXML(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        Map<String, Object> parametros = getMapaParametro(idLogArquivoRecebido, "idLogArquivoRecebido");
        parametros.put("idLogDetArqInicial", idLogDetArqInicial);
        parametros.put("idLogDetArqFinal", idLogDetArqFinal);
        return parametros;
    }

    /**
     * @param nomeComando
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @param log
     * @param e void
     * 
     */
    private void erroProcessamentoArquivo(String nomeComando, long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal, String log, PersistenciaException e) {
        getLogger().erro(e, "ERRO! " + log + " COMANDO: " + nomeComando);
        getLogger().erro(e, "Parâmetros do Comando:[ ID:" + idLogArquivoRecebido + " Inicial: " + idLogDetArqInicial + " Final: " + idLogDetArqFinal + "]");
        throw (e);
    }
}
