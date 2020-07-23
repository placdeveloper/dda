/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemDDA0401Servico.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * MensagemDDA0401Servico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface MensagemDDA0401Servico extends IntegracaoCipServico {

    /**
     * Método responsável por incluir a mensagem
     * 
     * @param codTipoHorario
     * @param dataReferencia
     * @param numPrioridadeEnvio
     * @throws BancoobException
     */
    void incluir(String codTipoHorario, DateTimeDB dataReferencia, Integer numPrioridadeEnvio) throws BancoobException;

}
