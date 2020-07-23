/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MonitoracaoArqRemessaDto.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MonitoracaoArqRemessaDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoArqRemessaDTO")
public class MonitoracaoArqRemessaDto extends BancoobDto {

    private static final long serialVersionUID = -4330571688219071607L;

    private Long arqEnviar;
    private Long arqSemProtocolo;
    private Long arqSemRetornoCIP;
    private Long arqProcessadoErro;

    private DateTimeDB dataHoraAtualizacao;

    private Integer parametroTempoAtualizacao;

    private Integer alertaEnviar;
    private Integer alertaSemProtocoloCIP;
    private Integer alertaSemRetornoCIP;
    private Integer alertaProcessadoErro;

    private Boolean bolAlertaBloqueioMotorEnvio;
    private Boolean bolAlertaGradeHoraria;
    private Boolean bolAlertaErroProtocolo;

    private List<DetMonitoracaoDto> listaDetalhamento;
    private List<TipoErroCipDto> listaErro;

    /**
     * 
     */
    public MonitoracaoArqRemessaDto() {
        super();
    }

    /**
     * Construtor Monitoracao de Arquivos de Remessa
     * 
     * @param arqEnviar
     * @param arqSemProtocolo
     * @param arqSemRetornoCIP
     * @param arqProcessadoErro
     * @param bolAlertaGradeHoraria
     */
    public MonitoracaoArqRemessaDto(Long arqEnviar, Long arqSemProtocolo, Long arqSemRetornoCIP, Long arqProcessadoErro, Boolean bolAlertaGradeHoraria, Boolean bolAlertaErroProtocolo) {
        super();
        this.arqEnviar = arqEnviar;
        this.arqSemProtocolo = arqSemProtocolo;
        this.arqSemRetornoCIP = arqSemRetornoCIP;
        this.arqProcessadoErro = arqProcessadoErro;
        this.bolAlertaGradeHoraria = bolAlertaGradeHoraria;
        this.bolAlertaErroProtocolo = bolAlertaErroProtocolo;
        this.dataHoraAtualizacao = new DateTimeDB();
    }

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
    public DateTimeDB getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * Define o atributo dataHoraAtualizacao
     */
    public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
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
    public List<DetMonitoracaoDto> getListaDetalhamento() {
        return listaDetalhamento;
    }

    /**
     * Define o atributo listaDetalhamento
     */
    public void setListaDetalhamento(List<DetMonitoracaoDto> listaDetalhamento) {
        this.listaDetalhamento = listaDetalhamento;
    }

    /**
     * @return o atributo listaErro
     */
    public List<TipoErroCipDto> getListaErro() {
        return listaErro;
    }

    /**
     * Define o atributo listaErro
     */
    public void setListaErro(List<TipoErroCipDto> listaErro) {
        this.listaErro = listaErro;
    }

}
