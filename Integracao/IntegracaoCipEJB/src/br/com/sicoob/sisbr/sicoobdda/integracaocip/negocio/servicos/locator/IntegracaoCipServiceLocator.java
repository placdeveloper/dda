/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator
 * Arquivo:         IntegracaoCipServiceLocator.java
 * Data Criação:    Abr 18, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaDecursoPrazoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalContingenciaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBoletoPagamentoAbertoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoConsultaBoletoPagamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoDecursoPrazoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoExclusaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoExclusaoTerceiroAutorizadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoInclusaoBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoInclusaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoManutencaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoTestaConectividadeArquivosServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoTestaConectividadeServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAberturaGradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarAceiteBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarHorariosDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarSituacaoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAvisaArquivoDisponivelServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaEfetivaBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaOperacionalBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBoletoPagamentoAbertoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCadastrarReenvioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemCancelamentoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaEventoTarifavelServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaExtratoProcessamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultarHorariosDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultarPagamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDA0401Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAAGEN001Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAInclusaoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDecursoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemExcluirPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemExcluirRelacionamentoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemExcluirTerceiroAutorizadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemFechamentoGradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemInclusaoBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemInclusaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemInclusaoTerceiroAutorizadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemParticipanteRequisitaArquivoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemRequisicaoArquivoMensagemServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemSerie0200Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensDetalheServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico;

/**
 * Service Locator usado pelo sistema SicoobDDA.
 * 
 * @author Sicoob
 */
public final class IntegracaoCipServiceLocator extends ComumServiceLocator {

    private static IntegracaoCipServiceLocator locator;

    /**
     * Singleton da class
     * 
     * @return A instancia da classe
     */
    public static IntegracaoCipServiceLocator getInstance() {
        if (locator == null) {
            locator = new IntegracaoCipServiceLocator();
        }
        return locator;
    }

    /**
     * @param nomeAplicacao
     */
    private IntegracaoCipServiceLocator() {
        super("sicoobdda_integracaocip");
    }

