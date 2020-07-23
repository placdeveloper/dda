package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoPadraoLeiauteCobrancaEnum é responsável por
 * 
 * @author Wesley.Costa
 */
public enum TipoPadraoLeiauteCobrancaEnum {

    PADRAO_LEGADO((short) 1, "PADRÃO LEGADO"),
    NOVO_PADRAO_COBRANCA_LEGADO((short) 3, "NOVO PADRÃO - COBRANÇA LEGADO"),
    NOVO_PADRAO_NOVO_COBRANCA((short) 4, "NOVO PADRÃO - NOVO COBRANÇA");

    private short id;
    private String descricao;

    /**
     * @param id
     * @param descricao
     */
    private TipoPadraoLeiauteCobrancaEnum(short id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * @return o atributo id
     */
    public short getId() {
        return id;
    }

    /**
     * @return o atributo descricao
     */
    public String getDescricao() {
        return descricao;
    }

}
