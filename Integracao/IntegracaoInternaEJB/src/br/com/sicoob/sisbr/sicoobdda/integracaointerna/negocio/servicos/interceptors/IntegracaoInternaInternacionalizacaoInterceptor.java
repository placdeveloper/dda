package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.interceptors;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.infraestrutura.mensagens.IntegracaoInternaResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema IntegracaoInternaPrivada
 * 
 * @author Samuell.Ramos
 */
public class IntegracaoInternaInternacionalizacaoInterceptor extends ComumInternacionalizacaoInterceptor {

    @Override
    protected ComumResourceBundle getResourceBundle() {
        return IntegracaoInternaResourceBundle.getInstance();
    }
}