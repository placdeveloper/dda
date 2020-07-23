/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.servico
 * Arquivo:         RelatorioServico.java
 * Data Criação:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RequisicaoRelatorioDTO;
import br.com.sicoob.relatorio.api.dto.RetornoRelatorioDTO;
import br.com.sicoob.relatorio.api.dto.SolicitacaoRelatorioDTO;
import br.com.sicoob.relatorio.fachada.servicos.ServicoRelatorio;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaHistoricoMensagem106Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.GsonUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaEventoRateioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaMovimentoSicoobDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTarifasDDAPagasDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ControleRateioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LancamentosTarifasDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.RelatorioDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.MensagemDDADto;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRelatorioServico;

/**
 * RelatorioServico é responsável por
 * 
 * @author Felipe.Rosa
 */
@RemoteService
public class RelatorioServico extends BancoobFachada implements IRelatorioServico {

    private static final String FILTRO = "filtro";
    private static final String JSON_FILTRO = "jsonFiltro";
    private static final String JSON_LISTA_DADOS = "jsonListaDados";
    private static final String JNDI_RELATORIO_SERVICO = "sicoobdda_operacional/RelatorioServicoRemote";

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IRelatorioServico#gerar(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public List<RetornoRelatorioDTO> gerar(RequisicaoReqDTO dto) throws ComumException {
        RelatorioDTO filtroDTO = (RelatorioDTO) dto.getDados().get(FILTRO);

        ParametroDTO parametroDTO = null;

        RelatorioSicoobDDAEnum relatorio = RelatorioSicoobDDAEnum.getByContextoFlex(filtroDTO.getContextoFlex());

