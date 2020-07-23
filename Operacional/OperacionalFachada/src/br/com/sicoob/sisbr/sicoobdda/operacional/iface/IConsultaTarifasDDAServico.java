package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;

public interface IConsultaTarifasDDAServico {

    /**
     * M�todo respons�vel por pesquisar os lan�amentos das tarifas
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    DadosSelGeralDTO pesquisarTarifasDDAPagasPaginado(SelGeralReqDTO dto) throws BancoobException;

    /**
     * M�todo respons�vel por listar os lan�amentos
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    RetornoDTO listarLancamentosTarifas(RequisicaoReqDTO dto) throws BancoobException;
 
    /**
     * M�todo respons�vel por listar o detalhamento da movimenta��o financeira
     * 
     * @param dto
     * @return
     * @throws BancoobException
     */
    DadosSelGeralDTO pesquisarMovimentacaoSicoobDDAPaginado(SelGeralReqDTO dto) throws BancoobException;

}
