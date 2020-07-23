/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         CadastrarBeneficiarioServico.java
 * Data Criação:    May 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * Interface que define o servico padrao do sistema CadastroBeneficiario
 * 
 * @author Sicoob
 */
public interface CadastrarBeneficiarioServico extends OperacionalServico {

    /**
     * Método responsável por cadastrar o beneficiário vindo da tela de reenvio.
     * 
     * @param numCpfCnpj
     * @param listaNumCooperativa
     * @throws ComumException void
     * 
     */
    void processarReenvioMensagemCadastroBeneficiario(String numCpfCnpj, List<Integer> listaNumCooperativa, Short idCanal) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por cadastrar o beneficiário por mensagem de contingência
     * 
     * @param idBeneficiario
     * @throws ComumException void
     * 
     */
    void processarCadastroBeneficiario(Long idBeneficiario, Short idCanal) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por cadastrar o beneficiário pelo CedenteServico
     * 
     * @param numCliente
     * @param numCpfCnpj
     * @param numCooperativa
     * @throws ComumException void
     * 
     */
    void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException;

}