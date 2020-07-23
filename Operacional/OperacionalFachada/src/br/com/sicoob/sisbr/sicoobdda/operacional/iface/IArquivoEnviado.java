package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;


/**
 * @author Samuell.Ramos
 */
public interface IArquivoEnviado {

	/**
	 * M�todo respons�vel por 
	 * @param dto
	 * @throws BancoobException void
	 * 
	 */
	void alterarArquivo(RequisicaoReqDTO dto) throws BancoobException;
	/**
	 * M�todo respons�vel por 
	 * @param dto
	 * @return
	 * @throws BancoobException DadosSelGeralDTO
	 * 
	 */
	DadosSelGeralDTO pesquisarArquivoEnviadoPaginado(SelGeralReqDTO dto) throws BancoobException;
	/**
	 * M�todo respons�vel por 
	 * @return
	 * @throws ComumException RetornoDTO
	 * 
	 */
	RetornoDTO carregarFiltrosArquivoEnviado() throws ComumException;
	/**
	 * M�todo respons�vel por 
	 * @param dto
	 * @return
	 * @throws ComumException RetornoDTO
	 * 
	 */
	RetornoDTO obterArquivoEnviado(RequisicaoReqDTO dto) throws ComumException; 
	
	
}
