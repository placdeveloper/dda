/**
 * Projeto:         SICOOB DDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         EventoTarifavelDto.java
 * Data Criação:    Dec 05, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EventoTarifavelDto
 * 
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoTarifavelDTO")
public class EventoTarifavelDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 3669713602984620845L;

    private Long idEventoTarifavelDDATarifa;
    private Integer codEventoTarifavel;
    private Integer diaVencimento;
    private String descEventoTarifavelManual;
    private String descEventoTarifavelExtrato;
    private String descTipoEventoTarifavel;
    private DateTimeDB dataInicioVigencia;
    private DateTimeDB dataFimVigencia;
    private BigDecimal valorCIP;
    private BigDecimal valorBancoob;
    private String status;
    private Boolean bolTarifavel;

    /**
     * 
     */
    public EventoTarifavelDto() {
        super();
    }

    /**
     * @param codEventoTarifavel
     * @param descEventoTarifavelExtrato
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param bolTarifavel
     * @param valorCIP
     * @param valorBancoob
     * @param descTipoEventoTarifavel
     * @param status
     * @param idEventoTarifavelDDATarifa
     */
    public EventoTarifavelDto(Integer codEventoTarifavel, String descEventoTarifavelExtrato, Date dataInicioVigencia, Date dataFimVigencia, Boolean bolTarifavel,
            BigDecimal valorCIP,
            BigDecimal valorBancoob, String descTipoEventoTarifavel, String status, Long idEventoTarifavelDDATarifa) {
        this.status = status;
        this.codEventoTarifavel = codEventoTarifavel;
        this.descEventoTarifavelExtrato = descEventoTarifavelExtrato;
        this.dataInicioVigencia = ObjectUtil.isNull(dataInicioVigencia) ? null : new DateTimeDB(dataInicioVigencia.getTime());
        this.dataFimVigencia = ObjectUtil.isNull(dataFimVigencia) ? null : new DateTimeDB(dataFimVigencia.getTime());
        this.valorCIP = valorCIP;
        this.valorBancoob = valorBancoob;
        this.idEventoTarifavelDDATarifa = idEventoTarifavelDDATarifa;
        this.bolTarifavel = bolTarifavel;
        this.descTipoEventoTarifavel = descTipoEventoTarifavel;
    }

    /**
     * @return o atributo status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o atributo status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return o atributo codEventoTarifavel
     */
    public Integer getCodEventoTarifavel() {
        return codEventoTarifavel;
    }

    /**
     * Define o atributo codEventoTarifavel
     */
    public void setCodEventoTarifavel(Integer codEventoTarifavel) {
        this.codEventoTarifavel = codEventoTarifavel;
    }

    /**
     * @return o atributo descEventoTarifavelManual
     */
    public String getDescEventoTarifavelManual() {
        return descEventoTarifavelManual;
    }

    /**
     * Define o atributo descEventoTarifavelManual
     */
    public void setDescEventoTarifavelManual(String descEventoTarifavelManual) {
        this.descEventoTarifavelManual = descEventoTarifavelManual;
    }

    /**
     * @return o atributo dataInicioVigencia
     */
    public DateTimeDB getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * Define o atributo dataInicioVigencia
     */
    public void setDataInicioVigencia(DateTimeDB dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    /**
     * @return o atributo dataFimVigencia
     */
    public DateTimeDB getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * Define o atributo dataFimVigencia
     */
    public void setDataFimVigencia(DateTimeDB dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    /**
     * @return o atributo valorCIP
     */
    public BigDecimal getValorCIP() {
        return valorCIP;
    }

    /**
     * Define o atributo valorCIP
     */
    public void setValorCIP(BigDecimal valorCIP) {
        this.valorCIP = valorCIP;
    }

    /**
     * @return o atributo valorBancoob
     */
    public BigDecimal getValorBancoob() {
        return valorBancoob;
    }

    /**
     * Define o atributo valorBancoob
     */
    public void setValorBancoob(BigDecimal valorBancoob) {
        this.valorBancoob = valorBancoob;
    }

    /**
     * @return o atributo idEventoTarifavelDDATarifa
     */
    public Long getIdEventoTarifavelDDATarifa() {
        return idEventoTarifavelDDATarifa;
    }

    /**
     * Define o atributo idEventoTarifavelDDATarifa
     */
    public void setIdEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) {
        this.idEventoTarifavelDDATarifa = idEventoTarifavelDDATarifa;
    }

    /**
     * @return o atributo diaVencimento
     */
    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    /**
     * Define o atributo diaVencimento
     */
    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    /**
     * @return o atributo descTipoEventoTarifavel
     */
    public String getDescTipoEventoTarifavel() {
        return descTipoEventoTarifavel;
    }

    /**
     * Define o atributo descTipoEventoTarifavel
     */
    public void setDescTipoEventoTarifavel(String descTipoEventoTarifavel) {
        this.descTipoEventoTarifavel = descTipoEventoTarifavel;
    }

    /**
     * @return o atributo bolTarifavel
     */
    public Boolean getBolTarifavel() {
        return bolTarifavel;
    }

    /**
     * Define o atributo bolTarifavel
     */
    public void setBolTarifavel(Boolean bolTarifavel) {
        this.bolTarifavel = bolTarifavel;
    }

	/**
	 * @return the descEventoTarifavelExtrato
	 */
	public String getDescEventoTarifavelExtrato() {
		return descEventoTarifavelExtrato;
	}

	/**
	 * @param descEventoTarifavelExtrato the descEventoTarifavelExtrato to set
	 */
	public void setDescEventoTarifavelExtrato(String descEventoTarifavelExtrato) {
		this.descEventoTarifavelExtrato = descEventoTarifavelExtrato;
	}

}
