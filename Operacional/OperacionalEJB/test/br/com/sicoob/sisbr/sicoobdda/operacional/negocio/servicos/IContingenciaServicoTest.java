/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servico
 * Arquivo:         IContingenciaServicoTest.java
 * Data Criação:    Jan 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;

/**
 * IContingenciaServicoTest
 * 
 * @author Danilo.Barros
 */
public interface IContingenciaServicoTest {
    void deveRetornarListaContingencias() throws OperacionalNoResultException, ComumException;

    void deveIncluirContingenciaHabilitada() throws BancoobException;

    void deveIncluirContingenciaDesabilitada() throws BancoobException;

    void deveRetornarNumeroInteiro() throws ComumException;

    void deveAtualizarValorParametro() throws ComumException;

    void deveRetornarIdContingencia() throws ComumException;
}
