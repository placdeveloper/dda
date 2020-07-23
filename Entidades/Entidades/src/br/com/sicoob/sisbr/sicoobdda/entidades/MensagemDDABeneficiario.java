/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABeneficiario.java
 * Data Criação:    Jun 3, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.ArrayList;
import java.util.Date;
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

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDABeneficiario
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "MENSAGEMDDABENEFICIARIO", schema = "DDA")
public class MensagemDDABeneficiario extends SicoobDDAEntidade implements IMensagemDDA {

    private static final long serialVersionUID = -7109540133467222514L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    @Column(nullable = false)
    private String numCpfCnpjBeneficiario;

    // DDA.TipoPessoaDDA
    @Column(nullable = false)
    private String codTipoPessoaBeneficiario;

    private String nomeBeneficiario;

    private String nomeFantasiaBeneficiario;

    // DDA.SituacaoBeneficiario
    private String codSituacaoBeneficiario;

    private DateTimeDB dataHoraSituacao;

    // DDA.SituacaoRelacionamentoBeneficiario
    private String codSituacaoRelacionamentoBeneficiario;

    private DateTimeDB dataInicioRelacionamento;

    private DateTimeDB dataFimRelacionamento;

    private Long numRefAtualCadBeneficiario;

    private Long numSeqAtualCadBeneficiario;

    @Transient
    private Long numIdentificadorBenficiario;

    @OneToMany(mappedBy = "mensagemDDABeneficiario", cascade = CascadeType.ALL)
    private List<MensagemDDABeneficiarioConvenio> listaMensagemDDABeneficiarioConvenio;

    @OneToMany(mappedBy = "mensagemDDABeneficiario", cascade = CascadeType.ALL)
    private List<MensagemDDABeneficiarioRepresentante> listaMensagemDDABeneficiarioRepresentante;

    /**
     * @param id
     * @param numCpfCnpjBeneficiario
     * @param codTipoPessoaBeneficiario
     * @param nomeBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param codSituacaoBeneficiario
     * @param dataHoraSituacao
     * @param codSituacaoRelacionamentoBeneficiario
     * @param dataInicioRelacionamento
     * @param dataFimRelacionamento
     */
    public MensagemDDABeneficiario(long id, String numCpfCnpjBeneficiario, String codTipoPessoaBeneficiario, String nomeBeneficiario, String nomeFantasiaBeneficiario,
            String codSituacaoBeneficiario, DateTimeDB dataHoraSituacao, String codSituacaoRelacionamentoBeneficiario, Date dataInicioRelacionamento, Date dataFimRelacionamento,
            Long numRefAtualCadBeneficiario, Long numSeqAtualCadBeneficiario) {
        this.id = id;
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
        this.codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
        this.nomeBeneficiario = nomeBeneficiario;
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
        this.dataHoraSituacao = dataHoraSituacao;
        this.codSituacaoRelacionamentoBeneficiario = codSituacaoRelacionamentoBeneficiario;
        this.dataInicioRelacionamento = dataInicioRelacionamento != null ? new DateTimeDB(dataInicioRelacionamento.getTime()) : null;
        this.dataFimRelacionamento = dataFimRelacionamento != null ? new DateTimeDB(dataFimRelacionamento.getTime()) : null;
        this.numRefAtualCadBeneficiario = numRefAtualCadBeneficiario;
        this.numSeqAtualCadBeneficiario = numSeqAtualCadBeneficiario;
    }

    /**
     * @param id
     * @param numCpfCnpjBeneficiario
     * @param codTipoPessoaBeneficiario
     * @param nomeBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param codSituacaoBeneficiario
     * @param dataHoraSituacao
     * @param codSituacaoRelacionamentoBeneficiario
     * @param dataInicioRelacionamento
     * @param dataFimRelacionamento
     */
    public MensagemDDABeneficiario(long id, String numCpfCnpjBeneficiario, String codTipoPessoaBeneficiario, String nomeBeneficiario, String nomeFantasiaBeneficiario,
            String codSituacaoBeneficiario, DateTimeDB dataHoraSituacao, String codSituacaoRelacionamentoBeneficiario, Date dataInicioRelacionamento, Date dataFimRelacionamento) {
        this.id = id;
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
        this.codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
        this.nomeBeneficiario = nomeBeneficiario;
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
        this.dataHoraSituacao = dataHoraSituacao;
        this.codSituacaoRelacionamentoBeneficiario = codSituacaoRelacionamentoBeneficiario;
        this.dataInicioRelacionamento = dataInicioRelacionamento != null ? new DateTimeDB(dataInicioRelacionamento.getTime()) : null;
        this.dataFimRelacionamento = dataFimRelacionamento != null ? new DateTimeDB(dataFimRelacionamento.getTime()) : null;
    }

