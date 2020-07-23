/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         AlterarCadastroBeneficiarioServico.java
 * Data Cria��o:    May 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.tipos.DateTime;

/**
 * AlterarCadastroBeneficiarioServico
 * 
 * @author Rafael.Silva
 */
public interface AlterarCadastroBeneficiarioServico extends OperacionalServico {

    /**
     * M�todo respons�vel por alterar o cadastro do beneficiario que tiveram sua sit��o alterada no CedenteServico
     * 
     * @param numCliente
     * @param idInstituicao
     * @param codSituacaoCedente
     * @param dataCancelamento
     */
    void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por alterar o cadastro de beneficiarios que foram cadastrados no CedenteServi�o mas j� estavam cadastrados na base DDA.
     * 
     * @param numCliente
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param beneficiario
     * 
     */
    void alterarCadastroBeneficiario(Long numCliente, Integer numCooperativa, Long codSituacaoCedente, BeneficiarioDDA beneficiario, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por reenviar mensagem de altera��o de cadastro.
     * 
     * @param beneficiario
     * @param listaConvenio
     * 
     */
    void processarReenvioAlterarCadastroBeneficiario(BeneficiarioDDA beneficiario, List<ConvenioAlteracaoDDADto> listaConvenio, Short idCanal) throws ComumException,
            ComumNegocioException;

    /**
     * 
     * @param numCliente
     * @param numCooperativa
     * 
     */
    void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException, ComumNegocioException;
}
