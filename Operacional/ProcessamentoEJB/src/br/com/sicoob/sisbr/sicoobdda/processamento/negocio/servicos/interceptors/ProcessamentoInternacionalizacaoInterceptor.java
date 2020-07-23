package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.interceptors;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor;
import br.com.sicoob.sisbr.sicoobdda.processamento.infraestrutura.mensagens.ProcessamentoResourceBundle;

/**
 * Interceptor que coloca o contexto de internacionalizacao para o sistema Processamento
 * 
 * @author Sicoob
 */
public class ProcessamentoInternacionalizacaoInterceptor extends ComumInternacionalizacaoInterceptor {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors.ComumInternacionalizacaoInterceptor#getResourceBundle()
     */
    @Override
    protected ComumResourceBundle getResourceBundle() {
        return ProcessamentoResourceBundle.getInstance();
    }
}