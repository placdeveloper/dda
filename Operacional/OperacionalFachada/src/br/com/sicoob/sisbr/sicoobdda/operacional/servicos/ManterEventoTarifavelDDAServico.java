package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoTarifavelDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDAVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterEventoTarifavelDDA;

/**
 * ManterEventoTarifavelDDAServico
 * 
 * @author samuell.ramos
 */
@RemoteService
public class ManterEventoTarifavelDDAServico extends OperacionalFachada implements IManterEventoTarifavelDDA {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.listaEventoTarifavelDDA#listaEventoTarifavelDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listaEventoTarifavelDDA() throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        List<EventoTarifavelDDAVO> listaEventoTarifavel = (List<EventoTarifavelDDAVO>) ConversorVO.getInstance().converter(
                getEventoTarifavelDDADelegate().listarEventoTarifavelDDA());
        retorno.getDados().put("lista", listaEventoTarifavel);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterEventoTarifavelDDA#pesquisaEventoTarifavelDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO pesquisaEventoTarifavelDDA(RequisicaoReqDTO reqDto) throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        Integer codEventoTarifavel = getCodEventoTarifavel(reqDto);
        Integer codStatus = getCodStatus(reqDto);
        List<EventoTarifavelDTO> listaEventoTarifavel = (List<EventoTarifavelDTO>) ConversorVO.getInstance().converter(
                getEventoTarifavelDDADelegate().pesquisaEventoTarifavelDDA(codEventoTarifavel, codStatus));
        retorno.getDados().put("lista", listaEventoTarifavel);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterEventoTarifavelDDA#obterEventoTarifavelDDATarifa(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterEventoTarifavelDDATarifa(RequisicaoReqDTO reqDto) throws ComumException {
        RetornoDTO retorno = new RetornoDTO();
        Long idEventoTarifavelDDATarifa = getIdEventoTarifavelDDATarifa(reqDto);
        EventoTarifavelDTO eventoTarifavel = (EventoTarifavelDTO) ConversorVO.getInstance().converter(
                getEventoTarifavelDDADelegate().obterEventoTarifavelDDATarifa(idEventoTarifavelDDATarifa));
        retorno.getDados().put("dto", eventoTarifavel);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterEventoTarifavelDDA#obterEventoTarifavelDDATarifa(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO manterEventoTarifavel(RequisicaoReqDTO reqDto) throws BancoobException {
        RetornoDTO retorno = new RetornoDTO();
        EventoTarifavelDto eventoTarDto = getEventoTarifavelDDA(reqDto);
        retorno.getDados().put("tipoOperacao", getEventoTarifavelDDADelegate().manterEventoTarifavel(eventoTarDto));
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IManterEventoTarifavelDDA#excluirEventoTarifavelDDA(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void excluirEventoTarifavelDDA(RequisicaoReqDTO reqDto) throws BancoobException {
        EventoTarifavelDto eventoTarDto = getEventoTarifavelDDA(reqDto);
        getEventoTarifavelDDADelegate().excluirEventoTarifavelDDA(eventoTarDto);
    }

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return Integer
     * 
     */
    private Long getIdEventoTarifavelDDATarifa(RequisicaoReqDTO reqDto) {
        Integer idEvento = (Integer) reqDto.getDados().get("idEventoTarifavelDDATarifa");
        return idEvento.longValue();
    }

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    private EventoTarifavelDto getEventoTarifavelDDA(RequisicaoReqDTO reqDto) throws ComumException {
        EventoTarifavelDto eventoTarifavelDto = (EventoTarifavelDto) ConversorVO.getInstance().converter(reqDto.getDados().get("dto"));
        return eventoTarifavelDto;
    }

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return Integer
     * 
     */
    private Integer getCodEventoTarifavel(RequisicaoReqDTO reqDto) {
        return (Integer) (reqDto.getDados().get("codEventoTarifavel") == null ? null : reqDto.getDados().get("codEventoTarifavel"));
    }

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return Integer
     * 
     */
    private Integer getCodStatus(RequisicaoReqDTO reqDto) {
        return (Integer) (reqDto.getDados().get("codStatus") == null ? null : reqDto.getDados().get("codStatus"));
    }

}
