/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         MensagemDDADTO.java
 * Data Criação:    Aug 31, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.io.Serializable;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDADTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.MensagemDDADto")
public class MensagemDDADTO extends BancoobDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -117943412054456042L;
	private Long idMensagem;
    private String codTipoMensagem;
    private String numOperacao;
    private Boolean bolOrigemSicoob;
    private DateTime dataHoraMensagem;
    private DateTime dataHoraMensagemInicial;
    private DateTime dataHoraMensagemFinal;
    private DateTime dataHoraProtocolo;
    private DateTime dataHoraCadastroInicial;
    private DateTime dataHoraCadastroFinal;
    private Short statusEnvio;
    private Boolean selecionado;

    private String descTipoTratamentoErroCip;

    private DateTime movimentoDataInicial;
    private DateTime movimentoDataFinal;
    
    
    
    
	public MensagemDDADTO() {		
	}

	/**
     * @return the idMensagem
     */
    public Long getIdMensagem() {
        return idMensagem;
    }

    /**
     * @param idMensagem the idMensagem to set
     */
    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    /**
     * @return the codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * @param codTipoMensagem the codTipoMensagem to set
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return the numOperacao
     */
    public String getNumOperacao() {
        return numOperacao;
    }

    /**
     * @param numOperacao the numOperacao to set
     */
    public void setNumOperacao(String numOperacao) {
        this.numOperacao = numOperacao;
    }

    /**
     * @return the bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * @param bolOrigemSicoob the bolOrigemSicoob to set
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @return the dataHoraProtocolo
     */
    public DateTime getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    /**
     * @param dataHoraProtocolo the dataHoraProtocolo to set
     */
    public void setDataHoraProtocolo(DateTime dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    /**
     * @return the statusEnvio
     */
    public Short getStatusEnvio() {
        return statusEnvio;
    }

    /**
     * @return o atributo descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * Define o atributo descTipoTratamentoErroCip
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @param statusEnvio the statusEnvio to set
     */
    public void setStatusEnvio(Short statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

    /**
     * @return the selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the movimentoDataInicial
     */
    public DateTime getMovimentoDataInicial() {
        return movimentoDataInicial;
    }

    /**
     * @param movimentoDataInicial the movimentoDataInicial to set
     */
    public void setMovimentoDataInicial(DateTime movimentoDataInicial) {
        this.movimentoDataInicial = movimentoDataInicial;
    }

    /**
     * @return the movimentoDataFinal
     */
    public DateTime getMovimentoDataFinal() {
        return movimentoDataFinal;
    }

    /**
     * @param movimentoDataFinal the movimentoDataFinal to set
     */
    public void setMovimentoDataFinal(DateTime movimentoDataFinal) {
        this.movimentoDataFinal = movimentoDataFinal;
    }

    public DateTime getDataHoraMensagemInicial() {
        return dataHoraMensagemInicial;
    }

    public void setDataHoraMensagemInicial(DateTime dataHoraMensagemInicial) {
        this.dataHoraMensagemInicial = dataHoraMensagemInicial;
    }

    public DateTime getDataHoraMensagemFinal() {
        return dataHoraMensagemFinal;
    }

    public void setDataHoraMensagemFinal(DateTime dataHoraMensagemFinal) {
        this.dataHoraMensagemFinal = dataHoraMensagemFinal;
    }

    public DateTime getDataHoraCadastroInicial() {
        return dataHoraCadastroInicial;
    }

    public void setDataHoraCadastroInicial(DateTime dataHoraCadastroInicial) {
        this.dataHoraCadastroInicial = dataHoraCadastroInicial;
    }

    public DateTime getDataHoraCadastroFinal() {
        return dataHoraCadastroFinal;
    }

    public void setDataHoraCadastroFinal(DateTime dataHoraCadastroFinal) {
        this.dataHoraCadastroFinal = dataHoraCadastroFinal;
    }

    public DateTime getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    public void setDataHoraMensagem(DateTime dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

}
