/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         ServidorDDAServico.java
 * Data Cria��o:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDAServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ServidorDDAServico extends ComumCrudServico<ServidorDDA> {

    /**
     * M�todo respons�vel por obter o servidor DDA (localhost).
     * 
     * Ser� antes, validado a exist�ncia do mesmo no Cache Central, depois no banco de dados. Se n�o houver em nenhuma das op��es ele ser� inclu�do.
     * 
     * N�o trabalharemos com lista de servidores para o Cache Central, pois ele sempre estar� a procura de um (dele mesmo).
     * 
     * @return
     * @throws ComumException ServidorDDA
     * 
     */
    ServidorDDA obterServidor() throws ComumException;

    /**
     * M�todo respons�vel por listar os servidores ativos.
     * 
     * @return
     * @throws ComumException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarAtivo() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws ComumException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarServidorDDA() throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param filtro
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> pesquisarServidorDDA(ServidorDDA filtro) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por
     * 
     * @param servidorDDA
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void alterarServidorDDA(ServidorDDA servidorDDA) throws ComumException, ComumNegocioException;

}
