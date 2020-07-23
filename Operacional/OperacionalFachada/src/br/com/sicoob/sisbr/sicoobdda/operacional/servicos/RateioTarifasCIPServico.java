package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaEventoRateioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ControleRateioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoRateioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.VisualizaRateioTarifaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RateioDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoRateioVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico;

/**
 * RateioTarifasCIPServico é responsável por
 * 
 * @author Rodrigo.Neri
 */
@RemoteService
public class RateioTarifasCIPServico extends OperacionalFachada implements IRateioTarifasCIPServico {

    private static final String DTO = "dto";
    private static final String LISTA = "lista";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#pesquisarLancamentos(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO pesquisarLancamentos(RequisicaoReqDTO reqDto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        VisualizaRateioTarifaDTO visualRateioDTO = (VisualizaRateioTarifaDTO) reqDto.getDados().get("dto");
        VisualizaRateioTarifaDto visualRateioDto = (VisualizaRateioTarifaDto) ConversorVO.getInstance().converter(visualRateioDTO);
        retornoDTO.getDados().put(LISTA, (List<VisualizaRateioTarifaDto>) ConversorVO.getInstance().converter(getRateioTarifasCIPDelegate().pesquisarLancamento(visualRateioDto)));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#pesquisarDadosRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO pesquisarDadosRateio(RequisicaoReqDTO reqDto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        VisualizaRateioTarifaDTO visualRateioDTO = (VisualizaRateioTarifaDTO) reqDto.getDados().get("dto");
        VisualizaRateioTarifaDto visualRateioDto = (VisualizaRateioTarifaDto) ConversorVO.getInstance().converter(visualRateioDTO);
        retornoDTO.getDados().put(DTO, (VisualizaRateioTarifaDTO) ConversorVO.getInstance().converter(getRateioTarifasCIPDelegate().pesquisarDadosRateio(visualRateioDto)));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#obterDadosLancamentoRateioDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosLancamentoRateioDDA(RequisicaoReqDTO reqDto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Integer idRateioDDALancamento = (Integer) reqDto.getDados().get("id");

        retornoDTO.getDados().put("dto",
                (VisualizaRateioTarifaDTO) ConversorVO.getInstance().converter(getRateioTarifasCIPDelegate().obterDadosLancamentoRateioDDA(idRateioDDALancamento.longValue())));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#listaSituacaoRateio()
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listaSituacaoRateio() throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        List<SituacaoRateioVO> listaSituacaoRateio = (List<SituacaoRateioVO>) ConversorVO.getInstance().converter(getRateioTarifasCIPDelegate().listaSituacaoRateio());
        retornoDTO.getDados().put(LISTA, listaSituacaoRateio);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#pesquisaRateioTarifaDDAPaginada(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisaRateioTarifaDDAPaginada(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<VisualizaRateioTarifaDTO> consultaDTO = montarConsultaDto(dto, VisualizaRateioTarifaDTO.class);

        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDTO.getFiltro();
        VisualizaRateioTarifaDTO filtro = (VisualizaRateioTarifaDTO) requisicaoReqDTO.getDados().get("dto");
        consultaDTO.setFiltro(filtro);

        ConsultaDto<VisualizaRateioTarifaDto> consultaDto = new ConsultaDto<VisualizaRateioTarifaDto>();
        popularConsultaDto(consultaDto, dto);

        VisualizaRateioTarifaDto filtroDto = (VisualizaRateioTarifaDto) ConversorVO.getInstance().converter(filtro);
        consultaDto.setFiltro(filtroDto);

        consultaDto = getRateioTarifasCIPDelegate().pesquisarRateioTarifaDDAPaginado(consultaDto);

