/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         OperacaoBeneficiarioDDADao.java
 * Data Cria��o:    Sep 1, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;

/**
 * OperacaoBeneficiarioDDADao
 * 
 * @author Daniel.Basso
 */
public interface OperacaoBeneficiarioDDADao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por buscar a situa��o do benefici�rio na base do DDA.
     * 
     * @param numCPFCNPJ
     * @return
     */
    SituacaoBeneficiarioDDA obterSituacaoBeneficiario(String numCPFCNPJ) throws ComumException;

    /**
     * M�todo respons�vel por verificar se o cpfCnpj do beneficiario tem mensagens pendentes.
     * 
     * @param numCpfCnpjBeneficiario
     * @return
     * @throws ComumException List<MensagemPendenteBeneficiarioDto>
     * 
     */
    List<MensagemPendenteBeneficiarioDto> listarMensagemPendenteBeneficiario(String numCpfCnpjBeneficiario) throws ComumException;

    /**
     * M�todo respons�vel por verificar se o boleto esta na cip
     * 
     * @param numCPFCNPJ
     * @return
     */
    Boolean beneficiarioEstaNaCip(String numCPFCNPJ) throws ComumException;
}
