/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-api-cliente
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.enums
 * Arquivo:         SituacaoBeneficiarioAPIEnum.java
 * Data Criação:    Aug 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums;

/**
 * SituacaoBeneficiarioAPIEnum é responsável por
 * 
 * @author felipe.rosa
 */
public enum SituacaoBeneficiarioAPIEnum {

    APTO("A", "Apto"),
    EM_ANALISE("E", "Em Análise"),
    INAPTO("I", "Inapto");

    private SituacaoBeneficiarioAPIEnum(String codDominio, String descSituacao) {
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
     * Método responsável por
     * 
     * @param codDominio
     * @return SituacaoBeneficiarioEnum
     * 
     */
    public static SituacaoBeneficiarioAPIEnum getBy(String codDominio) {
        for (SituacaoBeneficiarioAPIEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
