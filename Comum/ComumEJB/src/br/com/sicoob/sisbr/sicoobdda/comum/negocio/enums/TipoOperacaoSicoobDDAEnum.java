package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum TipoOperacaoSicoobDDAEnum {

    INCLUSAO("I", "Inclusao"),
    EXCLUSAO("E", "Exclusao"),
    ALTERACAO("A", "Alteracao");

    private String codigoOperacao;
    private String descricaoOperacao;

    private TipoOperacaoSicoobDDAEnum(String codigoOperacao, String descricaoOperacao) {
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

    public static TipoOperacaoSicoobDDAEnum getBy(String codigoOperacao) {
        TipoOperacaoSicoobDDAEnum[] lista = values();
        for (TipoOperacaoSicoobDDAEnum tipoOperacao : lista) {
            if (tipoOperacao.getCodigoOperacao().equals(codigoOperacao)) {
                return tipoOperacao;
            }
        }
        return null;
    }
}
