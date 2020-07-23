/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         SituacaoRelacionamentoParticipante.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * SituacaoRelacionamentoParticipante
 * 
 * @author Rafael.Silva
 */
public enum SituacaoRelacionamentoParticipanteEnum {

    ATIVO("A", "Ativo"),
    EXCLUIDO("E", "Excluido");

    private SituacaoRelacionamentoParticipanteEnum(String codRelacionamento, String descRelacionamento) {
        this.codDominio = codRelacionamento;
        this.descRelacionamento = descRelacionamento;
    }

    private String codDominio;
    private String descRelacionamento;

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * @return the descRelacionamento
     */
    public String getDescRelacionamento() {
        return descRelacionamento;
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @param codDominio
     * @return SituacaoRelacionamentoParticipanteEnum
     * 
     */
    public static SituacaoRelacionamentoParticipanteEnum getBy(String codDominio) {
        for (SituacaoRelacionamentoParticipanteEnum tipoEnum : values()) {
            if (tipoEnum.getCodDominio().equals(codDominio)) {
                return tipoEnum;
            }
        }
        return null;
    }

}
