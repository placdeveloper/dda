/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         BloquearEnvioMensagensJobServicoEJB.java
 * Data Criação:    Jun 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.JobSicoobServico;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * BloquearEnvioMensagensJobServicoEJB
 * 
 * @author rafael.silva
 */
@Stateless
@Remote(JobServico.class)
public class BloquearEnvioMensagensJobServicoEJB extends JobSicoobServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        try {
            if (motorEnvioMensagensEmExecucao()) {
                return finalizarFluxo("Existe uma execução do motor de envio de mensagens não finalizada.");
            } else {
                // Atualiza o parametro no próprio JOB pois é preciso realizar um lock no registro do parametro durante o select liberando apenas após a
                // atualização. Caso a atualização fosse realizada no STEP a transação seria outra, liberando o lock do registro.
                getParametroDao().atualizarValorParametro(ParametroDDA.MOTOR_ENVIO_MSGS_EM_EXECUCAO, Constantes.ID_SICOOB, Constantes.STRING_NUMERO_1);
            }
        } catch (ComumException e) {
            return erro(e.getMessage());
        }
        return sucesso();
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
    private Boolean motorEnvioMensagensEmExecucao() throws ComumException {
        return getParametroDao().obterValorBooleanLockRegistro(ParametroDDA.MOTOR_ENVIO_MSGS_EM_EXECUCAO, Constantes.ID_SICOOB, Boolean.TRUE);
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
