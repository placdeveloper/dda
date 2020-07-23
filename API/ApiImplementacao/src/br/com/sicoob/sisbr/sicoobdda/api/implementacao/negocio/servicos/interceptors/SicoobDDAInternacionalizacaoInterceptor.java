/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-implementacao
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.interceptors
 * Arquivo:         SicoobDDAInternacionalizacaoInterceptor.java
 * Data Criação:    10/08/2016
 */
package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.interceptors;

import br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.api.implementacao.infraestrutura.mensagens.SicoobDDAResourceBundle;

/**
 * SicoobDDAInternacionalizacaoInterceptor
 * 
 */
public class SicoobDDAInternacionalizacaoInterceptor extends InternacionalizacaoInterceptor {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.interceptors.InternacionalizacaoInterceptor#getResourceBundle()
     */
    @Override
    protected SicoobDDAResourceBundle getResourceBundle() {
        return SicoobDDAResourceBundle.getInstance();
    }

}
