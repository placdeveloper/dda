package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ConsolidarEventosTarifaveisCipDelegate
 * 
 * @author Danilo.Barros
 */
@SuppressWarnings("rawtypes")
public class ConsolidarEventosTarifaveisCipDelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected RateioTarifasCipServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarRateioTarifasCipServico();
    }

    /**
     * Método responsável por consolidar os eventos DDA e PCR tarifávies
     * 
     * @throws ComumException
     */
    public void consolidarEventosTarifaveis() throws ComumException {
        localizarServico().consolidarEventosTarifaveis();
    }

}
