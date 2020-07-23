/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         CarteiraDeParaEnum.java
 * Data Criação:    Aug 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * CarteiraDeParaEnum
 * 
 * @author rafael.silva
 */
public enum CarteiraDeParaEnum {
    SIMPLES("Simples", 1, 1),
    VINCULADA("Vinculada", 2, 2),
    CAUCIONADA("Caucionada", 3, 3),
    DESCONTADA("Descontada", 4, 4),
    EMPRESTIMO("Empréstimo", 7, 1),
    CARTAO_CABAL("Cartão Cabal", 8, 1);

    private CarteiraDeParaEnum(String descricaoCarteira, int idCarteiraCob, int idCarteiraCip) {
        this.descricaoCarteira = descricaoCarteira;
        this.idCarteiraCob = idCarteiraCob;
        this.idCarteiraCip = idCarteiraCip;
    }

    private final String descricaoCarteira;
    private final int idCarteiraCob;
    private final int idCarteiraCip;

    /**
     * @param idCarteiraCob
     * @return Integer
     */
    public static Integer getIdCarteiraCip(int idCarteiraCob) {
        CarteiraDeParaEnum[] lista = values();
        for (CarteiraDeParaEnum carteira : lista) {
            if (carteira.getIdCateiraCob() == idCarteiraCob) {
                return carteira.idCarteiraCip;
            }
        }
        return null;
    }

    /**
     * @param idCarteiraCob
     * @return Integer
     */
    public static String getDescricaoCarteiraCob(int idCarteiraCip) {
        CarteiraDeParaEnum[] lista = values();
        for (CarteiraDeParaEnum carteira : lista) {
            if (carteira.getIdCarteiraCip() == idCarteiraCip) {
                return carteira.descricaoCarteira;
            }
        }
        return null;
    }

    /**
     * Método responsável por
     * 
     * @return int
     * 
     */
    public String getDescricaoCarteira() {
        return descricaoCarteira;
    }

    /**
     * @return the idCarteiraCob
     */
    public int getIdCateiraCob() {
        return idCarteiraCob;
    }

    /**
     * @return the idCarteiraCip
     */
    public int getIdCarteiraCip() {
        return idCarteiraCip;
    }

}
