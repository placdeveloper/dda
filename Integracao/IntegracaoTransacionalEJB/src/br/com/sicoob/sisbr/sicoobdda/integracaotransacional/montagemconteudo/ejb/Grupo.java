package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb;

/**
 * Grupo de resultados, podendo possuir varias linhas com os valores.
 * 
 * @author Paulo.Stoppa
 */
public final class Grupo {

    public Grupo() {
    }

    public Grupo(Linha linha) {
        this.adicionarLinha(linha);
    }

    /**
     * Lista de linhas
     */
    private final Linhas linhas = new Linhas();

    public Linhas getLinhas() {
        return linhas;
    }

    /**
     * Adiciona linha na lista de Linhas
     * 
     * @param valor
     */
    public void adicionarLinha(Linha linha) {
        linhas.add(linha);
    }

}
