/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         MensagemDDAVO.java
 * Data Criação:    Aug 14, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDAVO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA")
public class MensagemDDAVO extends BancoobVO {

    private Long id;

    private MensagemDDAVO mensagemOrigem;

    private TipoMensagemDDAVO tipoMensagemDDA;

    private DateTime dataHoraMovimento;

    private DateTime dataHoraMensagem;

    private Integer qtdTotalRegistrosRecebidos;

    private DateTime dataHoraProtocolo;

    private String numOperacao;

    private String descErroProtocolo;

    private String xmlMensagem;

    private Boolean bolOrigemSicoob;

    private Boolean selecionado;

    /**
     * @return o atributo tipoMensagemDDA
     */
    public TipoMensagemDDAVO getTipoMensagemDDA() {
        return tipoMensagemDDA;
    }

    /**
     * Define o atributo tipoMensagemDDA
     */
    public void setTipoMensagemDDA(TipoMensagemDDAVO tipoMensagemDDA) {
        this.tipoMensagemDDA = tipoMensagemDDA;
    }

    /**
     * @return o atributo dataHoraMovimento
     */
    public DateTime getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    /**
     * Define o atributo dataHoraMovimento
     */
    public void setDataHoraMovimento(DateTime dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }

    /**
     * @return o atributo dataHoraMensagem
     */
    public DateTime getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * Define o atributo dataHoraMensagem
     */
    public void setDataHoraMensagem(DateTime dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

    /**
     * @return o atributo qtdTotalRegistrosRecebidos
     */
    public Integer getQtdTotalRegistrosRecebidos() {
        return qtdTotalRegistrosRecebidos;
    }

    /**
     * Define o atributo qtdTotalRegistrosRecebidos
     */
    public void setQtdTotalRegistrosRecebidos(Integer qtdTotalRegistrosRecebidos) {
        this.qtdTotalRegistrosRecebidos = qtdTotalRegistrosRecebidos;
    }

    /**
     * @return o atributo dataHoraProtocolo
     */
    public DateTime getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    /**
     * Define o atributo dataHoraProtocolo
     */
    public void setDataHoraProtocolo(DateTime dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    /**
     * @return o atributo numOperacao
     */
    public String getNumOperacao() {
        return numOperacao;
    }

    /**
     * Define o atributo numOperacao
     */
    public void setNumOperacao(String numOperacao) {
        this.numOperacao = numOperacao;
    }

    /**
     * @return o atributo descErroProtocolo
     */
    public String getDescErroProtocolo() {
        return descErroProtocolo;
    }

    /**
     * Define o atributo descErroProtocolo
     */
    public void setDescErroProtocolo(String descErroProtocolo) {
        this.descErroProtocolo = descErroProtocolo;
    }

    /**
     * @return o atributo xmlMensagem
     */
    public String getXmlMensagem() {
        return xmlMensagem;
    }

    /**
     * Define o atributo xmlMensagem
     */
    public void setXmlMensagem(String xmlMensagem) {
        this.xmlMensagem = xmlMensagem;
    }

    /**
     * @return o atributo bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * Define o atributo bolOrigemSicoob
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo mensagemOrigem
     */
    public MensagemDDAVO getMensagemOrigem() {
        return mensagemOrigem;
    }

    /**
     * Define o atributo mensagemOrigem
     */
    public void setMensagemOrigem(MensagemDDAVO mensagemOrigem) {
        this.mensagemOrigem = mensagemOrigem;
    }

    /**
     * @return o atributo selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * Define o atributo selecionado
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }
}
