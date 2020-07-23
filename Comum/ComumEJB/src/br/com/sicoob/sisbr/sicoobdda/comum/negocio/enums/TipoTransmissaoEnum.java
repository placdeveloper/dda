package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoTransmissaoEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoTransmissaoEnum {

    ARQUIVO_EXTERNO_E_USERMSG("A"),
    ARQUIVO_EXTERNO("E"),
    USERMSG("U");

    /**
     *
     */
    private TipoTransmissaoEnum(String cod) {
        this.codIndicador = cod;
    }

    private String codIndicador;

    /**
     * @return the codDominio
     */
    public String getCodIndicador() {
        return codIndicador;
    }

}
