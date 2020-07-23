/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  operacional-backoffice-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.iface
 * Arquivo:         IRelatorioSicoobDDA.java
 * Data Criação:    31/03/2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * IRelatorioSicoobDDA
 * 
 * @author samuell.ramos
 */
public interface IRelatorioSicoobDDA {

    /**
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException 
     */
	void relatorioTermoPagadorEletronico(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException;

    /**
     * @param dto
     * @throws ComumException
     */
	void gerarRelatorioMonitoramentoMensagensCIP(RequisicaoReqDTO dto) throws ComumException, ComumNegocioException ;
}