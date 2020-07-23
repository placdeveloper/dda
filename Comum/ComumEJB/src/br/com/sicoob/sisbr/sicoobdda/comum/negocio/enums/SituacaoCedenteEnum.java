package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum SituacaoCedenteEnum {

    INATIVO(0),
    ATIVO(1),
    CANCELADO(2);

    private long codigo;

    /**
     * Construtor
     * 
     * @param codigo
     */
    private SituacaoCedenteEnum(long codigo) {
        this.codigo = codigo;
    }

    /**
     * Método responsável por getCodigo
     * 
     * @return long
     * 
     */
    public long getCodigo() {
        return codigo;
    }

}
