package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EventoConsolidadoDDADetalhe é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "EventoConsolidadoDDADetalhe", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDADetalheVO")
public class EventoConsolidadoDDADetalhe extends SicoobDDAEntidade {

    private static final long serialVersionUID = -7963616123460983186L;

    @Id
    @Column(name = "IDEVENTOCONSOLIDADODDADETALHE", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDEVENTOCONSOLIDADODDA", nullable = false)
    private EventoConsolidadoDDA eventoConsolidadoDDA;

    @Column(nullable = false)
    private Integer qtdMensagemApurado;

    @Column(nullable = false)
    private Integer idInstituicao;

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
     * @return o atributo eventoConsolidadoDDA
     */
    public EventoConsolidadoDDA getEventoConsolidadoDDA() {
        return eventoConsolidadoDDA;
    }

    /**
     * Define o atributo eventoConsolidadoDDA
     */
    public void setEventoConsolidadoDDA(EventoConsolidadoDDA eventoConsolidadoDDA) {
        this.eventoConsolidadoDDA = eventoConsolidadoDDA;
    }

    /**
     * @return o atributo qtdMensagemApurado
     */
    public Integer getQtdMensagemApurado() {
        return qtdMensagemApurado;
    }

    /**
     * Define o atributo qtdMensagemApurado
     */
    public void setQtdMensagemApurado(Integer qtdMensagemApurado) {
        this.qtdMensagemApurado = qtdMensagemApurado;
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

}