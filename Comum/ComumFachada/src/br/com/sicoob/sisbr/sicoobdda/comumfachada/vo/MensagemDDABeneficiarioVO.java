package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDABeneficiarioVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario")
public class MensagemDDABeneficiarioVO extends BancoobVO {

    private Long id;
    private MensagemDDAVO mensagemDDA;
    private String numCpfCnpjBeneficiario;
    private String codTipoPessoaBeneficiario;
    private String nomeBeneficiario;
    private String nomeFantasiaBeneficiario;
    private String codSituacaoBeneficiario;
    private DateTime dataHoraSituacao;
    private String codSituacaoRelacionamentoBeneficiario;
    private DateTime dataInicioRelacionamento;
    private DateTime dataFimRelacionamento;
    private Long numRefAtualCadBeneficiario;
    private Long numSeqAtualCadBeneficiario;
    private List<MensagemDDABeneficiarioConvenioVO> listaMensagemDDABeneficiarioConvenio;
    private List<MensagemDDABeneficiarioRepresentanteVO> listaMensagemDDABeneficiarioRepresentante;

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
    public MensagemDDAVO getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDAVO mensagemDDA) {
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
    public DateTime getDataHoraSituacao() {
        return dataHoraSituacao;
    }

    /**
     * @param dataHoraSituacao the dataHoraSituacao to set
     */
    public void setDataHoraSituacao(DateTime dataHoraSituacao) {
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
     * @return the listaMensagemDDABeneficiarioConvenio
     */
    public List<MensagemDDABeneficiarioConvenioVO> getListaMensagemDDABeneficiarioConvenio() {
        return listaMensagemDDABeneficiarioConvenio;
    }

    /**
     * @param listaMensagemDDABeneficiarioConvenio the listaMensagemDDABeneficiarioConvenio to set
     */
    public void setListaMensagemDDABeneficiarioConvenio(List<MensagemDDABeneficiarioConvenioVO> listaMensagemDDABeneficiarioConvenio) {
        this.listaMensagemDDABeneficiarioConvenio = listaMensagemDDABeneficiarioConvenio;
    }

    /**
     * @return the listaMensagemDDABeneficiarioRepresentante
     */
    public List<MensagemDDABeneficiarioRepresentanteVO> getListaMensagemDDABeneficiarioRepresentante() {
        return listaMensagemDDABeneficiarioRepresentante;
    }

    /**
     * @param listaMensagemDDABeneficiarioRepresentante the listaMensagemDDABeneficiarioRepresentante to set
     */
    public void setListaMensagemDDABeneficiarioRepresentante(List<MensagemDDABeneficiarioRepresentanteVO> listaMensagemDDABeneficiarioRepresentante) {
        this.listaMensagemDDABeneficiarioRepresentante = listaMensagemDDABeneficiarioRepresentante;
    }


}
