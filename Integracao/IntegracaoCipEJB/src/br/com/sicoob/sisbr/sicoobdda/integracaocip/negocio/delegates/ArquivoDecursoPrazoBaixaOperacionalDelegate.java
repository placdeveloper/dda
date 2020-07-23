package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoDecursoPrazoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoDecursoPrazoBaixaOperacionalDelegate
 * 
 * @author George.Santos
 */
public class ArquivoDecursoPrazoBaixaOperacionalDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ArquivoDecursoPrazoBaixaOperacionalServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoDecursoPrazoBaixaOperacionalServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoDecursoPrazoBaixaOperacionalServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoDecursoPrazoBaixaOperacionalServico#processarRetornoMensagemDDA(long, long,
     *      long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

}
