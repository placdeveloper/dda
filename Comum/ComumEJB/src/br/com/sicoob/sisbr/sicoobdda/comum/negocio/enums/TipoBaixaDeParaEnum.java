/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         CarteiraDeParaEnum.java
 * Data Cria��o:    Aug 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoBaixaDeParaEnum
 * 
 * @author George.Santos
 */
public enum TipoBaixaDeParaEnum {
    BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA("0", "Baixa Efetiva Integral Interbancaria", "4"),
    BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA("1", "Baixa Efetiva Integral Intrabanc�ria", "3"),
    BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE("2", "Baixa Efetiva Integral por Solicita��o do Cedente", "2"),
    BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO("3", "Baixa Efetiva Integral por envio para protesto", "6"),
    BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO("4", "Baixa Efetiva Integral por decurso de prazo", "5"),
    BAIXA_EFETIVA_PARCIAL_INTRABANCARIA("5", "Baixa Efetiva Parcial Intrabanc�ria", "1"),
    BAIXA_EFETIVA_PARCIAL_INTERBANCARIA("6", "Baixa Efetiva Parcial Interbanc�ria", "1"),
    BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA("7", "Baixa Efetiva por solita��o da Institui��o Destinat�ria", "7");

    private TipoBaixaDeParaEnum(String codTipoBaixaEfetiva, String descTipoBaixaEfetiva, String codSituacaoBoleto) {
        this.codTipoBaixaEfetiva = codTipoBaixaEfetiva;
        this.descTipoBaixaEfetiva = descTipoBaixaEfetiva;
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    private final String codTipoBaixaEfetiva;
    private final String descTipoBaixaEfetiva;
    private final String codSituacaoBoleto;

    /**
     * M�todo respons�vel por verificar qual o tipo da situacao do boleto de acordo com o Tipo da baixa Efetiva
     * 
     * @param idTipoBaixaEfetiva
     * @return String
     * 
     */
    public static Integer getSituacaoBoleto(String idTipoBaixaEfetiva) {
        TipoBaixaDeParaEnum[] lista = values();
        for (TipoBaixaDeParaEnum tipoBaixa : lista) {
            if (tipoBaixa.getCodTipoBaixaEfetiva().equals(idTipoBaixaEfetiva)) {
                return Integer.valueOf(tipoBaixa.getCodSituacaoBoleto());
            }
        }
        return null;
    }

    /**
     * @return o atributo codTipoBaixaEfetiva
     */
    public String getCodTipoBaixaEfetiva() {
        return codTipoBaixaEfetiva;
    }

    /**
     * @return o atributo descTipoBaixaEfetiva
     */
    public String getDescTipoBaixaEfetiva() {
        return descTipoBaixaEfetiva;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public String getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

}
