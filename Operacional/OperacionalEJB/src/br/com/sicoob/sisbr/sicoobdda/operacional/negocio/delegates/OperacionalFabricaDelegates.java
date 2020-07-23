/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         OperacionalFabricaDelegates.java
 * Data Criação:    Mai 10, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.AtualizaParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * OperacionalFabricaDelegates
 * 
 * @author rafael.silva
 */
public final class OperacionalFabricaDelegates extends ComumFabricaDelegates {

    private static OperacionalFabricaDelegates fabrica;

    private AlterarCadastroBeneficiarioDelegate alterarCadastroBeneficiarioDelegate;
    private AlterarSituacaoBeneficiarioDelegate alterarSituacaoBeneficiarioDelegate;
    private CadastrarBeneficiarioDelegate cadastrarBeneficiarioDelegate;
    private ConsultarBeneficiarioDelegate consultarBeneficiarioDelegate;
    private ExcluirRelacionamentoBeneficiarioDelegate excluirRelacionamentoBeneficiarioDelegate;
    private MonitoramentoMensagensDDADelegate monitoramentoMensagensDDADelegate;
    private MonitoracaoDelegate monitoracaoDelegate;
    private OperacaoBeneficiarioDDADelegate operacaoBeneficiarioDDADelegate;
    private RequisitarArquivoDelegate requisitarArquivoDelegate;
    private TipoGradeHorariaDelegate tipoGradeHorariaDelegate;
    private GradeHorariaDelegate gradeHorariaDelegate;
    private TipoMensagemDelegate tipoMensagemDelegate;
    private ConsultaBoletoDDADelegate consultaBoletoDDADelegate;
    private PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate;
    private CancelamentoBaixaOperacionalDelegate cancelamentoBaixaOperacionalDelegate;
    private AtualizaParametroDelegate atualizaParametroDelegate;
    private TratamentoPendenciaErroDelegate tratamentoDelegate;
    private TipoErroCipDelegate tipoErroDelegate;
    private MensagemDDAPagadorDelegate mensagemDDAPagadorDelegate;
    private ContingenciaDelegate contingenciaDelegate;
    private ArquivoRecebidoDelegate arquivoRecebidoDelegate;
    private BeneficiariosAlertaDelegate beneficiariosAlertaDelegate;
    private HistoricoMensagemDDADelegate historicoMensagemDDADelegate;
    private ArquivoEnviadoDelegate arquivoEnviadoDelegate;
    private AdministrativoDelegate administrativoDelegate;
    private ManutencaoMensagemDDABoletoDelegate manutencaoMensagemDDABoletoDelegate;
    private AgendamentoBoletoDDADelegate agendamentoBoletoDDADelegate;
    private EventoTarifavelDDADelegate eventoTarifavelDDADelegate;
    private RateioDDALancamentoDelegate rateioDDALancamentoDelegate;
    private ConsolidarEventosTarifaveisCipDelegate consolidarEventosTarifaveisCipDelegate;
    private RateioTarifasCIPDelegate rateioTarifasCIPDelegate;

    /**
     * Retorna a fabrica de delegates a ser utilizada.
     * 
     * @return a fabrica de delegates a ser utilizada.
     */
    public static OperacionalFabricaDelegates getInstance() {
        synchronized (OperacionalFabricaDelegates.class) {
            if (fabrica == null) {
                fabrica = new OperacionalFabricaDelegates();
            }
        }
        return fabrica;
    }

    /**
     * Construtor privado no-args da classe
     */
    private OperacionalFabricaDelegates() {
    }

