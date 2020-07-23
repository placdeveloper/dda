package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoPadraoLeiauteCobrancaEnum � respons�vel por
 * 
 * @author Wesley.Costa
 */
public enum TipoPadraoLeiauteCobrancaEnum {

    PADRAO_LEGADO((short) 1, "PADR�O LEGADO"),
    NOVO_PADRAO_COBRANCA_LEGADO((short) 3, "NOVO PADR�O - COBRAN�A LEGADO"),
    NOVO_PADRAO_NOVO_COBRANCA((short) 4, "NOVO PADR�O - NOVO COBRAN�A");

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
