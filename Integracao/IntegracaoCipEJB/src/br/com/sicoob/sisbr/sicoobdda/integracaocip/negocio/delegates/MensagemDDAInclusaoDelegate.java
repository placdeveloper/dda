/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MensagemDDAInclusaoDelegate.java
 * Data Criação:    Outubro 4, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAInclusaoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemDDAInclusaoDelegate
 * 
 * @author Francisco.marcio
 */
public class MensagemDDAInclusaoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemDDAInclusaoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemDDAInclusaoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemDDAInclusaoServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAInclusaoServico#incluirMensagemDDA(java.lang.String)
     */
    public void incluirMensagemDDA(String xmlRecebido) throws BancoobException {
        localizarServico().incluirMensagemDDA(xmlRecebido);
    }

}
