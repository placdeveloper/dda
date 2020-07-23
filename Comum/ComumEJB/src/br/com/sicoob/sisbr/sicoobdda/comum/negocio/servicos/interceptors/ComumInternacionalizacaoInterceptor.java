/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.servicos.interceptors
 * Arquivo:         ComumInternacionalizacaoInterceptor.java
 * Data Criação:    28/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors;

import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * ComumInternacionalizacaoInterceptor
 * 
 * @author Rafael.Silva
 */
public class ComumInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
     */
    @Override
    protected ComumResourceBundle getResourceBundle() {
        return ComumResourceBundle.getInstance();
    }

}
