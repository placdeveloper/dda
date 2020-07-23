/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         TratamentoPendenciaErroDto.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TratamentoPendenciaErroDto é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoPendenciaErroDTO")
public class TratamentoPendenciaErroDto extends BancoobDto {

    private static final long serialVersionUID = -8122425036928962647L;

    private List<PendenciaDto> listaPendencia;
    private List<ErroAgrupadoDto> listaErroAgrupado;
    private List<ErroProcessamentoCipDto> listaErroProcessamentoCip;

    /**
     * 
     */
    public TratamentoPendenciaErroDto() {
        super();
    }

    /**
     * @param listaPendencia
     * @param listaErroAgrupado
     * @param listaErroProcessamentoCip
     */
    public TratamentoPendenciaErroDto(List<PendenciaDto> listaPendencia, List<ErroAgrupadoDto> listaErroAgrupado, List<ErroProcessamentoCipDto> listaErroProcessamentoCip) {
        super();
        this.listaPendencia = listaPendencia;
        this.listaErroAgrupado = listaErroAgrupado;
        this.listaErroProcessamentoCip = listaErroProcessamentoCip;
    }

    /**
     * @return the listaPendencia
     */
    public List<PendenciaDto> getListaPendencia() {
        return listaPendencia;
    }

    /**
     * @param listaPendencia the listaPendencia to set
     */
    public void setListaPendencia(List<PendenciaDto> listaPendencia) {
        this.listaPendencia = listaPendencia;
    }

    /**
     * @return the listaErroAgrupado
     */
    public List<ErroAgrupadoDto> getListaErroAgrupado() {
        return listaErroAgrupado;
    }

    /**
     * @param listaErroAgrupado the listaErroAgrupado to set
     */
    public void setListaErroAgrupado(List<ErroAgrupadoDto> listaErroAgrupado) {
        this.listaErroAgrupado = listaErroAgrupado;
    }

    /**
     * @return the listaErroProcessamentoCip
     */
    public List<ErroProcessamentoCipDto> getListaErroProcessamentoCip() {
        return listaErroProcessamentoCip;
    }

    /**
     * @param listaErroProcessamentoCip the listaErroProcessamentoCip to set
     */
    public void setListaErroProcessamentoCip(List<ErroProcessamentoCipDto> listaErroProcessamentoCip) {
        this.listaErroProcessamentoCip = listaErroProcessamentoCip;
    }

}
