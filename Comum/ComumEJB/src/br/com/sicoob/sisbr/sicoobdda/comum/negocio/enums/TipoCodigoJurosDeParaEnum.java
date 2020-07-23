/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoCodigoJurosDeParaEnum.java
 * Data Criação:    Aug 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoCodigoJurosDeParaEnum
 * 
 * @author rafael.silva
 */
public enum TipoCodigoJurosDeParaEnum {
    VALOR(2, 1),
    PERCENTUAL_MES(3, 3),
    ISENTO(1, 5);

    private TipoCodigoJurosDeParaEnum(int codTipoJurosCob, int codTipoJurosCip) {
        this.codTipoJurosCob = codTipoJurosCob;
        this.codTipoJurosCip = codTipoJurosCip;
    }

    private final int codTipoJurosCob;
    private final int codTipoJurosCip;

    /**
     * @param codTipoJurosCob
     * @return Integer
     */
    public static Integer getTipoCodigoJurosDeParaEnum(int codTipoJurosCob) {
        TipoCodigoJurosDeParaEnum[] lista = values();
        for (TipoCodigoJurosDeParaEnum tipoJuros : lista) {
            if (tipoJuros.getCodTipoJurosCob() == codTipoJurosCob) {
                return tipoJuros.codTipoJurosCip;
            }
        }
        return null;
    }

    /**
     * @return the codTipoJurosCob
     */
    public int getCodTipoJurosCob() {
        return codTipoJurosCob;
    }

    /**
     * @return the codTipoJurosCip
     */
    public int getCodTipoJurosCip() {
        return codTipoJurosCip;
    }

}
