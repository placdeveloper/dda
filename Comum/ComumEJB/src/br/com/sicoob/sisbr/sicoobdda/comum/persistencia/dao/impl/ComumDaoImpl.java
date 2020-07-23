/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         ComumDaoImpl.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumDaoImpl é responsável por
 * 
 * @author Francisco.Marcio
 */
public class ComumDaoImpl extends ComumCrudDaoDB2<SicoobDDAEntidade> implements ComumDao {

    private static final String OBTER_DATA_HORA_DATA_BASE = "OBTER_DATA_HORA_DATA_BASE";

    public static final String ARQUIVO_QUERIES = "sicoobdda.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-sicoobdda";

    public ComumDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao#obterDataHoraDataBase()
     */
    public DateTimeDB obterDataHoraDataBase() {
        Comando comando = null;
        Timestamp retorno = null;
        Connection conn = null;
        ResultSet rs = null;
        getLogger().debug("******obterDataHoraDataBase******");
        try {
            comando = getComando(OBTER_DATA_HORA_DATA_BASE);

            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                retorno = rs.getTimestamp(1);
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return getDateTimeDB(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param data
     * @return DateTimeDB
     * 
     */
    private static DateTimeDB getDateTimeDB(Timestamp data) {
        if (data != null) {
            return new DateTimeDB(data.getTime());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao#obterDataReferenciaGradeAberta(java.lang.String)
     */
    public DateTimeDB obterDataReferenciaGradeAberta(String codTipoMensagem) throws ComumException {
        getLogger().debug("*******INICIO obterDataReferenciaGradeAberta(String codTipoMensagem)*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        Date dataMovimento = null;

        try {
            comando = getComando("OBTER_DATA_REFERENCIA_GRADE_ABERTA");
            comando.adicionarVariavel("codTipoMensagem", codTipoMensagem);
            comando.configurar();
            getLogger().debug("codTipoMensagem = " + codTipoMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                dataMovimento = rs.getDate(1);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return DateUtil.getDateTimeDB(dataMovimento);
    }
}
