package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoBoletoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoInclusaoBoletoDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoAlteracaoBoletoDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoAlteracaoBoletoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoAlteracaoBoletoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoAlteracaoBoletoServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        localizarServico().processarRetornoMensagemDDA(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);

    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebArq
     * @param idLogDetRecebimentoInicial
     * @param idLogDetRecebimentoFinal void
     */
    public void processarRetornoAlteracaoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        localizarServico().processarRetornoAlteracaoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);

    }
}
