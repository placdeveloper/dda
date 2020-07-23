/**
 * 
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.util.Properties;

import br.com.bancoob.infraestrutura.conexao.BancoobDataSource;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Datasource padrao para o sistema
 * 
 * @author rafael.silva
 */
public class EntidadesLegadoDatasource extends BancoobDataSource {

    /** O atributo informacoes cooperativa. */
    private static ThreadLocal<Integer> informacoesCooperativa = new ThreadLocal<Integer>();

    /** O atributo logger. */
    private final ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());

    /**
     * @param nomeJndi
     * @param propriedades
     */
    public EntidadesLegadoDatasource(String nomeJndi, Properties propriedades) {
        super(nomeJndi, propriedades);
    }

    /**
     * @param numeroCooperativa the numeroCooperativa to set
     */
    public static void definirNumeroCooperativa(Integer numCooperativa) {
        informacoesCooperativa.set(numCooperativa);
    }

    /**
     * Recupera o número da cooperativa a ser manipulada.
     * 
     * @return o número da cooperativa a ser manipulada.
     */
    protected String recuperarNumeroCooperativa() {
        logger.info("Recuperando numero cooperativa para datasource...");
        Integer numeroCooperativa = informacoesCooperativa.get();
        String valor = "";
        if (numeroCooperativa != null) {
            valor = numeroCooperativa.toString();
        }
        logger.info("Numero cooperativa recuperado: ", valor);
        return valor;
    }

}