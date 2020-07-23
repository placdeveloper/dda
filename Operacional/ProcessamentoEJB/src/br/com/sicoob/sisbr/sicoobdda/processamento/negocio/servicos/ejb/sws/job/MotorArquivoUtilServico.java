/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job
 * Arquivo:         MotorRecebimentoArquivoUtilServico.java
 * Data Criação:    Jun 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.List;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;

/**
 * MotorRecebimentoArquivoUtilServico
 * 
 * @author rafael.silva
 */
public abstract class MotorArquivoUtilServico extends JobSicoobDDAServico {

    /**
     * Método responsável por verificar se existe algum arquivo para porcessar.
     * 
     * @param parametro
     * @param codSituacaoProcessamentoArquivo
     * @param nomeJob
     * @param log
     * @return VerificacaoDependencias
     * 
     */
    protected VerificacaoDependencias verificarDependenciasJobMotor(String parametro, short codSituacaoProcessamentoArquivo, String nomeJob, SicoobLoggerPadrao log) {
        try {
            if (ObjectUtil.isEmpty(parametro)) {
                return erro("PARAMETRO DINÂMICO NÃO IDENTIFICADO!");
            }

            TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf(parametro);

            if (getProcessamentoSWSDao().existeArquivoDisponivel(codSituacaoProcessamentoArquivo, instanciaSWS.getTiposArquivo(), instanciaSWS.getTiposDeMensagens())) {
                return sucesso();
            } else {
                return finalizarJob("Não existem arquivos disponíveis com a situação [" + codSituacaoProcessamentoArquivo + "] para processamento!");
            }
        } catch (ComumException e) {
            return erro(MensagemUtil.getString("erro.verificacao.dependencia.job", nomeJob, e.getMessage()));
        }
    }

    /**
     * Método responsável por buscar o arquivo com situação (@param codSituacaoProcessamentoArquivo) para processamento para esta instancia
     * 
     * Método responsável por
     * 
     * @param instanciaSWS
     * @param codSituacaoProcessamentoArquivo
     * @param nomeJob
     * @param log
     * @return ArquivoParaProc
     * 
     */
    protected List<ArquivoProcessamentoVO> listarArquivoParaProcessamento(TipoInstanciaProcessamentoEnum instanciaSWS, short codSituacaoProcessamentoArquivo, String nomeJob,
            SicoobLoggerPadrao log) {
        try {
            int qtdMaximaRegistros = obterQtdMaximaRegsitros(codSituacaoProcessamentoArquivo, log);
            return getProcessamentoSWSDao().listarArquivoDaSequenciaParaProcessamento(codSituacaoProcessamentoArquivo, instanciaSWS.getTiposArquivo(), instanciaSWS.getTiposDeMensagens(), qtdMaximaRegistros);
        } catch (ComumException e) {
            log.erro(e, MensagemUtil.getString("erro.job.obter.steps", nomeJob, e.getMessage()));
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", nomeJob, e.getMessage()), e);
        }
    }

    /**
     * Método responsável por
     * 
     * @param instanciaSWS
     * @param codSituacaoProcessamentoArquivo
     * @param nomeJob
     * @param log
     * @return List<Long>
     * 
     */
    protected List<Long> listarIdArquivoParaProcessamento(TipoInstanciaProcessamentoEnum instanciaSWS, short codSituacaoProcessamentoArquivo, String nomeJob, SicoobLoggerPadrao log) {
        try {
            int qtdMaximaRegistros = obterQtdMaximaRegsitros(codSituacaoProcessamentoArquivo, log);
            return getProcessamentoSWSDao().listarIdArquivoDaSequenciaParaProcessamento(codSituacaoProcessamentoArquivo, instanciaSWS.getTiposArquivo(), instanciaSWS.getTiposDeMensagens(), qtdMaximaRegistros);
        } catch (ComumException e) {
            log.erro(e, MensagemUtil.getString("erro.job.obter.steps", nomeJob, e.getMessage()));
            throw new BancoobRuntimeException(MensagemUtil.getString("erro.job.obter.steps", nomeJob, e.getMessage()), e);
        }
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoProcessamentoArquivo
     * @param log
     * @return
     * @throws ComumException int
     * 
     */
    protected int obterQtdMaximaRegsitros(short codSituacaoProcessamentoArquivo, SicoobLoggerPadrao log) throws ComumException {
        log.debug("###### Recuperando arquivo com situação " + codSituacaoProcessamentoArquivo + " disponível.");
        int qtdMaximaRegistros = getParametroDao().obterValorInteger(ParametroDDA.QTD_MAXIMA_PROCESSAMENTO_ARQUIVOS, Constantes.ID_SICOOB);
        log.debug("###### Qtd máxima de registros:  " + qtdMaximaRegistros);
        return qtdMaximaRegistros;
    }

    /**
     * @param idParametro
     * @return
     * @throws ComumException VerificacaoDependencias
     * 
     */
    protected VerificacaoDependencias bloquearMotorProcessamentoArquivo(long idParametro) throws ComumException {
        if (motorProcessaArquivoRecebidoCIPEmExecucao(idParametro)) {
            return finalizarFluxo("Existe uma execução do motor não finalizada.");
        } else {
            getParametroDao().atualizarValorParametro(idParametro, Constantes.ID_SICOOB, Constantes.STRING_NUMERO_1);
            return sucesso();
        }
    }

    /**
     * @param idParametro
     * @return
     * @throws ComumException VerificacaoDependencias
     * 
     */
    protected VerificacaoDependencias desbloquearMotorProcessamentoArquivo(long idParametro) throws ComumException {
        getParametroDao().atualizarValorParametro(idParametro, Constantes.ID_SICOOB, Constantes.STRING_NUMERO_0);
        return sucesso();
    }

    /**
     * @param idParametro
     * @return
     * @throws ComumException
     */
    private Boolean motorProcessaArquivoRecebidoCIPEmExecucao(long idParametro) throws ComumException {
        return getParametroDao().obterValorBoolean(idParametro, Constantes.ID_SICOOB);
    }

}
