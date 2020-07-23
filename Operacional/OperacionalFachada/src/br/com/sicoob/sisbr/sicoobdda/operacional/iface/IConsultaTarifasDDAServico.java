package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;

public interface IConsultaTarifasDDAServico {

    /**
     * Método responsável por pesquisar os lançamentos das tarifas
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    DadosSelGeralDTO pesquisarTarifasDDAPagasPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * Método responsável por listar os lançamentos
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO listarLancamentosTarifas(RequisicaoReqDTO dto) throws BancoobException;
 
    /**
     * Método responsável por listar o detalhamento da movimentação financeira
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    DadosSelGeralDTO pesquisarMovimentacaoSicoobDDAPaginado(SelGeralReqDTO dto) throws BancoobException;

}
