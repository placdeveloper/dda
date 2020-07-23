/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         ServidorDDADaoImpl.java
 * Data Criação:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDADaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ServidorDDADaoImpl extends ComumCrudDaoDB2<ServidorDDA> implements ServidorDDADao {

    private static final String LISTAR_SERVIDOR = "LISTAR_SERVIDOR";

    public static final String ARQUIVO_QUERIES = "sicoobdda_comum.servidordda.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-servidordda";

    /**
     * 
     */
    public ServidorDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, ServidorDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao#obterServidor(java.lang.String)
     */
    @Override
    public ServidorDDA obterServidor(String codServidorDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            comando = getComando("OBTER_SERVIDOR_DDA");
            comando.adicionarVariavel("codServidorDDA", codServidorDDA);
            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                return populaServidor(rs);
            }
        } catch (SQLException e) {
            throw new ComumException("servidordda.dao.erro.obter", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return null;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException ServidorDDA
     * 
     */
    private ServidorDDA populaServidor(ResultSet rs) throws SQLException {
        return new ServidorDDA(rs.getString("CODSERVIDORDDA"), rs.getString("DESCPERFIL"), getDateTimeDB(rs.getDate("DATAHORACADASTRO")), rs.getBoolean("BOLATIVO"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao#listarAtivo()
     */
    @Override
    public List<ServidorDDA> listarAtivo() throws ComumException {
        return listar(LISTAR_SERVIDOR, getMapaParametro(Boolean.TRUE, "bolAtivo"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao#listarServidorDDA()
     */
    @Override
    public List<ServidorDDA> listarServidorDDA() throws ComumException {
        return listar(LISTAR_SERVIDOR);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao#listarServidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public List<ServidorDDA> listarServidorDDA(ServidorDDA filtro) throws ComumException {
        return listar(LISTAR_SERVIDOR, montarMapaParametro(filtro.getCodServidorDDA(), filtro.getDescPerfil(), filtro.getBolAtivo()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ServidorDDADao#alterarSevidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public void alterarSevidorDDA(ServidorDDA servidorDDA) throws ComumException {
        executarUpdate("ALTERAR_SERVIDOR", montarMapaParametro(servidorDDA.getCodServidorDDA(), servidorDDA.getDescPerfil(), servidorDDA.getBolAtivo()));
    }

    /**
     * Método responsável por
     * 
     * @param codServidorDDA
     * @param descPerfil
     * @param bolAtivo
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> montarMapaParametro(String codServidorDDA, String descPerfil, Boolean bolAtivo) {
        Map<String, Object> parametros = getMapaParametro(codServidorDDA, "codServidorDDA");
        parametros.put("descPerfil", descPerfil);
        parametros.put("bolAtivo", bolAtivo);
        return parametros;
    }
}
