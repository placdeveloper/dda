/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         DDAIfBeneficiarioAlerta.java
 * Data Criação:    Jul 24, 2015
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
 * IFBeneficiarioAlerta
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "IFBENEFICIARIOALERTA", schema = "DDA")
public class IFBeneficiarioAlerta extends SicoobDDAEntidade {

    private static final long serialVersionUID = 8120083614268470290L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDIFBENEFICIARIOALERTA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBENEFICIARIODDA", nullable = false)
    private BeneficiarioDDA beneficiarioDDA;

    @Column(name = "CODISPBPARTDESTINATARIOORIGDRALERT", nullable = false)
    private String codIspbDestinatarioOriginalAlerta;

    @Column(nullable = false)
    private DateTimeDB dataHoraAtualizacao;

    /**
     * 
     */
    public IFBeneficiarioAlerta() {
        super();
    }

    /**
     * @param beneficiarioDDA
     * @param codIspbDestinatarioOriginalAlerta
     * @param dataHoraAtualizacao
     */
    public IFBeneficiarioAlerta(BeneficiarioDDA beneficiarioDDA, String codIspbDestinatarioOriginalAlerta, DateTimeDB dataHoraAtualizacao) {
        super();
        this.beneficiarioDDA = beneficiarioDDA;
        this.codIspbDestinatarioOriginalAlerta = codIspbDestinatarioOriginalAlerta;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
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
     * @return o atributo codIspbDestinatarioOriginalAlerta
     */
    public String getCodIspbDestinatarioOriginalAlerta() {
        return codIspbDestinatarioOriginalAlerta;
    }

    /**
     * Define o atributo codIspbDestinatarioOriginalAlerta
     */
    public void setCodIspbDestinatarioOriginalAlerta(String codIspbDestinatarioOriginalAlerta) {
        this.codIspbDestinatarioOriginalAlerta = codIspbDestinatarioOriginalAlerta;
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

}
