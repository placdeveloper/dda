package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;


/**
 * @author Samuell.Ramos
 */
public interface IVisualizarRateioTarifaServico {

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO pesquisarLancamentos(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO pesquisarDadosRateio(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO transferirDebitoCC(RequisicaoReqDTO reqDto) throws BancoobException;

    /**
     * @param reqDto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    public RetornoDTO efetivarRateioManualmente(RequisicaoReqDTO reqDto) throws BancoobException;

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
     * Método responsável por
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
     * Método responsável por
     * 
     * @param reqDto
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException void
     * 
     */
    void imprimirRateioTarifas(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException void
     * 
     */
    void imprimirDadosRateio(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * Método responsável por
     * 
     * @param reqDto
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException void
     * 
     */
    void imprimirLancamentos(RequisicaoReqDTO reqDto) throws ComumException, ComumNegocioException, BancoobException;

}
