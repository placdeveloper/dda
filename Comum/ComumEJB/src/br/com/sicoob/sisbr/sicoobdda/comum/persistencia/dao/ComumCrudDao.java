/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumCrudDao.java
 * Data Cria��o:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.dao.BancoobCrudDao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumCrudDao � respons�vel por
 * 
 * @author Fabricio.Brandao
 */
public abstract class ComumCrudDao<T extends SicoobDDAEntidade> extends BancoobCrudDao<T> implements ComumCrudDaoIF<T> {

    private static final String SEPARADOR = " ======================================= ";

    public static final String NOME_DATA_SOURCE = "jdbc/BancoobDS";
    private String nomePesquisaNumeroRegistros = null;

    private static final String PESQUISA_MAX_REGISTROS_CONSULTA = "pesquisa.max.registros.consulta";
    private static final ParametroDao PARAMETRO_DAO;

    static {
        PARAMETRO_DAO = ComumDaoFactory.getInstance().criarParametroDao();
    }

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public ComumCrudDao(String datasource, String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar) {
        super(datasource, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
    }

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public ComumCrudDao(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar) {
        super(NOME_DATA_SOURCE, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
    }

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ComumCrudDao(String nomeDataSource, String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar,
            String nomeComandoPesquisaNumeroRegistros) {
        super(nomeDataSource, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
        this.nomePesquisaNumeroRegistros = nomeComandoPesquisaNumeroRegistros;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoIF#pesquisar(java.lang.Class, br.com.bancoob.negocio.dto.ConsultaDto,
     * br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum, long, int)
     */
    public <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe, long idParametro, int idInstituicao)
            throws ComumNegocioException, ComumException {
        // Se o id do par�metro e a institui��o forem informados
        if (idParametro != -1 && idInstituicao > 0) {
            // Obt�m a quantidade de registros
            Long numeroRegistros = pesquisarNumeroRegistros(criterios, pe.getNomePesquisaNumRegistro(), false);

            // Se houver registro
            if (numeroRegistros > 0) {
                validarMaximoRegistros(idParametro, idInstituicao, numeroRegistros);
            } else {
                criterios.setTotalRegistros(numeroRegistros.intValue());
                criterios.setResultado(new ArrayList<E>());
                return criterios;
            }
        }

        return super.pesquisar(classe, criterios, pe.getNomePesquisa(), pe.getNomePesquisaNumRegistro());
    }

    /**
     * M�todo respons�vel por validar o m�ximo de registros permitidos na consulta.
     * 
     * @param idParametro
     * @param idInstituicao
     * @param numeroRegistros
     * @throws ComumException
     * @throws @throws ComumNegocioException void
     */
    private void validarMaximoRegistros(long idParametro, int idInstituicao, Long numeroRegistros) throws ComumException, ComumNegocioException {
        long maxRegistros = Long.valueOf(PARAMETRO_DAO.obterValor(idParametro, idInstituicao));

        if (numeroRegistros > maxRegistros) {
            throw new ComumNegocioException(PESQUISA_MAX_REGISTROS_CONSULTA, maxRegistros);
        }
    }

    /**
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public ComumCrudDao(Class<T> clazz, String nomeComandoPesquisar) {
        super("jdbc/BancoobDS", "sicoobdda.queries.xml", "comandos-sicoobdda-comum", clazz, nomeComandoPesquisar);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.persistencia.dao.BancoobDao#estabelecerConexao()
     */
    protected abstract Connection estabelecerConexao();

    /**
     * Passa a consulta com count da pesquisa devido a biblioteca n�o suportar consulta com construtor para a pagina��o provocando erro.
     */
    @SuppressWarnings("hiding")
    @Override
    public final <T> ConsultaDto<T> pesquisar(Class<T> classe, ConsultaDto<T> criterios, String nomePesquisa) {
        return super.pesquisar(classe, criterios, nomePesquisa, this.nomePesquisaNumeroRegistros);
    }

    /**
     * M�todo gen�rico de pesquisa.
     */
    public final <E extends Object> List<E> listar(final String nomeComando) throws ComumException {
        return listar(nomeComando, null);
    }

    /**
     * M�todo gen�rico de pesquisa.
     */
    public final <E extends Object> List<E> listar(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        return listar(nomeComando, parametros, null);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param nomeComando
     * @param parametros
     * @param maximoRegistros
     * @return
     * @throws ComumException List<E>
     * 
     */
    @SuppressWarnings("unchecked")
    public final <E extends Object> List<E> listar(final String nomeComando, Map<String, Object> parametros, Integer maximoRegistros) throws ComumException {
        getLogger().debug(SEPARADOR + " INICIO listar() " + SEPARADOR);

        Comando comando = null;
        List<E> lista = null;

        try {
            comando = getComando(nomeComando, parametros);

            Query query = comando.criarQuery(getEntityManager());
            if (maximoRegistros != null) {
                query.setMaxResults(maximoRegistros);
            }

            lista = query.getResultList();
        } catch (NoResultException e) {
            logNoResultException(nomeComando, parametros);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
        getLogger().debug(SEPARADOR + " FIM listar() " + SEPARADOR);
        return lista;
    }

    /**
     * M�todo gen�rico de pesquisa.
     */
    @SuppressWarnings("unchecked")
    public final <E extends Object> E obterDados(final String nomeComando) throws ComumException {
        return (E) obterDados(nomeComando, null, null, null);
    }

    /**
     * M�todo gen�rico de pesquisa.
     */
    @SuppressWarnings("unchecked")
    public final <E extends Object> E obterDados(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        return (E) obterDados(nomeComando, parametros, null, null);
    }

    /**
     * M�todo gen�rico retorna se existe registro.
     * 
     * @param nomeComando
     * @return
     * @throws ComumException boolean
     * 
     */
    public final boolean existeRegistro(final String nomeComando) throws ComumException {
        return existeRegistro(nomeComando, null);
    }

    /**
     * M�todo gen�rico retorna se existe registro.
     * 
     * @param nomeComando
     * @param parametros
     * @return
     * @throws ComumException Boolean
     * 
     */
    public final boolean existeRegistro(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        return !ObjectUtil.isEmpty((Long) obterDados(nomeComando, parametros));
    }

    /**
     * M�todo gen�rico, com SQL nativo, retorna se existe registro.
     * 
     * @param nomeComando
     * @return
     * @throws ComumException boolean
     * 
     */
    public final boolean existeRegistroNativo(final String nomeComando) throws ComumException {
        return existeRegistroNativo(nomeComando, null);
    }

    /**
     * M�todo gen�rico, com SQL nativo, retorna se existe registro.
     * 
     * @param nomeComando
     * @param parametros
     * @return
     * @throws ComumException Boolean
     * 
     */
    public final boolean existeRegistroNativo(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            comando = getComando(nomeComando, parametros);
            rs = comando.executarConsulta(conn);
            return rs.next();
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * M�todo gen�rico, COM SQL NATIVO, para obter COUNT.
     * 
     * @param nomeComando
     * @return
     * @throws ComumException long
     * 
     */
    public final long obterCount(final String nomeComando) throws ComumException {
        return obterCount(nomeComando, null);
    }

    /**
     * M�todo gen�rico, COM SQL NATIVO, para obter COUNT.
     * 
     * @param nomeComando
     * @param parametros
     * @return
     * @throws ComumException long
     * 
     */
    public final long obterCount(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            comando = getComando(nomeComando, parametros);
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return 0;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param nomecomando
     * @return
     * @throws ComumException List<Long>
     * 
     */
    public final List<Long> listarIdNativo(String nomecomando) throws ComumException {
        return listarIdNativo(nomecomando, null);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param nomecomando
     * @return
     * @throws ComumException List<Long>
     * 
     */
    public final List<Long> listarIdNativo(String nomecomando, Map<String, Object> parametros) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Long> listaId = new ArrayList<Long>();
        try {
            conn = estabelecerConexao();
            comando = getComando(nomecomando, parametros);
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaId.add(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaId;
    }

    /**
     * M�todo gen�rico de pesquisa.
     * 
     * @param nomeComando
     * @param parametros
     * @param primeiro
     * @param maximo
     */
    @SuppressWarnings("unchecked")
    public final <E extends Object> E obterDados(final String nomeComando, Map<String, Object> parametros, Integer primeiro, Integer maximo) throws ComumException {
        debug(SEPARADOR + " INICIO obterDados()" + SEPARADOR);

        Comando comando = null;
        E retorno = null;

        try {
            comando = getComando(nomeComando, parametros);

            Query query = comando.criarQuery(getEntityManager());

            if (!ObjectUtil.isEmpty(primeiro)) {
                query.setFirstResult(primeiro);
            }

            if (!ObjectUtil.isEmpty(maximo)) {
                query.setMaxResults(maximo);
            }

            retorno = (E) query.getSingleResult();
        } catch (NoResultException e) {
            logNoResultException(nomeComando, parametros);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }

        debug(SEPARADOR + " FIM obterDados() " + SEPARADOR);

        return retorno;
    }

    /**
     * M�todo gen�rico de update.
     * 
     * @param nomeComando
     * @throws ComumException
     */
    public final void executarUpdate(final String nomeComando) throws ComumException {
        executarUpdate(nomeComando, null);
    }

    /**
     * M�todo gen�rico de update.
     * 
     * @param nomeComando
     * @param parametros void
     * @throws ComumException
     */
    public final void executarUpdate(final String nomeComando, Map<String, Object> parametros) throws ComumException {
        executarUpdate(nomeComando, parametros, null);
    }

    /**
     * M�todo gen�rico de update.
     * 
     * @param nomeComando
     * @param parametros
     * @param numCooperativa
     * @throws ComumException
     */
    public final void executarUpdate(final String nomeComando, Map<String, Object> parametros, Integer numCooperativa) throws ComumException {
        Comando comando = null;
        Connection conn = null;

        try {
            comando = getComando(nomeComando, parametros);

            if (ObjectUtil.isEmpty(numCooperativa)) {
                debug("Estabelecendo conex�o...");
                conn = estabelecerConexao();
            } else {
                debug("Estabelecendo conex�o com a cooperativa: " + numCooperativa);
                conn = estabelecerConexao(numCooperativa);
            }

            debug("Executando atualiza��o...");
            comando.executarAtualizacao(conn);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharComando(comando);
        }
    }

    /**
     * M�todo respons�vel por executar UPDATE em lote
     * 
     * @param nomeComando
     * @param parametros
     * @throws ComumException void
     * 
     */
    public final void executarUpdateEmLote(final String nomeComando, List<Object[]> parametros) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        try {
            comando = getComando(nomeComando);
            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());

            conn = estabelecerConexao();
            comando.executarAtualizacaoEmLote(parametros, conn);
        } catch (PersistenceException e) {
            getLogger().erro(e, "FALHA AO EXECUTAR ATUALIZA��O EM LOTE! ");
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * M�todo respons�vel por
     * 
     * @param nomeComando
     * @param parametros
     * @return Comando
     * 
     */
    protected Comando getComando(final String nomeComando, Map<String, Object> parametros) {
        Comando comando = getComando(nomeComando);

        debug(SEPARADOR + " Nome do Comando:  " + nomeComando + SEPARADOR);

        if (!ObjectUtil.isEmpty(parametros)) {
            for (Entry<String, Object> parametro : parametros.entrySet()) {
                comando.adicionarVariavel(parametro.getKey(), parametro.getValue());

                debug(SEPARADOR + " parametro:  " + parametro.getKey() + " valor:  " + parametro.getValue() + SEPARADOR);
            }
        }

        comando.configurar();

        debug(SEPARADOR + " Comando:  " + comando.getSqlEmUso() + SEPARADOR);

        return comando;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param objeto
     * @param key
     * @return Map<String,Object>
     * 
     */
    protected Map<String, Object> getMapaParametro(Object objeto, String key) {
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put(key, objeto);
        return parametro;
    }

    /**
     * M�todo respons�vel por criar o log para o "noResultException"
     * 
     * @param nomeComando
     * @param parametros
     */
    private void logNoResultException(String nomeComando, Map<String, Object> parametros) {
        StringBuilder sb = new StringBuilder();

        sb.append("Nenhum resultado encontrado na execu��o do comando: \"");
        sb.append(nomeComando);

        if (!ObjectUtil.isNull(parametros)) {
            sb.append("\" com os par�metros");
            for (Entry<String, Object> parametro : parametros.entrySet()) {
                sb.append(" - ");
                sb.append(parametro.getKey());
                sb.append(": ");
                sb.append(parametro.getValue());
            }
        }
        getLogger().alerta(sb.toString());
    }

    /**
     * M�todo respons�vel por fechar o resultSet.
     * 
     * @param rs
     */
    public void fecharResultSet(ResultSet rs) {
        if (!ObjectUtil.isNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                getLogger().erro(e, e.getMessage());
            }
        }
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Byte
     * 
     */
    public Byte getNullableByte(String colName, ResultSet rs) throws SQLException {
        byte colValue = rs.getByte(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Short
     * 
     */
    public Short getNullableShort(String colName, ResultSet rs) throws SQLException {
        short colValue = rs.getShort(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colIndex
     * @param rs
     * @return
     * @throws SQLException Short
     * 
     */
    public Short getNullableShort(int colIndex, ResultSet rs) throws SQLException {
        short colValue = rs.getShort(colIndex);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Integer
     * 
     */
    public Integer getNullableInt(String colName, ResultSet rs) throws SQLException {
        int colValue = rs.getInt(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Long
     * 
     */
    public Long getNullableLong(String colName, ResultSet rs) throws SQLException {
        Long colValue = rs.getLong(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colIndex
     * @param rs
     * @return
     * @throws SQLException Long
     * 
     */
    public Long getNullableLong(int colIndex, ResultSet rs) throws SQLException {
        Long colValue = rs.getLong(colIndex);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Float
     * 
     */
    public Float getNullableFloat(String colName, ResultSet rs) throws SQLException {
        float colValue = rs.getFloat(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por retornar null caso o resultado do campo n�mero seja nulo. O ResultSet retorno "0" para campos n�mericos nulos.
     * 
     * @param colName
     * @param rs
     * @return
     * @throws SQLException Double
     * 
     */
    public Double getNullableDouble(String colName, ResultSet rs) throws SQLException {
        double colValue = rs.getDouble(colName);
        if (rs.wasNull()) {
            return null;
        }
        return colValue;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param date
     * @return DateTimeDB
     * 
     */
    protected DateTimeDB getDateTimeDB(Date date) {
        if (ObjectUtil.isNull(date)) {
            return null;
        }

        return new DateTimeDB(date.getTime());
    }
}
