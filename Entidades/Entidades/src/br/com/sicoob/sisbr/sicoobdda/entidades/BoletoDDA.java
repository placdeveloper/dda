/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         BoletoDDA.java
 * Data Criação:    Jul 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * BoletoDDA
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "BOLETODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDAVO")
public class BoletoDDA extends SicoobDDAEntidade {

	private static final long serialVersionUID = 1581943566243358617L;
	private static final String TIPO_DESCONTO_ISENTO = "0 - Isento"; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDA", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long numIdentificadorBoletoCip;

    @Column(nullable = false)
    private Long numRefAtualCadBoleto;

    private Long numSeqAtualCadBoleto;

    private Long numRefAtualCadAceite;

    private Long numSeqAtualAceite;

    private DateTimeDB dataHoraSituacaoBoleto;

    private String codIspbPartDestinatario;

    private String codPartDestinatario;

    // DDA.TipoPessoaDDA
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

    // DDA.TipoPessoaDDA
    private String codTipoPessoaBeneficiarioFinal;

    private String numCpfCnpjBeneficiarioFinal;

    private String nomeBeneficiarioFinal;

    private String nomeFantasiaBeneficiarioFinal;

    // DDA.TipoPessoaDDA
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

    // DDA.TipoPessoaDDAAvalista
    private String codTipoPessoaDDAAvalista;

    private String numCpfCnpjAvalista;

    private String nomeAvalista;

    @Column(nullable = false)
    private Integer idCarteira;

    @Column(nullable = false)
    private String codMoeda;

    private String numNossoNumero;

    @Column(nullable = false)
    private String numCodigoBarra;

    private String numLinhaDigitavel;

    @Column(nullable = false)
    private DateTimeDB dataVencimento;

    private BigDecimal valorBoleto;

    private String numDocumento;

    private Integer idEspecieDocumento;

    private DateTimeDB dataEmissao;

    private Integer numDiasProtesto;

    private DateTimeDB dataLimitePagamento;

    // DDA.TipoPagamento
    private Integer codTipoPagamento;

    private Integer numParcela;

    private Integer qtdTotalParcela;

    @Column(nullable = false)
    private Boolean bolTituloNegociado;

    @Column(nullable = false)
    private Boolean bolBloqueioPagamento;

    @Column(nullable = false)
    private Boolean bolPagamentoParcial;

    private BigDecimal valorAbatimento;

    // DDA.TipoPercentualValor
    private String codIndicadorValorMinimo;

    private BigDecimal valorMinimo;

    // DDA.TipoPercentualValor
    private String codIndicadorValorMaximo;

    private BigDecimal valorMaximo;

    // DDA.TipoModeloCalculo
    private String codTipoModeloCalculo;

    // DDA.AutorizacaoValorDivergente
    private String codAutorizacaoValorDivergente;

    @Column(nullable = false)
    private Integer qtdPagamentoParcialReg;

    private BigDecimal valorSaldoPagamento;

    // DDA.SituacaoBoletoPagamento
    private String codSituacaoBoletoPagamento;

    private Integer qtdPagamentoParcial;

    @Column(nullable = false)
    private Boolean bolAceite;

    private DateTimeDB dataHoraSituacaoAceite;

    @Column(insertable = false)
    private DateTimeDB dataHoraUltimaAtualizacao;

    // BoletoDDAJuros
    private DateTimeDB dataJuros;
    
    @OneToOne
    @JoinColumn(name="CODTIPOJUROS")    
    private TipoJuros tipoJuros;

    @Column(nullable = false)
    private BigDecimal valorPercentualJuros;

    // BoletoDDAMulta

    private DateTimeDB dataMulta;

    @OneToOne
    @JoinColumn(name="CODTIPOMULTA")    
	private TipoMulta tipoMulta;

    @Column(nullable = false)
    private BigDecimal valorPercentualMulta;    

    // BoletoDDADesconto
    private DateTimeDB dataDesconto1;
    
    @OneToOne
    @JoinColumn(name="CODTIPODESCONTO1")
    private TipoDesconto tipoDesconto1;

    @Column(nullable = false)
    private BigDecimal valorPercentualDesconto1;

    @OneToOne
    @JoinColumn(name="CODTIPODESCONTO2")
    private TipoDesconto tipoDesconto2;
    
