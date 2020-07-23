package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaDecursoPrazoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoBaixaEfetivaDecursoPrazoDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoBaixaEfetivaDecursoPrazoDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoBaixaEfetivaDecursoPrazoServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoBaixaEfetivaDecursoPrazoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoBaixaEfetivaDecursoPrazoServico();
    }

    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }
}
