package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DDA.BOLETODDANOTAFISCAL
 * 
 * @author George.santos
 */
@Entity
@Table(name = "BOLETODDANOTAFISCAL", schema = "DDA")
public class BoletoDDANotaFiscal extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDANOTAFISCAL", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    @Column(nullable = false)
    private String numNotaFiscal;

    @Column(nullable = false)
    private DateTimeDB dataEmissaoNotaFiscal;

    @Column(nullable = false)
    private BigDecimal valorNotaFiscal;

    /**
     * 
     */
    public BoletoDDANotaFiscal() {
        super();
    }

    /**
     * @param boletoDDA
     * @param numNotaFiscal
     * @param dataEmissaoNotaFiscal
     * @param valorNotaFiscal
     */
    public BoletoDDANotaFiscal(BoletoDDA boletoDDA, String numNotaFiscal, DateTimeDB dataEmissaoNotaFiscal, BigDecimal valorNotaFiscal) {
        super();
        this.boletoDDA = boletoDDA;
        this.numNotaFiscal = numNotaFiscal;
        this.dataEmissaoNotaFiscal = dataEmissaoNotaFiscal;
        this.valorNotaFiscal = valorNotaFiscal;
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
     * @return o atributo numNotaFiscal
     */
    public String getNumNotaFiscal() {
        return numNotaFiscal;
    }

    /**
     * Define o atributo numNotaFiscal
     */
    public void setNumNotaFiscal(String numNotaFiscal) {
        this.numNotaFiscal = numNotaFiscal;
    }

    /**
     * @return o atributo dataEmissaoNotaFiscal
     */
    public DateTimeDB getDataEmissaoNotaFiscal() {
        return dataEmissaoNotaFiscal;
    }

    /**
     * Define o atributo dataEmissaoNotaFiscal
     */
    public void setDataEmissaoNotaFiscal(DateTimeDB dataEmissaoNotaFiscal) {
        this.dataEmissaoNotaFiscal = dataEmissaoNotaFiscal;
    }

    /**
     * @return o atributo valorNotaFiscal
     */
    public BigDecimal getValorNotaFiscal() {
        return valorNotaFiscal;
    }

    /**
     * Define o atributo valorNotaFiscal
     */
    public void setValorNotaFiscal(BigDecimal valorNotaFiscal) {
        this.valorNotaFiscal = valorNotaFiscal;
    }
}
