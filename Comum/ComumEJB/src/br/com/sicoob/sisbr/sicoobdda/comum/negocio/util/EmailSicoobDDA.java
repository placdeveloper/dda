/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         EmailSicoobDDA.java
 * Data Cria��o:    Aug 14, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.email.Email;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * EmailSicoobDDA � respons�vel por
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por
     * 
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(EmailSicoobDDA.class);
    }

}
