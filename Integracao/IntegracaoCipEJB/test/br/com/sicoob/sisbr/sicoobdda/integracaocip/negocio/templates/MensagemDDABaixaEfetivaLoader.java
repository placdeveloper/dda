/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDABaixaEfetivaLoader.java
 * Data Cria��o:    May 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDABaixaEfetivaLoader � respons�vel por
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
     * M�todo respons�vel por
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
     * M�todo respons�vel por
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
