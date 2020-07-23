/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         TipoMensagemDDALoader.java
 * Data Cria��o:    Jan 12, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * TipoMensagemDDALoader � respons�vel por 
 * 
 * @author felipe.rosa
 */
public abstract class TipoMensagemDDALoader {

    /**
     * 
     */
    private TipoMensagemDDALoader() {

    }

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoMensagem
     * @return TipoMensagemDDA
     * 
     */
    public static TipoMensagemDDA geraTipoMensagemDDA(String codTipoMensagem) {
        TipoMensagemDDA tipo = new TipoMensagemDDA(codTipoMensagem);
        tipo.setNumPrioridadeEnvio(Constantes.INTEGER_UM);
        return tipo;
    }
}
