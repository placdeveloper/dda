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
public enum TipoPessoaEnum {

    PF((short) 0, "F", "Pessoa Física"),
    PJ((short) 1, "J", "Pessoa Jurídica");

    /**
     * 
     */
    private TipoPessoaEnum(short codDominioCobranca, String codDominioCip, String descTipoPessoa) {
        this.codDominioCobranca = codDominioCobranca;
        this.codDominioCip = codDominioCip;
        this.descTipoPessoa = descTipoPessoa;
    }

    private short codDominioCobranca;
    private String codDominioCip;
    private String descTipoPessoa;

    /**
     * @return the codDominioCip
     */
    public String getCodDominioCip() {
        return codDominioCip;
    }

    /**
     * @return the codDominioCobranca
     */
    public short getCodDominioCobranca() {
        return codDominioCobranca;
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
    public static TipoPessoaEnum getBy(String codDominio) {
        if (codDominio.equals("F")) {
            return TipoPessoaEnum.PF;
        } else {
            return TipoPessoaEnum.PJ;
        }
    }

    /**
     * verifica se é Pessoa Juridica
     * 
     * @return boolean
     */
    public static boolean isPessoaJuridica(String codDominioCip) {
        if (codDominioCip == null) {
            return false;
        }
        return codDominioCip.equals(PJ.getCodDominioCip());
    }

}
