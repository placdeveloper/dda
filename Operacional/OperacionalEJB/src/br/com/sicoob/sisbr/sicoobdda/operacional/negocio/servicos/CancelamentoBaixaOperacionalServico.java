/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         CadastrarBeneficiarioServico.java
 * Data Criação:    May 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * Interface que define o servico padrao do sistema CancelamentoBaixaOperacionalServico
 * 
 * @author Sicoob
 */
public interface CancelamentoBaixaOperacionalServico extends OperacionalServico {

    /**
     * Método responsável por cancelar a baixa operacional
     * 
     * @param codigoBarras
     * @param numCooperativa
     * @param valorPago
     * @throws ComumException
     * @throws ComumNegocioException
     */
    void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws ComumException, ComumNegocioException;

}