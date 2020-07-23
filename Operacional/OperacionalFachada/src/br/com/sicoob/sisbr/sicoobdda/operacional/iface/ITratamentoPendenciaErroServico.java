/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         ITratamenoPendenciaErroServico.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ITratamenoPendenciaErroServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ITratamentoPendenciaErroServico {

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarTratamentoPendenciaErro() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemPendencia(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemErroAgrupado(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoArquivoErroRetornoCIP(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemErroRetornoCIP() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    DadosSelGeralDTO pesquisarMensagemContingenciaPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void executarTratamentoMensagem(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    void executarTratamentoAutomatizadoMensagem() throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMensagemErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO listarTipoTratamentoSitMensagemRetornoComErro() throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void exluirMensagemErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void excluirListaMensagemErro(RequisicaoReqDTO dto) throws BancoobException;
}
