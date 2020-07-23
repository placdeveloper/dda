package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * EventoTarifavelDTO
 * 
 * @author Samuell.Ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto")
public class EventoTarifavelDTO extends BancoobDTO {

    private Integer idEventoTarifavelDDATarifa;
    private Integer codEventoTarifavel;
    private String descEventoTarifavelExtrato;
    private String descEventoTarifavelManual;
    private String descTipoEventoTarifavel;
    private DateTime dataInicioVigencia;
    private DateTime dataFimVigencia;
    private BigDecimal valorCIP;
    private BigDecimal valorBancoob;
    private String status;
    private Integer diaVencimento;
    private Boolean bolTarifavel;

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
    public DateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * Define o atributo dataInicioVigencia
     */
    public void setDataInicioVigencia(DateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    /**
     * @return o atributo dataFimVigencia
     */
    public DateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * Define o atributo dataFimVigencia
     */
    public void setDataFimVigencia(DateTime dataFimVigencia) {
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
     * @return o atributo idEventoTarifavelDDATarifa
     */
    public Integer getIdEventoTarifavelDDATarifa() {
        return idEventoTarifavelDDATarifa;
    }

    /**
     * Define o atributo idEventoTarifavelDDATarifa
     */
    public void setIdEventoTarifavelDDATarifa(Integer idEventoTarifavelDDATarifa) {
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
