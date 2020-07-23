package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;

/**
 * Fabrica dos business delegates a serem usados pelo Sistema IntegracaoCip.
 * 
 * @author Sicoob
 */
public final class IntegracaoCipFabricaDelegates extends ComumFabricaDelegates {

    /** Instancia do Fabrica de Delegates. */
    private static IntegracaoCipFabricaDelegates fabrica;

    private MensagemAlterarCadastroBeneficiarioDelegate mensagemAlterarCadastroBeneficiarioDelegate;
    private MensagemAlterarSituacaoBeneficiarioDelegate mensagemAlterarSituacaoBeneficiarioDelegate;
    private MensagemCadastrarBeneficiarioDelegate mensagemCadastrarBeneficiarioDelegate;
    private MensagemConsultaBeneficiarioDelegate mensagemConsultaBeneficiarioDelegate;
    private MensagemExcluirRelacionamentoBeneficiarioDelegate mensagemExcluirRelacionamentoBeneficiarioDelegate;
    private MensagemGEN0014Delegate mensagemGEN0014Delegate;
    private ReplicarBeneficiarioLegadoDelegate replicarBeneficiarioLegadoDelegate;
    private ProcessarRetornoDDADelegate processarRetornoDDADelegate;
    private ProcessarEnvioMensagensDelegate processarEnvioMensagensDelegate;
    private ProcessarEnvioMensagensDetalheDelegate processarEnvioMensagensDetalheDelegate;
    private ProcessarCargaMensagensDelegate processarCargaMensagensDelegate;
    private MensagemInclusaoPagadorDelegate mensagemInclusaoPagadorDelegate;
    private MensagemConsultaPagadorDelegate mensagemConsultarPagadorDelegate;
    private MensagemAlterarPagadorDelegate mensagemAlterarPagadorDelegate;
    private MensagemExcluirPagadorDelegate mensagemExcluirPagadorDelegate;
    private MensagemInclusaoBoletoDelegate mensagemInclusaoBoletoDelegate;
    private MensagemAlterarBoletoDelegate mensagemAlterarBoletoDelegate;
    private MensagemAlterarAceiteBoletoDelegate mensagemAlterarAceiteBoletoDelegate;
    private MensagemConsultaBoletoDelegate mensagemConsultaBoletoDelegate;
    private MensagemBaixaOperacionalBoletoDelegate mensagemBaixaOperacionalBoletoDelegate;
    private MensagemConsultarPagamentoDelegate mensagemConsultarPagamentoDelegate;
    private MensagemCancelamentoBaixaOperacionalDelegate mensagemCancelamentoBaixaOperacionalDelegate;
    private MensagemBaixaEfetivaBoletoDelegate mensagemBaixaEfetivaBoletoDelegate;
    private MensagemInclusaoTerceiroAutorizadoDelegate mensagemInclusaoTerceiroAutorizadoDelegate;
    private MensagemExcluirTerceiroAutorizadoDelegate mensagemExcluirTerceiroAutorizadoDelegate;
    private MensagemConsultarHorariosDDADelegate mensagemConsultarHorariosDDADelegate;
    private MensagemAlterarHorariosDDADelegate mensagemAlterarHorariosDDADelegate;
    private ProcessarEnvioArquivoDelegate processarEnvioArquivoDelegate;
    private MensagemCadastrarReenvioDelegate mensagemCadastrarReenvioDelegate;
    private ArquivoInclusaoPagadorDelegate arquivoInclusaoPagadorDelegate;
    private ArquivoAlteracaoPagadorDelegate arquivoAlteracaoPagadorDelegate;
    private ArquivoExclusaoPagadorDelegate arquivoExclusaoPagadorDelegate;
    private ArquivoInclusaoBoletoDelegate arquivoInclusaoBoletoDelegate;
    private ArquivoAlteracaoBoletoDelegate arquivoAlteracaoBoletoDelegate;
    private ArquivoBaixaOperacionalContingenciaDelegate arquivoBaixaOperacionalContingenciaDelegate;
    private ArquivoDecursoPrazoBaixaOperacionalDelegate arquivoDecursoPrazoBaixaOperacionalDelegate;
    private ArquivoBaixaEfetivaBoletoDelegate arquivoBaixaEfetivaBoletoDelegate;
    private MensagemDDAInclusaoDelegate mensagemDDAInclusaoDelegate;
    private MensagemConsultaBoletoPagamentoDelegate mensagemConsultaBoletoPagamentoDelegate;
    private MensagemAvisaArquivoDisponivelDelegate avisaArquivoDisponivelDelegate;
    private MensagemAberturaGradeHorariaDelegate mensagemAberturaGradeHorariaDelegate;
    private MensagemFechamentoGradeHorariaDelegate mensagemFechamentoGradeHorariaDelegate;
    private ArquivoManutencaoPagadorDelegate arquivoManutencaoPagadorDelegate;
    private MensagemConsultaEventoTarifavelDelegate mensagemConsultaEventoTarifavelDelegate;
    private MensagemRequisicaoArquivoMensagemDelegate mensagemRequisicaoArquivoMensagemDelegate;
    private MensagemConsultaExtratoProcessamentoDelegate mensagemConsultaExtratoProcessamentoDelegate;
    private MensagemBoletoPagamentoAbertoDelegate mensagemBoletoPagamentoAbertoDelegate;
    private MensagemDecursoBaixaOperacionalDelegate mensagemDecursoBaixaOperacionalDelegate;
    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate;
    private ArquivoRecebidoCIPProcessadorServicoDelegate arquivoRecebidoCIPProcessadorDelegate;
    private MensagemDDADelegate mensagemDDADelegate;
    private MensagemSerie0200Delegate mensagemSerie0200Delegate;
    private MensagemDDA0401Delegate mensagemDDA0401Delegate;
    private ArquivoConsultaBoletoPagamentoDelegate arquivoConsultaBoletoPagamentoDelegate;
    private ProcessarContingenciaMensagemDelegate processarContingenciaMensagemDelegate;
    private MensagemParticipanteRequisitaArquivoDelegate mensagemParticipanteRequisitaArquivoDelegate;
    private ArquivoExclusaoTerceiroAutorizadoDelegate arquivoExclusaoTerceiroAutorizadoDelegate;
    private MensagemDDAAGEN001Delegate mensagemDDAAGEM001Delegate;
    private ArquivoTestaConectividadeDelegate arquivoTesteDeConectividadeDelegate;
    private ArquivoBaixaOperacionalDelegate arquivoBaixaOperacionalDelegate;
    private ConsultaBoletoPagamentoCIPDelegate consultaBoletoPagamentoCIPDelegate;
    private ArquivoBoletoPagamentoAbertoDelegate arquivoBoletoPagamentoAbertoDelegate;

