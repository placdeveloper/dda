package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * ConsultaBoletoDDAAPIDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
/**
 * ConsultaBoletoDDAAPIDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ConsultaBoletoDDAAPIDto extends BancoobDto {

    private static final long serialVersionUID = -1649405449145373945L;

    private String ispbParticipanteDestinatario;

    private Character tipoPessoaBeneficiario;

    private String numCnpjCpfBeneficiario;

    private String nomeRazaoSocialBeneficiario;

    private String nomeFantasiaBeneficiario;

    /**
     * Os campos de sacador avalista estão definidos com o nome BeneficiarioFinal no documento. Foi alterado no objeto pra facilitar o entendimento.
     */
    private Character tipoPessoaSacadorAvalista;

    private String numCnpjCpfSacadorAvalista;

    private String nomeRazaoSocialSacadorAvalista;

    private String nomeFantasiaSacadorAvalista;

    private Character tipoPessoaPagador;

    private String numCnpjCpfPagador;

    private String nomeRazaoSocialPagador;

    private String nomeFantasiaPagador;

    private String numCodigoBarras;

    private String numLinhaDigitavel;

    private DateTime dataVencimentoBoleto;

    private BigDecimal valorBoleto;

    private Integer qtdDiasProtesto;

    private DateTime dataLimitePagamentoBoleto;

    private Boolean permiteAlterarValor;

    private String situacaoBoletoPagamento;

    private BigDecimal valorAbatimentoDesconto;

    private String numNossoNumero;

    private String numDocumento;

    private Long numIdentificadorBoletoCIP;

    // INFORMAÇÕES ADICIONAIS

    private Short numInstituicaoEmissora;

    private String descInstituicaoEmissora;

    private String instrucaoDesconto1;

    private String instrucaoDesconto2;

    private String instrucaoDesconto3;

    private String instrucaoValorMinMax;

    private BigDecimal valorMultaMora;

    private BigDecimal valorPagamento;

    private Boolean bloquearPagamento;

    private String mensagemBloqueioPagamento;

    private DateTime dataPagamento;

    // ****************************************
    // CAMPOS QUE NÃO ESTARÃO NO RETORNO DA SP
    // Utilizados na validação do pagamento
    // ****************************************

    private BigDecimal valorMinimo;
    private BigDecimal valorMaximo;
    private BigDecimal valorSaldoPagamento;

    private Boolean validarValorMinimoEMaximo;
    private Boolean validarValorMinimo;
    private Boolean validarValorMaximo;

    private DateTime dataVencimentoUtil;

    private Boolean bolNovoFluxo;
    private Boolean bolPagamentoParcial;

    private Boolean permiteValorDivergente;

    private Long numRefAtualCadBoleto;

    /**
     * @return o atributo ispbParticipanteDestinatario
     */
    public String getIspbParticipanteDestinatario() {
        return ispbParticipanteDestinatario;
    }

    /**
     * Define o atributo ispbParticipanteDestinatario
     */
    public void setIspbParticipanteDestinatario(String ispbParticipanteDestinatario) {
        this.ispbParticipanteDestinatario = ispbParticipanteDestinatario;
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
     * @return o atributo numCnpjCpfBeneficiario
     */
    public String getNumCnpjCpfBeneficiario() {
        return numCnpjCpfBeneficiario;
    }

    /**
     * Define o atributo numCnpjCpfBeneficiario
     */
    public void setNumCnpjCpfBeneficiario(String numCnpjCpfBeneficiario) {
        this.numCnpjCpfBeneficiario = numCnpjCpfBeneficiario;
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
     * @return o atributo nomeFantasiaBeneficiario
     */
    public String getNomeFantasiaBeneficiario() {
        return nomeFantasiaBeneficiario;
    }

    /**
     * Define o atributo nomeFantasiaBeneficiario
     */
    public void setNomeFantasiaBeneficiario(String nomeFantasiaBeneficiario) {
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
    }

    /**
     * @return o atributo tipoPessoaSacadorAvalista
     */
    public Character getTipoPessoaSacadorAvalista() {
        return tipoPessoaSacadorAvalista;
    }

    /**
     * Define o atributo tipoPessoaSacadorAvalista
     */
    public void setTipoPessoaSacadorAvalista(Character tipoPessoaSacadorAvalista) {
        this.tipoPessoaSacadorAvalista = tipoPessoaSacadorAvalista;
    }

    /**
     * @return o atributo numCnpjCpfSacadorAvalista
     */
    public String getNumCnpjCpfSacadorAvalista() {
        return numCnpjCpfSacadorAvalista;
    }

    /**
     * Define o atributo numCnpjCpfSacadorAvalista
     */
    public void setNumCnpjCpfSacadorAvalista(String numCnpjCpfSacadorAvalista) {
        this.numCnpjCpfSacadorAvalista = numCnpjCpfSacadorAvalista;
    }

    /**
     * @return o atributo nomeRazaoSocialSacadorAvalista
     */
    public String getNomeRazaoSocialSacadorAvalista() {
        return nomeRazaoSocialSacadorAvalista;
    }

    /**
     * Define o atributo nomeRazaoSocialSacadorAvalista
     */
    public void setNomeRazaoSocialSacadorAvalista(String nomeRazaoSocialSacadorAvalista) {
        this.nomeRazaoSocialSacadorAvalista = nomeRazaoSocialSacadorAvalista;
    }

    /**
     * @return o atributo nomeFantasiaSacadorAvalista
     */
    public String getNomeFantasiaSacadorAvalista() {
        return nomeFantasiaSacadorAvalista;
    }

    /**
     * Define o atributo nomeFantasiaSacadorAvalista
     */
    public void setNomeFantasiaSacadorAvalista(String nomeFantasiaSacadorAvalista) {
        this.nomeFantasiaSacadorAvalista = nomeFantasiaSacadorAvalista;
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
     * @return o atributo numCnpjCpfPagador
     */
    public String getNumCnpjCpfPagador() {
        return numCnpjCpfPagador;
    }

    /**
     * Define o atributo numCnpjCpfPagador
     */
    public void setNumCnpjCpfPagador(String numCnpjCpfPagador) {
        this.numCnpjCpfPagador = numCnpjCpfPagador;
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
     * @return o atributo nomeFantasiaPagador
     */
    public String getNomeFantasiaPagador() {
        return nomeFantasiaPagador;
    }

    /**
     * Define o atributo nomeFantasiaPagador
     */
    public void setNomeFantasiaPagador(String nomeFantasiaPagador) {
        this.nomeFantasiaPagador = nomeFantasiaPagador;
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
     * @return o atributo dataVencimentoBoleto
     */
    public DateTime getDataVencimentoBoleto() {
        return dataVencimentoBoleto;
    }

    /**
     * Define o atributo dataVencimentoBoleto
     */
    public void setDataVencimentoBoleto(DateTime dataVencimentoBoleto) {
        this.dataVencimentoBoleto = dataVencimentoBoleto;
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
     * @return o atributo qtdDiasProtesto
     */
    public Integer getQtdDiasProtesto() {
        return qtdDiasProtesto;
    }

    /**
     * Define o atributo qtdDiasProtesto
     */
    public void setQtdDiasProtesto(Integer qtdDiasProtesto) {
        this.qtdDiasProtesto = qtdDiasProtesto;
    }

    /**
     * @return o atributo dataLimitePagamentoBoleto
     */
    public DateTime getDataLimitePagamentoBoleto() {
        return dataLimitePagamentoBoleto;
    }

    /**
     * Define o atributo dataLimitePagamentoBoleto
     */
    public void setDataLimitePagamentoBoleto(DateTime dataLimitePagamentoBoleto) {
        this.dataLimitePagamentoBoleto = dataLimitePagamentoBoleto;
    }

    /**
     * @return o atributo permiteAlterarValor
     */
    public Boolean getPermiteAlterarValor() {
        return permiteAlterarValor;
    }

    /**
     * Define o atributo permiteAlterarValor
     */
    public void setPermiteAlterarValor(Boolean permiteAlterarValor) {
        this.permiteAlterarValor = permiteAlterarValor;
    }

    /**
     * @return o atributo situacaoBoletoPagamento
     */
    public String getSituacaoBoletoPagamento() {
        return situacaoBoletoPagamento;
    }

    /**
     * Define o atributo situacaoBoletoPagamento
     */
    public void setSituacaoBoletoPagamento(String situacaoBoletoPagamento) {
        this.situacaoBoletoPagamento = situacaoBoletoPagamento;
    }

    /**
     * @return o atributo valorAbatimentoDesconto
     */
    public BigDecimal getValorAbatimentoDesconto() {
        return valorAbatimentoDesconto;
    }

    /**
     * Define o atributo valorAbatimentoDesconto
     */
    public void setValorAbatimentoDesconto(BigDecimal valorAbatimentoDesconto) {
        this.valorAbatimentoDesconto = valorAbatimentoDesconto;
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
     * @return o atributo numIdentificadorBoletoCIP
     */
    public Long getNumIdentificadorBoletoCIP() {
        return numIdentificadorBoletoCIP;
    }

    /**
     * Define o atributo numIdentificadorBoletoCIP
     */
    public void setNumIdentificadorBoletoCIP(Long numIdentificadorBoletoCIP) {
        this.numIdentificadorBoletoCIP = numIdentificadorBoletoCIP;
    }

    /**
     * @return o atributo numInstituicaoEmissora
     */
    public Short getNumInstituicaoEmissora() {
        return numInstituicaoEmissora;
    }

    /**
     * Define o atributo numInstituicaoEmissora
     */
    public void setNumInstituicaoEmissora(Short numInstituicaoEmissora) {
        this.numInstituicaoEmissora = numInstituicaoEmissora;
    }

    /**
     * @return o atributo descInstituicaoEmissora
     */
    public String getDescInstituicaoEmissora() {
        return descInstituicaoEmissora;
    }

    /**
     * Define o atributo descInstituicaoEmissora
     */
    public void setDescInstituicaoEmissora(String descInstituicaoEmissora) {
        this.descInstituicaoEmissora = descInstituicaoEmissora;
    }

    /**
     * @return o atributo instrucaoDesconto1
     */
    public String getInstrucaoDesconto1() {
        return instrucaoDesconto1;
    }

    /**
     * Define o atributo instrucaoDesconto1
     */
    public void setInstrucaoDesconto1(String instrucaoDesconto1) {
        this.instrucaoDesconto1 = instrucaoDesconto1;
    }

    /**
     * @return o atributo instrucaoDesconto2
     */
    public String getInstrucaoDesconto2() {
        return instrucaoDesconto2;
    }

    /**
     * Define o atributo instrucaoDesconto2
     */
    public void setInstrucaoDesconto2(String instrucaoDesconto2) {
        this.instrucaoDesconto2 = instrucaoDesconto2;
    }

    /**
     * @return o atributo instrucaoDesconto3
     */
    public String getInstrucaoDesconto3() {
        return instrucaoDesconto3;
    }

    /**
     * Define o atributo instrucaoDesconto3
     */
    public void setInstrucaoDesconto3(String instrucaoDesconto3) {
        this.instrucaoDesconto3 = instrucaoDesconto3;
    }

    /**
     * @return o atributo instrucaoValorMinMax
     */
    public String getInstrucaoValorMinMax() {
        return instrucaoValorMinMax;
    }

    /**
     * Define o atributo instrucaoValorMinMax
     */
    public void setInstrucaoValorMinMax(String instrucaoValorMinMax) {
        this.instrucaoValorMinMax = instrucaoValorMinMax;
    }

    /**
     * @return o atributo valorMultaMora
     */
    public BigDecimal getValorMultaMora() {
        return valorMultaMora;
    }

    /**
     * Define o atributo valorMultaMora
     */
    public void setValorMultaMora(BigDecimal valorMultaMora) {
        this.valorMultaMora = valorMultaMora;
    }

    /**
     * @return o atributo valorPagamento
     */
    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    /**
     * Define o atributo valorPagamento
     */
    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    /**
     * @return o atributo bloquearPagamento
     */
    public Boolean getBloquearPagamento() {
        return bloquearPagamento;
    }

    /**
     * Define o atributo bloquearPagamento
     */
    public void setBloquearPagamento(Boolean bloquearPagamento) {
        this.bloquearPagamento = bloquearPagamento;
    }

    /**
     * @return o atributo mensagemBloqueioPagamento
     */
    public String getMensagemBloqueioPagamento() {
        return mensagemBloqueioPagamento;
    }

    /**
     * Define o atributo mensagemBloqueioPagamento
     */
    public void setMensagemBloqueioPagamento(String mensagemBloqueioPagamento) {
        this.mensagemBloqueioPagamento = mensagemBloqueioPagamento;
    }

    /**
     * @return o atributo dataPagamento
     */
    public DateTime getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Define o atributo dataPagamento
     */
    public void setDataPagamento(DateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return o atributo valorMinimo
     */
    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    /**
     * Define o atributo valorMinimo
     */
    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    /**
     * @return o atributo valorMaximo
     */
    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    /**
     * Define o atributo valorMaximo
     */
    public void setValorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    /**
     * @return o atributo valorSaldoPagamento
     */
    public BigDecimal getValorSaldoPagamento() {
        return valorSaldoPagamento;
    }

    /**
     * Define o atributo valorSaldoPagamento
     */
    public void setValorSaldoPagamento(BigDecimal valorSaldoPagamento) {
        this.valorSaldoPagamento = valorSaldoPagamento;
    }

    /**
     * @return o atributo validarValorMinimoEMaximo
     */
    public Boolean getValidarValorMinimoEMaximo() {
        return validarValorMinimoEMaximo;
    }

    /**
     * Define o atributo validarValorMinimoEMaximo
     */
    public void setValidarValorMinimoEMaximo(Boolean validarValorMinimoEMaximo) {
        this.validarValorMinimoEMaximo = validarValorMinimoEMaximo;
    }

    /**
     * @return o atributo validarValorMinimo
     */
    public Boolean getValidarValorMinimo() {
        return validarValorMinimo;
    }

    /**
     * Define o atributo validarValorMinimo
     */
    public void setValidarValorMinimo(Boolean validarValorMinimo) {
        this.validarValorMinimo = validarValorMinimo;
    }

    /**
     * @return o atributo validarValorMaximo
     */
    public Boolean getValidarValorMaximo() {
        return validarValorMaximo;
    }

    /**
     * Define o atributo validarValorMaximo
     */
    public void setValidarValorMaximo(Boolean validarValorMaximo) {
        this.validarValorMaximo = validarValorMaximo;
    }

    /**
     * @return o atributo dataVencimentoUtil
     */
    public DateTime getDataVencimentoUtil() {
        return dataVencimentoUtil;
    }

    /**
     * Define o atributo dataVencimentoUtil
     */
    public void setDataVencimentoUtil(DateTime dataVencimentoUtil) {
        this.dataVencimentoUtil = dataVencimentoUtil;
    }

    /**
     * @return o atributo bolNovoFluxo
     */
    public Boolean getBolNovoFluxo() {
        return bolNovoFluxo;
    }

    /**
     * Define o atributo bolNovoFluxo
     */
    public void setBolNovoFluxo(Boolean bolNovoFluxo) {
        this.bolNovoFluxo = bolNovoFluxo;
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
     * @return o atributo permiteValorDivergente
     */
    public Boolean getPermiteValorDivergente() {
        return permiteValorDivergente;
    }

    /**
     * Define o atributo permiteValorDivergente
     */
    public void setPermiteValorDivergente(Boolean permiteValorDivergente) {
        this.permiteValorDivergente = permiteValorDivergente;
    }

    /**
     * @return o atributo numRefAtualCadBoleto
     */
    public Long getNumRefAtualCadBoleto() {
        return numRefAtualCadBoleto;
    }

    /**
     * Define o atributo numRefAtualCadBoleto
     */
    public void setNumRefAtualCadBoleto(Long numRefAtualCadBoleto) {
        this.numRefAtualCadBoleto = numRefAtualCadBoleto;
    }

}
