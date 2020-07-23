/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         AdministrativoServico.java
 * Data Criação:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * AdministrativoServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface AdministrativoServico extends OperacionalServico {

    /**
     * Método responsável por atualizar a situação do boleto pelo SWS Administrativo.
     * 
     * @throws ComumException void
     * 
     */
    void atualizarSituacaoBoletos() throws ComumException;

}
