/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ExcluirRelacionamentoBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ExcluirRelacionamentoBeneficiarioDelegate
 * 
 * @author rafael.silva
 */
public class ExcluirRelacionamentoBeneficiarioDelegate extends OperacionalDelegate<OperacionalServico> implements ExcluirRelacionamentoBeneficiarioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected ExcluirRelacionamentoBeneficiarioServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarExcluirRelacionamentoBeneficiarioServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico#excluirRelacionamentoBeneficiario(java.lang.String,
     *      java.lang.Long)
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, Long numIdentBeneficiario, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException {
        localizarServico().excluirRelacionamentoBeneficiario(numCpfCnpj, numIdentBeneficiario, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico#excluirRelacionamentoBeneficiario(java.lang.String)
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException, ComumException {
        localizarServico().excluirRelacionamentoBeneficiario(numCpfCnpj, dataAtualMovimento, idCanal);
    }
}
