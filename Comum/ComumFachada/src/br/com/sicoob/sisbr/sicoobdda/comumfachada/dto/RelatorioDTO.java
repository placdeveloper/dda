/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         FiltroRelatorioDTO.java
 * Data Criação:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;

/**
 * FiltroRelatorioDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
public class RelatorioDTO extends BancoobDTO {

    private String contextoFlex;
    private String formato;
    private Object filtro;
    private List<? extends Object> listaDados;

    /**
     * @return o atributo contextoFlex
     */
    public String getContextoFlex() {
        return contextoFlex;
    }

    /**
     * Define o atributo contextoFlex
     */
    public void setContextoFlex(String contextoFlex) {
        this.contextoFlex = contextoFlex;
    }

    /**
     * @return o atributo formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Define o atributo formato
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * @return o atributo filtro
     */
    public Object getFiltro() {
        return filtro;
    }

    /**
     * Define o atributo filtro
     */
    public void setFiltro(Object filtro) {
        this.filtro = filtro;
    }

    /**
     * @return o atributo listaDados
     */
    public List<? extends Object> getListaDados() {
        return listaDados;
    }

    /**
     * Define o atributo listaDados
     */
    public void setListaDados(List<? extends Object> listaDados) {
        this.listaDados = listaDados;
    }

}
