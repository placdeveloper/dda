package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * CalculoBoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class CalculoBoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = -6559906178379670554L;

    private Double valorCalculadoJuros;

    private Double valorCalculadoMulta;

    private Double valorCalculadoDesconto;

    private Double valorTotalCobrar;

    private DateTimeDB dataValidadeCalculo;

    /**
     * @return o atributo valorCalculadoJuros
     */
    public Double getValorCalculadoJuros() {
        return valorCalculadoJuros;
    }

    /**
     * Define o atributo valorCalculadoJuros
     */
    public void setValorCalculadoJuros(Double valorCalculadoJuros) {
        this.valorCalculadoJuros = valorCalculadoJuros;
    }

    /**
     * @return o atributo valorCalculadoMulta
     */
    public Double getValorCalculadoMulta() {
        return valorCalculadoMulta;
    }

    /**
     * Define o atributo valorCalculadoMulta
     */
    public void setValorCalculadoMulta(Double valorCalculadoMulta) {
        this.valorCalculadoMulta = valorCalculadoMulta;
    }

    /**
     * @return o atributo valorCalculadoDesconto
     */
    public Double getValorCalculadoDesconto() {
        return valorCalculadoDesconto;
    }

    /**
     * Define o atributo valorCalculadoDesconto
     */
    public void setValorCalculadoDesconto(Double valorCalculadoDesconto) {
        this.valorCalculadoDesconto = valorCalculadoDesconto;
    }

    /**
     * @return o atributo valorTotalCobrar
     */
    public Double getValorTotalCobrar() {
        return valorTotalCobrar;
    }

    /**
     * Define o atributo valorTotalCobrar
     */
    public void setValorTotalCobrar(Double valorTotalCobrar) {
        this.valorTotalCobrar = valorTotalCobrar;
    }

    /**
     * @return o atributo dataValidadeCalculo
     */
    public DateTimeDB getDataValidadeCalculo() {
        return dataValidadeCalculo;
    }

    /**
     * Define o atributo dataValidadeCalculo
     */
    public void setDataValidadeCalculo(DateTimeDB dataValidadeCalculo) {
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

}
