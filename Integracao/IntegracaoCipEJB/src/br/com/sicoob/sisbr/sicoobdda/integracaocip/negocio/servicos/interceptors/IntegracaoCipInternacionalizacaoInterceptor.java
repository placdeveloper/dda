/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.interceptors
 * Arquivo:         IntegracaoCipInternacionalizacaoInterceptor.java
 * Data Criação:    Abr 18, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interceptors;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.mensagens.IntegracaoCIPResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public class IntegracaoCipInternacionalizacaoInterceptor extends ComumInternacionalizacaoInterceptor {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor#getResourceBundle()
     */
    @Override
    protected ComumResourceBundle getResourceBundle() {
        return IntegracaoCIPResourceBundle.getInstance();
    }
}