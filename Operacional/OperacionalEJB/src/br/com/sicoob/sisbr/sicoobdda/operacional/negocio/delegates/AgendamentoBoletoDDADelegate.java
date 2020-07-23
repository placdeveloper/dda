package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AgendamentoBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AgendamentoBoletoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * AgendamentoBoletoDDADelegate é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class AgendamentoBoletoDDADelegate extends OperacionalDelegate<OperacionalServico> implements AgendamentoBoletoDDAServico {

    private AgendamentoBoletoDDAServico agendamentoBoletoDDAServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    public AgendamentoBoletoDDAServico localizarServico() {
        if (agendamentoBoletoDDAServico == null) {
            agendamentoBoletoDDAServico = OperacionalServiceLocator.getInstance().localizarAgendamentoBoletoDDAServico();
        }

        return agendamentoBoletoDDAServico;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultaBoletoDDAServico#obterParametrosAgendamento(int)
     */
    public AgendamentoBoletoDDADto obterParametrosAgendamento(int idInstituicao) throws ComumException, ComumNegocioException {
        return localizarServico().obterParametrosAgendamento(idInstituicao);
    }

}
