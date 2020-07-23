package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoDecursoPrazoBaixaOperacionalDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoBaixaEfetivaBoletoDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoBaixaEfetivaBoletoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoBaixaEfetivaBoletoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoBaixaEfetivaBoletoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipArquivoDelegate#obterSISARQ(java.lang.Long)
     */
    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaBoletoServico#processarArquivoRetornoBaixaEfetivaDDA(long, long, long)
     */
    public void processarArquivoRetornoBaixaEfetivaDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        localizarServico().processarArquivoRetornoBaixaEfetivaDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }
}