    private DateTimeDB dataDesconto2;    

    private BigDecimal valorPercentualDesconto2;

    private DateTimeDB dataDesconto3;

    @OneToOne
    @JoinColumn(name="CODTIPODESCONTO3")
    private TipoDesconto tipoDesconto3;

    private BigDecimal valorPercentualDesconto3;

    @Column(nullable = false)
    private String numCodBarrasCampoLivre;

    @PreUpdate
    void onUpdate() {
        this.dataHoraUltimaAtualizacao = new DateTimeDB();
    }

    // DDA.SituacaoBoleto
    private Integer codSituacaoBoleto;

    @Column(insertable = false)
    private DateTimeDB dataHoraInclusao;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDABaixaEfet> listaBoletoDDABaixaEfet;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDABaixaOper> listaBoletoDDABaixaOper;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDAGrupoCalculo> listaBoletoDDAGrupoCalculo;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDANotaFiscal> listaBoletoDDANotaFiscal;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDATextoInfo> listaBoletoDDATextoInfo;

    @OneToMany(mappedBy = "boletoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoletoDDATerceiroAut> listaBoletoDDATerceiroAutorizado;

    @Transient
    private Boolean bolProcessarMensagemRecebida;

    @Transient
    private Boolean bolProcessarMensagem106;

    // Quantidade de pagamento que ainda pode ser feito
    @Transient
    private Integer qtdPagamentoRestante;

    // Indica o valor já pago (baixa operacional e efetiva)
    @Transient
    private BigDecimal valorPago;

    @Transient
    private String descBanco;

    @Transient
    private String descModeloCalculo;

    @Transient
    private String descAutorizacaoValorDivergente;

    @Transient
    private String descSituacaoBoleto;

    @Transient
    private String descSituacaoBoletoPagamento;

    @Transient
    private String descTipoPessoaAvalista;

    @Transient
    private String descTextoInformativoSemEspaco;

    @Transient
    private String descTipoPagamento;

    @Transient
    private String descCarteira;

    @Transient
    private String descEspecie;

    @Transient
    private List<PagadorDDAAgregado> listaPagadorDDAAgregado;

    @Transient
    private String numRefAtualCadBoletoStr;

    @Transient
    private String numIdentificadorBoletoCipStr;

    /**
     * Construtor padrão
     */
    public BoletoDDA() {
    }

    /**
     * @param id
     * @param codTipoPessoaPagador
     * @param numCpfCnpjPagador
     */
    public BoletoDDA(Long id, String codTipoPessoaPagador, String numCpfCnpjPagador) {
        this.id = id;
        this.codTipoPessoaPagador = codTipoPessoaPagador;
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

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
     * @return o atributo numRefAtualCadAceite
     */
    public Long getNumRefAtualCadAceite() {
        return numRefAtualCadAceite;
    }

    /**
     * Define o atributo numRefAtualCadAceite
     */
    public void setNumRefAtualCadAceite(Long numRefAtualCadAceite) {
        this.numRefAtualCadAceite = numRefAtualCadAceite;
    }

    /**
     * @return o atributo numSeqAtualAceite
     */
    public Long getNumSeqAtualAceite() {
        return numSeqAtualAceite;
    }

    /**
     * Define o atributo numSeqAtualAceite
     */
    public void setNumSeqAtualAceite(Long numSeqAtualAceite) {
        this.numSeqAtualAceite = numSeqAtualAceite;
    }

    /**
     * @return o atributo dataHoraSituacaoBoleto
     */
    public DateTimeDB getDataHoraSituacaoBoleto() {
        return dataHoraSituacaoBoleto;
    }

    /**
     * Define o atributo dataHoraSituacaoBoleto
     */
    public void setDataHoraSituacaoBoleto(DateTimeDB dataHoraSituacaoBoleto) {
        this.dataHoraSituacaoBoleto = dataHoraSituacaoBoleto;
    }

    /**
     * @return o atributo codIspbPartDestinatario
     */
    public String getCodIspbPartDestinatario() {
        return codIspbPartDestinatario;
    }

    /**
     * Define o atributo codIspbPartDestinatario
     */
    public void setCodIspbPartDestinatario(String codIspbPartDestinatario) {
        this.codIspbPartDestinatario = codIspbPartDestinatario;
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
     * @return o atributo bolTituloNegociado
     */
    public Boolean getBolTituloNegociado() {
        return bolTituloNegociado;
    }

    /**
     * Define o atributo bolTituloNegociado
     */
    public void setBolTituloNegociado(Boolean bolTituloNegociado) {
        this.bolTituloNegociado = bolTituloNegociado;
    }

    /**
     * @return o atributo bolBloqueioPagamento
     */
    public Boolean getBolBloqueioPagamento() {
        return bolBloqueioPagamento;
    }

    /**
     * Define o atributo bolBloqueioPagamento
     */
    public void setBolBloqueioPagamento(Boolean bolBloqueioPagamento) {
        this.bolBloqueioPagamento = bolBloqueioPagamento;
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
     * @return o atributo qtdPagamentoParcialReg
     */
    public Integer getQtdPagamentoParcialReg() {
        return qtdPagamentoParcialReg;
    }

    /**
     * Define o atributo qtdPagamentoParcialReg
     */
    public void setQtdPagamentoParcialReg(Integer qtdPagamentoParcialReg) {
        this.qtdPagamentoParcialReg = qtdPagamentoParcialReg;
    }

    /**
     * Método responsável por
     * 
     * @param qtdPagamentoParcialReg void
     * 
     */
    public void setQtdPagamentoParcialReg(BigInteger qtdPagamentoParcialReg) {
        if (qtdPagamentoParcialReg != null) {
            this.qtdPagamentoParcialReg = qtdPagamentoParcialReg.intValue();
        }
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
     * @return o atributo dataHoraSituacaoAceite
     */
    public DateTimeDB getDataHoraSituacaoAceite() {
        return dataHoraSituacaoAceite;
    }

    /**
     * Define o atributo dataHoraSituacaoAceite
     */
    public void setDataHoraSituacaoAceite(DateTimeDB dataHoraSituacaoAceite) {
        this.dataHoraSituacaoAceite = dataHoraSituacaoAceite;
    }

    /**
     * @return o atributo listaBoletoDDABaixaEfet
     */
    public List<BoletoDDABaixaEfet> getListaBoletoDDABaixaEfet() {
        return listaBoletoDDABaixaEfet;
    }

    /**
     * Define o atributo listaBoletoDDABaixaEfet
     */
    public void setListaBoletoDDABaixaEfet(List<BoletoDDABaixaEfet> listaBoletoDDABaixaEfet) {
        this.listaBoletoDDABaixaEfet = listaBoletoDDABaixaEfet;
    }

    /**
     * @return o atributo listaBoletoDDABaixaOper
     */
    public List<BoletoDDABaixaOper> getListaBoletoDDABaixaOper() {
        return listaBoletoDDABaixaOper;
    }

    /**
     * Define o atributo listaBoletoDDABaixaOper
     */
    public void setListaBoletoDDABaixaOper(List<BoletoDDABaixaOper> listaBoletoDDABaixaOper) {
        this.listaBoletoDDABaixaOper = listaBoletoDDABaixaOper;
    }

    /**
     * @return o atributo listaBoletoDDAGrupoCalculo
     */
    public List<BoletoDDAGrupoCalculo> getListaBoletoDDAGrupoCalculo() {
        return listaBoletoDDAGrupoCalculo;
    }

    /**
     * Define o atributo listaBoletoDDAGrupoCalculo
     */
    public void setListaBoletoDDAGrupoCalculo(List<BoletoDDAGrupoCalculo> listaBoletoDDAGrupoCalculo) {
        this.listaBoletoDDAGrupoCalculo = listaBoletoDDAGrupoCalculo;
    }

    /**
     * @return o atributo listaBoletoDDANotaFiscal
     */
    public List<BoletoDDANotaFiscal> getListaBoletoDDANotaFiscal() {
        return listaBoletoDDANotaFiscal;
    }

    /**
     * Define o atributo listaBoletoDDANotaFiscal
     */
    public void setListaBoletoDDANotaFiscal(List<BoletoDDANotaFiscal> listaBoletoDDANotaFiscal) {
        this.listaBoletoDDANotaFiscal = listaBoletoDDANotaFiscal;
    }

    /**
     * @return o atributo listaBoletoDDATextoInfo
     */
    public List<BoletoDDATextoInfo> getListaBoletoDDATextoInfo() {
        return listaBoletoDDATextoInfo;
    }

    /**
     * Define o atributo listaBoletoDDATextoInfo
     */
    public void setListaBoletoDDATextoInfo(List<BoletoDDATextoInfo> listaBoletoDDATextoInfo) {
        this.listaBoletoDDATextoInfo = listaBoletoDDATextoInfo;
    }

    /**
     * @return o atributo listaBoletoDDATerceiroAutorizado
     */
    public List<BoletoDDATerceiroAut> getListaBoletoDDATerceiroAutorizado() {
        return listaBoletoDDATerceiroAutorizado;
    }

    /**
     * Define o atributo listaBoletoDDATerceiroAutorizado
     */
    public void setListaBoletoDDATerceiroAutorizado(List<BoletoDDATerceiroAut> listaBoletoDDATerceiroAutorizado) {
        this.listaBoletoDDATerceiroAutorizado = listaBoletoDDATerceiroAutorizado;
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

    /**
     * Define o atributo numCpfCnpjBeneficiario
     */
    public void setNumCpfCnpjBeneficiario(String numCpfCnpjBeneficiario, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjBeneficiario = SDDAStringUtil.incluirZerosCPF(numCpfCnpjBeneficiario);
        } else {
            this.numCpfCnpjBeneficiario = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjBeneficiario);
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaBeneficiario void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaBeneficiario(String numCpfCnpjBeneficiario, String codTipoPessoaBeneficiario) {
        setCodTipoPessoaBeneficiario(codTipoPessoaBeneficiario);
        setNumCpfCnpjBeneficiario(numCpfCnpjBeneficiario, TipoPessoaEnum.getBy(codTipoPessoaBeneficiario));
    }

    /**
     * Define o atributo NumCpfCnpjBeneficiarioFinal
     */
    public void setNumCpfCnpjBeneficiarioFinal(String numCpfCnpjBeneficiarioFinal, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjBeneficiarioFinal = SDDAStringUtil.incluirZerosCPF(numCpfCnpjBeneficiarioFinal);
        } else {
            this.numCpfCnpjBeneficiarioFinal = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjBeneficiarioFinal);
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaBeneficiarioFinal void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaBeneficiarioFinal(String numCpfCnpjBeneficiarioFinal, String codTipoPessoaBeneficiarioFinal) {
        setCodTipoPessoaBeneficiarioFinal(codTipoPessoaBeneficiarioFinal);
        setNumCpfCnpjBeneficiarioFinal(numCpfCnpjBeneficiarioFinal, TipoPessoaEnum.getBy(codTipoPessoaBeneficiarioFinal));
    }

    /**
     * Define o atributo NumCpfCnpjBeneficiarioFinal
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjPagador = SDDAStringUtil.incluirZerosCPF(numCpfCnpjPagador);
        } else {
            this.numCpfCnpjPagador = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjPagador);
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaPagador void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaPagador(String numCpfCnpjPagador, String codTipoPessoaPagador) {
        setCodTipoPessoaPagador(codTipoPessoaPagador);
        setNumCpfCnpjPagador(numCpfCnpjPagador, TipoPessoaEnum.getBy(codTipoPessoaPagador));
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

    /**
     * Define o atributo numCpfCnpjAvalista
     */
    public void setNumCpfCnpjAvalista(String numCpfCnpjAvalista, TipoPessoaSacadorAvalistaEnum tipoPessoaSacadorAvalistaEnum) {
        switch (tipoPessoaSacadorAvalistaEnum) {
        case CPF:
            this.numCpfCnpjAvalista = SDDAStringUtil.incluirZerosCPF(numCpfCnpjAvalista);
            break;
        case CNPJ:
            this.numCpfCnpjAvalista = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjAvalista);
            break;
        default:
            this.numCpfCnpjAvalista = numCpfCnpjAvalista;
            break;
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaAvalista void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaAvalista(String numCpfCnpjAvalista, String codTipoPessoaAvalista) {
        setCodTipoPessoaDDAAvalista(codTipoPessoaAvalista);
        setNumCpfCnpjAvalista(numCpfCnpjAvalista, TipoPessoaSacadorAvalistaEnum.getBy(codTipoPessoaAvalista));
    }

    /**
     * @return o atributo bolProcessarMensagemRecebida
     */
    public Boolean getBolProcessarMensagemRecebida() {
        return bolProcessarMensagemRecebida;
    }

    /**
     * Define o atributo bolProcessarMensagemRecebida
     */
    public void setBolProcessarMensagemRecebida(Boolean bolProcessarMensagemRecebida) {
        this.bolProcessarMensagemRecebida = bolProcessarMensagemRecebida;
    }

    /**
     * @return o atributo bolProcessarMensagem106
     */
    public Boolean getBolProcessarMensagem106() {
        return bolProcessarMensagem106;
    }

    /**
     * Define o atributo bolProcessarMensagem106
     */
    public void setBolProcessarMensagem106(Boolean bolProcessarMensagem106) {
        this.bolProcessarMensagem106 = bolProcessarMensagem106;
    }

    /**
     * @return o atributo qtdPagamentoRestante
     */
    public Integer getQtdPagamentoRestante() {
        return qtdPagamentoRestante;
    }

    /**
     * Define o atributo qtdPagamentoRestante
     */
    public void setQtdPagamentoRestante(Integer qtdPagamentoRestante) {
        this.qtdPagamentoRestante = qtdPagamentoRestante;
    }

    /**
     * @return o atributo valorPago
     */
    public BigDecimal getValorPago() {
        return valorPago;
    }

    /**
     * Define o atributo valorPago
     */
    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
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
     * @return o atributo descBanco
     */
    public String getDescBanco() {
        return descBanco;
    }

    /**
     * Define o atributo descBanco
     */
    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

    /**
     * @return o atributo descModeloCalculo
     */
    public String getDescModeloCalculo() {
        return descModeloCalculo;
    }

    /**
     * Define o atributo descModeloCalculo
     */
    public void setDescModeloCalculo(String descModeloCalculo) {
        this.descModeloCalculo = descModeloCalculo;
    }

    /**
     * @return o atributo descAutorizacaoValorDivergente
     */
    public String getDescAutorizacaoValorDivergente() {
        return descAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo descAutorizacaoValorDivergente
     */
    public void setDescAutorizacaoValorDivergente(String descAutorizacaoValorDivergente) {
        this.descAutorizacaoValorDivergente = descAutorizacaoValorDivergente;
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
     * @return o atributo descSituacaoBoletoPagamento
     */
    public String getDescSituacaoBoletoPagamento() {
        return descSituacaoBoletoPagamento;
    }

    /**
     * Define o atributo descSituacaoBoletoPagamento
     */
    public void setDescSituacaoBoletoPagamento(String descSituacaoBoletoPagamento) {
        this.descSituacaoBoletoPagamento = descSituacaoBoletoPagamento;
    }

    /**
     * @return o atributo descTipoPessoaAvalista
     */
    public String getDescTipoPessoaAvalista() {
        return descTipoPessoaAvalista;
    }

    /**
     * Define o atributo descTipoPessoaAvalista
     */
    public void setDescTipoPessoaAvalista(String descTipoPessoaAvalista) {
        this.descTipoPessoaAvalista = descTipoPessoaAvalista;
    }

    /**
     * @return o atributo listaPagadorDDAAgregado
     */
    public List<PagadorDDAAgregado> getListaPagadorDDAAgregado() {
        return listaPagadorDDAAgregado;
    }

    /**
     * Define o atributo listaPagadorDDAAgregado
     */
    public void setListaPagadorDDAAgregado(List<PagadorDDAAgregado> listaPagadorDDAAgregado) {
        this.listaPagadorDDAAgregado = listaPagadorDDAAgregado;
    }

    /**
     * @return o atributo descTextoInformativoSemEspaco
     */
    public String getDescTextoInformativoSemEspaco() {
        this.descTextoInformativoSemEspaco = "";
        for (BoletoDDATextoInfo boletoDDATextoInfo : listaBoletoDDATextoInfo) {
            this.descTextoInformativoSemEspaco = this.descTextoInformativoSemEspaco + boletoDDATextoInfo.getDescTextoInformativo().trim();
        }
        return descTextoInformativoSemEspaco;
    }

    /**
     * Define o atributo descTextoInformativoSemEspaco
     */
    public void setDescTextoInformativoSemEspaco(String descTextoInformativoSemEspaco) {
        this.descTextoInformativoSemEspaco = descTextoInformativoSemEspaco;
    }

    /**
     * @return o atributo descTipoPagamento
     */
    public String getDescTipoPagamento() {
        return descTipoPagamento;
    }

    /**
     * Define o atributo descTipoPagamento
     */
    public void setDescTipoPagamento(String descTipoPagamento) {
        this.descTipoPagamento = descTipoPagamento;
    }

    /**
     * @return o atributo descCarteira
     */
    public String getDescCarteira() {
        return descCarteira;
    }

    /**
     * Define o atributo descCarteira
     */
    public void setDescCarteira(String descCarteira) {
        this.descCarteira = descCarteira;
    }

    /**
     * @return o atributo descEspecie
     */
    public String getDescEspecie() {
        return descEspecie;
    }

    /**
     * Define o atributo descEspecie
     */
    public void setDescEspecie(String descEspecie) {
        this.descEspecie = descEspecie;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoBanco() {
        return codPartDestinatario + " - " + descBanco;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoModeloCalculo() {
        return codTipoModeloCalculo + " - " + descModeloCalculo;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoAutorizacaoValorDivergente() {
        return codAutorizacaoValorDivergente + " - " + descAutorizacaoValorDivergente;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoSituacaoBoleto() {
        return codSituacaoBoleto + " - " + descSituacaoBoleto;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoSitBoletoPagamento() {
        if (codSituacaoBoletoPagamento != null && descSituacaoBoletoPagamento != null) {
            return codSituacaoBoletoPagamento + " - " + descSituacaoBoletoPagamento;
        }
        return "";
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoJuros() {

        return verificaNullJuros() ? this.tipoJuros.getId() + " - " + this.tipoJuros.getDescTipoJuros() : "";
    }

    /**
     * Método responsável por verificar atributos nulos da antiga BoletoDDAJuros equivalem o antigo objeto
     * 
     * @return Boolean
     */
    private Boolean verificaNullJuros() {

        return this.getDataJuros() != null && this.tipoJuros.getId() != null && this.getValorPercentualJuros() != null;

    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoMulta() {
        return verificaNullMulta() ? this.tipoMulta.getId() + " - " + this.tipoMulta.getDescTipoMulta() : "";
    }

    /**
     * Método responsável por verificar atributos nulos da antiga BoletoDDAMulta equivalem o antigo objeto
     * 
     * @return Boolean
     */
    private Boolean verificaNullMulta() {

        return this.getDataMulta() != null && this.tipoMulta.getId() != null && this.getValorPercentualMulta() != null;

    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoTipoPagamento() {
        return codTipoPagamento + " - " + descTipoPagamento;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoCarteira() {
        return idCarteira + " - " + descCarteira;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaoEspecie() {
        return idEspecieDocumento + " - " + descEspecie;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescricaSacadorAvalista() {
        if (codTipoPessoaDDAAvalista != null) {
            return codTipoPessoaDDAAvalista + " - " + descTipoPessoaAvalista;
        } else {
            return TipoPessoaSacadorAvalistaEnum.ISENTO_NAO_INFORMADO.getDescTipoPessoa();
        }
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getNumCpfCnpjBeneficiarioFormatado() {
        if (getNumCpfCnpjBeneficiario() != null) {
            if (getNumCpfCnpjBeneficiario().trim().length() == Constantes.TAMANHO_CPF) {
                return FormatadorUtil.formatar(getNumCpfCnpjBeneficiario().trim(), FormatadorUtil.MASCARA_CPF);
            } else {
                return FormatadorUtil.formatar(getNumCpfCnpjBeneficiario().trim(), FormatadorUtil.MASCARA_CNPJ);
            }
        }
        return "";
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getNumCepBeneficiarioFormatado() {
        if (getNumCepBeneficiario() != null && getNumCepBeneficiario().length() == Constantes.NUM_CEP) {
            return FormatadorUtil.formatar(getNumCepBeneficiario(), FormatadorUtil.MASCARA_CEP);
        }
        return "";
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getNumCpfCnpjPagadorFormatado() {
        if (getNumCpfCnpjPagador() != null) {
            if (getNumCpfCnpjPagador().trim().length() == Constantes.TAMANHO_CPF) {
                return FormatadorUtil.formatar(getNumCpfCnpjPagador().trim(), FormatadorUtil.MASCARA_CPF);
            } else {
                return FormatadorUtil.formatar(getNumCpfCnpjPagador().trim(), FormatadorUtil.MASCARA_CNPJ);
            }
        }
        return "";
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getNumCepPagadorFormatado() {
        if (getNumCepPagador() != null && getNumCepPagador().length() == Constantes.NUM_CEP) {
            return FormatadorUtil.formatar(getNumCepPagador(), FormatadorUtil.MASCARA_CEP);
        }
        return "";
    }

    /**
     * @return o atributo numRefAtualCadBoletoStr
     */
    public String getNumRefAtualCadBoletoStr() {
        return numRefAtualCadBoletoStr;
    }

    /**
     * Define o atributo numRefAtualCadBoletoStr
     */
    public void setNumRefAtualCadBoletoStr(String numRefAtualCadBoletoStr) {
        this.numRefAtualCadBoletoStr = numRefAtualCadBoletoStr;
    }

    /**
     * @return o atributo numIdentificadorBoletoCipStr
     */
    public String getNumIdentificadorBoletoCipStr() {
        return numIdentificadorBoletoCipStr;
    }

    /**
     * Define o atributo numIdentificadorBoletoCipStr
     */
    public void setNumIdentificadorBoletoCipStr(String numIdentificadorBoletoCipStr) {
        this.numIdentificadorBoletoCipStr = numIdentificadorBoletoCipStr;
    }

    /**
     * @return the dataJuros
     */
    public DateTimeDB getDataJuros() {
        return dataJuros;
    }

    /**
     * @param dataJuros the dataJuros to set
     */
    public void setDataJuros(DateTimeDB dataJuros) {
        this.dataJuros = dataJuros;
    }
    
    /**
	 * @return the tipoJuros
	 */
	public TipoJuros getTipoJuros() {
		return tipoJuros;
	}

	/**
	 * @param tipoJuros the tipoJuros to set
	 */
	public void setTipoJuros(TipoJuros tipoJuros) {
		this.tipoJuros = tipoJuros;
	}
    
    /**
     * @return the valorPercentualJuros
     */
    public BigDecimal getValorPercentualJuros() {
        return valorPercentualJuros;
    }

    /**
     * @param valorPercentualJuros the valorPercentualJuros to set
     */
    public void setValorPercentualJuros(BigDecimal valorPercentualJuros) {
        this.valorPercentualJuros = valorPercentualJuros;
    }

    /**
     * @return the dataMulta
     */
    public DateTimeDB getDataMulta() {
        return dataMulta;
    }

    /**
     * @param dataMulta the dataMulta to set
     */
    public void setDataMulta(DateTimeDB dataMulta) {
        this.dataMulta = dataMulta;
    }

    /**
     * @return the valorPercentualMulta
     */
    public BigDecimal getValorPercentualMulta() {
        return valorPercentualMulta;
    }

    /**
     * @param valorPercentualMulta the valorPercentualMulta to set
     */
    public void setValorPercentualMulta(BigDecimal valorPercentualMulta) {
        this.valorPercentualMulta = valorPercentualMulta;
    }

    /**
     * @return the dataDesconto1
     */
    public DateTimeDB getDataDesconto1() {
        return dataDesconto1;
    }

    /**
     * @param dataDesconto1 the dataDesconto1 to set
     */
    public void setDataDesconto1(DateTimeDB dataDesconto1) {
        this.dataDesconto1 = dataDesconto1;
    }
    
    /**
	 * @return the tipoDesconto1
	 */
	public TipoDesconto getTipoDesconto1() {
		return tipoDesconto1;
	}

	/**
	 * @param tipoDesconto1 the tipoDesconto1 to set
	 */
	public void setTipoDesconto1(TipoDesconto tipoDesconto1) {
		this.tipoDesconto1 = tipoDesconto1;
	}
    
    
    /**
     * @return the valorPercentualDesconto1
     */
    public BigDecimal getValorPercentualDesconto1() {
        return valorPercentualDesconto1;
    }

    /**
     * @param valorPercentualDesconto1 the valorPercentualDesconto1 to set
     */
    public void setValorPercentualDesconto1(BigDecimal valorPercentualDesconto1) {
        this.valorPercentualDesconto1 = valorPercentualDesconto1;
    }

    /**
     * @return the dataDesconto2
     */
    public DateTimeDB getDataDesconto2() {
        return dataDesconto2;
    }

    /**
     * @param dataDesconto2 the dataDesconto2 to set
     */
    public void setDataDesconto2(DateTimeDB dataDesconto2) {
        this.dataDesconto2 = dataDesconto2;
    }

    /**
     * @return the valorPercentualDesconto2
     */
    public BigDecimal getValorPercentualDesconto2() {
        return valorPercentualDesconto2;
    }

    /**
     * @param valorPercentualDesconto2 the valorPercentualDesconto2 to set
     */
    public void setValorPercentualDesconto2(BigDecimal valorPercentualDesconto2) {
        this.valorPercentualDesconto2 = valorPercentualDesconto2;
    }

    /**
     * @return the dataDesconto3
     */
    public DateTimeDB getDataDesconto3() {
        return dataDesconto3;
    }

    /**
     * @param dataDesconto3 the dataDesconto3 to set
     */
    public void setDataDesconto3(DateTimeDB dataDesconto3) {
        this.dataDesconto3 = dataDesconto3;
    }
    
    /**
     * @return the valorPercentualDesconto3
     */
    public BigDecimal getValorPercentualDesconto3() {
        return valorPercentualDesconto3;
    }

    /**
     * @param valorPercentualDesconto3 the valorPercentualDesconto3 to set
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

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    public Boolean isTipoDescontoIsento() {
        if(tipoDesconto1 == null) {
            return Boolean.TRUE;
        }
        return tipoDesconto1.getCodTipoDesconto() == null || tipoDesconto1.getCodTipoDesconto().equals(TipoDesconto.ISENTO);
    }

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    public Boolean isTipoJurosIsento() {
        if(tipoJuros == null) {
            return Boolean.TRUE;
        }
        return tipoJuros.getId() == null || tipoJuros.getId().equals(TipoJuros.ISENTO);
    }
    
	
	/**
	 * @return the descTipoDesconto1
	 */
	public String getDescTipoDesconto1() {	    
		return (tipoDesconto1 != null && tipoDesconto1.getCodTipoDesconto() != null) ?
		        tipoDesconto1.getCodTipoDesconto() + " - " + tipoDesconto1.getDescTipoDesconto() :
		        TIPO_DESCONTO_ISENTO;
				
	}

	/**
	 * @return the descTipoDesconto2
	 */
	public String getDescTipoDesconto2() {
		return (tipoDesconto2 != null && tipoDesconto2.getCodTipoDesconto() != null) ?
		        tipoDesconto2.getCodTipoDesconto() + " - " + tipoDesconto2.getDescTipoDesconto():		
				TIPO_DESCONTO_ISENTO;
	}
	
	/**
	 * @return the descTipoDesconto3
	 */
	public String getDescTipoDesconto3() {		
		return (tipoDesconto3 != null && tipoDesconto3.getCodTipoDesconto() != null)?
		        tipoDesconto3.getCodTipoDesconto() + " - " + tipoDesconto3.getDescTipoDesconto():
				TIPO_DESCONTO_ISENTO;
	}

	/**
	 * @param descTipoDesconto3 the descTipoDesconto3 to set
	 */
	public void setDescTipoDesconto3(String descTipoDesconto3) {
		this.tipoDesconto3.setDescTipoDesconto(descTipoDesconto3);
	}

	/**
	 * @return the tipoDesconto2
	 */
	public TipoDesconto getTipoDesconto2() {
		return tipoDesconto2;
	}

	/**
	 * @param tipoDesconto2 the tipoDesconto2 to set
	 */
	public void setTipoDesconto2(TipoDesconto tipoDesconto2) {
		this.tipoDesconto2 = tipoDesconto2;
	}

	/**
	 * @return the tipoDesconto3
	 */
	public TipoDesconto getTipoDesconto3() {
		return tipoDesconto3;
	}

	/**
	 * @param tipoDesconto3 the tipoDesconto3 to set
	 */
	public void setTipoDesconto3(TipoDesconto tipoDesconto3) {
		this.tipoDesconto3 = tipoDesconto3;
	}

	/**
	 * @return the tipoMulta
	 */
	public TipoMulta getTipoMulta() {
		return tipoMulta;
	}

	/**
	 * @param tipoMulta the tipoMulta to set
	 */
	public void setTipoMulta(TipoMulta tipoMulta) {
		this.tipoMulta = tipoMulta;
	}	
}