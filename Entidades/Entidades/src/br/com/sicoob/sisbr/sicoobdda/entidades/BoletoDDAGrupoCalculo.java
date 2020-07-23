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
 * DDA.BOLETODDAGRUPOCALCULO
 * 
 * @author George.santos
 */
@Entity
@Table(name = "BOLETODDAGRUPOCALCULO", schema = "DDA")
public class BoletoDDAGrupoCalculo extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDAGRUPOCALCULO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    @Column(nullable = false)
    private BigDecimal valorCalculadoJuros;

    @Column(nullable = false)
    private BigDecimal valorCalculadoMulta;

    @Column(nullable = false)
    private BigDecimal valorCalculadoDesconto;

    @Column(nullable = false)
    private BigDecimal valorTotalCobrado;

    @Column(nullable = false)
    private DateTimeDB dataValidadeCalculo;

    /**
     * 
     */
    public BoletoDDAGrupoCalculo() {
        super();
    }

    /**
     * @param boletoDDA
     * @param valorCalculadoJuros
     * @param valorCalculadoMulta
     * @param valorCalculadoDesconto
     * @param valorTotalCobrado
     * @param dataValidadeCalculo
     */
    public BoletoDDAGrupoCalculo(BoletoDDA boletoDDA, BigDecimal valorCalculadoJuros, BigDecimal valorCalculadoMulta, BigDecimal valorCalculadoDesconto,
            BigDecimal valorTotalCobrado, DateTimeDB dataValidadeCalculo) {
        super();
        this.boletoDDA = boletoDDA;
        this.valorCalculadoJuros = valorCalculadoJuros;
        this.valorCalculadoMulta = valorCalculadoMulta;
        this.valorCalculadoDesconto = valorCalculadoDesconto;
        this.valorTotalCobrado = valorTotalCobrado;
        this.dataValidadeCalculo = dataValidadeCalculo;
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
     * @return o atributo valorCalculadoJuros
     */
    public BigDecimal getValorCalculadoJuros() {
        return valorCalculadoJuros;
    }

    /**
     * Define o atributo valorCalculadoJuros
     */
    public void setValorCalculadoJuros(BigDecimal valorCalculadoJuros) {
        this.valorCalculadoJuros = valorCalculadoJuros;
    }

    /**
     * @return o atributo valorCalculadoMulta
     */
    public BigDecimal getValorCalculadoMulta() {
        return valorCalculadoMulta;
    }

    /**
     * Define o atributo valorCalculadoMulta
     */
    public void setValorCalculadoMulta(BigDecimal valorCalculadoMulta) {
        this.valorCalculadoMulta = valorCalculadoMulta;
    }

    /**
     * @return o atributo valorCalculadoDesconto
     */
    public BigDecimal getValorCalculadoDesconto() {
        return valorCalculadoDesconto;
    }

    /**
     * Define o atributo valorCalculadoDesconto
     */
    public void setValorCalculadoDesconto(BigDecimal valorCalculadoDesconto) {
        this.valorCalculadoDesconto = valorCalculadoDesconto;
    }

    /**
     * @return o atributo valorTotalCobrado
     */
    public BigDecimal getValorTotalCobrado() {
        return valorTotalCobrado;
    }

    /**
     * Define o atributo valorTotalCobrado
     */
    public void setValorTotalCobrado(BigDecimal valorTotalCobrado) {
        this.valorTotalCobrado = valorTotalCobrado;
    }

    /**
     * @return o atributo dataValidadeCalculo
     */
    public DateTimeDB getDataValidadeCalculo() {
        return dataValidadeCalculo;
    }

    /**
     * Define o atributo dataValidadeCalculo
     */
    public void setDataValidadeCalculo(DateTimeDB dataValidadeCalculo) {
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

}
