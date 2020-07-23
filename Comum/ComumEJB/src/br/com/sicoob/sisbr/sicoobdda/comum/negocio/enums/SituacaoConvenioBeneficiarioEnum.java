/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         SituacaoConvenioBeneficiarioParticipanteEnum.java
 * Data Cria��o:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * SituacaoConvenioBeneficiarioParticipanteEnum
 * 
 * @author Rafael.Silva
 */
public enum SituacaoConvenioBeneficiarioEnum {

    INATIVO((short) 0, "A"),
    ATIVO((short) 1, "A"),
    EXCLUIDO((short) 2, "E"),
    CANCELADO((short) 3, "C");

    // RegraCIP:
    // Ativo para conv�nios ativos no Participante.

    // Exclu�do para conv�nios em que houve desist�ncia do conv�nio ou por alguma outra regra da Participante, cujo pagamento do boleto ser� poss�vel,
    // mas n�o ser� poss�vel uma nova inclus�o do boleto j� que ao menos que um conv�nio de cobran�a deve estar ativo.

    // Inativo para conv�nios inativos por alguma regra do Participante, cujo pagamento e nova emiss�o do boleto ser� poss�vel.
    // Devido nossa regra de neg�cio, todas as vezes que a situa��o for SUSPENSO/INATIVO ser� encaminhado ATIVO para CIP.

    // Cancelado - Para conv�nios inv�lidos, registrados de maneira incorreta e n�o far�o parte da valida��o para o cadastro e consulta de boletos.

    private SituacaoConvenioBeneficiarioEnum(Short codSituacao, String codDominio) {
        this.codSituacao = codSituacao;
        this.codDominio = codDominio;
    }

    private Short codSituacao;
    private String codDominio;

    /**
     * @return the codSituacao
     */
    public Short getCodSituacao() {
        return codSituacao;
    }

    /**
     * @return the codDominio
     */
    public String getCodDominio() {
        return codDominio;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param codSituacaoCedente
     * @return SituacaoConvenioBeneficiarioEnum
     * 
     */
    public static SituacaoConvenioBeneficiarioEnum getBy(Short codSituacaoCedente) {
        SituacaoConvenioBeneficiarioEnum[] lista = values();
        for (SituacaoConvenioBeneficiarioEnum situacao : lista) {
            if (situacao.getCodSituacao().equals(codSituacaoCedente)) {
                return situacao;
            }
        }
        return null;
    }
}
