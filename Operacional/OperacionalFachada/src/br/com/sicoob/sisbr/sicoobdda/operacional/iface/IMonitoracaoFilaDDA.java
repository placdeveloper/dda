package br.com.sicoob.sisbr.sicoobdda.operacional.iface;

import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * @author Samuell.Ramos
 */
public interface IMonitoracaoFilaDDA {

	    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException RetornoDTO
     * 
     */
    RetornoDTO obterDadosMonitoramentoFila() throws ComumException;
}
