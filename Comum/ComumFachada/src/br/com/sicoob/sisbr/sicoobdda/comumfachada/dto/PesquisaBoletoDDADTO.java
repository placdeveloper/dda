package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto")
public class PesquisaBoletoDDADTO extends BancoobDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String numBanco;

    private String numCpfCnpjBeneficiario;
    private String numCpfCnpjPagador;
    private String numCpfCnpjPagadorAgregado;
    private String numCpfCnpjTerceiro;

    private String numCodigoBarra;
    private String numLinhaDigitavel;

    private DateTime dataVencimentoInicial;
    private DateTime dataVencimentoFinal;

    private DateTime dataRegistroDDAInicial;
    private DateTime dataRegistroDDAFinal;

    private String numSeuNumero;

    private String codSituacaoBoleto;

    private DateTime dataProcessamento;
    private DateTime dataHoraEntrada;
    private DateTime dataVencimento;
    private BigDecimal valorBoleto;

    private String descSituacaoBoletoCIP;
    private String descSituacaoBoletoSicoob;
    private String numCooperativa;

    private String descTipoPagador;

    /**
     * @return o atributo numBanco
     */
    public String getNumBanco() {
        return numBanco;
    }

    /**
     * Define o atributo numBanco
     */
    public void setNumBanco(String numBanco) {
        this.numBanco = numBanco;
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
     * @return o atributo numCpfCnpjPagadorAgregado
     */
    public String getNumCpfCnpjPagadorAgregado() {
        return numCpfCnpjPagadorAgregado;
    }

    /**
     * Define o atributo numCpfCnpjPagadorAgregado
     */
    public void setNumCpfCnpjPagadorAgregado(String numCpfCnpjPagadorAgregado) {
        this.numCpfCnpjPagadorAgregado = numCpfCnpjPagadorAgregado;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
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
     * @return o atributo numLinhaDigitavel
     */
    public String getNumLinhaDigitavel() {
        return numLinhaDigitavel;
    }

    /**
     * Define o atributo numLinhaDigitavel
     */
    public void setNumLinhaDigitavel(String numLinhaDigitavel) {
        this.numLinhaDigitavel = numLinhaDigitavel;
    }

    /**
     * @return o atributo dataVencimentoInicial
     */
    public DateTime getDataVencimentoInicial() {
        return dataVencimentoInicial;
    }

    /**
     * Define o atributo dataVencimentoInicial
     */
    public void setDataVencimentoInicial(DateTime dataVencimentoInicial) {
        this.dataVencimentoInicial = dataVencimentoInicial;
    }

    /**
     * @return o atributo dataVencimentoFinal
     */
    public DateTime getDataVencimentoFinal() {
        return dataVencimentoFinal;
    }

    /**
     * Define o atributo dataVencimentoFinal
     */
    public void setDataVencimentoFinal(DateTime dataVencimentoFinal) {
        this.dataVencimentoFinal = dataVencimentoFinal;
    }

    /**
     * @return o atributo dataRegistroDDAInicial
     */
    public DateTime getDataRegistroDDAInicial() {
        return dataRegistroDDAInicial;
    }

    /**
     * Define o atributo dataRegistroDDAInicial
     */
    public void setDataRegistroDDAInicial(DateTime dataRegistroDDAInicial) {
        this.dataRegistroDDAInicial = dataRegistroDDAInicial;
    }

    /**
     * @return o atributo dataRegistroDDAFinal
     */
    public DateTime getDataRegistroDDAFinal() {
        return dataRegistroDDAFinal;
    }

    /**
     * Define o atributo dataRegistroDDAFinal
     */
    public void setDataRegistroDDAFinal(DateTime dataRegistroDDAFinal) {
        this.dataRegistroDDAFinal = dataRegistroDDAFinal;
    }

    /**
     * @return o atributo numSeuNumero
     */
    public String getNumSeuNumero() {
        return numSeuNumero;
    }

    /**
     * Define o atributo numSeuNumero
     */
    public void setNumSeuNumero(String numSeuNumero) {
        this.numSeuNumero = numSeuNumero;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public String getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(String codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    /**
     * @return o atributo dataProcessamento
     */
    public DateTime getDataProcessamento() {
        return dataProcessamento;
    }

    /**
     * Define o atributo dataProcessamento
     */
    public void setDataProcessamento(DateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    /**
     * @return o atributo dataHoraEntrada
     */
    public DateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    /**
     * Define o atributo dataHoraEntrada
     */
    public void setDataHoraEntrada(DateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    /**
     * @return o atributo dataVencimento
     */
    public DateTime getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Define o atributo dataVencimento
     */
    public void setDataVencimento(DateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
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
     * @return o atributo descSituacaoBoletoCIP
     */
    public String getDescSituacaoBoletoCIP() {
        return descSituacaoBoletoCIP;
    }

    /**
     * Define o atributo descSituacaoBoletoCIP
     */
    public void setDescSituacaoBoletoCIP(String descSituacaoBoletoCIP) {
        this.descSituacaoBoletoCIP = descSituacaoBoletoCIP;
    }

    /**
     * @return o atributo descSituacaoBoletoSicoob
     */
    public String getDescSituacaoBoletoSicoob() {
        return descSituacaoBoletoSicoob;
    }

    /**
     * Define o atributo descSituacaoBoletoSicoob
     */
    public void setDescSituacaoBoletoSicoob(String descSituacaoBoletoSicoob) {
        this.descSituacaoBoletoSicoob = descSituacaoBoletoSicoob;
    }

    /**
     * @return o atributo numCooperativa
     */
    public String getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(String numCooperativa) {
        this.numCooperativa = numCooperativa;
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
}
