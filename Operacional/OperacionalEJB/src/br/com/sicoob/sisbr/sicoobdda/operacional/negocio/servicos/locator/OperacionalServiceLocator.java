package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AdministrativoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AgendamentoBoletoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarSituacaoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoEnviadoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CancelamentoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ContingenciaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.EventoTarifavelDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.GradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.HistoricoMensagemDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.InclusaoPagadorDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MensagemDDAPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RequisitarArquivoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoGradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico;

/**
 * Service Locator usado pelo sistema Operacional.
 * 
 * @author rafael.silva
 */
public final class OperacionalServiceLocator extends ComumServiceLocator {

    private static OperacionalServiceLocator locator;

    /**
     * Singleton da class
     * 
     * @return A instancia da classe
     */
    public static OperacionalServiceLocator getInstance() {
        if (locator == null) {
            locator = new OperacionalServiceLocator();
        }
        return locator;
    }

    /**
     * @param nomeAplicacao
     */
    private OperacionalServiceLocator() {
        super("sicoobdda_operacional");
    }

    /**
     * 
     * @return AlterarCadastroBeneficiarioServico
     * 
     */
    public AlterarCadastroBeneficiarioServico localizarAlterarCadastroBeneficiarioServico() {
        return (AlterarCadastroBeneficiarioServico) localizar("servico.sicoobdda.operacional.AlterarCadastroBeneficiarioServico");
    }

    /**
     * 
     * @return AlterarSituacaoBeneficiarioServico
     * 
     */
    public AlterarSituacaoBeneficiarioServico localizarAlterarSituacaoBeneficiarioServico() {
        return (AlterarSituacaoBeneficiarioServico) localizar("servico.sicoobdda.operacional.AlterarSituacaoBeneficiarioServico");
    }

    /**
     * 
     * @return CadastrarBeneficiarioServico
     * 
     */
    public CadastrarBeneficiarioServico localizarCadastrarBeneficiarioServico() {
        return (CadastrarBeneficiarioServico) localizar("servico.sicoobdda.operacional.CadastrarBeneficiarioServico");
    }

    /**
     * 
     * @return ConsultarBeneficiarioServico
     * 
     */
    public ConsultarBeneficiarioServico localizarConsultarBeneficiarioServico() {
        return (ConsultarBeneficiarioServico) localizar("servico.sicoobdda.operacional.ConsultarBeneficiarioServico");
    }

    /**
     * 
     * @return ExcluirRelacionamentoBeneficiarioServico
     * 
     */
    public ExcluirRelacionamentoBeneficiarioServico localizarExcluirRelacionamentoBeneficiarioServico() {
        return (ExcluirRelacionamentoBeneficiarioServico) localizar("servico.sicoobdda.operacional.ExcluirRelacionamentoBeneficiarioServico");
    }

    /**
     * @return MonitoramentoMensagensDDAServico
     * 
     */
    public MonitoramentoMensagensDDAServico localizarMonitoramentoMensagensDDAServico() {
        return (MonitoramentoMensagensDDAServico) localizar("servico.sicoobdda.operacional.MonitoramentoMensagensDDAServico");
    }

    /**
     * 
     * @return OperacaoBeneficiarioDDAServico
     * 
     */
    public OperacaoBeneficiarioDDAServico localizarOperacaoBeneficiarioDDASevico() {
        return (OperacaoBeneficiarioDDAServico) localizar("servico.sicoobdda.operacional.OperacaoBeneficiarioDDAServico");
    }

