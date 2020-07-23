/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemSerie0200Servico.java
 * Data Criação:    Jan 13, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;

/**
 * MensagemSerie0200Servico é responsável por
 * 
 * @author felipe.rosa
 */
public interface MensagemSerie0200Servico extends IntegracaoCipServico {

    /**
     * Método responsável por incluir e postar na fila uma mensagem série 0200: DDA0200, DDA0214, DDA0215
     * 
     * @param cadastroDto
     * @param codTipoMensagem
     * @param numPrioridadeEnvio
     * @throws BancoobException
     */
    void incluir(CadastroSerie0200Dto cadastroDto, String codTipoMensagem, Integer numPrioridadeEnvio) throws BancoobException;

}
