/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums
 * Arquivo:         TipoTratamentoMensagemEnum.java
 * Data Criação:    Sep 15, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;


/**
 * TipoTratamentoErroCipEnum é responsável por
 * 
 * @author felipe.rosa
 */
public enum TipoTratamentoErroCipEnum {

    ATUALIZAR_DATA_MOVIMENTO((short) 1, "ATUALIZAR A DATA DE MOVIMENTO"),
    REENVIAR_SSPB((short) 2, "REENVIAR PARA O SSPB"),
    REENVIAR_CIP((short) 3, "REENVIAR PARA A CIP"),
    FINALIZAR_SEM_ACAO((short) 4, "FINALIZAR SEM AÇÃO"),
    FINALIZAR_REENVIAR_NOVA_MSG((short) 5, "FINALIZAR E REENVIAR NOVA MENSAGEM"),
    FINALIZAR_REENVIAR_ATUALIZACAO_DADOS((short) 6, "FINALIZAR E REENVIAR APÓS A ATUALIZAÇÃO DE DADOS"),
    REPROCESSAR_MENSAGEM_CONTINGENCIA((short) 7, "REPROCESSAR MENSAGEM EM CONTINGÊNCIA"),
    PREPARAR_REENVIO_ARQUIVO_CIP((short) 8, "PREPARAR REENVIO DE ARQUIVO PARA A CIP"),
    ATUALIZAR_DATA_PROXIMO_MOVIMENTO((short) 9, "ATUALIZAR PARA A DATA DO PRÓXIMO MOVIMENTO"),
    PRIORIZAR_MENSAGEM((short) 10, "PRIORIZAR MENSAGEM"),
    DESPRIORIZAR_MENSAGEM((short) 11, "DESPRIORIZAR MENSAGEM");

    /**
     * @param codTipoTratamento
     * @param descTipoTratamento
     */
    private TipoTratamentoErroCipEnum(Short codTipoTratamento, String descTipoTratamento) {
        this.codTipoTratamento = codTipoTratamento;
        this.descTipoTratamento = descTipoTratamento;
    }

    private Short codTipoTratamento;
    private String descTipoTratamento;

    /**
     * @return the codTipoTratamento
     */
    public Short getCodTipoTratamento() {
        return codTipoTratamento;
    }

    /**
     * @param codTipoTratamento the codTipoTratamento to set
     */
    public void setCodTipoTratamento(Short codTipoTratamento) {
        this.codTipoTratamento = codTipoTratamento;
    }

    /**
     * @return the descTipoTratamento
     */
    public String getDescTipoTratamento() {
        return descTipoTratamento;
    }

    /**
     * @param descTipoTratamento the descTipoTratamento to set
     */
    public void setDescTipoTratamento(String descTipoTratamento) {
        this.descTipoTratamento = descTipoTratamento;
    }

    /**
     * 
     * Método responsável por
     * 
     * @param codTipoTratamento
     * @return TipoTratamentoMensagemEnum
     * 
     */
    public static TipoTratamentoErroCipEnum getBy(Short codTipoTratamento) {
        TipoTratamentoErroCipEnum[] lista = values();
        for (TipoTratamentoErroCipEnum tipoTratamento : lista) {
            if (tipoTratamento.getCodTipoTratamento().equals(codTipoTratamento)) {
                return tipoTratamento;
            }
        }
        return null;
    }
}
