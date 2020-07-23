package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb;

import java.util.ArrayList;
import java.util.List;

/**
 * Linha de resultados, podendo possuir varias colunas com os valores.
 * 
 * @author Paulo.Stoppa
 */
public final class Linha {

    /**
     * Lista de valores
     */
    private final List<String> valores = new ArrayList<String>();

    public List<String> getValores() {
        return valores;
    }

    /**
     * Adiciona valor na lista de valores
     * 
     * @param valor
     */
    public void adicionarValor(Object valor) {
        valores.add(valor != null ? valor.toString() : null);
    }

}
