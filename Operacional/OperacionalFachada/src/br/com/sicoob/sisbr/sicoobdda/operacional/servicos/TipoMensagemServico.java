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
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoMensagemDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CategoriaMensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoGradeHorariaVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * GradeHorariaServico
 * 
 * @author samuell.ramos
 */
/**
 * TipoMensagemServico é responsável por
 * 
 * @author Samuell.Ramos
 */
@RemoteService
public class TipoMensagemServico extends OperacionalFachada implements ITipoMensagem {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem#carregarFiltrosTipoMensagem()
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO carregarFiltrosTipoMensagem() throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        // TODO - Samuell.Ramos - REFATORAR SERVIÇO PARA UMA CHAMADA À CAMADA DE EJB
        List<TipoGradeHorariaVO> listaTipoGradeHoraria = (List<TipoGradeHorariaVO>) ConversorVO.getInstance().converter(getTipoMensagemDelegate().listarTipoGradeHoraria());
        List<CategoriaMensagemDDAVO> listaCategoriaMensagem = (List<CategoriaMensagemDDAVO>) ConversorVO.getInstance().converter(
                getTipoMensagemDelegate().listarCategoriaMensagemDDA());
        List<TipoMensagemDDAVO> listaTipoMensagem = (List<TipoMensagemDDAVO>) ConversorVO.getInstance().converter(getTipoMensagemDelegate().listarTipoMensagemDDA());

        TipoMensagemDTO tipoMensagemDTO = new TipoMensagemDTO();
        tipoMensagemDTO.setListaCategoriaMensagem(listaCategoriaMensagem);
        tipoMensagemDTO.setListaTipoGradeHoraria(listaTipoGradeHoraria);
        tipoMensagemDTO.setListaArquivoCorrespondente(listaTipoMensagem);
        retornoDTO.getDados().put("lista", tipoMensagemDTO);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem#incluirTipoMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO incluirTipoMensagem(RequisicaoReqDTO dto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDDAVO reqTipoMensagemDTO = (TipoMensagemDDAVO) dto.getDados().get("dto");
        TipoMensagemDDA tipoMensagemDDA = (TipoMensagemDDA) ConversorVO.getInstance().converter(reqTipoMensagemDTO);
        getTipoMensagemDelegate().incluirTipoMensagemDDA(tipoMensagemDDA);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem#alterarTipoMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO alterarTipoMensagem(RequisicaoReqDTO dto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDDAVO reqTipoMensagemDTO = (TipoMensagemDDAVO) dto.getDados().get("dto");
        TipoMensagemDDA tipoMensagemDDA = (TipoMensagemDDA) ConversorVO.getInstance().converter(reqTipoMensagemDTO);
        getTipoMensagemDelegate().alterarTipoMensagemDDA(tipoMensagemDDA);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem#pesquisarPaginadaTipoMensagem(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarPaginadaTipoMensagem(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<TipoMensagemDTO> consulta = montarConsultaDto(dto, TipoMensagemDTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consulta.getFiltro();
        if (!ObjectUtil.isNull(requisicaoReqDTO)) {
            consulta.setFiltro(requisicaoReqDTO.getDados().get("dto".toString()));
        }
        ConsultaDto<TipoMensagemDDAVO> consultaVo = getTipoMensagemDelegate().pesquisar(TipoMensagemDto.class, consulta, PesquisaEnum.PESQUISAR_TIPO_MENSAGEM);
        consultaVo.setResultado((List<TipoMensagemDDAVO>) ConversorVO.getInstance().converter(consulta.getResultado()));
        if (ObjectUtil.isNull(consulta.getResultado()) || ObjectUtil.isEmpty(consulta.getResultado())) {
            throw new ComumNegocioException("Nenhum item encontrado");
        }
        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoMensagem#obterTipoMensagem(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterTipoMensagem(RequisicaoReqDTO dto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDDAVO reqTipoMensagemVO = (TipoMensagemDDAVO) dto.getDados().get("dto");

        TipoMensagemDDA tipoGradeHoraria = (TipoMensagemDDA) ConversorVO.getInstance().converter(reqTipoMensagemVO);

        TipoMensagemDto tipoMensagem = getTipoMensagemDelegate().obterTipoMensagem(tipoGradeHoraria);
        TipoMensagemDTO tipoMensagemDTO = (TipoMensagemDTO) ConversorVO.getInstance().converter(tipoMensagem);
        retornoDTO.getDados().put("lista", tipoMensagemDTO);

        return retornoDTO;
    }

    /**
     * @return
     * @throws BancoobException
     */
    public RetornoDTO carregarListasTipoMensagem() throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDto tipoMensagem = getTipoMensagemDelegate().carregarListasTipoMensagem();
        TipoMensagemDTO tipoMensagemDTO = (TipoMensagemDTO) ConversorVO.getInstance().converter(tipoMensagem);
        retornoDTO.getDados().put("lista", tipoMensagemDTO);

        return retornoDTO;
    }

    /**
     * @param dto
     * @return RetornoDTO
     * @throws BancoobException
     */
    public RetornoDTO carregarCamposEdicao(RequisicaoReqDTO dto) throws BancoobException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDDAVO reqTipoMensagemVO = (TipoMensagemDDAVO) dto.getDados().get("dto");

        TipoMensagemDDA tipoGradeHoraria = (TipoMensagemDDA) ConversorVO.getInstance().converter(reqTipoMensagemVO);

        TipoMensagemDto tipoMensagem = getTipoMensagemDelegate().obterTipoMensagem(tipoGradeHoraria);
        TipoMensagemDTO tipoMensagemDTO = (TipoMensagemDTO) ConversorVO.getInstance().converter(tipoMensagem);
        retornoDTO.getDados().put("lista", tipoMensagemDTO);

        return retornoDTO;
    }

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     * @throws OperacionalNegocionException
     */
    public RetornoDTO removerGradeHoraria(RequisicaoReqDTO dto) throws ComumException, OperacionalNegocionException {
        RetornoDTO retornoDTO = new RetornoDTO();
        TipoMensagemDDA tipoMensagemDDA = new TipoMensagemDDA();
        String codTipoMensagem = (String) dto.getDados().get("codTipoMensagem");
        tipoMensagemDDA.setCodTipoMensagem(codTipoMensagem);
        getTipoMensagemDelegate().removerTipoMensagem(tipoMensagemDDA.getCodTipoMensagem());

        return retornoDTO;
    }
}
