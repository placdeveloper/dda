package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * IndicadorManutencaoEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoManutencaoEnum {

    INCLUSAO("I"),
    EXCLUSAO("E"),
    MANTER("M"),
    ALTERAR("A");

    /**
     *
     */
    private TipoManutencaoEnum(String cod) {
        this.codIndicador = cod;
    }

    private String codIndicador;

    /**
     * @return the codDominio
     */
    public String getCodIndicador() {
        return codIndicador;
    }

    /**
     * Método responsável por
     * 
     * @param codIndicador
     * @return TipoManutencaoEnum
     * 
     */
    public static TipoManutencaoEnum getBy(String codIndicador) {
        for (TipoManutencaoEnum tipoEnum : values()) {
            if (tipoEnum.getCodIndicador().equals(codIndicador)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
