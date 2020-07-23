package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * ConsultaBoletoDescontoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ConsultaBoletoDescontoDDADto extends BancoobDto {

    private static final long serialVersionUID = -1455179052596244070L;

    private DateTimeDB dataLimiteDesconto;

    private Double valorDesconto;

    private Double valorFinalBoleto;

    /**
     * @return o atributo dataLimiteDesconto
     */
    public DateTimeDB getDataLimiteDesconto() {
        return dataLimiteDesconto;
    }

    /**
     * Define o atributo dataLimiteDesconto
     */
    public void setDataLimiteDesconto(DateTimeDB dataLimiteDesconto) {
        this.dataLimiteDesconto = dataLimiteDesconto;
    }

    /**
     * @return o atributo valorDesconto
     */
    public Double getValorDesconto() {
        return valorDesconto;
    }

    /**
     * Define o atributo valorDesconto
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return o atributo valorFinalBoleto
     */
    public Double getValorFinalBoleto() {
        return valorFinalBoleto;
    }

    /**
     * Define o atributo valorFinalBoleto
     */
    public void setValorFinalBoleto(Double valorFinalBoleto) {
        this.valorFinalBoleto = valorFinalBoleto;
    }

}
