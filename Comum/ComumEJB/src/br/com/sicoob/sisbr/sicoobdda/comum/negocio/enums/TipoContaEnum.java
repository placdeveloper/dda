/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         TipoPessoaEnum.java
 * Data CriaÃ§Ã£o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoPessoaEnum
 * 
 * @author Rafael.Silva
 */
public enum TipoContaEnum {

    CONTA_CORRENTE("CC", "Conta Corrente"),
    CONTA_DEPOSITO("CD", "Conta de Depósito"),
    CONTA_INVESTIMENTO("CI", "Conta de Investimento"),
    CONTA_PAGAMENTO("PG", "Conta de Pagamento"),
    CONTA_POUPANCA("PP", "Poupança"),
    CONTA_SALARIO("CS", "Conta Salário"),
    PROPRIO_BANCO("PP", "Próprio Banco");

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
     * Método responsável por obter o TipoConta por codDominio
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
