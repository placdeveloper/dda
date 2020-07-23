/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IMensagemDDAServico.java
 * Data Criação:    Jan 3, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IConsultaBoletoDDAServico é responsável por
 * 
 * @author George.santos
 */
public interface IConsultaBoletoDDAServico {

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws BancoobException void
     * 
     */
    DadosSelGeralDTO pesquisarConsultaBoletoDDAPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    DadosSelGeralDTO pesquisarHistoricoMensagem106Paginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por listar todas as Situações do Boleto
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO listarSituacoesBoleto(RequisicaoReqDTO dto) throws ComumException;

    /**
     * Método responsável por consultar os Boleto na CIP
     * 
     * @param dto
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO consultarBoletoCIP(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter o nome do Banco
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO obterNomeBanco(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por obter o BoletoDDA
     * 
     * @param dto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException RetornoDTO
     * 
     */
    RetornoDTO obterBoletoDDA(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;
}
