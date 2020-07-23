package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * ConsultaBoletoDescontoDDAAPIDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ConsultaBoletoDescontoDDAAPIDto extends BancoobDto {

    private static final long serialVersionUID = -7626970079893419103L;

    private DateTime dataLimiteDesconto;

    private Double valorDesconto;

    private Double valorFinalBoleto;

    /**
     * @return o atributo dataLimiteDesconto
     */
    public DateTime getDataLimiteDesconto() {
        return dataLimiteDesconto;
    }

    /**
     * Define o atributo dataLimiteDesconto
     */
    public void setDataLimiteDesconto(DateTime dataLimiteDesconto) {
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
