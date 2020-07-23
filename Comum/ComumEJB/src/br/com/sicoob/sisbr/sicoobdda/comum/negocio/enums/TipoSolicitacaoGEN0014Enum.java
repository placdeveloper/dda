/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoSolicitacaoGEN0014Enum.java
 * Data Criação:    Jan 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;


/**
 * TipoSolicitacaoGEN0014Enum é responsável por 
 * 
 * @author Felipe.Rosa
 */
public enum TipoSolicitacaoGEN0014Enum {

    BENEFICIARIO("B"),
    PAGADOR("P"),
    TITULO("T");

    /**
     * @param codDominio
     */
    private TipoSolicitacaoGEN0014Enum(String codDominio) {
        this.codDominio = codDominio;
    }

    private String codDominio;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @param codDominio the codDominio to set
     */
    public void setCodDominio(String codDominio) {
        this.codDominio = codDominio;
    }

    /**
     * Método responsável por
     * 
     * @param codDominio
     * @return TipoSolicitacaoGEN0014Enum
     * 
     */
    public static TipoSolicitacaoGEN0014Enum getBy(String codDominio) {
        for (TipoSolicitacaoGEN0014Enum tipo : values()) {
            if (tipo.getCodDominio().equals(codDominio)) {
                return tipo;
            }
        }
        return null;
    }
}
