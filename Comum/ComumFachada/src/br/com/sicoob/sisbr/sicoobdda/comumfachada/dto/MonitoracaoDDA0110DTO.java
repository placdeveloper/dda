package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoMQDto")
public class MonitoracaoDDA0110DTO extends BancoobDTO {

    private Long dda0110Sucesso;
    private Long dda0110Rejeitada;
    private Long dda0110SucessoTotal;
    private Long dda0110RejeitadaTotal;

    private DateTime dataHoraAtualizacao;
    private String dataHoraAfericao;
    private String horaAfericao;

    private Integer parametroTempoAtualizacao;
    private Double percentualAceitavelSLA;

    private Boolean bolConsultaCipHabilitada;
    private Boolean bolContingenciaHabilitadaAutomatica;
    private Boolean bolContingenciaHabilitadaManual;

    /**
     * @return o atributo dda0110Sucesso
     */
    public Long getDda0110Sucesso() {
        return dda0110Sucesso;
    }

    /**
     * Define o atributo dda0110Sucesso
     */
    public void setDda0110Sucesso(Long dda0110Sucesso) {
        this.dda0110Sucesso = dda0110Sucesso;
    }

    /**
     * @return o atributo dda0110Rejeitada
     */
    public Long getDda0110Rejeitada() {
        return dda0110Rejeitada;
    }

    /**
     * Define o atributo dda0110Rejeitada
     */
    public void setDda0110Rejeitada(Long dda0110Rejeitada) {
        this.dda0110Rejeitada = dda0110Rejeitada;
    }

    /**
     * @return o atributo dda0110SucessoTotal
     */
    public Long getDda0110SucessoTotal() {
        return dda0110SucessoTotal;
    }

    /**
     * Define o atributo dda0110SucessoTotal
     */
    public void setDda0110SucessoTotal(Long dda0110SucessoTotal) {
        this.dda0110SucessoTotal = dda0110SucessoTotal;
    }

    /**
     * @return o atributo dda0110RejeitadaTotal
     */
    public Long getDda0110RejeitadaTotal() {
        return dda0110RejeitadaTotal;
    }

    /**
     * Define o atributo dda0110RejeitadaTotal
     */
    public void setDda0110RejeitadaTotal(Long dda0110RejeitadaTotal) {
        this.dda0110RejeitadaTotal = dda0110RejeitadaTotal;
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
     * @return o atributo dataHoraAfericao
     */
    public String getDataHoraAfericao() {
        return dataHoraAfericao;
    }

    /**
     * Define o atributo dataHoraAfericao
     */
    public void setDataHoraAfericao(String dataHoraAfericao) {
        this.dataHoraAfericao = dataHoraAfericao;
    }

    /**
     * @return o atributo horaAfericao
     */
    public String getHoraAfericao() {
        return horaAfericao;
    }

    /**
     * Define o atributo horaAfericao
     */
    public void setHoraAfericao(String horaAfericao) {
        this.horaAfericao = horaAfericao;
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
     * @return o atributo percentualAceitavelSLA
     */
    public Double getPercentualAceitavelSLA() {
        return percentualAceitavelSLA;
    }

    /**
     * Define o atributo percentualAceitavelSLA
     */
    public void setPercentualAceitavelSLA(Double percentualAceitavelSLA) {
        this.percentualAceitavelSLA = percentualAceitavelSLA;
    }

    /**
     * @return o atributo bolConsultaCipHabilitada
     */
    public Boolean getBolConsultaCipHabilitada() {
        return bolConsultaCipHabilitada;
    }

    /**
     * Define o atributo bolConsultaCipHabilitada
     */
    public void setBolConsultaCipHabilitada(Boolean bolConsultaCipHabilitada) {
        this.bolConsultaCipHabilitada = bolConsultaCipHabilitada;
    }

    /**
     * @return bolContingenciaHabilitadaAutomatica
     */
    public Boolean getBolContingenciaHabilitadaAutomatica() {
        return bolContingenciaHabilitadaAutomatica;
    }

    /**
     * @param bolContingenciaHabilitadaAutomatica
     */
    public void setBolContingenciaHabilitadaAutomatica(Boolean bolContingenciaHabilitadaAutomatica) {
        this.bolContingenciaHabilitadaAutomatica = bolContingenciaHabilitadaAutomatica;
    }

    /**
     * @return bolContingenciaHabilitadaManual
     */
    public Boolean getBolContingenciaHabilitadaManual() {
        return bolContingenciaHabilitadaManual;
    }

    /**
     * @param bolContingenciaHabilitadaManual
     */
    public void setBolContingenciaHabilitadaManual(Boolean bolContingenciaHabilitadaManual) {
        this.bolContingenciaHabilitadaManual = bolContingenciaHabilitadaManual;
    }
}
