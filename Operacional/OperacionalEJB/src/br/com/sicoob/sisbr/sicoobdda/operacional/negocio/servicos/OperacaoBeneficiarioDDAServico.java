/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         OperacaoBeneficiarioDDAServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * OperacaoBeneficiarioDDAServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface OperacaoBeneficiarioDDAServico extends OperacionalServico {

    /**
     * M�todo respons�vel por retornar uma ComumNegocioException caso a situa��o do benefici�rio esteja INAPTO na CIP.
     * 
     * @param numCpfCnpj
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws ComumException, ComumNegocioException;

    /**
     * @param idBeneficiario
     * @param idInstituicao
     * @return
     * @throws ComumException boolean
     * 
     */
    boolean permiteRealizarOperacoesCobranca(String numCPFCNPJ) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param numCpfCnpj
     * @return Boolean
     * 
     */
    boolean beneficiarioEstaNaCip(String numCpfCnpj) throws ComumException;
}
