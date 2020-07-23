package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ViewBoletoDDASituacao é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "VIW_BOLETODDASITUACAO", schema = "DDA")
public class ViewBoletoDDASituacao extends SicoobDDAEntidade {

    private static final long serialVersionUID = 694592555202745994L;

    @Id
    @Column(name = "IDBOLETODDA", unique = true, nullable = false)
    private Long id;

    private Long numIdentificadorBoletoCip;

    private Integer qtdPagamentoParcial;

    private Integer qtdPagamentoRegistrado;

    private BigDecimal valorBaixaOper;

    private BigDecimal valorBaixaEfet;

    private String codSituacaoBoletoPagamento;

    private Integer codSituacaoBoleto;

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
     * @return o atributo numIdentificadorBoletoCip
     */
    public Long getNumIdentificadorBoletoCip() {
        return numIdentificadorBoletoCip;
    }

    /**
     * Define o atributo numIdentificadorBoletoCip
     */
    public void setNumIdentificadorBoletoCip(Long numIdentificadorBoletoCip) {
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

    /**
     * @return o atributo qtdPagamentoParcial
     */
    public Integer getQtdPagamentoParcial() {
        return qtdPagamentoParcial;
    }

    /**
     * Define o atributo qtdPagamentoParcial
     */
    public void setQtdPagamentoParcial(Integer qtdPagamentoParcial) {
        this.qtdPagamentoParcial = qtdPagamentoParcial;
    }

    /**
     * @return o atributo qtdPagamentoRegistrado
     */
    public Integer getQtdPagamentoRegistrado() {
        return qtdPagamentoRegistrado;
    }

    /**
     * Define o atributo qtdPagamentoRegistrado
     */
    public void setQtdPagamentoRegistrado(Integer qtdPagamentoRegistrado) {
        this.qtdPagamentoRegistrado = qtdPagamentoRegistrado;
    }

    /**
     * @return o atributo valorBaixaOper
     */
    public BigDecimal getValorBaixaOper() {
        return valorBaixaOper;
    }

    /**
     * Define o atributo valorBaixaOper
     */
    public void setValorBaixaOper(BigDecimal valorBaixaOper) {
        this.valorBaixaOper = valorBaixaOper;
    }

    /**
     * @return o atributo valorBaixaEfet
     */
    public BigDecimal getValorBaixaEfet() {
        return valorBaixaEfet;
    }

    /**
     * Define o atributo valorBaixaEfet
     */
    public void setValorBaixaEfet(BigDecimal valorBaixaEfet) {
        this.valorBaixaEfet = valorBaixaEfet;
    }

    /**
     * @return o atributo codSituacaoBoletoPagamento
     */
    public String getCodSituacaoBoletoPagamento() {
        return codSituacaoBoletoPagamento;
    }

    /**
     * Define o atributo codSituacaoBoletoPagamento
     */
    public void setCodSituacaoBoletoPagamento(String codSituacaoBoletoPagamento) {
        this.codSituacaoBoletoPagamento = codSituacaoBoletoPagamento;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public Integer getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(Integer codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

}
