/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.delegates
 * Arquivo:         AntecipacaoDelegate.java
 * Data Criação:    Nov 09, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.SSPBServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator.IntegracaoInternaServiceLocator;

/**
 * SSPBDelegate é responsável por
 * 
 * @author George.Santos
 */
public class SSPBDelegate extends IntegracaoInternaDelegate<IntegracaoInternaServico> implements SSPBServico {

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
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected SSPBServico localizarServico() {
        return IntegracaoInternaServiceLocator.getInstance().localizarSSPBServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.sicoobdda.integracao.interna.privada.negocio.servicos.SSPBServico#criptografar(byte[])
     */
    public byte[] criptografar(byte[] bytesXmlEnvio) throws IntegracaoInternaException {
        return localizarServico().criptografar(bytesXmlEnvio);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.sicoobdda.integracao.interna.privada.negocio.servicos.SSPBServico#decriptar(byte[])
     */
    public byte[] decriptar(byte[] bytesXml) throws IntegracaoInternaException {
        return localizarServico().decriptar(bytesXml);
    }

}
