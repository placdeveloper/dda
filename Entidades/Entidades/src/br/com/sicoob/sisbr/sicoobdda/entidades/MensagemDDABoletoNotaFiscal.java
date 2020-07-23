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
 * MensagemDDABoletoNotaFiscal
 * 
 * @author george.santos
 */
@Entity
@Table(name = "MensagemDDABoletoNotaFiscal", schema = "DDA")
public class MensagemDDABoletoNotaFiscal extends SicoobDDAEntidade {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDABoletoNotaFiscal", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDABoleto mensagemDDABoleto;

    @Column(nullable = false)
    private String numNotaFiscal;

    @Column(nullable = false)
    private DateTimeDB dataEmissaoNotaFiscal;

    @Column(nullable = false)
    private BigDecimal valorNotaFiscal;

    /**
     * 
     */
    public MensagemDDABoletoNotaFiscal() {
    }

    /**
     * @param id
     * @param idMensagemDDABoleto
     * @param numNotaFiscal
     * @param dataEmissaoNotaFiscal
     * @param valorNotaFiscal
     */
    public MensagemDDABoletoNotaFiscal(Long id, Long idMensagemDDABoleto, String numNotaFiscal, DateTimeDB dataEmissaoNotaFiscal, BigDecimal valorNotaFiscal) {
        super();
        this.id = id;
        this.mensagemDDABoleto = new MensagemDDABoleto(idMensagemDDABoleto);
        this.numNotaFiscal = numNotaFiscal;
        this.dataEmissaoNotaFiscal = dataEmissaoNotaFiscal;
        this.valorNotaFiscal = valorNotaFiscal;
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
