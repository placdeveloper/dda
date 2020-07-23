/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ProcessarContingenciaMensagmServico.java
 * Data Criação:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * ProcessarContingenciaMensagmServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface ProcessarContingenciaMensagemServico extends IntegracaoCipServico {

    /**
     * Método responsável por processar a contingência de mensagens.
     * 
     * @param listaIdMensagem
     * @throws BancoobException void
     * 
     */
    void processarMensagemContingencia(List<Long> listaIdMensagem) throws BancoobException;

    /**
     * Método responsável por processar a contingência de mensagens (SWS).
     * 
     * @param listaIdMensagem
     * @param dataMovimento
     * @param codTipoMensagem
     * @return Se processado com sucesso NULL, se não po nome do arquivo de erro que foi gerado.
     * @throws BancoobException String
     * 
     */
    String processarMensagemContingenciaBatch(List<Long> listaIdMensagem, DateTimeDB dataMovimento, String codTipoMensagem) throws BancoobException;

}
