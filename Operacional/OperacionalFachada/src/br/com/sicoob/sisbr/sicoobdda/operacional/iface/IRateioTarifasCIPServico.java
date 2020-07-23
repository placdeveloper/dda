package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IRateioTarifasCIPServico � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface IRateioTarifasCIPServico {

    /**
     * M�todo respons�vel por
     * 
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO pesquisarLancamentos(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO pesquisarDadosRateio(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * 
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO obterDadosLancamentoRateioDDA(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    public DadosSelGeralDTO pesquisaRateioTarifaDDAPaginada(SelGeralReqDTO dto) throws BancoobException;

    /**
     * @return
     * @throws ComumException RetornoDTO
     * @throws BancoobException
     * 
     */
    RetornoDTO listaSituacaoRateio() throws BancoobException;

    /**
     * @param reqDto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    DadosSelGeralDTO pesquisaRateioTarifaPaginada(SelGeralReqDTO reqDto) throws ComumException;

    /**
     * @param reqDto
     * @return
     * @throws ComumException RetornoDTO
     * @throws BancoobException
     * 
     */
    RetornoDTO detalharRateio(RequisicaoReqDTO reqDto) throws ComumException, BancoobException;

    /**
     * @param reqDto
     * @throws ComumException void
     * @throws ComumNegocioException
     * @throws BancoobException
     * 
     */
    RetornoDTO efetivacaoManual(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param reqDto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO transferenciaDebito(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * M�todo respons�vel por pesquisar os eventos dispon�veis para rateio
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO pesquisarEventosDisponiveis(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por obter os dados da tela de rateio
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO obterDadosControleRateio(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por incluir os eventos consolidados no rateio
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO incluirEventoConsolidadoRateio(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por remover os eventos consolidados do rateio
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO removerEventoConsolidadoRateio(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por alterar os quantitativos dos eventos consolidados e o par�metro do desvio padr�o
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO atualizarEventoConsolidado(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por aprovar o rateio atual
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO aprovarRateio(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por alterar a situa��o do rateio para "aguardando aprova��o"
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO cancelarAprovacaoRateio(RequisicaoReqDTO dto) throws BancoobException;

}
