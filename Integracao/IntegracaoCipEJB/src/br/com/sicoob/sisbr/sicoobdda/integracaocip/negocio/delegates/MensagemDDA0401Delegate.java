/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MensagemDDA0401Delegate.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDA0401Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemDDA0401Delegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDA0401Delegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemDDA0401Servico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemDDA0401Servico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemDDA0401Servico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDA0401Servico#incluir(java.lang.String,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
     */
    public void incluir(String codTipoHorario, DateTimeDB dataReferencia, Integer numPrioridadeEnvio) throws BancoobException {
        localizarServico().incluir(codTipoHorario, dataReferencia, numPrioridadeEnvio);
    }

}
