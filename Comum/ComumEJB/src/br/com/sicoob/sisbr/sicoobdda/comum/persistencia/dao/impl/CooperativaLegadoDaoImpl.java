package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CooperativaLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * CooperativaLegadoDaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class CooperativaLegadoDaoImpl extends ComumCrudDao<SicoobDDAEntidade> implements CooperativaLegadoDao {

    private static final String ARQUIVO_QUERIES = "sicoobdda_comum.cooperativas.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-cooperativas";

    private ParametroDao parametroDAO = ComumDaoFactory.getInstance().criarParametroDao();

    public CooperativaLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao#estabelecerConexao()
     */
    @Override
    public Connection estabelecerConexao() {
        try {
            DataSource datasource = new CorporativoDataSource(NOME_DATA_SOURCE, System.getProperties());
            return datasource.getConnection();
        } catch (SQLException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new PersistenciaException(excecao);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CooperativaLegadoDao#listarCooperativa(java.lang.Integer)
     */
    public List<Integer> listarCooperativa(Integer numCooperativa) throws ComumException {
        debug("### Listar cooperativas do legado...");
        debug("### Parâmetro - numCooperativa: " + numCooperativa);

        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        List<Integer> listaCooperativa = new ArrayList<Integer>();

        try {
            comando = getComando("LISTAR_COOPERATIVAS_LEGADO");
            comando.adicionarVariavel("listaCooperativasIgnorar", listarCooperativasIgnorar());
            comando.configurar();

            debug(comando.getSqlEmUso());

            conn = estabelecerConexao(numCooperativa);
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaCooperativa.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (ComumException e) {
            throw e;
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return listaCooperativa;
    }

    /**
     * Método responsável por obter o parâmetro com a lista de cooperativas que devem ser ignoradas nas instituições para processamento.
     * 
     * @return
     * @throws ComumException
     */
    private String listarCooperativasIgnorar() throws ComumException {
        return parametroDAO.obterValor(ParametroDDA.LISTA_COOPERATIVAS_IGNORAR, Constantes.ID_SICOOB);
    }

}
