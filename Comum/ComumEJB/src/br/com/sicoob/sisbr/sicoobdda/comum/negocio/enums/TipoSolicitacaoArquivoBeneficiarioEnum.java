/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoAgenciaDestinoEnum.java
 * Data Criaçao:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;


/**
 * TipoSolicitacaoArquivoBeneficiarioEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoSolicitacaoArquivoBeneficiarioEnum {

    INVENTARIO("ID", "Inventario de Beneficiarios Destinatarios"),
    CARGA_INICIAL("CD", "Carga Inicial de Beneficiarios Destinatarios");

    /**
     * @param codDominio
     * @param descDominio
     */
    private TipoSolicitacaoArquivoBeneficiarioEnum(String codDominio, String descDominio) {
        this.codDominio = codDominio;
        this.descDominio = descDominio;
    }

    private String codDominio;
    private String descDominio;

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
     * @return the descDominio
     */
    public String getDescDominio() {
        return descDominio;
    }

    /**
     * @param descDominio the descDominio to set
     */
    public void setDescDominio(String descDominio) {
        this.descDominio = descDominio;
    }

    /**
     * Método responsável por
     * 
     * @param codDominio
     * @return TipoSolicitacaoArquivoBeneficiarioEnum
     * 
     */
    public static TipoSolicitacaoArquivoBeneficiarioEnum getBy(String codDominio) {
        for (TipoSolicitacaoArquivoBeneficiarioEnum tipo : values()) {
            if (tipo.getCodDominio().equals(codDominio)) {
                return tipo;
            }
        }
        return null;
    }
}
