package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TratamentoPendenciaErroDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoPendenciaErroDto")
public class TratamentoPendenciaErroDTO extends BancoobDTO {

    private List<PendenciaDTO> listaPendencia;
    private List<ErroAgrupadoDTO> listaErroAgrupado;
    private List<ErroProcessamentoCipDTO> listaErroProcessamentoCip;

    /**
     * @return the listaPendencia
     */
    public List<PendenciaDTO> getListaPendencia() {
        return listaPendencia;
    }

    /**
     * @param listaPendencia the listaPendencia to set
     */
    public void setListaPendencia(List<PendenciaDTO> listaPendencia) {
        this.listaPendencia = listaPendencia;
    }

    /**
     * @return the listaErroAgrupado
     */
    public List<ErroAgrupadoDTO> getListaErroAgrupado() {
        return listaErroAgrupado;
    }

    /**
     * @param listaErroAgrupado the listaErroAgrupado to set
     */
    public void setListaErroAgrupado(List<ErroAgrupadoDTO> listaErroAgrupado) {
        this.listaErroAgrupado = listaErroAgrupado;
    }

    /**
     * @return the listaErroProcessamentoCip
     */
    public List<ErroProcessamentoCipDTO> getListaErroProcessamentoCip() {
        return listaErroProcessamentoCip;
    }

    /**
     * @param listaErroProcessamentoCip the listaErroProcessamentoCip to set
     */
    public void setListaErroProcessamentoCip(List<ErroProcessamentoCipDTO> listaErroProcessamentoCip) {
        this.listaErroProcessamentoCip = listaErroProcessamentoCip;
    }


}
