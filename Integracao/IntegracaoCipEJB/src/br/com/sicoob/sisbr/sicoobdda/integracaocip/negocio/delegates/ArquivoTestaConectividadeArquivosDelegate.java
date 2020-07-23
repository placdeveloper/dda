/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ArquivoTestaConectividadeArquivosDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoTestaConectividadeArquivosServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ArquivoTestaConectividadeArquivosDelegate
 * 
 * @author rafael.silva
 */
public class ArquivoTestaConectividadeArquivosDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ArquivoTestaConectividadeArquivosServico {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected ArquivoTestaConectividadeArquivosServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarArquivoTestaConectividadeArquivosServico();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.TestaConectividadeArquivosServico#processarTesteConectividade()
     */
    public void processarTesteConectividade() throws ComumException {
        localizarServico().processarTesteConectividade();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.TestaConectividadeArquivosServico#processarRetornoTesteConectividade(java.lang.String )
     */
    public void processarRetornoCIPTesteConectividadePorArquivo(String nomeArquivoRecebido) throws ComumException {
        localizarServico().processarRetornoCIPTesteConectividadePorArquivo(nomeArquivoRecebido);

    }

}
