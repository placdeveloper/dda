/**
 * Projeto:         SDDA
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ReplicarBeneficiarioLegadoDao.java
 * Data Cria��o:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico;

/**
 * ReplicarPagadorEletronicoLegadoDao � respons�vel por
 * 
 * @author george.santos
 */
public interface ReplicarPagadorEletronicoLegadoDao extends IntegracaoCipCrudDaoIF<DDAPagadorEletronico> {

    /**
     * M�todo respons�vel por replicar os Clientes Pagador Eletr�nico referentes ao arquivo ADDA003
     * 
     * @param prParametros
     * @exception ComumException
     */
    void replicarPagadorEletronico(List<Object[]> parametros) throws ComumException;

    /**
     * M�todo respons�vel por obter o Pagador Eletr�nico no BDSicoobIntegra��o
     * 
     * @param cPFCNPJ
     * @return DDAPagadorEletronico
     * @exception ComumException
     */
    DDAPagadorEletronico obterDDAPagadorEletronico(String cPFCNPJ) throws ComumException;

    /**
     * M�todo respons�vel por alterar o Pagador Eletr�nico no BDSicoobIntegra��o
     * 
     * @param ddaPagadorEletronico
     * @return
     * @exception ComumException
     */
    void alterarDDAPagadorEletronico(DDAPagadorEletronico ddaPagadorEletronico) throws ComumException;

    /**
     * M�todo respons�vel por incluir o Pagador Eletronico no BDSicoobIntegra��o
     * 
     * @param numCpfCnpj
     * @param bolSacadoEletronico
     * @param numCooperativa void
     * 
     */
    void incluirDDAPagadorEletronico(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws ComumException;
}
