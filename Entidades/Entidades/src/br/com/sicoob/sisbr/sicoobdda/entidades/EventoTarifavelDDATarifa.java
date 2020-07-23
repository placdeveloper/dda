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
 * The persistent class for the EVENTOTARIFAVELDDATARIFA database table.
 * 
 */
@Entity
@Table(name = "EVENTOTARIFAVELDDATARIFA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDATarifaVO")
public class EventoTarifavelDDATarifa extends SicoobDDAEntidade {

    private static final long serialVersionUID = 3875167667108814257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEVENTOTARIFAVELDDATARIFA", unique = true, nullable = false)
    private Long id;

    private DateTimeDB dataFimVigencia;

    @Column(nullable = false)
    private DateTimeDB dataInicioVigencia;

    @Column(name = "IDINSTITUICAOUSUARIOLOGADO", nullable = false)
    private Integer idInstituicao;

    @Column(name = "IDUSUARIOLOGADO", nullable = false)
    private String idUsuario;

    @Column(nullable = false)
    private BigDecimal valorBancoob;

    @Column(nullable = false)
    private BigDecimal valorCIP;

    // bi-directional many-to-one association to EventoTarifavelDDA
    @ManyToOne
    @JoinColumn(name = "CODEVENTOTARIFAVEL", nullable = false)
    private EventoTarifavelDDA eventoTarifavelDDA;

    // bi-directional many-to-one association to TipoOperacaoLog
    @ManyToOne
    @JoinColumn(name = "CODTIPOOPERACAOLOG", nullable = false)
    private TipoOperacaoLog tipoOperacaoLog;

    public EventoTarifavelDDATarifa() {
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
     * @return o atributo dataFimVigencia
     */
    public DateTimeDB getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * Define o atributo dataFimVigencia
     */
    public void setDataFimVigencia(DateTimeDB dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    /**
     * @return o atributo dataInicioVigencia
     */
    public DateTimeDB getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * Define o atributo dataInicioVigencia
     */
    public void setDataInicioVigencia(DateTimeDB dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
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
     * @return o atributo idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define o atributo idUsuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return o atributo valorBancoob
     */
    public BigDecimal getValorBancoob() {
        return valorBancoob;
    }

    /**
     * Define o atributo valorBancoob
     */
    public void setValorBancoob(BigDecimal valorBancoob) {
        this.valorBancoob = valorBancoob;
    }

    /**
     * @return o atributo valorCIP
     */
    public BigDecimal getValorCIP() {
        return valorCIP;
    }

    /**
     * Define o atributo valorCIP
     */
    public void setValorCIP(BigDecimal valorCIP) {
        this.valorCIP = valorCIP;
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