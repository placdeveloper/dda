/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         EmailSicoobDDA.java
 * Data Criação:    Aug 14, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.email.Email;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * EmailSicoobDDA é responsável por
 * 
 * @author felipe.rosa
 */
public final class EmailSicoobDDA extends Email {

    /**
     * 
     */
    private EmailSicoobDDA() {

    }

    /**
     * Método responsável por
     * 
     * @param remetente
     * @param destinatario
     * @param assunto
     * @param corpoMensagem void
     * 
     */
    public static void enviar(String remetente, String destinatario, String assunto, String corpoMensagem) {
        try {
            Email email = new Email(remetente, destinatario, assunto, corpoMensagem);
            email.enviar();
        } catch (BancoobRuntimeException e) {
            getLogger().debug(e.getMessage());
        }
    }

    /**
     * Método responsável por
     * 
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(EmailSicoobDDA.class);
    }

}
