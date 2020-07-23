/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         ParametroDaoImpl.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * ParametroDaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ParametroDaoImpl extends ComumCrudDaoDB2<ParametroDDA> implements ParametroDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_comum.parametro.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-parametro";

    private static final String FECHANDO_CONEXAO = "FECHANDO CONEXÃO ";
    private static final String FECHANDO_COMANDO = "FECHANDO COMANDO";
    private static final String COMANDO_EXECUTADO_SUCESSO = "COMANDO EXECUTADO COM SUCESSO! ";
    private static final String EXECUTANDO_COMANDO_BASE_DADOS = "EXECUTANDO O COMANDO NA BASE DE DADOS ";
    private static final String COMANDO_SQL = "COMANDO SQL ";
    private static final String NOME_QUERY_UTILIZADA = "NOME QUERY UTILIZADA: ";
    private static final String ATUALIZANDO_VALOR_PARAMETRO_INSTITUICAO = "ATUALIZANDO VALOR PARAMETRO INSTITUICAO ";
    private static final String CONEXAO_ESTABELECIDA = "CONEXÃO ESTABELECIDA! ";
    private static final String TENTANDO_ESTABELECER_CONEXAO_COM_BASE_DADOS = "TENTANDO ESTABELECER CONEXÃO COM A BASE DE DADOS ";
    private static final String ATUALIZAR_VALOR_PARAMETRO_INSTITUICAO = "ATUALIZAR_VALOR_PARAMETRO_INSTITUICAO";
    private static final String LISTAR_INSTITUICOES = "LISTAR_INSTITUICOES_JDBC";
    private static final String ATUALIZANDO_LISTA_VALOR_PARAMETRO_INSTITUICAO = "ATUALIZANDO_LISTA_VALOR_PARAMETRO_INSTITUICAO_JDBC";
    private static final String OBTER_VALOR_PARAMETRO_INSTITUICAO = "OBTER_VALOR_PARAMETRO_INSTITUICAO";

    /**
     * Construtor
     */
    public ParametroDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, ParametroDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterParametro(java.lang.Long, java.lang.Integer)
     */
    public ParametroDDA obterParametro(Long idParametro, Integer idInstituicao) throws ComumException {
        ParametroDDA parametroRetorno = null;

        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        getLogger().debug("******ObterParametro******");
        try {
            comando = getComando("OBTER_PARAMETRO_INSTIUICAO_JDBC");
            comando.adicionarVariavel("idParametro", idParametro);
            comando.configurar();
            getLogger().debug("idParametro: " + idParametro);

            getLogger().debug(comando.getSqlEmUso());

            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                parametroRetorno = populaParametro(rs);
            }

            if (parametroRetorno != null && !parametroRetorno.getBolParametroGlobal()) {
                this.obterValorParametro(parametroRetorno, idParametro, idInstituicao);
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return parametroRetorno;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException Parametro
     * 
     */
    private ParametroDDA populaParametro(ResultSet rs) throws SQLException {
        ParametroDDA parametroRetorno = new ParametroDDA();
        parametroRetorno.setId(rs.getLong("idParametro"));
        parametroRetorno.setNomeParametro(rs.getString("nomeParametro"));
        parametroRetorno.setBolVisivelFuncionalidadeAlteracao(rs.getBoolean("bolVisivelFuncionalidadeAlteracao"));
        parametroRetorno.setBolPermiteAlteracaoPeloUsuario(rs.getBoolean("bolPermiteAlteracaoPeloUsuario"));
        parametroRetorno.setBolAtivo(rs.getBoolean("bolAtivo"));
        parametroRetorno.setDataCriacao(new DateTimeDB(rs.getDate("dataCriacao").getTime()));
        parametroRetorno.setDataHoraUltimaAtualizacao(new DateTimeDB(rs.getDate("dataHoraUltimaAtualizacao").getTime()));
        parametroRetorno.setDescParametro(rs.getString("descParametro"));
        parametroRetorno.setValorParametro(rs.getString("valorBaseParametro"));
        parametroRetorno.setValorParametroTexto(rs.getString("valorBaseParametroTexto"));
        parametroRetorno.setNomeTabelaDominio(rs.getString("nomeTabelaDominio"));
        parametroRetorno.setBolParametroGlobal(rs.getBoolean("bolParametroGlobal"));

        TipoParametroDDA tipoParametro = new TipoParametroDDA();
        tipoParametro.setId(rs.getShort("idTipoParametro"));
        parametroRetorno.setTipoParametro(tipoParametro);

        return parametroRetorno;
    }

    /**
     * Metodo responsavel por consultar o ValorParametro
     * 
     * @param parametro
     * @param idParametro
     * @param idInstituicao
     * @throws ComumException
     * 
     */
    private void obterValorParametro(ParametroDDA parametro, Long idParametro, Integer idInstituicao) throws ComumException {
        List<ValorParametroDDA> listaValorParametro = null;

        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        getLogger().debug("******ObterValorParametro******");
        try {
            comando = getComando("OBTER_VALOR_PARAMETRO_INSTIUICAO_JDBC");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.adicionarVariavel("idParametro", idParametro);
            comando.configurar();

            conn = estabelecerConexao();

            getLogger().debug(comando.getSqlEmUso());

            getLogger().debug("idParametro: " + idParametro);
            getLogger().debug("idIntituicao: " + idInstituicao);

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                listaValorParametro = new ArrayList<ValorParametroDDA>();
                listaValorParametro.add(populaValorParametro(rs));
            }
            parametro.setListaValorParametro(listaValorParametro);
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * Metodo responsavel por
     * 
     * @param rs
     * @return
     * @throws SQLException ValorParametro
     * 
     */
    private ValorParametroDDA populaValorParametro(ResultSet rs) throws SQLException {
        ValorParametroDDA valorParametro = new ValorParametroDDA();
        valorParametro.setId(rs.getLong("idValorParametro"));
        valorParametro.setIdInstituicao(rs.getLong("idInstituicao"));
        valorParametro.setIdUsuarioResponsavel(rs.getString("idUsuarioResponsavel"));
        valorParametro.setValorParametro(rs.getString("valorParametro"));
        valorParametro.setDataCriacao(new DateTimeDB(rs.getDate("dataCriacao").getTime()));
        valorParametro.setDataHoraUltimaAtualizacao(new DateTimeDB(rs.getDate("dataHoraUltimaAtualizacao").getTime()));
        return valorParametro;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValor(java.lang.Long, java.lang.Integer)
     */
    public String obterValor(Long idParametro, Integer idInstituicao) throws ComumException {
        getLogger().debug("******ObterValor******");
        ParametroDDA parametro = this.obterParametro(idParametro, idInstituicao);
        if (parametro.getBolParametroGlobal()) {
            return parametro.getValorParametro();
        } else {
            return parametro.getListaValorParametro().get(0).getValorParametro();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorBoolean(java.lang.Long, java.lang.Integer)
     */
    public Boolean obterValorBoolean(Long idParametro, Integer idInstituicao) throws ComumException {
        return obterValor(idParametro, idInstituicao).equals(Constantes.STRING_NUMERO_1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorInteger(java.lang.Long, java.lang.Integer)
     */
    public Integer obterValorInteger(Long idParametro, Integer idInstituicao) throws ComumException {
        return Integer.valueOf(obterValor(idParametro, idInstituicao));
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#atualizarValorParametro(java.lang.Long, java.lang.Integer)
     */
    public void atualizarValorParametro(Long idParametro, Integer idInstituicao, String valorParametro) throws ComumException {
        getLogger().debug("******atualizarValorParametro******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        ParametroDDA parametroRetorno = null;
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_PARAMETRO_INSTIUICAO_JDBC");
            comando.adicionarVariavel("idParametro", idParametro);
            comando.configurar();
            getLogger().debug("idParametro: " + idParametro);
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                parametroRetorno = populaParametro(rs);
            }

            if (!ObjectUtil.isNull(parametroRetorno) && parametroRetorno.getBolParametroGlobal()) {
                comando = getComando("ATUALIZAR_VALOR_PARAMETRO_GLOBAL_JDBC");
                comando.adicionarVariavel("idParametro", idParametro);
                comando.adicionarVariavel("valorParametro", valorParametro);
                comando.configurar();

                getLogger().debug(comando.getSqlEmUso());
                getLogger().debug("idParametro: " + idParametro);
                getLogger().debug("valorParametro: " + valorParametro);
            } else {
                comando = getComando("ATUALIZAR_VALOR_PARAMETRO_INSTIUICAO_JDBC");
                comando.adicionarVariavel("idInstituicao", idInstituicao);
                comando.adicionarVariavel("idParametro", idParametro);
                comando.adicionarVariavel("valorParametro", valorParametro);
                comando.configurar();

                getLogger().debug(comando.getSqlEmUso());
                getLogger().debug("idParametro: " + idParametro);
                getLogger().debug("idIntituicao: " + idInstituicao);
                getLogger().debug("valorParametro: " + valorParametro);
            }
            comando.executarAtualizacao(conn);
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#listarParametros(boolean, boolean)
     */
    public List<ParametroDDA> listarParametros(boolean somenteVisiveis, boolean somenteNaoGlobal) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("somenteVisiveis", somenteVisiveis);
        parametros.put("somenteNaoGlobal", somenteNaoGlobal);

        return listar("LISTAR_PARAMETRO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterDadosParametro(java.lang.Long, java.lang.String)
     */
    public List<ParametroDDA> obterDadosParametro(Long idParametro, String nomeParametro) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idParametro", idParametro);
        parametros.put("nomeParametro", nomeParametro);
        return listar("PESQUISAR_PARAMETRO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterListaTipoParametro()
     */
    public List<TipoParametroDDA> obterListaTipoParametro() throws ComumException {
        return listar("LISTAR_TIPO_PARAMETRO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#atualizaValorParametro()
     */
    public void atualizaValorParametro() throws ComumException {
        Connection conn = null;

        try {
            getLogger().info(TENTANDO_ESTABELECER_CONEXAO_COM_BASE_DADOS);
            conn = estabelecerConexao();
            getLogger().info(CONEXAO_ESTABELECIDA);

            getLogger().info(ATUALIZANDO_VALOR_PARAMETRO_INSTITUICAO);

            // Exclusão
            excluirValorParametroDesnecessario(conn);

            // Inclusão
            atualizarValorParametro(conn);
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            getLogger().info(FECHANDO_CONEXAO);
            fecharConexao(conn);
        }

    }

    /**
     * Método responsável por excluir os valores parâmetro dos parâmetros que são globais.
     * 
     * @param conn
     * @throws SQLException
     */
    private void excluirValorParametroDesnecessario(Connection conn) throws SQLException {
        Comando comando = null;
        PreparedStatement ps = null;

        try {
            final String comandoSql = "EXCLUIR_VALOR_PARAMETRO_DESNECESSARIO";

            getLogger().info(NOME_QUERY_UTILIZADA + comandoSql);
            comando = getComando(comandoSql);

            comando.configurar();

            getLogger().info(COMANDO_SQL + comando.getSqlEmUso());
            ps = conn.prepareStatement(comando.getSqlEmUso());

            getLogger().info(EXECUTANDO_COMANDO_BASE_DADOS);
            ps.execute();
            getLogger().info(COMANDO_EXECUTADO_SUCESSO);
        } finally {
            getLogger().info(FECHANDO_COMANDO);
            fecharComando(comando);
            fecharStatement(ps);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterListaInstituicoes(java.util.List)
     */
    public List<ValorParametroDDA> obterListaInstituicoes(List<Integer> listaSingular) throws ComumException {
        List<ValorParametroDDA> listaValorParametro = new ArrayList<ValorParametroDDA>();
        Comando comando = null;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            getLogger().info("CONSULTANDO DADOS  - LISTA INSTITUIÇÕES ");
            getLogger().debug(NOME_QUERY_UTILIZADA + LISTAR_INSTITUICOES);
            comando = getComando(LISTAR_INSTITUICOES);
            comando.adicionarVariavel("listaSingular", listaSingular);
            comando.configurar();

            getLogger().debug(COMANDO_SQL);
            getLogger().debug(comando.getSqlEmUso());

            getLogger().debug(TENTANDO_ESTABELECER_CONEXAO_COM_BASE_DADOS);
            conn = estabelecerConexao();

            getLogger().debug(CONEXAO_ESTABELECIDA);

            getLogger().debug(EXECUTANDO_COMANDO_BASE_DADOS);
            rs = comando.executarConsulta(conn);
            getLogger().info(COMANDO_EXECUTADO_SUCESSO);

            while (rs.next()) {
                listaValorParametro.add(populaDadosValorParametro(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharConexoesAbertas(comando, conn, stm, rs);
        }

        return listaValorParametro;
    }

    /**
     * Método responsável por incluir os valores parâmetro para os parâmetros que ainda não possuem.
     * 
     * @param conn
     * @throws SQLException
     */
    private void atualizarValorParametro(Connection conn) throws SQLException {
        Comando comando = null;
        PreparedStatement ps = null;

        try {
            getLogger().info(NOME_QUERY_UTILIZADA + ATUALIZAR_VALOR_PARAMETRO_INSTITUICAO);
            comando = getComando(ATUALIZAR_VALOR_PARAMETRO_INSTITUICAO);

            comando.configurar();

            getLogger().info(COMANDO_SQL + comando.getSqlEmUso());
            ps = conn.prepareStatement(comando.getSqlEmUso());

            getLogger().info(EXECUTANDO_COMANDO_BASE_DADOS);
            ps.execute();
        } finally {
            getLogger().info(FECHANDO_COMANDO);
            fecharComando(comando);
            fecharStatement(ps);
        }
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return ValorParametro
     * @throws SQLException
     * 
     */
    private ValorParametroDDA populaDadosValorParametro(ResultSet rs) throws SQLException {
        ValorParametroDDA valorParametro = new ValorParametroDDA();
        valorParametro.setIdInstituicao(rs.getLong("IDINSTITUICAO"));
        valorParametro.setDescInstituicao(rs.getString("DESCSIGLACOOP"));
        return valorParametro;
    }

    /**
     * 
     * Método responsável por fechar todas as conexões abertas
     * 
     * @param comando
     * @param conn
     * @param stm
     * @param rs void
     * 
     */
    private void fecharConexoesAbertas(Comando comando, Connection conn, Statement stm, ResultSet rs) {
        fecharResultSet(rs);
        fecharComando(comando);
        fecharConexao(conn);
        fecharStatement(stm);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#atualizarValorParametro(br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA,
     *      java.util.List)
     */
    public void atualizarValorParametro(ParametroDDA parametro, List<Long> listaInstituicao) throws ComumException {
        Comando comando = null;
        Connection conn = null;

        try {

            getLogger().debug(TENTANDO_ESTABELECER_CONEXAO_COM_BASE_DADOS);
            conn = estabelecerConexao();
            getLogger().debug(CONEXAO_ESTABELECIDA);

            getLogger().debug(NOME_QUERY_UTILIZADA + ATUALIZANDO_LISTA_VALOR_PARAMETRO_INSTITUICAO);
            comando = getComando(ATUALIZANDO_LISTA_VALOR_PARAMETRO_INSTITUICAO);

            comando.adicionarVariavel("valorParametro", parametro.getValorParametro());
            comando.adicionarVariavel("idParametro", parametro.getId());
            comando.adicionarVariavel("listaInstituicao", converterListToString(listaInstituicao));
            comando.configurar();

            getLogger().debug(COMANDO_SQL + comando.getSqlEmUso());
            comando.executarAtualizacao(conn);
            getLogger().info(COMANDO_EXECUTADO_SUCESSO);

        } catch (PersistenceException e) {
            getLogger().erro(e, e.getMessage());
        } finally {
            fecharConexoesAbertas(comando, conn, null, null);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorParametroInstituicao(java.lang.Long, java.lang.Long)
     */
    public String obterValorParametroInstituicao(Integer idInstituicao, Long idParametro) throws ComumException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idInstituicao", idInstituicao.longValue());
        parametros.put("idParametro", idParametro);
        return obterDados(OBTER_VALOR_PARAMETRO_INSTITUICAO, parametros);
    }

    /**
     * Converter um parametro da lista generica para uma String
     * 
     * @param lista
     * @return
     */
    public static String converterListToString(Collection<?> lista) {
        Iterator<?> it = lista.iterator();
        String identificador = null;
        while (it.hasNext()) {
            identificador = (String) (identificador == null ? " " + it.next() : " " + identificador + "," + it.next());
        }
        return identificador;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorBooleanLockRegistro(java.lang.Long, java.lang.Integer,
     *      java.lang.Boolean)
     */
    public Boolean obterValorBooleanLockRegistro(Long idParametro, Integer idInstituicao, Boolean parametroGlobal) throws ComumException {
        Comando comando = null;
        String retorno = null;
        try {
            if (parametroGlobal) {
                comando = getComando("OBTER_VALOR_PARAMETRO_GLOBAL_LOCK_REGISTRO_JDBC");
                comando.adicionarVariavel("idParametro", idParametro);
                getLogger().debug("OBTER_VALOR_PARAMETRO_GLOBAL_LOCK_REGISTRO_JDBC " + "idParametro: " + idParametro);
            } else {
                comando = getComando("OBTER_VALOR_PARAMETRO_INSTITUICAO_LOCK_REGISTRO_JDBC");
                comando.adicionarVariavel("idParametro", idParametro);
                comando.adicionarVariavel("idInstituicao", idInstituicao);
                getLogger().debug("OBTER_VALOR_PARAMETRO_INSTITUICAO_LOCK_REGISTRO_JDBC " + "idParametro: " + idParametro + "idIntituicao: " + idInstituicao);
            }
            retorno = obterValorGlobalLockRegistro(comando);
        } finally {
            fecharComando(comando);
        }
        return retorno.equals(Constantes.STRING_NUMERO_1) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Método responsável por consultar o valor do parametro, global ou por instituicao locando o registro consultado até o fim da transação (SELECT FOR
     * UPDATE).
     * 
     * @param comando
     * @return
     * @throws ComumException String
     * 
     */
    private String obterValorGlobalLockRegistro(Comando comando) throws ComumException {
        String retorno = null;
        Connection conn = null;
        ResultSet rs = null;
        getLogger().debug("******obterValorGlobalLockRegistro******");
        try {
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                retorno = rs.getString(1);
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#listarParametros(long[])
     */
    public List<ParametroDDA> listarParametros(long... idsParametros) {
        getLogger().debug("*******recuperarParamtrosParaStep*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        List<ParametroDDA> listaParametros = null;
        List<Long> cods = new ArrayList<Long>();
        try {
            for (int cont = 0; cont < idsParametros.length; cont++) {
                cods.add(idsParametros[cont]);
            }
            comando = getComando("LISTAR_PARAMETROS_PARA_STEP");
            comando.adicionarVariavel("idsParametrosNecessarios", cods);

            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            listaParametros = new ArrayList<>();

            while (rs.next()) {
                listaParametros.add(populaParametro(rs));
            }
            return listaParametros;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorDouble(java.lang.Long, java.lang.Integer)
     */
    public Double obterValorDouble(Long idParametro, Integer idInstituicao) throws ComumException {
        return Double.valueOf(obterValor(idParametro, idInstituicao));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterValorShort(java.lang.Long, java.lang.Integer)
     */
    public Short obterValorShort(Long idParametro, Integer idInstituicao) throws ComumException {
        return Short.valueOf(obterValor(idParametro, idInstituicao));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#listarAtivos()
     */
    @Override
    public List<ParametroDDA> listarAtivos() throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        List<ParametroDDA> listaParametros = new ArrayList<>();
        try {
            comando = getComando("LISTAR_PARAMETROS_ATIVOS");
            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaParametros.add(populaParametro(rs));
            }
            return listaParametros;
        } catch (SQLException e) {
            throw new ComumException("parametro.dao.erro.listar", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterMapaParametro(long[])
     */
    public Map<Long, ParametroDDA> obterMapaParametro(long... idParametros) throws ComumException {

        Connection conn = null;
        ResultSet rs = null;
        Comando comando = null;

        Map<Long, ParametroDDA> mapaParametro = new HashMap<>();
        try {

            conn = estabelecerConexao();
            comando = getComando("LISTAR_PARAMETROS");
            comando.adicionarVariavel("listaIdParametro", getListaParametros(idParametros));

            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                mapaParametro.put(rs.getLong(1), geraParametro(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mapaParametro;
    }

    /**
     * Método responsável por
     * 
     * @param idParametros
     * @return Object
     * 
     */
    private List<Long> getListaParametros(long[] idParametros) {
        List<Long> lista = new ArrayList<Long>();
        for (long l : idParametros) {
            lista.add(l);
        }
        return lista;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException Parametro
     * 
     */
    private ParametroDDA geraParametro(ResultSet rs) throws SQLException {
        return new ParametroDDA(rs.getLong(1), rs.getString(2), rs.getString(3));
    }

}
