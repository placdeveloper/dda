package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb;

import java.util.ArrayList;

/**
 * Classe que representa litsa de {@link Grupo} para realizar a formatação dos resultados
 * 
 * @author Paulo.Stoppa
 */
public class Grupos extends ArrayList<Grupo> {

    private static final long serialVersionUID = 2852397301040993031L;

    public Grupos() {
    }

    public Grupos(Grupo grupo) {
        this.add(grupo);
    }

}
