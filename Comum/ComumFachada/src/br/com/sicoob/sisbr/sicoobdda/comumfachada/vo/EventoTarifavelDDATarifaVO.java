package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EventoTarifavelDDATarifaVO
 * 
 * @author samuell.ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa")
public class EventoTarifavelDDATarifaVO extends BancoobVO {

    private Long id;
    private DateTimeDB dataFimVigencia;
    private DateTimeDB dataInicioVigencia;
    private int idInstituicao;
    private String idUsuario;
    private BigDecimal valorBancoob;
    private BigDecimal valorCIP;
    private EventoTarifavelDDAVO eventoTarifavelDDA;
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
    public int getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(int idInstituicao) {
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
