/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         CodigoSituacaoRecebivelEnum.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * @author samuell.ramos
 */
public enum CodigoSituacaoRecebivelEnum {

    A_DESCONTAR(1, "A Descontar"),
    LIQUIDADO(2, "Liquidado"),
    PRORROGADO(3, "Prorrogado"),
    ABATIDO(4, "Abatido"),
    CANCELADO(5, "Cancelado"),
    APROVADO(6, "Aprovado"),
    NAO_APROVADO(7, "Não Aprovado"),
    MOVIDO_CARTEIRA_SIMPLES(8, "Movido Carteira Simples"),
    EFETIVADO(9, "Efetivado");

    private Integer id;
    private String descricao;

    private CodigoSituacaoRecebivelEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Recupera enum por identificador.
     * 
     * @param id {@link Integer}.
     * @return {@link CodigoSituacaoRecebivelEnum}.
     */
    public static CodigoSituacaoRecebivelEnum getPorId(Integer id) {
        for (CodigoSituacaoRecebivelEnum enumm : CodigoSituacaoRecebivelEnum.values()) {
            if (enumm.getId().equals(id)) {
                return enumm;
            }

        }
        return null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the codigoSituacao to set
     */
    public void setId(Integer id) {
        this.id = id;
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
}
