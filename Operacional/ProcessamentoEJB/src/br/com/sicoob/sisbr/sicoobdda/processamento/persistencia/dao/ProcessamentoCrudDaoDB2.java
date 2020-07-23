package br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema Processamento
 * 
 * @author Sicoob
 */
public abstract class ProcessamentoCrudDaoDB2<T extends SicoobDDAEntidade> extends ComumCrudDaoDB2<T> implements ProcessamentoCrudDaoIF<T> {

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ProcessamentoCrudDaoDB2(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar, String nomeComandoPesquisaNumeroRegistros) {
        super(arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar, nomeComandoPesquisaNumeroRegistros);
    }

    /**
     * Estabelece a conexao com o banco de dados.
     * 
     * @return a conexao com o banco de dados.
     */
    protected Connection estabelecerConexao() {
        try {
            CorporativoDataSource dataSource = new CorporativoDataSource(NOME_DATA_SOURCE, System.getProperties());

            return dataSource.getConnection();
        } catch (SQLException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new PersistenciaException(excecao);
        }
    }
}