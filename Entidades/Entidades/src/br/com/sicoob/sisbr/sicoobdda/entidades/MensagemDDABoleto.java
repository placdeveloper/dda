/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABoleto.java
 * Data Criação:    Jun 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDABoleto
 * 
 * @author george.santos
 */
/**
 * MensagemDDABoleto é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "MensagemDDABoleto", schema = "DDA")
public class MensagemDDABoleto extends SicoobDDAEntidade implements IMensagemDDA {
    private static final long serialVersionUID = -1463531203355301738L;

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

    @Column(nullable = false)
    private String codTipoPessoaBeneficiario;

    @Column(nullable = false)
    private String numCpfCnpjBeneficiario;

    @Column(nullable = false)
    private String nomeBeneficiario;

    private String nomeFantasiaBeneficiario;

    private String descLogradouroBeneficiario;

    private String descCidadeBeneficiario;

    private String siglaUfBeneficiario;

    private String numCepBeneficiario;

    private String codTipoPessoaBeneficiarioFinal;

    private String numCpfCnpjBeneficiarioFinal;

    private String nomeBeneficiarioFinal;

    private String nomeFantasiaBeneficiarioFinal;

    // Pagador
    @Column(nullable = false)
    private String codTipoPessoaPagador;

    @Column(nullable = false)
    private String numCpfCnpjPagador;

    @Column(nullable = false)
    private String nomePagador;

    private String nomeFantasiaPagador;

    private String descLogradouroPagador;

    private String descCidadePagador;

    private String siglaUfPagador;

    private String numCepPagador;

    // SacadorAvalista
    private String codTipoPessoaDDAAvalista;

    private String numCpfCnpjAvalista;

    private String nomeAvalista;

    @Column(nullable = false)
    private Integer idCarteira;

    @Column(nullable = false)
    private String codMoeda;

    @Column(nullable = false)
    private Integer idOrgaoMoeda;

    private String numNossoNumero;

    @Column(nullable = false)
    private String numCodigoBarra;

    private String numLinhaDigitavel;

    @Column(nullable = false)
    private DateTimeDB dataVencimento;

    @Column(nullable = false)
    private BigDecimal valorBoleto;

    private String numDocumento;

    @Column(nullable = false)
    private DateTimeDB dataEmissao;

    private Integer numDiasProtesto;

    @Column(nullable = false)
    private DateTimeDB dataLimitePagamento;

    private Integer codTipoPagamento;

    private Integer numParcela;

    private Integer qtdTotalParcela;

    @Column(nullable = false)
    private Boolean bolTituloNegociado;

    @Column(nullable = false)
    private Boolean bolBloqueioPagamento;

    @Column(nullable = false)
    private Boolean bolPagamentoParcial;

    private Integer qtdPagamentoParcial;

    @Column(nullable = false)
    private BigDecimal valorAbatimento;

    private String codIndicadorValorMinimo;

    private BigDecimal valorMinimo;

    private String codIndicadorValorMaximo;

    private BigDecimal valorMaximo;

    @Column(nullable = false)
    private String codTipoModeloCalculo;

    @Column(nullable = false)
    private String codAutorizacaoValorDivergente;

    @Column(nullable = false)
    private Integer idEspecieDocumento;

    private Long numRefAtualCadBoleto;

    private Long numSeqAtualCadBoleto;

    @Transient
    private Long numIdentificadorBoletoCip;

    // MensagemDDABoletoJuros
    private DateTimeDB dataJuros;

    @Column(nullable = false)
    private Integer codTipoJuros;

    @Column(nullable = false)
    private BigDecimal valorPercentualJuros;

    // MensagemDDABoletoMulta

    private DateTimeDB dataMulta;

    @Column(nullable = false)
    private Integer codTipoMulta;

    @Column(nullable = false)
    private BigDecimal valorPercentualMulta;

    // MensagemDDABoletoDesconto
    private DateTimeDB dataDesconto1;

    @Column(nullable = false)
    private String codTipoDesconto1;

    @Column(nullable = false)
    private BigDecimal valorPercentualDesconto1;

    private DateTimeDB dataDesconto2;

    private String codTipoDesconto2;

    private BigDecimal valorPercentualDesconto2;

    private DateTimeDB dataDesconto3;

    private String codTipoDesconto3;

    private BigDecimal valorPercentualDesconto3;

    @Column(nullable = false)
    private String numCodBarrasCampoLivre;

    @OneToMany(mappedBy = "mensagemDDABoleto", cascade = CascadeType.ALL)
    private List<MensagemDDABoletoGrupoCalculo> listaMensagemDDABoletoGrupoCalculo;

    @OneToMany(mappedBy = "mensagemDDABoleto", cascade = CascadeType.ALL)
    private List<MensagemDDABoletoNotaFiscal> listaMensagemDDABoletoNotaFiscal;

    @OneToMany(mappedBy = "mensagemDDABoleto", cascade = CascadeType.ALL)
    private List<MensagemDDABoletoTextoInfo> listaMensagemDDABoletoTextoInfo;

    public MensagemDDABoleto() {
    }

    public MensagemDDABoleto(Long idMensagemDDA) {
        super();
        this.id = idMensagemDDA;
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
     * @return o atributo siglaUfBeneficiario
     */
    public String getSiglaUfBeneficiario() {
        return siglaUfBeneficiario;
    }

    /**
     * Define o atributo siglaUfBeneficiario
     */
    public void setSiglaUfBeneficiario(String siglaUfBeneficiario) {
        this.siglaUfBeneficiario = siglaUfBeneficiario;
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
     * @return o atributo numCpfCnpjBeneficiarioFinal
     */
    public String getNumCpfCnpjBeneficiarioFinal() {
        return numCpfCnpjBeneficiarioFinal;
    }

    /**
     * Define o atributo numCpfCnpjBeneficiarioFinal
     */
    public void setNumCpfCnpjBeneficiarioFinal(String numCpfCnpjBeneficiarioFinal) {
        this.numCpfCnpjBeneficiarioFinal = numCpfCnpjBeneficiarioFinal;
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
     * @return o atributo descCidadePagador
     */
    public String getDescCidadePagador() {
        return descCidadePagador;
    }

    /**
     * Define o atributo descCidadePagador
     */
    public void setDescCidadePagador(String descCidadePagador) {
        this.descCidadePagador = descCidadePagador;
    }

    /**
     * @return o atributo siglaUfPagador
     */
    public String getSiglaUfPagador() {
        return siglaUfPagador;
    }

    /**
     * Define o atributo siglaUfPagador
     */
    public void setSiglaUfPagador(String siglaUfPagador) {
        this.siglaUfPagador = siglaUfPagador;
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
     * @return o atributo codTipoPessoaDDAAvalista
     */
    public String getCodTipoPessoaDDAAvalista() {
        return codTipoPessoaDDAAvalista;
    }

    /**
     * Define o atributo codTipoPessoaDDAAvalista
     */
    public void setCodTipoPessoaDDAAvalista(String codTipoPessoaDDAAvalista) {
        this.codTipoPessoaDDAAvalista = codTipoPessoaDDAAvalista;
    }

    /**
     * @return o atributo numCpfCnpjAvalista
     */
    public String getNumCpfCnpjAvalista() {
        return numCpfCnpjAvalista;
    }

    /**
     * Define o atributo numCpfCnpjAvalista
     */
    public void setNumCpfCnpjAvalista(String numCpfCnpjAvalista) {
        this.numCpfCnpjAvalista = numCpfCnpjAvalista;
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
     * @return o atributo dataEmissao
     */
    public DateTimeDB getDataEmissao() {
        return dataEmissao;
    }

    /**
     * Define o atributo dataEmissao
     */
    public void setDataEmissao(DateTimeDB dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return o atributo numDiasProtesto
     */
    public Integer getNumDiasProtesto() {
        return numDiasProtesto;
    }

    /**
     * Define o atributo numDiasProtesto
     */
    public void setNumDiasProtesto(Integer numDiasProtesto) {
        this.numDiasProtesto = numDiasProtesto;
    }

    /**
     * @return o atributo dataLimitePagamento
     */
    public DateTimeDB getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    /**
     * Define o atributo dataLimitePagamento
     */
    public void setDataLimitePagamento(DateTimeDB dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
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
     * @return o atributo numParcela
     */
    public Integer getNumParcela() {
        return numParcela;
    }

    /**
     * Define o atributo numParcela
     */
    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    /**
     * @return o atributo qtdTotalParcela
     */
    public Integer getQtdTotalParcela() {
        return qtdTotalParcela;
    }

    /**
     * Define o atributo qtdTotalParcela
     */
    public void setQtdTotalParcela(Integer qtdTotalParcela) {
        this.qtdTotalParcela = qtdTotalParcela;
    }

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    public Boolean isBolTituloNegociado() {
        return bolTituloNegociado;
    }

    /**
     * @return o atributo bolTituloNegociado retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public String getBolTituloNegociado() {
        return bolTituloNegociado ? "S" : "N";
    }

    /**
     * Define o atributo bolTituloNegociado
     */
    public void setBolTituloNegociado(Boolean bolTituloNegociado) {
        this.bolTituloNegociado = bolTituloNegociado;
    }

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    public Boolean isBolBloqueioPagamento() {
        return bolBloqueioPagamento;
    }

    /**
     * @return o atributo bolBloqueioPagamento retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public String getBolBloqueioPagamento() {
        return bolBloqueioPagamento ? "S" : "N";
    }

    /**
     * Define o atributo bolBloqueioPagamento
     */
    public void setBolBloqueioPagamento(Boolean bolBloqueioPagamento) {
        this.bolBloqueioPagamento = bolBloqueioPagamento;
    }

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    public Boolean isBolPagamentoParcial() {
        return bolPagamentoParcial;
    }

    /**
     * @return o atributo bolPagamentoParcial retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public String getBolPagamentoParcial() {
        return bolPagamentoParcial ? "S" : "N";
    }

    /**
     * Define o atributo bolPagamentoParcial
     */
    public void setBolPagamentoParcial(Boolean bolPagamentoParcial) {
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
     * @return o atributo codIndicadorValorMinimo
     */
    public String getCodIndicadorValorMinimo() {
        return codIndicadorValorMinimo;
    }

    /**
     * Define o atributo codIndicadorValorMinimo
     */
    public void setCodIndicadorValorMinimo(String codIndicadorValorMinimo) {
        this.codIndicadorValorMinimo = codIndicadorValorMinimo;
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
     * @return o atributo codIndicadorValorMaximo
     */
    public String getCodIndicadorValorMaximo() {
        return codIndicadorValorMaximo;
    }

    /**
     * Define o atributo codIndicadorValorMaximo
     */
    public void setCodIndicadorValorMaximo(String codIndicadorValorMaximo) {
        this.codIndicadorValorMaximo = codIndicadorValorMaximo;
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
     * @return o atributo codTipoModeloCalculo
     */
    public String getCodTipoModeloCalculo() {
        return codTipoModeloCalculo;
    }

    /**
     * Define o atributo codTipoModeloCalculo
     */
    public void setCodTipoModeloCalculo(String codTipoModeloCalculo) {
        this.codTipoModeloCalculo = codTipoModeloCalculo;
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
     * @return o atributo listaMensagemDDABoletoGrupoCalculo
     */
    public List<MensagemDDABoletoGrupoCalculo> getListaMensagemDDABoletoGrupoCalculo() {
        if (listaMensagemDDABoletoGrupoCalculo == null) {
            listaMensagemDDABoletoGrupoCalculo = new ArrayList<MensagemDDABoletoGrupoCalculo>();
        }
        return listaMensagemDDABoletoGrupoCalculo;
    }

    /**
     * Define o atributo listaMensagemDDABoletoGrupoCalculo
     */
    public void setListaMensagemDDABoletoGrupoCalculo(List<MensagemDDABoletoGrupoCalculo> listaMensagemDDABoletoGrupoCalculo) {
        this.listaMensagemDDABoletoGrupoCalculo = listaMensagemDDABoletoGrupoCalculo;
    }

    /**
     * @return o atributo listaMensagemDDABoletoNotaFiscal
     */
    public List<MensagemDDABoletoNotaFiscal> getListaMensagemDDABoletoNotaFiscal() {
        if (listaMensagemDDABoletoNotaFiscal == null) {
            listaMensagemDDABoletoNotaFiscal = new ArrayList<MensagemDDABoletoNotaFiscal>();
        }
        return listaMensagemDDABoletoNotaFiscal;
    }

    /**
     * Define o atributo listaMensagemDDABoletoNotaFiscal
     */
    public void setListaMensagemDDABoletoNotaFiscal(List<MensagemDDABoletoNotaFiscal> listaMensagemDDABoletoNotaFiscal) {
        this.listaMensagemDDABoletoNotaFiscal = listaMensagemDDABoletoNotaFiscal;
    }

    /**
     * @return o atributo listaMensagemDDABoletoTextoInfo
     */
    public List<MensagemDDABoletoTextoInfo> getListaMensagemDDABoletoTextoInfo() {
        if (listaMensagemDDABoletoTextoInfo == null) {
            listaMensagemDDABoletoTextoInfo = new ArrayList<MensagemDDABoletoTextoInfo>();
        }
        return listaMensagemDDABoletoTextoInfo;
    }

    /**
     * Define o atributo listaMensagemDDABoletoTextoInfo
     */
    public void setListaMensagemDDABoletoTextoInfo(List<MensagemDDABoletoTextoInfo> listaMensagemDDABoletoTextoInfo) {
        this.listaMensagemDDABoletoTextoInfo = listaMensagemDDABoletoTextoInfo;
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
     * @return the numSeqAtualCadBoleto
     */
    public Long getNumSeqAtualCadBoleto() {
        return numSeqAtualCadBoleto;
    }

    /**
     * @param numSeqAtualCadBoleto the numSeqAtualCadBoleto to set
     */
    public void setNumSeqAtualCadBoleto(Long numSeqAtualCadBoleto) {
        this.numSeqAtualCadBoleto = numSeqAtualCadBoleto;
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
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return getIdOperacaoLeg();
    }

    /**
     * @return dataJuros
     */
    public DateTimeDB getDataJuros() {
        return dataJuros;
    }

    /**
     * @param dataJuros
     */
    public void setDataJuros(DateTimeDB dataJuros) {
        this.dataJuros = dataJuros;
    }

    /**
     * @return codTipoJuros
     */
    public Integer getCodTipoJuros() {
        return codTipoJuros;
    }

    /**
     * @param codTipoJuros
     */
    public void setCodTipoJuros(Integer codTipoJuros) {
        this.codTipoJuros = codTipoJuros;
    }

    /**
     * @return valorPercentualJuros
     */
    public BigDecimal getValorPercentualJuros() {
        return valorPercentualJuros;
    }

    /**
     * @param valorPercentualJuros
     */
    public void setValorPercentualJuros(BigDecimal valorPercentualJuros) {
        this.valorPercentualJuros = valorPercentualJuros;
    }

    /**
     * @return dataMulta
     */
    public DateTimeDB getDataMulta() {
        return dataMulta;
    }

    /**
     * @param dataMulta
     */
    public void setDataMulta(DateTimeDB dataMulta) {
        this.dataMulta = dataMulta;
    }

    /**
     * @return codTipoMulta
     */
    public Integer getCodTipoMulta() {
        return codTipoMulta;
    }

    /**
     * @param codTipoMulta
     */
    public void setCodTipoMulta(Integer codTipoMulta) {
        this.codTipoMulta = codTipoMulta;
    }

    /**
     * @return valorPercentualMulta
     */
    public BigDecimal getValorPercentualMulta() {
        return valorPercentualMulta;
    }

    /**
     * @param valorPercentualMulta
     */
    public void setValorPercentualMulta(BigDecimal valorPercentualMulta) {
        this.valorPercentualMulta = valorPercentualMulta;
    }

    /**
     * @return dataDesconto1
     */
    public DateTimeDB getDataDesconto1() {
        return dataDesconto1;
    }

    /**
     * @param dataDesconto1
     */
    public void setDataDesconto1(DateTimeDB dataDesconto1) {
        this.dataDesconto1 = dataDesconto1;
    }

    /**
     * @return codTipoDesconto1
     */
    public String getCodTipoDesconto1() {
        return codTipoDesconto1;
    }

    /**
     * @param codTipoDesconto1
     */
    public void setCodTipoDesconto1(String codTipoDesconto1) {
        this.codTipoDesconto1 = codTipoDesconto1;
    }

    /**
     * @return valorPercentualDesconto1
     */
    public BigDecimal getValorPercentualDesconto1() {
        return valorPercentualDesconto1;
    }

    /**
     * @param valorPercentualDesconto1
     */
    public void setValorPercentualDesconto1(BigDecimal valorPercentualDesconto1) {
        this.valorPercentualDesconto1 = valorPercentualDesconto1;
    }

    /**
     * @return dataDesconto2
     */
    public DateTimeDB getDataDesconto2() {
        return dataDesconto2;
    }

    /**
     * @param dataDesconto2
     */
    public void setDataDesconto2(DateTimeDB dataDesconto2) {
        this.dataDesconto2 = dataDesconto2;
    }

    /**
     * @return codTipoDesconto2
     */
    public String getCodTipoDesconto2() {
        return codTipoDesconto2;
    }

    /**
     * @param codTipoDesconto2
     */
    public void setCodTipoDesconto2(String codTipoDesconto2) {
        this.codTipoDesconto2 = codTipoDesconto2;
    }

    /**
     * @return valorPercentualDesconto2
     */
    public BigDecimal getValorPercentualDesconto2() {
        return valorPercentualDesconto2;
    }

    /**
     * @param valorPercentualDesconto2
     */
    public void setValorPercentualDesconto2(BigDecimal valorPercentualDesconto2) {
        this.valorPercentualDesconto2 = valorPercentualDesconto2;
    }

    /**
     * @return dataDesconto3
     */
    public DateTimeDB getDataDesconto3() {
        return dataDesconto3;
    }

    /**
     * @param dataDesconto3
     */
    public void setDataDesconto3(DateTimeDB dataDesconto3) {
        this.dataDesconto3 = dataDesconto3;
    }

    /**
     * @return codTipoDesconto3
     */
    public String getCodTipoDesconto3() {
        return codTipoDesconto3;
    }

    /**
     * @param codTipoDesconto3
     */
    public void setCodTipoDesconto3(String codTipoDesconto3) {
        this.codTipoDesconto3 = codTipoDesconto3;
    }

    /**
     * @return valorPercentualDesconto3
     */
    public BigDecimal getValorPercentualDesconto3() {
        return valorPercentualDesconto3;
    }

    /**
     * @param valorPercentualDesconto3
     */
    public void setValorPercentualDesconto3(BigDecimal valorPercentualDesconto3) {
        this.valorPercentualDesconto3 = valorPercentualDesconto3;
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

}
