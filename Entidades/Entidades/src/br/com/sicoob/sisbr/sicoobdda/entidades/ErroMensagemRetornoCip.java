/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

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
 * ErroMensagemRetornoCip
 * 
 * @author george.Santos
 */
@Entity
@Table(name = "ERROMENSAGEMRETORNOCIP", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ErroMensagemRetornoCipVO")
public class ErroMensagemRetornoCip extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -4682503244237676373L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDERROMENSAGEMRETORNOCIP", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMENSAGEMDDA")
    private MensagemDDA mensagemDDA;

    @ManyToOne
    @JoinColumn(name = "CODTIPOERROCIP", nullable = false)
    private TipoErroCip tipoErroCip;

    @Column(nullable = false)
    private DateTimeDB dataHoraAtualizacao;

    @ManyToOne
    @JoinColumn(name = "CODTIPOTRATAMENTOERROCIP")
    private TipoTratamentoErroCip tipoTratamentoErroCip;

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
     * @return o atributo mensagemDDA
     */
    public MensagemDDA getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * Define o atributo mensagemDDA
     */
    public void setMensagemDDA(MensagemDDA mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return o atributo tipoErroCip
     */
    public TipoErroCip getTipoErroCip() {
        return tipoErroCip;
    }

    /**
     * Define o atributo tipoErroCip
     */
    public void setTipoErroCip(TipoErroCip tipoErroCip) {
        this.tipoErroCip = tipoErroCip;
    }

    /**
     * @return o atributo dataHoraAtualizacao
     */
    public DateTimeDB getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return o atributo tipoTratamentoErroCip
     */
    public TipoTratamentoErroCip getTipoTratamentoErroCip() {
        return tipoTratamentoErroCip;
    }

    /**
     * Define o atributo tipoTratamentoErroCip
     */
    public void setTipoTratamentoErroCip(TipoTratamentoErroCip tipoTratamentoErroCip) {
        this.tipoTratamentoErroCip = tipoTratamentoErroCip;
    }

}
