/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         PagadorAPIServico.java
 * Data Criação:    May 9, 2016
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
 * PagadorAPIServico é responsável por
 * 
 * @author Daniel.Basso
 */
public interface BeneficiarioAPIServico extends SicoobDDAServico {

    /**
     * Método responsável por cadastrar o beneficiário na CIP
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
     * Método responsável por cadastrar o beneficiário na CIP
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
     * Método responsável por alterar o cadastro do beneficiário na CIP
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
     * étodo responsável por alterar o cadastro do beneficiário na CIP
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
     * Método responsável por excluir convenio do beneficiário na CIP
     * 
     * @param numCliente
     * @param numCooperativa
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException
     */
    void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por excluir convenio do beneficiário na CIP
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
     * Método responsável por processar a alteração de Situação do Beneficiário.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws CobrancaBancariaException RetornoAlterarSituacaoBeneficiarioAPIEnum
     * 
     */
    RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto, Short idCanal)
            throws SicoobDDAException;

    /**
     * Método responsável por processar a alteração de Situação do Beneficiário.
     * 
     * @param alteraSitBeneficiarioDto
     * @return
     * @throws SicoobDDAException RetornoAlterarSituacaoBeneficiarioAPIEnum
     * 
     */
    RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto) throws SicoobDDAException;

    /**
     * Método responsável por returnar válido se a situação do beneficiário for diferente de Inapto
     * 
     * @param numCpfCnpj
     * @return
     * @throws ComumException
     */
    void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws SicoobDDAException, SicoobDDANegocioException;

    /**
     * Método responsável por verificar se o beneficiario esta na CIP
     * 
     * @param numCpfCnpj
     * @return
     * @throws SicoobDDAException
     * @throws SicoobDDANegocioException Boolean
     * 
     */
    Boolean beneficiarioEstaNaCip(String numCpfCnpj) throws SicoobDDAException;
}
