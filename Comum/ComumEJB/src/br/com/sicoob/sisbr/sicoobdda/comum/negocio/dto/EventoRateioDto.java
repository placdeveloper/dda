package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;

/**
 * EventoRateioDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EventoRateioDTO")
public class EventoRateioDto extends BancoobDto {

    private static final long serialVersionUID = -3101994817876811974L;

    private Long idEventoConsolidado;
    private DateTimeDB dataEvento;
    private String descEventoTarifavel;
    private BigDecimal valorUnitarioBancoob;
    private BigDecimal valorUnitarioCIP;
    private Integer qtdApuradaSicoob;
    private Integer qtdCIP;
    private BigDecimal desvioPadrao;

    public EventoRateioDto() {
    }

    public EventoRateioDto(Long idEventoConsolidado, Date dataEvento, String descEventoTarifavel, BigDecimal valorUnitarioBancoob, BigDecimal valorUnitarioCIP,
            Integer qtdApuradaSicoob, Integer qtdCIP) {
        this.idEventoConsolidado = idEventoConsolidado;
        this.dataEvento = dataEvento != null ? new DateTimeDB(dataEvento.getTime()) : null;
        this.descEventoTarifavel = descEventoTarifavel;
        this.valorUnitarioBancoob = valorUnitarioBancoob;
        this.valorUnitarioCIP = valorUnitarioCIP;
        this.qtdApuradaSicoob = qtdApuradaSicoob;
        this.qtdCIP = qtdCIP;
    }

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
    public DateTimeDB getDataEvento() {
        return dataEvento;
    }

    /**
     * Define o atributo dataEvento
     */
    public void setDataEvento(DateTimeDB dataEvento) {
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
     * Define o atributo valorUnitario
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
     * Método responsável por calcular o valor apurado do Sicoob
     * 
     * @return
     */
    public BigDecimal getValorApuradoSicoob() {
        return valorUnitarioCIP.multiply(new BigDecimal(qtdApuradaSicoob));
    }

    /**
     * Método responsável por calcular o valor apurado da CIP
     * 
     * @return
     */
    public BigDecimal getValorCIP() {
        return qtdCIP > 0 ? valorUnitarioCIP.multiply(new BigDecimal(qtdCIP)) : BigDecimal.ZERO;
    }

    /**
     * Método responsável por obter o valor formatado
     * 
     * @return
     */
    public String getValorCIPFormatado() {
        BigDecimal valorCIP = getValorCIP();

        if (valorCIP.equals(BigDecimal.ZERO)) {
            return "-";
        }

        return FormatadorUtil.formatarValorReal(valorCIP.doubleValue());
    }

    /**
     * Método responsável por obter a diferença das quantidades
     * 
     * @return
     */
    protected Integer getDiferenca() {
        return qtdApuradaSicoob - qtdCIP;
    }

    /**
     * Método responsável por obter a diferença da quantidade e valor
     * 
     * @return
     */
    public String getDifQtdValor() {
        String resultado;

        if (qtdCIP > 0) {
            resultado = formatarDiferencaEvento(new BigDecimal(getDiferenca()), valorUnitarioCIP);
        } else {
            resultado = "-";
        }

        return resultado;
    }

    /**
     * Método responsável por obter o desvio positivo formatado
     * 
     * @return
     */
    public String getDesvioQtdValorMaior() {
        String resultado;

        if (qtdCIP > 0 && desvioPadrao.doubleValue() > 0) {
            resultado = formatarDiferencaEvento(getDesvioPadraoMaior(), valorUnitarioCIP);
        } else {
            resultado = "-";
        }

        return resultado;
    }

    /**
     * Método responsável por obter o desvio positivo
     * 
     * @return
     */
    private BigDecimal getDesvioPadraoMaior() {
        BigDecimal qtd = new BigDecimal(qtdApuradaSicoob);

        return qtd.add(qtd.multiply(desvioPadrao).divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     * Método responsável por obter o desvio negativo formatado
     * 
     * @return
     */
    public String getDesvioQtdValorMenor() {
        String resultado;

        if (qtdCIP > 0 && desvioPadrao.doubleValue() > 0) {
            resultado = formatarDiferencaEvento(getDesvioPadraoMenor(), valorUnitarioCIP);
        } else {
            resultado = "-";
        }

        return resultado;
    }

    /**
     * Método responsável por obter o desvio negativo
     * 
     * @return
     */
    private BigDecimal getDesvioPadraoMenor() {
        BigDecimal qtd = new BigDecimal(qtdApuradaSicoob);

        return qtd.subtract(qtd.multiply(desvioPadrao).divide(Constantes.CEM, Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     * Método responsável por formatar
     * 
     * @param diferenca
     * @param valorUnitario
     * @return
     */
    private String formatarDiferencaEvento(BigDecimal diferenca, BigDecimal valorUnitario) {
        return FormatadorUtil.formatarValor(diferenca.intValue(), 0) + " / "
                + (FormatadorUtil.formatarValorReal(valorUnitario.multiply(diferenca).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue()));
    }

}
