package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum TipoDocBeneficiarioEnum {
    BENEFICIARIO("BENEFICIARIO"),
    PAGADOR("PAGADOR"),
    BOLETO("BOLETO"),
    OUTROS("OUTROS");

    private final String descTipoCod;

    private TipoDocBeneficiarioEnum(String descTipoCod) {
        this.descTipoCod = descTipoCod;
    }

    /**
     * @return o atributo descTipoCod
     */
    public String getDescTipoCod() {
        return descTipoCod;
    }

}
