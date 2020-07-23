package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * RateioDDALancamento é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "RateioDDALancamento", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RateioDDALancamentoVO")
public class RateioDDALancamento extends SicoobDDAEntidade {

    private static final long serialVersionUID = 5801989887244803374L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRATEIODDALANCAMENTO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDRATEIODDA", nullable = false)
    private RateioDDA rateioDDA;

    @Column(nullable = false)
    private Integer idInstituicao;

    @ManyToOne
    @JoinColumn(name = "CODEVENTOTARIFAVEL", nullable = false)
    private EventoTarifavelDDA eventoTarifavelDDA;

    @Column(nullable = false)
    private Integer qtdMensagemTotalRateio;

    @Column(nullable = false)
    private BigDecimal valorTotalRateio;

    @ManyToOne
    @JoinColumn(name = "CODSITUACAORATEIOLANCAMENTO", nullable = false)
    private SituacaoRateioLancamento situacaoRateioLancamento;

    @Column(nullable = false)
    private Integer idInstituicaoUsuarioLogado;

    @Column(nullable = false)
    private String idUsuarioLogado;

    private DateTimeDB dataMovimentoLoteLancamentoCCO;

    private DateTimeDB dataHoraLancamentoCCO;

    @Column(nullable = false)
    private Long numContaLancamentoCCO;

    private Long numSeqLancamentoCCO;

    private String codErroLancamentoCCO;

    @Column(length = 400)
    private String descErroLancamentoCCO;

    private Short codRetornoLancamentoCCO;

    @Column(length = 160)
    private String descCampoErroLancamentoCCO;

    private Integer idInstituicaoTransferenciaDebito;

    @ManyToOne
    @JoinColumn(name = "CODTIPOOPERACAOLOG", nullable = false)
    private TipoOperacaoLog tipoOperacaoLog;

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
     * @return o atributo rateioDDA
     */
    public RateioDDA getRateioDDA() {
        return rateioDDA;
    }

