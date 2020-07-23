package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoManutencaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarPagadorDelegate
 * 
 * @author George.Santos
 */
public class ArquivoManutencaoPagadorDelegate extends IntegracaoCipMensagemDelegate<IntegracaoCipServico> implements ArquivoManutencaoPagadorServico {

    public void verificarDisponibilidade() {

    }

    @Override
    public String processarMensagem(Long idMensagem) throws ComumException {
        return localizarServico().processarMensagem(idMensagem);

    }

    @Override
    protected ArquivoManutencaoPagadorServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoManutencaoPagadorServico();
    }

    @Override
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(conteudoMsg);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoManutencaoPagadorServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal);
    }

}