    /**
     * @return AlterarCadastroBeneficiarioServico
     */
    public MensagemAlterarCadastroBeneficiarioServico localizarMensagemAlterarCadastroBeneficiarioServico() {
        return (MensagemAlterarCadastroBeneficiarioServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarCadastroBeneficiarioServico");
    }

    /**
     * @return AlterarSituacaoBeneficiarioServico
     */
    public MensagemAlterarSituacaoBeneficiarioServico localizarMensagemAlterarSituacaoBeneficiarioServico() {
        return (MensagemAlterarSituacaoBeneficiarioServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarSituacaoBeneficiarioServico");
    }

    /**
     * @return CadastrarBeneficiarioServico
     */
    public MensagemCadastrarBeneficiarioServico localizarMensagemCadastrarBeneficiarioServico() {
        return (MensagemCadastrarBeneficiarioServico) localizar("servico.sicoobdda.integracaocip.MensagemCadastrarBeneficiarioServico");
    }

    /**
     * @return ConsultarBeneficiarioServico
     */
    public MensagemConsultaBeneficiarioServico localizarMensagemConsultaBeneficiarioServico() {
        return (MensagemConsultaBeneficiarioServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaBeneficiarioServico");
    }

    /**
     * @return ExcluirRelacionamentoBeneficiarioServico
     */
    public MensagemExcluirRelacionamentoBeneficiarioServico localizarMensagemExcluirRelacionamentoBeneficiarioServico() {
        return (MensagemExcluirRelacionamentoBeneficiarioServico) localizar("servico.sicoobdda.integracaocip.MensagemExcluirRelacionamentoBeneficiarioServico");
    }

    /**
     * @return RequisitarArquivoServico
     */
    public MensagemGEN0014Servico localizarMensagemGEN0014Servico() {
        return (MensagemGEN0014Servico) localizar("servico.sicoobdda.integracaocip.MensagemGEN0014Servico");
    }

    /**
     * @return TestaConectividadeArquivosServico
     */
    public ArquivoTestaConectividadeArquivosServico localizarArquivoTestaConectividadeArquivosServico() {
        return (ArquivoTestaConectividadeArquivosServico) localizar("servico.sicoobdda.integracaocip.ArquivoTestaConectividadeArquivosServico");
    }

    /**
     * @return ReplicarBeneficiarioLegadoServico
     */
    public ReplicarBeneficiarioLegadoServico localizarReplicarBeneficiarioLegadoServico() {
        return (ReplicarBeneficiarioLegadoServico) localizar("servico.sicoobdda.integracaocip.ReplicarBeneficiarioLegadoServico");
    }

    /**
     * @return ProcessarRetornoDDAServico
     */
    public ProcessarRetornoDDAServico localizarProcessarRetornoDDAServico() {
        return (ProcessarRetornoDDAServico) localizar("servico.sicoobdda.integracaocip.ProcessarRetornoDDAServico");
    }

    /**
     * @return ProcessarEnvioMensagensServico
     */
    public ProcessarEnvioMensagensServico localizarProcessarEnvioMensagensServico() {
        return (ProcessarEnvioMensagensServico) localizar("servico.sicoobdda.integracaocip.ProcessarEnvioMensagensServico");
    }

    /**
     * @return ProcessarEnvioMensagensServico
     */
    public MensagemInclusaoPagadorServico localizarMensagemInclusaoPagadorServico() {
        return (MensagemInclusaoPagadorServico) localizar("servico.sicoobdda.integracaocip.MensagemInclusaoPagadorServico");
    }

    /**
     * @return ProcessarEnvioMensagensServico
     */
    public MensagemConsultaPagadorServico localizarMensagemConsultarPagadorServico() {
        return (MensagemConsultaPagadorServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaPagadorServico");
    }

    /**
     * @return MensagemAlterarPagadorServico
     */
    public MensagemAlterarPagadorServico localizarMensagemAlterarPagadorServico() {
        return (MensagemAlterarPagadorServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarPagadorServico");
    }

    /**
     * @return MensagemExcluirPagadorServico
     */
    public MensagemExcluirPagadorServico localizarMensagemExcluirPagadorServico() {
        return (MensagemExcluirPagadorServico) localizar("servico.sicoobdda.integracaocip.MensagemExcluirPagadorServico");
    }

    /**
     * @return MensagemInclusaoBoletoServico
     */
    public MensagemInclusaoBoletoServico localizarMensagemInclusaoBoletoServico() {
        return (MensagemInclusaoBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemInclusaoBoletoServico");
    }

    /**
     * @return MensagemAlterarBoletoServico
     */
    public MensagemAlterarBoletoServico localizarMensagemAlterarBoletoServico() {
        return (MensagemAlterarBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarBoletoServico");
    }

    /**
     * @return MensagemAlterarAceiteBoletoServico
     */
    public MensagemAlterarAceiteBoletoServico localizarMensagemAlterarAceiteBoletoServico() {
        return (MensagemAlterarAceiteBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarAceiteBoletoServico");
    }

    /**
     * @return MensagemConsultarBoletoServico
     */
    public MensagemConsultaBoletoServico localizarMensagemConsultaBoletoServico() {
        return (MensagemConsultaBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaBoletoServico");
    }

    /**
     * @return MensagemBaixaOperacionalBoletoServico
     */
    public MensagemBaixaOperacionalBoletoServico localizarMensagemBaixaOperacionalBoletoServico() {
        return (MensagemBaixaOperacionalBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemBaixaOperacionalBoletoServico");
    }

    /**
     * @return MensagemConsultarPagamentoServico
     */
    public MensagemConsultarPagamentoServico localizarMensagemConsultarPagamentoServico() {
        return (MensagemConsultarPagamentoServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultarPagamentoServico");
    }

    /**
     * @return MensagemCancelamentoBaixaOperacionalServico
     */
    public MensagemCancelamentoBaixaOperacionalServico localizarMensagemCancelamentoBaixaOperacionalServico() {
        return (MensagemCancelamentoBaixaOperacionalServico) localizar("servico.sicoobdda.integracaocip.MensagemCancelamentoBaixaOperacionalServico");
    }

    /**
     * @return MensagemBaixaEfetivaBoletoServico
     */
    public MensagemBaixaEfetivaBoletoServico localizarMensagemBaixaEfetivaBoletoServico() {
        return (MensagemBaixaEfetivaBoletoServico) localizar("servico.sicoobdda.integracaocip.MensagemBaixaEfetivaBoletoServico");
    }

    /**
     * @return MensagemInclusaoTerceiroAutorizadoServico
     */
    public MensagemInclusaoTerceiroAutorizadoServico localizarMensagemInclusaoTerceiroAutorizadoServico() {
        return (MensagemInclusaoTerceiroAutorizadoServico) localizar("servico.sicoobdda.integracaocip.MensagemInclusaoTerceiroAutorizadoServico");
    }

    /**
     * @return MensagemExcluirTerceiroAutorizadoServico
     */
    public MensagemExcluirTerceiroAutorizadoServico localizarMensagemExcluirTerceiroAutorizadoServico() {
        return (MensagemExcluirTerceiroAutorizadoServico) localizar("servico.sicoobdda.integracaocip.MensagemExcluirTerceiroAutorizadoServico");
    }

    /**
     * @return MensagemConsultarHorariosDDAServico
     */
    public MensagemConsultarHorariosDDAServico localizarMensagemConsultarHorariosDDAServico() {
        return (MensagemConsultarHorariosDDAServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultarHorariosDDAServico");
    }

    /**
     * @return MensagemAlterarHorariosDDAServico
     */
    public MensagemAlterarHorariosDDAServico localizarMensagemAlterarHorariosDDAServico() {
        return (MensagemAlterarHorariosDDAServico) localizar("servico.sicoobdda.integracaocip.MensagemAlterarHorariosDDAServico");
    }

    /**
     * @return ProcessarCargaMensagensServico
     */
    public ProcessarCargaMensagensServico localizarProcessarCargaMensagensServico() {
        return (ProcessarCargaMensagensServico) localizar("servico.sicoobdda.integracaocip.ProcessarCargaMensagensServico");
    }

    /**
     * @return ProcessarCargaMensagensServico
     */
    public ProcessarEnvioArquivoServico localizarProcessarEnvioArquivoServico() {
        return (ProcessarEnvioArquivoServico) localizar("servico.sicoobdda.integracaocip.ProcessarEnvioArquivoServico");
    }

    /**
     * @return ProcessarCargaMensagensServico
     */
    public MensagemCadastrarReenvioServico localizarMensagemCadastrarReenvioServico() {
        return (MensagemCadastrarReenvioServico) localizar("servico.sicoobdda.integracaocip.MensagemCadastrarReenvioServico");
    }

    /**
     * @return ArquivoInclusaoPagadorServico
     */
    public ArquivoInclusaoPagadorServico localizarArquivoInclusaoPagadorServico() {
        return (ArquivoInclusaoPagadorServico) localizar("servico.sicoobdda.integracaocip.ArquivoInclusaoPagadorServico");
    }

    /**
     * @return ArquivoAlteracaoPagadorServico
     */
    public ArquivoAlteracaoPagadorServico localizarArquivoAlteracaoPagadorServico() {
        return (ArquivoAlteracaoPagadorServico) localizar("servico.sicoobdda.integracaocip.ArquivoAlteracaoPagadorServico");
    }

    /**
     * @return ArquivoExclusaoPagadorServico
     */
    public ArquivoExclusaoPagadorServico localizarArquivoExclusaoPagadorServico() {
        return (ArquivoExclusaoPagadorServico) localizar("servico.sicoobdda.integracaocip.ArquivoExclusaoPagadorServico");
    }

    public ArquivoExclusaoTerceiroAutorizadoServico localizarArquivoExclusaoTerceiroAutorizadoServico() {
        return (ArquivoExclusaoTerceiroAutorizadoServico) localizar("servico.sicoobdda.integracaocip.ArquivoExclusaoTerceiroAutorizadoServico");
    }

    /**
     * @return ArquivoInclusaoBoletoServico
     */
    public ArquivoInclusaoBoletoServico localizarArquivoInclusaoBoletoServico() {
        return (ArquivoInclusaoBoletoServico) localizar("servico.sicoobdda.integracaocip.ArquivoInclusaoBoletoServico");
    }

    /**
     * @return ArquivoRecebidoCIPProcessadorServico
     * @see ArquivoRecebidoCIPProcessadorServico
     */
    public ArquivoRecebidoCIPProcessadorServico localizarArquivoRecebidoCIPProcessadorServico() {
        return (ArquivoRecebidoCIPProcessadorServico) localizar("servico.sicoobdda.integracaocip.ArquivoRecebidoCIPProcessadorServico");
    }

    /**
     * @return ArquivoInclusaoBoletoServico
     */
    public ArquivoAlteracaoBoletoServico localizarArquivoAlteracaoBoletoServico() {
        return (ArquivoAlteracaoBoletoServico) localizar("servico.sicoobdda.integracaocip.ArquivoAlteracaoBoletoServico");
    }

    /**
     * @return ArquivoBaixaOperacionalContingenciaServico
     */
    public ArquivoBaixaOperacionalContingenciaServico localizarArquivoBaixaOperacionalContingenciaServico() {
        return (ArquivoBaixaOperacionalContingenciaServico) localizar("servico.sicoobdda.integracaocip.ArquivoBaixaOperacionalContingenciaServico");
    }

    /**
     * @return ArquivoBaixaOperacionalServico
     */
    public ArquivoBaixaOperacionalServico localizarArquivoBaixaOperacionalServico() {
        return (ArquivoBaixaOperacionalServico) localizar("servico.sicoobdda.integracaocip.ArquivoBaixaOperacionalServico");
    }

    /**
     * @return MensagemAlterarHorariosDDAServico
     */
    public ArquivoBoletoPagamentoAbertoServico localizarArquivoBoletoPagamentoAbertoServico() {
        return (ArquivoBoletoPagamentoAbertoServico) localizar("servico.sicoobdda.integracaocip.ArquivoBoletoPagamentoAbertoServico");
    }

    /**
     * @return ArquivoDecursoPrazoBaixaOperacionalServico
     */
    public ArquivoDecursoPrazoBaixaOperacionalServico localizarArquivoDecursoPrazoBaixaOperacionalServico() {
        return (ArquivoDecursoPrazoBaixaOperacionalServico) localizar("servico.sicoobdda.integracaocip.ArquivoDecursoPrazoBaixaOperacionalServico");
    }

    /**
     * @return ArquivoBaixaEfetivaBoletoServico
     */
    public ArquivoBaixaEfetivaBoletoServico localizarArquivoBaixaEfetivaBoletoServico() {
        return (ArquivoBaixaEfetivaBoletoServico) localizar("servico.sicoobdda.integracaocip.ArquivoBaixaEfetivaBoletoServico");
    }

    /**
     * @return ArquivoInclusaoBoletoServico
     */
    public ArquivoBaixaEfetivaDecursoPrazoServico localizarArquivoBaixaEfetivaDecursoPrazoServico() {
        return (ArquivoBaixaEfetivaDecursoPrazoServico) localizar("servico.sicoobdda.integracaocip.ArquivoBaixaEfetivaDecursoPrazoServico");
    }

    /**
     * 
     * @return MensagemConsultaBoletoPagamentoServico
     * 
     */
    public MensagemConsultaBoletoPagamentoServico localizarMensagemConsultaBoletoPagamentoServico() {
        return (MensagemConsultaBoletoPagamentoServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaBoletoPagamentoServico");
    }

    /**
     * @return ConsultaBoletoPagamentoCIPServico
     * 
     */
    public ConsultaBoletoPagamentoCIPServico localizarConsultaBoletoPagamentoCIPServico() {
        return (ConsultaBoletoPagamentoCIPServico) localizar("servico.sicoobdda.integracaocip.ConsultaBoletoPagamentoCIPServico");
    }

    /**
     * @return MensagemDDAInclusaoServico
     */
    public MensagemDDAInclusaoServico localizarMensagemDDAInclusaoServico() {
        return (MensagemDDAInclusaoServico) localizar("servico.sicoobdda.integracaocip.MensagemDDAInclusaoServico");
    }

    /**
     * @return MensagemDDAServico
     */
    public MensagemDDAServico localizarMensagemDDAServico() {
        return (MensagemDDAServico) localizar("servico.sicoobdda.integracaocip.MensagemDDAServico");
    }

    /**
     * @return AvisaArquivoDisponivelServico
     */
    public MensagemAvisaArquivoDisponivelServico localizarAvisaArquivoDisponivelServico() {
        return (MensagemAvisaArquivoDisponivelServico) localizar("servico.sicoobdda.integracaocip.MensagemAvisaArquivoDisponivelServico");
    }

    /**
     * @return MensagemAlterarHorariosDDAServico
     */
    public MensagemAberturaGradeHorariaServico localizarMensagemAberturaGradeHorariaServico() {
        return (MensagemAberturaGradeHorariaServico) localizar("servico.sicoobdda.integracaocip.MensagemAberturaGradeHorariaServico");
    }

    /**
     * @return MensagemAlterarHorariosDDAServico
     */
    public MensagemFechamentoGradeHorariaServico localizarMensagemFechamentoGradeHorariaServico() {
        return (MensagemFechamentoGradeHorariaServico) localizar("servico.sicoobdda.integracaocip.MensagemFechamentoGradeHorariaServico");
    }

    /**
     * @return ArquivoManutencaoPagadorServico
     */
    public ArquivoManutencaoPagadorServico localizarArquivoManutencaoPagadorServico() {
        return (ArquivoManutencaoPagadorServico) localizar("servico.sicoobdda.integracaocip.ArquivoManutencaoPagadorServico");
    }

    /**
     * @return MensagemConsultaEventoTarifavelServico
     */
    public MensagemConsultaEventoTarifavelServico localizarMensagemConsultaEventoTarifavelServico() {
        return (MensagemConsultaEventoTarifavelServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaEventoTarifavelServico");
    }

    /**
     * @return MensagemRequisicaoArquivoMensagemServico
     * 
     */
    public MensagemRequisicaoArquivoMensagemServico localizarMensagemRequisicaoArquivoMensagemServico() {
        return (MensagemRequisicaoArquivoMensagemServico) localizar("servico.sicoobdda.integracaocip.MensagemRequisicaoArquivoMensagemServico");
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultaExtratoProcessamentoServico
     * 
     */
    public MensagemConsultaExtratoProcessamentoServico localizarMensagemConsultaExtratoProcessamentoServico() {
        return (MensagemConsultaExtratoProcessamentoServico) localizar("servico.sicoobdda.integracaocip.MensagemConsultaExtratoProcessamentoServico");
    }

    /**
     * @return MensagemAlterarHorariosDDAServico
     */
    public MensagemBoletoPagamentoAbertoServico localizarMensagemBoletoPagamentoAbertoServico() {
        return (MensagemBoletoPagamentoAbertoServico) localizar("servico.sicoobdda.integracaocip.MensagemBoletoPagamentoAbertoServico");
    }

    /**
     * @return MensagemDecursoBaixaOperacionalServico
     */
    public MensagemDecursoBaixaOperacionalServico localizarMensagemDecursoBaixaOperacionalServico() {
        return (MensagemDecursoBaixaOperacionalServico) localizar("servico.sicoobdda.integracaocip.MensagemDecursoBaixaOperacionalServico");
    }

    /**
     * @return MensagemDecursoBaixaOperacionalServico
     */
    public ReplicarPagadorEletronicoLegadoServico localizarReplicarPagadorEletronicoLegadoServico() {
        return (ReplicarPagadorEletronicoLegadoServico) localizar("servico.sicoobdda.integracaocip.ReplicarPagadorEletronicoLegadoServico");
    }

    /**
     * Método responsável por
     * 
     * @return MensagemSerie0200Servico
     * 
     */
    public MensagemSerie0200Servico localizarMensagemSerie0200Servico() {
        return (MensagemSerie0200Servico) localizar("servico.sicoobdda.integracaocip.MensagemSerie0200Servico");
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDA0401Servico
     * 
     */
    public MensagemDDA0401Servico localizarMensagemDDA0401Servico() {
        return (MensagemDDA0401Servico) localizar("servico.sicoobdda.integracaocip.MensagemDDA0401Servico");
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoConsultaBoletoPagamentoServico
     * 
     */
    public ArquivoConsultaBoletoPagamentoServico localizarArquivoConsultaBoletoPagamentoServico() {
        return (ArquivoConsultaBoletoPagamentoServico) localizar("servico.sicoobdda.integracaocip.ArquivoConsultaBoletoPagamentoServico");
    }

    /**
     * @return ProcessarContingenciaMensagemServico
     */
    public ProcessarContingenciaMensagemServico localizarProcessarContingenciaMensagemServico() {
        return (ProcessarContingenciaMensagemServico) localizar("servico.sicoobdda.integracaocip.ProcessarContingenciaMensagemServico");
    }

    /**
     * Método responsável por obter o serviço
     */
    public MensagemParticipanteRequisitaArquivoServico localizarMensagemParticipanteRequisitaArquivoServico() {
        return (MensagemParticipanteRequisitaArquivoServico) localizar("servico.sicoobdda.integracaocip.MensagemParticipanteRequisitaArquivoServico");
    }

    /**
     * @return MensagemDDAAGEN001Servico
     */
    public MensagemDDAAGEN001Servico localizarMensagemADDAAGEN001Servico() {
        return (MensagemDDAAGEN001Servico) localizar("servico.sicoobdda.integracaocip.MensagemDDAAGEN001Servico");
    }

    /**
     * @return ArquivoTestaConectividadeServico
     */
    public ArquivoTestaConectividadeServico localizarArquivoTestaConectividadeServico() {
        return (ArquivoTestaConectividadeServico) localizar("servico.sicoobdda.integracaocip.ArquivoTestaConectividadeServico");
    }

    /**
     * @return ProcessarEnvioMensagensDetalheServico
     */
    public ProcessarEnvioMensagensDetalheServico localizarProcessarEnvioMensagensDetalheServico() {
        return (ProcessarEnvioMensagensDetalheServico) localizar("servico.sicoobdda.integracaocip.ProcessarEnvioMensagensDetalheServico");
    }

}
