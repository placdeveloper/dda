/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         CadastrarBeneficiarioServico.java
 * Data Cria��o:    May 19, 2015
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
     * M�todo respons�vel por cadastrar o benefici�rio vindo da tela de reenvio.
     * 
     * @param numCpfCnpj
     * @param listaNumCooperativa
     * @throws ComumException void
     * 
     */
    void processarReenvioMensagemCadastroBeneficiario(String numCpfCnpj, List<Integer> listaNumCooperativa, Short idCanal) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por cadastrar o benefici�rio por mensagem de conting�ncia
     * 
     * @param idBeneficiario
     * @throws ComumException void
     * 
     */
    void processarCadastroBeneficiario(Long idBeneficiario, Short idCanal) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por cadastrar o benefici�rio pelo CedenteServico
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