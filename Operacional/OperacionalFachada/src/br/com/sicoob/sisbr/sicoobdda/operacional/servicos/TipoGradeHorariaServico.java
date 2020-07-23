package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoGradeHorariaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * TipoGradeHorariaServico
 * 
 * @author samuell.ramos
 */
@RemoteService
public class TipoGradeHorariaServico extends OperacionalFachada implements ITipoGradeHoraria {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#listarTipoGradeHorariaPorCodigoSubtipo(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarTipoGradeHorariaPorCodigoSubtipo(RequisicaoReqDTO dto) throws ComumException {
        Integer codSubTipoGrade = (Integer) dto.getDados().get("idSubTipo");
        List<TipoGradeHorariaDTO> listaTipoGradeHoraria = (List<TipoGradeHorariaDTO>) ConversorVO.getInstance().converter(
                getTipoGradeHorariaDelegate().listarTipoGradeHorariaPorCodigoSubtipo(codSubTipoGrade.shortValue()));
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", listaTipoGradeHoraria);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#obterTipoGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterTipoGradeHoraria(RequisicaoReqDTO dto) throws ComumException {
        String codTipoGradeHoraria = dto.getDados().get("codTipoGradeHoraria").toString();
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("retornoObterTipoGradeHoraria",
                (TipoGradeHorariaDTO) ConversorVO.getInstance().converter(getTipoGradeHorariaDelegate().obterTipoGradeHoraria(codTipoGradeHoraria)));
        return retorno;
    }

    public RetornoDTO listarCombos() throws ComumException {
        RetornoDTO retorno = new RetornoDTO();

        retorno.getDados()
                .put("retornoListarCombos", (TipoGradeHorariaDTO) ConversorVO.getInstance().converter(getTipoGradeHorariaDelegate().listarTipoGradeHorariaSubtipoGrade()));
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#incluirTipoGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO incluirTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalException, OperacionalNegocionException {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        ObjectUtil.copy(dto.getDados().get("dto"), tipoGradeHorariaDto);

        Boolean resultadoInclusao = getTipoGradeHorariaDelegate().incluirTipoGradeHoraria(tipoGradeHorariaDto);
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("resultadoInclusao", resultadoInclusao);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#apagarTipoGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void apagarTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalNegocionException, ComumException {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria((String) dto.getDados().get("codTipoGradeHoraria"));

        getTipoGradeHorariaDelegate().apagarTipoGradeHoraria(tipoGradeHoraria);

    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalNegocionException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#alterarTipoGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void alterarTipoGradeHoraria(RequisicaoReqDTO dto) throws OperacionalException, OperacionalNegocionException {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        ObjectUtil.copy(dto.getDados().get("dto"), tipoGradeHorariaDto);

        getTipoGradeHorariaDelegate().alterarTipoGradeHoraria(tipoGradeHorariaDto);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.ITipoGradeHoraria#isExisteTipoGradeHoraria(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO isExisteTipoGradeHoraria(RequisicaoReqDTO dto) throws ComumException {
        String codTipoGradeHoraria = dto.getDados().get("codTipoGradeHoraria").toString();
        boolean isExisteTipoGradeHoraria = getTipoGradeHorariaDelegate().isExisteTipoGradeHoraria(codTipoGradeHoraria);
        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("isExisteTipoGradeHoraria", isExisteTipoGradeHoraria);
        return retorno;
    }

}
