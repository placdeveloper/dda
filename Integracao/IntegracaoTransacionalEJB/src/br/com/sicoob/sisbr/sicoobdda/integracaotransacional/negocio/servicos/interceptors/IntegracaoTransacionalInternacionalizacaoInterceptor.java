package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.interceptors;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.infraestrutura.mensagens.IntegracaoTransacionalResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema Integracao Transacional
 * 
 * @author george.santos
 */
public class IntegracaoTransacionalInternacionalizacaoInterceptor extends ComumInternacionalizacaoInterceptor {

    @Override
    protected ComumResourceBundle getResourceBundle() {
        return IntegracaoTransacionalResourceBundle.getInstance();
    }

}
