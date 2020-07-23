/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         BloquearMotorCargaJobServicoEJB.java
 * Data Criação:    Ago 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaCargaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * BloquearMotorCargaJobServicoEJB
 * 
 * @author rafael.silva
 */
@Stateless
@Remote(JobServico.class)
public class BloquearMotorCargaJobServicoEJB extends JobSicoobServico {
    protected static final String ERRO_VERIFICAR_DEPENDENCIA_PARAMETRO = "NÃO FOI IDENTIFICADO O VALOR DO PARÂMETRO DINÂMICO NECESSÁRIO PARA EXECUÇÃO DO JOB. ";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        try {
            String valorParametro = (String) contexto.getParametroDinamico().getValor();
            if (!ObjectUtil.isEmpty(valorParametro)) {
                return motorCargaEmExecucao(TipoInstanciaCargaEnum.valueOf(valorParametro).getIdParametroMotorCarga());
            } else {
                return erro(ERRO_VERIFICAR_DEPENDENCIA_PARAMETRO);
            }
        } catch (ComumException e) {
            return erro(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        return new ArrayList<Step>();
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException
     */
    private VerificacaoDependencias motorCargaEmExecucao(long idParametroDDA) throws ComumException {
        if (getParametroDao().obterValorBooleanLockRegistro(idParametroDDA, Constantes.ID_SICOOB, Boolean.TRUE)) {
            return finalizarFluxo("Existe uma execução do motor não finalizada.");
        } else {
            getParametroDao().atualizarValorParametro(idParametroDDA, Constantes.ID_SICOOB, Constantes.STRING_NUMERO_1);
            return sucesso();
        }

    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    private ParametroDao getParametroDao() {
        return ComumDaoFactory.getInstance().criarParametroDao();
    }

}
