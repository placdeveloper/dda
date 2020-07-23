/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces
 * Arquivo:         ServidorDDAServicoLocal.java
 * Data Cria��o:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDAServicoLocal � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ServidorDDAServicoLocal extends ServidorDDAServico {

    /**
     * M�todo respons�vel por
     * 
     * @param nomeServidor
     * @return
     * @throws ComumException ServidorDDA
     * 
     */
    ServidorDDA incluirServidor(String nomeServidor) throws ComumException;

}
