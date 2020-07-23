package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.tipos.DateTime;
import java.math.BigDecimal;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto")
public class EventoRateioDTO extends BancoobDTO {

    private Long idEventoConsolidado;
    private DateTime dataEvento;
    private String descEventoTarifavel;
    private BigDecimal valorUnitarioBancoob;
    private BigDecimal valorUnitarioCIP;
    private Integer qtdApuradaSicoob;
    private Integer qtdCIP;
    private BigDecimal desvioPadrao;
    private Boolean selecionado;

    /**
     * @return o atributo idEventoConsolidado
     */
    public Long getIdEventoConsolidado() {
        return idEventoConsolidado;
    }

    /**
     * Define o atributo idEventoConsolidado
     */
    public void setIdEventoConsolidado(Long idEventoConsolidado) {
        this.idEventoConsolidado = idEventoConsolidado;
    }

    /**
     * @return o atributo dataEvento
     */
    public DateTime getDataEvento() {
        return dataEvento;
    }

    /**
     * Define o atributo dataEvento
     */
    public void setDataEvento(DateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    /**
     * @return o atributo descEventoTarifavel
     */
    public String getDescEventoTarifavel() {
        return descEventoTarifavel;
    }

    /**
     * Define o atributo descEventoTarifavel
     */
    public void setDescEventoTarifavel(String descEventoTarifavel) {
        this.descEventoTarifavel = descEventoTarifavel;
    }

    /**
     * @return o atributo valorUnitarioBancoob
     */
    public BigDecimal getValorUnitarioBancoob() {
        return valorUnitarioBancoob;
    }

    /**
     * Define o atributo valorUnitarioBancoob
     */
    public void setValorUnitarioBancoob(BigDecimal valorUnitarioBancoob) {
        this.valorUnitarioBancoob = valorUnitarioBancoob;
    }

    /**
     * @return o atributo valorUnitarioCIP
     */
    public BigDecimal getValorUnitarioCIP() {
        return valorUnitarioCIP;
    }

    /**
     * Define o atributo valorUnitarioCIP
     */
    public void setValorUnitarioCIP(BigDecimal valorUnitarioCIP) {
        this.valorUnitarioCIP = valorUnitarioCIP;
    }

    /**
     * @return o atributo qtdApuradaSicoob
     */
    public Integer getQtdApuradaSicoob() {
        return qtdApuradaSicoob;
    }

    /**
     * Define o atributo qtdApuradaSicoob
     */
    public void setQtdApuradaSicoob(Integer qtdApuradaSicoob) {
        this.qtdApuradaSicoob = qtdApuradaSicoob;
    }

    /**
     * @return o atributo qtdCIP
     */
    public Integer getQtdCIP() {
        return qtdCIP;
    }

    /**
     * Define o atributo qtdCIP
     */
    public void setQtdCIP(Integer qtdCIP) {
        this.qtdCIP = qtdCIP;
    }

    /**
     * @return o atributo desvioPadrao
     */
    public BigDecimal getDesvioPadrao() {
        return desvioPadrao;
    }

    /**
     * Define o atributo desvioPadrao
     */
    public void setDesvioPadrao(BigDecimal desvioPadrao) {
        this.desvioPadrao = desvioPadrao;
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
