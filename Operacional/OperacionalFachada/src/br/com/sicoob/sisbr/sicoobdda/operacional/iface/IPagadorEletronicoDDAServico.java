/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IPagadorEletronicoDDAServico.java
 * Data Criação:    Dec 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;

/**
 * IPagadorEletronicoDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface IPagadorEletronicoDDAServico {

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    DadosSelGeralDTO pesquisarPagadorEletronicoPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException DadosSelGeralDTO
     * 
     */
    DadosSelGeralDTO pesquisarPagadorAgregadoPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO obterDetalharPagador(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO obterDadosPagador(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO solicitarAdesao(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO solicitarCancelamentoAdesao(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO solicitarInclusaoPagadorAgregado(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO solicitarExclusaoPagadorAgregado(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * @param dto
     * @return RetornoDTO
     * @throws BancoobException
     */
    RetornoDTO obterTermoPagadorEletronico(RequisicaoReqDTO dto) throws BancoobException;
}
