package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DDA.BOLETODDATEXTOINFO
 * 
 * @author George.santos
 */
@Entity
@Table(name = "BOLETODDATEXTOINFO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDATextoInfoVO")
public class BoletoDDATextoInfo extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDATEXTOINFO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    @Column(nullable = false)
    private String descTextoInformativo;

    /**
     * 
     */
    public BoletoDDATextoInfo() {
        super();
    }

    /**
     * @param boletoDDA
     * @param descTextoInformativo
     */
    public BoletoDDATextoInfo(BoletoDDA boletoDDA, String descTextoInformativo) {
        super();
        this.boletoDDA = boletoDDA;
        this.descTextoInformativo = descTextoInformativo;
    }

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo boletoDDA
     */
    public BoletoDDA getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDA boletoDDA) {
        this.boletoDDA = boletoDDA;
    }

    /**
     * @return o atributo descTextoInformativo
     */
    public String getDescTextoInformativo() {
        return descTextoInformativo;
    }

    /**
     * Define o atributo descTextoInformativo
     */
    public void setDescTextoInformativo(String descTextoInformativo) {
        this.descTextoInformativo = descTextoInformativo;
    }

}
