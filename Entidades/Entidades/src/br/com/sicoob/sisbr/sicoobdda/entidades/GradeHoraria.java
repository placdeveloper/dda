/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         GradeHoraria.java
 * Data Criação:    Ago 09, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

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
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * GradeHoraria
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "GRADEHORARIA", schema = "DDA")
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.GradeHorariaVO")
public class GradeHoraria extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDGRADEHORARIA", unique = true, nullable = false)
    private Long id;

    private DateTimeDB dataReferencia;

    private DateTimeDB dataHoraAbertura;

    private DateTimeDB dataHoraFechamento;

    @ManyToOne
    @JoinColumn(name = "CODTIPOGRADEHORARIA")
    private TipoGradeHoraria tipoGradeHoraria;

    /**
     * 
     */
    public GradeHoraria() {
        super();
    }

    /**
     * @param id
     * @param dataReferencia
     * @param dataHoraAbertura
     * @param dataHoraFechamento
     * @param codTipoGradeHoraria
     */
    public GradeHoraria(Long id, Date dataReferencia, Date dataHoraAbertura, Date dataHoraFechamento, String codTipoGradeHoraria) {
        super();
        this.id = id;
        this.dataReferencia = new DateTimeDB(dataReferencia.getTime());
        this.dataHoraAbertura = new DateTimeDB(dataHoraAbertura.getTime());
        this.dataHoraFechamento = new DateTimeDB(dataHoraFechamento.getTime());
        this.tipoGradeHoraria = new TipoGradeHoraria(codTipoGradeHoraria);
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
     * @return o atributo dataReferencia
     */
    public DateTimeDB getDataReferencia() {
        return dataReferencia;
    }

    /**
     * Define o atributo dataReferencia
     */
    public void setDataReferencia(DateTimeDB dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    /**
     * @return o atributo dataHoraAbertura
     */
    public DateTimeDB getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    /**
     * Define o atributo dataHoraAbertura
     */
    public void setDataHoraAbertura(DateTimeDB dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    /**
     * @return o atributo dataHoraFechamento
     */
    public DateTimeDB getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    /**
     * Define o atributo dataHoraFechamento
     */
    public void setDataHoraFechamento(DateTimeDB dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    /**
     * @return o atributo tipoGradeHoraria
     */
    public TipoGradeHoraria getTipoGradeHoraria() {
        return tipoGradeHoraria;
    }

    /**
     * Define o atributo tipoGradeHoraria
     */
    public void setTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) {
        this.tipoGradeHoraria = tipoGradeHoraria;
    }

}
