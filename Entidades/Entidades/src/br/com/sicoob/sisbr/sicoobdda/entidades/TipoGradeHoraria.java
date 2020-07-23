/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         TipoGradeHoraria.java
 * Data Criação:    Ago 09, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoGradeHoraria
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "TIPOGRADEHORARIA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoGradeHorariaVO")
public class TipoGradeHoraria extends SicoobDDAEntidade {

    private static final long serialVersionUID = -360348702310281585L;

    public static final String DDA12 = "DDA12";

    @Id
    @Column(name = "CODTIPOGRADEHORARIA", unique = true, nullable = false)
    private String codTipoGradeHoraria;

    private String descTipoGradeHoraria;

    @OneToMany(mappedBy = "tipoGradeHoraria")
    private List<GradeHoraria> listaGradeHoraria;

    @ManyToOne
    @JoinColumn(name = "CODSUBTIPOGRADE")
    private SubTipoGrade subTipoGrade;

    @OneToOne
    @JoinColumn(name = "CODTIPOGRADEORIGEM")
    private TipoGradeHoraria tipoGradeHorariaOrigem;

    private DateTimeDB dataHoraAberturaPadrao;

    private DateTimeDB dataHoraFechamentoPadrao;

    public TipoGradeHoraria() {
    }

    public TipoGradeHoraria(String codTipoGradeHoraria) {
        this.codTipoGradeHoraria = codTipoGradeHoraria;
    }

    /**
     * @return o atributo codTipoGradeHoraria
     */
    public String getCodTipoGradeHoraria() {
        return codTipoGradeHoraria;
    }

    /**
     * Define o atributo codTipoGradeHoraria
     */
    public void setCodTipoGradeHoraria(String codTipoGradeHoraria) {
        this.codTipoGradeHoraria = codTipoGradeHoraria;
    }

    /**
     * @return o atributo descTipoGradeHoraria
     */
    public String getDescTipoGradeHoraria() {
        return descTipoGradeHoraria;
    }

    /**
     * Define o atributo descTipoGradeHoraria
     */
    public void setDescTipoGradeHoraria(String descTipoGradeHoraria) {
        this.descTipoGradeHoraria = descTipoGradeHoraria;
    }

    /**
     * @return o atributo listaGradeHoraria
     */
    public List<GradeHoraria> getListaGradeHoraria() {
        return listaGradeHoraria;
    }

    /**
     * Define o atributo listaGradeHoraria
     */
    public void setListaGradeHoraria(List<GradeHoraria> listaGradeHoraria) {
        this.listaGradeHoraria = listaGradeHoraria;
    }

    /**
     * @return o atributo subTipoGrade
     */
    public SubTipoGrade getSubTipoGrade() {
        return subTipoGrade;
    }

    /**
     * Define o atributo subTipoGrade
     */
    public void setSubTipoGrade(SubTipoGrade subTipoGrade) {
        this.subTipoGrade = subTipoGrade;
    }

    /**
     * @return o atributo tipoGradeHorariaOrigem
     */
    public TipoGradeHoraria getTipoGradeHorariaOrigem() {
        return tipoGradeHorariaOrigem;
    }

    /**
     * Define o atributo tipoGradeHorariaOrigem
     */
    public void setTipoGradeHorariaOrigem(TipoGradeHoraria tipoGradeHorariaOrigem) {
        this.tipoGradeHorariaOrigem = tipoGradeHorariaOrigem;
    }

    /**
     * @return o atributo dataHoraAberturaPadrao
     */
    public DateTimeDB getDataHoraAberturaPadrao() {
        return dataHoraAberturaPadrao;
    }

    /**
     * Define o atributo dataHoraAberturaPadrao
     */
    public void setDataHoraAberturaPadrao(DateTimeDB dataHoraAberturaPadrao) {
        this.dataHoraAberturaPadrao = dataHoraAberturaPadrao;
    }

    /**
     * @return o atributo dataHoraFechamentoPadrao
     */
    public DateTimeDB getDataHoraFechamentoPadrao() {
        return dataHoraFechamentoPadrao;
    }

    /**
     * Define o atributo dataHoraFechamentoPadrao
     */
    public void setDataHoraFechamentoPadrao(DateTimeDB dataHoraFechamentoPadrao) {
        this.dataHoraFechamentoPadrao = dataHoraFechamentoPadrao;
    }
}
