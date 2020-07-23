package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoRecebidoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.IdentificadorDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetRecebimentoArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetalheEnvioArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido;

/**
 * GradeHorariaServico
 * 
 * @author samuell.ramos
 */
@RemoteService
public class ArquivoRecebidoServico extends OperacionalFachada implements IArquivoRecebido {

    private static final String DTO = "dto";
    private static final String VO = "vo";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#alterarSituacaoArquivoRecebido(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void alterarSituacaoArquivoRecebido(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        Integer idLogDetRecArquivoDDA = (Integer) dto.getDados().get("idLogRecebimentoArqDDA");
        Integer codSituacao = (Integer) dto.getDados().get("codSituacao");
        getArquivoRecebidoDelegate().alterarSituacaoArquivoRecebido(idLogDetRecArquivoDDA.longValue(), codSituacao.shortValue());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#alterarSituacaoRegistro(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void alterarSituacaoRegistro(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        LogDetRecebimentoArquivoDDAVO logDetRecebimentoArquivoDDAVO = (LogDetRecebimentoArquivoDDAVO) dto.getDados().get(VO);
        getArquivoRecebidoDelegate().alterarSituacaoRegistro((LogDetRecebimentoArquivoDDA) ConversorVO.getInstance().converter(logDetRecebimentoArquivoDDAVO));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#pesquisarArquivoRecebidoPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarArquivoRecebidoPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<ArquivoRecebidoDTO> consulta = montarConsultaDto(dto, ArquivoRecebidoDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(ObjectUtil.isNull(reqDto) ? null : reqDto.getDados().get(DTO.toString()));

        ConsultaDto<ArquivoRecebidoDTO> consultaDto = getArquivoRecebidoDelegate().pesquisar(ArquivoRecebidoDto.class, consulta, PesquisaEnum.PESQUISAR_ARQUIVO_RECEBIDO);
        consultaDto.setResultado((List<ArquivoRecebidoDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        if (ObjectUtil.isNull(consultaDto.getResultado()) || ObjectUtil.isEmpty(consultaDto.getResultado())) {
            throw new ComumNegocioException("Nenhum item encontrado");
        }
        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#carregarFiltrosArquivoRecebido()
     */
    public RetornoDTO carregarFiltrosArquivoRecebido() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        ArquivoRecebidoDTO listaFiltrosArquivoRec = (ArquivoRecebidoDTO) ConversorVO.getInstance().converter(getArquivoRecebidoDelegate().carregarListasArquivoRecebido());
        retornoDTO.getDados().put("filtros", listaFiltrosArquivoRec);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#obterArquivoRecebido(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterArquivoRecebido(RequisicaoReqDTO dto) throws ComumException {
        IdentificadorDTO identificadorDTO = (IdentificadorDTO) dto.getDados().get("identificadorDTO");
        return preparaRetornoArquivoDTO(getArquivoRecebidoDelegate().obterArquivoRecebido(identificadorDTO.getIdentificadorGeral()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#pesquisarLogDetRecArquivoDDAPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarLogDetRecArquivoDDAPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<ArquivoRecebidoDTO> consulta = montarConsultaDto(dto, ArquivoRecebidoDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(reqDto.getDados().get(DTO));

        ConsultaDto<LogDetRecebimentoArquivoDDAVO> consultaDto = getArquivoRecebidoDelegate().pesquisar(LogDetalheEnvioArquivoDDAVO.class, consulta,
                PesquisaEnum.LISTAR_LOG_DET_RECEBIMENTO_ARQUIVODDA);
        consultaDto.setResultado((List<LogDetRecebimentoArquivoDDAVO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#obterLogDetRecebimentoArquivoDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterLogDetRecebimentoArquivoDDA(RequisicaoReqDTO dto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        IdentificadorDTO identificadorDTO = (IdentificadorDTO) dto.getDados().get("identificadorDTO");
        LogDetRecebimentoArquivoDDAVO logDetRecebimentoArquivoDDAVO = (LogDetRecebimentoArquivoDDAVO) ConversorVO.getInstance().converter(
                getArquivoRecebidoDelegate().obterDetLogRecebimentoArquivoDDA(identificadorDTO.getIdentificadorGeral()));
        retornoDTO.getDados().put("VO", logDetRecebimentoArquivoDDAVO);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#descriptografarArquivo(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO descriptografarArquivo(RequisicaoReqDTO dto) throws BancoobException {
        ArquivoRecebidoDto arquivoDto = getArquivoRecebidoDto(dto);
        arquivoDto = getArquivoRecebidoDelegate().descriptografarArquivo(arquivoDto);
        return preparaRetornoArquivoDTO(arquivoDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#gravarDetalheArquivo(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO gravarDetalheArquivo(RequisicaoReqDTO dto) throws BancoobException {
        ArquivoRecebidoDto arquivoDto = getArquivoRecebidoDto(dto);
        arquivoDto = getArquivoRecebidoDelegate().gravarDetalheArquivo(arquivoDto);
        return preparaRetornoArquivoDTO(arquivoDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoRecebido#processarArquivo(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO processarArquivo(RequisicaoReqDTO dto) throws BancoobException {
        ArquivoRecebidoDto arquivoDto = getArquivoRecebidoDto(dto);
        arquivoDto = getArquivoRecebidoDelegate().processarArquivo(arquivoDto);
        return preparaRetornoArquivoDTO(arquivoDto);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException ArquivoRecebidoDto
     * 
     */
    private ArquivoRecebidoDto getArquivoRecebidoDto(RequisicaoReqDTO dto) throws ComumException {
        ArquivoRecebidoDTO arquivoDTO = (ArquivoRecebidoDTO) dto.getDados().get(DTO);
        ArquivoRecebidoDto arquivoDto = (ArquivoRecebidoDto) ConversorVO.getInstance().converter(arquivoDTO);
        return arquivoDto;
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     * 
     */
    private RetornoDTO preparaRetornoArquivoDTO(ArquivoRecebidoDto dto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put(DTO, (ArquivoRecebidoDTO) ConversorVO.getInstance().converter(dto));
        return retornoDTO;
    }

    /**
     * Método responsável por
     * 
     * @param obj
     * @return Long
     * 
     */
    private Long parseLong(Object obj) {
        if (!ObjectUtil.isNull(obj)) {
            return Long.parseLong(obj.toString());
        }
        return null;
    }
}
