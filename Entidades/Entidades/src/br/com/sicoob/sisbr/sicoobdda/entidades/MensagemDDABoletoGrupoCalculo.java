/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABoletoDesconto.java
 * Data Cria��o:    Jun 1, 2016
 */
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
 * MensagemDDABoletoDesconto
 * 
 * @author george.santos
 */
@Entity
@Table(name = "MensagemDDABoletoGrupoCalculo", schema = "DDA")
public class MensagemDDABoletoGrupoCalculo extends SicoobDDAEntidade {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDABoletoGrupoCalculo", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDABoleto mensagemDDABoleto;

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

    public MensagemDDABoletoGrupoCalculo() {
    }

    public MensagemDDABoletoGrupoCalculo(Long id, Long idMensagemDDA, BigDecimal valorCalculadoJuros, BigDecimal valorCalculadoMulta, BigDecimal valorCalculadoDesconto,
            BigDecimal valorTotalCobrado, DateTimeDB dataValidadeCalculo) {
        super();
        this.id = id;
        this.mensagemDDABoleto = new MensagemDDABoleto(idMensagemDDA);
        this.valorCalculadoJuros = valorCalculadoJuros;
        this.valorCalculadoMulta = valorCalculadoMulta;
        this.valorCalculadoDesconto = valorCalculadoDesconto;
        this.valorTotalCobrado = valorTotalCobrado;
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo mensagemDDABoleto
     */
    public MensagemDDABoleto getMensagemDDABoleto() {
        return mensagemDDABoleto;
    }

    /**
     * Define o atributo mensagemDDABoleto
     */
    public void setMensagemDDABoleto(MensagemDDABoleto mensagemDDABoleto) {
        this.mensagemDDABoleto = mensagemDDABoleto;
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
