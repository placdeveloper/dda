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
 * MensagemDDABaixaEfetiva
 * 
 * As FKs não estão mapeadas pois estas tabelas precisam ser rápida para mecanismo de envio/recebimento de mensagens.
 * 
 * @author Rafael.Silva
 */
/**
 * MensagemDDABaixaEfetiva é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "MensagemDDABaixaEfetiva", schema = "DDA")
public class MensagemDDABaixaEfetiva extends SicoobDDAEntidade implements IMensagemDDA {
    private static final long serialVersionUID = 5968129263798380423L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @Transient
    private Long idOperacaoLeg;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    private Long numRefAtualBaixaEfetiva;

    private Long numSeqAtualBaixaEfetiva;

    private Long numIdentificadorBaixaOper;

    @Column(nullable = false)
    private String numCodigoBarra;

    private Long numRefAtualCadBoleto;

    // DDA.TipoBaixaEfetiva
    @Column(nullable = false)
    private Integer codTipoBaixaEfetiva;

    @Column(nullable = false)
    private BigDecimal valorBaixa;

    // DDA.CanalPagamentoDDA
    private Integer codCanalPagamento;

    // DDA.MeioPagamento
    private Integer codMeioPagamento;

    private String numCodBarrasCampoLivre;

    @Transient
    private Long numIdentificadorBoletoCip;

    @Transient
    private String codISPBPartRecbdrBaixaOper;

    @Transient
    private String codPartRecbdrBaixaOper;

    /**
     * 
     */
    public MensagemDDABaixaEfetiva() {
        super();
    }

    /**
     * @param id
     */
    public MensagemDDABaixaEfetiva(Long id) {
        super();
        this.id = id;
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
     * @return the numRefAtualBaixaEfetiva
     */
    public Long getNumRefAtualBaixaEfetiva() {
        return numRefAtualBaixaEfetiva;
    }

    /**
     * @param numRefAtualBaixaEfetiva the numRefAtualBaixaEfetiva to set
     */
    public void setNumRefAtualBaixaEfetiva(Long numRefAtualBaixaEfetiva) {
        this.numRefAtualBaixaEfetiva = numRefAtualBaixaEfetiva;
    }

    /**
     * @return the numSeqAtualBaixaEfetiva
     */
    public Long getNumSeqAtualBaixaEfetiva() {
        return numSeqAtualBaixaEfetiva;
    }

    /**
     * @param numSeqAtualBaixaEfetiva the numSeqAtualBaixaEfetiva to set
     */
    public void setNumSeqAtualBaixaEfetiva(Long numSeqAtualBaixaEfetiva) {
        this.numSeqAtualBaixaEfetiva = numSeqAtualBaixaEfetiva;
    }

    /**
     * @return the numIdentificadorBaixaOper
     */
    public Long getNumIdentificadorBaixaOper() {
        return numIdentificadorBaixaOper;
    }

    /**
     * @param numIdentificadorBaixaOper the numIdentificadorBaixaOper to set
     */
    public void setNumIdentificadorBaixaOper(Long numIdentificadorBaixaOper) {
        this.numIdentificadorBaixaOper = numIdentificadorBaixaOper;
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
     * @return the codTipoBaixaEfetiva
     */
    public Integer getCodTipoBaixaEfetiva() {
        return codTipoBaixaEfetiva;
    }

    /**
     * @param codTipoBaixaEfetiva the codTipoBaixaEfetiva to set
     */
    public void setCodTipoBaixaEfetiva(Integer codTipoBaixaEfetiva) {
        this.codTipoBaixaEfetiva = codTipoBaixaEfetiva;
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
    public Integer getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * @param codCanalPagamento the codCanalPagamento to set
     */
    public void setCodCanalPagamento(Integer codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
    }

    /**
     * @return the codMeioPagamento
     */
    public Integer getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * @param codMeioPagamento the codMeioPagamento to set
     */
    public void setCodMeioPagamento(Integer codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
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
     * @return the idOperacaoLeg
     */
    public Long getIdOperacaoLeg() {
        return idOperacaoLeg;
    }

    /**
     * @param idOperacaoLeg the idOperacaoLeg to set
     */
    public void setIdOperacaoLeg(Long idOperacaoLeg) {
        this.idOperacaoLeg = idOperacaoLeg;
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
     * @return codISPBPartRecbdrBaixaOper
     */
    public String getCodISPBPartRecbdrBaixaOper() {
        return codISPBPartRecbdrBaixaOper;
    }

    /**
     * @param codISPBPartRecbdrBaixaOper
     */
    public void setCodISPBPartRecbdrBaixaOper(String codISPBPartRecbdrBaixaOper) {
        this.codISPBPartRecbdrBaixaOper = codISPBPartRecbdrBaixaOper;
    }

    /**
     * @return codPartRecbdrBaixaOper
     */
    public String getCodPartRecbdrBaixaOper() {
        return codPartRecbdrBaixaOper;
    }

    /**
     * @param codPartRecbdrBaixaOper
     */
    public void setCodPartRecbdrBaixaOper(String codPartRecbdrBaixaOper) {
        this.codPartRecbdrBaixaOper = codPartRecbdrBaixaOper;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return getIdOperacaoLeg();
    }
}
