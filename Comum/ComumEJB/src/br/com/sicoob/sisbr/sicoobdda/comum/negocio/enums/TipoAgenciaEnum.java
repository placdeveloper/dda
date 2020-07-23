/**
 * Projeto:         Cobran√ßa Banc√°ria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoAgenciaDestinoEnum.java
 * Data Cria√ß√£o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoAgenciaDestinoEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoAgenciaEnum {

    FISICA("F", "FÌsica"),
    VIRTUAL("V", "Virtual"),
    NAO_INFORMADO("N", "N„o informado");

    private TipoAgenciaEnum(String codDominio, String descTipoAgencia) {
        this.codDominio = codDominio;
        this.descTipoAgencia = descTipoAgencia;
    }

    private String codDominio;
    private String descTipoAgencia;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipoAgencia
     */
    public String getDescTipoAgencia() {
        return descTipoAgencia;
    }

    /**
     * MÈtodo respons·vel por recuperar TipoAgenciaEnum por codDominio
     * 
     * @param string
     * @return TipoAgenciaEnum
     * 
     */
    public static TipoAgenciaEnum getBy(String codDominio) {
        for (TipoAgenciaEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }
}
