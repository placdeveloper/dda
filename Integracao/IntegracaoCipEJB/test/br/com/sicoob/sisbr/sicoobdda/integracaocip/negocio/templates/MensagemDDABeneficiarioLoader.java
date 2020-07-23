/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDABeneficiarioLoader.java
 * Data Cria��o:    Nov 4, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDABeneficiarioLoader � respons�vel por 
 * 
 * @author Felipe.Rosa
 */
public final class MensagemDDABeneficiarioLoader extends MensagemDDALoader {

    /**
     * 
     */
    private MensagemDDABeneficiarioLoader() {
        super();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    public static MensagemDDABeneficiario geraMensagemDDABeneficiario() {
        return geraMensagemDDABeneficiario(TipoPessoaEnum.PJ.getCodDominioCip(), Constantes.CNPJ_AUX, SituacaoBeneficiarioEnum.APTO.getCodDominio(),
                SituacaoRelacionamentoParticipanteEnum.ATIVO.getCodDominio());
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    public static MensagemDDABeneficiario geraMensagemDDABeneficiarioCodTipoPessoaNull() {
        return geraMensagemDDABeneficiario(null, Constantes.CNPJ_AUX, SituacaoBeneficiarioEnum.APTO.getCodDominio(), SituacaoRelacionamentoParticipanteEnum.ATIVO.getCodDominio());
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    public static MensagemDDABeneficiario geraMensagemDDABeneficiarioNumCnpjCpfNull() {
        return geraMensagemDDABeneficiario(TipoPessoaEnum.PJ.getCodDominioCip(), null, SituacaoBeneficiarioEnum.APTO.getCodDominio(),
                SituacaoRelacionamentoParticipanteEnum.ATIVO.getCodDominio());
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    public static MensagemDDABeneficiario geraMensagemDDABeneficiarioRelacionamentoNull() {
        return geraMensagemDDABeneficiario(null, Constantes.CNPJ_AUX, null, null);
    }

    /**
     * M�todo respons�vel por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    private static MensagemDDABeneficiario geraMensagemDDABeneficiario(String codTipoPessoa, String numCnpjCpf, String codSituacaoBeneficiario, String codRelacionamentoParticipante) {
        MensagemDDABeneficiario mensagemBeneficiario = new MensagemDDABeneficiario();
        mensagemBeneficiario.setId(Constantes.LONG_UM);
        mensagemBeneficiario.setCodTipoPessoaBeneficiario(codTipoPessoa);
        mensagemBeneficiario.setNumCpfCnpjBeneficiario(numCnpjCpf);
        mensagemBeneficiario.setCodSituacaoBeneficiario(codSituacaoBeneficiario);
        mensagemBeneficiario.setCodSituacaoRelacionamentoBeneficiario(codRelacionamentoParticipante);
        mensagemBeneficiario.setMensagemDDA(geraMensagemDDA());
        return mensagemBeneficiario;
    }
}
