/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDAAceite.java
 * Data Criação:    Jun 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDAAceite
 * 
 * @author george.santos
 */
@Entity
@Table(name = "MensagemDDAAceite", schema = "DDA")
public class MensagemDDAAceite extends SicoobDDAEntidade implements IMensagemDDA {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @Transient
    private Long idEventoTituloDDA;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    @Column(nullable = false)
    private Boolean bolAceite;

    private Long numRefAtualAceite;

    private Long numSeqAtualAceite;

    @Column(nullable = false)
    private Long numIdentificadorBoletoCip;

    /**
     * @param idMensagemDDA
     * @param bolAceite
     * @param numRefAtualAceite
     * @param numSeqAtualAceite
     * @param numIdentificadorBoletoCip
     */
    public MensagemDDAAceite(Long idMensagemDDA, Boolean bolAceite, Long numRefAtualAceite, Long numSeqAtualAceite, Long numIdentificadorBoletoCip) {
        super();
        this.id = idMensagemDDA;
        this.bolAceite = bolAceite;
        this.numRefAtualAceite = numRefAtualAceite;
        this.numSeqAtualAceite = numSeqAtualAceite;
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

    /**
     * 
     */
    public MensagemDDAAceite() {
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
     * @return the mensagemDDA
     */
    public MensagemDDA getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDA mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return o atributo bolAceite retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public Boolean isBolAceite() {
        return bolAceite;
    }

    /**
     * @return o atributo bolAceite retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public String getBolAceite() {
        return bolAceite ? "S" : "N";
    }

    /**
     * Define o atributo bolAceite
     */
    public void setBolAceite(Boolean bolAceite) {
        this.bolAceite = bolAceite;
    }

    /**
     * @return o atributo numRefAtualAceite
     */
    public Long getNumRefAtualAceite() {
        return numRefAtualAceite;
    }

    /**
     * Define o atributo numRefAtualAceite
     */
    public void setNumRefAtualAceite(Long numRefAtualAceite) {
        this.numRefAtualAceite = numRefAtualAceite;
    }

    /**
     * @return o atributo numSeqAtualAceite
     */
    public Long getNumSeqAtualAceite() {
        return numSeqAtualAceite;
    }

    /**
     * Define o atributo numSeqAtualAceite
     */
    public void setNumSeqAtualAceite(Long numSeqAtualAceite) {
        this.numSeqAtualAceite = numSeqAtualAceite;
    }

    /**
     * @return the numIdentificadorBoletoCip
     */
    public Long getNumIdentificadorBoletoCip() {
        return numIdentificadorBoletoCip;
    }

    /**
     * @param numIdentificadorBoletoCip the numIdentificadorBoletoCip to set
     */
    public void setNumIdentificadorBoletoCip(Long numIdentificadorBoletoCip) {
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

    /**
     * @return the idEventoTituloDDA
     */
    public Long getIdEventoTituloDDA() {
        return idEventoTituloDDA;
    }

    /**
     * @param idEventoTituloDDA the idEventoTituloDDA to set
     */
    public void setIdEventoTituloDDA(Long idEventoTituloDDA) {
        this.idEventoTituloDDA = idEventoTituloDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return getIdEventoTituloDDA();
    }

}
