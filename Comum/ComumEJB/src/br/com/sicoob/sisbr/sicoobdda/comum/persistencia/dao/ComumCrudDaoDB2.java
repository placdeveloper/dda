/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumCrudDaoDB2.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumCrudDaoDB2
 * 
 * @author Rodrigo.Neri
 */
public abstract class ComumCrudDaoDB2<T extends SicoobDDAEntidade> extends ComumCrudDao<T> implements ComumCrudDaoIF<T> {

    public static final String NOME_DATA_SOURCE = "jdbc/sicoobdda_comumDS";

    /**
     * Construtor
     * 
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ComumCrudDaoDB2(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar, String nomeComandoPesquisaNumeroRegistros) {
        super(NOME_DATA_SOURCE, arquivoQueries, nomeColecaoComandos, clazz, nomeComandoPesquisar, nomeComandoPesquisaNumeroRegistros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao#estabelecerConexao()
     */
    @Override
    protected Connection estabelecerConexao() {
        try {
            CorporativoDataSource dataSource = new CorporativoDataSource(NOME_DATA_SOURCE, System.getProperties());

            return dataSource.getConnection();
        } catch (SQLException excecao) {
            throw new PersistenciaException(excecao);
        }
    }

}
