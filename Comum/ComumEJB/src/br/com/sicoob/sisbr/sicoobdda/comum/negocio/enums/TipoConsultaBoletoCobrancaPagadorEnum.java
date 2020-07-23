package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum TipoConsultaBoletoCobrancaPagadorEnum {
    TITULOS_VENCIDOS((short) 1, "Títulos de Cobrança Vencidos"),
    TITULOS_A_VENCER((short) 2, "Títulos de Cobrança a Vencer");

    private Short idTipoConsulta;
    private String descTipoConsulta;

    private TipoConsultaBoletoCobrancaPagadorEnum(Short idTipoConsulta, String descTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
        this.descTipoConsulta = descTipoConsulta;
    }

    /**
     * @return the idTipoConsulta
     */
    public Short getIdTipoConsulta() {
        return idTipoConsulta;
    }

    /**
     * @param idTipoConsulta the idTipoConsulta to set
     */
    public void setIdTipoConsulta(Short idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
    }

    /**
     * @return the descTipoConsulta
     */
    public String getDescTipoConsulta() {
        return descTipoConsulta;
    }

    /**
     * @param descTipoConsulta the descTipoConsulta to set
     */
    public void setDescTipoConsulta(String descTipoConsulta) {
        this.descTipoConsulta = descTipoConsulta;
    }

}
