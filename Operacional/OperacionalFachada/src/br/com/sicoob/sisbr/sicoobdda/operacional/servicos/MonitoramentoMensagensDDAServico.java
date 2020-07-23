package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LogErroCargaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDADTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoramentoMensagensDDA;

/**
 * MonitoramentoMensagensDDAServico
 * 
 * @author Felipe.Rosa
 */
@SuppressWarnings("unchecked")
@RemoteService
public class MonitoramentoMensagensDDAServico extends OperacionalFachada implements IMonitoramentoMensagensDDA {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#listarTipoMensagens()
     */
    public RetornoDTO listarTipoMensagens(RequisicaoReqDTO dto) throws ComumException {
        String origem = dto.getDados().get("origem").toString();
        List<TipoMensagemDDAVO> listaTipoMensagemDDAVO = (List<TipoMensagemDDAVO>) ConversorVO.getInstance().converter(
                getMonitoramentoMensagensDDADelegate().listarTipoMensagens(origem));

        RetornoDTO retorno = new RetornoDTO();
        retorno.getDados().put("lista", listaTipoMensagemDDAVO);
        return retorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#pesquisaPainelMensagensPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    public DadosSelGeralDTO pesquisaPainelMensagensPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<MensagemDDADTO> filtro = montarConsultaDto(dto, MensagemDDADTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) filtro.getFiltro();
        filtro.setFiltro(requisicaoReqDTO.getDados().get("dto".toString()));
        ConsultaDto<MensagemDDADTO> consulta = getMonitoramentoMensagensDDADelegate().pesquisar(MensagemDDADTO.class, filtro, PesquisaEnum.PESQUISAR_PAINEL_MENSAGENSDDA);
        consulta.setResultado((List<MensagemDDADTO>) ConversorVO.getInstance().converter(consulta.getResultado()));

        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#recuperaMensagemDDA(br.com.sicoob.sisbr.cobrancabancaria.comum.fachada.vo.MensagemDDAVO)
     */
    public RetornoDTO recuperaMensagemDDA(MensagemDDADTO dto) throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        MensagemDDA mensagemDDA = (MensagemDDA) getMonitoramentoMensagensDDADelegate().recuperaMensagemDDA(dto.getIdMensagem());
        mensagemDDA.setMensagemOrigem(getMonitoramentoMensagensDDADelegate().recuperaMensagemOrigemDDA(dto.getIdMensagem()));
        MensagemDDAVO mensagemDDAVO = (MensagemDDAVO) ConversorVO.getInstance().converter(mensagemDDA);
        retornoDTO.getDados().put("mensagemDDA", mensagemDDAVO);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#reenviarListaMensagemCip(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO reenviarListaMensagemCip(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        List<Integer> listaIdMensagem = (List<Integer>) dto.getDados().get("listaIdMensagem");
        getMonitoramentoMensagensDDADelegate().reenviarMensagemCip(listaIdMensagem, CanalEnum.RETAGUARDA.getId());
        return new RetornoDTO();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#reenviarMensagemCip(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO reenviarMensagemCip(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        Integer idMensagem = (Integer) dto.getDados().get("idMensagem");
        getMonitoramentoMensagensDDADelegate().reenviarMensagemCip(idMensagem.longValue(), CanalEnum.RETAGUARDA.getId());
        return new RetornoDTO();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#pesquisarErroCargaBeneficiarioPaginado(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    public DadosSelGeralDTO pesquisarErroCargaBeneficiarioPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException {
        ConsultaDto<LogErroCargaDTO> consulta = montarConsultaDto(dto, LogErroCargaDTO.class);
        RequisicaoReqDTO requisicaoReqDTO = (RequisicaoReqDTO) consulta.getFiltro();
        consulta.setFiltro(requisicaoReqDTO.getDados().get("dto".toString()));
        consulta = getMonitoramentoMensagensDDADelegate().pesquisar(LogErroCargaDTO.class, consulta, PesquisaEnum.PESQUISAR_PAINEL_LOGERROCARGA);
        consulta.setResultado((List<LogErroCargaDTO>) ConversorVO.getInstance().converter(consulta.getResultado()));

        return montarResultado(consulta);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.operacional.iface.IMonitoramentoMensagensDDA#recuperaRegistroErroCarga(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    public RetornoDTO recuperaRegistroErroCarga(RequisicaoReqDTO dto) throws ComumException {
        Integer idLogErroCarga = (Integer) dto.getDados().get("idLogErro");
        LogErroCargaDTO logErroDTO = (LogErroCargaDTO) ConversorVO.getInstance().converter(
                getMonitoramentoMensagensDDADelegate().recuperaRegistroErroCarga(idLogErroCarga.longValue()));

        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put("logErroDTO", logErroDTO);
        return retornoDTO;
    }
    

}
