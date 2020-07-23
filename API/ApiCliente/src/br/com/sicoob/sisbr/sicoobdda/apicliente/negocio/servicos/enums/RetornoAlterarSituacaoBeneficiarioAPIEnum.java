/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-api-cliente
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.enums
 * Arquivo:         RetornoAlterarSituacaoBeneficiarioAPIEnum.java
 * Data Cria��o:    Aug 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums;

/**
 * RetornoAlterarSituacaoBeneficiarioAPIEnum � respons�vel por
 * 
 * @author felipe.rosa
 */
public enum RetornoAlterarSituacaoBeneficiarioAPIEnum {

    SUCESSO((short) 1, "Altera��o de situa��o processada com sucesso."),
    ALERTA((short) 2, "Altera��o de situa��o encaminhada � CIP. A Situa��o atual do benefici�rio n�o p�de ser alterada por existir alerta(s) em outra(s) IF(s).");

    private Short codDescricao;
    private String descricao;

    /**
     * @param codDescricao
     * @param descricao
     */
    private RetornoAlterarSituacaoBeneficiarioAPIEnum(Short codDescricao, String descricao) {
        this.codDescricao = codDescricao;
        this.descricao = descricao;
    }

    /**
     * @return the codDescricao
     */
    public Short getCodDescricao() {
        return codDescricao;
    }

    /**
     * @param codDescricao the codDescricao to set
     */
    public void setCodDescricao(Short codDescricao) {
        this.codDescricao = codDescricao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param descricao
     * @return RetornoAlterarSituacaoBeneficiarioEnum
     * 
     */
    public static RetornoAlterarSituacaoBeneficiarioAPIEnum getBy(Short codDescricao) {
        if (codDescricao.equals(RetornoAlterarSituacaoBeneficiarioAPIEnum.SUCESSO.codDescricao)) {
            return RetornoAlterarSituacaoBeneficiarioAPIEnum.SUCESSO;
        } else {
            return RetornoAlterarSituacaoBeneficiarioAPIEnum.ALERTA;
        }
    }

}
