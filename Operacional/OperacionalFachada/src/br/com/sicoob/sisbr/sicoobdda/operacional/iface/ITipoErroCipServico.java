/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         ITipoErroCipServico.java
 * Data Cria��o:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;

/**
 * ITipoErroCipServico � respons�vel por 
 * 
 * @author Felipe.Rosa
 */
public interface ITipoErroCipServico {

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    DadosSelGeralDTO pesquisarTipoErroCipPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO incluirTipoErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO alterarTipoErro(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    void excluirTipoErro(RequisicaoReqDTO dto) throws BancoobException;

}
