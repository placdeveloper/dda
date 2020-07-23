package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaOperacionalBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemBaixaOperacionalBoletoDelegate
 * 
 * @author George.Santos
 */
public class MensagemBaixaOperacionalBoletoDelegate extends IntegracaoCipMensagemDelegate<IntegracaoCipServico> implements MensagemBaixaOperacionalBoletoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate#processarMensagem(java.lang.Long)
     */
    @Override
    public String processarMensagem(Long idMensagem) throws ComumException {
        return localizarServico().processarMensagem(idMensagem);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemBaixaOperacionalBoletoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemBaixaOperacionalBoletoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    @Override
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(conteudoMsg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaOperacionalBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        localizarServico().processarRetornoMensagemDDA(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

}
