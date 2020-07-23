/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.enums
 * Arquivo:         CategoriaMensagemDDAEnum.java
 * Data Criação:    Sep 29, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades.enums;

/**
 * CategoriaMensagemDDAEnum é responsável por 
 * 
 * @author Felipe.Rosa
 */
public enum CategoriaMensagemDDAEnum {

    ENVIO_FILA("E", "Envio por fila"),
    RECEBIMENTO("R", "Recebimento"),
    ERRO("X", "Erro"),
    GENERICA("G", "Genérica"),
    ENVIO_ARQUIVO("A", "Envio por Arquivo"),
    CONTIGENCIA("C", "Contingência");

    /**
     * @param codCategoriaMensagemDDA
     * @param descCategoriaMensagemDDA
     */
    private CategoriaMensagemDDAEnum(String codCategoriaMensagemDDA, String descCategoriaMensagemDDA) {
        this.codCategoriaMensagemDDA = codCategoriaMensagemDDA;
        this.descCategoriaMensagemDDA = descCategoriaMensagemDDA;
    }

    private String codCategoriaMensagemDDA;
    private String descCategoriaMensagemDDA;

    /**
     * @return the codCategoriaMensagemDDA
     */
    public String getCodCategoriaMensagemDDA() {
        return codCategoriaMensagemDDA;
    }

    /**
     * @param codCategoriaMensagemDDA the codCategoriaMensagemDDA to set
     */
    public void setCodCategoriaMensagemDDA(String codCategoriaMensagemDDA) {
        this.codCategoriaMensagemDDA = codCategoriaMensagemDDA;
    }

    /**
     * @return the descCategoriaMensagemDDA
     */
    public String getDescCategoriaMensagemDDA() {
        return descCategoriaMensagemDDA;
    }

    /**
     * @param descCategoriaMensagemDDA the descCategoriaMensagemDDA to set
     */
    public void setDescCategoriaMensagemDDA(String descCategoriaMensagemDDA) {
        this.descCategoriaMensagemDDA = descCategoriaMensagemDDA;
    }

}
