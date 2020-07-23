package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalContingenciaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoInclusaoBoletoDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoBaixaOperacionalContingenciaDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoBaixaOperacionalContingenciaServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoBaixaOperacionalContingenciaServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoBaixaOperacionalContingenciaServico();
    }

    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        localizarServico().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
    }
}
