/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         HistoricoTermoPagador.java
 * Data Criação:    Mar 31, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TermoPagador
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "TERMOPAGADOR", schema = "DDA")
public class TermoPagador extends SicoobDDAEntidade {

    /**
     * 
     */
    private static final long serialVersionUID = 6080778589979476565L;

    @Id
    @Column(name = "IDTERMOPAGADOR", unique = true, nullable = false)
    private Short id;

    private Short bolFormatoHtml;

    private Date dataFimVigencia;

    private Date dataInicioVigencia;

    @Lob
    private String descConteudoTermoPagador;

    // bi-directional many-to-one association to TipoTermoPagador
    @ManyToOne
    @JoinColumn(name = "CODTIPOTERMOPAGADOR")
    private TipoTermoPagador tipoTermoPagador;

    public TermoPagador() {
    }

    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    /**
     * @return o atributo bolFormatoHtml
     */
    public short getBolFormatoHtml() {
        return bolFormatoHtml;
    }

    /**
     * Define o atributo bolFormatoHtml
     */
    public void setBolFormatoHtml(short bolFormatoHtml) {
        this.bolFormatoHtml = bolFormatoHtml;
    }

    /**
     * @return o atributo dataFimVigencia
     */
    public Date getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * Define o atributo dataFimVigencia
     */
    public void setDataFimVigencia(Date dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    /**
     * @return o atributo dataInicioVigencia
     */
    public Date getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * Define o atributo dataInicioVigencia
     */
    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    /**
     * @return o atributo descConteudoTermoPagador
     */
    public String getDescConteudoTermoPagador() {
        return descConteudoTermoPagador;
    }

    /**
     * Define o atributo descConteudoTermoPagador
     */
    public void setDescConteudoTermoPagador(String descConteudoTermoPagador) {
        this.descConteudoTermoPagador = descConteudoTermoPagador;
    }

    /**
     * @return o atributo tipoTermoPagador
     */
    public TipoTermoPagador getTipoTermoPagador() {
        return tipoTermoPagador;
    }

    /**
     * Define o atributo tipoTermoPagador
     */
    public void setTipoTermoPagador(TipoTermoPagador tipoTermoPagador) {
        this.tipoTermoPagador = tipoTermoPagador;
    }
}