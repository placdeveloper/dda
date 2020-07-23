/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDABoletoLoader.java
 * Data Cria��o:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDABoletoLoader � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDABoletoLoader extends MensagemDDALoader {

    /**
     * 
     */
    private MensagemDDABoletoLoader() {
        super();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABoleto
     * 
     */
    public static MensagemDDABoleto gerar() {
        MensagemDDABoleto msg = new MensagemDDABoleto(Constantes.LONG_UM);
        msg.setMensagemDDA(geraMensagemDDA());
        msg.setIdOperacaoLeg(Constantes.LONG_UM);
        return msg;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return List<MensagemDDABoleto>
     * 
     */
    public static List<MensagemDDABoleto> gerarLista() {
        List<MensagemDDABoleto> lista = new ArrayList<MensagemDDABoleto>();
        lista.add(gerar());
        return lista;
    }
}
