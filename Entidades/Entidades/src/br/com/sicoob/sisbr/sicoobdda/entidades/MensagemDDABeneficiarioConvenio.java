/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDABeneficiarioConvenio.java
 * Data Criação:    Jun 3, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.Date;

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
 * MensagemDDABeneficiarioConvenio
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "MENSAGEMDDABENEFICIARIOCONVENIO", schema = "DDA")
public class MensagemDDABeneficiarioConvenio extends SicoobDDAEntidade {

    private static final long serialVersionUID = 4757811964879410432L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDABeneficiarioConvenio", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDABeneficiario mensagemDDABeneficiario;

    @Column(nullable = false)
    private Integer numAgencia;

    private Long numConta;

    private String numClienteOuConvenio;

    // DDA.TipoProdutoDDA
    @Column(nullable = false)
    private String codTipoProdutoDDA;

    // DDA.SituacaoConvenioDDA
    private String codSituacaoConvenioDDA;

    @Column(nullable = false)
    private DateTimeDB dataInicioRelacionamento;

    private DateTimeDB dataFimRelacionamento;

    private String codISPBParticipanteIncorporado;

    // TipoManutencaoEnum
    private String codTipoOperacao;

    /**
     * 
     */
    public MensagemDDABeneficiarioConvenio(Long id, Integer numAgencia, Long numConta, String numClienteOuConvenio, String codTipoProduto, String codSituacaoConvenio,
            Date dataInicioRelacionamento, Date dataFimRelacionamento, String codISPBParticipanteIncorporado, String codTipoOperacao) {
        this.id = id;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.numClienteOuConvenio = numClienteOuConvenio;
        this.codTipoProdutoDDA = codTipoProduto;
        this.codSituacaoConvenioDDA = codSituacaoConvenio;
        this.dataInicioRelacionamento = dataInicioRelacionamento != null ? new DateTimeDB(dataInicioRelacionamento.getTime()) : null;
        this.dataFimRelacionamento = dataFimRelacionamento != null ? new DateTimeDB(dataFimRelacionamento.getTime()) : null;
        this.codISPBParticipanteIncorporado = codISPBParticipanteIncorporado;
        this.codTipoOperacao = codTipoOperacao;
    }

    public MensagemDDABeneficiarioConvenio() {
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
     * @return o atributo mensagemDDABeneficiario
     */
    public MensagemDDABeneficiario getMensagemDDABeneficiario() {
        return mensagemDDABeneficiario;
    }

    /**
     * Define o atributo mensagemDDABeneficiario
     */
    public void setMensagemDDABeneficiario(MensagemDDABeneficiario mensagemDDABeneficiario) {
        this.mensagemDDABeneficiario = mensagemDDABeneficiario;
    }

    /**
     * @return the numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * @param numAgencia the numAgencia to set
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return the numConta
     */
    public Long getNumConta() {
        return numConta;
    }

    /**
     * @param numConta the numConta to set
     */
    public void setNumConta(Long numConta) {
        this.numConta = numConta;
    }

    /**
     * @return the numClienteOuConvenio
     */
    public String getNumClienteOuConvenio() {
        return numClienteOuConvenio;
    }

    /**
     * @param numClienteOuConvenio the numClienteOuConvenio to set
     */
    public void setNumClienteOuConvenio(String numClienteOuConvenio) {
        this.numClienteOuConvenio = numClienteOuConvenio;
    }

    /**
     * @return the codTipoProdutoDDA
     */
    public String getCodTipoProdutoDDA() {
        return codTipoProdutoDDA;
    }

    /**
     * @param codTipoProdutoDDA the codTipoProdutoDDA to set
     */
    public void setCodTipoProdutoDDA(String codTipoProdutoDDA) {
        this.codTipoProdutoDDA = codTipoProdutoDDA;
    }

    /**
     * @return the codSituacaoConvenioDDA
     */
    public String getCodSituacaoConvenioDDA() {
        return codSituacaoConvenioDDA;
    }

    /**
     * @param codSituacaoConvenioDDA the codSituacaoConvenioDDA to set
     */
    public void setCodSituacaoConvenioDDA(String codSituacaoConvenioDDA) {
        this.codSituacaoConvenioDDA = codSituacaoConvenioDDA;
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
     * @return the codISPBParticipanteIncorporado
     */
    public String getCodISPBParticipanteIncorporado() {
        return codISPBParticipanteIncorporado;
    }

    /**
     * @param codISPBParticipanteIncorporado the codISPBParticipanteIncorporado to set
     */
    public void setCodISPBParticipanteIncorporado(String codISPBParticipanteIncorporado) {
        this.codISPBParticipanteIncorporado = codISPBParticipanteIncorporado;
    }

    /**
     * @return o atributo codTipoOperacao
     */
    public String getCodTipoOperacao() {
        return codTipoOperacao;
    }

    /**
     * Define o atributo codTipoOperacao
     */
    public void setCodTipoOperacao(String codTipoOperacao) {
        this.codTipoOperacao = codTipoOperacao;
    }
}
