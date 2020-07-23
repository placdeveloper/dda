/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         CadastrarBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.math.BigDecimal;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CancelamentoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * CancelamentoBaixaOperacionalDelegate é responsável por
 * 
 * @author George.Santos
 */
public class CancelamentoBaixaOperacionalDelegate extends OperacionalDelegate<OperacionalServico> implements CancelamentoBaixaOperacionalServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected CancelamentoBaixaOperacionalServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarCancelamentoBaixaOperacionalServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CancelamentoBaixaOperacionalServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws ComumException,
            ComumNegocioException {
        localizarServico().processarCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago, idCanal);
    }
}
