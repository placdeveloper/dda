/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDAPagador.java
 * Data Criação:    Jun 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDAPagador
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "MensagemDDAPagador", schema = "DDA")
public class MensagemDDAPagador extends SicoobDDAEntidade implements IMensagemDDA {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @Transient
    private Long idEventoDDA;

    @Transient
    private Long numIdentificaPagadorCip;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    @Column(nullable = false)
    private String numCpfCnpjPagador;

    @Column(nullable = false)
    private String codTipoPessoaPagador;

    @Column(nullable = false)
    private Boolean bolPagadorEletronico;

    private Long numRefAtualCadPagador;

    private Long numSeqAtualCadPagador;

    @OneToMany(mappedBy = "mensagemDDAPagador", cascade = CascadeType.ALL)
    private List<MensagemDDAPagadorAgregado> listaMensagemDDAPagadorAgregado;

    @OneToMany(mappedBy = "mensagemDDAPagador", cascade = CascadeType.ALL)
    private List<MensagemDDAPagadorConta> listaMensagemDDAPagadorConta;

    /**
     * Atributo utilizado na SPU_DDA_ENVIO_PAGADORDDA para carga de mensagens do pagador.
     */
    @Transient
    private Short codTipoTermoPagador;

    /**
     * @param idMensagemDDA
     */
    public MensagemDDAPagador(Long idMensagemDDA) {
        this.id = idMensagemDDA;
    }

    /**
     * @param id
     * @param numCpfCnpjPagador
     * @param codTipoPessoaPagador
     * @param bolPagadorEletronico
     * @param numIdentificaPagadorCip
     * @param numRefAtualCadPagador
     * @param numSeqAtualCadPagador
     */
    public MensagemDDAPagador(Long id, String numCpfCnpjPagador, String codTipoPessoaPagador, Boolean bolPagadorEletronico, Long numRefAtualCadPagador, Long numSeqAtualCadPagador,
            Long numIdentificaPagadorCip) {
        super();
        this.id = id;
        this.numCpfCnpjPagador = numCpfCnpjPagador;
        this.codTipoPessoaPagador = codTipoPessoaPagador;
        this.bolPagadorEletronico = bolPagadorEletronico;
        this.numRefAtualCadPagador = numRefAtualCadPagador;
        this.numSeqAtualCadPagador = numSeqAtualCadPagador;
        this.numIdentificaPagadorCip = numIdentificaPagadorCip;
    }

    /**
     * @param mensagemDDA
     * @param numCpfCnpjPagador
     * @param codTipoPessoaPagador
     * @param bolPagadorEletronico
     */
    public MensagemDDAPagador(MensagemDDA mensagemDDA, String numCpfCnpjPagador, String codTipoPessoaPagador, Boolean bolPagadorEletronico) {
        super();
        this.mensagemDDA = mensagemDDA;
        this.numCpfCnpjPagador = numCpfCnpjPagador;
        this.codTipoPessoaPagador = codTipoPessoaPagador;
        this.bolPagadorEletronico = bolPagadorEletronico;
    }

    /**
     * 
     */
    public MensagemDDAPagador() {
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
     * @return the numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * @param numCpfCnpjPagador the numCpfCnpjPagador to set
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return the codTipoPessoaPagador
     */
    public String getCodTipoPessoaPagador() {
        return codTipoPessoaPagador;
    }

    /**
     * @param codTipoPessoaPagador the codTipoPessoaPagador to set
     */
    public void setCodTipoPessoaPagador(String codTipoPessoaPagador) {
        this.codTipoPessoaPagador = codTipoPessoaPagador;
    }

    /**
     * @return the bolPagadorEletronico retorna S- Sim ou N- Nao por causa do manual da CIP
     */
    public String getBolPagadorEletronico() {
        return bolPagadorEletronico ? "S" : "N";
    }

    /**
     * @param bolPagadorEletronico the bolPagadorEletronico to set
     */
    public void setBolPagadorEletronico(Boolean bolPagadorEletronico) {
        this.bolPagadorEletronico = bolPagadorEletronico;
    }

    /**
     * @return o atributo listaMensagemDDAPagadorAgregado
     */
    public List<MensagemDDAPagadorAgregado> getListaMensagemDDAPagadorAgregado() {
        if (listaMensagemDDAPagadorAgregado == null) {
            listaMensagemDDAPagadorAgregado = new ArrayList<MensagemDDAPagadorAgregado>();
        }
        return listaMensagemDDAPagadorAgregado;
    }

    /**
     * Define o atributo listaMensagemDDAPagadorAgregado
     */
    public void setListaMensagemDDAPagadorAgregado(List<MensagemDDAPagadorAgregado> listaMensagemDDAPagadorAgregado) {
        this.listaMensagemDDAPagadorAgregado = listaMensagemDDAPagadorAgregado;
    }

    /**
     * @return o atributo listaMensagemDDAPagadorConta
     */
    public List<MensagemDDAPagadorConta> getListaMensagemDDAPagadorConta() {
        if (listaMensagemDDAPagadorConta == null) {
            listaMensagemDDAPagadorConta = new ArrayList<MensagemDDAPagadorConta>();
        }
        return listaMensagemDDAPagadorConta;
    }

    /**
     * Define o atributo listaMensagemDDAPagadorConta
     */
    public void setListaMensagemDDAPagadorConta(List<MensagemDDAPagadorConta> listaMensagemDDAPagadorConta) {
        this.listaMensagemDDAPagadorConta = listaMensagemDDAPagadorConta;
    }

    /**
     * @return the idEventoDDA
     */
    public Long getIdEventoDDA() {
        return idEventoDDA;
    }

    /**
     * @param idEventoDDA the idEventoDDA to set
     */
    public void setIdEventoDDA(Long idEventoDDA) {
        this.idEventoDDA = idEventoDDA;
    }

    /**
     * @return the numRefAtualCadPagador
     */
    public Long getNumRefAtualCadPagador() {
        return numRefAtualCadPagador;
    }

    /**
     * @param numRefAtualCadPagador the numRefAtualCadPagador to set
     */
    public void setNumRefAtualCadPagador(Long numRefAtualCadPagador) {
        this.numRefAtualCadPagador = numRefAtualCadPagador;
    }

    /**
     * @return the numSeqAtualCadPagador
     */
    public Long getNumSeqAtualCadPagador() {
        return numSeqAtualCadPagador;
    }

    /**
     * @param numSeqAtualCadPagador the numSeqAtualCadPagador to set
     */
    public void setNumSeqAtualCadPagador(Long numSeqAtualCadPagador) {
        this.numSeqAtualCadPagador = numSeqAtualCadPagador;
    }

    /**
     * @return the numIdentificaPagadorCip
     */
    public Long getNumIdentificaPagadorCip() {
        return numIdentificaPagadorCip;
    }

    /**
     * @param numIdentificaPagadorCip the numIdentificaPagadorCip to set
     */
    public void setNumIdentificaPagadorCip(Long numIdentificaPagadorCip) {
        this.numIdentificaPagadorCip = numIdentificaPagadorCip;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return getIdEventoDDA();
    }

    /**
     * @return o atributo codTipoTermoPagador
     */
    public Short getCodTipoTermoPagador() {
        return codTipoTermoPagador;
    }

    /**
     * Define o atributo codTipoTermoPagador
     */
    public void setCodTipoTermoPagador(Short codTipoTermoPagador) {
        this.codTipoTermoPagador = codTipoTermoPagador;
    }

}