    /**
     * Método responsável por criar o AlterarCadastroBeneficiarioDelegate.
     */
    public AlterarCadastroBeneficiarioDelegate getAlterarCadastroBeneficiarioDelegate() {
        if (alterarCadastroBeneficiarioDelegate == null) {
            alterarCadastroBeneficiarioDelegate = new AlterarCadastroBeneficiarioDelegate();
        }
        return alterarCadastroBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o AlterarSituacaoBeneficiarioDelegate
     */
    public AlterarSituacaoBeneficiarioDelegate getAlterarSituacaoBeneficiarioDelegate() {
        if (alterarSituacaoBeneficiarioDelegate == null) {
            alterarSituacaoBeneficiarioDelegate = new AlterarSituacaoBeneficiarioDelegate();
        }
        return alterarSituacaoBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o CadastrarBeneficiarioDelegate
     */
    public CadastrarBeneficiarioDelegate getCadastrarBeneficiarioDelegate() {
        if (cadastrarBeneficiarioDelegate == null) {
            cadastrarBeneficiarioDelegate = new CadastrarBeneficiarioDelegate();
        }
        return cadastrarBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o ConsultarBeneficiarioDelegate
     */
    public ConsultarBeneficiarioDelegate getConsultarBeneficiarioDelegate() {
        if (consultarBeneficiarioDelegate == null) {
            consultarBeneficiarioDelegate = new ConsultarBeneficiarioDelegate();
        }
        return consultarBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o ExcluirRelacionamentoBeneficiarioDelegate
     */
    public ExcluirRelacionamentoBeneficiarioDelegate getExcluirRelacionamentoBeneficiarioDelegate() {
        if (excluirRelacionamentoBeneficiarioDelegate == null) {
            excluirRelacionamentoBeneficiarioDelegate = new ExcluirRelacionamentoBeneficiarioDelegate();
        }
        return excluirRelacionamentoBeneficiarioDelegate;
    }

    /**
     * Método responsável por criar o MonitoramentoMensagensDDADelegate
     */
    public MonitoramentoMensagensDDADelegate getMonitoramentoMensagensDDADelegate() {
        if (monitoramentoMensagensDDADelegate == null) {
            monitoramentoMensagensDDADelegate = new MonitoramentoMensagensDDADelegate();
        }
        return monitoramentoMensagensDDADelegate;
    }

    /**
     * Método responsável por criar o OperacaoBeneficiarioDDADelegate
     */
    public OperacaoBeneficiarioDDADelegate getOperacaoBeneficiarioDDADelegate() {
        if (operacaoBeneficiarioDDADelegate == null) {
            operacaoBeneficiarioDDADelegate = new OperacaoBeneficiarioDDADelegate();
        }
        return operacaoBeneficiarioDDADelegate;
    }

    /**
     * Método responsável por criar o RequisitarArquivoDelegate
     */
    public RequisitarArquivoDelegate getRequisitarArquivoDelegate() {
        if (requisitarArquivoDelegate == null) {
            requisitarArquivoDelegate = new RequisitarArquivoDelegate();
        }
        return requisitarArquivoDelegate;
    }

    /**
     * Método responsável por retornar o delegate
     * 
     * @return ConsultaBoletoDDADelegate
     */
    public ConsultaBoletoDDADelegate getConsultaBoletoDDADelegate() {
        if (consultaBoletoDDADelegate == null) {
            consultaBoletoDDADelegate = new ConsultaBoletoDDADelegate();
        }
        return consultaBoletoDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADelegate
     * 
     */
    public PagadorEletronicoDDADelegate getPagadorEletronicoDDADelegate() {
        if (pagadorEletronicoDDADelegate == null) {
            pagadorEletronicoDDADelegate = new PagadorEletronicoDDADelegate();
        }
        return pagadorEletronicoDDADelegate;
    }

    /**
     * Método responsável por criar o TipoGradeHorariaDelegate
     */
    public TipoGradeHorariaDelegate getTipoGradeHorariaDelegate() {
        if (tipoGradeHorariaDelegate == null) {
            tipoGradeHorariaDelegate = new TipoGradeHorariaDelegate();
        }
        return tipoGradeHorariaDelegate;
    }

    /**
     * Método responsável por criar o GradeHorariaDelegate
     */
    public GradeHorariaDelegate getGradeHorariaDelegate() {
        if (gradeHorariaDelegate == null) {
            gradeHorariaDelegate = new GradeHorariaDelegate();
        }
        return gradeHorariaDelegate;
    }

    /**
     * Método responsável por criar o TipoMensagemDelegate
     */
    public TipoMensagemDelegate getTipoMensagemDelegate() {
        if (tipoMensagemDelegate == null) {
            tipoMensagemDelegate = new TipoMensagemDelegate();
        }
        return tipoMensagemDelegate;
    }

    /**
     * Método responsável por criar o MonitoracaoDelegate
     */
    public MonitoracaoDelegate getMonitoracaoDelegate() {
        if (monitoracaoDelegate == null) {
            monitoracaoDelegate = new MonitoracaoDelegate();
        }
        return monitoracaoDelegate;
    }

    /**
     * Método responsável por criar o MonitoracaoFilaDDADelegate
     */
    public CancelamentoBaixaOperacionalDelegate getCancelamentoBaixaOperacionalDelegate() {
        if (cancelamentoBaixaOperacionalDelegate == null) {
            cancelamentoBaixaOperacionalDelegate = new CancelamentoBaixaOperacionalDelegate();
        }
        return cancelamentoBaixaOperacionalDelegate;
    }

    /**
     * 
     * Método responsável por criar o delegate de parametro
     * 
     * @return AtualizaParametroDelegate
     * 
     */
    public AtualizaParametroDelegate getAtualizaParametroDelegate() {
        if (atualizaParametroDelegate == null) {
            atualizaParametroDelegate = new AtualizaParametroDelegate();
        }
        return atualizaParametroDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return TratamentoPendenciaErroDelegate
     * 
     */
    public TratamentoPendenciaErroDelegate getTratamentoPendenciaErroDelegate() {
        if (tratamentoDelegate == null) {
            tratamentoDelegate = new TratamentoPendenciaErroDelegate();
        }
        return tratamentoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCipDelegate
     * 
     */
    public TipoErroCipDelegate getTipoErroCipDelegate() {
        if (tipoErroDelegate == null) {
            tipoErroDelegate = new TipoErroCipDelegate();
        }
        return tipoErroDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorDelegate
     * 
     */
    public MensagemDDAPagadorDelegate getMensagemDDAPagadorDelegate() {
        if (mensagemDDAPagadorDelegate == null) {
            mensagemDDAPagadorDelegate = new MensagemDDAPagadorDelegate();
        }
        return mensagemDDAPagadorDelegate;
    }

    /**
     * 
     * @return ContingenciaDelegate
     * 
     */
    public ContingenciaDelegate getContingenciaDelegate() {
        if (contingenciaDelegate == null) {
            contingenciaDelegate = new ContingenciaDelegate();
        }
        return contingenciaDelegate;
    }

    /**
     * 
     * @return ArquivoRecebidoDelegate
     * 
     */
    public ArquivoRecebidoDelegate getArquivoRecebidoDelegate() {
        if (arquivoRecebidoDelegate == null) {
            arquivoRecebidoDelegate = new ArquivoRecebidoDelegate();
        }
        return arquivoRecebidoDelegate;
    }

    /**
     * 
     * @return BeneficiariosAlertaDelegate
     * 
     */
    public BeneficiariosAlertaDelegate getBeneficiariosAlertaDelegate() {
        if (beneficiariosAlertaDelegate == null) {
            beneficiariosAlertaDelegate = new BeneficiariosAlertaDelegate();
        }
        return beneficiariosAlertaDelegate;
    }

    /**
     * 
     * @return HistoricoMensagemDDADelegate
     * 
     */
    public HistoricoMensagemDDADelegate getHistoricoMensagemDDADelegate() {
        if (historicoMensagemDDADelegate == null) {
            historicoMensagemDDADelegate = new HistoricoMensagemDDADelegate();
        }
        return historicoMensagemDDADelegate;
    }

    /**
     * 
     * @return ArquivoEnviadoDelegate
     * 
     */
    public ArquivoEnviadoDelegate getArquivoEnviadoDelegate() {
        if (arquivoEnviadoDelegate == null) {
            arquivoEnviadoDelegate = new ArquivoEnviadoDelegate();
        }
        return arquivoEnviadoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return AdministrativoDelegate
     * 
     */
    public AdministrativoDelegate getAdministrativoDelegate() {
        if (administrativoDelegate == null) {
            administrativoDelegate = new AdministrativoDelegate();
        }
        return administrativoDelegate;
    }

    /**
     * @return o atributo manutencaoMensagemDDABoletoDelegate
     */
    public ManutencaoMensagemDDABoletoDelegate getManutencaoMensagemDDABoletoDelegate() {
        if (ObjectUtil.isNull(manutencaoMensagemDDABoletoDelegate)) {
            manutencaoMensagemDDABoletoDelegate = new ManutencaoMensagemDDABoletoDelegate();
        }
        return manutencaoMensagemDDABoletoDelegate;
    }

    /**
     * @return o delegate
     */
    public AgendamentoBoletoDDADelegate getAgendamentoBoletoDDADelegate() {
        if (agendamentoBoletoDDADelegate == null) {
            agendamentoBoletoDDADelegate = new AgendamentoBoletoDDADelegate();
        }
        return agendamentoBoletoDDADelegate;
    }

    /**
     * @return o delegate
     */
    public EventoTarifavelDDADelegate getEventoTarifavelDDADelegate() {
        if (eventoTarifavelDDADelegate == null) {
            eventoTarifavelDDADelegate = new EventoTarifavelDDADelegate();
        }
        return eventoTarifavelDDADelegate;
    }

    /**
     * @return o delegate
     */
    public RateioDDALancamentoDelegate getRateioDDALancamentoDelegate() {
        if (rateioDDALancamentoDelegate == null) {
            rateioDDALancamentoDelegate = new RateioDDALancamentoDelegate();
        }
        return rateioDDALancamentoDelegate;
    }

    /**
     * @return o delegate
     */
    public ConsolidarEventosTarifaveisCipDelegate getConsolidarEventosTarifaveisCipDelegate() {
        if (consolidarEventosTarifaveisCipDelegate == null) {
            consolidarEventosTarifaveisCipDelegate = new ConsolidarEventosTarifaveisCipDelegate();
        }
        return consolidarEventosTarifaveisCipDelegate;
    }

    /**
     * @return o delegate
     */
    public RateioTarifasCIPDelegate getRateioTarifasCIPDelegate() {
        if (rateioTarifasCIPDelegate == null) {
            rateioTarifasCIPDelegate = new RateioTarifasCIPDelegate();
        }
        return rateioTarifasCIPDelegate;
    }

}
