/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         CacheServico.java
 * Data Cria��o:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * CacheServico � respons�vel por 
 * 
 * @author Felipe.Rosa
 */
public interface CacheServico extends ComumServico {

    /**
     * Prepara a execu��o dos servi�os. Uma dessas prepara��es � o pr�ximo hor�rio de execu��o que � feito com base no par�metro. Se o valor deste par�metro for
     * vazio, � utilizado o par�metro padr�o, Para esses par�metros s�o esperados minutos que ser�o adicionados ao hor�rio atual do qual indicar� a pr�ximo
     * execu��o dos servi�os de sincroniza��o do cache.
     * 
     * @throws ComumException void
     */
    void carregar() throws ComumException;

}