    public MensagemDDABeneficiario() {
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
     * @return the numCpfCnpjBeneficiario
     */
    public String getNumCpfCnpjBeneficiario() {
        return numCpfCnpjBeneficiario;
    }

    /**
     * @param numCpfCnpjBeneficiario the numCpfCnpjBeneficiario to set
     */
    public void setNumCpfCnpjBeneficiario(String numCpfCnpjBeneficiario) {
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
    }

    /**
     * @return the codTipoPessoaBeneficiario
     */
    public String getCodTipoPessoaBeneficiario() {
        return codTipoPessoaBeneficiario;
    }

    /**
     * @param codTipoPessoaBeneficiario the codTipoPessoaBeneficiario to set
     */
    public void setCodTipoPessoaBeneficiario(String codTipoPessoaBeneficiario) {
        this.codTipoPessoaBeneficiario = codTipoPessoaBeneficiario;
    }

    /**
     * @return the nomeBeneficiario
     */
    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    /**
     * @param nomeBeneficiario the nomeBeneficiario to set
     */
    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    /**
     * @return the nomeFantasiaBeneficiario
     */
    public String getNomeFantasiaBeneficiario() {
        return nomeFantasiaBeneficiario;
    }

    /**
     * @param nomeFantasiaBeneficiario the nomeFantasiaBeneficiario to set
     */
    public void setNomeFantasiaBeneficiario(String nomeFantasiaBeneficiario) {
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
    }

    /**
     * @return the codSituacaoBeneficiario
     */
    public String getCodSituacaoBeneficiario() {
        return codSituacaoBeneficiario;
    }

    /**
     * @param codSituacaoBeneficiario the codSituacaoBeneficiario to set
     */
    public void setCodSituacaoBeneficiario(String codSituacaoBeneficiario) {
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
    }

    /**
     * @return the dataHoraSituacao
     */
    public DateTimeDB getDataHoraSituacao() {
        return dataHoraSituacao;
    }

    /**
     * @param dataHoraSituacao the dataHoraSituacao to set
     */
    public void setDataHoraSituacao(DateTimeDB dataHoraSituacao) {
        this.dataHoraSituacao = dataHoraSituacao;
    }

    /**
     * @return the codSituacaoRelacionamentoBeneficiario
     */
    public String getCodSituacaoRelacionamentoBeneficiario() {
        return codSituacaoRelacionamentoBeneficiario;
    }

    /**
     * @param codSituacaoRelacionamentoBeneficiario the codSituacaoRelacionamentoBeneficiario to set
     */
    public void setCodSituacaoRelacionamentoBeneficiario(String codSituacaoRelacionamentoBeneficiario) {
        this.codSituacaoRelacionamentoBeneficiario = codSituacaoRelacionamentoBeneficiario;
    }

    /**
     * @return the dataInicioRelacionamento
     */
    public DateTimeDB getDataInicioRelacionamento() {
        return dataInicioRelacionamento;
    }

    /**
     * @param dataInicioRelacionamento the dataInicioRelacionamento to set
     */
    public void setDataInicioRelacionamento(DateTimeDB dataInicioRelacionamento) {
        this.dataInicioRelacionamento = dataInicioRelacionamento;
    }

    /**
     * @return the dataFimRelacionamento
     */
    public DateTimeDB getDataFimRelacionamento() {
        return dataFimRelacionamento;
    }

    /**
     * @param dataFimRelacionamento the dataFimRelacionamento to set
     */
    public void setDataFimRelacionamento(DateTimeDB dataFimRelacionamento) {
        this.dataFimRelacionamento = dataFimRelacionamento;
    }

    /**
     * @return the listaMensagemDDABeneficiarioConvenio
     */
    public List<MensagemDDABeneficiarioConvenio> getListaMensagemDDABeneficiarioConvenio() {
        if (listaMensagemDDABeneficiarioConvenio == null) {
            listaMensagemDDABeneficiarioConvenio = new ArrayList<MensagemDDABeneficiarioConvenio>();
        }
        return listaMensagemDDABeneficiarioConvenio;
    }

    /**
     * @param listaMensagemDDABeneficiarioConvenio the listaMensagemDDABeneficiarioConvenio to set
     */
    public void setListaMensagemDDABeneficiarioConvenio(List<MensagemDDABeneficiarioConvenio> listaMensagemDDABeneficiarioConvenio) {
        this.listaMensagemDDABeneficiarioConvenio = listaMensagemDDABeneficiarioConvenio;
    }

    /**
     * @return the listaMensagemDDABeneficiarioRepresentante
     */
    public List<MensagemDDABeneficiarioRepresentante> getListaMensagemDDABeneficiarioRepresentante() {
        if (listaMensagemDDABeneficiarioRepresentante == null) {
            listaMensagemDDABeneficiarioRepresentante = new ArrayList<MensagemDDABeneficiarioRepresentante>();
        }
        return listaMensagemDDABeneficiarioRepresentante;
    }

    /**
     * @param listaMensagemDDABeneficiarioRepresentante the listaMensagemDDABeneficiarioRepresentante to set
     */
    public void setListaMensagemDDABeneficiarioRepresentante(List<MensagemDDABeneficiarioRepresentante> listaMensagemDDABeneficiarioRepresentante) {
        this.listaMensagemDDABeneficiarioRepresentante = listaMensagemDDABeneficiarioRepresentante;
    }

    /**
     * @return the numRefAtualCadBeneficiario
     */
    public Long getNumRefAtualCadBeneficiario() {
        return numRefAtualCadBeneficiario;
    }

    /**
     * @param numRefAtualCadBeneficiario the numRefAtualCadBeneficiario to set
     */
    public void setNumRefAtualCadBeneficiario(Long numRefAtualCadBeneficiario) {
        this.numRefAtualCadBeneficiario = numRefAtualCadBeneficiario;
    }

    /**
     * @return the numSeqAtualCadBeneficiario
     */
    public Long getNumSeqAtualCadBeneficiario() {
        return numSeqAtualCadBeneficiario;
    }

    /**
     * @param numSeqAtualCadBeneficiario the numSeqAtualCadBeneficiario to set
     */
    public void setNumSeqAtualCadBeneficiario(Long numSeqAtualCadBeneficiario) {
        this.numSeqAtualCadBeneficiario = numSeqAtualCadBeneficiario;
    }

    /**
     * @return o atributo numIdentificadorBenficiario
     */
    public Long getNumIdentificadorBenficiario() {
        return numIdentificadorBenficiario;
    }

    /**
     * Define o atributo numIdentificadorBenficiario
     */
    public void setNumIdentificadorBenficiario(Long numIdentificadorBenficiario) {
        this.numIdentificadorBenficiario = numIdentificadorBenficiario;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return null;
    }
}
