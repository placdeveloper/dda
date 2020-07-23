package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TratamentoMesagemDTO é responsável por
 * 
 * @author felipe.rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto")
public class TratamentoMesagemDTO extends BancoobDTO {

    private List<Long> listaIdMensagem;
    private List<Long> listaIdErroMensagem;
    private List<Long> listaIdLogRecebimento;
    private Short codTipoTratamento;

    /**
     * @return the listaIdMensagem
     */
    public List<Long> getListaIdMensagem() {
        return listaIdMensagem;
    }

    /**
     * @return the listaIdLogRecebimento
     */
    public List<Long> getListaIdLogRecebimento() {
        return listaIdLogRecebimento;
    }

    /**
     * @param listaIdLogRecebimento the listaIdLogRecebimento to set
     */
    public void setListaIdLogRecebimento(List<Long> listaIdLogRecebimento) {
        this.listaIdLogRecebimento = listaIdLogRecebimento;
    }

    /**
     * @param listaIdMensagem the listaIdMensagem to set
     */
    public void setListaIdMensagem(List<Long> listaIdMensagem) {
        this.listaIdMensagem = listaIdMensagem;
    }

    /**
     * @return the listaIdErroMensagem
     */
    public List<Long> getListaIdErroMensagem() {
        return listaIdErroMensagem;
    }

    /**
     * @param listaIdErroMensagem the listaIdErroMensagem to set
     */
    public void setListaIdErroMensagem(List<Long> listaIdErroMensagem) {
        this.listaIdErroMensagem = listaIdErroMensagem;
    }

    /**
     * @return the codTipoTratamento
     */
    public Short getCodTipoTratamento() {
        return codTipoTratamento;
    }

    /**
     * @param codTipoTratamento the codTipoTratamento to set
     */
    public void setCodTipoTratamento(Short codTipoTratamento) {
        this.codTipoTratamento = codTipoTratamento;
    }

}
