package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EventoTarifavelDDA
 * 
 * @author Samuell.Ramos
 */
@Entity
@Table(name = "EVENTOTARIFAVELDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDAVO")
public class EventoTarifavelDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4795342787604371447L;

    @Id
    @Column(unique = true, nullable = false)
    private Integer codEventoTarifavel;

    @Column(nullable = false)
    private Boolean bolAtivo;

    @Column(nullable = false)
    private Boolean bolTarifavel;

    @Column(nullable = false)
    private String descEventoTarifavelExtrato;

    @Column(nullable = false)
    private String descEventoTarifavelManual;

    @Column(name = "IDINSTITUICAOUSUARIOLOGADO", nullable = false)
    private Long idInstituicao;

    private Long idTipoHistoricoLancamentoCCO;

    @Column(name = "IDUSUARIOLOGADO", nullable = false)
    private String idUsuario;

    private Integer numLoteLancamentoCCO;

    @OneToMany(mappedBy = "eventoTarifavelDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoTarifavelDDATarifa> listaEventoTarifavelDDATarifa;

    @OneToMany(mappedBy = "eventoTarifavelDDA", fetch = FetchType.LAZY)
    private List<EventoConsolidadoDDA> listaEventoConsolidadoDDA;

    @ManyToOne
    @JoinColumn(name = "CODTIPOEVENTOTARIFAVEL", nullable = false)
    private TipoEventoTarifavel tipoEventoTarifavel;

    @ManyToOne
    @JoinColumn(name = "CODTIPOOPERACAOLOG", nullable = false)
    private TipoOperacaoLog tipoOperacaoLog;

    @ManyToOne
    @JoinColumn(name = "CODTIPOSERVICOTARIFAVEL", nullable = false)
    private TipoServicoTarifavel tipoServicoTarifavel;

    /**
     * 
     */
    public EventoTarifavelDDA() {
    }

    /**
     * @param codEventoTarifavel
     * @param descEventoTarifavelExtrato
     */
    public EventoTarifavelDDA(Integer codEventoTarifavel, String descEventoTarifavelExtrato) {
        this.codEventoTarifavel = codEventoTarifavel;
        this.descEventoTarifavelExtrato = descEventoTarifavelExtrato;
    }

    /**
     * @return o atributo codEventoTarifavel
     */
    public Integer getCodEventoTarifavel() {
        return codEventoTarifavel;
    }

    /**
     * Define o atributo codEventoTarifavel
     */
    public void setCodEventoTarifavel(Integer codEventoTarifavel) {
        this.codEventoTarifavel = codEventoTarifavel;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo bolTarifavel
     */
    public Boolean getBolTarifavel() {
        return bolTarifavel;
    }

    /**
     * Define o atributo bolTarifavel
     */
    public void setBolTarifavel(Boolean bolTarifavel) {
        this.bolTarifavel = bolTarifavel;
    }

    /**
     * @return o atributo descEventoTarifavelExtrato
     */
    public String getDescEventoTarifavelExtrato() {
        return descEventoTarifavelExtrato;
    }

    /**
     * Define o atributo descEventoTarifavelExtrato
     */
    public void setDescEventoTarifavelExtrato(String descEventoTarifavelExtrato) {
        this.descEventoTarifavelExtrato = descEventoTarifavelExtrato;
    }

    /**
     * @return o atributo descEventoTarifavelManual
     */
    public String getDescEventoTarifavelManual() {
        return descEventoTarifavelManual;
    }

    /**
     * Define o atributo descEventoTarifavelManual
     */
    public void setDescEventoTarifavelManual(String descEventoTarifavelManual) {
        this.descEventoTarifavelManual = descEventoTarifavelManual;
    }

    /**
     * @return o atributo idTipoHistoricoLancamentoCCO
     */
    public Long getIdTipoHistoricoLancamentoCCO() {
        return idTipoHistoricoLancamentoCCO;
    }

    /**
     * Define o atributo idTipoHistoricoLancamentoCCO
     */
    public void setIdTipoHistoricoLancamentoCCO(Long idTipoHistoricoLancamentoCCO) {
        this.idTipoHistoricoLancamentoCCO = idTipoHistoricoLancamentoCCO;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Long getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Long idInstituicao) {
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
     * @return o atributo numLoteLancamentoCCO
     */
    public Integer getNumLoteLancamentoCCO() {
        return numLoteLancamentoCCO;
    }

    /**
     * Define o atributo numLoteLancamentoCCO
     */
    public void setNumLoteLancamentoCCO(Integer numLoteLancamentoCCO) {
        this.numLoteLancamentoCCO = numLoteLancamentoCCO;
    }

    /**
     * @return o atributo listaEventoTarifavelDDATarifa
     */
    public List<EventoTarifavelDDATarifa> getListaEventoTarifavelDDATarifa() {
        return listaEventoTarifavelDDATarifa;
    }

    /**
     * Define o atributo listaEventoTarifavelDDATarifa
     */
    public void setListaEventoTarifavelDDATarifa(List<EventoTarifavelDDATarifa> listaEventoTarifavelDDATarifa) {
        this.listaEventoTarifavelDDATarifa = listaEventoTarifavelDDATarifa;
    }

    /**
     * @return o atributo listaEventoConsolidadoDDA
     */
    public List<EventoConsolidadoDDA> getListaEventoConsolidadoDDA() {
        return listaEventoConsolidadoDDA;
    }

    /**
     * Define o atributo listaEventoConsolidadoDDA
     */
    public void setListaEventoConsolidadoDDA(List<EventoConsolidadoDDA> listaEventoConsolidadoDDA) {
        this.listaEventoConsolidadoDDA = listaEventoConsolidadoDDA;
    }

    /**
     * @return o atributo tipoEventoTarifavel
     */
    public TipoEventoTarifavel getTipoEventoTarifavel() {
        return tipoEventoTarifavel;
    }

    /**
     * Define o atributo tipoEventoTarifavel
     */
    public void setTipoEventoTarifavel(TipoEventoTarifavel tipoEventoTarifavel) {
        this.tipoEventoTarifavel = tipoEventoTarifavel;
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

    /**
     * @return o atributo tipoServicoTarifavel
     */
    public TipoServicoTarifavel getTipoServicoTarifavel() {
        return tipoServicoTarifavel;
    }

    /**
     * Define o atributo tipoServicoTarifavel
     */
    public void setTipoServicoTarifavel(TipoServicoTarifavel tipoServicoTarifavel) {
        this.tipoServicoTarifavel = tipoServicoTarifavel;
    }

}