    /**
     * 
     * @return RequisitarArquivoServico
     * 
     */
    public RequisitarArquivoServico localizarRequisitarArquivoServico() {
        return (RequisitarArquivoServico) localizar("servico.sicoobdda.operacional.RequisitarArquivoServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return ConsultaBoletoDDAServico
     */
    public ConsultaBoletoDDAServico localizarConsultaBoletoDDAServico() {
        return (ConsultaBoletoDDAServico) localizar("servico.sicoobdda.operacional.ConsultaBoletoDDAServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return PagadorEletronicoDDAServico
     */
    public PagadorEletronicoDDAServico localizarPagadorEletronicoDDAServico() {
        return (PagadorEletronicoDDAServico) localizar("servico.sicoobdda.operacional.PagadorEletronicoDDAServico");
    }

    /**
     * @return TipoGradeHorariaServico
     * 
     */
    public TipoGradeHorariaServico localizarTipoGradeHorariaServico() {
        return (TipoGradeHorariaServico) localizar("servico.sicoobdda.operacional.TipoGradeHorariaServico");
    }

    /**
     * @return TipoMensagem
     * 
     */
    public TipoMensagemServico localizarTipoMensagemServico() {
        return (TipoMensagemServico) localizar("servico.sicoobdda.operacional.TipoMensagemServico");
    }

    /**
     * @return GradeHorariaServico
     * 
     */
    public GradeHorariaServico localizarGradeHorariaServico() {
        return (GradeHorariaServico) localizar("servico.sicoobdda.operacional.GradeHorariaServico");
    }

    /**
     * @return MonitoracaoServico
     * 
     */
    public MonitoracaoDDAServico localizarMonitoracaoServico() {
        return (MonitoracaoDDAServico) localizar("servico.sicoobdda.operacional.MonitoracaoDDAServico");
    }

    /**
     * @return
     */
    public CancelamentoBaixaOperacionalServico localizarCancelamentoBaixaOperacionalServico() {
        return (CancelamentoBaixaOperacionalServico) localizar("servico.sicoobdda.operacional.CancelamentoBaixaOperacionalServico");
    }

    /**
     * @return
     */
    public InclusaoPagadorDDAServico localizarInclusaoPagadorDDAServico() {
        return (InclusaoPagadorDDAServico) localizar("servico.sicoobdda.operacional.InclusaoPagadorDDAServico");
    }

    /**
     * @return
     */
    public TratamentoPendenciaErroServico localizarTratamentoPendenciaErroServico() {
        return (TratamentoPendenciaErroServico) localizar("servico.sicoobdda.operacional.TratamentoPendenciaErroServico");
    }

    /**
     * @return
     */
    public TipoErroCipServico localizarTipoErroCipServico() {
        return (TipoErroCipServico) localizar("servico.sicoobdda.operacional.TipoErroCipServico");
    }

    /**
     * @return o serviço
     */
    public BaixaOperacionalServico localizarBaixaOperacionalServico() {
        return (BaixaOperacionalServico) localizar("servico.sicoobdda.operacional.BaixaOperacionalServico");
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorServico
     * 
     */
    public MensagemDDAPagadorServico localizarMensagemDDAPagadorServico() {
        return (MensagemDDAPagadorServico) localizar("servico.sicoobdda.operacional.MensagemDDAPagadorServico");
    }

    /**
     * @return Contingencia
     * 
     */
    public ContingenciaServico localizarContingenciaServico() {
        return (ContingenciaServico) localizar("servico.sicoobdda.operacional.ContingenciaServico");
    }

    /**
     * @return ArquivoRecebidoServico
     */
    public ArquivoRecebidoServico localizarArquivoRecebidoServico() {
        return (ArquivoRecebidoServico) localizar("servico.sicoobdda.operacional.ArquivoRecebidoServico");
    }

    /**
     * @return ManutencaoMensagemDDABoletoServico
     * 
     */
    public ManutencaoMensagemDDABoletoServico localizarManutencaoMensagemBoletoServico() {
        return (ManutencaoMensagemDDABoletoServico) localizar("servico.sicoobdda.operacional.ManutencaoMensagemDDABoletoServico");
    }

    /**
     * @return BeneficiariosAlertaServico
     * 
     */
    public BeneficiariosAlertaServico localizarBeneficiariosAlertaServico() {
        return (BeneficiariosAlertaServico) localizar("servico.sicoobdda.operacional.BeneficiariosAlertaServico");
    }

    /**
     * @return HistoricoMensagemDDAServico
     * 
     */
    public HistoricoMensagemDDAServico localizarHistoricoMensagemDDAServico() {
        return (HistoricoMensagemDDAServico) localizar("servico.sicoobdda.operacional.HistoricoMensagemDDAServico");
    }

    /**
     * @return ArquivoEnviadoServico
     */
    public ArquivoEnviadoServico localizarArquivoEnviadoServico() {
        return (ArquivoEnviadoServico) localizar("servico.sicoobdda.operacional.ArquivoEnviadoServico");
    }

    /**
     * @return AdministrativoServico
     */
    public AdministrativoServico localizarAdministrativoServico() {
        return (AdministrativoServico) localizar("servico.sicoobdda.operacional.AdministrativoServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return AgendamentoBoletoDDAServico
     */
    public AgendamentoBoletoDDAServico localizarAgendamentoBoletoDDAServico() {
        return (AgendamentoBoletoDDAServico) localizar("servico.sicoobdda.operacional.AgendamentoBoletoDDAServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return EventoTarifavelDDAServico
     */
    public EventoTarifavelDDAServico localizarEventoTarifavelDDAServico() {
        return (EventoTarifavelDDAServico) localizar("servico.sicoobdda.operacional.EventoTarifavelDDAServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return
     */
    public RateioDDALancamentoServico localizarRateioDDALancamentoServico() {
        return (RateioDDALancamentoServico) localizar("servico.sicoobdda.operacional.RateioDDALancamentoServico");
    }

    /**
     * Método responsável por retornar o serviço.
     * 
     * @return
     */
    public RateioTarifasCipServico localizarRateioTarifasCipServico() {
        return (RateioTarifasCipServico) localizar("servico.sicoobdda.operacional.RateioTarifasCipServico");
    }

}
