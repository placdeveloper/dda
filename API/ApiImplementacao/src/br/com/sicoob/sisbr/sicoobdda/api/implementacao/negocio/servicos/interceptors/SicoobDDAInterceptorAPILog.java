/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interceptors
 * Arquivo:         Xpto.java
 * Data Cria��o:    Apr 19, 2017
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
     * m�todo que intercepta os m�todos do EJB, e debuga os par�metros e gera log Exception.
     * 
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object interceptarMetodo(InvocationContext ctx) throws Exception { // NOSONAR
        Object retorno = null;
        if (!ctx.getMethod().getName().equals("verificarDisponibilidade")) {
            debug("Entrou no m�todo " + ctx.getTarget().getClass().getSimpleName() + "." + ctx.getMethod().getName());
            gerarLogParametros(ctx.getParameters());
            try {
                retorno = ctx.proceed();
                gerarLogRetorno(retorno);
            } catch (SicoobDDANegocioException e) {
                debug("Exce��o: " + e.getClass().getName());
                log.alerta(e, e.getMessage());
                throw e;
            } catch (SicoobDDAException e) {
                debug("Exce��o: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw e;
            } catch (RuntimeException e) {
                debug("Exce��o: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw e;
            } catch (Exception e) { // NOSONAR
                debug("Exce��o: " + e.getClass().getName());
                log.erro(e, e.getMessage());
                throw new SicoobDDAException(e.getMessage());
            }
            debug("Saiu do m�todo " + ctx.getTarget().getClass().getSimpleName() + "." + ctx.getMethod().getName());
        } else {
            retorno = ctx.proceed();
        }
        return retorno;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param parametros void
     * 
     */
    private void gerarLogParametros(Object[] parametros) {
        debug("*****Par�metros:");
        if (parametros != null) {
            for (Object param : parametros) {
                debug("*****Valor Par�metro: " + (param == null ? "NULL" : param));
            }
        }
    }

    /**
     * M�todo respons�vel por
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
