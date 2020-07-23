/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors
 * Arquivo:         Xpto.java
 * Data Criação:    Apr 19, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.interceptors;

import java.util.Collection;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;

/**
 * Interceptor para os EJB's
 * 
 * @author felipe.rosa
 */
public final class SicoobDDAInterceptorAPILog {

    private final ISicoobLogger log = SicoobLoggerPadrao.getInstance(SicoobDDAInterceptorAPILog.class);

    /**
     * método que intercepta os métodos do EJB, e debuga os parâmetros e gera log Exception.
     * 
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object interceptarMetodo(InvocationContext ctx) throws Exception { // NOSONAR
        Object retorno = null;
        if (!ctx.getMethod().getName().equals("verificarDisponibilidade")) {
            debug("Entrou no método " + ctx.getTarget().getClass().getSimpleName() + "." + ctx.getMethod().getName());
            gerarLogParametros(ctx.getParameters());
            try {
                retorno = ctx.proceed();
                gerarLogRetorno(retorno);
            } catch (SicoobDDANegocioException e) {
                debug("Exceção: " + e.getClass().getName());
                log.alerta(e, e.getMessage());
                throw e;
            } catch (SicoobDDAException e) {
                debug("Exceção: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw e;
            } catch (RuntimeException e) {
                debug("Exceção: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw e;
            } catch (Exception e) { // NOSONAR
                debug("Exceção: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw new SicoobDDAException(e.getMessage());
            }
            debug("Saiu do método " + ctx.getTarget().getClass().getSimpleName() + "." + ctx.getMethod().getName());
        } else {
            retorno = ctx.proceed();
        }
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param parametros void
     * 
     */
    private void gerarLogParametros(Object[] parametros) {
        debug("*****Parâmetros:");
        if (parametros != null) {
            for (Object param : parametros) {
                debug("*****Valor Parâmetro: " + (param == null ? "NULL" : param));
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param retorno void
     * 
     */
    private void gerarLogRetorno(Object retorno) {
        if (retorno == null) {
            debug("Resultado NULO");
        } else if (retorno instanceof Collection) {
            debug("Quantidade de resultados na lista: " + ((Collection<?>) retorno).size());
        } else if (retorno instanceof ConsultaDto<?>) {
            debug("Resultado: ConsultaDto<?>");
        } else {
            debug("Resultado:" + retorno.toString());
        }
    }

    /**
     * recupera a mensagem e gera o log de DEBUG
     * 
     * @param chave
     * @param parametros
     */
    private void debug(String chave) {
        log.debug("############### " + chave);
    }

}
