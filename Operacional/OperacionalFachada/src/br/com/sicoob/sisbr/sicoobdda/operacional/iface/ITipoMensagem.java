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
public interface ITipoMensagem {

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException
     */
    RetornoDTO incluirTipoMensagem(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * @param dto
     * @return RetornoDTO
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException
     */
    RetornoDTO alterarTipoMensagem(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * @param dto
     * @return DadosSelGeralDTO
     * @throws ComumException
     * @throws ComumNegocioException
     * @throws BancoobException
     */
    DadosSelGeralDTO pesquisarPaginadaTipoMensagem(SelGeralReqDTO dto) throws ComumException, ComumNegocioException, BancoobException;

    /**
     * @param dto
     * @return RetornoDTO
     * @throws BancoobException
     */
    RetornoDTO obterTipoMensagem(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * 
     * @return RetornoDTO
     * @throws BancoobException
     */
    RetornoDTO carregarFiltrosTipoMensagem() throws BancoobException;
}
