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
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeHorariaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoGradeHorariaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * GradeHorariaServico
 * 
 * @author samuell.ramos
 */
@RemoteService
public class GradeHorariaServico extends OperacionalFachada implements IGradeHoraria {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#listarTipoGradeHoraria()
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarTipoGradeHoraria() throws OperacionalException, ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        List<TipoGradeHorariaDTO> listaTipoGradeHoraria = (List<TipoGradeHorariaDTO>) ConversorVO.getInstance().converter(getGradeHorariaDelegate().listarTipoGradeHoraria());
        retornoDTO.getDados().put("lista", listaTipoGradeHoraria);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#incluirGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO incluirGradeHoraria(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        RetornoDTO retornoDTO = new RetornoDTO();
        GradeHorariaDTO reqGradeHorariaDTO = (GradeHorariaDTO) dto.getDados().get("dto");
        GradeHorariaDto gradeHorariaDto = (GradeHorariaDto) ConversorVO.getInstance().converter(reqGradeHorariaDTO);
        getGradeHorariaDelegate().incluirGradeHoraria(gradeHorariaDto);

        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#alterarGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO alterarGradeHoraria(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        RetornoDTO retornoDTO = new RetornoDTO();
        GradeHorariaDTO reqGradeHorariaDTO = (GradeHorariaDTO) dto.getDados().get("dto");
        GradeHorariaDto gradeHorariaDto = (GradeHorariaDto) ConversorVO.getInstance().converter(reqGradeHorariaDTO);
        getGradeHorariaDelegate().incluirGradeHoraria(gradeHorariaDto);

        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#pesquisarGradeHorariaPaginada(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarGradeHorariaPaginada(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<GradeHorariaDTO> consulta = montarConsultaDto(dto, GradeHorariaDTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consulta.getFiltro();
        if (!ObjectUtil.isNull(requisicaoReqDTO)) {
            consulta.setFiltro(requisicaoReqDTO.getDados().get("dto".toString()));
        }

        ConsultaDto<GradeHorariaDTO> consultaVo = getGradeHorariaDelegate().pesquisar(GradeHorariaDTO.class, consulta, PesquisaEnum.PESQUISAR_GRADE_HORARIA);
        consultaVo.setResultado((List<GradeHorariaDTO>) ConversorVO.getInstance().converter(consulta.getResultado()));
        if (ObjectUtil.isNull(consulta.getResultado()) || ObjectUtil.isEmpty(consulta.getResultado())) {
            throw new ComumNegocioException("Nenhum item encontrado");
        }
        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#obterGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterGradeHoraria(RequisicaoReqDTO dto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        GradeHorariaDTO reqGradeHorariaDTO = (GradeHorariaDTO) dto.getDados().get("dto");
        GradeHorariaDto gradeHorariaDto = (GradeHorariaDto) ConversorVO.getInstance().converter(reqGradeHorariaDTO);

        GradeHorariaDTO gradeHorariaDTO = (GradeHorariaDTO) ConversorVO.getInstance().converter(getGradeHorariaDelegate().obterGradeHoraria(gradeHorariaDto));
        retornoDTO.getDados().put("lista", gradeHorariaDTO);

        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IGradeHoraria#removerGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO removerGradeHoraria(RequisicaoReqDTO dto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        GradeHoraria gradeHoraria = new GradeHoraria();
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        GradeHorariaDTO gradeHorariaDto = (GradeHorariaDTO) dto.getDados().get("dto");
        gradeHoraria.setId(gradeHorariaDto.getIdGradeHoraria());
        tipoGradeHoraria.setCodTipoGradeHoraria(gradeHorariaDto.getTipoGradeHorariaDto().getCodTipoGradeHoraria());
        gradeHoraria.setTipoGradeHoraria(tipoGradeHoraria);
        getGradeHorariaDelegate().removerGradeHoraria(gradeHoraria);

        return retornoDTO;
    }
}
