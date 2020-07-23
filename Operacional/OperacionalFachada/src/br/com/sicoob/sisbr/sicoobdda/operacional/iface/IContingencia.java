/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IContingencia.java
 * Data Cria��o:    Jan 4, 2017
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
     * M�todo
     * 
     * @param dto
     * @return RetornoDTO
     * @throws BancoobException
     */
    RetornoDTO incluirContingencia(RequisicaoReqDTO dto) throws BancoobException;

    /**
     * M�todo
     * 
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO listarHistoricoContingencias() throws ComumException;

    /**
     * M�todo
     * 
     * @return RetornoDTO
     * @throws ComumException
     */
    RetornoDTO isContingenciaHabilitada() throws ComumException;
}
