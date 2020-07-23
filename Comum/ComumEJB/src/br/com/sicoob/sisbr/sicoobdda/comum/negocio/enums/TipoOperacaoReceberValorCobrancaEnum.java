package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum TipoOperacaoReceberValorCobrancaEnum {

    DEC("DEC", "Transferencia entre contas do Bancoob"),
    STR("STR", "Transferencia para Instituicoes Externas"),
    DEV("DEV", "Devolucao");

    private String codigoOperacao;
    private String descricaoOperacao;

    private TipoOperacaoReceberValorCobrancaEnum(String codigoOperacao, String descricaoOperacao) {
        this.codigoOperacao = codigoOperacao;
        this.descricaoOperacao = descricaoOperacao;
    }

    public String getCodigoOperacao() {
        return codigoOperacao;
    }

    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    /**
     * @return the descricaoOperacao
     */
    public String getDescricaoOperacao() {
        return descricaoOperacao;
    }

    /**
     * @param descricaoOperacao the descricaoOperacao to set
     */
    public void setDescricaoOperacao(String descricaoOperacao) {
        this.descricaoOperacao = descricaoOperacao;
    }

    public static TipoOperacaoReceberValorCobrancaEnum getBy(String codigoOperacao) {
        TipoOperacaoReceberValorCobrancaEnum[] lista = values();
        for (TipoOperacaoReceberValorCobrancaEnum tipoOperacao : lista) {
            if (tipoOperacao.getCodigoOperacao().equals(codigoOperacao)) {
                return tipoOperacao;
            }
        }
        return null;
    }
}
