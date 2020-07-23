package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

public enum TipoPessoaBacenEnum {

    FISICA("F", "Pessoa Física"),
    JURIDICA("J", "Pessoa Jurídica");

    private String codTipoPessoa;
    private String descTipoPessoa;

    private TipoPessoaBacenEnum(String codTipoPessoa, String descTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
        this.descTipoPessoa = descTipoPessoa;
    }

    public String getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(String codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    public String getDescTipoPessoa() {
        return descTipoPessoa;
    }

    public void setDescTipoPessoa(String descTipoPessoa) {
        this.descTipoPessoa = descTipoPessoa;
    }

    public static TipoPessoaBacenEnum getBy(String codTipoPessoa) {
        TipoPessoaBacenEnum[] lista = values();
        for (TipoPessoaBacenEnum tipoPessoa : lista) {
            if (tipoPessoa.getCodTipoPessoa().equals(codTipoPessoa)) {
                return tipoPessoa;
            }
        }
        return null;
    }

}
