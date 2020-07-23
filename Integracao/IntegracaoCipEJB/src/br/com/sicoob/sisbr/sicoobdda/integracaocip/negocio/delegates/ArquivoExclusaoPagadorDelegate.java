package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoExclusaoPagadorServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemInclusaoPagadorDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoExclusaoPagadorDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoExclusaoPagadorServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoExclusaoPagadorServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoExclusaoPagadorServico();
    }

    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }
}