    /**
     * Retorna a fabrica de delegates a ser utilizada.
     * 
     * @return a fabrica de delegates a ser utilizada.
     */
    public static IntegracaoCipFabricaDelegates getInstance() {
        synchronized (IntegracaoCipFabricaDelegates.class) {
            if (fabrica == null) {
                fabrica = new IntegracaoCipFabricaDelegates();
            }
        }
        return fabrica;
    }

    /**
     * Construtor privado no-args da classe
     */
    private IntegracaoCipFabricaDelegates() {
    }

    /**
     * Método responsável por criar o MensagemAlterarCadastroBeneficiarioDelegate.
     */
    public MensagemAlterarCadastroBeneficiarioDelegate getMensagemAlterarCadastroBeneficiarioDelegate() {
        if (mensagemAlterarCadastroBeneficiarioDelegate == null) {
            mensagemAlterarCadastroBeneficiarioDelegate = new MensagemAlterarCadastroBeneficiarioDelegate();
        }
        return mensagemAlterarCadastroBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o MensagemAlterarSituacaoBeneficiarioDelegate.
     */
    public MensagemAlterarSituacaoBeneficiarioDelegate getMensagemAlterarSituacaoBeneficiarioDelegate() {
        if (mensagemAlterarSituacaoBeneficiarioDelegate == null) {
            mensagemAlterarSituacaoBeneficiarioDelegate = new MensagemAlterarSituacaoBeneficiarioDelegate();
        }
        return mensagemAlterarSituacaoBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o MensagemCadastrarBeneficiarioDelegate.
     */
    public MensagemCadastrarBeneficiarioDelegate getMensagemCadastrarBeneficiarioDelegate() {
        if (mensagemCadastrarBeneficiarioDelegate == null) {
            mensagemCadastrarBeneficiarioDelegate = new MensagemCadastrarBeneficiarioDelegate();
        }
        return mensagemCadastrarBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o MensagemConsultarBeneficiarioDelegate.
     */
    public MensagemConsultaBeneficiarioDelegate getMensagemConsultaBeneficiarioDelegate() {
        if (mensagemConsultaBeneficiarioDelegate == null) {
            mensagemConsultaBeneficiarioDelegate = new MensagemConsultaBeneficiarioDelegate();
        }
        return mensagemConsultaBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o MensagemExcluirRelacionamentoBeneficiarioDelegate.
     */
    public MensagemExcluirRelacionamentoBeneficiarioDelegate getMensagemExcluirRelacionamentoBeneficiarioDelegate() {
        if (mensagemExcluirRelacionamentoBeneficiarioDelegate == null) {
            mensagemExcluirRelacionamentoBeneficiarioDelegate = new MensagemExcluirRelacionamentoBeneficiarioDelegate();
        }
        return mensagemExcluirRelacionamentoBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o ArquivoRequisitarArquivoDelegate.
     */
    public MensagemGEN0014Delegate getMensagemGEN0014Delegate() {
        if (mensagemGEN0014Delegate == null) {
            mensagemGEN0014Delegate = new MensagemGEN0014Delegate();
        }
        return mensagemGEN0014Delegate;
    }

    // /**
    // * Método responsável por criar o ArquivoTestaConectividadeArquivosDelegate.
    // */
    // public ArquivoTestaConectividadeArquivosDelegate getArquivoTestaConectividadeArquivosDelegate() {
    // if (arquivoTestaConectividadeArquivosDelegate == null) {
    // arquivoTestaConectividadeArquivosDelegate = new ArquivoTestaConectividadeArquivosDelegate();
    // }
    // return arquivoTestaConectividadeArquivosDelegate;
    // }

    /**
     * Método responsável por criar o ReplicarBeneficiarioLegadoDelegate
     */
    public ReplicarBeneficiarioLegadoDelegate getReplicarBeneficiarioLegadoDelegate() {
        if (replicarBeneficiarioLegadoDelegate == null) {
            replicarBeneficiarioLegadoDelegate = new ReplicarBeneficiarioLegadoDelegate();
        }
        return replicarBeneficiarioLegadoDelegate;
    }

    /**
     * Método responsável por criar o ProcessarRetornoDDADelegate
     */
    public ProcessarRetornoDDADelegate getProcessarRetornoDDADelegate() {
        if (processarRetornoDDADelegate == null) {
            processarRetornoDDADelegate = new ProcessarRetornoDDADelegate();
        }
        return processarRetornoDDADelegate;
    }

    /**
     * Método responsável por criar o ProcessarEnvioMensagensDelegate
     */
    public ProcessarEnvioMensagensDelegate getProcessarEnvioMensagensDelegate() {
        if (processarEnvioMensagensDelegate == null) {
            processarEnvioMensagensDelegate = new ProcessarEnvioMensagensDelegate();
        }
        return processarEnvioMensagensDelegate;
    }

    /**
     * Método responsável por criar o ProcessarEnvioMensagensDetalheDelegate
     */
    public ProcessarEnvioMensagensDetalheDelegate getProcessarEnvioMensagensDetalheDelegate() {
        if (processarEnvioMensagensDetalheDelegate == null) {
            processarEnvioMensagensDetalheDelegate = new ProcessarEnvioMensagensDetalheDelegate();
        }
        return processarEnvioMensagensDetalheDelegate;
    }

    /**
     * Método responsável por criar o ProcessarCargaMensagensDelegate
     */
    public ProcessarCargaMensagensDelegate getProcessarCargaMensagensDelegate() {
        if (processarCargaMensagensDelegate == null) {
            processarCargaMensagensDelegate = new ProcessarCargaMensagensDelegate();
        }
        return processarCargaMensagensDelegate;
    }

    /**
     * Método responsável por criar o MensagemInclusaoPagadorDelegate
     */
    public MensagemInclusaoPagadorDelegate getMensagemInclusaoPagadorDelegate() {
        if (mensagemInclusaoPagadorDelegate == null) {
            mensagemInclusaoPagadorDelegate = new MensagemInclusaoPagadorDelegate();
        }
        return mensagemInclusaoPagadorDelegate;
    }

    /**
     * Método responsável por criar o MensagemConsultarPagadorDelegate
     * 
     * @return MensagemConsultarPagadorDelegate
     * 
     */
    public MensagemConsultaPagadorDelegate getMensagemConsultarPagadorDelegate() {
        if (mensagemConsultarPagadorDelegate == null) {
            mensagemConsultarPagadorDelegate = new MensagemConsultaPagadorDelegate();
        }
        return mensagemConsultarPagadorDelegate;
    }

    /**
     * Método responsável por criar o MensagemAlterarPagadorDelegate
     * 
     * @return MensagemAlterarPagadorDelegate
     * 
     */
    public MensagemAlterarPagadorDelegate getMensagemAlterarPagadorDelegate() {
        if (mensagemAlterarPagadorDelegate == null) {
            mensagemAlterarPagadorDelegate = new MensagemAlterarPagadorDelegate();
        }
        return mensagemAlterarPagadorDelegate;
    }

    /**
     * Método responsável por o MensagemExcluirPagadorDelegate
     * 
     * @return MensagemExcluirPagadorDelegate
     * 
     */
    public MensagemExcluirPagadorDelegate getMensagemExcluirPagadorDelegate() {
        if (mensagemExcluirPagadorDelegate == null) {
            mensagemExcluirPagadorDelegate = new MensagemExcluirPagadorDelegate();
        }
        return mensagemExcluirPagadorDelegate;
    }

    /**
     * Método responsável por o MensagemInclusaoBoletoDelegate
     * 
     * @return MensagemInclusaoBoletoDelegate
     * 
     */
    public MensagemInclusaoBoletoDelegate getMensagemInclusaoBoletoDelegate() {
        if (mensagemInclusaoBoletoDelegate == null) {
            mensagemInclusaoBoletoDelegate = new MensagemInclusaoBoletoDelegate();
        }
        return mensagemInclusaoBoletoDelegate;
    }

    /**
     * Método responsável por o MensagemInclusaoBoletoDelegate
     * 
     * @return MensagemInclusaoBoletoDelegate
     * 
     */
    public MensagemAlterarBoletoDelegate getMensagemAlterarBoletoDelegate() {
        if (mensagemAlterarBoletoDelegate == null) {
            mensagemAlterarBoletoDelegate = new MensagemAlterarBoletoDelegate();
        }
        return mensagemAlterarBoletoDelegate;
    }

    /**
     * Método responsável por o MensagemAlterarAceiteBoletoDelegate
     * 
     * @return MensagemAlterarAceiteBoletoDelegate
     * 
     */
    public MensagemAlterarAceiteBoletoDelegate getMensagemAlterarAceiteBoletoDelegate() {
        if (mensagemAlterarAceiteBoletoDelegate == null) {
            mensagemAlterarAceiteBoletoDelegate = new MensagemAlterarAceiteBoletoDelegate();
        }
        return mensagemAlterarAceiteBoletoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultaBoletoDelegate
     * 
     */
    public MensagemConsultaBoletoDelegate getMensagemConsultaBoletoDelegate() {
        if (mensagemConsultaBoletoDelegate == null) {
            mensagemConsultaBoletoDelegate = new MensagemConsultaBoletoDelegate();
        }
        return mensagemConsultaBoletoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemBaixaOperacionalBoletoDelegate
     * 
     */
    public MensagemBaixaOperacionalBoletoDelegate getMensagemBaixaOperacionalBoletoDelegate() {
        if (mensagemBaixaOperacionalBoletoDelegate == null) {
            mensagemBaixaOperacionalBoletoDelegate = new MensagemBaixaOperacionalBoletoDelegate();
        }
        return mensagemBaixaOperacionalBoletoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultarPagamentoDelegate
     * 
     */
    public MensagemConsultarPagamentoDelegate getMensagemConsultarPagamentoDelegate() {
        if (mensagemConsultarPagamentoDelegate == null) {
            mensagemConsultarPagamentoDelegate = new MensagemConsultarPagamentoDelegate();
        }
        return mensagemConsultarPagamentoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemCancelamentoBaixaOperacionalDelegate
     * 
     */
    public MensagemCancelamentoBaixaOperacionalDelegate getMensagemCancelamentoBaixaOperacionalDelegate() {
        if (mensagemCancelamentoBaixaOperacionalDelegate == null) {
            mensagemCancelamentoBaixaOperacionalDelegate = new MensagemCancelamentoBaixaOperacionalDelegate();
        }
        return mensagemCancelamentoBaixaOperacionalDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemBaixaEfetivaBoletoDelegate
     * 
     */
    public MensagemBaixaEfetivaBoletoDelegate getMensagemBaixaEfetivaBoletoDelegate() {
        if (mensagemBaixaEfetivaBoletoDelegate == null) {
            mensagemBaixaEfetivaBoletoDelegate = new MensagemBaixaEfetivaBoletoDelegate();
        }
        return mensagemBaixaEfetivaBoletoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemInclusaoTerceiroAutorizadoDelegate
     * 
     */
    public MensagemInclusaoTerceiroAutorizadoDelegate getMensagemInclusaoTerceiroAutorizadoDelegate() {
        if (mensagemInclusaoTerceiroAutorizadoDelegate == null) {
            mensagemInclusaoTerceiroAutorizadoDelegate = new MensagemInclusaoTerceiroAutorizadoDelegate();
        }
        return mensagemInclusaoTerceiroAutorizadoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemExcluirTerceiroAutorizadoDelegate
     * 
     */
    public MensagemExcluirTerceiroAutorizadoDelegate getMensagemExcluirTerceiroAutorizadoDelegate() {
        if (mensagemExcluirTerceiroAutorizadoDelegate == null) {
            mensagemExcluirTerceiroAutorizadoDelegate = new MensagemExcluirTerceiroAutorizadoDelegate();
        }
        return mensagemExcluirTerceiroAutorizadoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultarHorariosDDADelegate
     * 
     */
    public MensagemConsultarHorariosDDADelegate getMensagemConsultarHorariosDDADelegate() {
        if (mensagemConsultarHorariosDDADelegate == null) {
            mensagemConsultarHorariosDDADelegate = new MensagemConsultarHorariosDDADelegate();
        }
        return mensagemConsultarHorariosDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemAlterarHorariosDDADelegate
     * 
     */
    public MensagemAlterarHorariosDDADelegate getMensagemAlterarHorariosDDADelegate() {
        if (mensagemAlterarHorariosDDADelegate == null) {
            mensagemAlterarHorariosDDADelegate = new MensagemAlterarHorariosDDADelegate();
        }
        return mensagemAlterarHorariosDDADelegate;
    }

    /**
     * @return o atributo processarEnvioArquivoDelegate
     */
    public ProcessarEnvioArquivoDelegate getProcessarEnvioArquivoDelegate() {
        if (processarEnvioArquivoDelegate == null) {
            processarEnvioArquivoDelegate = new ProcessarEnvioArquivoDelegate();
        }
        return processarEnvioArquivoDelegate;
    }

    /**
     * @return o atributo mensagemCadastrarReenvioDelegate
     */
    public MensagemCadastrarReenvioDelegate getMensagemCadastrarReenvioDelegate() {
        if (mensagemCadastrarReenvioDelegate == null) {
            mensagemCadastrarReenvioDelegate = new MensagemCadastrarReenvioDelegate();
        }
        return mensagemCadastrarReenvioDelegate;
    }

    /**
     * @return o atributo getArquivoInclusaoPagadorDelegate
     */
    public ArquivoInclusaoPagadorDelegate getArquivoInclusaoPagadorDelegate() {
        if (arquivoInclusaoPagadorDelegate == null) {
            arquivoInclusaoPagadorDelegate = new ArquivoInclusaoPagadorDelegate();
        }
        return arquivoInclusaoPagadorDelegate;
    }

    /**
     * @return o atributo getArquivoInclusaoPagadorDelegate
     */
    public ArquivoAlteracaoPagadorDelegate getArquivoAlteracaoPagadorDelegate() {
        if (arquivoAlteracaoPagadorDelegate == null) {
            arquivoAlteracaoPagadorDelegate = new ArquivoAlteracaoPagadorDelegate();
        }
        return arquivoAlteracaoPagadorDelegate;
    }

    /**
     * @return o atributo getArquivoExclusaoPagadorDelegate
     */
    public ArquivoExclusaoPagadorDelegate getArquivoExclusaoPagadorDelegate() {
        if (arquivoExclusaoPagadorDelegate == null) {
            arquivoExclusaoPagadorDelegate = new ArquivoExclusaoPagadorDelegate();
        }
        return arquivoExclusaoPagadorDelegate;
    }

    /**
     * @return o atributo getArquivoInclusaoBoletoDelegate
     */
    public ArquivoInclusaoBoletoDelegate getArquivoInclusaoBoletoDelegate() {
        if (arquivoInclusaoBoletoDelegate == null) {
            arquivoInclusaoBoletoDelegate = new ArquivoInclusaoBoletoDelegate();
        }
        return arquivoInclusaoBoletoDelegate;
    }

    /**
     * @return o atributo ArquivoAlteracaoBoletoDelegate
     */
    public ArquivoAlteracaoBoletoDelegate getArquivoAlteracaoBoletoDelegate() {
        if (arquivoAlteracaoBoletoDelegate == null) {
            arquivoAlteracaoBoletoDelegate = new ArquivoAlteracaoBoletoDelegate();
        }
        return arquivoAlteracaoBoletoDelegate;
    }

    /**
     * @return o atributo ArquivoBaixaOperacionalContingenciaDelegate
     */
    public ArquivoBaixaOperacionalContingenciaDelegate getArquivoBaixaOperacionalContingenciaDelegate() {
        if (arquivoBaixaOperacionalContingenciaDelegate == null) {
            arquivoBaixaOperacionalContingenciaDelegate = new ArquivoBaixaOperacionalContingenciaDelegate();
        }
        return arquivoBaixaOperacionalContingenciaDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoBaixaOperacionalDelegate
     * 
     */
    public ArquivoBaixaOperacionalDelegate getArquivoBaixaOperacionalDelegate() {
        if (arquivoBaixaOperacionalDelegate == null) {
            arquivoBaixaOperacionalDelegate = new ArquivoBaixaOperacionalDelegate();
        }
        return arquivoBaixaOperacionalDelegate;
    }

    /**
     * @return o atributo ArquivoDecursoPrazoBaixaOperacionalDelegate
     */
    public ArquivoDecursoPrazoBaixaOperacionalDelegate getArquivoDecursoPrazoBaixaOperacionalDelegate() {
        if (arquivoDecursoPrazoBaixaOperacionalDelegate == null) {
            arquivoDecursoPrazoBaixaOperacionalDelegate = new ArquivoDecursoPrazoBaixaOperacionalDelegate();
        }
        return arquivoDecursoPrazoBaixaOperacionalDelegate;
    }

    /**
     * @return o atributo ArquivoBaixaEfetivaBoletoDelegate
     */
    public ArquivoBaixaEfetivaBoletoDelegate getArquivoBaixaEfetivaBoletoDelegate() {
        if (arquivoBaixaEfetivaBoletoDelegate == null) {
            arquivoBaixaEfetivaBoletoDelegate = new ArquivoBaixaEfetivaBoletoDelegate();
        }
        return arquivoBaixaEfetivaBoletoDelegate;
    }

    /**
     * @return MensagemConsultaBoletoPagamentoDelegate
     * 
     */
    public MensagemConsultaBoletoPagamentoDelegate getMensagemConsultaBoletoPagamentoDelegate() {
        if (mensagemConsultaBoletoPagamentoDelegate == null) {
            mensagemConsultaBoletoPagamentoDelegate = new MensagemConsultaBoletoPagamentoDelegate();
        }
        return mensagemConsultaBoletoPagamentoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemAlterarHorariosDDADelegate
     * 
     */
    public MensagemAberturaGradeHorariaDelegate getMensagemAberturaGradeHoraria() {
        if (mensagemAberturaGradeHorariaDelegate == null) {
            mensagemAberturaGradeHorariaDelegate = new MensagemAberturaGradeHorariaDelegate();
        }
        return mensagemAberturaGradeHorariaDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemAlterarHorariosDDADelegate
     * 
     */
    public MensagemFechamentoGradeHorariaDelegate getMensagemFechamentoGradeHoraria() {
        if (mensagemFechamentoGradeHorariaDelegate == null) {
            mensagemFechamentoGradeHorariaDelegate = new MensagemFechamentoGradeHorariaDelegate();
        }
        return mensagemFechamentoGradeHorariaDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoManutencaoPagadorDelegate
     * 
     */
    public ArquivoManutencaoPagadorDelegate getArquivoManutencaoPagadorDelegate() {
        if (arquivoManutencaoPagadorDelegate == null) {
            arquivoManutencaoPagadorDelegate = new ArquivoManutencaoPagadorDelegate();
        }
        return arquivoManutencaoPagadorDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultaEventoTarifavelDelegate
     * 
     */
    public MensagemConsultaEventoTarifavelDelegate getMensagemConsultaEventoTarifavelDelegate() {
        if (mensagemConsultaEventoTarifavelDelegate == null) {
            mensagemConsultaEventoTarifavelDelegate = new MensagemConsultaEventoTarifavelDelegate();
        }
        return mensagemConsultaEventoTarifavelDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemRequisicaoArquivoMensagemDelegate
     * 
     */
    public MensagemRequisicaoArquivoMensagemDelegate getMensagemRequisicaoArquivoMensagemDelegate() {
        if (mensagemRequisicaoArquivoMensagemDelegate == null) {
            mensagemRequisicaoArquivoMensagemDelegate = new MensagemRequisicaoArquivoMensagemDelegate();
        }
        return mensagemRequisicaoArquivoMensagemDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultaExtratoProcessamentoDelegate
     * 
     */
    public MensagemConsultaExtratoProcessamentoDelegate getMensagemConsultaExtratoProcessamentoDelegate() {
        if (mensagemConsultaExtratoProcessamentoDelegate == null) {
            mensagemConsultaExtratoProcessamentoDelegate = new MensagemConsultaExtratoProcessamentoDelegate();
        }
        return mensagemConsultaExtratoProcessamentoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultarHorariosDDADelegate
     * 
     */
    public MensagemBoletoPagamentoAbertoDelegate getMensagemBoletoPagamentoAbertoDelegate() {
        if (mensagemBoletoPagamentoAbertoDelegate == null) {
            mensagemBoletoPagamentoAbertoDelegate = new MensagemBoletoPagamentoAbertoDelegate();
        }
        return mensagemBoletoPagamentoAbertoDelegate;
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return
     */
    public MensagemDecursoBaixaOperacionalDelegate getMensagemDecursoBaixaOperacionalDelegate() {
        if (mensagemDecursoBaixaOperacionalDelegate == null) {
            mensagemDecursoBaixaOperacionalDelegate = new MensagemDecursoBaixaOperacionalDelegate();
        }
        return mensagemDecursoBaixaOperacionalDelegate;
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return
     */
    public MensagemParticipanteRequisitaArquivoDelegate getMensagemParticipanteRequisitaArquivoDelegate() {
        if (mensagemParticipanteRequisitaArquivoDelegate == null) {
            mensagemParticipanteRequisitaArquivoDelegate = new MensagemParticipanteRequisitaArquivoDelegate();
        }
        return mensagemParticipanteRequisitaArquivoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoExclusaoTerceiroAutorizadoDelegate
     * 
     */
    public ArquivoExclusaoTerceiroAutorizadoDelegate getArquivoExclusaoTerceiroAutorizadoDelegate() {
        if (arquivoExclusaoTerceiroAutorizadoDelegate == null) {
            arquivoExclusaoTerceiroAutorizadoDelegate = new ArquivoExclusaoTerceiroAutorizadoDelegate();
        }
        return arquivoExclusaoTerceiroAutorizadoDelegate;
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return
     */
    public ReplicarPagadorEletronicoLegadoDelegate getReplicarPagadorEletronicoLegadoDelegate() {
        if (replicarPagadorEletronicoLegadoDelegate == null) {
            replicarPagadorEletronicoLegadoDelegate = new ReplicarPagadorEletronicoLegadoDelegate();
        }
        return replicarPagadorEletronicoLegadoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoBoletoPagamentoAbertoDelegate
     * 
     */
    public ArquivoBoletoPagamentoAbertoDelegate getArquivoBoletoPagamentoAbertoDelegate() {
        if (arquivoBoletoPagamentoAbertoDelegate == null) {
            arquivoBoletoPagamentoAbertoDelegate = new ArquivoBoletoPagamentoAbertoDelegate();
        }
        return arquivoBoletoPagamentoAbertoDelegate;
    }

    /**
     * @param codTipoMensagem
     * @return
     * @throws MensagemDesconhecidaException IntegracaoCipMensagemDelegate<IntegracaoCipServico>
     * 
     */
    public IntegracaoCipMensagemDelegate<IntegracaoCipServico> getMensagemDelegate(String codTipoMensagem) throws MensagemDesconhecidaException {
        if (codTipoMensagem.equals(TipoMensagemDDA.DDA0501) || codTipoMensagem.equals(TipoMensagemDDA.DDA0501R1)) {
            return getMensagemCadastrarBeneficiarioDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0502) || codTipoMensagem.equals(TipoMensagemDDA.DDA0502R1)) {
            return getMensagemAlterarCadastroBeneficiarioDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0503) || codTipoMensagem.equals(TipoMensagemDDA.DDA0503R1)) {
            return getMensagemExcluirRelacionamentoBeneficiarioDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0504) || codTipoMensagem.equals(TipoMensagemDDA.DDA0504R1) || codTipoMensagem.equals(TipoMensagemDDA.ADDA504)) {
            return getMensagemConsultaBeneficiarioDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0505) || codTipoMensagem.equals(TipoMensagemDDA.DDA0505R1) || codTipoMensagem.equals(TipoMensagemDDA.DDA0506)) {
            return getMensagemAlterarSituacaoBeneficiarioDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0001) || codTipoMensagem.equals(TipoMensagemDDA.ADDA001RET) || codTipoMensagem.equals(TipoMensagemDDA.DDA0001R1)) {
            return getMensagemInclusaoPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0002) || codTipoMensagem.equals(TipoMensagemDDA.DDA0002R1) || codTipoMensagem.equals(TipoMensagemDDA.ADDA002)) {
            return getMensagemConsultarPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0005) || codTipoMensagem.equals(TipoMensagemDDA.DDA0005R1) || codTipoMensagem.equals(TipoMensagemDDA.ADDA005RET)) {
            return getMensagemAlterarPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0006) || codTipoMensagem.equals(TipoMensagemDDA.DDA0006R1) || codTipoMensagem.equals(TipoMensagemDDA.ADDA006RET)) {
            return getMensagemExcluirPagadorDelegate();
        } else if (TipoMensagemDDA.isMsgInclusaoBoleto(codTipoMensagem)) {
            return getMensagemInclusaoBoletoDelegate();
        } else if (TipoMensagemDDA.isMsgAlteracaoBoleto(codTipoMensagem)) {
            return getMensagemAlterarBoletoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0106) || codTipoMensagem.equals(TipoMensagemDDA.DDA0106R1) || codTipoMensagem.equals(TipoMensagemDDA.ADDA106)) {
            return getMensagemConsultaBoletoDelegate();
        } else if (TipoMensagemDDA.isMsgBxOperacional(codTipoMensagem)) {
            return getMensagemBaixaOperacionalBoletoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA110RET) || codTipoMensagem.equals(TipoMensagemDDA.DDA0110R1)) {
            return getMensagemConsultarPagamentoDelegate();
        } else if (TipoMensagemDDA.isMsgCancelamentoBxOperacional(codTipoMensagem)) {
            return getMensagemCancelamentoBaixaOperacionalDelegate();
        } else if (TipoMensagemDDA.isMsgBxEfetiva(codTipoMensagem)) {
            return getMensagemBaixaEfetivaBoletoDelegate();
        } else if (TipoMensagemDDA.isMsgInclusaoTerceiroAutorizado(codTipoMensagem)) {
            return getMensagemInclusaoTerceiroAutorizadoDelegate();
        } else if (TipoMensagemDDA.isMsgExclusaoTerceiroAutorizado(codTipoMensagem)) {
            return getMensagemExcluirTerceiroAutorizadoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0200) || codTipoMensagem.equals(TipoMensagemDDA.DDA0200R1)) {
            return getMensagemConsultaEventoTarifavelDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0214) || codTipoMensagem.equals(TipoMensagemDDA.DDA0214R1)) {
            return getMensagemConsultaExtratoProcessamentoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0215) || codTipoMensagem.equals(TipoMensagemDDA.DDA0215R1)) {
            return getMensagemRequisicaoArquivoMensagemDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0401) || codTipoMensagem.equals(TipoMensagemDDA.DDA0401R1)) {
            return getMensagemConsultarHorariosDDADelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.GEN0015)) {
            return getAvisaArquivoDisponivelDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0402)) {
            return getMensagemAlterarHorariosDDADelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0403)) {
            return getMensagemAberturaGradeHoraria();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0404)) {
            return getMensagemFechamentoGradeHoraria();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA003)) {
            return getArquivoManutencaoPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.DDA0127) || (codTipoMensagem.equals(TipoMensagemDDA.ADDA127))) {
            return getMensagemBoletoPagamentoAbertoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA117)) {
            return getMensagemDecursoBaixaOperacionalDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.GEN0014)) {
            return getMensagemParticipanteRequisitaArquivoDelegate();
        } else {
            throw new MensagemDesconhecidaException("integracaocip.erro.msg.desconhecida", codTipoMensagem);
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoMensagem
     * @return
     * @throws ComumException IntegracaoCipDelegate<IntegracaoCipServico> mensagemDDAAGEM001Delegate
     */
    public IntegracaoCipArquivoDelegate<IntegracaoCipServico> getArquivoDelegate(String codTipoMensagem) throws ComumException {
        if (codTipoMensagem.equals(TipoMensagemDDA.ADDA001)) {
            return getArquivoInclusaoPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA005)) {
            return getArquivoAlteracaoPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA006)) {
            return getArquivoExclusaoPagadorDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA101)) {
            return getArquivoInclusaoBoletoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA102)) {
            return getArquivoAlteracaoBoletoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA108)) {
            return getArquivoBaixaOperacionalDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA110)) {
            return getArquivoConsultaBoletoPagamentoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA114)) {
            return getArquivoBaixaOperacionalContingenciaDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA118)) {
            return getArquivoBaixaEfetivaBoletoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA122)) {
            return getArquivoExclusaoTerceiroAutorizadoDelegate();
        } else if (codTipoMensagem.equals(TipoMensagemDDA.AGEN001)) {
            return getArquivoTesteDeConectividadeDelegate();

        } else {

            throw new ComumException("integracaocip.erro.msg.desconhecida", codTipoMensagem);
        }
    }

    /**
     * Método responsável por instanciar o MensagemDDAInclusaoDelegate caso esteja nulo.
     * 
     * @return MensagemDDAInclusaoDelegate
     * 
     */
    public MensagemDDAInclusaoDelegate getMensagemDDAInclusaoDelegate() {
        if (mensagemDDAInclusaoDelegate == null) {
            mensagemDDAInclusaoDelegate = new MensagemDDAInclusaoDelegate();
        }
        return mensagemDDAInclusaoDelegate;
    }

    /**
     * Método responsável por instanciar o AvisaArquivoDisponivelDelegate caso esteja nulo.
     * 
     * @return AvisaArquivoDisponivelDelegate
     * 
     */
    public MensagemAvisaArquivoDisponivelDelegate getAvisaArquivoDisponivelDelegate() {
        if (avisaArquivoDisponivelDelegate == null) {
            avisaArquivoDisponivelDelegate = new MensagemAvisaArquivoDisponivelDelegate();
        }
        return avisaArquivoDisponivelDelegate;
    }

    /**
     * Método responsável por instanciar o AvisaArquivoDisponivelDelegate caso esteja nulo.
     * 
     * @return AvisaArquivoDisponivelDelegate
     * 
     */
    public ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebidoCIPProcessadorServicoDelegate() {
        if (arquivoRecebidoCIPProcessadorDelegate == null) {
            arquivoRecebidoCIPProcessadorDelegate = new ArquivoRecebidoCIPProcessadorServicoDelegate();
        }
        return arquivoRecebidoCIPProcessadorDelegate;
    }

    /**
     * Método responsável por instanciar o delegate.
     * 
     * @return
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = new MensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemSerie0200Delegate
     * 
     */
    public MensagemSerie0200Delegate getMensagemSerie0200Delegate() {
        if (mensagemSerie0200Delegate == null) {
            mensagemSerie0200Delegate = new MensagemSerie0200Delegate();
        }
        return mensagemSerie0200Delegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDA0401Delegate
     * 
     */
    public MensagemDDA0401Delegate getMensagemDDA0401Delegate() {
        if (mensagemDDA0401Delegate == null) {
            mensagemDDA0401Delegate = new MensagemDDA0401Delegate();
        }
        return mensagemDDA0401Delegate;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoConsultaBoletoPagamentoDelegate
     * 
     */
    public ArquivoConsultaBoletoPagamentoDelegate getArquivoConsultaBoletoPagamentoDelegate() {
        if (arquivoConsultaBoletoPagamentoDelegate == null) {
            arquivoConsultaBoletoPagamentoDelegate = new ArquivoConsultaBoletoPagamentoDelegate();
        }
        return arquivoConsultaBoletoPagamentoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ProcessarContingenciaMensagemDelegate
     * 
     */
    public ProcessarContingenciaMensagemDelegate getProcessarContingenciaMensagemDelegate() {
        if (processarContingenciaMensagemDelegate == null) {
            processarContingenciaMensagemDelegate = new ProcessarContingenciaMensagemDelegate();
        }
        return processarContingenciaMensagemDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAAGEN001Delegate
     * 
     */
    public MensagemDDAAGEN001Delegate getMensagemEGEN001Delegate() {
        if (this.mensagemDDAAGEM001Delegate == null) {
            this.mensagemDDAAGEM001Delegate = new MensagemDDAAGEN001Delegate();
        }
        return this.mensagemDDAAGEM001Delegate;

    }

    /**
     * @return the arquivoTesteDeConectividadeDelegate
     */
    public ArquivoTestaConectividadeDelegate getArquivoTesteDeConectividadeDelegate() {
        if (this.arquivoTesteDeConectividadeDelegate == null) {
            this.arquivoTesteDeConectividadeDelegate = new ArquivoTestaConectividadeDelegate();
        }
        return this.arquivoTesteDeConectividadeDelegate;
    }

    /**
     * @return o atributo consultaBoletoPagamentoCIPDelegate
     */
    public ConsultaBoletoPagamentoCIPDelegate getConsultaBoletoPagamentoCIPDelegate() {
        if (this.consultaBoletoPagamentoCIPDelegate == null) {
            this.consultaBoletoPagamentoCIPDelegate = new ConsultaBoletoPagamentoCIPDelegate();
        }
        return this.consultaBoletoPagamentoCIPDelegate;
    }

}