/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         DadosTratamentoMensagemDto.java
 * Data Criação:    Sep 14, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DadosTratamentoMensagemDto é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DadosTratamentoMensagemDTO")
public class DadosTratamentoMensagemDto extends BancoobDto{

    private static final long serialVersionUID = -6510288235892813893L;

    private List<MensagemErroDto> listaMensagens;
    private List<TipoTratamentoErroCip> listaTipoTratamento;

    /**
     * 
     */
    public DadosTratamentoMensagemDto() {
        super();
    }

    /**
     * @param listaMensagens
     * @param listaTipoTratamento
     */
    public DadosTratamentoMensagemDto(List<MensagemErroDto> listaMensagens, List<TipoTratamentoErroCip> listaTipoTratamento) {
        super();
        this.listaMensagens = listaMensagens;
        this.listaTipoTratamento = listaTipoTratamento;
    }

    /**
     * @return the listaMensagens
     */
    public List<MensagemErroDto> getListaMensagens() {
        return listaMensagens;
    }

    /**
     * @param listaMensagens the listaMensagens to set
     */
    public void setListaMensagens(List<MensagemErroDto> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    /**
     * @return the listaTipoTratamento
     */
    public List<TipoTratamentoErroCip> getListaTipoTratamento() {
        return listaTipoTratamento;
    }

    /**
     * @param listaTipoTratamento the listaTipoTratamento to set
     */
    public void setListaTipoTratamento(List<TipoTratamentoErroCip> listaTipoTratamento) {
        this.listaTipoTratamento = listaTipoTratamento;
    }


}
