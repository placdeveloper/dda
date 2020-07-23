package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DescontoBoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class DescontoBoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = 4798353764643714833L;

    private DateTimeDB dataDesconto;

    private String codTipoDesconto;

    private BigDecimal valorPercentualDesconto;

    /**
     * 
     */
    public DescontoBoletoDDADto() {
        super();
    }

    /**
     * @param dataDesconto
     * @param codTipoDesconto
     * @param valorPercentualDesconto
     */
    public DescontoBoletoDDADto(DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        super();
        this.dataDesconto = dataDesconto;
        this.codTipoDesconto = codTipoDesconto;
        this.valorPercentualDesconto = valorPercentualDesconto;
    }

    /**
     * @return o atributo dataDesconto
     */
    public DateTimeDB getDataDesconto() {
        return dataDesconto;
    }

    /**
     * Define o atributo dataDesconto
     */
    public void setDataDesconto(DateTimeDB dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    /**
     * @return o atributo codTipoDesconto
     */
    public String getCodTipoDesconto() {
        return codTipoDesconto;
    }

    /**
     * Define o atributo codTipoDesconto
     */
    public void setCodTipoDesconto(String codTipoDesconto) {
        this.codTipoDesconto = codTipoDesconto;
    }

    /**
     * @return o atributo valorPercentualDesconto
     */
    public BigDecimal getValorPercentualDesconto() {
        return valorPercentualDesconto;
    }

    /**
     * Define o atributo valorPercentualDesconto
     */
    public void setValorPercentualDesconto(BigDecimal valorPercentualDesconto) {
        this.valorPercentualDesconto = valorPercentualDesconto;
    }

}
