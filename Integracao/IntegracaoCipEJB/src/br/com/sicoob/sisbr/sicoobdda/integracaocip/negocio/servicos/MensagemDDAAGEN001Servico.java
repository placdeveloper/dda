/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemADDAAGEN001Servico.java
 * Data Criação:    May 5, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * MensagemADDAAGEN001Servico é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public interface MensagemDDAAGEN001Servico extends IntegracaoCipServico {

    /**
     * Método responsável por incluir uma mensagem AGEN001
     * 
     * @throws BancoobException void
     * 
     */
    void incluir(String mensagemEco) throws BancoobException;

}
