/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws
 * Arquivo:         DesbloquearMotorCargaJobServicoEJB.java
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
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * DesbloquearMotorCargaJobServicoEJB
 * 
 * @author rafael.silva
 */
@Stateless
@Remote(JobServico.class)
public class DesbloquearMotorCargaJobServicoEJB extends MotorArquivoUtilServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        try {
            String valorParametro = contexto.getParametroDinamico().getValor();
            if (!ObjectUtil.isEmpty(valorParametro)) {
                return desbloquearMotorProcessamentoArquivo(TipoInstanciaCargaEnum.valueOf(valorParametro).getIdParametroMotorCarga());
            } else {
                return erro(ERRO_VERIFICAR_DEPENDENCIA_PARAMETRO);
            }
        } catch (ComumException e) {
            return erro("FALHA NO DESBLOQUEIO DO MOTOR DE CARGA DE ARQUIVOS MOTIVO:[" + e.getMessage() + "]");
        }
    }


    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        return new ArrayList<>();
    }

}
