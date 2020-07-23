/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoCarteiraCobrancaEnum.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoCarteiraCobrancaEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoCarteiraCobrancaEnum {

    COM_REGISTRO("1", "Com Registro"),
    SEM_REGISTRO("2", "Sem Registro"),
    AMBAS("3", "Ambas");

    /**
     * 
     */
    private TipoCarteiraCobrancaEnum(String codDominio, String descTipoCarteira) {
        this.codDominio = codDominio;
        this.descTipoCarteira = descTipoCarteira;
    }

    private String codDominio;
    private String descTipoCarteira;

    /**
     * @return the codTipoCarteira
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipoCarteira
     */
    public String getDescTipoCarteira() {
        return descTipoCarteira;
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @param codDominio
     * @return TipoCarteiraCobrancaEnum
     * 
     */
    public static TipoCarteiraCobrancaEnum getBy(String codDominio) {
        for (TipoCarteiraCobrancaEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
