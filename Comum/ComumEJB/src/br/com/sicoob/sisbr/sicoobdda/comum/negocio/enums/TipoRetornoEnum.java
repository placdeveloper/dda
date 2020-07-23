/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoRetornoEnum.java
 * Data Criação:    May 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoRetornoEnum é responsável por
 * 
 * @author Rafael.Silva
 */
public enum TipoRetornoEnum {

    M("M", "Mensagem"), // *
    X("X", "Arquivo XML (FTP)"), // *
    L("L", "Arquivo anexado no USERMSG XML"),
    P("P", "Arquivo Posicional (FTP)"),
    S("S", "Arquivo XML (PSTA)"),
    U("U", "Arquivo anexado no USERMSG Posicional");

    TipoRetornoEnum(String codDominio, String descTipoRetorno) {
        this.codDominio = codDominio;
        this.descTipoRetorno = descTipoRetorno;
    }

    private String codDominio;
    private String descTipoRetorno;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipoRetorno
     */
    public String getDescTipoRetorno() {
        return descTipoRetorno;
    }

}
