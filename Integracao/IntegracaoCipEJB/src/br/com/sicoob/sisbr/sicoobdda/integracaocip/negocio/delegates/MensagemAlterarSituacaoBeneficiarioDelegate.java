/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MensagemAlterarSituacaoBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarSituacaoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarSituacaoBeneficiarioDelegate
 * 
 * @author rafael.silva
 */
public class MensagemAlterarSituacaoBeneficiarioDelegate extends IntegracaoCipMensagemDelegate<IntegracaoCipServico> implements MensagemAlterarSituacaoBeneficiarioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected MensagemAlterarSituacaoBeneficiarioServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemAlterarSituacaoBeneficiarioServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate#processarMensagem(java.lang.Long)
     */
    @Override
    public String processarMensagem(Long idMensagem) throws ComumException {
        return localizarServico().processarMensagem(idMensagem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda
     * .integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    @Override
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(conteudoMsg);
    }

}
