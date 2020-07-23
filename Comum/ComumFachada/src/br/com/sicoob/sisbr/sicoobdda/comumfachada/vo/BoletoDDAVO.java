package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDAVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA")
public class BoletoDDAVO extends BancoobVO {

    private Long id;

    private Long numIdentificadorBoletoCip;

    private Long numRefAtualCadBoleto;

    private Long numSeqAtualCadBoleto;

    private Long numRefAtualCadAceite;

    private Long numSeqAtualAceite;

    private DateTime dataHoraSituacaoBoleto;

    private String codIspbPartDestinatario;

    private String codPartDestinatario;

    private String codTipoPessoaBeneficiario;

    private String numCpfCnpjBeneficiario;

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

    private String codTipoPessoaPagador;
    private String numCpfCnpjPagador;

    private String nomePagador;

    private String nomeFantasiaPagador;

    private String descLogradouroPagador;

    private String descCidadePagador;

    private String siglaUfPagador;

    private String numCepPagador;

    private String codTipoPessoaDDAAvalista;

    private String numCpfCnpjAvalista;

    private String nomeAvalista;

    private Integer idCarteira;

    private String codMoeda;

    private String numNossoNumero;

    private String numCodigoBarra;

    private String numLinhaDigitavel;

    private DateTime dataVencimento;

    private BigDecimal valorBoleto;

    private String numDocumento;

    private Integer idEspecieDocumento;

    private DateTime dataEmissao;

    private Integer numDiasProtesto;

    private DateTime dataLimitePagamento;

    private Integer codTipoPagamento;

    private Integer numParcela;

    private Integer qtdTotalParcela;

    private Boolean bolTituloNegociado;

    private Boolean bolBloqueioPagamento;

    private Boolean bolPagamentoParcial;

    private BigDecimal valorAbatimento;

    private String codIndicadorValorMinimo;

    private BigDecimal valorMinimo;

    private String codIndicadorValorMaximo;

    private BigDecimal valorMaximo;

    private String codTipoModeloCalculo;

    private String codAutorizacaoValorDivergente;

    private Integer qtdPagamentoParcialReg;

    private BigDecimal valorSaldoPagamento;

    private String codSituacaoBoletoPagamento;

    private Integer qtdPagamentoParcial;

    private Boolean bolAceite;

    private DateTime dataHoraSituacaoAceite;

    private DateTime dataHoraUltimaAtualizacao;

    private Integer codSituacaoBoleto;

    private DateTime dataHoraInclusao;

    private String descBanco;

    private String descModeloCalculo;

    private String descAutorizacaoValorDivergente;

    private String descSituacaoBoleto;

    private String descSituacaoBoletoPagamento;

    private String descTipoPessoaAvalista;

    private List<BoletoDDABaixaEfetVO> listaBoletoDDABaixaEfet;

    private List<BoletoDDABaixaOperVO> listaBoletoDDABaixaOper;

    private List<BoletoDDATerceiroAutVO> listaBoletoDDATerceiroAutorizado;

    private List<BoletoDDATextoInfoVO> listaBoletoDDATextoInfo;

    private String descTextoInformativoSemEspaco;

    private String descTipoPagamento;

    private String descCarteira;

    private String descEspecie;

    private List<PagadorDDAAgregadoVO> listaPagadorDDAAgregado;

    private String numRefAtualCadBoletoStr;

    private String numIdentificadorBoletoCipStr;

    // BoletoDDAJuros
    private DateTimeDB dataJuros;    
    
    private TipoJurosVO tipoJuros;
    
    private BigDecimal valorPercentualJuros;

    // BoletoDDAMulta

    private DateTimeDB dataMulta;

    private TipoMultaVO tipoMulta;

    private BigDecimal valorPercentualMulta;

    // BoletoDDADesconto

    private DateTimeDB dataDesconto1;

    private TipoDescontoVO tipoDesconto1;

    private BigDecimal valorPercentualDesconto1;

    private DateTimeDB dataDesconto2;

    private TipoDescontoVO tipoDesconto2;
    
    private BigDecimal valorPercentualDesconto2;

    private DateTimeDB dataDesconto3;

    private TipoDescontoVO tipoDesconto3;

    private BigDecimal valorPercentualDesconto3;

    private String numCodBarrasCampoLivre;

    // End

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
    public DateTime getDataHoraSituacaoBoleto() {
        return dataHoraSituacaoBoleto;
    }

