package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * RetornoAlterarSituacaoBeneficiarioEnum
 * 
 * @author Rafael.Silva
 */
public enum RetornoAlterarSituacaoBeneficiarioEnum {

    SUCESSO((short) 1, "Alteração de situação processada com sucesso."),
    ALERTA((short) 2, "Alteração de situação encaminhada à CIP. A Situação atual do beneficiário não pôde ser alterada por existir alerta(s) em outra(s) IF(s).");

    private Short codDescricao;
    private String descricao;

    /**
     * @param codDescricao
     * @param descricao
     */
    private RetornoAlterarSituacaoBeneficiarioEnum(Short codDescricao, String descricao) {
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
     * Método responsável por
     * 
     * @param descricao
     * @return RetornoAlterarSituacaoBeneficiarioEnum
     * 
     */
    public static RetornoAlterarSituacaoBeneficiarioEnum getBy(Short codDescricao) {
        if (codDescricao.equals(RetornoAlterarSituacaoBeneficiarioEnum.SUCESSO.codDescricao)) {
            return RetornoAlterarSituacaoBeneficiarioEnum.SUCESSO;
        } else {
            return RetornoAlterarSituacaoBeneficiarioEnum.ALERTA;
        }
    }
}
