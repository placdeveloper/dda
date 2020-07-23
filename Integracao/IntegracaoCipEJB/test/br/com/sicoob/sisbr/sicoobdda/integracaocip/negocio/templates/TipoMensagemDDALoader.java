/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         TipoMensagemDDALoader.java
 * Data Criação:    Jan 12, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * TipoMensagemDDALoader é responsável por 
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
     * Método responsável por
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
