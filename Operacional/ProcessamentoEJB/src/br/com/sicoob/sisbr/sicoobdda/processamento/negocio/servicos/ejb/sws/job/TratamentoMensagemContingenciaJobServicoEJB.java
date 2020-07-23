/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         TratamentoMensagemContingenciaJobServicoEJB.java
 * Data Criação:    06/10/2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemContingenciaDto;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * TratamentoMensagemContingenciaJobServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Remote(JobServico.class)
public class TratamentoMensagemContingenciaJobServicoEJB extends JobSicoobDDAServico {

    private static final String JNDI_STEP = "sicoobdda_processamento/TratamentoMensagemContingenciaStepServicoRemote";
    private static final int TIME_OUT = 300; // 5 min

    private static final String NOME_JOB = "TratamentoMensagemContingencia";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        return sucesso();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        List<Step> listaSteps = new ArrayList<Step>();

        List<MensagemContingenciaDto> listaMensagemContingencia = listarParametroStepContingenciaMensagem();
        for (MensagemContingenciaDto mensagemContingencia : listaMensagemContingencia) {

            Parametro dataMovimento = new Parametro("dataMovimento", mensagemContingencia.getDataMovimento(), TipoParametro.DATA);
            Parametro codTipoMensagem = new Parametro("codTipoMensagem", mensagemContingencia.getCodTipoMensagem(), TipoParametro.TEXTO);
            Parametro idMensagemInicial = new Parametro("idMensagemInicial", mensagemContingencia.getIdMensagemInicial(), TipoParametro.LONGO);
            Parametro idMensagemFinal = new Parametro("idMensagemFinal", mensagemContingencia.getIdMensagemFinal(), TipoParametro.LONGO);
            Parametro qtdRegistro = new Parametro("qtdRegistro", mensagemContingencia.getQtdRegistros(), TipoParametro.INTEIRO);

            listaSteps.add(ejb(JNDI_STEP).comTimeout(TIME_OUT).comParametros(dataMovimento, codTipoMensagem, idMensagemInicial, idMensagemFinal, qtdRegistro));
        }

        return listaSteps;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemContingenciaDto>
     * 
     */
    private List<MensagemContingenciaDto> listarParametroStepContingenciaMensagem() {
        try {
            return getProcessamentoSWSDao().listarParametroStepContingenciaMensagem();
        } catch (ComumException e) {
            getLogger().erro(e, MensagemUtil.getString("erro.job.obter.steps", NOME_JOB, e.getMessage()));
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", NOME_JOB, e.getMessage()), e);
        }
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(TratamentoMensagemContingenciaJobServicoEJB.class);
    }

}
