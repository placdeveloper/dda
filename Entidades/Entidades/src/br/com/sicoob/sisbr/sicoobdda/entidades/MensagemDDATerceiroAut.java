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
 * MensagemDDATerceiroAut é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "MensagemDDATerceiroAut", schema = "DDA")
public class MensagemDDATerceiroAut extends SicoobDDAEntidade implements IMensagemDDA {
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
    private Long numIdentificadorBoletoCip;

    @Column(nullable = false)
    private String codTipoPessoaAutorizador;

    @Column(nullable = false)
    private String numCpfCnpjAutorizador;

    @Column(nullable = false)
    private String codTipoPessoaTerceiro;

    @Column(nullable = false)
    private String numCpfCnpjTerceiro;

    private Long numRefAtualTerceiro;

    @Transient
    private Long numIdentificadorTerceiro;

    /**
     * 
     */
    public MensagemDDATerceiroAut() {
    }

    /**
     * @param idMensagemDDA
     * @param numIdentificadorBoletoCip
     * @param codTipoPessoaAutorizador
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaTerceiro
     * @param numCpfCnpjTerceiro
     * @param numRefAtualTerceiro
     * @param numIdentificadorTerceiro
     */
    public MensagemDDATerceiroAut(Long idMensagemDDA, Long numIdentificadorBoletoCip, String codTipoPessoaAutorizador, String numCpfCnpjAutorizador, String codTipoPessoaTerceiro,
            String numCpfCnpjTerceiro, Long numRefAtualTerceiro, Long numIdentificadorTerceiro) {
        super();
        this.id = idMensagemDDA;
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
        this.codTipoPessoaAutorizador = codTipoPessoaAutorizador;
        this.numCpfCnpjAutorizador = numCpfCnpjAutorizador;
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
        this.numRefAtualTerceiro = numRefAtualTerceiro;
        this.numIdentificadorTerceiro = numIdentificadorTerceiro;
    }

    /**
     * @param idMensagemDDA
     * @param numIdentificadorBoletoCip
     * @param codTipoPessoaAutorizador
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaTerceiro
     * @param numCpfCnpjTerceiro
     * @param numRefAtualTerceiro
     */
    public MensagemDDATerceiroAut(Long idMensagemDDA, Long numIdentificadorBoletoCip, String codTipoPessoaAutorizador, String numCpfCnpjAutorizador, String codTipoPessoaTerceiro,
            String numCpfCnpjTerceiro, Long numRefAtualTerceiro) {
        super();
        this.id = idMensagemDDA;
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
        this.codTipoPessoaAutorizador = codTipoPessoaAutorizador;
        this.numCpfCnpjAutorizador = numCpfCnpjAutorizador;
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
        this.numRefAtualTerceiro = numRefAtualTerceiro;
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
     * @return o atributo codTipoPessoaAutorizador
     */
    public String getCodTipoPessoaAutorizador() {
        return codTipoPessoaAutorizador;
    }

    /**
     * Define o atributo codTipoPessoaAutorizador
     */
    public void setCodTipoPessoaAutorizador(String codTipoPessoaAutorizador) {
        this.codTipoPessoaAutorizador = codTipoPessoaAutorizador;
    }

    /**
     * @return o atributo numCpfCnpjAutorizador
     */
    public String getNumCpfCnpjAutorizador() {
        return numCpfCnpjAutorizador;
    }

    /**
     * Define o atributo numCpfCnpjAutorizador
     */
    public void setNumCpfCnpjAutorizador(String numCpfCnpjAutorizador) {
        this.numCpfCnpjAutorizador = numCpfCnpjAutorizador;
    }

    /**
     * @return o atributo codTipoPessoaTerceiro
     */
    public String getCodTipoPessoaTerceiro() {
        return codTipoPessoaTerceiro;
    }

    /**
     * Define o atributo codTipoPessoaTerceiro
     */
    public void setCodTipoPessoaTerceiro(String codTipoPessoaTerceiro) {
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
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
     * @return the numRefAtualTerceiro
     */
    public Long getNumRefAtualTerceiro() {
        return numRefAtualTerceiro;
    }

    /**
     * @param numRefAtualTerceiro the numRefAtualTerceiro to set
     */
    public void setNumRefAtualTerceiro(Long numRefAtualTerceiro) {
        this.numRefAtualTerceiro = numRefAtualTerceiro;
    }

    /**
     * @return o atributo numIdentificadorTerceiro
     */
    public Long getNumIdentificadorTerceiro() {
        return numIdentificadorTerceiro;
    }

    /**
     * Define o atributo numIdentificadorTerceiro
     */
    public void setNumIdentificadorTerceiro(Long numIdentificadorTerceiro) {
        this.numIdentificadorTerceiro = numIdentificadorTerceiro;
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
