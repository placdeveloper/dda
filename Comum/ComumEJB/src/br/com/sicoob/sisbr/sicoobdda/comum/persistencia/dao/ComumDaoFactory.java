/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumDaoFactory.java
 * Data Cria��o:    26/09/2012
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
 * ComumDaoFactory � respons�vel por
 * 
 * @author Fabricio.Brandao
 */
public class ComumDaoFactory extends BancoobDaoFactory {

    private static ComumDaoFactory instance = null;

    /**
     * M�todo respons�vel por getInstance
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
     * Metodo respons�vel pela recupera��o da DAO.
     */
    public ParametroDao criarParametroDao() {
        return new ParametroDaoImpl();
    }

    /**
     * M�todo respons�vel por recuperar a DAO.
     * 
     * @return ParametroLegadoDao
     */
    public ParametroLegadoDao criarParametroLegadoDao() {
        return new ParametroLegadoDaoImpl();
    }

    /**
     * M�todo respons�vel pela recupera��o do DAO de Institui��o.
     * 
     * @return
     */
    public InstituicaoDao criarInstituicaoDao() {
        return new InstituicaoDaoImpl();
    }

    /**
     * M�todo respons�vel pela recupera��o do ComumDao
     * 
     * @return
     */
    public ComumDao criarComumDao() {
        return new ComumDaoImpl();
    }

    /**
     * M�todo respons�vel por obter o DAO
     * 
     * @return
     */
    public CooperativaLegadoDao criarCooperativaLegadoDao() {
        return new CooperativaLegadoDaoImpl();
    }

    /**
     * M�todo respons�vel por CapesDao
     * 
     * @return CapesDao
     * 
     */
    public CapesDao criarCapesDao() {
        return new CapesDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return RequisicaoCacheDao
     * 
     */
    public RequisicaoCacheDao criarRequisicaoCacheDao() {
        return new RequisicaoCacheDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return AtualizacaoCacheDao
     * 
     */
    public AtualizacaoCacheDao criarAtualizacaoCacheDao() {
        return new AtualizacaoCacheDaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ServidorDDADao
     * 
     */
    public ServidorDDADao criarServidorDDADao() {
        return new ServidorDDADaoImpl();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return DominioDDADao
     * 
     */
    public DominioDDADao criarDominioDDADao() {
        return new DominioDDADaoImpl();
    }
}
