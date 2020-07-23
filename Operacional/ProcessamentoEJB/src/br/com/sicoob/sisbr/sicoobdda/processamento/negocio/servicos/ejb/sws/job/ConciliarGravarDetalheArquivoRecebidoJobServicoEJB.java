/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         ConciliarGravarDetalheArquivoRecebidoJobServicoEJB.java
 * Data Criação:    Jan 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * ConciliarGravarDetalheArquivoRecebidoJobServicoEJB
 * 
 * @author Adriano.Pinheiro
 */
@Stateless
@Remote(JobServico.class)
public class ConciliarGravarDetalheArquivoRecebidoJobServicoEJB extends MotorArquivoUtilServico {

    private static final Integer TIME_OUT = 180;
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/ConciliarGravarDetalheArquivoRecebidoStepServicoRemote";
    private static final String NOME_JOB = "ConciliarGravarDetalhe";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        String parametro = contexto.getParametroDinamico().getValor();
        return verificarDependenciasJobMotor(parametro, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO, NOME_JOB, getLogger());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        List<Step> listaProcessadores = new ArrayList<Step>();
        TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf((String) contexto.getParametroDinamico().getValor());

        List<Long> listaIdArquivo = listarIdArquivoParaProcessamento(instanciaSWS, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO, NOME_JOB, getLogger());
        for (Long idArquivo : listaIdArquivo) {
            Parametro prArquivoRec = new Parametro("idArquivoRecebido", idArquivo, TipoParametro.LONGO);
            listaProcessadores.add(ejb(JNDI_STEP_EJB).comTimeout(TIME_OUT).comParametros(prArquivoRec));
        }

        return listaProcessadores;
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(ConciliarGravarDetalheArquivoRecebidoJobServicoEJB.class);
    }

}
