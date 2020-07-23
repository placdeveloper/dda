/**
 * Projeto:         Cobran√ßa Banc√°ria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoProdutoConvenioEnum.java
 * Data Cria√ß√£o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoProdutoConvenioEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoProdutoConvenioEnum {

    BOLETO_DE_COBRANCA("01", "Boleto de CobranÁa"),
    BOLETO_DE_PROPOSTA("02", "Boleto de Proposta"),
    CARTAO_DE_CREDITO("03", "Cart„o de CrÈdito"),
    CONTA_BANCARIA("04", "Conta Banc·ria"),
    OUTROS("05", "Outros");

    /**
     * 
     */
    private TipoProdutoConvenioEnum(String codDominio, String descTipoProduto) {
        this.codDominio = codDominio;
        this.descTipoProduto = descTipoProduto;
    }

    private String codDominio;
    private String descTipoProduto;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipoProduto
     */
    public String getDescTipoProduto() {
        return descTipoProduto;
    }

}
