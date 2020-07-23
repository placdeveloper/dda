/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.iface
 * Arquivo:         IRelatorioServico.java
 * Data Cria��o:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.sicoob.relatorio.api.dto.RetornoRelatorioDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * IRelatorioServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface IRelatorioServico {

    /**
     * M�todo respons�vel por
     * 
     * @param dto
     * @return
     * @throws ComumException List<RetornoRelatorioDTO>
     * 
     */
    List<RetornoRelatorioDTO> gerar(RequisicaoReqDTO dto) throws ComumException;

}