        switch (relatorio) {
        case SDDA501:
            parametroDTO = obterParametroMensagemDDADto(filtroDTO);
            break;
        case SDDA503:
            // Não possui parâmetros
            parametroDTO = new ParametroDTO();
            break;
        case SDDA504:
            parametroDTO = obterParametroBeneficiariosAlertaFiltroDto(filtroDTO);
            break;
        case SDDA505:
            parametroDTO = obterParametroTermoPagadorEletronico(filtroDTO);
            break;
        case SDDA506:
            parametroDTO = obterParametroDetalheBoleto(dto);
            break;
        case SDDA507:
        case SDDA508:
            parametroDTO = obterParametroPagadorEletronico(filtroDTO);
            break;
        case SDDA509:
            parametroDTO = obterParametroPagadorAgregado(dto);
            break;
        case SDDA510:
            parametroDTO = obterParametroConsultaBoletoDDA(filtroDTO);
            break;
        case SDDA511:
            parametroDTO = obterParametroHistoricoConsultaBoletoDDA(filtroDTO);
            break;
        case SDDA512:
            parametroDTO = obterParametroEventoTarifavelDDA(filtroDTO);
            break;
        case SDDA513:
        case SDDA514:
            parametroDTO = obterParametrosRateioDDA(filtroDTO);
            break;
        case SDDA515:
            parametroDTO = obterParametrosMovimentoDDA(dto);
            break;
        case SDDA516:
            parametroDTO = obterParametrosEventosDisponiveis(dto);
            break;
        case SDDA517:
            parametroDTO = obterParametroEventosRateio(filtroDTO);
            break;
        case SDDA518:
        case SDDA519:
        case SDDA520:
            parametroDTO = obterParametroRateio(filtroDTO);
            break;
        default:
            throw new ComumException("Relatório não cadastrado: " + filtroDTO.getContextoFlex());
        }
        return gerar(relatorio, parametroDTO, filtroDTO.getFormato());
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroMensagemDDADto(RelatorioDTO filtroDTO) throws ComumException {
        MensagemDDADto mensagemDDADto = (MensagemDDADto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(FILTRO, mensagemDDADto.getIdMensagem());
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return
     * @throws ComumException String
     * 
     */
    private ParametroDTO obterParametroBeneficiariosAlertaFiltroDto(RelatorioDTO filtroDTO) throws ComumException {
        BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto = (BeneficiariosAlertaFiltroDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(beneficiariosAlertaFiltroDto, BeneficiariosAlertaFiltroDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroTermoPagadorEletronico(RelatorioDTO filtroDTO) throws ComumException {
        HistoricoPagadorEletronicoDto historicoPagdorEletroDto = (HistoricoPagadorEletronicoDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(historicoPagdorEletroDto, HistoricoPagadorEletronicoDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroDetalheBoleto(RequisicaoReqDTO dto) throws ComumException {
        String numCodigoBarra = (String) dto.getDados().get("numCodigoBarra");
        String codSituacaoBoleto = String.valueOf( (Integer) dto.getDados().get("codSituacaoBoleto"));
        BoletoDDAVO boletoDDAVO = (BoletoDDAVO) dto.getDados().get("boletoDDAVO");

        BoletoDDA boletoDDA = (BoletoDDA) ConversorVO.getInstance().converter(boletoDDAVO, Boolean.TRUE);

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put("numCodigoBarra", numCodigoBarra);
        parametroDTO.getDados().put("codSituacaoBoleto", codSituacaoBoleto);
        parametroDTO.getDados().put("jsonLancamentosTarifasDDADto", GsonUtil.getGson().toJson(boletoDDA, BoletoDDA.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * 
     */
    private ParametroDTO obterParametroPagadorEletronico(RelatorioDTO filtroDTO) {
        String filtro = (String) filtroDTO.getFiltro();

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(FILTRO, filtro);
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * 
     */
    private ParametroDTO obterParametroPagadorAgregado(RequisicaoReqDTO dto) {
        Integer numAgencia = (Integer) dto.getDados().get("numAgencia");
        String numCpfCnpj = (String) dto.getDados().get("numCpfCnpj");
        Integer codCentral =  (Integer) dto.getDados().get("codCentral");

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put("numAgencia", numAgencia);
        parametroDTO.getDados().put("numCpfCnpj", numCpfCnpj);
        parametroDTO.getDados().put("codCentral", codCentral);
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroConsultaBoletoDDA(RelatorioDTO filtroDTO) throws ComumException {
        PesquisaBoletoDDADto pesquisaBoletoDDADto = (PesquisaBoletoDDADto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(pesquisaBoletoDDADto, PesquisaBoletoDDADto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    @SuppressWarnings("unchecked")
    private ParametroDTO obterParametroHistoricoConsultaBoletoDDA(RelatorioDTO filtroDTO) throws ComumException {
        List<HistoricoMensagem106Dto> listaHistoricoDto = (List<HistoricoMensagem106Dto>) ConversorVO.getInstance().converter(filtroDTO.getListaDados());

        ListaHistoricoMensagem106Dto listaHistorico = new ListaHistoricoMensagem106Dto(listaHistoricoDto);

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_LISTA_DADOS, listaHistorico.getJson());
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroEventoTarifavelDDA(RelatorioDTO filtroDTO) throws ComumException {
        FiltroRelatorioEventoTarifavelDto filtroRelatorioEventoTarifavelDto = (FiltroRelatorioEventoTarifavelDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(filtroRelatorioEventoTarifavelDto, FiltroRelatorioEventoTarifavelDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return
     * @throws ComumException ParametroDTO
     * 
     */
    @SuppressWarnings("unchecked")
    private ParametroDTO obterParametrosRateioDDA(RelatorioDTO filtroDTO) throws ComumException {
        ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto = (ConsultaTarifasDDAPagasDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());
        List<LancamentosTarifasDDADto> listaLancamentosDto = (List<LancamentosTarifasDDADto>) ConversorVO.getInstance().converter(filtroDTO.getListaDados());

        ListaLancamentosTarifasDDADto listaLancamento = new ListaLancamentosTarifasDDADto(listaLancamentosDto);

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(consultaTarifasDDAPagasDto, ConsultaTarifasDDAPagasDto.class));
        parametroDTO.getDados().put(JSON_LISTA_DADOS, listaLancamento.getJson());
        return parametroDTO;

    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametrosMovimentoDDA(RequisicaoReqDTO dto) throws ComumException {
        ConsultaMovimentoSicoobDDADTO consultaMovimento = (ConsultaMovimentoSicoobDDADTO) dto.getDados().get("consultaDTO");
        ConsultaTarifasDDAPagasDTO consultaTarifa = (ConsultaTarifasDDAPagasDTO) dto.getDados().get("consultaTarifasDTO");
        LancamentosTarifasDDADTO lancamento = (LancamentosTarifasDDADTO) dto.getDados().get("lancamentosDTO");

        ConsultaMovimentoSicoobDDADto consultaDto = (ConsultaMovimentoSicoobDDADto) ConversorVO.getInstance().converter(consultaMovimento);
        ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto = (ConsultaTarifasDDAPagasDto) ConversorVO.getInstance().converter(consultaTarifa);
        LancamentosTarifasDDADto lancamentosTarifasDDADto = (LancamentosTarifasDDADto) ConversorVO.getInstance().converter(lancamento);

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put("jsonConsultaDto", GsonUtil.getGson().toJson(consultaDto, ConsultaMovimentoSicoobDDADto.class));
        parametroDTO.getDados().put("jsonConsultaTarifasDDAPagasDto", GsonUtil.getGson().toJson(consultaTarifasDDAPagasDto, ConsultaTarifasDDAPagasDto.class));
        parametroDTO.getDados().put("jsonLancamentosTarifasDDADto", GsonUtil.getGson().toJson(lancamentosTarifasDDADto, LancamentosTarifasDDADto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametrosEventosDisponiveis(RequisicaoReqDTO dto) throws ComumException {
        ControleRateioDTO controleRateioDTO = (ControleRateioDTO) dto.getDados().get("controleRateioDto");
        ConsultaEventoRateioDTO consultaTarifaDTO = (ConsultaEventoRateioDTO) dto.getDados().get("consultaDto");

        ControleRateioDto controleRateioDto = (ControleRateioDto) ConversorVO.getInstance().converter(controleRateioDTO);
        ConsultaEventoRateioDto consultaTarifa = (ConsultaEventoRateioDto) ConversorVO.getInstance().converter(consultaTarifaDTO);

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put("jsonControleRateioDto", GsonUtil.getGson().toJson(controleRateioDto, ControleRateioDto.class));
        parametroDTO.getDados().put("jsonConsultaDto", GsonUtil.getGson().toJson(consultaTarifa, ConsultaEventoRateioDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroEventosRateio(RelatorioDTO filtroDTO) throws ComumException {
        ControleRateioDto controleRateioDto = (ControleRateioDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(controleRateioDto, ControleRateioDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param filtroDTO
     * @return ParametroDTO
     * @throws ComumException
     * 
     */
    private ParametroDTO obterParametroRateio(RelatorioDTO filtroDTO) throws ComumException {
        VisualizaRateioTarifaDto visualizaRateioTarifaDto = (VisualizaRateioTarifaDto) ConversorVO.getInstance().converter(filtroDTO.getFiltro());

        ParametroDTO parametroDTO = new ParametroDTO();
        parametroDTO.getDados().put(JSON_FILTRO, GsonUtil.getGson().toJson(visualizaRateioTarifaDto, VisualizaRateioTarifaDto.class));
        return parametroDTO;
    }

    /**
     * Método responsável por
     * 
     * @param relatorio
     * @param jsonFiltro
     * @param formato
     * @return
     * @throws ComumException List<RetornoRelatorioDTO>
     * 
     */
    private List<RetornoRelatorioDTO> gerar(RelatorioSicoobDDAEnum relatorio, ParametroDTO parametroDTO, String formato) throws ComumException {
        parametroDTO.getDados().put(Constantes.RELATORIO_CONTEXTO_FLEX, relatorio.getContextoFlex());

        SolicitacaoRelatorioDTO solicitacaoRelatorioDTO = new SolicitacaoRelatorioDTO();
        solicitacaoRelatorioDTO.setParametro(parametroDTO);
        solicitacaoRelatorioDTO.setServico(JNDI_RELATORIO_SERVICO);
        solicitacaoRelatorioDTO.setNome(relatorio.getContextoFlex());

        List<SolicitacaoRelatorioDTO> solicitacaoRelatorioDTOs = new ArrayList<>();
        solicitacaoRelatorioDTOs.add(solicitacaoRelatorioDTO);

        RequisicaoRelatorioDTO requisicaoRelatorioDTO = new RequisicaoRelatorioDTO();
        requisicaoRelatorioDTO.setLista(solicitacaoRelatorioDTOs);
        requisicaoRelatorioDTO.setFormato(formato);

        ServicoRelatorio servicoRelatorio = new ServicoRelatorio();
        try {
            return servicoRelatorio.processarRelatorio(requisicaoRelatorioDTO);
        } catch (Exception e) { // NOSONAR
            throw new ComumException(e.getMessage());
        }
    }

}
