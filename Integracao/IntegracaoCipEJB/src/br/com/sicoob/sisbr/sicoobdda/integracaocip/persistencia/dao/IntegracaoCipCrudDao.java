package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipCrudDao<T extends SicoobDDAEntidade> extends ComumCrudDao<T> implements IntegracaoCipCrudDaoIF<T> {
    private static final String MSG_ERRO_LOCK = "integracaocip.erro.locar.regitro.update";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public IntegracaoCipCrudDao(String arquivoQueries, String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar) {
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

    /**
     * Realiza o lock do registro, equivalente ao updlock e rowlock no SQL Server
     * 
     * @return
     */
    protected void locarRegistroUpdate(SicoobDDAEntidade objeto) throws BancoobException {
        try {
            if (ObjectUtil.isNull(getEntityManager())) {
                throw new BancoobException(MSG_ERRO_LOCK);
            }
            Session session = (Session) getEntityManager().getDelegate();

            if (ObjectUtil.isNull(session)) {
                throw new BancoobException(MSG_ERRO_LOCK);
            }
            session.lock(objeto, LockMode.UPGRADE);

        } catch (HibernateException excecao) {
            getLogger().erro(excecao, excecao.getMessage());
            throw new BancoobException(MSG_ERRO_LOCK, excecao);
        }
    }

}