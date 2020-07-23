/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MenagemDDAAGEN001Delegate.java
 * Data Criação:    May 5, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAAGEN001Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MenagemDDAAGEN001Delegate é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public class MensagemDDAAGEN001Delegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemDDAAGEN001Servico {

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemADDAAGEN001Servico#incluir()
     */
    public void incluir(String mensagemEco) throws BancoobException {
        localizarServico().incluir(mensagemEco);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemDDAAGEN001Servico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemADDAAGEN001Servico();
    }
}
