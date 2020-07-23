/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumDaoFactory.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.AtualizacaoCacheDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.CapesDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.ComumDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.CooperativaLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.DominioDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.InstituicaoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.ParametroDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.ParametroLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.RequisicaoCacheDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl.ServidorDDADaoImpl;

/**
 * ComumDaoFactory é responsável por
 * 
 * @author Fabricio.Brandao
 */
public class ComumDaoFactory extends BancoobDaoFactory {

    private static ComumDaoFactory instance = null;

    /**
     * Método responsável por getInstance
     * 
     * @return ComumDaoFactory
     * 
     */
    public static ComumDaoFactory getInstance() {
        if (instance == null) {
            instance = new ComumDaoFactory();
        }
        return instance;
    }

    /**
     * Construtor
     */
    protected ComumDaoFactory() {
    }

    /**
     * Metodo responsável pela recuperação da DAO.
     */
    public ParametroDao criarParametroDao() {
        return new ParametroDaoImpl();
    }

    /**
     * Método responsável por recuperar a DAO.
     * 
     * @return ParametroLegadoDao
     */
    public ParametroLegadoDao criarParametroLegadoDao() {
        return new ParametroLegadoDaoImpl();
    }

    /**
     * Método responsável pela recuperação do DAO de Instituição.
     * 
     * @return
     */
    public InstituicaoDao criarInstituicaoDao() {
        return new InstituicaoDaoImpl();
    }

    /**
     * Método responsável pela recuperação do ComumDao
     * 
     * @return
     */
    public ComumDao criarComumDao() {
        return new ComumDaoImpl();
    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    public CooperativaLegadoDao criarCooperativaLegadoDao() {
        return new CooperativaLegadoDaoImpl();
    }

    /**
     * Método responsável por CapesDao
     * 
     * @return CapesDao
     * 
     */
    public CapesDao criarCapesDao() {
        return new CapesDaoImpl();
    }

    /**
     * Método responsável por
     * 
     * @return RequisicaoCacheDao
     * 
     */
    public RequisicaoCacheDao criarRequisicaoCacheDao() {
        return new RequisicaoCacheDaoImpl();
    }

    /**
     * Método responsável por
     * 
     * @return AtualizacaoCacheDao
     * 
     */
    public AtualizacaoCacheDao criarAtualizacaoCacheDao() {
        return new AtualizacaoCacheDaoImpl();
    }

    /**
     * Método responsável por
     * 
     * @return ServidorDDADao
     * 
     */
    public ServidorDDADao criarServidorDDADao() {
        return new ServidorDDADaoImpl();
    }

    /**
     * Método responsável por
     * 
     * @return DominioDDADao
     * 
     */
    public DominioDDADao criarDominioDDADao() {
        return new DominioDDADaoImpl();
    }
}
