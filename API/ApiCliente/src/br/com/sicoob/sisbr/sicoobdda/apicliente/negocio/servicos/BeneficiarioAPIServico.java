/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         PagadorAPIServico.java
 * Data Cria��o:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos;

import java.util.Date;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.RetornoAlterarSituacaoBeneficiarioAPIEnum;
import br.com.sicoob.tipos.DateTime;

/**
 * PagadorAPIServico � respons�vel por
 * 
 * @author Daniel.Basso
 */
public interface BeneficiarioAPIServico extends SicoobDDAServico {

    /**
     * M�todo respons�vel por cadastrar o benefici�rio na CIP
     * 
     * @param numCliente
     * @param numCpfCnpj
     * @param numCooperativa
     * @param dataAtualMovimento
     * @throws ComumException void
     * @throws ComumNegocioException void
     */
    void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException,
            SicoobDDANegocioException;

    /**
     * M�todo respons�vel por cadastrar o benefici�rio na CIP
     * 
     * @param numCliente
     * @param numCpfCnpj
     * @param numCooperativa
     * @param dataAtualMovimento
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException,
            SicoobDDANegocioException;

    /**
     * M�todo respons�vel por alterar o cadastro do benefici�rio na CIP
     * 
     * @param numCliente
     * @param idInstituicao
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento, Short idCanal)
            throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * �todo respons�vel por alterar o cadastro do benefici�rio na CIP
     * 
     * @param numCliente
     * @param idInstituicao
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @param dataAtualMovimento
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento)
            throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por excluir convenio do benefici�rio na CIP
     * 
     * @param numCliente
     * @param numCooperativa
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por excluir convenio do benefici�rio na CIP
     * 
     * @param numCliente
     * @param numCooperativa
     * @param dataAtualMovimento
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException void
     * 
     */
    void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por processar a altera��o de Situa��o do Benefici�rio.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws CobrancaBancariaException RetornoAlterarSituacaoBeneficiarioAPIEnum
     * 
     */
    RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto, Short idCanal)
            throws SicoobDDAException;

    /**
     * M�todo respons�vel por processar a altera��o de Situa��o do Benefici�rio.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws SicoobDDAException RetornoAlterarSituacaoBeneficiarioAPIEnum
     * 
     */
    RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto) throws SicoobDDAException;

    /**
     * M�todo respons�vel por returnar v�lido se a situa��o do benefici�rio for diferente de Inapto
     * 
     * @param numCpfCnpj
     * @return
     * @throws ComumException
     */
    void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * M�todo respons�vel por verificar se o beneficiario esta na CIP
     * 
     * @param numCpfCnpj
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException Boolean
     * 
     */
    Boolean beneficiarioEstaNaCip(String numCpfCnpj) throws SicoobDDAException;
}
