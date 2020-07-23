package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento")
public class RateioDDALancamentoVO extends BancoobVO {

    private Long id;
    private RateioDDAVO rateioDDA;
    private Integer idInstituicao;
    private EventoTarifavelDDAVO eventoTarifavelDDA;
    private Integer qtdMensagemTotalRateio;
    private BigDecimal valorTotalRateio;
    private SituacaoRateioLancamentoVO situacaoRateioLancamento;
    private Integer idInstituicaoUsuarioLogado;
    private String idUsuarioLogado;
    private DateTime dataMovimentoLoteLancamentoCCO;
    private DateTime dataHoraLancamentoCCO;
    private Long numContaLancamentoCCO;
    private Long numSeqLancamentoCCO;
    private String codErroLancamentoCCO;
    private String descErroLancamentoCCO;
    private Short codRetornoLancamentoCCO;
    private String descCampoErroLancamentoCCO;
    private Integer idInstituicaoTransferenciaDebito;
    private TipoOperacaoLogVO tipoOperacaoLog;

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
    public RateioDDAVO getRateioDDA() {
        return rateioDDA;
    }

    /**
     * Define o atributo rateioDDA
     */
    public void setRateioDDA(RateioDDAVO rateioDDA) {
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
    public EventoTarifavelDDAVO getEventoTarifavelDDA() {
        return eventoTarifavelDDA;
    }

    /**
     * Define o atributo eventoTarifavelDDA
     */
    public void setEventoTarifavelDDA(EventoTarifavelDDAVO eventoTarifavelDDA) {
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
    public SituacaoRateioLancamentoVO getSituacaoRateioLancamento() {
        return situacaoRateioLancamento;
    }

    /**
     * Define o atributo situacaoRateioLancamento
     */
    public void setSituacaoRateioLancamento(SituacaoRateioLancamentoVO situacaoRateioLancamento) {
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
    public DateTime getDataMovimentoLoteLancamentoCCO() {
        return dataMovimentoLoteLancamentoCCO;
    }

    /**
     * Define o atributo dataMovimentoLoteLancamentoCCO
     */
    public void setDataMovimentoLoteLancamentoCCO(DateTime dataMovimentoLoteLancamentoCCO) {
        this.dataMovimentoLoteLancamentoCCO = dataMovimentoLoteLancamentoCCO;
    }

    /**
     * @return o atributo dataHoraLancamentoCCO
     */
    public DateTime getDataHoraLancamentoCCO() {
        return dataHoraLancamentoCCO;
    }

    /**
     * Define o atributo dataHoraLancamentoCCO
     */
    public void setDataHoraLancamentoCCO(DateTime dataHoraLancamentoCCO) {
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
    public TipoOperacaoLogVO getTipoOperacaoLog() {
        return tipoOperacaoLog;
    }

    /**
     * Define o atributo tipoOperacaoLog
     */
    public void setTipoOperacaoLog(TipoOperacaoLogVO tipoOperacaoLog) {
        this.tipoOperacaoLog = tipoOperacaoLog;
    }

}
