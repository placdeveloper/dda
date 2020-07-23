/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         MonitoracaoCacheServico.java
 * Data Criação:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.AtualizacaoCacheDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.RequisicaoCacheDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.FiltroRequisicaoCacheDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AtualizacaoCacheVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RequisicaoCacheVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoCacheServico;

/**
 * MonitoracaoCacheServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MonitoracaoCacheServico extends OperacionalFachada implements IMonitoracaoCacheServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoCacheServico#pesquisarRequisicaoCache(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO pesquisarRequisicaoCache(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        FiltroRequisicaoCacheDTO filtroDTO = (FiltroRequisicaoCacheDTO) dto.getDados().get("dto");
        FiltroRequisicaoCacheDto filtroDto = (FiltroRequisicaoCacheDto) ConversorVO.getInstance().converter(filtroDTO);

        List<RequisicaoCache> listaRequisicao = getRequisicaoCacheDelegate().pesquisarRequisicao(filtroDto);
        List<RequisicaoCacheVO> listaRequisicaoVO = (List<RequisicaoCacheVO>) ConversorVO.getInstance().converter(listaRequisicao);

        return montarListaRetorno(listaRequisicaoVO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoCacheServico#listarAtualizacaoCache(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO listarAtualizacaoCache(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException {
        RequisicaoCacheVO requisicao = (RequisicaoCacheVO) dto.getDados().get("vo");

        List<AtualizacaoCache> listaAtualizacao = getAtualizacaoCacheDelegate().listar(requisicao.getIdRequisicaoCache());
        List<AtualizacaoCacheVO> listaAtualizacaoVO = (List<AtualizacaoCacheVO>) ConversorVO.getInstance().converter(listaAtualizacao);

        return montarListaRetorno(listaAtualizacaoVO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IMonitoracaoCacheServico#cadastrarRequisicaoCache(br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO)
     */
    @Override
    public void cadastrarRequisicaoCache(RequisicaoReqDTO dto) throws BancoobException {
        RequisicaoCacheVO requisicaoVO = (RequisicaoCacheVO) dto.getDados().get("vo");

        RequisicaoCache requisicao = (RequisicaoCache) ConversorVO.getInstance().converter(requisicaoVO);
        getRequisicaoCacheDelegate().incluir(requisicao);
    }

    /**
     * Método responsável por
     * 
     * @return RequisicaoCacheDelegate
     * 
     */
    private RequisicaoCacheDelegate getRequisicaoCacheDelegate() {
        return ComumFabricaDelegates.getInstance().getRequisicaoCacheDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return AtualizacaoCacheDelegate
     * 
     */
    private AtualizacaoCacheDelegate getAtualizacaoCacheDelegate() {
        return ComumFabricaDelegates.getInstance().getAtualizacaoCacheDelegate();
    }
}
