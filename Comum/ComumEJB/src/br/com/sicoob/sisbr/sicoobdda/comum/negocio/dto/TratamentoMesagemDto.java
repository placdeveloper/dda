/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         TratamentoMesagemDto.java
 * Data Criação:    Sep 15, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TratamentoMesagemDto é responsável por 
 * 
 * @author felipe.rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TratamentoMesagemDTO")
public class TratamentoMesagemDto extends BancoobDto {

    private static final long serialVersionUID = -1083182212053103301L;

    private List<Long> listaIdMensagem;
    private List<Long> listaIdErroMensagem;
    private List<Long> listaIdLogRecebimento;
    private Short codTipoTratamento;

    /**
     * 
     */
    public TratamentoMesagemDto() {
        super();
    }

    /**
     * @param codTipoTratamento
     */
    public TratamentoMesagemDto(Short codTipoTratamento) {
        super();
        this.codTipoTratamento = codTipoTratamento;
    }

    /**
     * Construtor QUERY - OBTER_LISTA_TRATAMENTO_AUTOMATIZADO
     * 
     * @param idMensagem
     * @param idErroMensagem
     * @param codTipoTratamento
     */
    public TratamentoMesagemDto(Long idMensagem, Long idErroMensagem, Short codTipoTratamento) {
        super();
        this.listaIdMensagem = criaListaId(idMensagem);
        this.listaIdErroMensagem = criaListaId(idErroMensagem);
        this.codTipoTratamento = codTipoTratamento;
    }

    /**
     * @param listaIdMensagem
     * @param codTipoTratamento
     */
    public TratamentoMesagemDto(List<Long> listaIdMensagem, Short codTipoTratamento) {
        super();
        this.listaIdMensagem = listaIdMensagem;
        this.codTipoTratamento = codTipoTratamento;
    }

    /**
     * @return the listaIdMensagem
     */
    public List<Long> getListaIdMensagem() {
        return listaIdMensagem;
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
     * Método responsável por
     * 
     * @param id
     * @return List<Long>
     * 
     */
    private List<Long> criaListaId(Long id) {
        List<Long> listaId = new ArrayList<Long>();
        listaId.add(id);
        return listaId;
    }

}
