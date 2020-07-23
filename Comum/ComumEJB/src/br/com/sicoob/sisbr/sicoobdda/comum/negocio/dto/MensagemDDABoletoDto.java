package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDABoletoDto é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoDTO")
public class MensagemDDABoletoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idMensagem;
    private String tipoMensagem;
    private DateTime dataMovimento;
    private String numCodigoDeBarras;
    private DateTime dataHoraMensagem;
    private DateTime dataHoraProtocolo;
    private String numOperacao;

    // Dados Beneficiario
    private String codTipoPessoaBeneficiario;
    private String numCnpjCpfBeneficiario;
    private String nomeBeneficiario;
    private String nomeFantasiaBeneficiario;
    private String descLogradouroBeneficiario;
    private String descCidadeBeneficiario;
    private String ufBeneficiario;
    private String numCepBeneficiario;

    // Dados Beneficiario Final
    private String codTipoPessoaBeneficiarioFinal;
    private String numCnpjCpfBeneficiarioFinal;
    private String nomeBeneficiarioFinal;
    private String nomeFantasiaBeneficiarioFinal;

    // Dados Pagador
    private String codTipoPessoaPagador;
    private String numCnpjCpfPagador;
    private String nomePagador;
    private String nomeFantasiaPagador;
    private String descLogradouroPagador;
    private String desCidadePagador;
    private String ufPagador;
    private String numCepPagador;

    // Dados Avalista
    private String codTipoPessoaAvalista;
    private String numCnpjCpfAvalista;
    private String nomeAvalista;

    // Dados Boleto
    private Integer idCarteira;
    private String codMoeda;
    private Integer idOrgaoMoeda;
    private String numNossoNumero;
    private String numLinhaDigitavel;
    private DateTime dataVencimento;
    private BigDecimal valorDoBoleto;
    private DateTime dataEmissao;
    private String numDocumento;
    private DateTime dataLimitePgto;
    private Integer diasDeProtesto;
    private BigDecimal valorAbatimento;
    private String indValorMinimo;
    private String indValorMaximo;
    private BigDecimal valorMaximo;
    private BigDecimal valorMinimo;
    private Integer numeroParcela;
    private Integer qtdTotalParcelas;
    private String bolTituloNegociado;
    private String bolBloqueioPagamento;
    private String bolPagamentoParcial;
    private Integer qtdPagamentoParcial;
    private String tipoModeloDeCalculo;
    private String codAutorizacaoValorDivergente;
    private Integer idEspecieDocumento;
    private Integer codTipoPagamento;
    private Long numRefAtualCadBoleto;
    private Long numSeqAtualCadBoleto;

    // Dados Desconto
    private List<MensagemDDABoletoDescontoDto> listDadosDesconto;

    // Dados de Juros de Mora
    private Integer codTipoJuros;
    private DateTime dataJuros;
    private BigDecimal valorJuros;

    // Dados de Multa
    private Integer codTipoMulta;
    private DateTime dataMulta;
    private BigDecimal valorMulta;

    // Dados Grupo de Calculo
    private List<MensagemDDABoletoGrupoCalculoDto> listDadosGrupoCalculo;

    // Qtd Pesquisa
    private Integer qtdPesquisa;

    // CONSTRUCTOR ================================================
    /**
     * 
     */
    public MensagemDDABoletoDto() {
        super();
    }

    /**
     * @param idMensagem
     * @param tipoMensagem
     * @param dataMovimento
     * @param numCodigoDeBarras
     */
    public MensagemDDABoletoDto(Long idMensagem, String tipoMensagem, DateTime dataMovimento, String numCodigoDeBarras) {
        this.idMensagem = idMensagem;
        this.tipoMensagem = tipoMensagem;
        this.dataMovimento = dataMovimento;
        this.numCodigoDeBarras = numCodigoDeBarras;
    }

    /**
     * @return o atributo idMensagem
     */
    public Long getIdMensagem() {
        return idMensagem;
    }

    /**
     * Define o atributo idMensagem
     */
    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    /**
     * @return o atributo tipoMensagem
     */
    public String getTipoMensagem() {
        return tipoMensagem;
    }

    /**
     * Define o atributo tipoMensagem
     */
    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    /**
     * @return o atributo dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo numCodigoDeBarras
     */
    public String getNumCodigoDeBarras() {
        return numCodigoDeBarras;
    }

    /**
     * Define o atributo numCodigoDeBarras
     */
    public void setNumCodigoDeBarras(String numCodigoDeBarras) {
        this.numCodigoDeBarras = numCodigoDeBarras;
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
     * @return o atributo descLogradouroBeneficiario
     */
    public String getDescLogradouroBeneficiario() {
        return descLogradouroBeneficiario;
    }

    /**
     * Define o atributo descLogradouroBeneficiario
     */
    public void setDescLogradouroBeneficiario(String descLogradouroBeneficiario) {
        this.descLogradouroBeneficiario = descLogradouroBeneficiario;
    }

    /**
     * @return o atributo descCidadeBeneficiario
     */
    public String getDescCidadeBeneficiario() {
        return descCidadeBeneficiario;
    }

    /**
     * Define o atributo descCidadeBeneficiario
     */
    public void setDescCidadeBeneficiario(String descCidadeBeneficiario) {
        this.descCidadeBeneficiario = descCidadeBeneficiario;
    }

    /**
     * @return o atributo ufBeneficiario
     */
    public String getUfBeneficiario() {
        return ufBeneficiario;
    }

    /**
     * Define o atributo ufBeneficiario
     */
    public void setUfBeneficiario(String ufBeneficiario) {
        this.ufBeneficiario = ufBeneficiario;
    }

    /**
     * @return o atributo numCepBeneficiario
     */
    public String getNumCepBeneficiario() {
        return numCepBeneficiario;
    }

    /**
     * Define o atributo numCepBeneficiario
     */
    public void setNumCepBeneficiario(String numCepBeneficiario) {
        this.numCepBeneficiario = numCepBeneficiario;
    }

    /**
     * @return o atributo codTipoPessoaBeneficiarioFinal
     */
    public String getCodTipoPessoaBeneficiarioFinal() {
        return codTipoPessoaBeneficiarioFinal;
    }

    /**
     * Define o atributo codTipoPessoaBeneficiarioFinal
     */
    public void setCodTipoPessoaBeneficiarioFinal(String codTipoPessoaBeneficiarioFinal) {
        this.codTipoPessoaBeneficiarioFinal = codTipoPessoaBeneficiarioFinal;
    }

    /**
     * @return o atributo numCnpjCpfBeneficiarioFinal
     */
    public String getNumCnpjCpfBeneficiarioFinal() {
        return numCnpjCpfBeneficiarioFinal;
    }

    /**
     * Define o atributo numCnpjCpfBeneficiarioFinal
     */
    public void setNumCnpjCpfBeneficiarioFinal(String numCnpjCpfBeneficiarioFinal) {
        this.numCnpjCpfBeneficiarioFinal = numCnpjCpfBeneficiarioFinal;
    }

    /**
     * @return o atributo nomeBeneficiarioFinal
     */
    public String getNomeBeneficiarioFinal() {
        return nomeBeneficiarioFinal;
    }

    /**
     * Define o atributo nomeBeneficiarioFinal
     */
    public void setNomeBeneficiarioFinal(String nomeBeneficiarioFinal) {
        this.nomeBeneficiarioFinal = nomeBeneficiarioFinal;
    }

    /**
     * @return o atributo nomeFantasiaBeneficiarioFinal
     */
    public String getNomeFantasiaBeneficiarioFinal() {
        return nomeFantasiaBeneficiarioFinal;
    }

    /**
     * Define o atributo nomeFantasiaBeneficiarioFinal
     */
    public void setNomeFantasiaBeneficiarioFinal(String nomeFantasiaBeneficiarioFinal) {
        this.nomeFantasiaBeneficiarioFinal = nomeFantasiaBeneficiarioFinal;
    }

    /**
     * @return o atributo codTipoPessoaPagador
     */
    public String getCodTipoPessoaPagador() {
        return codTipoPessoaPagador;
    }

    /**
     * Define o atributo codTipoPessoaPagador
     */
    public void setCodTipoPessoaPagador(String codTipoPessoaPagador) {
        this.codTipoPessoaPagador = codTipoPessoaPagador;
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
     * @return o atributo descLogradouroPagador
     */
    public String getDescLogradouroPagador() {
        return descLogradouroPagador;
    }

    /**
     * Define o atributo descLogradouroPagador
     */
    public void setDescLogradouroPagador(String descLogradouroPagador) {
        this.descLogradouroPagador = descLogradouroPagador;
    }

    /**
     * @return o atributo desCidadePagador
     */
    public String getDesCidadePagador() {
        return desCidadePagador;
    }

    /**
     * Define o atributo desCidadePagador
     */
    public void setDesCidadePagador(String desCidadePagador) {
        this.desCidadePagador = desCidadePagador;
    }

    /**
     * @return o atributo ufPagador
     */
    public String getUfPagador() {
        return ufPagador;
    }

    /**
     * Define o atributo ufPagador
     */
    public void setUfPagador(String ufPagador) {
        this.ufPagador = ufPagador;
    }

    /**
     * @return o atributo numCepPagador
     */
    public String getNumCepPagador() {
        return numCepPagador;
    }

    /**
     * Define o atributo numCepPagador
     */
    public void setNumCepPagador(String numCepPagador) {
        this.numCepPagador = numCepPagador;
    }

    /**
     * @return o atributo codTipoPessoaAvalista
     */
    public String getCodTipoPessoaAvalista() {
        return codTipoPessoaAvalista;
    }

    /**
     * Define o atributo codTipoPessoaAvalista
     */
    public void setCodTipoPessoaAvalista(String codTipoPessoaAvalista) {
        this.codTipoPessoaAvalista = codTipoPessoaAvalista;
    }

    /**
     * @return o atributo numCnpjCpfAvalista
     */
    public String getNumCnpjCpfAvalista() {
        return numCnpjCpfAvalista;
    }

    /**
     * Define o atributo numCnpjCpfAvalista
     */
    public void setNumCnpjCpfAvalista(String numCnpjCpfAvalista) {
        this.numCnpjCpfAvalista = numCnpjCpfAvalista;
    }

    /**
     * @return o atributo nomeAvalista
     */
    public String getNomeAvalista() {
        return nomeAvalista;
    }

    /**
     * Define o atributo nomeAvalista
     */
    public void setNomeAvalista(String nomeAvalista) {
        this.nomeAvalista = nomeAvalista;
    }

    /**
     * @return o atributo idCarteira
     */
    public Integer getIdCarteira() {
        return idCarteira;
    }

    /**
     * Define o atributo idCarteira
     */
    public void setIdCarteira(Integer idCarteira) {
        this.idCarteira = idCarteira;
    }

    /**
     * @return o atributo codMoeda
     */
    public String getCodMoeda() {
        return codMoeda;
    }

    /**
     * Define o atributo codMoeda
     */
    public void setCodMoeda(String codMoeda) {
        this.codMoeda = codMoeda;
    }

    /**
     * @return o atributo idOrgaoMoeda
     */
    public Integer getIdOrgaoMoeda() {
        return idOrgaoMoeda;
    }

    /**
     * Define o atributo idOrgaoMoeda
     */
    public void setIdOrgaoMoeda(Integer idOrgaoMoeda) {
        this.idOrgaoMoeda = idOrgaoMoeda;
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
     * @return o atributo valorDoBoleto
     */
    public BigDecimal getValorDoBoleto() {
        return valorDoBoleto;
    }

    /**
     * Define o atributo valorDoBoleto
     */
    public void setValorDoBoleto(BigDecimal valorDoBoleto) {
        this.valorDoBoleto = valorDoBoleto;
    }

    /**
     * @return o atributo dataEmissao
     */
    public DateTime getDataEmissao() {
        return dataEmissao;
    }

    /**
     * Define o atributo dataEmissao
     */
    public void setDataEmissao(DateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
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
     * @return o atributo dataLimitePgto
     */
    public DateTime getDataLimitePgto() {
        return dataLimitePgto;
    }

    /**
     * Define o atributo dataLimitePgto
     */
    public void setDataLimitePgto(DateTime dataLimitePgto) {
        this.dataLimitePgto = dataLimitePgto;
    }

    /**
     * @return o atributo diasDeProtesto
     */
    public Integer getDiasDeProtesto() {
        return diasDeProtesto;
    }

    /**
     * Define o atributo diasDeProtesto
     */
    public void setDiasDeProtesto(Integer diasDeProtesto) {
        this.diasDeProtesto = diasDeProtesto;
    }

    /**
     * @return o atributo valorAbatimento
     */
    public BigDecimal getValorAbatimento() {
        return valorAbatimento;
    }

    /**
     * Define o atributo valorAbatimento
     */
    public void setValorAbatimento(BigDecimal valorAbatimento) {
        this.valorAbatimento = valorAbatimento;
    }

    /**
     * @return o atributo indValorMinimo
     */
    public String getIndValorMinimo() {
        return indValorMinimo;
    }

    /**
     * Define o atributo indValorMinimo
     */
    public void setIndValorMinimo(String indValorMinimo) {
        this.indValorMinimo = indValorMinimo;
    }

    /**
     * @return o atributo indValorMaximo
     */
    public String getIndValorMaximo() {
        return indValorMaximo;
    }

    /**
     * Define o atributo indValorMaximo
     */
    public void setIndValorMaximo(String indValorMaximo) {
        this.indValorMaximo = indValorMaximo;
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
     * @return o atributo numeroParcela
     */
    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    /**
     * Define o atributo numeroParcela
     */
    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    /**
     * @return o atributo qtdTotalParcelas
     */
    public Integer getQtdTotalParcelas() {
        return qtdTotalParcelas;
    }

    /**
     * Define o atributo qtdTotalParcelas
     */
    public void setQtdTotalParcelas(Integer qtdTotalParcelas) {
        this.qtdTotalParcelas = qtdTotalParcelas;
    }

    /**
     * @return o atributo bolTituloNegociado
     */
    public String getBolTituloNegociado() {
        return bolTituloNegociado;
    }

    /**
     * Define o atributo bolTituloNegociado
     */
    public void setBolTituloNegociado(String bolTituloNegociado) {
        this.bolTituloNegociado = bolTituloNegociado;
    }

    /**
     * @return o atributo bolBloqueioPagamento
     */
    public String getBolBloqueioPagamento() {
        return bolBloqueioPagamento;
    }

    /**
     * Define o atributo bolBloqueioPagamento
     */
    public void setBolBloqueioPagamento(String bolBloqueioPagamento) {
        this.bolBloqueioPagamento = bolBloqueioPagamento;
    }

    /**
     * @return o atributo bolPagamentoParcial
     */
    public String getBolPagamentoParcial() {
        return bolPagamentoParcial;
    }

    /**
     * Define o atributo bolPagamentoParcial
     */
    public void setBolPagamentoParcial(String bolPagamentoParcial) {
        this.bolPagamentoParcial = bolPagamentoParcial;
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
     * @return o atributo tipoModeloDeCalculo
     */
    public String getTipoModeloDeCalculo() {
        return tipoModeloDeCalculo;
    }

    /**
     * Define o atributo tipoModeloDeCalculo
     */
    public void setTipoModeloDeCalculo(String tipoModeloDeCalculo) {
        this.tipoModeloDeCalculo = tipoModeloDeCalculo;
    }

    /**
     * @return o atributo codAutorizacaoValorDivergente
     */
    public String getCodAutorizacaoValorDivergente() {
        return codAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo codAutorizacaoValorDivergente
     */
    public void setCodAutorizacaoValorDivergente(String codAutorizacaoValorDivergente) {
        this.codAutorizacaoValorDivergente = codAutorizacaoValorDivergente;
    }

    /**
     * @return o atributo idEspecieDocumento
     */
    public Integer getIdEspecieDocumento() {
        return idEspecieDocumento;
    }

    /**
     * Define o atributo idEspecieDocumento
     */
    public void setIdEspecieDocumento(Integer idEspecieDocumento) {
        this.idEspecieDocumento = idEspecieDocumento;
    }

    /**
     * @return o atributo codTipoPagamento
     */
    public Integer getCodTipoPagamento() {
        return codTipoPagamento;
    }

    /**
     * Define o atributo codTipoPagamento
     */
    public void setCodTipoPagamento(Integer codTipoPagamento) {
        this.codTipoPagamento = codTipoPagamento;
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

    /**
     * @return o atributo numSeqAtualCadBoleto
     */
    public Long getNumSeqAtualCadBoleto() {
        return numSeqAtualCadBoleto;
    }

    /**
     * Define o atributo numSeqAtualCadBoleto
     */
    public void setNumSeqAtualCadBoleto(Long numSeqAtualCadBoleto) {
        this.numSeqAtualCadBoleto = numSeqAtualCadBoleto;
    }

    /**
     * @return o atributo listDadosDesconto
     */
    public List<MensagemDDABoletoDescontoDto> getListDadosDesconto() {
        return listDadosDesconto;
    }

    /**
     * Define o atributo listDadosDesconto
     */
    public void setListDadosDesconto(List<MensagemDDABoletoDescontoDto> listDadosDesconto) {
        if (ObjectUtil.isEmpty(this.listDadosDesconto)) {
            this.listDadosDesconto = new ArrayList<MensagemDDABoletoDescontoDto>();
        }

        this.listDadosDesconto = listDadosDesconto;
    }

    /**
     * @return o atributo codTipoJuros
     */
    public Integer getCodTipoJuros() {
        return codTipoJuros;
    }

    /**
     * Define o atributo codTipoJuros
     */
    public void setCodTipoJuros(Integer codTipoJuros) {
        this.codTipoJuros = codTipoJuros;
    }

    /**
     * @return o atributo dataJuros
     */
    public DateTime getDataJuros() {
        return dataJuros;
    }

    /**
     * Define o atributo dataJuros
     */
    public void setDataJuros(DateTime dataJuros) {
        this.dataJuros = dataJuros;
    }

    /**
     * @return o atributo valorJuros
     */
    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    /**
     * Define o atributo valorJuros
     */
    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    /**
     * @return o atributo codTipoMulta
     */
    public Integer getCodTipoMulta() {
        return codTipoMulta;
    }

    /**
     * Define o atributo codTipoMulta
     */
    public void setCodTipoMulta(Integer codTipoMulta) {
        this.codTipoMulta = codTipoMulta;
    }

    /**
     * @return o atributo dataMulta
     */
    public DateTime getDataMulta() {
        return dataMulta;
    }

    /**
     * Define o atributo dataMulta
     */
    public void setDataMulta(DateTime dataMulta) {
        this.dataMulta = dataMulta;
    }

    /**
     * @return o atributo valorMulta
     */
    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    /**
     * Define o atributo valorMulta
     */
    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    /**
     * @return o atributo listDadosGrupoCalculo
     */
    public List<MensagemDDABoletoGrupoCalculoDto> getListDadosGrupoCalculo() {
        return listDadosGrupoCalculo;
    }

    /**
     * Define o atributo listDadosGrupoCalculo
     */
    public void setListDadosGrupoCalculo(List<MensagemDDABoletoGrupoCalculoDto> listDadosGrupoCalculo) {
        if (ObjectUtil.isEmpty(this.listDadosGrupoCalculo)) {
            this.listDadosGrupoCalculo = new ArrayList<MensagemDDABoletoGrupoCalculoDto>();
        }
        this.listDadosGrupoCalculo = listDadosGrupoCalculo;
    }

    /**
     * @return o atributo dataHoraMensagem
     */
    public DateTime getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * Define o atributo dataHoraMensagem
     */
    public void setDataHoraMensagem(DateTime dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

    /**
     * @return o atributo dataHoraProtocolo
     */
    public DateTime getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    /**
     * Define o atributo dataHoraProtocolo
     */
    public void setDataHoraProtocolo(DateTime dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    /**
     * @return o atributo numOperacao
     */
    public String getNumOperacao() {
        return numOperacao;
    }

    /**
     * Define o atributo numOperacao
     */
    public void setNumOperacao(String numOperacao) {
        this.numOperacao = numOperacao;
    }

    /**
     * @return o atributo qtdPesquisa
     */
    public Integer getQtdPesquisa() {
        return qtdPesquisa;
    }

    /**
     * Define o atributo qtdPesquisa
     */
    public void setQtdPesquisa(Integer qtdPesquisa) {
        this.qtdPesquisa = qtdPesquisa;
    }
}
