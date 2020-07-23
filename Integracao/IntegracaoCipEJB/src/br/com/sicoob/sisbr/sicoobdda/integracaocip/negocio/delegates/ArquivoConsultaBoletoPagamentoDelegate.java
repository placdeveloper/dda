package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoConsultaBoletoPagamentoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoBaixaEfetivaDecursoPrazoDelegate é responsável por
 * 
 * @author George.Santos
 */
public class ArquivoConsultaBoletoPagamentoDelegate extends IntegracaoCipArquivoDelegate<IntegracaoCipServico> implements ArquivoConsultaBoletoPagamentoServico {

    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoConsultaBoletoPagamentoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoConsultaBoletoPagamentoServico();
    }

    @Override
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return localizarServico().obterSISARQ(idLogEnvioArquivoDDA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoConsultaBoletoPagamentoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarArquivoRetornoConsultaBoletoDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {

        localizarServico().processarArquivoRetornoConsultaBoletoDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
    }

}
