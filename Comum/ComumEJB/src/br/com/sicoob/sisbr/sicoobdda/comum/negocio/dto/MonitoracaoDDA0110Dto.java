/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MonitoracaoMQ.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MonitoracaoMQ é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDDA0110DTO")
public class MonitoracaoDDA0110Dto extends BancoobDto {

    private static final long serialVersionUID = 3179642665835192475L;

    private Long dda0110Sucesso;
    private Long dda0110Rejeitada;
    private Long dda0110SucessoTotal;
    private Long dda0110RejeitadaTotal;

    private DateTimeDB dataHoraAtualizacao;
    private String dataHoraAfericao;
    private String horaAfericao;

    private Integer parametroTempoAtualizacao;
    private Double percentualAceitavelSLA;

    private Boolean bolConsultaCipHabilitada;
    private Boolean bolContingenciaHabilitadaAutomatica;
    private Boolean bolContingenciaHabilitadaManual;

    /**
     * 
     */
    public MonitoracaoDDA0110Dto() {
        super();
    }

    /**
     * @param dda0110SucessoTotal
     * @param dda0110RejeitadaTotal
     * @param dda0110Sucesso
     * @param dda0110Rejeitada
     * @param dataHoraAfericao
     * @param horaAfericao
     * @param parametroTempoAtualizacao
     */
    public MonitoracaoDDA0110Dto(Long dda0110SucessoTotal, Long dda0110RejeitadaTotal, Long dda0110Sucesso, Long dda0110Rejeitada, String dataHoraAfericao, String horaAfericao,
            Integer parametroTempoAtualizacao) {
        super();
        this.dda0110Sucesso = dda0110Sucesso;
        this.dda0110Rejeitada = dda0110Rejeitada;
        this.dda0110SucessoTotal = dda0110SucessoTotal;
        this.dda0110RejeitadaTotal = dda0110RejeitadaTotal;
        this.dataHoraAfericao = dataHoraAfericao;
        this.horaAfericao = horaAfericao;
        this.parametroTempoAtualizacao = parametroTempoAtualizacao;
        this.dataHoraAtualizacao = new DateTimeDB();
    }

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
