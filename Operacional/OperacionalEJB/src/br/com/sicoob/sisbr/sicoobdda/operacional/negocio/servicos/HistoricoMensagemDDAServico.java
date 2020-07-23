package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Interface que define o servico padrão da HistoricoMensagemDDAServico
 * 
 * @author George.santos
 */
public interface HistoricoMensagemDDAServico extends OperacionalServico {

    /**
     * Método responsável por por fazer todo o processo do arquivamento
     * 
     * Copiar as mensagens para uma tabela de historico
     * 
     * Expurgar as mensagens da tabela principal
     * 
     * @throws ComumException void
     * 
     */
    void arquivarMensagensDDA() throws ComumException;
}