    /**
     * Define o atributo rateioDDA
     */
    public void setRateioDDA(RateioDDA rateioDDA) {
        this.rateioDDA = rateioDDA;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo eventoTarifavelDDA
     */
    public EventoTarifavelDDA getEventoTarifavelDDA() {
        return eventoTarifavelDDA;
    }

    /**
     * Define o atributo eventoTarifavelDDA
     */
    public void setEventoTarifavelDDA(EventoTarifavelDDA eventoTarifavelDDA) {
        this.eventoTarifavelDDA = eventoTarifavelDDA;
    }

    /**
     * @return o atributo qtdMensagemTotalRateio
     */
    public Integer getQtdMensagemTotalRateio() {
        return qtdMensagemTotalRateio;
    }

    /**
     * Define o atributo qtdMensagemTotalRateio
     */
    public void setQtdMensagemTotalRateio(Integer qtdMensagemTotalRateio) {
        this.qtdMensagemTotalRateio = qtdMensagemTotalRateio;
    }

    /**
     * @return o atributo valorTotalRateio
     */
    public BigDecimal getValorTotalRateio() {
        return valorTotalRateio;
    }

    /**
     * Define o atributo valorTotalRateio
     */
    public void setValorTotalRateio(BigDecimal valorTotalRateio) {
        this.valorTotalRateio = valorTotalRateio;
    }

    /**
     * @return o atributo situacaoRateioLancamento
     */
    public SituacaoRateioLancamento getSituacaoRateioLancamento() {
        return situacaoRateioLancamento;
    }

    /**
     * Define o atributo situacaoRateioLancamento
     */
    public void setSituacaoRateioLancamento(SituacaoRateioLancamento situacaoRateioLancamento) {
        this.situacaoRateioLancamento = situacaoRateioLancamento;
    }

    /**
     * @return o atributo idInstituicaoUsuarioLogado
     */
    public Integer getIdInstituicaoUsuarioLogado() {
        return idInstituicaoUsuarioLogado;
    }

    /**
     * Define o atributo idInstituicaoUsuarioLogado
     */
    public void setIdInstituicaoUsuarioLogado(Integer idInstituicaoUsuarioLogado) {
        this.idInstituicaoUsuarioLogado = idInstituicaoUsuarioLogado;
    }

    /**
     * @return o atributo idUsuarioLogado
     */
    public String getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    /**
     * Define o atributo idUsuarioLogado
     */
    public void setIdUsuarioLogado(String idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

    /**
     * @return o atributo dataMovimentoLoteLancamentoCCO
     */
    public DateTimeDB getDataMovimentoLoteLancamentoCCO() {
        return dataMovimentoLoteLancamentoCCO;
    }

    /**
     * Define o atributo dataMovimentoLoteLancamentoCCO
     */
    public void setDataMovimentoLoteLancamentoCCO(DateTimeDB dataMovimentoLoteLancamentoCCO) {
        this.dataMovimentoLoteLancamentoCCO = dataMovimentoLoteLancamentoCCO;
    }

    /**
     * @return o atributo dataHoraLancamentoCCO
     */
    public DateTimeDB getDataHoraLancamentoCCO() {
        return dataHoraLancamentoCCO;
    }

    /**
     * Define o atributo dataHoraLancamentoCCO
     */
    public void setDataHoraLancamentoCCO(DateTimeDB dataHoraLancamentoCCO) {
        this.dataHoraLancamentoCCO = dataHoraLancamentoCCO;
    }

    /**
     * @return o atributo numContaLancamentoCCO
     */
    public Long getNumContaLancamentoCCO() {
        return numContaLancamentoCCO;
    }

    /**
     * Define o atributo numContaLancamentoCCO
     */
    public void setNumContaLancamentoCCO(Long numContaLancamentoCCO) {
        this.numContaLancamentoCCO = numContaLancamentoCCO;
    }

    /**
     * @return o atributo numSeqLancamentoCCO
     */
    public Long getNumSeqLancamentoCCO() {
        return numSeqLancamentoCCO;
    }

    /**
     * Define o atributo numSeqLancamentoCCO
     */
    public void setNumSeqLancamentoCCO(Long numSeqLancamentoCCO) {
        this.numSeqLancamentoCCO = numSeqLancamentoCCO;
    }

    /**
     * @return o atributo codErroLancamentoCCO
     */
    public String getCodErroLancamentoCCO() {
        return codErroLancamentoCCO;
    }

    /**
     * Define o atributo codErroLancamentoCCO
     */
    public void setCodErroLancamentoCCO(String codErroLancamentoCCO) {
        this.codErroLancamentoCCO = codErroLancamentoCCO;
    }

    /**
     * @return o atributo descErroLancamentoCCO
     */
    public String getDescErroLancamentoCCO() {
        return descErroLancamentoCCO;
    }

    /**
     * Define o atributo descErroLancamentoCCO
     */
    public void setDescErroLancamentoCCO(String descErroLancamentoCCO) {
        this.descErroLancamentoCCO = descErroLancamentoCCO;
    }

    /**
     * @return o atributo codRetornoLancamentoCCO
     */
    public Short getCodRetornoLancamentoCCO() {
        return codRetornoLancamentoCCO;
    }

    /**
     * Define o atributo codRetornoLancamentoCCO
     */
    public void setCodRetornoLancamentoCCO(Short codRetornoLancamentoCCO) {
        this.codRetornoLancamentoCCO = codRetornoLancamentoCCO;
    }

    /**
     * @return o atributo descCampoErroLancamentoCCO
     */
    public String getDescCampoErroLancamentoCCO() {
        return descCampoErroLancamentoCCO;
    }

    /**
     * Define o atributo descCampoErroLancamentoCCO
     */
    public void setDescCampoErroLancamentoCCO(String descCampoErroLancamentoCCO) {
        this.descCampoErroLancamentoCCO = descCampoErroLancamentoCCO;
    }

    /**
     * @return o atributo idInstituicaoTransferenciaDebito
     */
    public Integer getIdInstituicaoTransferenciaDebito() {
        return idInstituicaoTransferenciaDebito;
    }

    /**
     * Define o atributo idInstituicaoTransferenciaDebito
     */
    public void setIdInstituicaoTransferenciaDebito(Integer idInstituicaoTransferenciaDebito) {
        this.idInstituicaoTransferenciaDebito = idInstituicaoTransferenciaDebito;
    }

    /**
     * @return o atributo tipoOperacaoLog
     */
    public TipoOperacaoLog getTipoOperacaoLog() {
        return tipoOperacaoLog;
    }

    /**
     * Define o atributo tipoOperacaoLog
     */
    public void setTipoOperacaoLog(TipoOperacaoLog tipoOperacaoLog) {
        this.tipoOperacaoLog = tipoOperacaoLog;
    }

}