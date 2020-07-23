/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoContingenciaDTO.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoContingenciaEnum é responsável por
 * 
 * @author Danilo.Barros
 */
public enum TipoContingenciaEnum {
    CIP(01, "CIP"),
    BANCOOB(02, "BANCOOB"),
    DESTINATARIO(03, "DESTINATARIO");

    private TipoContingenciaEnum(Integer codContingencia, String descContingencia) {
        this.codContingencia = codContingencia;
        this.descContingencia = descContingencia;
    }

    private Integer codContingencia;
    private String descContingencia;

    /**
     * @return o atributo codContingencia
     */
    public Integer getCodContingencia() {
        return codContingencia;
    }

    /**
     * Define o atributo codContingencia
     */
    public void setCodContingencia(Integer codContingencia) {
        this.codContingencia = codContingencia;
    }

    /**
     * @return o atributo descContingencia
     */
    public String getDescContingencia() {
        return descContingencia;
    }

    /**
     * Define o atributo descContingencia
     */
    public void setDescContingencia(String descContingencia) {
        this.descContingencia = descContingencia;
    }

}
