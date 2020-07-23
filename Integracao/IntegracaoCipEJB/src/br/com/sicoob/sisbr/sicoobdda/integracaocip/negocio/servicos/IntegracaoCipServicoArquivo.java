/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         IntegracaoCipServico.java
 * Data Criação:    May 21, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;

/**
 * Interface que define o servico padrao do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public interface IntegracaoCipServicoArquivo extends IntegracaoCipServico {

    /**
     * Método responsável por obter SISARQ do Arquivo para envio a CIP.
     * 
     * @param logEnvioArquivoDDA
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException;

}