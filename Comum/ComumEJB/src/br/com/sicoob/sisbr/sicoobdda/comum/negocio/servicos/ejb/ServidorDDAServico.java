/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         ServidorDDAServico.java
 * Data Criação:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDAServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ServidorDDAServico extends ComumCrudServico<ServidorDDA> {

    /**
     * Método responsável por obter o servidor DDA (localhost).
     * 
     * Será antes, validado a existência do mesmo no Cache Central, depois no banco de dados. Se não houver em nenhuma das opções ele será incluído.
     * 
     * Não trabalharemos com lista de servidores para o Cache Central, pois ele sempre estará a procura de um (dele mesmo).
     * 
     * @return
     * @throws ComumException ServidorDDA
     * 
     */
    ServidorDDA obterServidor() throws ComumException;

    /**
     * Método responsável por listar os servidores ativos.
     * 
     * @return
     * @throws ComumException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarAtivo() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> listarServidorDDA() throws ComumException;

    /**
     * Método responsável por
     * 
     * @param filtro
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<ServidorDDA>
     * 
     */
    List<ServidorDDA> pesquisarServidorDDA(ServidorDDA filtro) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por
     * 
     * @param servidorDDA
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    void alterarServidorDDA(ServidorDDA servidorDDA) throws ComumException, ComumNegocioException;

}
