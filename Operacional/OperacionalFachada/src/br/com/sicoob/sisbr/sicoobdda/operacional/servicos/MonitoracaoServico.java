/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         MonitoracaoServico.java
 * Data Criação:    Nov 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqRemessaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqVarreduraDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDDA0110DTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDemaisMensagensDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.MonitoracaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;

/**
 * MonitoracaoServico é responsável por
 * 
 * @author Felipe.Rosa
 */
@RemoteService
public class MonitoracaoServico extends OperacionalFachada implements IMonitoracaoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDadosMonitoracao()
     */
    public RetornoDTO obterDadosMonitoracao() throws ComumException {
        MonitoracaoDto monitoracaoDto = getMonitoracaoDelegate().obterDadosMonitoracao();
        MonitoracaoDTO monitoracao = (MonitoracaoDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDadosMonitoracaoMQ()
     */
    public RetornoDTO obterDadosMonitoracaoDDA0110(RequisicaoReqDTO dto) throws ComumException {
        String dataHoraUltimaAfericao = dto.getDados().get("dataHoraUltimaAfericao").toString();
        MonitoracaoDDA0110Dto monitoracaoDto = getMonitoracaoDelegate().obterDadosMonitoracaoDDA0110(dataHoraUltimaAfericao);
        MonitoracaoDDA0110DTO monitoracao = (MonitoracaoDDA0110DTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDadosMonitoracaoDemaisMensagens()
     */
    public RetornoDTO obterDadosMonitoracaoDemaisMensagens() throws ComumException {
        MonitoracaoDemaisMensagensDto monitoracaoDto = getMonitoracaoDelegate().obterDadosMonitoracaoDemaisMensagens();
        MonitoracaoDemaisMensagensDTO monitoracao = (MonitoracaoDemaisMensagensDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDadosMonitoracaoArquivoRemessa()
     */
    public RetornoDTO obterDadosMonitoracaoArquivoRemessa() throws ComumException {
        MonitoracaoArqRemessaDto monitoracaoDto = getMonitoracaoDelegate().obterDadosMonitoracaoArquivoRemessa();
        MonitoracaoArqRemessaDTO monitoracao = (MonitoracaoArqRemessaDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDadosMonitoracaoArquivoVarredura()
     */
    public RetornoDTO obterDadosMonitoracaoArquivoVarredura() throws ComumException {
        MonitoracaoArqVarreduraDto monitoracaoDto = getMonitoracaoDelegate().obterDadosMonitoracaoArquivoVarredura();
        MonitoracaoArqVarreduraDTO monitoracao = (MonitoracaoArqVarreduraDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDetalheMonitoracaoDemaisMensagens()
     */
    public RetornoDTO obterDetalheMonitoracaoDemaisMensagens() throws ComumException {
        MonitoracaoDemaisMensagensDto monitoracaoDto = getMonitoracaoDelegate().obterDetalheMonitoracaoDemaisMensagens();
        MonitoracaoDemaisMensagensDTO monitoracao = (MonitoracaoDemaisMensagensDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoServico#obterDetalheMonitoracaoArquivoRemessa()
     */
    public RetornoDTO obterDetalheMonitoracaoArquivoRemessa() throws ComumException {
        MonitoracaoArqRemessaDto monitoracaoDto = getMonitoracaoDelegate().obterDetalheMonitoracaoArquivoRemessa();
        MonitoracaoArqRemessaDTO monitoracao = (MonitoracaoArqRemessaDTO) ConversorVO.getInstance().converter(monitoracaoDto);
        return preparaRetornoMonitoracaoDTO(monitoracao);
    }

    /**
     * Método responsável por
     * 
     * @param monitoracao
     * @return RetornoDTO
     * 
     */
    private RetornoDTO preparaRetornoMonitoracaoDTO(BancoobDTO monitoracao) {
        RetornoDTO retornoDTO = new RetornoDTO();
        retornoDTO.getDados().put("monitoracao", monitoracao);
        return retornoDTO;
    }

    /**
     * Método responsável por
     * 
     * @return MonitoracaoDelegate
     * 
     */
    private MonitoracaoDelegate getMonitoracaoDelegate() {
        return OperacionalFabricaDelegates.getInstance().getMonitoracaoDelegate();
    }

}
