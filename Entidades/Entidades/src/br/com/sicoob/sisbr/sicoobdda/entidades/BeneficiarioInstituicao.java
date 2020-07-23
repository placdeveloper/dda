/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  swdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         BeneficiarioInstituicao.java
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

/**
 * DDABeneficiarioInstituicao
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "BENEFICIARIOINSTITUICAO", schema = "DDA")
public class BeneficiarioInstituicao extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBENEFICIARIOINSTITUICAO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBENEFICIARIODDA", nullable = false)
    private BeneficiarioDDA beneficiarioDDA;

    @Column(nullable = false)
    private Integer idInstituicao;

    @Column(name = "DATAINICIORELACIONAMENTO", nullable = false)
    private DateTimeDB dataInicioRelacionamento;

    /**
     * 
     */
    public BeneficiarioInstituicao() {
        super();
    }

    /**
     * @param beneficiarioDDA
     * @param idInstituicao
     * @param dataInicioRelacionamento
     */
    public BeneficiarioInstituicao(BeneficiarioDDA beneficiarioDDA, Integer idInstituicao, DateTimeDB dataInicioRelacionamento) {
        super();
        this.beneficiarioDDA = beneficiarioDDA;
        this.idInstituicao = idInstituicao;
        this.dataInicioRelacionamento = dataInicioRelacionamento;
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
     * @return o atributo beneficiarioDDA
     */
    public BeneficiarioDDA getBeneficiarioDDA() {
        return beneficiarioDDA;
    }

    /**
     * Define o atributo beneficiarioDDA
     */
    public void setBeneficiarioDDA(BeneficiarioDDA beneficiarioDDA) {
        this.beneficiarioDDA = beneficiarioDDA;
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

    /**
     * @return o atributo dataInicioRelacionamento
     */
    public DateTimeDB getDataInicioRelacionamento() {
        return dataInicioRelacionamento;
    }

    /**
     * Define o atributo dataInicioRelacionamento
     */
    public void setDataInicioRelacionamento(DateTimeDB dataInicioRelacionamento) {
        this.dataInicioRelacionamento = dataInicioRelacionamento;
    }

}
