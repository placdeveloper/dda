package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultarHorariosDDAServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultarHorariosDDAServico
 * 
 * @author George.Santos
 */
public class MensagemConsultarHorariosDDADelegate extends IntegracaoCipMensagemDelegate<IntegracaoCipServico> implements MensagemConsultarHorariosDDAServico {

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
    protected MensagemConsultarHorariosDDAServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemConsultarHorariosDDAServico();
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

}
