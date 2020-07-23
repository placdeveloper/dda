package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VIW_INSTITUICAO", schema = "SCI")
public class ViewInstituicao extends SicoobDDAEntidade {

    private static final long serialVersionUID = 5051534478440523461L;

    @Id
    @Column(name = "IDINSTITUICAO")
    private Integer id;
    private Integer idUnidadeInst;
    private String numCooperativa;

    @Column(name = "numCoopPai")
    private String numCooperativaPai;

    private String descSiglaCoop;
    private Short codTipoUnidade;
    private Integer numPac;
    private String descNomeCoop;

    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return o atributo idUnidadeInst
     */
    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    /**
     * Define o atributo idUnidadeInst
     */
    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    /**
     * @return o atributo numCooperativa
     */
    public String getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(String numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo numCooperativaPai
     */
    public String getNumCooperativaPai() {
        return numCooperativaPai;
    }

    /**
     * Define o atributo numCooperativaPai
     */
    public void setNumCooperativaPai(String numCooperativaPai) {
        this.numCooperativaPai = numCooperativaPai;
    }

    /**
     * @return o atributo descSiglaCoop
     */
    public String getDescSiglaCoop() {
        return descSiglaCoop;
    }

    /**
     * Define o atributo descSiglaCoop
     */
    public void setDescSiglaCoop(String descSiglaCoop) {
        this.descSiglaCoop = descSiglaCoop;
    }

    /**
     * @return o atributo codTipoUnidade
     */
    public Short getCodTipoUnidade() {
        return codTipoUnidade;
    }

    /**
     * Define o atributo codTipoUnidade
     */
    public void setCodTipoUnidade(Short codTipoUnidade) {
        this.codTipoUnidade = codTipoUnidade;
    }

    /**
     * @return o atributo numPac
     */
    public Integer getNumPac() {
        return numPac;
    }

    /**
     * Define o atributo numPac
     */
    public void setNumPac(Integer numPac) {
        this.numPac = numPac;
    }

    /**
     * @return o atributo descNomeCoop
     */
    public String getDescNomeCoop() {
        return descNomeCoop;
    }

    /**
     * Define o atributo descNomeCoop
     */
    public void setDescNomeCoop(String descNomeCoop) {
        this.descNomeCoop = descNomeCoop;
    }

}
