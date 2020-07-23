/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABeneficiarioRepresentante.java
 * Data Criação:    Jun 3, 2016
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

/**
 * MensagemDDABeneficiarioRepresentante
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "MENSAGEMDDABENEFICIARIOREPRESENTANTE", schema = "DDA")
public class MensagemDDABeneficiarioRepresentante extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4676538475685067865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDABeneficiarioRepresentante", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA")
    private MensagemDDABeneficiario mensagemDDABeneficiario;

    @Column(nullable = false)
    private String numCpfCnpjRepresentante;

    // DDA.TipoPessoaDDA
    @Column(nullable = false)
    private String codTipoPessoaRepresentante;

    /**
     * @param numCpfCnpjRepresentante
     * @param codTipoPessoaRepresentante
     */
    public MensagemDDABeneficiarioRepresentante(String numCpfCnpjRepresentante, String codTipoPessoaRepresentante) {
        this.numCpfCnpjRepresentante = numCpfCnpjRepresentante;
        this.codTipoPessoaRepresentante = codTipoPessoaRepresentante;
    }

    public MensagemDDABeneficiarioRepresentante() {
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
     * @return the mensagemDDABeneficiario
     */
    public MensagemDDABeneficiario getMensagemDDABeneficiario() {
        return mensagemDDABeneficiario;
    }

    /**
     * @param mensagemDDABeneficiario the mensagemDDABeneficiario to set
     */
    public void setMensagemDDABeneficiario(MensagemDDABeneficiario mensagemDDABeneficiario) {
        this.mensagemDDABeneficiario = mensagemDDABeneficiario;
    }

    /**
     * @return the numCpfCnpjRepresentante
     */
    public String getNumCpfCnpjRepresentante() {
        return numCpfCnpjRepresentante;
    }

    /**
     * @param numCpfCnpjRepresentante the numCpfCnpjRepresentante to set
     */
    public void setNumCpfCnpjRepresentante(String numCpfCnpjRepresentante) {
        this.numCpfCnpjRepresentante = numCpfCnpjRepresentante;
    }

    /**
     * @return the codTipoPessoaRepresentante
     */
    public String getCodTipoPessoaRepresentante() {
        return codTipoPessoaRepresentante;
    }

    /**
     * @param codTipoPessoaRepresentante the codTipoPessoaRepresentante to set
     */
    public void setCodTipoPessoaRepresentante(String codTipoPessoaRepresentante) {
        this.codTipoPessoaRepresentante = codTipoPessoaRepresentante;
    }
}
