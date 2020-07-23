/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces
 * Arquivo:         ProcessarContingenciaMensagemServicoLocal.java
 * Data Cria��o:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico;

/**
 * ProcessarContingenciaMensagemServicoLocal � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface ProcessarContingenciaMensagemServicoLocal extends ProcessarContingenciaMensagemServico {

    /**
     * @param idMensagem
     * @throws BancoobException void
     * 
     */
    void processarMensagemContingencia(Long idMensagem) throws BancoobException;

}
