/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         HistoricoContingencia.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * HistoricoContingencia
 * 
 * @author Danilo.Barros
 */

@Entity
@Table(name = "HISTORICOCONTINGENCIA", schema = "DDA")
public class HistoricoContingencia extends SicoobDDAEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHISTORICOCONTINGENCIA", unique = true, nullable = false)
    private Long id;

    @Column(name = "DATAHORASITUACAO", nullable = false)
    private DateTimeDB dataHoraSituacao;

    @ManyToOne
    @JoinColumn(name = "CODTIPOCONTINGENCIA", nullable = false)
    private TipoContingencia tipoContingencia;

    @Column(name = "IDUSUARIO", nullable = false)
    private String idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDHISTORICOCONTINGENCIAHABILITACAO")
    private HistoricoContingencia habilitacaoContingencia;

    @Column(name = "BOLCONTINGENCIAHABILITADA", nullable = false)
    private Boolean contingenciaHabilitada;

    @Transient
    private String periodoIndisponibilidade;

    /**
     * @param
     */
    public HistoricoContingencia() {
        super();
    }

    /**
     * @param dataSituacao
     * @param tipoContingencia
     * @param idUsuario
     * @param habilitacaoContingencia
     * @param situacaoContingencia
     */
    public HistoricoContingencia(DateTimeDB dataHoraSituacao, TipoContingencia tipoContingencia, String idUsuario, HistoricoContingencia habilitacaoContingencia,
            Boolean contingenciaHabilitada) {
        super();
        this.dataHoraSituacao = dataHoraSituacao;
        this.tipoContingencia = tipoContingencia;
        this.idUsuario = idUsuario;
        this.setHabilitacaoContingencia(habilitacaoContingencia);
        this.contingenciaHabilitada = contingenciaHabilitada;
    }

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Método responsável por 
     * @param id void
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo dataHoraSituacao
     */
    public DateTimeDB getDataHoraSituacao() {
        return dataHoraSituacao;
    }

    /**
     * Define o atributo dataHoraSituacao
     */
    public void setDataHoraSituacao(DateTimeDB dataHoraSituacao) {
        this.dataHoraSituacao = dataHoraSituacao;
    }

    /**
     * @return o atributo tipoContingencia
     */
    public TipoContingencia getTipoContingencia() {
        return tipoContingencia;
    }

    /**
     * Define o atributo tipoContingencia
     */
    public void setTipoContingencia(TipoContingencia tipoContingencia) {
        this.tipoContingencia = tipoContingencia;
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
     * Define o atributo habilitacaoContingencia
     */
    public HistoricoContingencia getHabilitacaoContingencia() {
        return habilitacaoContingencia;
    }

    /**
     * Define o atributo habilitacaoContingencia
     */
    public void setHabilitacaoContingencia(HistoricoContingencia habilitacaoContingencia) {
        this.habilitacaoContingencia = habilitacaoContingencia;
    }

    /**
     * @return o atributo contingenciaHabilitada
     */
    public Boolean getContingenciaHabilitada() {
        return contingenciaHabilitada;
    }

    /**
     * Define o atributo contingenciaHabilitada
     */
    public void setContingenciaHabilitada(Boolean contingenciaHabilitada) {
        this.contingenciaHabilitada = contingenciaHabilitada;
    }

    /**
     * @return o atributo periodoIndisponibilidade
     */
    public String getPeriodoIndisponibilidade() {
        return periodoIndisponibilidade;
    }

    /**
     * Define o atributo periodoIndisponibilidade
     */
    public void setPeriodoIndisponibilidade(String periodoIndisponibilidade) {
        this.periodoIndisponibilidade = periodoIndisponibilidade;
    }

    /**
     * @return o atributo contingenciaHabilitada
     */
    public Boolean isContingenciaHabilitada() {
        return contingenciaHabilitada;
    }

}
