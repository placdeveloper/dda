package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
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

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EventoConsolidadoDDA
 * 
 * @author Samuell.Ramos
 */
@Entity
@Table(name = "EVENTOCONSOLIDADODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDAVO")
public class EventoConsolidadoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1530867154393797489L;

    @Id
    @Column(name = "IDEVENTOCONSOLIDADODDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODEVENTOTARIFAVEL", nullable = false)
    private EventoTarifavelDDA eventoTarifavelDDA;

    @Column(nullable = false)
    private DateTimeDB dataMovimento;
    
    @Column(nullable = false)
    private DateTimeDB dataHoraConsolidacao;

    @Column(nullable = false)
    private Integer qtdApuradoSicoob;

    private Integer qtdApuradoCIP;

    @Column(precision = 18, scale = 2)
    private BigDecimal valorTarifaCIP;

    @Column(precision = 18, scale = 2)
    private BigDecimal valorTarifaBancoob;

    @ManyToOne
    @JoinColumn(name = "IDRATEIODDA", nullable = false)
    private RateioDDA rateioDDA;

    @Column(length = 40)
    private String idUsuarioInclusaoRateio;

    private Integer idInstituicaoUsuarioInclusaoRateio;

    private DateTimeDB dataHoraInclusaoRateio;

    @OneToMany(mappedBy = "eventoConsolidadoDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoConsolidadoDDADetalhe> listaEventoConsolidadoDDADetalhe;

    public EventoConsolidadoDDA() {
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
     * @return
     */
    public DateTimeDB getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * @param dataMovimento
	 */
	public void setDataMovimento(DateTimeDB dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
     * @return o atributo qtdApuradoSicoob
     */
    public Integer getQtdApuradoSicoob() {
        return qtdApuradoSicoob;
    }

    /**
     * Define o atributo qtdApuradoSicoob
     */
    public void setQtdApuradoSicoob(Integer qtdApuradoSicoob) {
        this.qtdApuradoSicoob = qtdApuradoSicoob;
    }

    /**
     * @return o atributo qtdApuradoCIP
     */
    public Integer getQtdApuradoCIP() {
        return qtdApuradoCIP;
    }

    /**
     * Define o atributo qtdApuradoCIP
     */
    public void setQtdApuradoCIP(Integer qtdApuradoCIP) {
        this.qtdApuradoCIP = qtdApuradoCIP;
    }

    /**
     * @return o atributo valorTarifaCIP
     */
    public BigDecimal getValorTarifaCIP() {
        return valorTarifaCIP;
    }

    /**
     * Define o atributo valorTarifaCIP
     */
    public void setValorTarifaCIP(BigDecimal valorTarifaCIP) {
        this.valorTarifaCIP = valorTarifaCIP;
    }

    /**
     * @return o atributo valorTarifaBancoob
     */
    public BigDecimal getValorTarifaBancoob() {
        return valorTarifaBancoob;
    }

    /**
     * Define o atributo valorTarifaBancoob
     */
    public void setValorTarifaBancoob(BigDecimal valorTarifaBancoob) {
        this.valorTarifaBancoob = valorTarifaBancoob;
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
     * @return o atributo idUsuarioInclusaoRateio
     */
    public String getIdUsuarioInclusaoRateio() {
        return idUsuarioInclusaoRateio;
    }

    /**
     * Define o atributo idUsuarioInclusaoRateio
     */
    public void setIdUsuarioInclusaoRateio(String idUsuarioInclusaoRateio) {
        this.idUsuarioInclusaoRateio = idUsuarioInclusaoRateio;
    }

    /**
     * @return o atributo idInstituicaoUsuarioInclusaoRateio
     */
    public Integer getIdInstituicaoUsuarioInclusaoRateio() {
        return idInstituicaoUsuarioInclusaoRateio;
    }

    /**
     * Define o atributo idInstituicaoUsuarioInclusaoRateio
     */
    public void setIdInstituicaoUsuarioInclusaoRateio(Integer idInstituicaoUsuarioInclusaoRateio) {
        this.idInstituicaoUsuarioInclusaoRateio = idInstituicaoUsuarioInclusaoRateio;
    }

    /**
     * @return o atributo dataHoraInclusaoRateio
     */
    public DateTimeDB getDataHoraInclusaoRateio() {
        return dataHoraInclusaoRateio;
    }

    /**
     * Define o atributo dataHoraInclusaoRateio
     */
    public void setDataHoraInclusaoRateio(DateTimeDB dataHoraInclusaoRateio) {
        this.dataHoraInclusaoRateio = dataHoraInclusaoRateio;
    }

    /**
     * @return o atributo listaEventoConsolidadoDDADetalhe
     */
    public List<EventoConsolidadoDDADetalhe> getListaEventoConsolidadoDDADetalhe() {
        return listaEventoConsolidadoDDADetalhe;
    }

    /**
     * Define o atributo listaEventoConsolidadoDDADetalhe
     */
    public void setListaEventoConsolidadoDDADetalhe(List<EventoConsolidadoDDADetalhe> listaEventoConsolidadoDDADetalhe) {
        this.listaEventoConsolidadoDDADetalhe = listaEventoConsolidadoDDADetalhe;
    }

	/**
	 * @return the dataHoraConsolidacao
	 */
	public DateTimeDB getDataHoraConsolidacao() {
		return dataHoraConsolidacao;
	}

	/**
	 * @param dataHoraConsolidacao the dataHoraConsolidacao to set
	 */
	public void setDataHoraConsolidacao(DateTimeDB dataHoraConsolidacao) {
		this.dataHoraConsolidacao = dataHoraConsolidacao;
	}


}