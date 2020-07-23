/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         CadastrarBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.HistoricoMensagemDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * HistoricoMensagemDDADelegate é responsável por
 * 
 * @author George.Santos
 */
public class HistoricoMensagemDDADelegate extends OperacionalDelegate<OperacionalServico> implements HistoricoMensagemDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected HistoricoMensagemDDAServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarHistoricoMensagemDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.HistoricoMensagemDDAServico#arquivarMensagensDDA()
     */
    public void arquivarMensagensDDA() throws ComumException {
        localizarServico().arquivarMensagensDDA();

    }

}
