package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaMovimentoSicoobDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTarifasDDAPagasDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LancamentosTarifasDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaTarifasDDAServico;

/**
 * ConsultaTarifasDDAServico é responsável por
 * 
 * @author rodrigo.neri
 */
@RemoteService
public class ConsultaTarifasDDAServico extends OperacionalFachada implements IConsultaTarifasDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaTarifasDDAServico#pesquisarTarifasDDAPagasPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarTarifasDDAPagasPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<ConsultaTarifasDDAPagasDTO> consultaDto = montarConsultaDto(dto, ConsultaTarifasDDAPagasDTO.class);

        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDto.getFiltro();
        ConsultaTarifasDDAPagasDTO consultaTarifasDDAPagasDTO = (ConsultaTarifasDDAPagasDTO) requisicaoReqDTO.getDados().get("dto");
        consultaDto.setFiltro(consultaTarifasDDAPagasDTO);

        consultaDto = getRateioDDALancamentoDelegate().pesquisar(consultaDto);

        consultaDto.setResultado((List<ConsultaTarifasDDAPagasDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));

        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaTarifasDDAServico#listarLancamentosTarifas(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @SuppressWarnings("unchecked")
    public RetornoDTO listarLancamentosTarifas(RequisicaoReqDTO dto) throws BancoobException {
        Long idRateioDDALancamento = Long.parseLong(dto.getDados().get("idRateioDDALancamento").toString());

        List<LancamentosTarifasDDADto> lista = getRateioDDALancamentoDelegate().listarLancamentosTarifas(idRateioDDALancamento);

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", (List<LancamentosTarifasDDADTO>) ConversorVO.getInstance().converter(lista));

        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IConsultaTarifasDDAServico#pesquisarMovimentacaoSicoobDDAPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarMovimentacaoSicoobDDAPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<ConsultaMovimentoSicoobDDADTO> consultaDTO = montarConsultaDto(dto, ConsultaMovimentoSicoobDDADTO.class);

        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consultaDTO.getFiltro();
        ConsultaMovimentoSicoobDDADTO filtro = (ConsultaMovimentoSicoobDDADTO) requisicaoReqDTO.getDados().get("dto");
        consultaDTO.setFiltro(filtro);

        ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto = new ConsultaDto<>();
        popularConsultaDto(consultaDto, dto);

        ConsultaMovimentoSicoobDDADto filtroDto = (ConsultaMovimentoSicoobDDADto) ConversorVO.getInstance().converter(filtro);
        consultaDto.setFiltro(filtroDto);

        consultaDto = getRateioDDALancamentoDelegate().pesquisarMovimentoPaginado(consultaDto);

        consultaDTO.setResultado((List<ConsultaMovimentoSicoobDDADTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        consultaDTO.setTotalRegistros(consultaDto.getTotalRegistros());

        return montarResultado(consultaDTO);
    }

}