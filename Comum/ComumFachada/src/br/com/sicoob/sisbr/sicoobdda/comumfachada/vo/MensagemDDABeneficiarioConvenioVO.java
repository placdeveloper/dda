package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDABeneficiarioConvenioVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio")
public class MensagemDDABeneficiarioConvenioVO extends BancoobVO {

    private Long id;
    private MensagemDDABeneficiarioVO mensagemDDABeneficiario;
    private Integer numAgencia;
    private Long numConta;
    private String numClienteOuConvenio;
    private String codTipoProdutoDDA;
    private String codSituacaoConvenioDDA;
    private DateTime dataInicioRelacionamento;
    private DateTime dataFimRelacionamento;
    private String codISPBParticipanteIncorporado;
    private String codTipoOperacao;

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
    public MensagemDDABeneficiarioVO getMensagemDDABeneficiario() {
        return mensagemDDABeneficiario;
    }

    /**
     * @param mensagemDDABeneficiario the mensagemDDABeneficiario to set
     */
    public void setMensagemDDABeneficiario(MensagemDDABeneficiarioVO mensagemDDABeneficiario) {
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
    public DateTime getDataInicioRelacionamento() {
        return dataInicioRelacionamento;
    }

    /**
     * @param dataInicioRelacionamento the dataInicioRelacionamento to set
     */
    public void setDataInicioRelacionamento(DateTime dataInicioRelacionamento) {
        this.dataInicioRelacionamento = dataInicioRelacionamento;
    }

    /**
     * @return the dataFimRelacionamento
     */
    public DateTime getDataFimRelacionamento() {
        return dataFimRelacionamento;
    }

    /**
     * @param dataFimRelacionamento the dataFimRelacionamento to set
     */
    public void setDataFimRelacionamento(DateTime dataFimRelacionamento) {
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
     * @return the codTipoOperacao
     */
    public String getCodTipoOperacao() {
        return codTipoOperacao;
    }

    /**
     * @param codTipoOperacao the codTipoOperacao to set
     */
    public void setCodTipoOperacao(String codTipoOperacao) {
        this.codTipoOperacao = codTipoOperacao;
    }

}
