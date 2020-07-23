package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoTratamentoErroCipVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DadosTratamentoMensagemDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DadosTratamentoMensagemDto")
public class DadosTratamentoMensagemDTO extends BancoobDTO {

    private List<MensagemDDADTO> listaMensagens;
    private List<TipoTratamentoErroCipVO> listaTipoTratamento;

    /**
     * @return the listaMensagens
     */
    public List<MensagemDDADTO> getListaMensagens() {
        return listaMensagens;
    }

    /**
     * @param listaMensagens the listaMensagens to set
     */
    public void setListaMensagens(List<MensagemDDADTO> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    /**
     * @return the listaTipoTratamento
     */
    public List<TipoTratamentoErroCipVO> getListaTipoTratamento() {
        return listaTipoTratamento;
    }

    /**
     * @param listaTipoTratamento the listaTipoTratamento to set
     */
    public void setListaTipoTratamento(List<TipoTratamentoErroCipVO> listaTipoTratamento) {
        this.listaTipoTratamento = listaTipoTratamento;
    }


}
