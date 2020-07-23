package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AgendamentoBoletoDDADto;

/**
 * AgendamentoBoletoDDAServico � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface AgendamentoBoletoDDAServico extends OperacionalServico {

    /**
     * M�todo respons�vel por obter alguns par�metros para o agendamento.
     * 
     * @param idInstituicao
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    AgendamentoBoletoDDADto obterParametrosAgendamento(int idInstituicao) throws ComumException, ComumNegocioException;

}