        consultaDTO.setResultado((List<VisualizaRateioTarifaDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        consultaDTO.setTotalRegistros(consultaDto.getTotalRegistros());

        return montarResultado(consultaDTO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#pesquisaRateioTarifaPaginada(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisaRateioTarifaPaginada(SelGeralReqDTO dto) throws ComumException {
        ConsultaDto<VisualizaRateioTarifaDto> consulta = montarConsultaDto(dto, VisualizaRateioTarifaDto.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(ObjectUtil.isNull(reqDto) ? null : reqDto.getDados().get(DTO.toString()));

        ConsultaDto<VisualizaRateioTarifaDto> consultaDto = getRateioTarifasCIPDelegate().pesquisar(VisualizaRateioTarifaDto.class, consulta,
                PesquisaEnum.PESQUISAR_VISUALIZAR_RATEIO_TARIFAS);
        consultaDto.setResultado((List<VisualizaRateioTarifaDto>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#detalharRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO detalharRateio(RequisicaoReqDTO reqDto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        VisualizaRateioTarifaDTO visualRateioDTO = (VisualizaRateioTarifaDTO) reqDto.getDados().get("dto");
        VisualizaRateioTarifaDto visualRateioDto = (VisualizaRateioTarifaDto) ConversorVO.getInstance().converter(visualRateioDTO);
        retornoDTO.getDados().put("dto", (VisualizaRateioTarifaDTO) ConversorVO.getInstance().converter(getRateioTarifasCIPDelegate().detalharRateioTarifas(visualRateioDto)));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#efetivacaoManual(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO efetivacaoManual(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Integer idRateioDDALancamento = (Integer) reqDto.getDados().get("id");
        retornoDTO.getDados().put("codMsgRetorno", getRateioTarifasCIPDelegate().realizarEfetivacaoManual(idRateioDDALancamento.longValue()));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IVisualizarRateioTarifaServico#efetivacaoManual(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO transferenciaDebito(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Integer idRateioDDALancamento = (Integer) reqDto.getDados().get("id");
        Integer numCooperativa = (Integer) reqDto.getDados().get("numCooperativa");
        Integer codSituacaoLancamento = (Integer) reqDto.getDados().get("codSituacaoLancamento");
        retornoDTO.getDados().put("codMsgRetorno",
                getRateioTarifasCIPDelegate().realizaTransferenciaDebito(idRateioDDALancamento.longValue(), numCooperativa, codSituacaoLancamento.longValue()));
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#pesquisarEventosDisponiveisPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO pesquisarEventosDisponiveis(RequisicaoReqDTO dto) throws BancoobException {
        ConsultaEventoRateioDTO eventoRateioDTO = (ConsultaEventoRateioDTO) dto.getDados().get("dto");

        List<EventoRateioDto> lista = getRateioTarifasCIPDelegate().pesquisarEventosDisponiveis((ConsultaEventoRateioDto) ConversorVO.getInstance().converter(eventoRateioDTO));

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", (List<EventoRateioDTO>) ConversorVO.getInstance().converter(lista));

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#obterDadosControleRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosControleRateio(RequisicaoReqDTO dto) throws BancoobException {
        ControleRateioDto controleRateio = getRateioTarifasCIPDelegate().obterDadosControleRateio();

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("dto", (ControleRateioDTO) ConversorVO.getInstance().converter(controleRateio));

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#incluirEventoConsolidadoRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO incluirEventoConsolidadoRateio(RequisicaoReqDTO dto) throws BancoobException {
        List<EventoRateioDTO> listaDTO = (List<EventoRateioDTO>) dto.getDados().get("lista");

        RateioDDA rateio = getRateioTarifasCIPDelegate().incluirListaEventoConsolidadoRateio((List<EventoRateioDto>) ConversorVO.getInstance().converter(listaDTO));

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("vo", (RateioDDAVO) ConversorVO.getInstance().converter(rateio));

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#removerEventoConsolidadoRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO removerEventoConsolidadoRateio(RequisicaoReqDTO dto) throws BancoobException {
        List<EventoRateioDTO> listaDTO = (List<EventoRateioDTO>) dto.getDados().get("lista");

        getRateioTarifasCIPDelegate().removerListaEventoConsolidadoRateio((List<EventoRateioDto>) ConversorVO.getInstance().converter(listaDTO));

        RetornoDTO retorno = new RetornoDTO();

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#atualizarEventoConsolidado(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO atualizarEventoConsolidado(RequisicaoReqDTO dto) throws BancoobException {
        Object desvioPadraoObj = dto.getDados().get("desvioPadrao");
        BigDecimal desvioPadrao = null;

        if (!ObjectUtil.isNull(desvioPadraoObj)) {
            desvioPadrao = new BigDecimal(desvioPadraoObj.toString());
        }

        List<EventoRateioDTO> lista = (List<EventoRateioDTO>) dto.getDados().get("lista");

        getRateioTarifasCIPDelegate().atualizarEventoConsolidado(desvioPadrao, (List<EventoRateioDto>) ConversorVO.getInstance().converter(lista));

        return new RetornoDTO();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#aprovarRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO aprovarRateio(RequisicaoReqDTO dto) throws BancoobException {
        UsuarioBancoob usuario = getUsuario();

        RateioDDA rateioDDA = getRateioTarifasCIPDelegate().aprovarRateio(usuario.getLogin(), Integer.parseInt(usuario.getIdInstituicao()));

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("vo", (RateioDDAVO) ConversorVO.getInstance().converter(rateioDDA));

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRateioTarifasCIPServico#cancelarAprovacaoRateio(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO cancelarAprovacaoRateio(RequisicaoReqDTO dto) throws BancoobException {
        RateioDDA rateioDDA = getRateioTarifasCIPDelegate().cancelarAprovacaoRateio();

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("vo", (RateioDDAVO) ConversorVO.getInstance().converter(rateioDDA));

        return retorno;
    }

}