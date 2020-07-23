package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * JurosBoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class JurosBoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = -694145459741324295L;

    private DateTimeDB dataJuros;

    private Character codJuros;

    private Double valorPercentualJuros;

    /**
     * @return o atributo dataJuros
     */
    public DateTimeDB getDataJuros() {
        return dataJuros;
    }

    /**
     * Define o atributo dataJuros
     */
    public void setDataJuros(DateTimeDB dataJuros) {
        this.dataJuros = dataJuros;
    }

    /**
     * @return o atributo codJuros
     */
    public Character getCodJuros() {
        return codJuros;
    }

    /**
     * Define o atributo codJuros
     */
    public void setCodJuros(Character codJuros) {
        this.codJuros = codJuros;
    }

    /**
     * @return o atributo valorPercentualJuros
     */
    public Double getValorPercentualJuros() {
        return valorPercentualJuros;
    }

    /**
     * Define o atributo valorPercentualJuros
     */
    public void setValorPercentualJuros(Double valorPercentualJuros) {
        this.valorPercentualJuros = valorPercentualJuros;
    }

}
