/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ProcessarContingenciaMensagemDelegate.java
 * Data Criação:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarContingenciaMensagemDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ProcessarContingenciaMensagemDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarContingenciaMensagemServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ProcessarContingenciaMensagemServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarContingenciaMensagemServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico#processarMensagemContingencia(java.util.List)
     */
    public void processarMensagemContingencia(List<Long> listaIdMensagem) throws BancoobException {
        localizarServico().processarMensagemContingencia(listaIdMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico#processarMensagemContingenciaBatch(java.util.List, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.String)
     */
    public String processarMensagemContingenciaBatch(List<Long> listaIdMensagem, DateTimeDB dataMovimento, String codTipoMensagem) throws BancoobException {
        return localizarServico().processarMensagemContingenciaBatch(listaIdMensagem, dataMovimento, codTipoMensagem);
    }
}
