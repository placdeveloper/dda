/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ProcessarEnvioMensagensDelegate.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensDetalheServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarEnvioMensagensDelegate
 * 
 * @author Rafael.Silva
 */
public class ProcessarEnvioMensagensDetalheDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarEnvioMensagensDetalheServico {

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
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ProcessarEnvioMensagensDetalheServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarEnvioMensagensDetalheServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensDetalheServico#processarEnvioMensagens(String, Long)
     */
    public void processarEnvioMensagens(String codTipoMensagemDDA, Long idMensagemDDA) throws ComumException {
        localizarServico().processarEnvioMensagens(codTipoMensagemDDA, idMensagemDDA);

    }

}
