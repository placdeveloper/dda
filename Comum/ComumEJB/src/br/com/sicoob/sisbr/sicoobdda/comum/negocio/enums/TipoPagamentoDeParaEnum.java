/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoPagamentoDeParaEnum.java
 * Data Criação:    Dez 15, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoPagamentoDeParaEnum
 * 
 * @author rafael.silva
 */
public enum TipoPagamentoDeParaEnum {
    NORMAL(1, 3), // CIP = VENCIMENTO DETERMINADO
    A_VISTA(2, 3), // CIP = VENCIMENTO DETERMINADO
    CONTRA_APRESENTACAO(3, 1); // CIP = A VISTA

    private TipoPagamentoDeParaEnum(int codTipoPagamentoCob, int codTipoPagamentoCip) {
        this.codTipoPagamentoCob = codTipoPagamentoCob;
        this.codTipoPagamentoCip = codTipoPagamentoCip;
    }

    private final int codTipoPagamentoCob;
    private final int codTipoPagamentoCip;

    /**
     * @param codTipoPagamentoCob
     * @return Integer
     */
    public static Integer getIdTipoPagamentoCip(int codTipoPagamentoCob) {
        TipoPagamentoDeParaEnum[] lista = values();
        for (TipoPagamentoDeParaEnum tipoPagamento : lista) {
            if (tipoPagamento.getCodTipoPagamentoCob() == codTipoPagamentoCob) {
                return tipoPagamento.codTipoPagamentoCip;
            }
        }
        return null;
    }

    /**
     * @return the codTipoPagamentoCob
     */
    public int getCodTipoPagamentoCob() {
        return codTipoPagamentoCob;
    }

    /**
     * @return the codTipoPagamentoCip
     */
    public int getCodTipoPagamentoCip() {
        return codTipoPagamentoCip;
    }

}
