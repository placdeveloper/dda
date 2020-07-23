package br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.impl.ProcessamentoSWSDaoImpl;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema TrocaArquivoMovimento
 * 
 */
public class ProcessamentoDaoFactory extends ComumDaoFactory {

    /** Instancia do DAOFactory. */
    private static ProcessamentoDaoFactory factory;

    /**
     * Retorna a fabrica de DAOs a ser utilizada.
     * 
     * @return a fabrica de DAOs a ser utilizada.
     */
    public static ProcessamentoDaoFactory getInstance() {
        if (factory == null) {
            factory = new ProcessamentoDaoFactory();
        }
        return factory;
    }

    /**
     * Construtor privado no-args da classe
     */
    protected ProcessamentoDaoFactory() {
    }

    /**
     * @return ProcessamentoSWSDao
     */
    public ProcessamentoSWSDao criarProcessamentoSWSDao() {
        return new ProcessamentoSWSDaoImpl();
    }
}