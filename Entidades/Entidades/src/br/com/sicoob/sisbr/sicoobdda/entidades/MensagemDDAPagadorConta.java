package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * MensagemDDAPagadorConta
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "MensagemDDAPagadorConta", schema = "DDA")
public class MensagemDDAPagadorConta extends SicoobDDAEntidade {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDAPagadorConta", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDAPagador mensagemDDAPagador;

    @Column(nullable = false)
    private Integer numAgencia;

    @Column(nullable = false)
    private BigDecimal numContaCorrente;

    @Column(nullable = false)
    private String codTipoOperacao;

    private DateTimeDB dataHoraAdesao;

    /**
     * 
     */
    public MensagemDDAPagadorConta() {
    }

    /**
     * @param mensagemDDAPagador
     * @param numAgencia
     * @param numContaCorrente
     * @param codTipoOperacao
     * @param dataHoraAdesao
     */
    public MensagemDDAPagadorConta(MensagemDDAPagador mensagemDDAPagador, Integer numAgencia, BigDecimal numContaCorrente, String codTipoOperacao, DateTimeDB dataHoraAdesao) {
        super();
        this.mensagemDDAPagador = mensagemDDAPagador;
        this.numAgencia = numAgencia;
        this.numContaCorrente = numContaCorrente;
        this.codTipoOperacao = codTipoOperacao;
        this.dataHoraAdesao = dataHoraAdesao;
    }

    /**
     * @param id
     * @param idMensagemDDAPagador
     * @param numAgencia
     * @param numContaCorrente
     * @param codTipoOperacao
     * @param dataHoraAdesao
     */
    public MensagemDDAPagadorConta(Long id, Long idMensagemDDAPagador, Integer numAgencia, BigDecimal numContaCorrente, String codTipoOperacao, Date dataHoraAdesao) {
        super();
        this.id = id;
        this.mensagemDDAPagador = new MensagemDDAPagador(idMensagemDDAPagador);
        this.numAgencia = numAgencia;
        this.numContaCorrente = numContaCorrente;
        this.codTipoOperacao = codTipoOperacao;
        this.dataHoraAdesao = dataHoraAdesao != null ? new DateTimeDB(dataHoraAdesao.getTime()) : null;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo mensagemDDAPagador
     */
    public MensagemDDAPagador getMensagemDDAPagador() {
        return mensagemDDAPagador;
    }

    /**
     * Define o atributo mensagemDDAPagador
     */
    public void setMensagemDDAPagador(MensagemDDAPagador mensagemDDAPagador) {
        this.mensagemDDAPagador = mensagemDDAPagador;
    }

    /**
     * @return o atributo numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * Define o atributo numAgencia
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return o atributo numContaCorrente
     */
    public BigDecimal getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * Define o atributo numContaCorrente
     */
    public void setNumContaCorrente(BigDecimal numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return o atributo codTipoOperacao
     */
    public String getCodTipoOperacao() {
        return codTipoOperacao;
    }

    /**
     * Define o atributo codTipoOperacao
     */
    public void setCodTipoOperacao(String codTipoOperacao) {
        this.codTipoOperacao = codTipoOperacao;
    }

    /**
     * @return o atributo dataHoraAdesao
     */
    public DateTimeDB getDataHoraAdesao() {
        return dataHoraAdesao;
    }

    /**
     * Define o atributo dataHoraAdesao
     */
    public void setDataHoraAdesao(DateTimeDB dataHoraAdesao) {
        this.dataHoraAdesao = dataHoraAdesao;
    }
}
