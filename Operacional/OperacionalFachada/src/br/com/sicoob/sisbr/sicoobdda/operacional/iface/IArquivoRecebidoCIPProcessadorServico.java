/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IArquivoRecebidoCIPProcessadorServico.java
 * Data Criação:    Apr 19, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IArquivoRecebidoCIPProcessadorServico
 * 
 * @author Adriano.Pinheiro
 */
public interface IArquivoRecebidoCIPProcessadorServico {

    RetornoDTO abrirArquivoCIP(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

}
