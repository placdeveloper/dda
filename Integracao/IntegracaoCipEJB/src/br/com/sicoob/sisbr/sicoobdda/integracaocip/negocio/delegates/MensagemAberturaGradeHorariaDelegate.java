package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAberturaGradeHorariaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

public class MensagemAberturaGradeHorariaDelegate extends IntegracaoCipMensagemDelegate<IntegracaoCipServico> implements MensagemAberturaGradeHorariaServico {

    @Override
    public String processarMensagem(Long idMensagem) throws ComumException {
        return null;
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

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemAberturaGradeHorariaServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemAberturaGradeHorariaServico();
    }

    public void verificarDisponibilidade() {
    }

}
