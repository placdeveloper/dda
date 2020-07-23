/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         ViewMensagemDDAPendente.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * ViewBoletoPagadorEletronico
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "VIW_BOLETOPAGELETRONICO", schema = "DDA")
public class ViewBoletoPagadorEletronico extends SicoobDDAEntidade {
    private static final long serialVersionUID = -6619242856273333002L;

    @Id
    @Column(name = "idBoletoDDA")
    private Long id;

    private String descTipoPagador;

    private String codTipoPessoaBeneficiario;

    private String numCpfCnpjBeneficiario;

    private String nomeBeneficiario;

    private String codTIpoPessoaPagador;

    private String numCpfCnpjPagador;

    private String nomePagador;

    private String numNossoNumero;

    private String numDocumento;

    private BigDecimal valorBoleto;

    private DateTimeDB dataVencimento;

    private Boolean bolAceite;

    private Integer idTipoSituacaoBoleto;

    private String descTipoSituacaoBoleto;

    private Long numIdentificadorBoletoCip;

    private String numCodigoBarra;

    private String numCpfCnpjPagadorEletronico;

    private Boolean bolPagamentoParcial;

    private String codPartDestinatario;

    private DateTimeDB dataHoraInclusao;

    private DateTimeDB dataHoraUltimaAtualizacao;

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
     * @return o atributo descTipoPagador
     */
    public String getDescTipoPagador() {
        return descTipoPagador;
    }

    /**
     * Define o atributo descTipoPagador
     */
    public void setDescTipoPagador(String descTipoPagador) {
        this.descTipoPagador = descTipoPagador;
    }

    /**
     * @return o atributo codTipoPessoaBeneficiario
     */
    public String getCodTipoPessoaBeneficiario() {
        return codTipoPessoaBeneficiario;
    }

    /**
     * Define o atributo codTipoPessoaBeneficiario
     */
    public void setCodTipoPessoaBeneficiario(String codTipoPessoaBeneficiario) {
        this.codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
    }

    /**
     * @return o atributo numCpfCnpjBeneficiario
     */
    public String getNumCpfCnpjBeneficiario() {
        return numCpfCnpjBeneficiario;
    }

    /**
     * Define o atributo numCpfCnpjBeneficiario
     */
    public void setNumCpfCnpjBeneficiario(String numCpfCnpjBeneficiario) {
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
    }

    /**
     * @return o atributo nomeBeneficiario
     */
    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    /**
     * Define o atributo nomeBeneficiario
     */
    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    /**
     * @return o atributo codTIpoPessoaPagador
     */
    public String getCodTIpoPessoaPagador() {
        return codTIpoPessoaPagador;
    }

    /**
     * Define o atributo codTIpoPessoaPagador
     */
    public void setCodTIpoPessoaPagador(String codTIpoPessoaPagador) {
        this.codTIpoPessoaPagador = codTIpoPessoaPagador;
    }

    /**
     * @return o atributo numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * Define o atributo numCpfCnpjPagador
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return o atributo nomePagador
     */
    public String getNomePagador() {
        return nomePagador;
    }

    /**
     * Define o atributo nomePagador
     */
    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    /**
     * @return o atributo numNossoNumero
     */
    public String getNumNossoNumero() {
        return numNossoNumero;
    }

    /**
     * Define o atributo numNossoNumero
     */
    public void setNumNossoNumero(String numNossoNumero) {
        this.numNossoNumero = numNossoNumero;
    }

    /**
     * @return o atributo numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * Define o atributo numDocumento
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return o atributo valorBoleto
     */
    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    /**
     * Define o atributo valorBoleto
     */
    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    /**
     * @return o atributo dataVencimento
     */
    public DateTimeDB getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Define o atributo dataVencimento
     */
    public void setDataVencimento(DateTimeDB dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return o atributo bolAceite
     */
    public Boolean getBolAceite() {
        return bolAceite;
    }

    /**
     * Define o atributo bolAceite
     */
    public void setBolAceite(Boolean bolAceite) {
        this.bolAceite = bolAceite;
    }

    /**
     * @return o atributo idTipoSituacaoBoleto
     */
    public Integer getIdTipoSituacaoBoleto() {
        return idTipoSituacaoBoleto;
    }

    /**
     * Define o atributo idTipoSituacaoBoleto
     */
    public void setIdTipoSituacaoBoleto(Integer idTipoSituacaoBoleto) {
        this.idTipoSituacaoBoleto = idTipoSituacaoBoleto;
    }

    /**
     * @return o atributo descTipoSituacaoBoleto
     */
    public String getDescTipoSituacaoBoleto() {
        return descTipoSituacaoBoleto;
    }

    /**
     * Define o atributo descTipoSituacaoBoleto
     */
    public void setDescTipoSituacaoBoleto(String descTipoSituacaoBoleto) {
        this.descTipoSituacaoBoleto = descTipoSituacaoBoleto;
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
     * @return o atributo numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * Define o atributo numCodigoBarra
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return o atributo numCpfCnpjPAgadorEletronico
     */
    public String getNumCpfCnpjPagadorEletronico() {
        return numCpfCnpjPagadorEletronico;
    }

    /**
     * Define o atributo numCpfCnpjPAgadorEletronico
     */
    public void setNumCpfCnpjPagadorEletronico(String numCpfCnpjPagadorEletronico) {
        this.numCpfCnpjPagadorEletronico = numCpfCnpjPagadorEletronico;
    }

    /**
     * @return o atributo bolPagamentoParcial
     */
    public Boolean getBolPagamentoParcial() {
        return bolPagamentoParcial;
    }

    /**
     * Define o atributo bolPagamentoParcial
     */
    public void setBolPagamentoParcial(Boolean bolPagamentoParcial) {
        this.bolPagamentoParcial = bolPagamentoParcial;
    }

    /**
     * @return o atributo codPartDestinatario
     */
    public String getCodPartDestinatario() {
        return codPartDestinatario;
    }

    /**
     * Define o atributo codPartDestinatario
     */
    public void setCodPartDestinatario(String codPartDestinatario) {
        this.codPartDestinatario = codPartDestinatario;
    }

    /**
     * @return o atributo dataHoraInclusao
     */
    public DateTimeDB getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTimeDB dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    /**
     * @return o atributo dataHoraUltimaAtualizacao
     */
    public DateTimeDB getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * Define o atributo dataHoraUltimaAtualizacao
     */
    public void setDataHoraUltimaAtualizacao(DateTimeDB dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }
}
