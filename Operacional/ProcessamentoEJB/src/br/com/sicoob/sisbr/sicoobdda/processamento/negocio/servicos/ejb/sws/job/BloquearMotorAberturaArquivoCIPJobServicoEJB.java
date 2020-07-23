package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * BloquearMotorAberturaArquivoCIPJobServicoEJB é responsável por bloquear a instancia de processamento conforme o parametro passado para a execução do JOB
 * (PAG, BOL, BAX, RET) evitando que outros agendamentos iniciem o processamento em concorrencia.
 * 
 * Utilizado nos JOBS - Abrir/Descriptografar - Gravar Detalhes de Arquivo - Atualizar Situacao Arquivos para "Arquivo Processado"
 */
@Stateless
@Remote(JobServico.class)
public class BloquearMotorAberturaArquivoCIPJobServicoEJB extends MotorArquivoUtilServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        try {
            String valorParametro = (String) contexto.getParametroDinamico().getValor();
            if (!ObjectUtil.isEmpty(valorParametro)) {
                return bloquearMotorProcessamentoArquivo(TipoInstanciaProcessamentoEnum.valueOf(valorParametro).getIdParametroMotorAbertura());
            } else {
                return erro(ERRO_VERIFICAR_DEPENDENCIA_PARAMETRO);
            }
        } catch (ComumException e) {
            return erro("FALHA NO BLOQUEIO DO MOTOR DE ABERTURA DE ARQUIVOS MOTIVO:[" + e.getMessage() + "]");
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

}
