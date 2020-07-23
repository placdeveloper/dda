/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoContingenciaDTO.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoSituacaoContingenciaEnum
 * 
 * @author Danilo.Barros
 */
public enum TipoSituacaoContingenciaEnum {
    HABILITADA(1, "Habilitada"),
    DESABILITADA(2, "Desabilitada");

    private TipoSituacaoContingenciaEnum(Integer codSitContingencia, String descSitContingencia) {
        this.codSitContingencia = codSitContingencia;
        this.descSitContingencia = descSitContingencia;
    }

    private Integer codSitContingencia;
    private String descSitContingencia;

    /**
     * @return o atributo codSitContingencia
     */
    public Integer getCodSitContingencia() {
        return codSitContingencia;
    }

    /**
     * Define o atributo codSitContingencia
     */
    public void setCodSitContingencia(Integer codSitContingencia) {
        this.codSitContingencia = codSitContingencia;
    }

    /**
     * @return o atributo descSitContingencia
     */
    public String getDescSitContingencia() {
        return descSitContingencia;
    }

    /**
     * Define o atributo descSitContingencia
     */
    public void setDescSitContingencia(String descSitContingencia) {
        this.descSitContingencia = descSitContingencia;
    }

}