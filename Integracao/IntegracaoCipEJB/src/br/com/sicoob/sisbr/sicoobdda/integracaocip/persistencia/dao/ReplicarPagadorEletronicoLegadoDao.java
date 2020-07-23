/**
 * Projeto:         SDDA
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ReplicarBeneficiarioLegadoDao.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico;

/**
 * ReplicarPagadorEletronicoLegadoDao é responsável por
 * 
 * @author george.santos
 */
public interface ReplicarPagadorEletronicoLegadoDao extends IntegracaoCipCrudDaoIF<DDAPagadorEletronico> {

    /**
     * Método responsável por replicar os Clientes Pagador Eletrônico referentes ao arquivo ADDA003
     * 
     * @param prParametros
     * @exception ComumException
     */
    void replicarPagadorEletronico(List<Object[]> parametros) throws ComumException;

    /**
     * Método responsável por obter o Pagador Eletrônico no BDSicoobIntegração
     * 
     * @param cPFCNPJ
     * @return DDAPagadorEletronico
     * @exception ComumException
     */
    DDAPagadorEletronico obterDDAPagadorEletronico(String cPFCNPJ) throws ComumException;

    /**
     * Método responsável por alterar o Pagador Eletrônico no BDSicoobIntegração
     * 
     * @param ddaPagadorEletronico
     * @return
     * @exception ComumException
     */
    void alterarDDAPagadorEletronico(DDAPagadorEletronico ddaPagadorEletronico) throws ComumException;

    /**
     * Método responsável por incluir o Pagador Eletronico no BDSicoobIntegração
     * 
     * @param numCpfCnpj
     * @param bolSacadoEletronico
     * @param numCooperativa void
     * 
     */
    void incluirDDAPagadorEletronico(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws ComumException;
}
