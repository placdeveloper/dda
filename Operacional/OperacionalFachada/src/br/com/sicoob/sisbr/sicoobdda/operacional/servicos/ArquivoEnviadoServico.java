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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoEnviadoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogEnvioArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoEnviado;

/**
 * ArquivoEnviadoServico
 * 
 * @author samuell.ramos
 */
@RemoteService
public class ArquivoEnviadoServico extends OperacionalFachada implements IArquivoEnviado {

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoEnviado#alterarArquivo(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public void alterarArquivo(RequisicaoReqDTO dto) throws BancoobException {
    	LogEnvioArquivoDDAVO logEnvioArquivoDDAVO = (LogEnvioArquivoDDAVO) dto.getDados().get("vo");
    	getArquivoEnviadoDelegate().alterarArquivo((LogEnvioArquivoDDA) ConversorVO.getInstance().converter(logEnvioArquivoDDAVO));
    }

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoEnviado#pesquisarArquivoEnviadoPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @SuppressWarnings("unchecked")
	public DadosSelGeralDTO pesquisarArquivoEnviadoPaginado(SelGeralReqDTO dto) throws BancoobException {
        ConsultaDto<ArquivoEnviadoDTO> consulta = montarConsultaDto(dto, ArquivoEnviadoDTO.class);
        RequisicaoReqDTO reqDto = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(ObjectUtil.isNull(reqDto) ? null : reqDto.getDados().get("dto".toString()));
        consulta.setTamanhoPagina(11);
        ConsultaDto<ArquivoEnviadoDTO> consultaDto = getArquivoEnviadoDelegate().pesquisar(consulta);
        consultaDto.setResultado((List<ArquivoEnviadoDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado()));
        
        return montarResultado(consultaDto);
    }

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoEnviado#carregarFiltrosArquivoEnviado()
     */
    @SuppressWarnings("unchecked")
	public RetornoDTO carregarFiltrosArquivoEnviado() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        List<TipoMensagemDDAVO> listaTipoMensagem = (List<TipoMensagemDDAVO>) ConversorVO.getInstance().converter(getArquivoEnviadoDelegate().carregarListaTipoMensagem());
        retornoDTO.getDados().put("lista", listaTipoMensagem);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IArquivoEnviado#obterArquivoEnviado(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO obterArquivoEnviado(RequisicaoReqDTO dto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        Long idLogEnvioArquivodda = convertToLong((Object)dto.getDados().get("idLogEnvioArquivodda"));
        ArquivoEnviadoDTO retornoArquivoEnviadoVO= (ArquivoEnviadoDTO) ConversorVO.getInstance().converter(
                getArquivoEnviadoDelegate().obterArquivoEnviado(idLogEnvioArquivodda));
        retornoDTO.getDados().put("dto", retornoArquivoEnviadoVO);
        return retornoDTO;
    }

    private static Long convertToLong(Object o) throws ComumException{
    	if(ObjectUtil.isNull(o)){
    		return null;
    	}else{
    		return Long.parseLong(o.toString());
    	}
    }

}
