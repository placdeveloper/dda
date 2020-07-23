/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDAAceiteLoader.java
 * Data Cria��o:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDAAceiteLoader � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDAAceiteLoader extends MensagemDDALoader {

    /**
     * 
     */
    private MensagemDDAAceiteLoader() {
        super();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDAAceite
     * 
     */
    public static MensagemDDAAceite gerar() {
        MensagemDDAAceite msg = new MensagemDDAAceite(Constantes.LONG_UM, Boolean.TRUE, Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM);
        msg.setIdEventoTituloDDA(Constantes.LONG_UM);
        msg.setMensagemDDA(geraMensagemDDA());
        return msg;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return List<MensagemDDAAceite>
     * 
     */
    public static List<MensagemDDAAceite> gerarLista() {
        List<MensagemDDAAceite> lista = new ArrayList<MensagemDDAAceite>();
        lista.add(gerar());
        return lista;
    }
}
