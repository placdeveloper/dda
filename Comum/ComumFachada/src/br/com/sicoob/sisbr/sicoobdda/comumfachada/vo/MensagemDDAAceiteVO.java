package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite")
public class MensagemDDAAceiteVO extends BancoobVO {

    private Long id;
    private Long idEventoTituloDDA;
    private MensagemDDAVO mensagemDDA;
    private Boolean bolAceite;
    private Long numRefAtualAceite;
    private Long numSeqAtualAceite;
    private Long numIdentificadorBoletoCip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEventoTituloDDA() {
        return idEventoTituloDDA;
    }

    public void setIdEventoTituloDDA(Long idEventoTituloDDA) {
        this.idEventoTituloDDA = idEventoTituloDDA;
    }

    public MensagemDDAVO getMensagemDDA() {
        return mensagemDDA;
    }

    public void setMensagemDDA(MensagemDDAVO mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    public Boolean getBolAceite() {
        return bolAceite;
    }

    public void setBolAceite(Boolean bolAceite) {
        this.bolAceite = bolAceite;
    }

    public Long getNumRefAtualAceite() {
        return numRefAtualAceite;
    }

    public void setNumRefAtualAceite(Long numRefAtualAceite) {
        this.numRefAtualAceite = numRefAtualAceite;
    }

    public Long getNumSeqAtualAceite() {
        return numSeqAtualAceite;
    }

    public void setNumSeqAtualAceite(Long numSeqAtualAceite) {
        this.numSeqAtualAceite = numSeqAtualAceite;
    }

    public Long getNumIdentificadorBoletoCip() {
        return numIdentificadorBoletoCip;
    }

    public void setNumIdentificadorBoletoCip(Long numIdentificadorBoletoCip) {
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

}
