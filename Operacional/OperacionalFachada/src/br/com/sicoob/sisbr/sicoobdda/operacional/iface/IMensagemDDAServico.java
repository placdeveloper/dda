/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IMensagemDDAServico.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;

/**
 * IMensagemDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface IMensagemDDAServico {

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDAPagador(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDABeneficiario(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDAConsultaBoleto(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemGEN0014(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDASerie0200(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDA0401(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemDDA0110(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void cadastrarMensagemAGEN001(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO obterDominioCadastroMensagem() throws BancoobException;
}
