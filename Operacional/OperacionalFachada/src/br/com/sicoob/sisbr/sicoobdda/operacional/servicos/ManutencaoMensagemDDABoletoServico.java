package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoFiltroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;

/**
 * ManutencaoMensagemDDABoletoServico é responsável por
 * 
 * @author tayrone.oliveira
 */
public class ManutencaoMensagemDDABoletoServico extends OperacionalFachada {

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException DadosSelGeralDTO
     * 
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarMensagemDDABoletoPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<MensagemDDABoletoDTO> consulta = montarConsultaDto(dto, MensagemDDABoletoDTO.class);
        consulta.setTamanhoPagina(dto.getTamanhoPagina());
        RequisicaoReqDTO req = (RequisicaoReqDTO) consulta.getFiltro();
        MensagemDDABoletoFiltroDto filtro = (MensagemDDABoletoFiltroDto) ConversorVO.getInstance().converter(req.getDados().get("dto"));
        filtro.setPaginaAtual(consulta.getPagina() * consulta.getTamanhoPagina());
        List<MensagemDDABoletoDto> listaMensagemDDABoletoDto = getManutencaoMensagemDDABoletoDelegate().listarMensagemDDABoletoDtoPaginado(filtro);
        if (!ObjectUtil.isEmpty(listaMensagemDDABoletoDto)) {
            List<MensagemDDABoletoDTO> listaConvertida = new ArrayList<MensagemDDABoletoDTO>();
            for (MensagemDDABoletoDto retorno : listaMensagemDDABoletoDto) {
                MensagemDDABoletoDTO convert = new MensagemDDABoletoDTO(retorno.getIdMensagem(), retorno.getTipoMensagem(), retorno.getDataMovimento(),
                        retorno.getNumCodigoDeBarras());
                listaConvertida.add(convert);
            }
            consulta.setResultado(listaConvertida);
            consulta.setTotalRegistros(!ObjectUtil.isEmpty(listaMensagemDDABoletoDto) ? listaMensagemDDABoletoDto.get(Constantes.INTEGER_ZERO).getQtdPesquisa()
                    : Constantes.INTEGER_ZERO);
        } else {
            throw new ComumNegocioException("Nenhum item encontrado");
        }
        DadosSelGeralDTO retorno = montarResultado(consulta);
        retorno.getDados().put("listaMensagemDDABoletoDto", consulta.getResultado());
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return
     * @throws ComumException RetornoDTO
     * @throws ComumNegocioException
     * 
     */
    public RetornoDTO obterMensagemDDABoletoPorId(Long idMensagem) throws ComumException, ComumNegocioException {
        RetornoDTO retornoDTO = new RetornoDTO();
        MensagemDDABoletoDto msgDto = getManutencaoMensagemDDABoletoDelegate().obterMensagemDtoPorId(idMensagem);
        MensagemDDABoletoDTO msgDTO = (MensagemDDABoletoDTO) ConversorVO.getInstance().converter(msgDto);
        retornoDTO.getDados().put("dto", msgDTO);
        return retornoDTO;
    }

    /**
     * Método responsável por
     * 
     * @param req
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    public void alterarMensagemDDABoleto(RequisicaoReqDTO req) throws ComumException, ComumNegocioException {
        MensagemDDABoletoDTO dto = (MensagemDDABoletoDTO) req.getDados().get("dto");
        getManutencaoMensagemDDABoletoDelegate().alterarMensagemDDABoleto((MensagemDDABoletoDto) ConversorVO.getInstance().converter(dto));
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    public RetornoDTO listarCombos() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put("filtros", (MensagemDDABoletoFiltroDTO) ConversorVO.getInstance().converter(getManutencaoMensagemDDABoletoDelegate().listarCombos()));
        return retornoDTO;
    }
}
