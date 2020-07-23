/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.enums
 * Arquivo:         SituacaoConvenioBeneficiarioParticipanteEnum.java
 * Data Criação:    May 7, 2015
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
    // Ativo para convênios ativos no Participante.

    // Excluído para convênios em que houve desistência do convênio ou por alguma outra regra da Participante, cujo pagamento do boleto será possível,
    // mas não será possível uma nova inclusão do boleto já que ao menos que um convênio de cobrança deve estar ativo.

    // Inativo para convênios inativos por alguma regra do Participante, cujo pagamento e nova emissão do boleto será possível.
    // Devido nossa regra de negócio, todas as vezes que a situação for SUSPENSO/INATIVO será encaminhado ATIVO para CIP.

    // Cancelado - Para convênios inválidos, registrados de maneira incorreta e não farão parte da validação para o cadastro e consulta de boletos.

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
     * Método responsável por
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
