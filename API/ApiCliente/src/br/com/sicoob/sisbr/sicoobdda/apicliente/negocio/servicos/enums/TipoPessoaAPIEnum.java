/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-api-cliente
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.enums
 * Arquivo:         TipoPessoaAPIEnum.java
 * Data Cria��o:    Aug 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums;

/**
 * TipoPessoaAPIEnum � respons�vel por
 * 
 * @author felipe.rosa
 */
public enum TipoPessoaAPIEnum {

    PF("F", "Pessoa F�sica"),
    PJ("J", "Pessoa Jur�dica");

    /**
     * 
     */
    private TipoPessoaAPIEnum(String codDominio, String descTipoPessoa) {
        this.codDominio = codDominio;
        this.descTipoPessoa = descTipoPessoa;
    }

    private String codDominio;
    private String descTipoPessoa;

    /**
     * @return the codDominio
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
     * M�todo respons�vel por
     * 
     * @param codDominio
     * @return TipoPessoaEnum
     * 
     */
    public static TipoPessoaAPIEnum getBy(String codDominio) {
        if (codDominio.equals("F")) {
            return TipoPessoaAPIEnum.PF;
        } else {
            return TipoPessoaAPIEnum.PJ;
        }
    }

}
