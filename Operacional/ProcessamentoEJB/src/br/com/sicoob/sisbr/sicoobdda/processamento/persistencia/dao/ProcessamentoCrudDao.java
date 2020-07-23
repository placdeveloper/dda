package br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema Processamento
 * 
 * @author Sicoob
 */
public abstract class ProcessamentoCrudDao<T extends SicoobDDAEntidade> extends ComumCrudDao<T> implements ProcessamentoCrudDaoIF<T> {

    /**
     * @param nomeDataSource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ProcessamentoCrudDao(String nomeDataSource, String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar,
            String nomeComandoPesquisaNumeroRegistros) {
        super(nomeDataSource, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar, nomeComandoPesquisaNumeroRegistros);
    }

    /**
     * Estabelece a conexao com o banco de dados.
     * 
     * @return a conexao com o banco de dados.
     */
    protected Connection estabelecerConexao() {
        try {
            BancoobDataSource datasource = new BancoobDataSource(getNomeDatasource(), System.getProperties());

            return datasource.getConnection();
        } catch (SQLException excecao) {
            throw new PersistenciaException(excecao);
        }
    }
}