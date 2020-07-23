package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDABeneficiarioRepresentanteVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante")
public class MensagemDDABeneficiarioRepresentanteVO extends BancoobVO {

    private Long id;
    private MensagemDDABeneficiarioVO mensagemDDABeneficiario;
    private String numCpfCnpjRepresentante;
    private String codTipoPessoaRepresentante;

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
