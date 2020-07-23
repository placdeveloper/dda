package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author felipe.rosa
 */
/**
 * MonitoracaoDemaisMensagensDTO é responsável por
 * 
 * @author felipe.rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto")
public class MonitoracaoDemaisMensagensDTO extends BancoobDTO {

    private Long msgEnviar;
    private Long msgSemRetornoSSPB;
    private Long msgSemRetornoCIP;
    private Long msgRetornoErro;
    private Integer alertaEnviar;
    private Integer alertaSemRetornoSSPB;
    private Integer alertaSemRetornoCIP;
    private Integer alertaRetornoErro;
    private DateTimeDB dataHoraAtualizacao;
    private Integer parametroTempoAtualizacao;
    private Boolean bolAlertaBloqueioMotorEnvio;
    private Boolean bolAlertaGradeHoraria;
    private Boolean bolAlertaErroProtocolo;
    private List<DetMonitoracaoDTO> listaDetalhamento;
    private List<TipoErroCipDTO> listaErro;

    /**
     * @return o atributo msgEnviar
     */
    public Long getMsgEnviar() {
        return msgEnviar;
    }

    /**
     * Define o atributo msgEnviar
     */
    public void setMsgEnviar(Long msgEnviar) {
        this.msgEnviar = msgEnviar;
    }

    /**
     * @return o atributo msgSemRetornoSSPB
     */
    public Long getMsgSemRetornoSSPB() {
        return msgSemRetornoSSPB;
    }

    /**
     * Define o atributo msgSemRetornoSSPB
     */
    public void setMsgSemRetornoSSPB(Long msgSemRetornoSSPB) {
        this.msgSemRetornoSSPB = msgSemRetornoSSPB;
    }

    /**
     * @return o atributo msgSemRetornoCIP
     */
    public Long getMsgSemRetornoCIP() {
        return msgSemRetornoCIP;
    }

    /**
     * Define o atributo msgSemRetornoCIP
     */
    public void setMsgSemRetornoCIP(Long msgSemRetornoCIP) {
        this.msgSemRetornoCIP = msgSemRetornoCIP;
    }

    /**
     * @return o atributo msgRetornoErro
     */
    public Long getMsgRetornoErro() {
        return msgRetornoErro;
    }

    /**
     * Define o atributo msgRetornoErro
     */
    public void setMsgRetornoErro(Long msgRetornoErro) {
        this.msgRetornoErro = msgRetornoErro;
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
     * @return o atributo alertaSemRetornoSSPB
     */
    public Integer getAlertaSemRetornoSSPB() {
        return alertaSemRetornoSSPB;
    }

    /**
     * Define o atributo alertaSemRetornoSSPB
     */
    public void setAlertaSemRetornoSSPB(Integer alertaSemRetornoSSPB) {
        this.alertaSemRetornoSSPB = alertaSemRetornoSSPB;
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
     * @return o atributo alertaRetornoErro
     */
    public Integer getAlertaRetornoErro() {
        return alertaRetornoErro;
    }

    /**
     * Define o atributo alertaRetornoErro
     */
    public void setAlertaRetornoErro(Integer alertaRetornoErro) {
        this.alertaRetornoErro = alertaRetornoErro;
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
