/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoPessoaEnum.java
 * Data CriaÃ§Ã£o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades.enums;

/**
 * TipoPessoaEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoPessoaSacadorAvalistaEnum {

    ISENTO_NAO_INFORMADO("0", "Isento / Não Informado"),
    CPF("1", "CPF"),
    CNPJ("2", "CNPJ"),
    PISPASEP("3", "PIS/PASEP"),
    OUTROS("9", "Outros");

    /**
     * 
     */
    private TipoPessoaSacadorAvalistaEnum(String codDominio, String descTipoPessoa) {
        this.codDominio = codDominio;
        this.descTipoPessoa = descTipoPessoa;
    }

    private String codDominio;
    private String descTipoPessoa;

    /**
     * @return o atributo codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipoPessoa
     */
    public String getDescTipoPessoa() {
        return descTipoPessoa;
    }

    /**
     * 
     * @param codDominio
     * @return TipoPessoaEnum
     * 
     */
    public static TipoPessoaSacadorAvalistaEnum getBy(String codDominio) {
        if (codDominio == null) {
            return ISENTO_NAO_INFORMADO;
        }
        for (TipoPessoaSacadorAvalistaEnum tipoPessoa : values()) {
            if (codDominio.equals(tipoPessoa.getCodDominio())) {
                return tipoPessoa;
            }
        }
        return ISENTO_NAO_INFORMADO;
    }

}
