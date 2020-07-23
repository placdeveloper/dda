/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemSerie0200Servico.java
 * Data Cria��o:    Jan 13, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;

/**
 * MensagemSerie0200Servico � respons�vel por
 * 
 * @author felipe.rosa
 */
public interface MensagemSerie0200Servico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por incluir e postar na fila uma mensagem s�rie 0200: DDA0200, DDA0214, DDA0215
     * 
     * @param cadastroDto
     * @param codTipoMensagem
     * @param numPrioridadeEnvio
     * @throws BancoobException
     */
    void incluir(CadastroSerie0200Dto cadastroDto, String codTipoMensagem, Integer numPrioridadeEnvio) throws BancoobException;

}
