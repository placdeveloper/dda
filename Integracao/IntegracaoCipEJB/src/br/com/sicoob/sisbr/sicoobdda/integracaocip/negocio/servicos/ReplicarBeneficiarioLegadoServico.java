/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ReplicarBeneficiarioLegadoServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * ReplicarBeneficiarioLegadoServico
 * 
 * @author felipe.rosa
 */
public interface ReplicarBeneficiarioLegadoServico extends IntegracaoCipServico {

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @throws OperacionalException void
     * 
     */
    void replicarSituacaoBeneficiarioCIPLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException;

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @throws OperacionalException void
     * 
     */
    void replicarSituacaoBeneficiarioFraudeLegado(BeneficiarioDDA beneficiario) throws IntegracaoCipException;

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @throws OperacionalException void
     * 
     */
    void replicarExclusaoBeneficiarioLegado(Long idBeneficiarioDDA) throws IntegracaoCipException;

}
