package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * BoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class BoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = -7233918615348979028L;

    private Long idBoletoDDA;

    private String descTipoPagador;

    private Character tipoPessoaBeneficiario;

    private String numCpfCnpjBeneficiario;

    private String nomeRazaoSocialBeneficiario;

    private Character tipoPessoaPagador;

    private String numCpfCnpjPagador;

    private String nomeRazaoSocialPagador;

    private Double valorBoleto;

    private DateTimeDB dataVencimentoBoleto;

    private Integer codTipoSituacaoBoleto;

    private String descSituacaoBoleto;

    private Long numIdentificadorBoletoCip;

    private String numCodigoBarras;

    private String numCpfCnpjPagadorEletronico;

    private Boolean bolAceite;

    private String descSituacaoAgendamento;

    private String numNossoNumero;

    private String numDocumento;

    private DateTimeDB dataPagamento;

    private Double valorPagamento;

    private Boolean bolMostrarDataValorPagamento;

    /**
     * @return o atributo idBoletoDDA
     */
    public Long getIdBoletoDDA() {
        return idBoletoDDA;
    }

    /**
     * Define o atributo idBoletoDDA
     */
    public void setIdBoletoDDA(Long idBoletoDDA) {
        this.idBoletoDDA = idBoletoDDA;
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
     * @return o atributo tipoPessoaBeneficiario
     */
    public Character getTipoPessoaBeneficiario() {
        return tipoPessoaBeneficiario;
    }

    /**
     * Define o atributo tipoPessoaBeneficiario
     */
    public void setTipoPessoaBeneficiario(Character tipoPessoaBeneficiario) {
        this.tipoPessoaBeneficiario = tipoPessoaBeneficiario;
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
     * @return o atributo nomeRazaoSocialBeneficiario
     */
    public String getNomeRazaoSocialBeneficiario() {
        return nomeRazaoSocialBeneficiario;
    }

    /**
     * Define o atributo nomeRazaoSocialBeneficiario
     */
    public void setNomeRazaoSocialBeneficiario(String nomeRazaoSocialBeneficiario) {
        this.nomeRazaoSocialBeneficiario = nomeRazaoSocialBeneficiario;
    }

    /**
     * @return o atributo tipoPessoaPagador
     */
    public Character getTipoPessoaPagador() {
        return tipoPessoaPagador;
    }

    /**
     * Define o atributo tipoPessoaPagador
     */
    public void setTipoPessoaPagador(Character tipoPessoaPagador) {
        this.tipoPessoaPagador = tipoPessoaPagador;
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
     * @return o atributo nomeRazaoSocialPagador
     */
    public String getNomeRazaoSocialPagador() {
        return nomeRazaoSocialPagador;
    }

    /**
     * Define o atributo nomeRazaoSocialPagador
     */
    public void setNomeRazaoSocialPagador(String nomeRazaoSocialPagador) {
        this.nomeRazaoSocialPagador = nomeRazaoSocialPagador;
    }

    /**
     * @return o atributo valorBoleto
     */
    public Double getValorBoleto() {
        return valorBoleto;
    }

    /**
     * Define o atributo valorBoleto
     */
    public void setValorBoleto(Double valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    /**
     * @return o atributo dataVencimentoBoleto
     */
    public DateTimeDB getDataVencimentoBoleto() {
        return dataVencimentoBoleto;
    }

    /**
     * Define o atributo dataVencimentoBoleto
     */
    public void setDataVencimentoBoleto(DateTimeDB dataVencimentoBoleto) {
        this.dataVencimentoBoleto = dataVencimentoBoleto;
    }

    /**
     * @return o atributo codTipoSituacaoBoleto
     */
    public Integer getCodTipoSituacaoBoleto() {
        return codTipoSituacaoBoleto;
    }

    /**
     * Define o atributo codTipoSituacaoBoleto
     */
    public void setCodTipoSituacaoBoleto(Integer codTipoSituacaoBoleto) {
        this.codTipoSituacaoBoleto = codTipoSituacaoBoleto;
    }

    /**
     * @return o atributo descSituacaoBoleto
     */
    public String getDescSituacaoBoleto() {
        return descSituacaoBoleto;
    }

    /**
     * Define o atributo descSituacaoBoleto
     */
    public void setDescSituacaoBoleto(String descSituacaoBoleto) {
        this.descSituacaoBoleto = descSituacaoBoleto;
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
     * @return o atributo numCodigoBarras
     */
    public String getNumCodigoBarras() {
        return numCodigoBarras;
    }

    /**
     * Define o atributo numCodigoBarras
     */
    public void setNumCodigoBarras(String numCodigoBarras) {
        this.numCodigoBarras = numCodigoBarras;
    }

    /**
     * @return o atributo numCpfCnpjPagadorEletronico
     */
    public String getNumCpfCnpjPagadorEletronico() {
        return numCpfCnpjPagadorEletronico;
    }

    /**
     * Define o atributo numCpfCnpjPagadorEletronico
     */
    public void setNumCpfCnpjPagadorEletronico(String numCpfCnpjPagadorEletronico) {
        this.numCpfCnpjPagadorEletronico = numCpfCnpjPagadorEletronico;
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
     * @return o atributo descSituacaoAgendamento
     */
    public String getDescSituacaoAgendamento() {
        return descSituacaoAgendamento;
    }

    /**
     * Define o atributo descSituacaoAgendamento
     */
    public void setDescSituacaoAgendamento(String descSituacaoAgendamento) {
        this.descSituacaoAgendamento = descSituacaoAgendamento;
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
     * @return o atributo dataPagamento
     */
    public DateTimeDB getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Define o atributo dataPagamento
     */
    public void setDataPagamento(DateTimeDB dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return o atributo valorPagamento
     */
    public Double getValorPagamento() {
        return valorPagamento;
    }

    /**
     * Define o atributo valorPagamento
     */
    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    /**
     * @return o atributo bolMostrarDataValorPagamento
     */
    public Boolean getBolMostrarDataValorPagamento() {
        return bolMostrarDataValorPagamento;
    }

    /**
     * Define o atributo bolMostrarDataValorPagamento
     */
    public void setBolMostrarDataValorPagamento(Boolean bolMostrarDataValorPagamento) {
        this.bolMostrarDataValorPagamento = bolMostrarDataValorPagamento;
    }

}
