package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoExclusaoTerceiroAutorizadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemInclusaoPagadorDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoExclusaoTerceiroAutorizadoDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoExclusaoTerceiroAutorizadoServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoExclusaoTerceiroAutorizadoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoExclusaoTerceiroAutorizadoServico();
    }

    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }
}
