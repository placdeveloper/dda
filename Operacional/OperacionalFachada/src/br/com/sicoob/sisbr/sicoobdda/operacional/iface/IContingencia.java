/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IContingencia.java
 * Data Criação:    Jan 4, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IContingencia
 * 
 * @author Danilo.Barros
 */
public interface IContingencia {

    /**
     * Método
     * 
     * @param dto
     * @return RetornoDTO
     * @throws BancoobException
     */
    RetornoDTO incluirContingencia(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * Método
     * 
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO listarHistoricoContingencias() throws ComumException;

    /**
     * Método
     * 
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO isContingenciaHabilitada() throws ComumException;
}
