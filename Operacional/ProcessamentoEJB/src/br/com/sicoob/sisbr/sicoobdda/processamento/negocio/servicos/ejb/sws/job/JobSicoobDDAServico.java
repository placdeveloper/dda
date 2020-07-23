/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         JobSicoobDDAServico.java
 * Data Cria��o:    Aug 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;

/**
 * JobSicoobDDAServico � respons�vel por
 * 
 * @author felipe.rosa
 */
public abstract class JobSicoobDDAServico extends JobSicoobServico {

    private ProcessamentoSWSDao dao;
    private ParametroDao parametroDao;

    protected static final String ERRO_VERIFICAR_DEPENDENCIA_PARAMETRO = "N�O FOI IDENTIFICADO O VALOR DO PAR�METRO DIN�MICO NECESS�RIO PARA EXECU��O DO JOB. ";

    /**
     * M�todo respons�vel por obter o DAO
     * 
     * @return
     */
    protected ParametroDao getParametroDao() {
        if (ObjectUtil.isNull(parametroDao)) {
            parametroDao = ComumDaoFactory.getInstance().criarParametroDao();
        }
        return parametroDao;
    }

    /**
     * @return ProcessamentoSWSDao
     * 
     */
    protected ProcessamentoSWSDao getProcessamentoSWSDao() {
        if (ObjectUtil.isNull(dao)) {
            dao = ProcessamentoDaoFactory.getInstance().criarProcessamentoSWSDao();
        }
        return dao;
    }

}
