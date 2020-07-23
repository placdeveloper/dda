package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoInclusaoBoletoDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoBaixaOperacionalDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoBaixaOperacionalServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoBaixaOperacionalServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoBaixaOperacionalServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalServico#processarArquivoRetornoBaixaOperacionalDDA(long, long, long)
     */
    public void processarArquivoRetornoBaixaOperacionalDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        localizarServico().processarArquivoRetornoBaixaOperacionalDDA(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
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
