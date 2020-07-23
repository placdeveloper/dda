/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDABoletoLoader.java
 * Data Criação:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDABoletoLoader é responsável por
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
     * Método responsável por
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
     * Método responsável por
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
