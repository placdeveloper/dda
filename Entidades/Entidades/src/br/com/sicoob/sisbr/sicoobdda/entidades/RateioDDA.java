package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * RateioDDA é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "RateioDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.RateioDDAVO")
public class RateioDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -1774412890094351444L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRATEIODDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDEXTRATODDA")
    private ExtratoDDA extrato;

    private String descRateio;

    @ManyToOne
    @JoinColumn(name = "CODSITUACAORATEIO", nullable = false)
    private SituacaoRateio situacaoRateio;

    @Column(nullable = false)
    private DateTimeDB dataHoraInclusao;

    private DateTimeDB dataHoraAprovacao;

    private String idUsuarioAprovacao;

    private Integer idInstituicaoUsuarioAprovacao;

    @OneToMany(mappedBy = "rateioDDA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventoConsolidadoDDA> listaEventoConsolidadoDDA;

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
     * @return o atributo extrato
     */
    public ExtratoDDA getExtrato() {
        return extrato;
    }

    /**
     * Define o atributo extrato
     */
    public void setExtrato(ExtratoDDA extrato) {
        this.extrato = extrato;
    }

    /**
     * @return o atributo descRateio
     */
    public String getDescRateio() {
        return descRateio;
    }

    /**
     * Define o atributo descRateio
     */
    public void setDescRateio(String descRateio) {
        this.descRateio = descRateio;
    }

    /**
     * @return o atributo situacaoRateio
     */
    public SituacaoRateio getSituacaoRateio() {
        return situacaoRateio;
    }

    /**
     * Define o atributo situacaoRateio
     */
    public void setSituacaoRateio(SituacaoRateio situacaoRateio) {
        this.situacaoRateio = situacaoRateio;
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
     * @return o atributo dataHoraAprovacao
     */
    public DateTimeDB getDataHoraAprovacao() {
        return dataHoraAprovacao;
    }

    /**
     * Define o atributo dataHoraAprovacao
     */
    public void setDataHoraAprovacao(DateTimeDB dataHoraAprovacao) {
        this.dataHoraAprovacao = dataHoraAprovacao;
    }

    /**
     * @return o atributo idUsuarioAprovacao
     */
    public String getIdUsuarioAprovacao() {
        return idUsuarioAprovacao;
    }

    /**
     * Define o atributo idUsuarioAprovacao
     */
    public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
        this.idUsuarioAprovacao = idUsuarioAprovacao;
    }

    /**
     * @return o atributo idInstituicaoUsuarioAprovacao
     */
    public Integer getIdInstituicaoUsuarioAprovacao() {
        return idInstituicaoUsuarioAprovacao;
    }

    /**
     * Define o atributo idInstituicaoUsuarioAprovacao
     */
    public void setIdInstituicaoUsuarioAprovacao(Integer idInstituicaoUsuarioAprovacao) {
        this.idInstituicaoUsuarioAprovacao = idInstituicaoUsuarioAprovacao;
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

}