/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABaixaOperacional.java
 * Data Criação:    Nov 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDABaixaOperacional
 * 
 * As FKs não estão mapeadas pois estas tabelas precisam ser rápida para mecanismo de envio/recebimento de mensagens.
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "MensagemDDABaixaOperacional", schema = "DDA")
public class MensagemDDABaixaOperacional extends SicoobDDAEntidade implements IMensagemDDA {
    private static final long serialVersionUID = 5968129263798380423L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    private Long numRefAtualBaixaOper;

    private Long numSeqAtualBaixaOper;

    @Column(nullable = false)
    private String numCodigoBarra;

    private Long numRefAtualCadBoleto;

    // DDA.TipoBaixaOperacional
    @Column(nullable = false)
    private Short codTipoBaixaOperacional;

    // DDA.TipoPessoaDDA
    private String codTipoPessoaPortador;

    private String numCpfCnpjPortador;

    @Column(nullable = false)
    private BigDecimal valorBaixa;

    // DDA.CanalPagamentoDDA
    @Column(nullable = false)
    private Short codCanalPagamento;

    // DDA.MeioPagamento
    @Column(nullable = false)
    private Short codMeioPagamento;

    @Column(nullable = false)
    private Boolean bolOperacaoContingencia;

    private String numCodBarrasCampoLivre;

    @Transient
    private Long numIdentificadorBaixaOper;

    @Transient
    private Long numIdentificadorBoleto;

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
     * @return the mensagemDDA
     */
    public MensagemDDA getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDA mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return the numRefAtualBaixaOper
     */
    public Long getNumRefAtualBaixaOper() {
        return numRefAtualBaixaOper;
    }

    /**
     * @param numRefAtualBaixaOper the numRefAtualBaixaOper to set
     */
    public void setNumRefAtualBaixaOper(Long numRefAtualBaixaOper) {
        this.numRefAtualBaixaOper = numRefAtualBaixaOper;
    }

    /**
     * @return the numSeqAtualBaixaOper
     */
    public Long getNumSeqAtualBaixaOper() {
        return numSeqAtualBaixaOper;
    }

    /**
     * @param numSeqAtualBaixaOper the numSeqAtualBaixaOper to set
     */
    public void setNumSeqAtualBaixaOper(Long numSeqAtualBaixaOper) {
        this.numSeqAtualBaixaOper = numSeqAtualBaixaOper;
    }

    /**
     * @return the numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * @param numCodigoBarra the numCodigoBarra to set
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return the numRefAtualCadBoleto
     */
    public Long getNumRefAtualCadBoleto() {
        return numRefAtualCadBoleto;
    }

    /**
     * @param numRefAtualCadBoleto the numRefAtualCadBoleto to set
     */
    public void setNumRefAtualCadBoleto(Long numRefAtualCadBoleto) {
        this.numRefAtualCadBoleto = numRefAtualCadBoleto;
    }

    /**
     * @return o atributo codTipoBaixaOperacional
     */
    public Short getCodTipoBaixaOperacional() {
        return codTipoBaixaOperacional;
    }

    /**
     * Define o atributo codTipoBaixaOperacional
     */
    public void setCodTipoBaixaOperacional(Short codTipoBaixaOperacional) {
        this.codTipoBaixaOperacional = codTipoBaixaOperacional;
    }

    /**
     * @return the codTipoPessoaPortador
     */
    public String getCodTipoPessoaPortador() {
        return codTipoPessoaPortador;
    }

    /**
     * @param codTipoPessoaPortador the codTipoPessoaPortador to set
     */
    public void setCodTipoPessoaPortador(String codTipoPessoaPortador) {
        this.codTipoPessoaPortador = codTipoPessoaPortador;
    }

    /**
     * @return the numCpfCnpjPortador
     */
    public String getNumCpfCnpjPortador() {
        return numCpfCnpjPortador;
    }

    /**
     * @param numCpfCnpjPortador the numCpfCnpjPortador to set
     */
    public void setNumCpfCnpjPortador(String numCpfCnpjPortador) {
        this.numCpfCnpjPortador = numCpfCnpjPortador;
    }

    /**
     * @return the valorBaixa
     */
    public BigDecimal getValorBaixa() {
        return valorBaixa;
    }

    /**
     * @param valorBaixa the valorBaixa to set
     */
    public void setValorBaixa(BigDecimal valorBaixa) {
        this.valorBaixa = valorBaixa;
    }

    /**
     * @return the codCanalPagamento
     */
    public Short getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * @param codCanalPagamento the codCanalPagamento to set
     */
    public void setCodCanalPagamento(Short codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
    }

    /**
     * @return the codMeioPagamento
     */
    public Short getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * @param codMeioPagamento the codMeioPagamento to set
     */
    public void setCodMeioPagamento(Short codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
    }

    /**
     * @return the bolOperacaoContingencia
     */
    public Boolean isBolOperacaoContingencia() {
        return bolOperacaoContingencia;
    }

    /**
     * @return the bolOperacaoContingencia
     */
    public String getBolOperacaoContingencia() {
        return bolOperacaoContingencia ? "S" : "N";
    }

    /**
     * @return numCodBarrasCampoLivre
     */
    public String getNumCodBarrasCampoLivre() {
        return numCodBarrasCampoLivre;
    }

    /**
     * @param numCodBarrasCampoLivre
     */
    public void setNumCodBarrasCampoLivre(String numCodBarrasCampoLivre) {
        this.numCodBarrasCampoLivre = numCodBarrasCampoLivre;
    }

    /**
     * @param bolOperacaoContingencia the bolOperacaoContingencia to set
     */
    public void setBolOperacaoContingencia(Boolean bolOperacaoContingencia) {
        this.bolOperacaoContingencia = bolOperacaoContingencia;
    }

    /**
     * @return o atributo numIdentificadorBaixaOper
     */
    public Long getNumIdentificadorBaixaOper() {
        return numIdentificadorBaixaOper;
    }

    /**
     * Define o atributo numIdentificadorBaixaOper
     */
    public void setNumIdentificadorBaixaOper(Long numIdentificadorBaixaOper) {
        this.numIdentificadorBaixaOper = numIdentificadorBaixaOper;
    }

    /**
     * @return o atributo numIdentificadorBoleto
     */
    public Long getNumIdentificadorBoleto() {
        return numIdentificadorBoleto;
    }

    /**
     * Define o atributo numIdentificadorBoleto
     */
    public void setNumIdentificadorBoleto(Long numIdentificadorBoleto) {
        this.numIdentificadorBoleto = numIdentificadorBoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return null;
    }

}
