/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ReplicarBeneficiarioLegadoDelegate.java
 * Data Criação:    Aug 28, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ReplicarBeneficiarioLegadoDelegate
 * 
 * @author felipe.rosa
 */
public class ReplicarBeneficiarioLegadoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ReplicarBeneficiarioLegadoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ReplicarBeneficiarioLegadoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarReplicarBeneficiarioLegadoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarSituacaoBeneficiarioCIPLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarSituacaoBeneficiarioCIPLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        localizarServico().replicarSituacaoBeneficiarioCIPLegado(beneficiario);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarSituacaoBeneficiarioFraudeLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarSituacaoBeneficiarioFraudeLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException {
        localizarServico().replicarSituacaoBeneficiarioFraudeLegado(beneficiario);
    }

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ReplicarBeneficiarioLegadoServico#replicarExclusaoBeneficiarioLegado(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA)
     */
    public void replicarExclusaoBeneficiarioLegado(Long idBeneficiarioDDA) throws IntegracaoCipException {
        localizarServico().replicarExclusaoBeneficiarioLegado(idBeneficiarioDDA);
    }

}