    /**
     * Define o atributo dataHoraSituacaoBoleto
     */
    public void setDataHoraSituacaoBoleto(DateTime dataHoraSituacaoBoleto) {
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
    public DateTime getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    /**
     * Define o atributo dataLimitePagamento
     */
    public void setDataLimitePagamento(DateTime dataLimitePagamento) {
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
    public DateTime getDataHoraSituacaoAceite() {
        return dataHoraSituacaoAceite;
    }

    /**
     * Define o atributo dataHoraSituacaoAceite
     */
    public void setDataHoraSituacaoAceite(DateTime dataHoraSituacaoAceite) {
        this.dataHoraSituacaoAceite = dataHoraSituacaoAceite;
    }

    /**
     * @return o atributo dataHoraUltimaAtualizacao
     */
    public DateTime getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * Define o atributo dataHoraUltimaAtualizacao
     */
    public void setDataHoraUltimaAtualizacao(DateTime dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
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
     * @return o atributo dataHoraInclusao
     */
    public DateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTime dataHoraInclusao) {
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
     * @return o atributo listaBoletoDDATerceiroAutorizado
     */
    public List<BoletoDDATerceiroAutVO> getListaBoletoDDATerceiroAutorizado() {
        return listaBoletoDDATerceiroAutorizado;
    }

    /**
     * Define o atributo listaBoletoDDATerceiroAutorizado
     */
    public void setListaBoletoDDATerceiroAutorizado(List<BoletoDDATerceiroAutVO> listaBoletoDDATerceiroAutorizado) {
        this.listaBoletoDDATerceiroAutorizado = listaBoletoDDATerceiroAutorizado;
    }

    /**
     * @return o atributo listaBoletoDDATextoInfo
     */
    public List<BoletoDDATextoInfoVO> getListaBoletoDDATextoInfo() {
        return listaBoletoDDATextoInfo;
    }

    /**
     * Define o atributo listaBoletoDDATextoInfo
     */
    public void setListaBoletoDDATextoInfo(List<BoletoDDATextoInfoVO> listaBoletoDDATextoInfo) {
        this.listaBoletoDDATextoInfo = listaBoletoDDATextoInfo;
    }

    /**
     * @return o atributo listaBoletoDDABaixaEfet
     */
    public List<BoletoDDABaixaEfetVO> getListaBoletoDDABaixaEfet() {
        return listaBoletoDDABaixaEfet;
    }

    /**
     * Define o atributo listaBoletoDDABaixaEfet
     */
    public void setListaBoletoDDABaixaEfet(List<BoletoDDABaixaEfetVO> listaBoletoDDABaixaEfet) {
        this.listaBoletoDDABaixaEfet = listaBoletoDDABaixaEfet;
    }

    /**
     * @return o atributo listaBoletoDDABaixaOper
     */
    public List<BoletoDDABaixaOperVO> getListaBoletoDDABaixaOper() {
        return listaBoletoDDABaixaOper;
    }

    /**
     * Define o atributo listaBoletoDDABaixaOper
     */
    public void setListaBoletoDDABaixaOper(List<BoletoDDABaixaOperVO> listaBoletoDDABaixaOper) {
        this.listaBoletoDDABaixaOper = listaBoletoDDABaixaOper;
    }

    /**
     * @return o atributo descTextoInformativoSemEspaco
     */
    public String getDescTextoInformativoSemEspaco() {
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
     * @return o atributo listaPagadorDDAAgregado
     */
    public List<PagadorDDAAgregadoVO> getListaPagadorDDAAgregado() {
        return listaPagadorDDAAgregado;
    }

    /**
     * Define o atributo listaPagadorDDAAgregado
     */
    public void setListaPagadorDDAAgregado(List<PagadorDDAAgregadoVO> listaPagadorDDAAgregado) {
        this.listaPagadorDDAAgregado = listaPagadorDDAAgregado;
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
	public TipoJurosVO getTipoJuros() {
		return tipoJuros;
	}

	/**
	 * @param tipoJuros the tipoJuros to set
	 */
	public void setTipoJuros(TipoJurosVO tipoJuros) {
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
	 * @return the tipoMulta
	 */
	public TipoMultaVO getTipoMulta() {
		return this.tipoMulta;
	}

	/**
	 * @param tipoMulta the tipoMulta to set
	 */
	public void setTipoMulta(TipoMultaVO tipoMulta) {
		this.tipoMulta = tipoMulta;
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
	public TipoDescontoVO getTipoDesconto1() {
		return tipoDesconto1;
	}

	/**
	 * @param tipoDesconto1 the tipoDesconto1 to set
	 */
	public void setTipoDesconto1(TipoDescontoVO tipoDesconto1) {
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
	 * @return the tipoDesconto2
	 */
	public TipoDescontoVO getTipoDesconto2() {
		return tipoDesconto2;
	}

	/**
	 * @param tipoDesconto2 the tipoDesconto2 to set
	 */
	public void setTipoDesconto2(TipoDescontoVO tipoDesconto2) {
		this.tipoDesconto2 = tipoDesconto2;
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
	 * @return the tipoDesconto3
	 */
	public TipoDescontoVO getTipoDesconto3() {
		return tipoDesconto3;
	}

	/**
	 * @param tipoDesconto3 the tipoDesconto3 to set
	 */
	public void setTipoDesconto3(TipoDescontoVO tipoDesconto3) {
		this.tipoDesconto3 = tipoDesconto3;
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
		
}
