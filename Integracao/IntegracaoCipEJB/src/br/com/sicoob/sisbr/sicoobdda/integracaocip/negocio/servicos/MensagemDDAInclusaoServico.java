/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos
 * Arquivo:         MensagemDDAInclusaoServico.java
 * Data Criação:    Outubro 01, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * MensagemDDAInclusaoServico
 * 
 * @author Francisco.marcio
 */
public interface MensagemDDAInclusaoServico extends IntegracaoCipServico {

    /**
     * Método responsável por persistir MensagemDDA depois de ter ocorrido algum erro no processamento do xml
     * 
     * @param String xmlRecebido
     * @return MensagemDDA
     * 
     */
    void incluirMensagemDDA(String xmlRecebido) throws BancoobException;
}
