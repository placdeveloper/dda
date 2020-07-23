/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         PagadorEletronicoDDAServico.java
 * Data Criação:    Dec 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PagadorAgregadoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PagadorDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TermoPagadorDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico;

/**
 * PagadorEletronicoDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public class PagadorEletronicoDDAServico extends OperacionalFachada implements IPagadorEletronicoDDAServico {

    private static final String DTO = "dto";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#pesquisarPagadorEletronicoPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarPagadorEletronicoPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<PagadorDTO> consultaDTO = montarConsultaDto(dto, PagadorDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consultaDTO.getFiltro();
        consultaDTO.setFiltro(ObjectUtil.isNull(reqDto) ? null : reqDto.getDados().get(DTO));

        ConsultaDto<PagadorDTO> consulta = getPagadorEletronicoDDADelegate().pesquisar(PagadorDto.class, consultaDTO, PesquisaEnum.PESQUISAR_PAGADOR_ELETRONICO);
        consulta.setResultado((List<PagadorDTO>) ConversorVO.getInstance().converter(consulta.getResultado()));
        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#pesquisarPagadorAgregadoPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarPagadorAgregadoPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<PagadorAgregadoDTO> consultaDTO = montarConsultaDto(dto, PagadorAgregadoDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consultaDTO.getFiltro();
        consultaDTO.setFiltro(ObjectUtil.isNull(reqDto) ? null : reqDto.getDados().get(DTO));

        ConsultaDto<PagadorAgregadoDTO> consulta = getPagadorEletronicoDDADelegate().pesquisar(PagadorAgregadoDto.class, consultaDTO, PesquisaEnum.PESQUISAR_AGREGADO);
        consulta.setResultado((List<PagadorAgregadoDTO>) ConversorVO.getInstance().converter(consulta.getResultado()));
        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#obterDetalharPagador(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDetalharPagador(RequisicaoReqDTO dto) throws BancoobException {
        String numCpfCnpj = getNumCpfCnpj(dto);

        PagadorDto pagador = getPagadorEletronicoDDADelegate().obterDadosPagador(numCpfCnpj);
        return preparaRetorno(pagador);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#obterDadosPagador(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterDadosPagador(RequisicaoReqDTO dto) throws BancoobException {
        String numCpfCnpj = getNumCpfCnpj(dto);
        Integer idInstituicao = getIdInstituicao(dto);

        PagadorDto pagador = getPagadorEletronicoDDADelegate().obterDadosPagador(numCpfCnpj, idInstituicao);
        return preparaRetorno(pagador);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#solicitarAdesao(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO solicitarAdesao(RequisicaoReqDTO dto) throws BancoobException {
        PagadorDTO pagadorDTO = getPagadorDTO(dto);
        PagadorDto pagador = getPagadorEletronicoDDADelegate().solicitarAdesao((PagadorDto) ConversorVO.getInstance().converter(pagadorDTO), CanalEnum.RETAGUARDA.getId(),
                Boolean.FALSE);
        return preparaRetorno(pagador);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#solicitarCancelamentoAdesao(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO solicitarCancelamentoAdesao(RequisicaoReqDTO dto) throws BancoobException {
        PagadorDTO pagadorDTO = getPagadorDTO(dto);
        PagadorDto pagador = getPagadorEletronicoDDADelegate().solicitarCancelamentoAdesao((PagadorDto) ConversorVO.getInstance().converter(pagadorDTO),
                CanalEnum.RETAGUARDA.getId(), Boolean.FALSE);
        return preparaRetorno(pagador);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#solicitarInclusaoPagadorAgregado(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO solicitarInclusaoPagadorAgregado(RequisicaoReqDTO dto) throws BancoobException {
        String numCpfCnpj = getNumCpfCnpj(dto);
        Integer idInstituicao = getIdInstituicao(dto);
        PagadorAgregadoDTO pagadorAgregadoDTO = (PagadorAgregadoDTO) dto.getDados().get("pagadorAgregado");
        PagadorAgregadoDto pagadorAgregadoDto = (PagadorAgregadoDto) ConversorVO.getInstance().converter(pagadorAgregadoDTO);

        PagadorDto pagador = getPagadorEletronicoDDADelegate().solicitarInclusaoPagadorAgregado(pagadorAgregadoDto, numCpfCnpj, idInstituicao, CanalEnum.RETAGUARDA.getId());
        return preparaRetorno(pagador);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#solicitarExclusaoPagadorAgregado(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO solicitarExclusaoPagadorAgregado(RequisicaoReqDTO dto) throws BancoobException {
        String numCpfCnpj = getNumCpfCnpj(dto);
        Integer idInstituicao = getIdInstituicao(dto);
        PagadorAgregadoDTO pagadorAgregadoDTO = (PagadorAgregadoDTO) dto.getDados().get("pagadorAgregado");
        PagadorAgregadoDto pagadorAgregadoDto = (PagadorAgregadoDto) ConversorVO.getInstance().converter(pagadorAgregadoDTO);

        PagadorDto pagador = getPagadorEletronicoDDADelegate().solicitarExclusaoPagadorAgregado(pagadorAgregadoDto, numCpfCnpj, idInstituicao, CanalEnum.RETAGUARDA.getId());

        return preparaRetorno(pagador);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.com.sicoob.sisbr.sicoobdda.operacional.iface.IPagadorEletronicoDDAServico#obterTermoPagadorEletronico(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterTermoPagadorEletronico(RequisicaoReqDTO dto) throws BancoobException {
        DateTimeDB dataInicioVigencia = getDataInicioVigencia(dto);
        Short codTipoTermoPagador = getCodTipoTermoPagador(dto);
        Boolean bolFormatoHtml = getBolFormatoHtml(dto);

        return preparaRetorno(getPagadorEletronicoDDADelegate().obterTermoPagadorEletronico(dataInicioVigencia, null, codTipoTermoPagador, bolFormatoHtml,
                Integer.valueOf(getUsuario().getCooperativa())));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return DateTimeDB
     * 
     */
    private DateTimeDB getDataInicioVigencia(RequisicaoReqDTO dto) {
        return (DateTimeDB) dto.getDados().get("dataInicioVigencia");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return Short
     * 
     */
    private Short getCodTipoTermoPagador(RequisicaoReqDTO dto) {
        return (Short) dto.getDados().get("codTipoTermoPagador");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return Boolean
     * 
     */
    private Boolean getBolFormatoHtml(RequisicaoReqDTO dto) {
        return (Boolean) dto.getDados().get("bolFormatoHtml");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String getNumCpfCnpj(RequisicaoReqDTO dto) {
        return (String) dto.getDados().get("numCpfCnpj");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return Integer
     * 
     */
    private Integer getIdInstituicao(RequisicaoReqDTO dto) {
        return (Integer) dto.getDados().get("idInstituicao");
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return PagadorDTO
     * 
     */
    private PagadorDTO getPagadorDTO(RequisicaoReqDTO dto) {
        return (PagadorDTO) dto.getDados().get("pagadorDTO");
    }

    /**
     * Método responsável por
     * 
     * @param pagador
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    private RetornoDTO preparaRetorno(PagadorDto pagador) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put("pagador", (PagadorDTO) ConversorVO.getInstance().converter(pagador));
        return retornoDTO;
    }

    /**
     * @param termoPagadorDto
     * @return
     * @throws ComumException
     */
    private RetornoDTO preparaRetorno(TermoPagadorDto termoPagadorDto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put("dto", (TermoPagadorDTO) ConversorVO.getInstance().converter(termoPagadorDto));
        return retornoDTO;
    }

}
