/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto
 * Arquivo:         AplicativoEnum.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.enums;

/**
 * AplicativoEnum
 * 
 * @author samuell.ramos
 */
public enum AplicativoEnum {
    COBRANCA_ADMINISTRATIVA(1),
    COBRANCA_BANCARIA(2),
    SSPB(3),
    PLATAFORMA_CREDITO(4),
    GESTAO_CARTORARIA(5);

    private int codigoAplicativo;

    private AplicativoEnum(int codigoAplicativo) {
        this.setCodigoAplicativo(codigoAplicativo);
    }

    public int getCodigoAplicativo() {
        return codigoAplicativo;
    }

    public void setCodigoAplicativo(int codigoAplicativo) {
        this.codigoAplicativo = codigoAplicativo;
    }
}
