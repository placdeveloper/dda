/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         CacheServico.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * CacheServico é responsável por 
 * 
 * @author Felipe.Rosa
 */
public interface CacheServico extends ComumServico {

    /**
     * Prepara a execução dos serviços. Uma dessas preparações é o próximo horário de execução que é feito com base no parâmetro. Se o valor deste parâmetro for
     * vazio, é utilizado o parâmetro padrão, Para esses parâmetros são esperados minutos que serão adicionados ao horário atual do qual indicará a próximo
     * execução dos serviços de sincronização do cache.
     * 
     * @throws ComumException void
     */
    void carregar() throws ComumException;

}
