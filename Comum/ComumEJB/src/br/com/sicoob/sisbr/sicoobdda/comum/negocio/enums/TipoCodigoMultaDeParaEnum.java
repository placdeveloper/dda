/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoCodigoMultaDeParaEnum.java
 * Data Criação:    Aug 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoCodigoMultaDeParaEnum
 * 
 * @author rafael.silva
 */
public enum TipoCodigoMultaDeParaEnum {
    VALOR_FIXO(2, 1),
    PERCENTUAL(3, 2),
    ISENTO(1, 3);

    private TipoCodigoMultaDeParaEnum(int codTipoMultaCob, int codTipoMultaCip) {
        this.codTipoMultaCob = codTipoMultaCob;
        this.codTipoMultaCip = codTipoMultaCip;
    }

    private final int codTipoMultaCob;
    private final int codTipoMultaCip;

    /**
     * @param codTipoMultaCob
     * @return Integer
     */
    public static Integer getTipoCodigoMultaDeParaEnum(int codTipoMultaCob) {
        TipoCodigoMultaDeParaEnum[] lista = values();
        for (TipoCodigoMultaDeParaEnum tipoMulta : lista) {
            if (tipoMulta.getCodTipoMultaCob() == codTipoMultaCob) {
                return tipoMulta.codTipoMultaCip;
            }
        }
        return null;
    }

    /**
     * @return the codTipoMultaCob
     */
    public int getCodTipoMultaCob() {
        return codTipoMultaCob;
    }

    /**
     * @return the codTipoMultaCip
     */
    public int getCodTipoMultaCip() {
        return codTipoMultaCip;
    }

}
