/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoPessoaEnum.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoPessoaEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoContaEnum {

    CONTA_CORRENTE("CC", "Conta Corrente"),
    CONTA_DEPOSITO("CD", "Conta de Dep�sito"),
    CONTA_INVESTIMENTO("CI", "Conta de Investimento"),
    CONTA_PAGAMENTO("PG", "Conta de Pagamento"),
    CONTA_POUPANCA("PP", "Poupan�a"),
    CONTA_SALARIO("CS", "Conta Sal�rio"),
    PROPRIO_BANCO("PP", "Pr�prio Banco");

    private TipoContaEnum(String codDominio, String descTipo) {
        this.codDominio = codDominio;
        this.descTipo = descTipo;
    }

    private String codDominio;
    private String descTipo;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descTipo
     */
    public String getDescTipo() {
        return descTipo;
    }

    /**
     * M�todo respons�vel por obter o TipoConta por codDominio
     * 
     * @param string
     * @return TipoContaEnum
     * 
     */
    public static TipoContaEnum getBy(String codDominio) {
        for (TipoContaEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
