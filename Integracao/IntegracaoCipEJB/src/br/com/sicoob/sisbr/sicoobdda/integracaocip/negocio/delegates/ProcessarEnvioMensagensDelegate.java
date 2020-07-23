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
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarEnvioMensagensDelegate
 * 
 * @author Rafael.Silva
 */
public class ProcessarEnvioMensagensDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarEnvioMensagensServico {

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
    protected ProcessarEnvioMensagensServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarEnvioMensagensServico();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#processarEnvioMensagensNaoPrioritarias(java.lang.Long,
     * java.lang.Long, int)
     */
    public void processarEnvioMensagensNaoPrioritarias(int numAgrupamentoSteps) throws ComumException {
        localizarServico().processarEnvioMensagensNaoPrioritarias(numAgrupamentoSteps);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#processarEnvioMensagensPrioritarias(int)
     */
    public void processarEnvioMensagensPrioritarias(int numAgrupamentoSteps) throws ComumException {
        localizarServico().processarEnvioMensagensPrioritarias(numAgrupamentoSteps);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#excluirMensagensTabelaTmp()
     */
    public void excluirMensagensTabelaTmp() throws ComumException {
        localizarServico().excluirMensagensTabelaTmp();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioMensagensServico#registrarMensagensTabelaTmp(java.lang.Integer,
     *      java.lang.Integer, java.lang.Integer)
     */
    public void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg)
            throws ComumException {
        localizarServico().registrarMensagensTabelaTmp(qtdParametroMensagensEnviadasPorRajada, qtdMaxRegistrosPorStep, qtdParametroNumPrioridadeMsg);

    }

}
