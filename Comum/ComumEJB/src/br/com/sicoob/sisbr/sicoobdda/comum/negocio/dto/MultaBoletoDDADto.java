package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * MultaBoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class MultaBoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = -8607190291282918966L;

    private DateTimeDB dataMulta;

    private Character codMulta;

    private Double valorPercentualMulta;

    /**
     * @return o atributo dataMulta
     */
    public DateTimeDB getDataMulta() {
        return dataMulta;
    }

    /**
     * Define o atributo dataMulta
     */
    public void setDataMulta(DateTimeDB dataMulta) {
        this.dataMulta = dataMulta;
    }

    /**
     * @return o atributo codMulta
     */
    public Character getCodMulta() {
        return codMulta;
    }

    /**
     * Define o atributo codMulta
     */
    public void setCodMulta(Character codMulta) {
        this.codMulta = codMulta;
    }

    /**
     * @return o atributo valorPercentualMulta
     */
    public Double getValorPercentualMulta() {
        return valorPercentualMulta;
    }

    /**
     * Define o atributo valorPercentualMulta
     */
    public void setValorPercentualMulta(Double valorPercentualMulta) {
        this.valorPercentualMulta = valorPercentualMulta;
    }

}
