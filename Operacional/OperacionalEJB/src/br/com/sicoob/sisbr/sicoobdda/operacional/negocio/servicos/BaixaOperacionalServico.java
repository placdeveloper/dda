package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;

/**
 * Interface que define o servico padrão da BaixaOperacionalServico
 * 
 * @author Rodrigo.Neri
 */
public interface BaixaOperacionalServico extends OperacionalServico {

    /**
     * Método responsável por incluir a mensagem de baixa operacional
     * 
     * @param mensagem
     * @param numBanco
     * @param canal
     * @param aceitaPagamentoParcial
     * @param dataMovimento
     * @param idInstituicao
     * @throws ComumException
     */
    void incluir(MensagemDDABaixaOperacional mensagem, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTimeDB dataMovimento, Integer idInstituicao)
            throws ComumException;

    /**
     * Método responsável por incluir a mensagem de baixa operacional
     * 
     * @param mensagem
     * @param numBanco
     * @param canal
     * @param aceitaPagamentoParcial
     * @param dataMovimento
     * @param idInstituicao
     * @param codMeioPagamento
     * @throws ComumException
     */
    void incluir(MensagemDDABaixaOperacional mensagem, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTimeDB dataMovimento, Integer idInstituicao,
            short codMeioPagamento) throws ComumException;

}