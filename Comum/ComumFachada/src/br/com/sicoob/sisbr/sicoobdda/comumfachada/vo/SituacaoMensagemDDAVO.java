package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoMensagemDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoMensagemDDA")
public class SituacaoMensagemDDAVO extends BancoobVO {

    private Short codSituacaoMensagemDDA;
    private String descSituacaoMensagemDDA;
    private List<TipoTratamentoErroCipVO> listaTipoTratamentoErroCip;

    /**
     * @return the codSituacaoMensagemDDA
     */
    public Short getCodSituacaoMensagemDDA() {
        return codSituacaoMensagemDDA;
    }

    /**
     * @param codSituacaoMensagemDDA the codSituacaoMensagemDDA to set
     */
    public void setCodSituacaoMensagemDDA(Short codSituacaoMensagemDDA) {
        this.codSituacaoMensagemDDA = codSituacaoMensagemDDA;
    }

    /**
     * @return the descSituacaoMensagemDDA
     */
    public String getDescSituacaoMensagemDDA() {
        return descSituacaoMensagemDDA;
    }

    /**
     * @param descSituacaoMensagemDDA the descSituacaoMensagemDDA to set
     */
    public void setDescSituacaoMensagemDDA(String descSituacaoMensagemDDA) {
        this.descSituacaoMensagemDDA = descSituacaoMensagemDDA;
    }

    /**
     * @return the listaTipoTratamentoErroCip
     */
    public List<TipoTratamentoErroCipVO> getListaTipoTratamentoErroCip() {
        return listaTipoTratamentoErroCip;
    }

    /**
     * @param listaTipoTratamentoErroCip the listaTipoTratamentoErroCip to set
     */
    public void setListaTipoTratamentoErroCip(List<TipoTratamentoErroCipVO> listaTipoTratamentoErroCip) {
        this.listaTipoTratamentoErroCip = listaTipoTratamentoErroCip;
    }


}
