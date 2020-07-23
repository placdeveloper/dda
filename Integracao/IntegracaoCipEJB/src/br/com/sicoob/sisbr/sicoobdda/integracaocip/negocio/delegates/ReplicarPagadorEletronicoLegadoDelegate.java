/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ReplicarBeneficiarioLegadoDelegate.java
 * Data Criação:    Aug 28, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ReplicarPagadorEletronicoLegadoDelegate
 * 
 * @author george.santos
 */
public class ReplicarPagadorEletronicoLegadoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ReplicarPagadorEletronicoLegadoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ReplicarPagadorEletronicoLegadoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarReplicarPagadorEletronicoLegadoServico();
    }

    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico#replicarPagadorCIPLegado(java.lang.String,
     *      java.lang.Boolean, java.lang.Integer)
     */
    public void replicarPagadorCIPLegado(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws IntegracaoCipException {
        localizarServico().replicarPagadorCIPLegado(numCpfCnpj, bolSacadoEletronico, numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarPagadorEletronicoLegadoServico#excluirPagadorCipLegador(java.lang.String)
     */
    public void excluirPagadorCipLegador(String numCpfCnpj, Boolean bolSacadoEletronico) throws IntegracaoCipException {
        localizarServico().excluirPagadorCipLegador(numCpfCnpj, bolSacadoEletronico);
    }

}
