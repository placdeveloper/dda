/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ReplicarBeneficiarioLegadoServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * ReplicarPagadorEletronicoLegadoServico
 * 
 * @author george.santos
 */
public interface ReplicarPagadorEletronicoLegadoServico extends IntegracaoCipServico {
    /**
     * Método responsável por fazer a replicacao inclusao/alteracao do pagadorDDA
     * 
     * @param numCpfCnpj
     * @param bolSacadoEletronico
     * @param numCooperativa
     * @throws IntegracaoCipException void
     * 
     */
    void replicarPagadorCIPLegado(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws IntegracaoCipException;

    /**
     * Método responsável por fazer a exclusao do PagadorDDA no Legado
     * 
     * @param numCpfCnpj
     * @throws IntegracaoCipException void
     * 
     */
    void excluirPagadorCipLegador(String numCpfCnpj, Boolean bolSacadoEletronico) throws IntegracaoCipException;

}
