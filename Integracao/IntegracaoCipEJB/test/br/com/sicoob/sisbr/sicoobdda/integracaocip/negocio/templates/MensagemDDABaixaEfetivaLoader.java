/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDABaixaEfetivaLoader.java
 * Data Criação:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDABaixaEfetivaLoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDABaixaEfetivaLoader extends MensagemDDALoader {

    /**
     * 
     */
    private MensagemDDABaixaEfetivaLoader() {
        super();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABaixaEfetiva
     * 
     */
    public static MensagemDDABaixaEfetiva gerar() {
        MensagemDDABaixaEfetiva msg = new MensagemDDABaixaEfetiva(Constantes.LONG_UM);
        msg.setMensagemDDA(geraMensagemDDA());
        msg.setIdOperacaoLeg(Constantes.LONG_UM);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABaixaEfetiva>
     * 
     */
    public static List<MensagemDDABaixaEfetiva> gerarLista() {
        List<MensagemDDABaixaEfetiva> lista = new ArrayList<MensagemDDABaixaEfetiva>();
        lista.add(gerar());
        return lista;
    }
}
