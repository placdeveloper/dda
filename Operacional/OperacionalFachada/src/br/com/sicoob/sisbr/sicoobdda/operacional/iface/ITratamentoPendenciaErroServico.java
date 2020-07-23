/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         ITratamenoPendenciaErroServico.java
 * Data Cria��o:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ITratamenoPendenciaErroServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ITratamentoPendenciaErroServico {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarTratamentoPendenciaErro() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemPendencia(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemErroAgrupado(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoArquivoErroRetornoCIP(RequisicaoReqDTO dto) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosTratamentoMensagemErroRetornoCIP() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    DadosSelGeralDTO pesquisarMensagemContingenciaPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void executarTratamentoMensagem(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @throws BancoobException void
     * 
     */
    void executarTratamentoAutomatizadoMensagem() throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO obterDetalheMensagemErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO listarTipoTratamentoSitMensagemRetornoComErro() throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void exluirMensagemErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void excluirListaMensagemErro(RequisicaoReqDTO dto) throws BancoobException;
}
