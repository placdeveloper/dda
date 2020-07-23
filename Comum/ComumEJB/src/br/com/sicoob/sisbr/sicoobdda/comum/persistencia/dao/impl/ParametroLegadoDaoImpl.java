package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ParametroNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * ParametroLegadoDaoImpl é responsável por
 * 
 * @author George.Santos
 */
public class ParametroLegadoDaoImpl extends ComumCrudDao<SicoobDDAEntidade> implements ParametroLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.parametrolegado.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-parametrolegado";

    /**
     * Construtor
     */
    public ParametroLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.persistencia.dao.ComumCrudDao#estabelecerConexao()
     */
    @Override
    public Connection estabelecerConexao() {
        try {
            BancoobDataSource datasource = new BancoobDataSource(getNomeDatasource(), System.getProperties());
            return datasource.getConnection();
        } catch (SQLException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new PersistenciaException(excecao);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.persistencia.dao.ParametroLegadoDao#obterValor(java.lang.Integer, java.lang.Integer)
     */
    public String obterValor(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException {
        return obterValorParametro(idParametro, numCooperativa, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.persistencia.dao.ParametroLegadoDao#obterValorSegundo(java.lang.Integer, java.lang.Integer)
     */
    public String obterValorSegundo(Long idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException {
        return obterValorParametro(idParametro.intValue(), numCooperativa, Boolean.TRUE);
    }

    /**
     * Método responsável por obter o valor do parâmetro
     * 
     * @param idParametro
     * @param numCooperativa
     * @param bolValorParSegundo
     * @return String
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    private String obterValorParametro(Integer idParametro, Integer numCooperativa, Boolean bolValorParSegundo) throws ComumException, ComumNegocioException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        final String sql = "SELECT p.ValorPar, p.ValorParSegundo FROM ValorParametro p WHERE idParametro = ?";

        try {
            conn = estabelecerConexao(numCooperativa);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idParametro);

            rs = ps.executeQuery();

            if (rs.next()) {
                if (bolValorParSegundo) {
                    return (rs.getString("ValorParSegundo"));
                } else {
                    return (rs.getString("valorPar"));
                }

            } else {
                // A exceção faz rollback da transação
                throw new ParametroNaoEncontradoException(idParametro, numCooperativa);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.persistencia.dao.ParametroLegadoDao#obterValorBoolean(java.lang.Integer, java.lang.Integer)
     */
    public Boolean obterValorBoolean(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException {
        return obterValor(idParametro, numCooperativa).equals(Constantes.STRING_NUMERO_1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.persistencia.dao.ParametroLegadoDao#obterValorDouble(java.lang.Integer, java.lang.Integer)
     */
    public Double obterValorDouble(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException {
        return Double.parseDouble(obterValor(idParametro, numCooperativa));
    }

}
