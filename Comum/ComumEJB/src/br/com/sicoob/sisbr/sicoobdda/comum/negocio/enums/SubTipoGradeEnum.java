/**
 * Projeto:         sdda-comum-ejb
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         SubTipoGradeEnum.java
 * Data Criação:    Sep 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * SubTipoGradeEnum é responsável por
 * 
 * @author jesliel.rocha
 */
public enum SubTipoGradeEnum {

    DDA(1, "GRADE DDA"),
    SICOOB(2, "GRADE SICOOB");

    /**
     * @param codSubTipoGrade
     * @param descSubTipoGrade
     */
    private SubTipoGradeEnum(Integer codSubTipoGrade, String descSubTipoGrade) {
        this.codSubTipoGrade = codSubTipoGrade.shortValue();
        this.descSubTipoGrade = descSubTipoGrade;
    }

    private Short codSubTipoGrade;
    private String descSubTipoGrade;

    /**
     * @return o atributo codSubTipoGrade
     */
    public Short getCodSubTipoGrade() {
        return codSubTipoGrade;
    }

    /**
     * Define o atributo codSubTipoGrade
     */
    public void setCodSubTipoGrade(Short codSubTipoGrade) {
        this.codSubTipoGrade = codSubTipoGrade;
    }

    /**
     * @return o atributo descSubTipoGrade
     */
    public String getDescSubTipoGrade() {
        return descSubTipoGrade;
    }

    /**
     * Define o atributo descSubTipoGrade
     */
    public void setDescSubTipoGrade(String descSubTipoGrade) {
        this.descSubTipoGrade = descSubTipoGrade;
    }

}
