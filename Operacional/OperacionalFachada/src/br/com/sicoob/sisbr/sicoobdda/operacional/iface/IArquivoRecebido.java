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
public interface IArquivoRecebido {

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     * @throws ComumNegocioException
     */
    void alterarSituacaoArquivoRecebido(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @return DadosSelGeralDTO
     * @throws ComumException
     * @throws ComumNegocioException
     */
    DadosSelGeralDTO pesquisarArquivoRecebidoPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @return RetornoDTO
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO carregarFiltrosArquivoRecebido() throws ComumException;

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterLogDetRecebimentoArquivoDDA(RequisicaoReqDTO dto) throws ComumException;

    /**
     * @param dto
     * @return DadosSelGeralDTO
     * @throws ComumNegocioException
     * @throws ComumException
     */
    DadosSelGeralDTO pesquisarLogDetRecArquivoDDAPaginado(SelGeralReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO obterArquivoRecebido(RequisicaoReqDTO dto) throws ComumException;

    /**
     * @param dto
     * @throws BancoobException void
     * 
     */
    RetornoDTO descriptografarArquivo(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO gravarDetalheArquivo(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param dto
     * @return
     * @throws BancoobException RetornoDTO
     * 
     */
    RetornoDTO processarArquivo(RequisicaoReqDTO dto) throws BancoobException;
    
    /**
     * Método responsável por 
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void alterarSituacaoRegistro(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;
}
