/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.comum.negocio.servicos
 * Arquivo:         EmailServico.java
 * Data Cria��o:    Jun 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * EmailServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface EmailServico extends ComumServico {

    /**
     * M�todo respons�vel por
     * 
     * @param idParametroEmailHabilitado
     * @param idParametroAssunto
     * @param mensagem
     * @throws ComumException void
     * 
     */
    void enviar(Long idParametroEmailHabilitado, Long idParametroAssunto, String mensagem) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param remetente
     * @param destinatario
     * @param assunto
     * @param mensagem
     * @throws ComumException void
     * 
     */
    void enviar(String remetente, String destinatario, String assunto, String mensagem) throws ComumException;

}
