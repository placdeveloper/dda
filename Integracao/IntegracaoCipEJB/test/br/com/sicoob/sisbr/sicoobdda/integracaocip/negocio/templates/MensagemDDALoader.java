/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDALoader.java
 * Data Cria��o:    Nov 4, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDALoader � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public abstract class MensagemDDALoader {

    /**
     * 
     */
    protected MensagemDDALoader() {
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDA
     * 
     */
    public static MensagemDDA geraMensagemDDA() {
        MensagemDDA msg = new MensagemDDA();
        msg.setId(Constantes.LONG_UM);
        msg.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        msg.setXmlMensagem(Constantes.STRING_NUMERO_0);
        msg.setTipoMensagemDDA(TipoMensagemDDALoader.geraTipoMensagemDDA(Constantes.STRING_NUMERO_1));
        return msg;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return List<MensagemDDA>
     * 
     */
    public static List<MensagemDDA> geraListaMensagemDDA() {
        List<MensagemDDA> lista = new ArrayList<MensagemDDA>();
        lista.add(geraMensagemDDA());
        return lista;
    }

}
