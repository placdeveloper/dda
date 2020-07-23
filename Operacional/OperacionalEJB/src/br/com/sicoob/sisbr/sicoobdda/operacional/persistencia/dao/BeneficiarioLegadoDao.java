/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         BeneficiarioLegadoDao.java
 * Data Criação:    Aug 31, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * BeneficiarioLegadoDao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface BeneficiarioLegadoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException CadastroBeneficiarioDDADto
     * 
     */
    CadastroBeneficiarioDDADto obterCadastroBeneficiarioDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException;

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException AlterarCadastroBeneficiarioDDADto
     * 
     */
    AlterarCadastroBeneficiarioDDADto obterAlterarCadastroBeneficiarioDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException;

    /**
     * Método responsável por
     * 
     * @param numCliente
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException AlterarCadastroBeneficiarioDDADto
     * 
     */
    AlterarCadastroBeneficiarioDDADto obterAlterarCadastroBeneficiarioDDADto(Long numCliente, Integer numCooperativa) throws IntegracaoCipException;

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException ConvenioAlteracaoDDADto
     * 
     */
    ConvenioAlteracaoDDADto obterConvenioAlteracaoDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException;

}
