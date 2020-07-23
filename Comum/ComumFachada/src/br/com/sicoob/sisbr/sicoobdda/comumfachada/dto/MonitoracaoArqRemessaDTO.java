package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto")
public class MonitoracaoArqRemessaDTO extends BancoobDTO {

    private Long arqEnviar;
    private Long arqSemProtocolo;
    private Long arqSemRetornoCIP;
    private Long arqProcessadoErro;
    private DateTime dataHoraAtualizacao;
    private Integer parametroTempoAtualizacao;
    private Integer alertaEnviar;
    private Integer alertaSemProtocoloCIP;
    private Integer alertaSemRetornoCIP;
    private Integer alertaProcessadoErro;
    private Boolean bolAlertaBloqueioMotorEnvio;
    private Boolean bolAlertaGradeHoraria;
    private Boolean bolAlertaErroProtocolo;
    private List<DetMonitoracaoDTO> listaDetalhamento;
    private List<TipoErroCipDTO> listaErro;

    /**
     * @return o atributo arqEnviar
     */
    public Long getArqEnviar() {
        return arqEnviar;
    }

    /**
     * Define o atributo arqEnviar
     */
    public void setArqEnviar(Long arqEnviar) {
        this.arqEnviar = arqEnviar;
    }

    /**
     * @return o atributo arqSemProtocolo
     */
    public Long getArqSemProtocolo() {
        return arqSemProtocolo;
    }

    /**
     * Define o atributo arqSemProtocolo
     */
    public void setArqSemProtocolo(Long arqSemProtocolo) {
        this.arqSemProtocolo = arqSemProtocolo;
    }

    /**
     * @return o atributo arqSemRetornoCIP
     */
    public Long getArqSemRetornoCIP() {
        return arqSemRetornoCIP;
    }

    /**
     * Define o atributo arqSemRetornoCIP
     */
    public void setArqSemRetornoCIP(Long arqSemRetornoCIP) {
        this.arqSemRetornoCIP = arqSemRetornoCIP;
    }

    /**
     * @return o atributo arqProcessadoErro
     */
    public Long getArqProcessadoErro() {
        return arqProcessadoErro;
    }

    /**
     * Define o atributo arqProcessadoErro
     */
    public void setArqProcessadoErro(Long arqProcessadoErro) {
        this.arqProcessadoErro = arqProcessadoErro;
    }

    /**
     * @return o atributo dataHoraAtualizacao
     */
    public DateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return o atributo parametroTempoAtualizacao
     */
    public Integer getParametroTempoAtualizacao() {
        return parametroTempoAtualizacao;
    }

    /**
     * Define o atributo parametroTempoAtualizacao
     */
    public void setParametroTempoAtualizacao(Integer parametroTempoAtualizacao) {
        this.parametroTempoAtualizacao = parametroTempoAtualizacao;
    }

    /**
     * @return o atributo alertaEnviar
     */
    public Integer getAlertaEnviar() {
        return alertaEnviar;
    }

    /**
     * Define o atributo alertaEnviar
     */
    public void setAlertaEnviar(Integer alertaEnviar) {
        this.alertaEnviar = alertaEnviar;
    }

    /**
     * @return o atributo alertaSemProtocoloCIP
     */
    public Integer getAlertaSemProtocoloCIP() {
        return alertaSemProtocoloCIP;
    }

    /**
     * Define o atributo alertaSemProtocoloCIP
     */
    public void setAlertaSemProtocoloCIP(Integer alertaSemProtocoloCIP) {
        this.alertaSemProtocoloCIP = alertaSemProtocoloCIP;
    }

    /**
     * @return o atributo alertaSemRetornoCIP
     */
    public Integer getAlertaSemRetornoCIP() {
        return alertaSemRetornoCIP;
    }

    /**
     * Define o atributo alertaSemRetornoCIP
     */
    public void setAlertaSemRetornoCIP(Integer alertaSemRetornoCIP) {
        this.alertaSemRetornoCIP = alertaSemRetornoCIP;
    }

    /**
     * @return o atributo alertaProcessadoErro
     */
    public Integer getAlertaProcessadoErro() {
        return alertaProcessadoErro;
    }

    /**
     * Define o atributo alertaProcessadoErro
     */
    public void setAlertaProcessadoErro(Integer alertaProcessadoErro) {
        this.alertaProcessadoErro = alertaProcessadoErro;
    }

    /**
     * @return o atributo bolAlertaBloqueioMotorEnvio
     */
    public Boolean getBolAlertaBloqueioMotorEnvio() {
        return bolAlertaBloqueioMotorEnvio;
    }

    /**
     * Define o atributo bolAlertaBloqueioMotorEnvio
     */
    public void setBolAlertaBloqueioMotorEnvio(Boolean bolAlertaBloqueioMotorEnvio) {
        this.bolAlertaBloqueioMotorEnvio = bolAlertaBloqueioMotorEnvio;
    }

    /**
     * @return o atributo bolAlertaGradeHoraria
     */
    public Boolean getBolAlertaGradeHoraria() {
        return bolAlertaGradeHoraria;
    }

    /**
     * Define o atributo bolAlertaGradeHoraria
     */
    public void setBolAlertaGradeHoraria(Boolean bolAlertaGradeHoraria) {
        this.bolAlertaGradeHoraria = bolAlertaGradeHoraria;
    }

    /**
     * @return o atributo bolAlertaErroProtocolo
     */
    public Boolean getBolAlertaErroProtocolo() {
        return bolAlertaErroProtocolo;
    }

    /**
     * Define o atributo bolAlertaErroProtocolo
     */
    public void setBolAlertaErroProtocolo(Boolean bolAlertaErroProtocolo) {
        this.bolAlertaErroProtocolo = bolAlertaErroProtocolo;
    }

    /**
     * @return o atributo listaDetalhamento
     */
    public List<DetMonitoracaoDTO> getListaDetalhamento() {
        return listaDetalhamento;
    }

    /**
     * Define o atributo listaDetalhamento
     */
    public void setListaDetalhamento(List<DetMonitoracaoDTO> listaDetalhamento) {
        this.listaDetalhamento = listaDetalhamento;
    }

    /**
     * @return o atributo listaErro
     */
    public List<TipoErroCipDTO> getListaErro() {
        return listaErro;
    }

    /**
     * Define o atributo listaErro
     */
    public void setListaErro(List<TipoErroCipDTO> listaErro) {
        this.listaErro = listaErro;
    }

}
