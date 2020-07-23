/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         SituacaoBeneficiarioEnum.java
 * Data Cria√ß√£o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * SituacaoBeneficiarioEnum
 * 
 * @author Rafael.Silva
 */
public enum SituacaoBeneficiarioEnum {

    APTO("A", "Apto"),
    EM_ANALISE("E", "Em An·lise"),
    INAPTO("I", "Inapto");

    private SituacaoBeneficiarioEnum(String codDominio, String descSituacao) {
        this.codDominio = codDominio;
        this.descSituacao = descSituacao;
    }

    private String codDominio;
    private String descSituacao;

    /**
     * @return the descSituacao
     */
    public String getDescSituacao() {
        return descSituacao;
    }

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * 
     * MÈtodo respons·vel por
     * 
     * @param codDominio
     * @return SituacaoBeneficiarioEnum
     * 
     */
    public static SituacaoBeneficiarioEnum getBy(String codDominio) {
        for (SituacaoBeneficiarioEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
