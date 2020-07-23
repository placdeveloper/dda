/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         ExcluirRelacionamentoBeneficiarioServico.java
 * Data Cria��o:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * ExcluirRelacionamentoBeneficiarioServico
 * 
 * @author Rafael.Silva
 */
public interface ExcluirRelacionamentoBeneficiarioServico extends OperacionalServico {

    /**
     * M�todo respons�vel por validar os dados recebidos e criar a MensagemDDA0503
     * 
     * @param numCpfCnpj
     * @throws ComumException void
     * 
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, Long numIdentBeneficiario, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException;

    /**
     * M�todo respons�vel por criar a MensagemDDA0503, porem sem a valida��o do Beneficiario, utilizado quando a mensagem de inclusao do beneficiario ainda nao
     * foi processado pela CIP.
     * 
     * @param numCpfCnpj
     * @throws ComumNegocioException void
     * 
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException, ComumException;
}
