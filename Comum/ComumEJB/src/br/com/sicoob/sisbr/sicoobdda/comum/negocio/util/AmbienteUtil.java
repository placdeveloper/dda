/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         AmbienteUtil.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * AmbienteUtil é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class AmbienteUtil {

    /**
     * 
     */
    private AmbienteUtil() {

    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getNomeServidor() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            logger.erro(ex, ex.getMessage());

            return "Servidor desconhecido.";
        }
    }

    /**
     * Logger
     */
    private static ISicoobLogger logger = SicoobLoggerPadrao.getInstance(AmbienteUtil.class);
}
