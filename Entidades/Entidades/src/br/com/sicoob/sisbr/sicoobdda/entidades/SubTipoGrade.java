/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         SubTipoGrade.java
 * Data Criação:    Ago 09, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoGradeHoraria
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "SUBTIPOGRADE", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SubTipoGradeVO")
public class SubTipoGrade extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1L;

    public static Short GRADE_TODOS = 0;
    public static Short GRADE_DDA = 1;
    public static Short GRADE_SICOOB = 2;

    @Id
    @Column(name = "CODSUBTIPOGRADE", unique = true, nullable = false)
    private Short codSubTipoGrade;

    private String descSubTipoGrade;

    @OneToMany(mappedBy = "subTipoGrade")
    private List<TipoGradeHoraria> listaTipoGradeHoraria;

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

    /**
     * @return o atributo listaTipoGradeHoraria
     */
    public List<TipoGradeHoraria> getListaTipoGradeHoraria() {
        return listaTipoGradeHoraria;
    }

    /**
     * Define o atributo listaTipoGradeHoraria
     */
    public void setListaTipoGradeHoraria(List<TipoGradeHoraria> listaTipoGradeHoraria) {
        this.listaTipoGradeHoraria = listaTipoGradeHoraria;
    }

}
