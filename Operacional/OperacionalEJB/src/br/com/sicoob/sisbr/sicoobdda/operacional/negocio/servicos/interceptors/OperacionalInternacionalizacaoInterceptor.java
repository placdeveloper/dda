package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interceptors;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.operacional.infraestrutura.mensagens.OperacionalResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o Operacional
 * 
 * @author Sicoob
 */
public class OperacionalInternacionalizacaoInterceptor extends ComumInternacionalizacaoInterceptor {

    @Override
    protected ComumResourceBundle getResourceBundle() {
        return OperacionalResourceBundle.getInstance();
    }
}