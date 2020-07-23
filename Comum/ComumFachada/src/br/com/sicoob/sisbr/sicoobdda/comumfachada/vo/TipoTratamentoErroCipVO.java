package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoTratamentoErroCipVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip")
public class TipoTratamentoErroCipVO extends BancoobVO {

    private Short codTipoTratamentoErroCip;
    private String descTipoTratamentoErroCip;
    private List<SituacaoMensagemDDAVO> listaSituacaoMensagemDDA;

    /**
     * @return the codTipoTratamentoErroCip
     */
    public Short getCodTipoTratamentoErroCip() {
        return codTipoTratamentoErroCip;
    }

    /**
     * @param codTipoTratamentoErroCip the codTipoTratamentoErroCip to set
     */
    public void setCodTipoTratamentoErroCip(Short codTipoTratamentoErroCip) {
        this.codTipoTratamentoErroCip = codTipoTratamentoErroCip;
    }

    /**
     * @return the descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * @param descTipoTratamentoErroCip the descTipoTratamentoErroCip to set
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @return the listaSituacaoMensagemDDA
     */
    public List<SituacaoMensagemDDAVO> getListaSituacaoMensagemDDA() {
        return listaSituacaoMensagemDDA;
    }

    /**
     * @param listaSituacaoMensagemDDA the listaSituacaoMensagemDDA to set
     */
    public void setListaSituacaoMensagemDDA(List<SituacaoMensagemDDAVO> listaSituacaoMensagemDDA) {
        this.listaSituacaoMensagemDDA = listaSituacaoMensagemDDA;
    }


}
