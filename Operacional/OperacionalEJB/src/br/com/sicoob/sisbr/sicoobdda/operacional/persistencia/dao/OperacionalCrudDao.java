package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class OperacionalCrudDao<T extends SicoobDDAEntidade> extends ComumCrudDao<T> implements OperacionalCrudDaoIF<T> {

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public OperacionalCrudDao(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar) {
        super(arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar);
    }

    /**
     * Estabelece a conexao com o banco de dados SQL Server
     * 
     * @return a conexao com o banco de dados SQL Server
     */
    @Override
    protected Connection estabelecerConexao() {
        try {
            BancoobDataSource datasource = new BancoobDataSource(getNomeDatasource(), System.getProperties());

            return datasource.getConnection();
        } catch (SQLException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new PersistenciaException(excecao);
        }
    }
}