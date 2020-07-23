package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * AtualizarArquivosProcessadosCIPJobServicoEJB
 */
@Stateless
@Remote(JobServico.class)
public class AtualizarArquivosProcessadosCIPJobServicoEJB extends MotorArquivoUtilServico {

    static final Integer TIME_OUT = 180;
    static final String JNDI_STEP_EJB = "sicoobdda_processamento/AtualizarSituacaoProcessamentoArquivoCIPStepServicoRemote";

    private static final String NOME_JOB = "AtualizarSituacaoArquivo";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        String parametro = contexto.getParametroDinamico().getValor();
        if (ObjectUtil.isEmpty(parametro)) {
            return erro("PARAMETRO DIN�MICO N�O IDENTIFICADO!");
        }

        TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf(parametro);

        try {
            if (getProcessamentoSWSDao().existeArquivoDisponivel(SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO, instanciaSWS.getTiposArquivo(),
                    instanciaSWS.getTiposDeMensagens())) {
                return sucesso();
            } else {
                return finalizarJob("N�o existem arquivos processados dispon�veis para finalizar!");
            }
        } catch (ComumException e) {
            return erro(MensagemUtil.getString("erro.verificacao.dependencia.job", NOME_JOB, e.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        List<Step> listaProcessadores = new ArrayList<Step>();
        TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf((String) contexto.getParametroDinamico().getValor());

        List<Long> listaArquivo = listarIdArquivosProcessados(instanciaSWS);
        for (Long idArquivo : listaArquivo) {
            Parametro idArquivoRecebido = new Parametro("idArquivoRecebido", idArquivo, TipoParametro.LONGO);
            Parametro codSituacaoProcessamentoArquivo = new Parametro("codSituacaoProcessamentoArquivo", SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO, TipoParametro.SHORT);
            listaProcessadores.add(ejb(JNDI_STEP_EJB).comTimeout(TIME_OUT).comParametros(idArquivoRecebido, codSituacaoProcessamentoArquivo));
        }
        return listaProcessadores;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param instanciaSWS
     * @return ArquivoProcessamentoVO
     * 
     */
    private List<Long> listarIdArquivosProcessados(TipoInstanciaProcessamentoEnum instanciaSWS) {
        try {
            int qtdMaximaRegistros = obterQtdMaximaRegsitros(SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO, getLogger());
            return getProcessamentoSWSDao().listarIdArquivosEmSituacao(SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO, instanciaSWS.getTiposArquivo(),
                    instanciaSWS.getTiposDeMensagens(), qtdMaximaRegistros);
        } catch (ComumException e) {
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", "Atualizar Arquivo Processado", e.getMessage()), e);
        }
    }

    /**
     * M�todo respons�vel por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(AtualizarArquivosProcessadosCIPJobServicoEJB.class);
    }
}
