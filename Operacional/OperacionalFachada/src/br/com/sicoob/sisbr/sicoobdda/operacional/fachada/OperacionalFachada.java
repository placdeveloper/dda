package br.com.sicoob.sisbr.sicoobdda.operacional.fachada;

import java.util.List;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.AtualizaParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.ISicoobDDAComumFachada;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.util.ComumFachadaUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemConsultaBoletoPagamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDA0401Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDAAGEN001Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemGEN0014Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemSerie0200Delegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ArquivoEnviadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ArquivoRecebidoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.BeneficiariosAlertaDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ConsultaBoletoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ContingenciaDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.EventoTarifavelDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.GradeHorariaDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ManutencaoMensagemDDABoletoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.MonitoramentoMensagensDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioDDALancamentoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioTarifasCIPDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RequisitarArquivoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TipoErroCipDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TipoGradeHorariaDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TipoMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.TratamentoPendenciaErroDelegate;

/**
 * OperacionalFachada é responsável por
 * 
 * @author Felipe.Rosa
 */
public abstract class OperacionalFachada extends BancoobFachada implements ISicoobDDAComumFachada {

    private ComumFachadaUtil comumFachadaUtil = new ComumFachadaUtil();

    public static final SicoobLoggerPadrao LOG = SicoobLoggerPadrao.getInstance(OperacionalFachada.class);

    /**
     * Método responsável por
     * 
     * @return MensagemDDADelegate
     * 
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemGEN0014Delegate
     * 
     */
    public MensagemGEN0014Delegate getMensagemGEN0014Delegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemGEN0014Delegate();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemSerie0200Delegate
     * 
     */
    public MensagemSerie0200Delegate getMensagemSerie0200Delegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemSerie0200Delegate();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDA0401Delegate
     * 
     */
    public MensagemDDA0401Delegate getMensagemDDA0401Delegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemDDA0401Delegate();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemConsultaBoletoPagamentoDelegate
     * 
     */
    public MensagemConsultaBoletoPagamentoDelegate getMensagemConsultaBoletoPagamentoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemConsultaBoletoPagamentoDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return MonitoramentoMensagensDDADelegate
     * 
     */
    public MonitoramentoMensagensDDADelegate getMonitoramentoMensagensDDADelegate() {
        return OperacionalFabricaDelegates.getInstance().getMonitoramentoMensagensDDADelegate();
    }

    /**
     * Método responsável por
     * 
     * @return RequisitarArquivoDelegate
     * 
     */
    public RequisitarArquivoDelegate getRequisitarArquivoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getRequisitarArquivoDelegate();
    }

    /**
     * @return TipoGradeHoraria
     */
    public TipoGradeHorariaDelegate getTipoGradeHorariaDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTipoGradeHorariaDelegate();
    }

    /**
     * @return GradeHoraria
     */
    public GradeHorariaDelegate getGradeHorariaDelegate() {
        return OperacionalFabricaDelegates.getInstance().getGradeHorariaDelegate();
    }

    /**
     * @return TipoMensagem
     */
    public TipoMensagemDelegate getTipoMensagemDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTipoMensagemDelegate();
    }

    /**
     * 
     * Método responsável por criar o delegate de atualiza parametro
     * 
     * @return AtualizaParametroDelegate
     * 
     */
    public AtualizaParametroDelegate getAtualizaParametroDelegate() {
        return OperacionalFabricaDelegates.getInstance().getAtualizaParametroDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return TratamentoPendenciaErroDelegate
     * 
     */
    public TratamentoPendenciaErroDelegate getTratamentoPendenciaErroDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTratamentoPendenciaErroDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return TipoErroCipDelegate
     * 
     */
    public TipoErroCipDelegate getTipoErroCipDelegate() {
        return OperacionalFabricaDelegates.getInstance().getTipoErroCipDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADelegate
     * 
     */
    public PagadorEletronicoDDADelegate getPagadorEletronicoDDADelegate() {
        return OperacionalFabricaDelegates.getInstance().getPagadorEletronicoDDADelegate();
    }

    /**
     * @return ContingenciaDelegate
     */
    public ContingenciaDelegate getContingenciaDelegate() {
        return OperacionalFabricaDelegates.getInstance().getContingenciaDelegate();
    }

    /**
     * @return ArquivoRecebidoDelegate
     */
    public ArquivoRecebidoDelegate getArquivoRecebidoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getArquivoRecebidoDelegate();
    }

    /**
     * @return ManutencaoMensagemDDABoletoDelegate
     */
    public ManutencaoMensagemDDABoletoDelegate getManutencaoMensagemDDABoletoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getManutencaoMensagemDDABoletoDelegate();
    }

    /**
     * @return BeneficiariosAlertaDelegate
     */
    public BeneficiariosAlertaDelegate getBeneficiariosAlertaDelegate() {
        return OperacionalFabricaDelegates.getInstance().getBeneficiariosAlertaDelegate();
    }

    /**
     * @return ArquivoEnviadoDelegate
     */
    public ArquivoEnviadoDelegate getArquivoEnviadoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getArquivoEnviadoDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return ConsultaBoletoDDADelegate
     * 
     */
    public ConsultaBoletoDDADelegate getConsultaBoletoDDADelegate() {
        return OperacionalFabricaDelegates.getInstance().getConsultaBoletoDDADelegate();
    }

    /**
     * @throws ComumException
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.iface.ICobrancaComumFachada#getInstituicao()
     */
    public InstituicaoDto getInstituicao() throws ComumException {
        return this.comumFachadaUtil.getInstituicao();
    }

    /**
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.iface.ICobrancaComumFachada#getUsuario()
     */
    public UsuarioBancoob getUsuario() {
        return this.comumFachadaUtil.getUsuario();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comumfachada.iface.ISicoobDDAComumFachada#getContextoHttp()
     */
    public ContextoHttp getContextoHttp() {
        return this.comumFachadaUtil.getContextoHttp();
    }

    /**
     * @return
     */
    public ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebidoCIPProcessadorServicoDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();

    }

    /**
     * @return
     */
    public MensagemDDAAGEN001Delegate getMensagemDDAAGEN001Delegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemEGEN001Delegate();
    }

    /**
     * @return
     */
    public EventoTarifavelDDADelegate getEventoTarifavelDDADelegate() {
        return OperacionalFabricaDelegates.getInstance().getEventoTarifavelDDADelegate();
    }

    /**
     * Método responsável por retornar o delegate
     * 
     * @return
     */
    public RateioDDALancamentoDelegate getRateioDDALancamentoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getRateioDDALancamentoDelegate();
    }

    /**
     * Método responsável por retornar o delegate
     * 
     * @return
     */
    public RateioTarifasCIPDelegate getRateioTarifasCIPDelegate() {
        return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate();
    }

    /**
     * Método responsável por
     * 
     * @param lista
     * @return RetornoDTO
     * 
     */
    protected RetornoDTO montarListaRetorno(List<? extends BancoobVO> lista) {
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", lista);
        return retorno;
    }
}
